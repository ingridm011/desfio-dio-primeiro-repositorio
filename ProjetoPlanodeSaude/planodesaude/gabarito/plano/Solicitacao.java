package planodesaude.gabarito.plano;

import java.util.Date;

public class Solicitacao {

    private Beneficiario bene;
    private Procedimento proced;
    private Date data;

    public Solicitacao(Beneficiario b, Procedimento p, Date d) {
        bene = b;
        proced = p;
        data = d;
    }

    public Procedimento getProced() {
        return proced;
    }

    public Beneficiario getBene() {
        return bene;
    }


    public Date getData() {
        return data;
    }
}
