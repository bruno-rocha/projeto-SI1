package models;

import javax.persistence.*;
import java.lang.Exception;

@Entity (name = "Comentario")
public class Comentario{

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="usuario")
    private Usuario usuario;

    @Column
    private String texto;

    public Comentario(Usuario usuario, String texto) throws Exception{

        if(texto.length() > 100) throw new Exception("Máximo de 100 caracteres.");
        if(usuario == null) throw new Exception("Usuario inválido.");
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
}