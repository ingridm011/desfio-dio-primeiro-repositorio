package planodesaude.gabarito.plano;

public class BeneficiarioInexistenteException extends Exception{

    public BeneficiarioInexistenteException(int cpf){
        super("Beneficiario de CPF: "+cpf+" inexistente!");
    }
}
