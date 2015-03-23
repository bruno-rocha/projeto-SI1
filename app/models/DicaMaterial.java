package models;

import javax.persistence.*;

@Entity(name="material")
public class DicaMaterial extends Dica {
    @Column
    private String url;

    public DicaMaterial(){}

    public DicaMaterial(Usuario user, String url) throws Exception{
        super(user);
        setURL(url);
    }

    @Override
    public String toString() {
        return "Link do Material: " + url + " .";
    }

    @Override
    public String getTipo(){
        return "Link de material útil";
    }

    @Override
    public String getTexto() {
        return url;
    }

    public void setURL(String url) throws Exception {

        if(!url.startsWith("http://")) throw new Exception("URL precisa começar com http:// .");
        if(!url.endsWith(".com") || !url.endsWith(".com.br") || !url.endsWith(".edu") || !url.endsWith(".edu.br"))
            throw new Exception("URL deve terminar com: .com, .com.br, .edu ou .edu.br .");

        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DicaMaterial)) return false;
        if (!super.equals(o)) return false;

        DicaMaterial that = (DicaMaterial) o;

        if (!url.equals(that.url)) return false;
        if (!getUsuario().equals(that.getUsuario())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
