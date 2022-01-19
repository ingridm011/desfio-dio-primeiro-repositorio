package planodesaude.gabarito.plano;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class PlanoDeSaude {

    public abstract void cadastrarBeneficiario(Beneficiario b) throws BeneficiarioInexistenteException, DadosInvalidosException, IdadeInvalidaException, BeneficiarioExistenteException;

    public abstract void salvarProcedimento(Procedimento p) throws DadosInvalidosException; //Salva ou altera. Verifica se tudo está preenchido

    public abstract void solicitar(int cpf, int codProcedimento, Date data) throws IdadeInvalidaException, GeneroInvalidoException,
            CarenciaException, PeriodicidadeException, UnicidadeException, BeneficiarioInexistenteException, ProcedimentoInexistenteException;

    public abstract double faturar(int mes, int ano);

    public abstract Procedimento pesquisarProcedimento(int cod) throws ProcedimentoInexistenteException;

    public abstract Beneficiario pesquisarBeneficiario(int cpf) throws BeneficiarioInexistenteException;

    protected int difDatas(Date dt1, Date dt2) {
        long tempo1 = dt1.getTime();
        long tempo2 = dt2.getTime();
        long difTempo = tempo1 - tempo2;
        return (int) ((difTempo + 60L * 60 * 1000) / (24L * 60 * 60 * 1000));
    }

    protected int calculaIdade(Date dataNasc) {
        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(dataNasc);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        dateOfBirth.add(Calendar.YEAR, age);
        if (today.before(dateOfBirth)) {
            age--;
        }
        return age;
    }
}

