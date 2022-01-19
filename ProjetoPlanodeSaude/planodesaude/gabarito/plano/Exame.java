package planodesaude.gabarito.plano;

public class Exame extends Procedimento {

    protected int carencia;
    protected int periodicidade;
    protected double porte;
    protected double custoOperacional;

    public Exame(int cod, String nome, int iMin, int iMax, int gen, int caren, int period, double port, double custoOp ) {
        super(cod, nome, iMin, iMax, gen);
        carencia = caren;
        periodicidade = period;
        porte = port;
        custoOperacional = custoOp;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getCarencia() {
        return carencia;
    }

    public int getPeriodicidade() {
        return periodicidade;
    }

    public double getPorte() {
        return porte;
    }

    public double getCustoOperacional() {
        return custoOperacional;
    }
}
