package planodesaude.gabarito.plano;

public class IdadeInvalidaException extends Exception{

    public IdadeInvalidaException(){
        super("Data de nascimento invalida!");
    }
}
