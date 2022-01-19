package planodesaude.gabarito.plano;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;

public class TestePlanoDeSaudeCorrecao {

    @Test
    public void testarCadastroDeBeneficiarioCorreto() throws BeneficiarioExistenteException, IdadeInvalidaException, DadosInvalidosException, BeneficiarioInexistenteException {
        ControlePlanoDeSaude plano = new ControlePlanoDeSaude();
        GregorianCalendar gc = new GregorianCalendar();
        // 14 de novembro de 1974
        gc.set(1974, 10, 14);
        Date nasc = gc.getTime();
        // 31 de outubro de 2016
        gc.set(2016, 9, 31);
        Date ingressoNoPlano = gc.getTime();
        Beneficiario b = new Beneficiario(111, "Pedro",  ingressoNoPlano, nasc, 1);
        plano.cadastrarBeneficiario(b);
        Beneficiario outro = plano.pesquisarBeneficiario(111);
        assertEquals("Pedro", outro.getNome());
        assertEquals(ingressoNoPlano, outro.getIngressoNoPlano());
        assertEquals(nasc, outro.getNasc());
        assertEquals(1, outro.getGenero());
    }

    @Test
    public void testarCadastroDeBeneficiarioJaCadastrado() throws BeneficiarioExistenteException, IdadeInvalidaException, DadosInvalidosException {
        ControlePlanoDeSaude plano = new ControlePlanoDeSaude();
        GregorianCalendar gc = new GregorianCalendar();
        // 14 de novembro de 1974
        gc.set(1974, 10, 14);
        Date nasc = gc.getTime();
        // 31 de outubro de 2016
        gc.set(2016, 9, 31);
        Date ingressoNoPlano = gc.getTime();
        Beneficiario b = new Beneficiario(111, "Pedro",  ingressoNoPlano, nasc, 1);
        plano.cadastrarBeneficiario(b);
        try {
            plano.cadastrarBeneficiario(b);
            fail("Deveria ter dado exceção de beneficiário já cadastrado.");
        } catch (BeneficiarioExistenteException e) {
            // Tudo certo!
        }
    }

    @Test
    public void testarCadastroDeBeneficiarioComIdadeInvalida() throws BeneficiarioExistenteException, IdadeInvalidaException {
        ControlePlanoDeSaude plano = new ControlePlanoDeSaude();
        GregorianCalendar gc = new GregorianCalendar();
        // 14 de novembro de 1950
        gc.set(1950, 10, 14);
        Date nasc = gc.getTime();
        // 31 de outubro de 2016
        gc.set(2016, 9, 31);
        Date ingressoNoPlano = gc.getTime();
        Beneficiario b = new Beneficiario(111, "Pedro",  ingressoNoPlano, nasc, 1);
        try {
            plano.cadastrarBeneficiario(b);
            fail("Deveria ter dado exceção de idade inválida.");
        } catch (Exception e) {
            assertTrue(e instanceof IdadeInvalidaException);
        }
        // 14 de novembro de 2006
        gc.set(2006, 10, 14);
        nasc = gc.getTime();
        Beneficiario b2 = new Beneficiario(111, "Pedro",  ingressoNoPlano, nasc, 1);
        try {
            plano.cadastrarBeneficiario(b2);
            fail("Deveria ter dado exceção de idade inválida.");
        } catch (Exception e) {
            assertTrue(e instanceof IdadeInvalidaException);
        }
    }

    private void assertTrue(boolean b) {
    }

