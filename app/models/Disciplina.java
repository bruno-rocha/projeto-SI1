package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filipe on 19/03/2015.
 */

@Entity(name = "Disciplina")
public class Disciplina {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nome;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Temas")
    private List<Tema> temas;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="MetaDicas")
    private List<Metadica> metadicas;

    public Disciplina(){}

    public Disciplina(String nome){
        this.nome = nome;
        temas = new ArrayList<Tema>();
        metadicas = new ArrayList<Metadica>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public List<Metadica> getMetadicas() {
        return metadicas;
    }

    public void addTema(Tema t){
        temas.add(t);
    }

    public void addMetadica(Metadica m){
        metadicas.add(m);
    }

}
