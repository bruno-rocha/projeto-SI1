package models;

import javax.persistence.*;

@Entity(name="Usuario")
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;
    @Column
    private String senha;
    @Column
    private String nome;

    public Usuario() {
    }

    public Usuario(String email, String senha, String nome, String sobrenome) throws Exception{

        if (email == "" || nome == "" || senha == "" || sobrenome == "") throw new Exception("Campos em branco.");
        if (senha.length() < 8 || senha.length() > 20) throw new Exception("Senha deve ter entre 8 e 20 caracteres.");

        this.email = email;
        this.nome = nome + " " + sobrenome;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String pass) {
        this.senha = pass;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (!email.equals(usuario.email)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

}