import models.Usuario;
import org.junit.Test;

public class UsuarioTest {

    @Test
    public void test(){
        try {
            Usuario u = new Usuario("fpc@gmail.com", "1234557","Filipe", "Coutinho");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}