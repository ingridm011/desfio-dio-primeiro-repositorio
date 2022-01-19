package planodesaude.gabarito.plano;

public class CarenciaException extends Exception {
    public CarenciaException (){
        super("Tempo de carencia não foi cumprida!");
    }
}
