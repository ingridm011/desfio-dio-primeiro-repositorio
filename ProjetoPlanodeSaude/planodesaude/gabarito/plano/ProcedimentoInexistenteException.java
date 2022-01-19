package planodesaude.gabarito.plano;

public class ProcedimentoInexistenteException extends Exception{
    public ProcedimentoInexistenteException (){
        super("Procedimento inexistente!");
    }
}
