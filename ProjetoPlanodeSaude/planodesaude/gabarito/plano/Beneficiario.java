package planodesaude.gabarito.plano;

import java.util.Date;


public class Beneficiario {

    protected int cpfBeneficiario;
    protected String nomeBeneficiario;
    protected Date ingressoNoPlanoB;
    protected Date nascBeneficiario;
    protected int generoBeneficiario;

    public Beneficiario(int cpf, String nome, Date ingressoNoPlano, Date nasc, int genero)  {
        cpfBeneficiario=cpf;
        nomeBeneficiario=nome;
        ingressoNoPlanoB=ingressoNoPlano;
        nascBeneficiario=nasc;
        generoBeneficiario=genero;
    }


    public String getNome() {
        return nomeBeneficiario;
    }

    public int getCpf() {
        return cpfBeneficiario;
    }

    public Date getIngressoNoPlano() {
        return ingressoNoPlanoB;
    }

    public Date getNasc() {
        return nascBeneficiario;
    }

    public int getGenero() {
        return generoBeneficiario;
    }
}
