package models;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Filipe on 19/03/2015.
 */

@Entity(name = "DicaDisciplina")
public class DicaDisciplina extends Dica{

    @Column
    private String nome;
    @Column
    private String razao;

    public DicaDisciplina(){}

    public DicaDisciplina(Usuario user, String nome, String razao){
        super(user);
        this.nome = nome;
        this.razao = razao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    @Override
    public String toString() {
        return "Disciplina: " + nome + ".\n" + "Razão da utilidade: " + razao + ".";
    }

    @Override
    public String getTipo(){
        return "Disciplina Importante";
    }
}
