import models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Filipe on 19/03/2015.
 */


public class DicaTest {

    private Usuario u;

    @Before
    public void initialize() {

        try {
            u = new Usuario("fpc@gmail.com", "12345578888", "Filipe", "Coutinho");
        } catch (Exception e) {
        }
    }


    @Test
    public void testeURL(){

            try{
                new DicaMaterial(u, "dumbledore.edu");
            }catch(Exception e){
                Assert.assertEquals(e.getMessage(), "URL precisa começar com http:// .");
            }

            try{
                new DicaMaterial(u, "http://dumbledore.uk");
            }catch(Exception e){
                Assert.assertEquals(e.getMessage(), "URL deve terminar com: .com, .com.br, .edu ou .edu.br .");
            }

    }

    @Test
    public void testeDisciplina(){

        DicaDisciplina d = new DicaDisciplina(u, "P2");
        Assert.assertEquals("P2", d.getTexto());

    }

    @Test
    public void testeAssunto(){

        DicaAssunto d = new DicaAssunto(u, "Padroes de Projeto");
        Assert.assertEquals("Padroes de Projeto", d.getTexto());

    }

    @Test
    public void testeConselho(){

        DicaConselho d = new DicaConselho(u, "Estude bem HTML");
        Assert.assertEquals("Estude bem HTML", d.getTexto());

    }
}
