import models.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Filipe on 19/03/2015.
 */


public class DicaTest {


    @Test
    public void testeURL(){

        try{
            new DicaMaterial(new Usuario("fpc@gmail.com", "1234557","Filipe", "Coutinho"), "http://a.edu");
        }catch(Exception e){
        }
    }
}