    @Test
    public void testarCadastroDeBeneficiarioComDadosInvalidos() throws BeneficiarioExistenteException, IdadeInvalidaException {
        ControlePlanoDeSaude plano = new ControlePlanoDeSaude();
        GregorianCalendar gc = new GregorianCalendar();
        // 14 de novembro de 1974
        gc.set(1974, 10, 14);
        Date nasc = gc.getTime();
        // 31 de outubro de 2016
        gc.set(2016, 9, 31);
        Date ingressoNoPlano = gc.getTime();
        Beneficiario b = new Beneficiario(0, "Pedro",  ingressoNoPlano, nasc, 1);
        try {
            plano.cadastrarBeneficiario(b);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e) {
            assertTrue(e instanceof DadosInvalidosException);
        }

        b = new Beneficiario(111, "",  ingressoNoPlano, nasc, 1);
        try {
            plano.cadastrarBeneficiario(b);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e) {
            assertTrue(e instanceof DadosInvalidosException);
        }

        b = new Beneficiario(111, null,  ingressoNoPlano, nasc, 1);
        try {
            plano.cadastrarBeneficiario(b);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e) {
            assertTrue(e instanceof DadosInvalidosException);
        }

        b = new Beneficiario(111, "Pedro",  null, nasc, 1);
        try {
            plano.cadastrarBeneficiario(b);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e) {
            assertTrue(e instanceof DadosInvalidosException);
        }

        b = new Beneficiario(111, "Pedro",  ingressoNoPlano, null, 1);
        try {
            plano.cadastrarBeneficiario(b);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e) {
            assertTrue(e instanceof DadosInvalidosException);
        }

        b = new Beneficiario(111, "Pedro",  ingressoNoPlano, nasc, 0);
        try {
            plano.cadastrarBeneficiario(b);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e) {
            assertTrue(e instanceof DadosInvalidosException);
        }

        b = new Beneficiario(111, "Pedro",  ingressoNoPlano, nasc, 3);
        try {
            plano.cadastrarBeneficiario(b);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e) {
            assertTrue(e instanceof DadosInvalidosException);
        }
    }

    @Test
    public void testarCadastroDeExameOk() throws DadosInvalidosException, ProcedimentoInexistenteException {
        ControlePlanoDeSaude plano = new ControlePlanoDeSaude();
        Exame e = new Exame(1, "Hemograma", 18, 64, 3, 30, 60, 1, 120);
        plano.salvarProcedimento(e);
        Exame e1 = (Exame) plano.pesquisarProcedimento(1);
        assertEquals(18, e1.getIdadeMin());
        assertEquals(64, e1.getIdadeMax());
        assertEquals(3, e1.getGenero());
        assertEquals(30, e1.getCarencia());
        assertEquals(60, e1.getPeriodicidade());
        assertEquals(1, e1.getPorte(), 0.0001);
        assertEquals(120, e1.getCustoOperacional(), 0.0001);
    }

