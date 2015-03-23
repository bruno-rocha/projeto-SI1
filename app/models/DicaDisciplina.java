package models;

import javax.persistence.*;

@Entity(name="dicaDisciplina")
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
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + nome.hashCode();
        return result;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
