import models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TemaTest {

    private Tema t;
    private Usuario u, u2, u3;

    @Before
    public void init(){
        t = new Tema("Teste");
        u = new Usuario("fpc@gmail.com", "12345578888", "Filipe", "Coutinho");
        u2 = new Usuario("fp@gmail.com", "12345578888", "Filipe", "Coutinho");
        u3 = new Usuario("f@gmail.com", "12345578888", "Filipe", "Coutinho");
    }

    @Test
    public void testeDificuldade(){

        t.addVoto(new Voto(u, 1));
        Assert.assertEquals(t.getMediaDificuldade(), "1,00");
        Assert.assertEquals(t.getMedianaDificuldade(), "1");

        t.addVoto(new Voto(u2, 2));
        Assert.assertEquals(t.getMediaDificuldade(), "1,50");
        Assert.assertEquals(t.getMedianaDificuldade(), "1,50");

        t.addVoto(new Voto(u3, -1));
        Assert.assertEquals(t.getMediaDificuldade(), "0,67");
        Assert.assertEquals(t.getMedianaDificuldade(), "1");


    }

}
