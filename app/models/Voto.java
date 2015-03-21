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
        setDificuldade(dificuldade);
        setUsuario(usuario);
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

    public void setUsuario(Usuario usuario) throws Exception{
        if(usuario == null) throw new Exception("Usuario inválido.");
        this.usuario = usuario;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}