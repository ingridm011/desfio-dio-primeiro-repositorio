package planodesaude.gabarito.plano;

public class Cirurgia extends Procedimento {

    protected int unicidadeCirurgia;
    protected double honorarioCirurgia;
    protected double materialCirurgia;

    public Cirurgia(int cod, String nome, int iMin, int iMax, int gen, int unicidade, double honorario, double material){
        super(cod, nome, iMin, iMax, gen);
        unicidadeCirurgia = unicidade;
        honorarioCirurgia = honorario;
        materialCirurgia = material;
    }


    public int getUnicidade() {
        return unicidadeCirurgia;
    }

    public double getHonorario() {
        return honorarioCirurgia;
    }

    public double getMaterial() {
        return materialCirurgia;
    }
}
