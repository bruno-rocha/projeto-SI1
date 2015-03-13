package models;

import javax.persistence.*;

@Entity(name="Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;
    private String senha;
    private String nome;

    public Usuario() {
    }

    public Usuario(String email, String pass, String nome, String sobrenome) {
        this.email = email;
        this.nome = nome + " " + sobrenome;
        this.senha = pass;
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
}