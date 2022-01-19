package planodesaude.gabarito.plano;

import java.util.ArrayList;

public class ArrayListDeBeneficiarios {
    private ArrayList<Beneficiario> array = new ArrayList<>();

    public void cadastrarBeneficiario (Beneficiario b){
        array.add(b);
    }
    public Beneficiario pesquisar (int cpf) throws BeneficiarioInexistenteException {
        for (Beneficiario b : array) {
            if (b.getCpf()==cpf){
                return b;
            }
        }
        throw new BeneficiarioInexistenteException(cpf);
    }



}
