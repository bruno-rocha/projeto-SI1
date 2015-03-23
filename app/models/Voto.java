package models;

import javax.persistence.*;

@Entity(name = "Voto")
public class Voto{
    @Id
    @GeneratedValue
    private long id;

    @Column
    private int dificuldade;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn
    private Usuario usuario;
/*
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="temaDoVoto")
    private Tema tema;
*/
    public Voto(Usuario usuario, int dificuldade){
        setDificuldade(dificuldade);
        setUsuario(usuario);
    }

    public Voto() {
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade){
        //if(dificuldade < -2 || dificuldade > 2) throw new Exception("Dificuldade inválida.");
        this.dificuldade = dificuldade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Voto voto = (Voto) o;
        if (dificuldade != voto.dificuldade){
            return false;
        }
        if (!usuario.equals(voto.usuario)){
            return false;
        }
        return true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}