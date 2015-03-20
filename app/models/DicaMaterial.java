package models;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Filipe on 19/03/2015.
 */

@Entity(name = "DicaMaterial")
public class DicaMaterial extends Dica {

    @Column
    private String url;

    public DicaMaterial(){}

    public DicaMaterial(Usuario user, String url) throws Exception{
        super(user);

        //condicao url
        if(url != "http://%s.com") throw new Exception("URL invalida.");
        this.url = url;
    }

    @Override
    public String toString() {
        return "Link do Material: " + url + " .";
    }

    @Override
    public String getTipo(){
        return "Link de material útil";
    }

}
