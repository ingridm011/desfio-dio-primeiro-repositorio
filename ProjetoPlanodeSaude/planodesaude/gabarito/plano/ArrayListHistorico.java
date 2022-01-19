package planodesaude.gabarito.plano;

import java.util.ArrayList;

public class ArrayListHistorico {

    private ArrayList<Solicitacao> array = new ArrayList<>();

    public void cadastrarSolicitacao (Solicitacao s){
        array.add(s);
    }

    public Solicitacao pesquisar (Beneficiario b) {
        for (Solicitacao s : array) {
            if ((s.getBene())==b){
                return s;
            }
        }
        return null;
    }

    public double valor (int mes, int ano){
        double valorExame=0;
        double valorCirurgia=0;
        for (Solicitacao s : array) {
            if(s.getData().getMonth()+1==mes && s.getData().getYear()+1900==ano){
                if(s.getProced() instanceof Exame){
                    valorExame += (((Exame) s.getProced()).getPorte()*100+((Exame) s.getProced()).getCustoOperacional());
                } else if (s.getProced() instanceof Cirurgia){
                    valorCirurgia += (((Cirurgia)s.getProced()).getHonorario()+((Cirurgia) s.getProced()).getMaterial());
                }
            }
        }
        return valorExame+valorCirurgia;
    }

    public int Quant (Procedimento p, Beneficiario b){
        int cont=0;
        for (Solicitacao s : array){
            if (s.getProced()==p && s.getBene()==b){
                cont++;
            }
        }
        return cont;
    }

    public Solicitacao data (Procedimento p, Beneficiario b){
        int aux = 0;
        for (Solicitacao s : array){
            if (s.getProced()==p && s.getBene()==b){
                aux = array.lastIndexOf(s);
                return array.get(aux);
            }
        }
        return null;
    }

}
