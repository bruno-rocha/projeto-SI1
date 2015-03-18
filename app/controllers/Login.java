package controllers;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.login;

import java.util.List;

import static play.data.Form.form;

public class Login extends Controller{
    private static GenericDAO dao = new GenericDAOImpl();
    static Form<Usuario> loginForm = form(Usuario.class).bindFromRequest();
    static Form<Usuario> cadastroForm = form(Usuario.class).bindFromRequest();
    static Usuario usuarioAtual;

    @Transactional
    public static Result show() {
        if (session().get("usuarioAtual") != null) {
            return redirect(routes.Application.index());
        }
        return ok(login.render(loginForm, cadastroForm));
    }

    @Transactional
    public static Result logout() {
        session().clear();
        return show();
    }

    @Transactional
    public static Result autenticar() {
        Usuario u = loginForm.bindFromRequest().get();

        String email = u.getEmail();
        String senha = u.getSenha();

        if (loginForm.hasErrors() || !validarLogin(email, senha)) {
            flash("fail", "Email ou Senha Inválidos");
            return badRequest(login.render(loginForm, cadastroForm));
        } else {
            usuarioAtual = (Usuario) dao.findByAttributeName(
                    "Usuario", "email", u.getEmail()).get(0);
            session().clear();
            session("usuarioAtual", usuarioAtual.getNome());
            return redirect(routes.Application.index());
        }

    }

    @Transactional
    private static boolean validarLogin(String email, String senha) {
        List<Usuario> u = dao.findByAttributeName("Usuario", "email", email);
        if (u == null || u.isEmpty()) {
            return false;
        }
        if (!u.get(0).getSenha().equals(senha)) {
            return false;
        }
        return true;
    }

    @Transactional
    public static Result cadastrar() {
        Usuario user = cadastroForm.bindFromRequest().get();

        if (cadastroForm.hasErrors() || validarCadastro(user.getEmail())) {
            flash("fail", "E-mail já está em uso");
            return badRequest(login.render(loginForm, cadastroForm));
        } else {
            dao.persist(user);
            flash("sucess", "Usuario cadastrado com sucesso!");
            return redirect(routes.Login.show());
        }
    }

    @Transactional
    private static boolean validarCadastro(String email) {
        List<Usuario> usuarios = dao.findByAttributeName("Usuario", "email", email);
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static Usuario getUsuarioAtual() {
        return usuarioAtual;
    }
}
