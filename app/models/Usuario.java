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
    @Column
    private String sobrenome;

    public Usuario() {
    }

    public Usuario(String email, String senha, String nome, String sobrenome) throws Exception{

        setEmail(email);
        setNome(nome);
        setSenha(senha);
        setSobrenome(sobrenome);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email == "") throw new Exception("Email inválido.");
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String pass) throws Exception{
        if (senha.length() < 8 || senha.length() > 20) throw new Exception("Senha deve ter entre 8 e 20 caracteres.");
        this.senha = pass;
    }

    public String getNomeCompleto(){
        return nome + " " + sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception{
        if (nome == "") throw new Exception("Nome inválido.");
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) throws Exception{
        if (sobrenome == "") throw new Exception("Sobrenome inválido.");
        this.sobrenome = sobrenome;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}