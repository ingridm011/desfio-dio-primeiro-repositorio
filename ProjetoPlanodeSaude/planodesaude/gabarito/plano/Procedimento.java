package planodesaude.gabarito.plano;

public class Procedimento {


    protected int idadeMax;
    protected int idadeMin;
    protected int codigo;
    protected String nomeB;
    protected int genero;

    public Procedimento(int cod,String nome, int iMin, int iMax, int gen){
        codigo = cod;
        nomeB = nome;
        idadeMin = iMin;
        idadeMax = iMax;
        genero = gen;

    }

    public int getIdadeMax() {
        return idadeMax;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getIdadeMin() {
        return idadeMin;
    }

    public String getNome() {
        return nomeB;
    }

    public int getGenero() {
        return genero;
    }
}
