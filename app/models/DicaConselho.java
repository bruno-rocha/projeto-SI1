package models;

import javax.persistence.*;

@Entity
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
    public String toString() {
        return "Conselho: " + texto + ".";
    }

    @Override
    public String getTipo(){
        return "Conselho sobre o tema";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DicaConselho)) return false;
        if (!super.equals(o)) return false;

        DicaConselho that = (DicaConselho) o;

        if (!texto.equals(that.texto)) return false;
        if (!getUsuario().equals(that.getUsuario())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + texto.hashCode();
        return result;
    }
}
