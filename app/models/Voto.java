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

    @ManyToOne(cascade=CascadeType.ALL)
    private Tema tema;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Voto voto = (Voto) o;

        if (dificuldade != voto.dificuldade) return false;
        if (!usuario.equals(voto.usuario)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dificuldade.hashCode();
        result = 31 * result + usuario.hashCode();
        return result;
    }
}