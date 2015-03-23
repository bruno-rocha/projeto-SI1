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
    public String getTipo(){
        return "Assunto Importante";
    }



    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + assunto.hashCode();
        return result;
    }
}
