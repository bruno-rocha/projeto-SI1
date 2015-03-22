package models;

import javax.persistence.*;

@Entity
public class DicaDisciplina extends Dica{
    @Column
    private String nome;


    public DicaDisciplina(){}


    public DicaDisciplina(Usuario user, String nome) {
        super(user);
        setNome(nome);
    }


    @Override
    public String getTexto() {
        return nome;
    }

    @Override
    public String getTipo(){
        return "Disciplina Importante";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DicaDisciplina)) return false;
        if (!super.equals(o)) return false;

        DicaDisciplina that = (DicaDisciplina) o;

        if (!nome.equals(that.nome)) return false;
        if (!getUsuario().equals(that.getUsuario())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + nome.hashCode();
        return result;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
