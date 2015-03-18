package models;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Dica")
public class Dica{

    public enum Status {
        FECHADA, ABERTA;
    }

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="usuario")
    private Usuario usuario;

    private Status status;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Concordancias")
    private List<Usuario> concordancias;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Discordancias")
    private List<Comentario> discordancias;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Acusaocoes")
    private List<Usuario> acusacoes;


    public Dica(Usuario user){
        this.usuario = user;
        this.status = Status.ABERTA;
        concordancias = new ArrayList<Usuario>();
        discordancias = new ArrayList<Comentario>();
        acusacoes =  new ArrayList<Usuario>();
    }

    public Dica(){

    }

    public int getNumeroConcordancias(){
        return concordancias.size();
    }

    public int getNumeroAcusacoes() {
        return acusacoes.size();
    }

    public void addDiscordancia(Comentario d){
        if(this.status == Status.ABERTA) discordancias.add(d);
        if(discordancias.size() == 20) this.status = Status.FECHADA;
    }

    public void addConcordancia(Usuario u){
        if(this.status == Status.ABERTA) concordancias.add(u);
        if(concordancias.size() == 20) this.status = Status.FECHADA;
    }

    public void addAcusacao(Usuario u){
        acusacoes.add(u);
    }

    public Usuario getUsuario() {
        return usuario;
    }
/*
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
*/
    public List<Comentario> getDiscordancias() {
        return discordancias;
    }

    public List<Usuario> getConcordancias() {
        return concordancias;
    }

    public List<Usuario> getAcusacoes() {
        return acusacoes;
    }

}