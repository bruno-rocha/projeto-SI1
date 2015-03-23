package models;

import javax.persistence.*;

@Entity(name="material")
public class DicaMaterial extends Dica {
    @Column
    private String url;

    public DicaMaterial(){}

    public DicaMaterial(Usuario user, String url) throws IllegalArgumentException {
        super(user);
        setURL(url);
    }


    @Override
    public String getTipo(){
        return "Link de material útil";
    }

    @Override
    public String getTexto() {
        return url;
    }

    public void setURL(String url) throws IllegalArgumentException {
        if(!url.startsWith("http://") && (!url.endsWith(".com") || !url.endsWith(".com.br") ||
                !url.endsWith(".edu") || !url.endsWith(".edu.br")))
            throw new IllegalArgumentException("URL precisa começar com http:// e deve terminar com: .com, .com.br, .edu ou .edu.br .");
        this.url = url;
    }



    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
