package models;

import javax.persistence.*;

@Entity(name = "assunto")
public class DicaAssunto extends Dica {
    @Column
    private String assunto;

    public DicaAssunto(){}

    public DicaAssunto(Usuario user, String assunto){
        super(user);
        setAssunto(assunto);
    }

    @Override
    public String getTexto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DicaAssunto)) return false;
        if (!super.equals(o)) return false;

        DicaAssunto that = (DicaAssunto) o;

        if (!assunto.equals(that.assunto)) return false;
        if (!getUsuario().equals(that.getUsuario())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + assunto.hashCode();
        return result;
    }
}
