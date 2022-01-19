package planodesaude.gabarito.plano;

import java.util.Date;
import java.util.Objects;

public class ControlePlanoDeSaude extends PlanoDeSaude {

    private ArrayListDeBeneficiarios array = new ArrayListDeBeneficiarios();
    private ArrayListDeProcedimento arrayP = new ArrayListDeProcedimento();
    private ArrayListHistorico arrayH = new ArrayListHistorico();

    public ControlePlanoDeSaude(){

    }

    @Override
    public void cadastrarBeneficiario(Beneficiario b) throws BeneficiarioExistenteException, DadosInvalidosException, IdadeInvalidaException {
        try {
            array.pesquisar(b.getCpf());
            throw new BeneficiarioExistenteException(b.getCpf());
        } catch (BeneficiarioInexistenteException e){
            System.out.println(e.getMessage());
        }
        if (calculaIdade(b.getNasc())<18 || calculaIdade(b.getNasc())>65|| b.getNasc()==null){
            throw new IdadeInvalidaException();
        } else if (b.getGenero()<=0 || b.getGenero()>=3){
            throw new DadosInvalidosException();
        }else if (Objects.equals(b.getNome(), "")||b.getNome()==null){
            throw new DadosInvalidosException();
        } else if (b.getCpf()<=0){
            throw new DadosInvalidosException();
        } else if (b.getIngressoNoPlano()==null){
            throw new DadosInvalidosException();
        }
        array.cadastrarBeneficiario(b);
    }

    @Override
    public void salvarProcedimento(Procedimento p) throws DadosInvalidosException {
        if (p instanceof Exame){
            if (((Exame) p).getCustoOperacional()<=0){
                throw new DadosInvalidosException();
            } else if (((Exame) p).getPorte()<=0){
                throw new DadosInvalidosException();
            } else if (((Exame) p).getCarencia()<0){
                throw new DadosInvalidosException();
            } else if (((Exame) p).getPeriodicidade()<0){
                throw new DadosInvalidosException();
            } else if(Objects.equals(((Exame) p).getNome(), "")||(((Exame)p).getNome()==null)){
                throw new DadosInvalidosException();
            }
        } else if (p instanceof Cirurgia){
            if (((Cirurgia) p).getMaterial()<=0){
                throw new DadosInvalidosException();
            } else if (((Cirurgia) p).getUnicidade()<=0){
                throw new DadosInvalidosException();
            } else if (((Cirurgia) p).getHonorario()<=0){
                throw new DadosInvalidosException();
            } else if(Objects.equals(((Cirurgia) p).getNome(), "") || (((Cirurgia)p).getNome()==null)){
                throw new DadosInvalidosException();
            }
        }
        if (p.getGenero()<=0 || p.getGenero()>3){
            throw new DadosInvalidosException();
        } else if (p.getIdadeMin()<0 || p.getIdadeMax()<0 || p.getIdadeMax()<p.getIdadeMin()){
            throw new DadosInvalidosException();
        } else if (p.getCodigo()<0){
            throw new DadosInvalidosException();
        }
        arrayP.cadastrarProcedimento(p);
    }

    @Override
    public void solicitar(int cpf, int codProcedimento, Date data) throws IdadeInvalidaException, GeneroInvalidoException, CarenciaException, PeriodicidadeException, UnicidadeException, BeneficiarioInexistenteException, ProcedimentoInexistenteException {
        Beneficiario b = array.pesquisar(cpf);
        Procedimento p = arrayP.pesquisar(codProcedimento);
        Solicitacao s = new Solicitacao(b,p,data);

        if (calculaIdade(b.getNasc())<p.getIdadeMin()|| calculaIdade(b.getNasc())>p.getIdadeMax()|| b.getNasc()==null){
            throw new IdadeInvalidaException();
        } else if (p.getGenero()!= 3 && b.getGenero()<p.getGenero()||b.getGenero()>p.getGenero()){
            throw new GeneroInvalidoException();
        }  else if (p instanceof Exame){
        Solicitacao teste = arrayH.data(p, b);
        if (((Exame) p).getCarencia()> difDatas(data,b.getIngressoNoPlano())){
            throw new CarenciaException();
        } else if (teste != null && ((Exame) p).getPeriodicidade()>difDatas(data,arrayH.data(p,b).getData())){
            throw new PeriodicidadeException();
        }
    } else if (p instanceof Cirurgia){
            if (((Cirurgia) p).getUnicidade()<= arrayH.Quant(p,b)){
                throw new UnicidadeException();
            }
        }
        arrayH.cadastrarSolicitacao(s);
    }

    @Override
    public double faturar(int mes, int ano) {
        return arrayH.valor(mes, ano);
    }

    @Override
    public Procedimento pesquisarProcedimento(int cod) throws ProcedimentoInexistenteException {
        return arrayP.pesquisar(cod);
    }

    @Override
    public Beneficiario pesquisarBeneficiario(int cpf) throws BeneficiarioInexistenteException {
        return array.pesquisar(cpf);
    }
}
