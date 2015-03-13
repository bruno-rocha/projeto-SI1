package models;

import javax.persistence.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class Tema{

    private String nome;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Votos")
    private List<Voto> votos;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Dicas")
    private List<Dica> dicas;


    public Tema(String nome){

        this.nome = nome;
        this.dicas = new ArrayList<Dica>();
        this.votos = new ArrayList<Voto>();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public List<Dica> getDicas() {
        return dicas;
    }

    public void addVoto(Voto vt){
        //checar se usuario ja votou e usar mudaVoto
        votos.add(vt);
    }

    public void addDica (Dica d){
        dicas.add(d);
    }

    private void mudaVoto(User u, int nota){
        for (Voto v: votos){
            if(v.getUsuario().equals(u)){
                v.setDificuldade(nota);
            }
        }
    }
}