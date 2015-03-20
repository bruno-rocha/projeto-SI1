package models;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Filipe on 19/03/2015.
 */

@Entity(name = "Conselho")
public class DicaConselho extends Dica {

    @Column
    private String texto;


    public DicaConselho(){}

    public DicaConselho(Usuario user, String texto){
        super(user);
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Conselho: " + texto + ".";
    }

    @Override
    public String getTipo(){
        return "Conselho sobre o tema";
    }
}
