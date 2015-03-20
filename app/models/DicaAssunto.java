package models;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Filipe on 19/03/2015.
 */

@Entity(name = "Assunto")
public class DicaAssunto extends Dica {

    @Column
    private String assunto;

    public DicaAssunto(){}

    public DicaAssunto(Usuario user, String assunto){
        super(user);
        this.assunto = assunto;
    }

    public String getAssunto() {
        return assunto;
    }


    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    @Override
    public String toString() {
        return "Assunto: " + assunto + ".";
    }

    @Override
    public String getTipo(){
        return "Assunto Importante";
    }
}
