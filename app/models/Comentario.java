package models;

import java.lang.Exception;

public class Comentario{

    private Usuario usuario;
    private String texto;

    public Comentario(Usuario usuario, String texto) throws Exception{

        if(texto.length() > 100) throw new Exception("Máximo de 100 caracteres.");
        if(usuario == null) throw new Exception("Usuario inválido.");
        this.texto = texto;
        this.usuario = usuario;

    }

}