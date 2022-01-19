package planodesaude.gabarito.plano;

public class BeneficiarioExistenteException extends Exception{
    public BeneficiarioExistenteException(int cpf){
        super("Beneficiario de CPF: "+ cpf+ " já existente");
    }
}
