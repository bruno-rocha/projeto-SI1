package models;

import java.lang.Exception;

public class Voto{

    private int dificuldade;
    private Usuario usuario;

    public Voto(Usuario usuario, int dificuldade) throws Exception{
        if(usuario == null) throw new Exception("Usuario inválido.");
        if(dificuldade < -2 || dificuldade > 2) throw new Exception("Dificuldade inválida.");
        this.dificuldade = dificuldade;
        this.usuario = usuario;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}