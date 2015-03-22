package models;

import javax.persistence.*;

@Entity
public class DicaDisciplina extends Dica{
    @Column
    private String nome;
    @Column
    private String razao;

    public DicaDisciplina(){}

    public DicaDisciplina(Usuario user, String nome, String razao){
        super(user);
        setNome(nome);
        setRazao(razao);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DicaDisciplina)) return false;
        if (!super.equals(o)) return false;

        DicaDisciplina that = (DicaDisciplina) o;

        if (!nome.equals(that.nome)) return false;
        if (!razao.equals(that.razao)) return false;
        if (!getUsuario().equals(that.getUsuario())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + razao.hashCode();
        return result;
    }
}
