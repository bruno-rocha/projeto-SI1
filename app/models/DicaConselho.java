package models;

import javax.persistence.*;

@Entity(name="conselho")
public class DicaConselho extends Dica {
    @Column
    private String texto;


    public DicaConselho(){}

    public DicaConselho(Usuario user, String texto){
        super(user);
        setTexto(texto);
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


    @Override
    public String getTipo(){
        return "Conselho sobre o tema";
    }



    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + texto.hashCode();
        return result;
    }
}
