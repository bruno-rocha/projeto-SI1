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

    @Column
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

    public double getMediaDificuldade(){

        double total = 0;
        for (Voto e: votos){
            total += e.getDificuldade().getValor();
        }

        return total/votos.size();

    }



    public void addDica (Dica d){
        dicas.add(d);
        ordenaDicas(d);
    }

    private void ordenaDicas(Dica d){
        Dica temp;
        int indice = dicas.size() -1;

        for (int i = 0 ; i < dicas.size() ; i++){
            if(dicas.get(i).equals(d)){
                indice = i;
                break;
            }
        }

        for (int i = indice -1 ; i >= 0 ; i--){
            if (dicas.get(i).getNumeroConcordancias() < d.getNumeroConcordancias()){
                temp = dicas.get(i);
                dicas.set(i, d);
                dicas.set(indice, temp);
                indice = i;
            }
        }

    }

    // addConcordancia esta aqui pra poder ordenar as dicas sempre q alguem concordar com alguma delas.
    // checar se existe outra forma.
    public void addConcordanciaDica (Dica d, Usuario u){
        d.addConcordancia(u);
        ordenaDicas(d);
    }

    public void addDiscordanciaDica (Dica d, Comentario c){
        d.addDiscordancia(c);
    }

    public void addAcusacao(Dica d, Usuario u){
        d.addAcusacao(u);
        if (d.getNumeroAcusacoes() == 3) dicas.remove(d);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}