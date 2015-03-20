package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Filipe on 19/03/2015.
 */

@Entity(name = "MetaDica")
public class Metadica extends Dica {

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="Dicas")
    private List<Dica> dicas;

    @Column
    private String comentario;

    public Metadica(){}

    public Metadica(Usuario user, String comentario, Dica ... dica){
        super(user);
        this.comentario = comentario;
        for(Dica d: dica) dicas.add(d);
    }

    public List<Dica> getDicas() {
        return dicas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {

        String result = "Comentario: " + comentario + "\n";
        for (Dica e: dicas) result += e.toString() + "\n";

        return result;
    }
}
