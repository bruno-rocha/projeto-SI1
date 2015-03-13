package models;

import javax.persistence.*;
import java.lang.Exception;

@Entity(name = "Voto")
public class Voto{
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Dificuldade dificuldade;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="usuario")
    private Usuario usuario;

    public Voto(Usuario usuario, Dificuldade dificuldade) throws Exception{
        if(usuario == null) throw new Exception("Usuario inválido.");
        if(dificuldade.getValor() < -2 || dificuldade.getValor() > 2) throw new Exception("Dificuldade inválida.");
        this.dificuldade = dificuldade;
        this.usuario = usuario;
    }

    public Voto() {
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}