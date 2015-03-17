package controllers;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.cadastro;

import java.util.Iterator;
import java.util.List;

import static play.data.Form.form;

public class Cadastro extends Controller {

    private static GenericDAO dao = new GenericDAOImpl();
    static Form<Usuario> cadastroForm = form(Usuario.class).bindFromRequest();

    @Transactional
    public static Result show() {
        return ok(cadastro.render(cadastroForm));
    }

    @Transactional
    public static Result registrar() {

        Usuario user = cadastroForm.bindFromRequest().get();

        if (cadastroForm.hasErrors() || validate(user.getEmail())) {
            flash("fail", "E-mail já está em uso");
            return badRequest(cadastro.render(cadastroForm));
        } else {
            dao.persist(user);
            return redirect(routes.Login.show());
        }
    }

    @Transactional
    private static boolean validate(String email) {
        List<Usuario> usuarios = dao.findByAttributeName("Usuario", "email", email);

        for (Usuario u: usuarios){
            if (u.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
/*
    @Transactional
    public static Result cadastraUsuario() throws NoSuchAlgorithmException {
        DynamicForm requestData = Form.form().bindFromRequest();
        final String email, nome, senha;
        email = requestData.get("email");
        nome = requestData.get("nome");
        senha = requestData.get("senha");
        Usuario u = new Usuario(email, senha, nome);
        if (Portal.salvaUsuario(u)) {
            return Application.login();
        }
        return Application.register();
    }*/
}