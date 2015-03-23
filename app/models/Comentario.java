package models;

import javax.persistence.*;

@Entity (name = "Comentario")
public class Comentario{
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn
    private Usuario usuario;

    @Column
    private String texto;

    @Transient
    private int LIMITE_CARACTERES = 100;

    public Comentario(Usuario usuario, String texto) throws IllegalArgumentException{
        if(texto.length() > LIMITE_CARACTERES){
            throw new IllegalArgumentException("Máximo de 100 caracteres.");
        }
        if(usuario == null){
            throw new IllegalArgumentException("Usuario inválido.");
        }
        this.texto = texto;
        this.usuario = usuario;
    }
    
    public Comentario(){
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }
}