    @Test
    public void testarCadastroDeExameComDadosInvalidos() throws DadosInvalidosException {
        ControlePlanoDeSaude plano = new ControlePlanoDeSaude();

        // Codigo invalido
        Exame e1 = new Exame(-1, "Hemograma", 0, 150, 3, 30, 60, 1, 120);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Nome invalido
        e1 = new Exame(1, null, 0, 150, 3, 30, 60, 1, 120);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Nome invalido
        e1 = new Exame(1, "", 0, 150, 3, 30, 60, 1, 120);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Idade minima invalido
        e1 = new Exame(1, "Hemograma", -1, 150, 3, 30, 60, 1, 120);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Idade maxima invalido
        e1 = new Exame(1, "Hemograma", 0, -1, 3, 30, 60, 1, 120);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        //Idade maxima menor que a mínima
        e1 = new Exame(1, "Hemograma", 10, 9, 3, 30, 60, 1, 120);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Genero invalido
        e1 = new Exame(1, "Hemograma", 0, 150, 0, 30, 60, 1, 120);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Genero invalido
        e1 = new Exame(1, "Hemograma", 0, 150, 4, 30, 60, 1, 120);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Carencia invalido
        e1 = new Exame(1, "Hemograma", 0, 150, 3, -1, 60, 1, 120);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Periodicidade invalido
        e1 = new Exame(1, "Hemograma", 0, 150, 3, 30, -1, 1, 120);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Porte invalido
        e1 = new Exame(1, "Hemograma", 0, 150, 3, 30, 60, -1, 120);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Custo operacional invalido
        e1 = new Exame(1, "Hemograma", 0, 150, 3, 30, 60, 1, -1);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }
    }

    @Test
    public void testarCadastroDeCirurgiaOk() throws DadosInvalidosException, ProcedimentoInexistenteException {
        ControlePlanoDeSaude plano = new ControlePlanoDeSaude();
        Cirurgia e = new Cirurgia(1, "Cesarea", 10, 150, 2, 10, 600.0, 300.0);
        plano.salvarProcedimento(e);
        Cirurgia e1 = (Cirurgia) plano.pesquisarProcedimento(1);
        assertEquals("Cesarea", e1.getNome());
        assertEquals(10, e1.getIdadeMin());
        assertEquals(150, e1.getIdadeMax());
        assertEquals(2, e1.getGenero());
        assertEquals(10, e1.getUnicidade());
        assertEquals(600, e1.getHonorario(), 0.0001);
        assertEquals(300, e1.getMaterial(), 0.0001);
        e = new Cirurgia(1, "Cesarea2", 11, 151, 3, 11, 601.0, 301.0);
        plano.salvarProcedimento(e);
        e1 = (Cirurgia) plano.pesquisarProcedimento(1);
        assertEquals("Cesarea2", e1.getNome());
        assertEquals(11, e1.getIdadeMin());
        assertEquals(151, e1.getIdadeMax());
        assertEquals(3, e1.getGenero());
        assertEquals(11, e1.getUnicidade());
        assertEquals(601, e1.getHonorario(), 0.0001);
        assertEquals(301, e1.getMaterial(), 0.0001);
    }

    @Test
    public void testarCadastroDeCirurgiaComDadosInvalidos() throws DadosInvalidosException {
        ControlePlanoDeSaude plano = new ControlePlanoDeSaude();

        // Codigo invalido
        Cirurgia e1 = new Cirurgia(-1, "Cesarea", 10, 150, 2, 10, 600.0, 300.0);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Nome invalido
        e1 = new Cirurgia(1, "", 10, 150, 2, 10, 600.0, 300.0);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Nome invalido
        e1 = new Cirurgia(1, null, 10, 150, 2, 10, 600.0, 300.0);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Idade minima invalido
        e1 = new Cirurgia(1, "Cesarea", -1, 150, 2, 10, 600.0, 300.0);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Idade maxima invalido
        e1 = new Cirurgia(1, "Cesarea", 10, -1, 2, 10, 600.0, 300.0);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        //Idade maxima menor que a mínima
        e1 = new Cirurgia(1, "Cesarea", 10, 9, 2, 10, 600.0, 300.0);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Genero invalido
        e1 = new Cirurgia(1, "Cesarea", 10, 150, 0, 10, 600.0, 300.0);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Genero invalido
        e1 = new Cirurgia(1, "Cesarea", 10, 150, 4, 10, 600.0, 300.0);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Unicidade invalido
        e1 = new Cirurgia(1, "Cesarea", 10, 150, 2, -1, 600.0, 300.0);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Honorario invalido
        e1 = new Cirurgia(1, "Cesarea", 10, 150, 2, 10, -1, 300.0);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }

        // Material invalido
        e1 = new Cirurgia(1, "Cesarea", 10, 150, 2, 10, 600.0, -1);
        try {
            plano.salvarProcedimento(e1);
            fail("Deveria ter dado exceção de dados inválidos.");
        } catch (Exception e){
            assertTrue(e instanceof DadosInvalidosException);
        }
    }

    @Test
    public void testarSolicitarCirurgiaComErro() throws DadosInvalidosException, BeneficiarioExistenteException, IdadeInvalidaException, GeneroInvalidoException, CarenciaException, PeriodicidadeException, UnicidadeException, BeneficiarioInexistenteException, ProcedimentoInexistenteException {
        ControlePlanoDeSaude plano = new ControlePlanoDeSaude();
        Cirurgia e1 = new Cirurgia(2, "�rnia", 43, 150, 1, 1, 600.0, 300.0);
        plano.salvarProcedimento(e1);

        GregorianCalendar gc = new GregorianCalendar();
        // 14 de novembro de 1979
        gc.set(1979, 11, 14);
        Date nasc = gc.getTime();
        // 31 de outubro de 2016
        gc.set(2016, 10, 31);
        Date ingressoNoPlano = gc.getTime();
        Date hoje = new Date();

        Beneficiario b = new Beneficiario(111, "Pedro",  ingressoNoPlano, nasc, 1);
        plano.cadastrarBeneficiario(b);

        // Idade do beneficiário menor que a mínima exigida para o procedimento
        try {
            plano.solicitar(111, 2, hoje);
            fail("Deveria ter dado erro de idade invalida.");
        } catch (Exception e){
            assertTrue(e instanceof IdadeInvalidaException);
        }

        // Idade do beneficiário maior que a máxima exigida para o procedimento
        e1 = new Cirurgia(1, "�rnia", 10, 40, 1, 1, 600.0, 300.0);
        plano.salvarProcedimento(e1);
        try {
            plano.solicitar(111, 1, hoje);
            fail("Deveria ter dado erro de idade invalida.");
        } catch (Exception e){
            assertTrue(e instanceof IdadeInvalidaException);
        }

        // Genero do procedimento diferente do beneficiario
        e1 = new Cirurgia(1, "Cesarea", 10, 150, 2, 1, 600.0, 300.0);
        plano.salvarProcedimento(e1);
        try {
            plano.solicitar(111, 1, hoje);
            fail("Deveria ter dado erro de genero invalido.");
        } catch (Exception e){
            assertTrue(e instanceof GeneroInvalidoException);
        }

        // Unicidade do procedimento maior
        e1 = new Cirurgia(1, "Cesarea", 10, 150, 3, 1, 600.0, 300.0);
        plano.salvarProcedimento(e1);
        try {
            plano.solicitar(111, 1, hoje);
            plano.solicitar(111, 1, hoje);
            fail("Não deveria solicitar novamente!");
        } catch (Exception e){
            assertTrue(e instanceof UnicidadeException);
        }
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testarSolicitarExameComErro() throws DadosInvalidosException, BeneficiarioExistenteException, IdadeInvalidaException, GeneroInvalidoException, CarenciaException, PeriodicidadeException, UnicidadeException, BeneficiarioInexistenteException, ProcedimentoInexistenteException {
        ControlePlanoDeSaude plano = new ControlePlanoDeSaude();
        Exame e1 = new Exame(1, "Hemograma", 0, 150, 3, 30, 60, 1, 120);
        plano.salvarProcedimento(e1);

        GregorianCalendar gc = new GregorianCalendar();
        // 14 de novembro de 1974
        gc.set(1979, 10, 14);
        Date nasc = gc.getTime();
        // 31 de outubro de 2016
        gc.set(2020, 9, 31);
        Date ingressoNoPlano = gc.getTime();
        Date hoje = new Date();

        Beneficiario b = new Beneficiario(111, "Pedro",  ingressoNoPlano, nasc, 1);
        plano.cadastrarBeneficiario(b);

        // Exame inexistente
        e1 = new Exame(1, "Hemograma", 0, 150, 3, 30, 60, 1, 120);
        plano.salvarProcedimento(e1);
        try {
            plano.solicitar(111, 9, hoje);
            fail("Deveria ter dado erro de exame inexistente.");
        } catch (Exception e){
            assertTrue(e instanceof ProcedimentoInexistenteException);
        }

        // Paciente inexistente
        try {
            plano.solicitar(999, 1, hoje);
            fail("Deveria ter dado erro de paciente inexistente.");
        } catch (Exception e){
            assertTrue(e instanceof BeneficiarioInexistenteException);
        }

        // Idade do beneficiário menor que a mínima exigida para o procedimento
        e1 = new Exame(1, "Hemograma", 43, 150, 3, 30, 60, 1, 120);
        plano.salvarProcedimento(e1);
        try {
            plano.solicitar(111, 1, hoje);
            fail("Deveria ter dado erro de idade invalida.");
        } catch (Exception e){
            assertTrue(e instanceof IdadeInvalidaException);
        }

        // Idade do beneficiário maior que a máxima exigida para o procedimento
        e1 = new Exame(1, "Hemograma", 0, 40, 3, 30, 60, 1, 120);
        plano.salvarProcedimento(e1);
        try {
            plano.solicitar(111, 1, hoje);
            fail("Deveria ter dado erro de idade invalida.");
        } catch (Exception e){
            assertTrue(e instanceof IdadeInvalidaException);
        }

        // Genero do procedimento diferente do beneficiario
        e1 = new Exame(1, "Hemograma", 0, 150, 2, 30, 60, 1, 120);
        plano.salvarProcedimento(e1);
        try {
            plano.solicitar(111, 1, hoje);
            fail("Deveria ter dado erro de genero invalido.");
        } catch (Exception e){
            assertTrue(e instanceof GeneroInvalidoException);
        }

        // Carencia do procedimento maior
        e1 = new Exame(1, "Hemograma", 0, 150, 3, 500, 60, 1, 120);
        plano.salvarProcedimento(e1);
        try {
            plano.solicitar(111, 1, hoje);
            fail("Deveria ter dado erro de carencia invalida.");
        } catch (Exception e){
            assertTrue(e instanceof CarenciaException);
        }

        // Periodicidade do procedimento maior
        e1 = new Exame(1, "Hemograma", 0, 150, 3, 35, 35, 1, 120);
        plano.salvarProcedimento(e1);
        hoje = new Date();
        Beneficiario b1 = new Beneficiario(222, "Pedro",  ingressoNoPlano, nasc, 1);
        plano.cadastrarBeneficiario(b1);
        plano.solicitar(222, 1, hoje);
        hoje.setMonth(hoje.getMonth()-1);
        try {
            plano.solicitar(222, 1, hoje);
            fail("Deveria ter dado erro de periodicidade invalida.");
        } catch (Exception e){
            assertTrue(e instanceof PeriodicidadeException);
        }
    }

    @Test
    public void testarFaturamento() throws DadosInvalidosException, BeneficiarioExistenteException, IdadeInvalidaException, GeneroInvalidoException, CarenciaException, PeriodicidadeException, UnicidadeException, BeneficiarioInexistenteException, ProcedimentoInexistenteException {
        ControlePlanoDeSaude plano = new ControlePlanoDeSaude();
        Exame e = new Exame(1, "Hemograma", 0, 150, 1, 30, 1, 1, 120);
        Cirurgia c = new Cirurgia(11, "Cesarea", 10, 150, 3, 5, 600.0, 300.0);
        plano.salvarProcedimento(c);
        plano.salvarProcedimento(e);

        GregorianCalendar gc = new GregorianCalendar();
        // 14 de novembro de 1974
        gc.set(1974, 10, 14);
        Date nasc = gc.getTime();
        // 31 de outubro de 2016
        gc.set(2015, 9, 31);
        Date ingressoNoPlano = gc.getTime();

        //Solicitação em outubro
        gc.set(2016, 9, 01);
        Date hoje = gc.getTime();;

        Beneficiario b1 = new Beneficiario(111, "Pedro",  ingressoNoPlano, nasc, 1);
        plano.cadastrarBeneficiario(b1);

        Beneficiario b2 = new Beneficiario(222, "Pedro",  ingressoNoPlano, nasc, 1);
        plano.cadastrarBeneficiario(b2);

        plano.solicitar(111, 1, hoje);
        plano.solicitar(111, 11, hoje);

        double valor = plano.faturar(10, 2016);
        assertEquals(1120, valor, 0.0001);

        //Solicitação em novembro
        gc.set(2016, 10, 01);
        hoje = gc.getTime();;

        plano.solicitar(222, 1, hoje);
        plano.solicitar(222, 11, hoje);

        //Verifica fatura de novembro
        Double valor2 = plano.faturar(11, 2016);
        assertEquals(1120, valor2, 0.0001);
        //Verifica se outubro continua com mesmo valor
        valor = plano.faturar(10, 2016);
        assertEquals(1120, valor, 0.0001);

        //Solicitação de novo em novembro
        gc.set(2016, 10, 14);
        hoje = gc.getTime();;

        plano.solicitar(222, 1, hoje);
        plano.solicitar(222, 11, hoje);

        //Verifica se novembro muda o valor
        double valor3 = plano.faturar(11, 2016);
        assertEquals(2240, valor3, 0.0001);
        //Verifica se outubro continua com mesmo valor
        valor = plano.faturar(10, 2016);
        assertEquals(1120, valor, 0.0001);
    }

}