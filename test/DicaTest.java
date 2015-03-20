import models.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Filipe on 19/03/2015.
 */


public class DicaTest {


    @Test
    public void testeURL(){

        Usuario u = null;

        try{
            u = new Usuario("fpc@gmail.com", "12345578888", "Filipe", "Coutinho");
        }catch(Exception e){

        }
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
}
