package models;

import javax.persistence.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Tema")
public class Tema{
    @Id
    @GeneratedValue
    private Long id;

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

    public Tema() {
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
        //checa se o Usuário já votou.
        boolean flag = true;
        for (Voto v: votos){
            if(v.getUsuario().equals(vt.getUsuario())){
                v.setDificuldade(vt.getDificuldade());
                flag = false;
            }
        }
        if(flag) votos.add(vt);
    }

    public void addDica (Dica d){
        dicas.add(d);
    }

}