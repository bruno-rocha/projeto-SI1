package models;

/**
 * Created by Filipe on 13/03/2015.
 */
public enum Dificuldade {
    MUITO_DIFICIL(-2), DIFICIL(-1), NEUTRO(0), FACIL(1), MUITO_FACIL(2);

    private int valor;

    private Dificuldade(int valor) throws Exception{
        if(valor < -2 || valor > 2) throw new Exception("Dificuldade inválida.");
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }


}
