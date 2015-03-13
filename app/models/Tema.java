package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Tema{

    private String nome;
    private List<Voto> votos;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="Dificuldade")
    private int dificuldade;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Dicas")
    private List<Dica> dicas;


    public Tema(String nome){
        this.nome = nome;
        this.dificuldade = 0;
        this.dicas = new ArrayList<Dica>();

    }

    public Tema(){

    }
}