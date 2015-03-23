package models;

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

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn
    private Usuario usuario;

    @Column
    private Status status;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Concordancias")
    private List<Usuario> concordancias;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Discordancias")
    private List<Comentario> discordancias;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Acusacoes")
    private List<Usuario> acusacoes;

    @ManyToOne
    private Tema tema;

    @Transient
    private int LIMITE_DISCORDANCIA = 20;
    @Transient
    private int LIMITE_CONCORDANCIA = 20;

    public Dica(Usuario user){
        setUsuario(user);
        setStatus(Status.ABERTA);
        this.concordancias = new ArrayList<Usuario>();
        this.discordancias = new ArrayList<Comentario>();
        this.acusacoes =  new ArrayList<Usuario>();
    }

    public Dica(){
        this.concordancias = new ArrayList<Usuario>();
        this.discordancias = new ArrayList<Comentario>();
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
        for (Comentario c: discordancias){
            Usuario us =  c.getUsuario();
            if (u.equals(us)){
                return false;
            }
        }
        return true;
    }

    public void addDiscordancia(Comentario d){
        if(this.status == Status.ABERTA && checaConcordancia(d.getUsuario())){
            discordancias.add(d);
            if(discordancias.size() == LIMITE_DISCORDANCIA){
                this.status = Status.FECHADA;
            }
        }
    }

    public void addConcordancia(Usuario u) throws Exception{
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

    public List<Comentario> getDiscordancias() {
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

}