package models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Tema")
public class Tema{
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String nome;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn
    private List<Voto> votos;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn
    private List<Dica> dicas;

    @Transient
    private int LIMITE_ACUSACOES = 3;

    public Tema(String nome){
        setNome(nome);
        this.dicas = new ArrayList<Dica>();
        this.votos = new ArrayList<Voto>();

    }

    public Tema() {
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
        //checa se o Usuário já votou.
        boolean flag = true;
        for (Voto v: getVotos()){
            if(v.getUsuario().equals(vt.getUsuario())){
                try {
                    v.setDificuldade(vt.getDificuldade());
                }catch(Exception e){
                }
                flag = false;
                break;
            }
        }
        if(flag){
            votos.add(vt);
        }
        ordenaVotos(vt);
    }

    public String getMediaDificuldade(){
        if(votos.size() == 0){
            return "0.00";
        }
        double total = 0d;
        for (Voto e: votos){
            total += e.getDificuldade();
        }
        return String.format("%.2f", total/votos.size());

    }

    public Dica getDica(String id){
        for (Dica d: dicas){
            if (d.toString().equals(id)) return d;
        }
        return null;
    }

    public String getMedianaDificuldade(){
        if(votos.size() == 0){
            return "0.00";
        }
        if (votos.size()%2 != 0){
            return String.format("%d", votos.get(votos.size() / 2).getDificuldade());
        }
        else{
            return String.format("%.2f", (votos.get(votos.size()/2).getDificuldade() +
                    votos.get(votos.size()/2 -1).getDificuldade())/2f);
        }
    }

    private void ordenaVotos(Voto v){
        Voto temp;
        int indice = votos.size() -1;

        for (int i = 0 ; i < votos.size() ; i++){
            if(votos.get(i).equals(v)){
                indice = i;
                break;
            }
        }
        for (int i = indice -1 ; i >= 0 ; i--){
            if (votos.get(i).getDificuldade() < v.getDificuldade()){
                temp = votos.get(i);
                votos.set(i, v);
                votos.set(indice, temp);
                indice = i;
            }
        }
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

    public void addDiscordanciaDica (Dica d, Usuario u){
        d.addDiscordancia(u);
    }

    public void addAcusacao(Dica d, Usuario u){
        d.addAcusacao(u);
        if (d.getNumeroAcusacoes() == LIMITE_ACUSACOES){
            dicas.remove(d);
        }
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Tema tema = (Tema) o;
        if (!nome.equals(tema.nome)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    public boolean checaVotou(Usuario u){
        for(Voto v: getVotos()){
            if(v.getUsuario().equals(u)){
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return nome;
    }
}