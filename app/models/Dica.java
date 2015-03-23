package models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Dica")
public abstract class Dica{
    @Id
    @GeneratedValue
    private long id;

    public enum Status {
        FECHADA, ABERTA;
    }

    @OneToOne
    @JoinColumn
    private Usuario usuario;

    @Column
    private Status status;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Concordancias")
    private List<Usuario> concordancias;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Discordancias")
    private List<Usuario> discordancias;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Acusacoes")
    private List<Usuario> acusacoes;

    @Transient
    private int LIMITE_DISCORDANCIA = 20;
    @Transient
    private int LIMITE_CONCORDANCIA = 20;

    public Dica(Usuario user){
        setUsuario(user);
        setStatus(Status.ABERTA);
        this.concordancias = new ArrayList<Usuario>();
        this.discordancias = new ArrayList<Usuario>();
        this.acusacoes =  new ArrayList<Usuario>();
    }

    public Dica(){
        this.concordancias = new ArrayList<Usuario>();
        this.discordancias = new ArrayList<Usuario>();
        this.acusacoes =  new ArrayList<Usuario>();
        setStatus(Status.ABERTA);
    }

    public abstract String getTexto ();

    public int getNumeroConcordancias(){
        return concordancias.size();
    }

    public int getNumeroAcusacoes() {
        return acusacoes.size();
    }

    private boolean checaConcordancia(Usuario u){
        for (Usuario us: concordancias){
            if (u.equals(us)){
                return false;
            }
        }
        for (Usuario us: discordancias){
            if (u.equals(us)){
                return false;
            }
        }
        return true;
    }

    public void addDiscordancia(Usuario u){
        if(this.status == Status.ABERTA && checaConcordancia(u)){
            discordancias.add(u);
            if(discordancias.size() == LIMITE_DISCORDANCIA){
                this.status = Status.FECHADA;
            }
        }
    }

    public void addConcordancia(Usuario u){
        if(this.status == Status.ABERTA && checaConcordancia(u)) {
            concordancias.add(u);
            if(concordancias.size() == LIMITE_CONCORDANCIA){
                this.status = Status.FECHADA;
            }
        }
    }

    public void addAcusacao(Usuario u){
        if (!acusacoes.contains(u)){
            acusacoes.add(u);
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Usuario> getDiscordancias() {
        return discordancias;
    }

    public List<Usuario> getConcordancias() {
        return concordancias;
    }

    public List<Usuario> getAcusacoes() {
        return acusacoes;
    }

    public String getTipo(){
        return "Dica";
    }

    public String getIndiceConcordancias(){
        return String.format("%.2f", (concordancias.size()+0.0)/(concordancias.size()+discordancias.size()));
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getTipo() + ": " + getTexto() + "     | " + "Feita por " + getUsuario().getNomeCompleto();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dica)) return false;

        Dica dica = (Dica) o;

        if (status != dica.status) return false;
        if (!usuario.equals(dica.usuario)) return false;
        if(!getTexto().equals(dica.getTexto())) return false;

        return true;
    }

}