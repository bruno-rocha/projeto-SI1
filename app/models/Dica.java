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
    @JoinColumn(name="Usuario")
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
    @JoinColumn(name="Acusaocoes")
    private List<Usuario> acusacoes;

    @ManyToOne(cascade=CascadeType.ALL)
    private Tema tema;


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

    public int getNumeroConcordancias(){
        return concordancias.size();
    }

    public int getNumeroAcusacoes() {
        return acusacoes.size();
    }


    private void checaConcordancia(Usuario u) throws Exception{
        for (Usuario us: concordancias){
            if (u.equals(us)) throw new Exception("Você já concordou com esta dica");
        }

        for (Comentario c: discordancias){
            Usuario us =  c.getUsuario();
            if (u.equals(us)) throw new Exception("Você já discordou desta dica");
        }

    }

    public void addDiscordancia(Comentario d)throws Exception{
        checaConcordancia(d.getUsuario());
        if(this.status == Status.ABERTA){
            discordancias.add(d);
            if(discordancias.size() == 20) this.status = Status.FECHADA;
        }
    }

    public void addConcordancia(Usuario u) throws Exception{
        checaConcordancia(u);
        if(this.status == Status.ABERTA) {
            concordancias.add(u);
            if(concordancias.size() == 20) this.status = Status.FECHADA;
        }

    }

    public void addAcusacao(Usuario u) throws Exception{

        if (acusacoes.contains(u)) throw new Exception("Você já acusou esta dica.");
        acusacoes.add(u);
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

    public String getIndiceDiscordancias(){
        return String.format("%.2f", (concordancias.size()+0.0)/(concordancias.size()+discordancias.size()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}