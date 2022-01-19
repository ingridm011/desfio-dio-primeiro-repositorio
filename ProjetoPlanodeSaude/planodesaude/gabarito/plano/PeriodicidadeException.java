package planodesaude.gabarito.plano;

public class PeriodicidadeException extends Exception{
    public PeriodicidadeException (){
        super("Tempo de periodicidade não cumprido");
    }
}
