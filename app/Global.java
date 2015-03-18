import models.Tema;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;

import java.util.List;


public class Global extends GlobalSettings {

    private GenericDAO dao = new GenericDAOImpl();

    public void onStart(Application app){
        Logger.info("Aplicacao inicializada - Carregando Temas");
        JPA.withTransaction(new play.libs.F.Callback0() {

            @Override
            public void invoke() throws Throwable {
                if(dao.findAllByClassName("Tema").isEmpty()){
                    Tema t1 = new Tema("Análise x Design");
                    Tema t2 = new Tema("Orientacao a Objetos");
                    Tema t3 = new Tema("GRASP");
                    Tema t4 = new Tema("GoF");
                    Tema t5 = new Tema("Arquitetura");
                    Tema t6 = new Tema("Play");
                    Tema t7 = new Tema("Javascript");
                    Tema t8 = new Tema("HTML+CSS+Bootstrap");
                    Tema t9 = new Tema("Heroku");
                    Tema t10 = new Tema("Labs");
                    Tema t11 = new Tema("Minitestes");
                    Tema t12 = new Tema("Projeto");

                    dao.persist(t1);
                    dao.persist(t2);
                    dao.persist(t3);
                    dao.persist(t4);
                    dao.persist(t5);
                    dao.persist(t6);
                    dao.persist(t7);
                    dao.persist(t8);
                    dao.persist(t9);
                    dao.persist(t10);
                    dao.persist(t11);
                    dao.persist(t12);

                    dao.flush();
                }
            }
        });
    }

    public void onStop(Application app) {
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Logger.info("Aplicação finalizando...");
                List<Tema> temas = dao.findAllByClassName("Tema");

                for (Tema tema : temas) {
                    dao.removeById(Tema.class, tema.getId());
                }
            }
        });
    }

}