package models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Dica")
public class Dica{

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="usuario")
    private Usuario usuario;

    private String texto;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Concordancias")
    private List<Usuario> concordancias;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Discordancias")
    private List<Comentario> discordancias;



    public Dica(){

    }

}