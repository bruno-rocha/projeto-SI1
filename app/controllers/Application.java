package controllers;

import models.Tema;
import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.mvc.Result;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import views.html.index;

import java.util.List;

public class Application extends Controller {
    private static GenericDAO dao = new GenericDAOImpl();
    private static List<Tema> temas;

    @Transactional
    public static Result index() {
        if (session().get("usuarioAtual") == null) {
            return redirect(routes.Login.show());
        }
        getTemas();
        return ok(index.render(temas, Login.getUsuarioAtual()));
    }

    @Transactional
    public static void getTemas() {
        temas = dao.findAllByClassName("Tema");
        dao.flush();
    }

/*
    @Transactional
    public static boolean salvaUsuario(Usuario usuario) throws NoSuchAlgorithmException {
        if (validaEmail(usuario.getEmail()) && validaSenha(usuario.getSenha()) && validaNome(usuario.getNome())) {
            usuario.setSenha(encryptaSenha(usuario.getSenha()));
            boolean operacao = dao.persist(usuario);
            dao.flush();
            return operacao;
        }
        return false;
    }
*/
/*
    @Transactional
    public static Usuario recuperaUsuario(String email) {
        List<Usuario> usuarios = dao.findByAttributeName(Usuario.class.getName(), "email", email);
        if (usuarios.size() > 0) {
            return usuarios.get(0);
        } else {
            return null;
        }
    }
/*
    @Transactional
    public static boolean adicionaDica(Dica dica) {
        if (validaDica(dica)){
            boolean operacao = dao.persist(dica);
            dao.flush();
            return operacao;
        }
        return false;
    }

    @Transactional
    public static void removerDica(Dica dica) {
        dao.removeById(Dica.class, dica.getDicaID());
        dao.flush();
    }

    @Transactional
    public static void atualizarDica(Dica newDica) {
        dao.merge(newDica);
        dao.flush();
    }

    @Transactional
    public static Dica recuperaDica(long id) {
        return dao.findByEntityId(Dica.class, id);
    }

    @Transactional
    public static boolean adicionaAvaliacao(Avaliacao avaliacao) {
        return false;
    }

    @Transactional
    public static Avaliacao recuperaAvaliacao(Usuario usuario, Tema tema) {
        return null;
    }

    @Transactional
    public static float recuperaMediaDeAvaliacoes() {
        return 0.0f;
    }

    @Transactional
    public static float recuperaMedianaDeAvaliacoes() {
        return 0.0f;
    }

    @Transactional
    public static boolean adicionaVoto(Voto voto) {
        boolean operacao = false;
        if (validaVoto(voto)){
            if (recuperaVotoPorUsuarioETema(voto.getUsuario(), voto.getDica()) == null ){
                operacao = dao.persist(voto);
                return operacao;
            } else {
                dao.merge(voto);
                operacao = true;
            }
            dao.flush();
        }
        return operacao;
    }

    public static Voto recuperaVotoPorUsuarioETema(String email, long dicaID){
        List<Voto> votos1 = dao.findByAttributeName(Voto.class.getName(), "usuario", email);
        List<Voto> votos2 = dao.findByAttributeName(Voto.class.getName(), "dica", String.valueOf(dicaID));
        votos1.retainAll(votos2);
        if (votos1.size() > 0) {
            return votos1.get(0);
        } else {
            return null;
        }

    }

    private static boolean validaVoto(Voto voto) {
        if (recuperaUsuario(voto.getUsuario())!= null && recuperaDica(voto.getDica()) != null){
            return true;
        }
        return false;
    }


    @Transactional
    public static boolean adicionaMetaDica(MetaDica dica) {
        return false;
    }


    @Transactional
    public static List<MetaDica> recuperaMetaDicasPorDisciplina(Disciplina disciplina) {
        return null;
    }


    @Transactional
    public static void denunciaDica(Dica dica) {
    }


    @Transactional
    public static List<Dica> recuperaDicasPorTema(Long idTema) {
        List<Dica> ld = dao.findByAttributeName(Dica.class.getName(), "temaID", String.valueOf(idTema));
        if (ld != null) {
            return ld;
        } else {
            return null;
        }
    }


    @Transactional
    public static void fechaDica(Dica dica) {
    }

    @Transactional
    public static List<Disciplina> getListaDisciplinas() {
        return dao.findAllByClassName(Disciplina.class.getName());
    }

    @Transactional
    public static List<Usuario> getListaDeUsuarios() {
        return dao.findAllByClassName(Usuario.class.getName());
    }


    @Transactional
    public static Disciplina getDisciplinaNoBD(String key, String value) {
        List<Disciplina> l = dao.findByAttributeName(Disciplina.class.getName(), key, value);
        if (l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }


    @Transactional
    public static Tema recuperaTemaPeloNome(String nome) {
        List<Tema> ld = dao.findByAttributeName(Tema.class.getName(), "nome", nome);
        if (ld.size() > 0) {
            return ld.get(0);
        } else {
            return null;
        }
    }


    @Transactional
    public static void adicionaTema(Tema tema) {
        if (validaNome(tema.getNome())) {
            dao.persist(tema);
            dao.flush();
        }
    }


    public static String encryptaSenha(String senha) throws NoSuchAlgorithmException {

        MessageDigest msg = MessageDigest.getInstance("MD5");
        msg.update(senha.getBytes(), 0, senha.length());
        return new BigInteger(1, msg.digest()).toString(16);
    }


    private static boolean validaEmail(String email) {

        if (email != null && recuperaUsuario(email) == null) {
            return true;
        }
        return false;
    }

    private static boolean validaSenha(String senha) {
        if (senha != null && !senha.equals("")) {
            return true;
        }
        return false;
    }

    private static boolean validaNome(String nome) {
        if (nome != null && !nome.equals("")) {
            return true;
        }
        return false;
    }

    private static boolean validaDica(Dica dica) {
        if (!dica.getConhecimento().equals("") || !dica.getPreRequisito().equals("") ||
                !dica.getRazao().equals("") || !dica.getConselho().equals("") ||
                (!dica.getMaterial().equals("") && validaURL(dica.getMaterial()))) {
            return true;
        }
        return false;
    }

    private static boolean validaURL(String material) {

        if (material.matches("http://(.*).com") || material.matches("htt://(.*).com.br") ||
                material.matches("http://(.*).edu") || material.matches("http://(.*).edu.br")) {
            return true;
        }
        return false;
    }

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
    }
    @Transactional
    public static Result login() {
        return ok(login.render(""));
    }

    @Transactional
    public static Result auth() throws NoSuchAlgorithmException {
        DynamicForm requestData = Form.form().bindFromRequest();
        final String email, senha;
        email = requestData.get("email");
        senha = requestData.get("senha");
        String md5 = Portal.encryptaSenha(senha);
        Usuario u = Portal.recuperaUsuario(email);
        if (u != null ) {
            if (md5.equals(u.getSenha())){
                session().clear();
                session("email", email);
                return app(u);
            } else {
                return ok(login.render("Senha inválida"));
            }
        }
        return ok(login.render("Usuario nao existe"));
    }


    @Transactional
    private static Result app(Usuario usuario) {
        return ok(dashboard.render(Portal.getListaDisciplinas(), usuario));
    }


    public static Result logarComFacebook() {
        return redirect(loginFacebook.getLoginRedirectURL());
    }

    @Transactional
    public static Result logarComFace(String code) throws IOException, NoSuchAlgorithmException {
        Logger.info("CODE:" + code);
        UsuarioFacebook ufb = loginFacebook.obterUsuarioFacebook(code);
        Usuario us = Portal.recuperaUsuario(ufb.getEmail());
        if(us==null){
            us = new Usuario(ufb.getEmail(),"12345", ufb.getName());
            Portal.salvaUsuario(us);
        }
        if (us!=null) {
            session().clear();
            session("email", us.getEmail());
            return ok(dashboard.render(Portal.getListaDisciplinas(),us));
        } else {
            return ok(login.render(""));
        }

    }*/
}
