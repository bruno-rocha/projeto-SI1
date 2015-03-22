import models.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

    @Test
    public void test(){

        Usuario u = new Usuario("fpc@gmail.com", "1234557","Filipe", "Coutinho");
        Assert.assertEquals(u.getEmail(), "fpc@gmail.com");
        Assert.assertEquals(u.getNome(), "Filipe");
        Assert.assertEquals(u.getSenha(), "1234557");
        Assert.assertEquals(u.getSobrenome(), "Coutinho");
        Assert.assertEquals(u.getNomeCompleto(), "Filipe Coutinho");

    }
}