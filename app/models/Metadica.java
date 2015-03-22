package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Metadica extends Dica {
    @ElementCollection
    private List<Dica> dicas;

    @Column
    private String comentario;

    public Metadica(){}

    public Metadica(Usuario user, String comentario, Dica ... dica){
        super(user);
        setComentario(comentario);
        this.dicas =  new ArrayList<Dica>();
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
