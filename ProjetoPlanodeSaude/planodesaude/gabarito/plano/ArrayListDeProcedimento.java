package planodesaude.gabarito.plano;

import java.util.ArrayList;

public class ArrayListDeProcedimento {
    private ArrayList<Procedimento> array = new ArrayList<>();

    public void cadastrarProcedimento (Procedimento p){
        try{
            pesquisar(p.getCodigo());
            array.set(array.indexOf(pesquisar(p.getCodigo())),p);
        } catch (ProcedimentoInexistenteException e) {
            array.add(p);
        }

    }
    public Procedimento pesquisar (int cod) throws ProcedimentoInexistenteException {
        for (Procedimento p : array) {
            if (p.getCodigo()==cod){
                return p;
            }
        }
        throw new ProcedimentoInexistenteException();
    }
}
