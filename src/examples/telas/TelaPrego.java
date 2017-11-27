/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.telas;

import examples.modelo.TipoPrego;
import examples.modelo.TipoMadeira;
import examples.modelo.Kmod3;
import examples.modelo.Kmod2;
import examples.modelo.Kmod1;
import examples.modelo.EspecieMadeira;
import examples.modelo.ClasseQuantidadePregos;
import examples.modelo.ClasseAcoPrego;
import examples.controller.DuasSecoesCortePerpendicularController;
import examples.controller.DuasSecoesCorteParaleloController;
import examples.controller.DuasSecoesCorteInclinadaController;
import examples.controller.ModeloLigacaoProvider;
import examples.controller.VerificadoresPrego;
import examples.controller.UmaSecaoCortePerpendicularController;
import examples.controller.UmaSecaoCorteParaleloController;
import examples.controller.UmaSecaoCorteInclinadaController;
import examples.modelo.ModeloLigacao;
import examples.modelo.CalculoModeloLigacao;
import javax.swing.JOptionPane;
import examples.modelo.Angulo;
import examples.modelo.ClasseMadeira;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

/**
 *
 * @author MarcosVinicius
 */
public class TelaPrego extends javax.swing.JFrame implements ModeloLigacaoProvider{
    
    
    
    private static Object dateFormat;

    /**
     * Creates new form TelaPrincipal
     */
    public double d, c, d1, tp, d2, forcaaplicada, fe0d1, fe0d2, beta, valorFaxrk, valorFaxrkPrego, valorFaxrkFinal, alfa, Rdmin, Rdlig, rd1, rd2, rd3, rd4, rd5, rd6, Rvd, nparafusos, arruela;
    public int fyd, npar;
    private ModeloLigacao modeloLigacao;
    private CalculoModeloLigacao calculoModeloLigacao;
    private Boolean m, IncSim1, IncSim2;
    private int NumParafusos;
    
    clsDataHora objDataHora = new clsDataHora();
    private Map<String, Map<String, Double[]>> normas;
    
    public TelaPrego() {
        m = false;
        alfa = 0;
        d = 0;
        c = 0;
        tp = 0;
        d1 = 0;
        d2 = 0;
        forcaaplicada = 0;
        nparafusos = 0.0;
//        kmod1 = 0;
//        kmod2 = 0;
//        kmod3 = 0;
        rd1 = 0;
        rd2 = 0;
        rd3 = 0;
        rd4 = 0;
        rd5 = 0;
        rd6 = 0;
        Rdmin = 0;
        Rdlig = 0;
        Rvd = 0;
        valorFaxrk = 0;
        valorFaxrkPrego = 0;
        valorFaxrkFinal = 0;
        npar = 0;
        IncSim1 = false;
        IncSim2 = true;
        //Inclinado2 = false;

        //Perpendicular2 = false;
        initComponents();
        inicializaNormas();
        ImagemTipoArruela.setVisible(false);
        CalculoForca01.setVisible(true);
        CalculoForca02.setVisible(true);
        CalculoForca901.setVisible(false);
        CalculoForca902.setVisible(false);
        CalculoForcaAlfa1.setVisible(false);
        CalculoForcaAlfa2.setVisible(false);
        Inclinado2.setVisible(false);
        Inclinado2.setVisible(false);
        
        MadeiraFigura.setVisible(true);
        
        RelatorioRd13.setVisible(true);
        RelatorioRd14.setVisible(true);
        RelatorioRd3.setVisible(true);
        RelatorioRd4.setVisible(true);
        jLabel60.setVisible(true);
        jLabel62.setVisible(true);
        
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(3, false);
        jTabbedPane1.setEnabledAt(4, false);
        jTabbedPane1.setEnabledAt(5, false);
        Next.setEnabled(false);
        Next2.setEnabled(false);
        Next3.setEnabled(false);
        ButtonCalcular.setEnabled(false);
        SerradaButton.setEnabled(false);
        RecompostaButton.setEnabled(false);
        btn1SecaoCorte.setEnabled(false);
        jToggleButton1.setEnabled(false);
        jToggleButton2.setEnabled(false);
        ConiferasButton.setEnabled(false);
        FolhosasButton.setEnabled(false);

        
        ItemListener il = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (((JComboBox) e.getSource()).getSelectedIndex() == 0) {
                    
                }
            }
        };
        ComboKmod1.addItemListener(il);
        
        Espessura1.setInputVerifier(new VerificadoresPrego.VerificadorEspessura1(this.jLabelStatusPrego, this));
        Espessura2.setInputVerifier(new VerificadoresPrego.VerificadorEspessura2(this.jLabelStatusPrego, this));
        ValorAngulo.setInputVerifier(new VerificadoresPrego.VerificadorValorAngulo(this.jLabelStatusPrego, this));
        ComboElem1ClasseMadeira.setInputVerifier(new VerificadoresPrego.VerificadorComboClasseElem1(this.jLabelStatusPrego, this));
        ComboElem2ClasseMadeira.setInputVerifier(new VerificadoresPrego.VerificadorComboClasseElem2(this.jLabelStatusPrego, this));
        ComboKmod1.setInputVerifier(new VerificadoresPrego.VerificadorKmod1(this.jLabelStatusPrego, this));
        ComboKmod2.setInputVerifier(new VerificadoresPrego.VerificadorKmod2(this.jLabelStatusPrego, this));
        ComboKmod3.setInputVerifier(new VerificadoresPrego.VerificadorKmod3(this.jLabelStatusPrego, this));
        ComboQuantPregos.setInputVerifier(new VerificadoresPrego.VerificadorComboQuantParafuso(this.jLabelStatusPrego, this));
        ComboTipoPrego.setInputVerifier(new VerificadoresPrego.VerificadorComboTipoParafuso(this.jLabelStatusPrego, this));
        ComboAco.setInputVerifier(new VerificadoresPrego.VerificadorComboAcoPrego(this.jLabelStatusPrego, this));
        
    }
    
    private void setNumParafusos(int x) { // alterar a variável global
        NumParafusos = x;
        
    }
    
    public int getNumParafusos() {
        return NumParafusos;
    }
    
    private void inicializaNormas() {
        // Preenche os ComboBoxes
        //ComboElem1ClasseMadeira.removeAllItems();
        for (ClasseMadeira classe : ClasseMadeira.values()) {
            ComboElem1ClasseMadeira.addItem(classe);
        }

//        ComboElem1ClasseMadeira.setModel(new DefaultComboBoxModel(ClasseMadeira.values()));
        for (ClasseMadeira classe : ClasseMadeira.values()) {
            ComboElem2ClasseMadeira.addItem(classe);
        }
        
        for (Kmod1 classe : Kmod1.values()) {
            ComboKmod1.addItem(classe);
        }
        
        for (Kmod2 classe : Kmod2.values()) {
            ComboKmod2.addItem(classe);
        }
        
        for (Kmod3 classe : Kmod3.values()) {
            ComboKmod3.addItem(classe);
        }
        
        for (TipoPrego classe: TipoPrego.values()){
            ComboTipoPrego.addItem(classe);
        }
        
        for (ClasseQuantidadePregos classe: ClasseQuantidadePregos.values()){
            ComboQuantPregos.addItem(classe);
        }
        for (ClasseAcoPrego classe: ClasseAcoPrego.values()){
            ComboAco.addItem(classe);
        }
        
        this.normas = new HashMap<String, Map<String, Double[]>>();
        
        Map<String, Double[]> parafusos = new HashMap<String, Double[]>();
        normas.put("DIN 436", parafusos);
        parafusos.put("M10", new Double[]{11.0, 30.0, 1.9078, 1.0, 3.0});
        parafusos.put("M12", new Double[]{13.5, 40.0, 1.7391, 1.0, 3.0});
        parafusos.put("M16", new Double[]{17.5, 50.0, 1.5166, 1.0, 3.0});
        parafusos.put("M20", new Double[]{22.0, 60.0, 1.3868, 1.0, 3.0});
        parafusos.put("M22", new Double[]{24.0, 70.0, 1.3338, 1.0, 3.0});
        parafusos.put("M24", new Double[]{26.0, 80.0, 1.2963, 1.0, 3.0});
        parafusos.put("M27", new Double[]{30.0, 90.0, 1.25, 1.0, 3.0});
        parafusos.put("M30", new Double[]{33.0, 95.0, 1.2125, 1.0, 3.0});
        
        parafusos = new HashMap<String, Double[]>();
        normas.put("DIN 440 R", parafusos);
        parafusos.put("M10", new Double[]{11.0, 34.0, 1.9078, 2.0, 1.0});
        parafusos.put("M12", new Double[]{13.5, 44.0, 1.7391, 2.0, 1.0});
        parafusos.put("M16", new Double[]{17.5, 56.0, 1.5166, 2.0, 1.0});
        parafusos.put("M20", new Double[]{22.0, 72.0, 1.3868, 2.0, 1.0});
        parafusos.put("M22", new Double[]{24.0, 80.0, 1.3338, 2.0, 1.0});
        parafusos.put("M24", new Double[]{26.0, 85.0, 1.2963, 2.0, 1.0});
        parafusos.put("M27", new Double[]{30.0, 98.0, 1.25, 2.0, 1.0});
        parafusos.put("M30", new Double[]{33.0, 105.0, 1.2125, 2.0, 1.0});
        parafusos.put("M33", new Double[]{36.0, 112.0, 1.1805, 2.0, 1.0});
        parafusos.put("M36", new Double[]{39.0, 125.0, 1.1567, 2.0, 1.0});
        
        parafusos = new HashMap<String, Double[]>();
        normas.put("DIN 440 V", parafusos);
        parafusos.put("M10", new Double[]{11.0, 34.0, 1.9078, 3.0, 2.0});
        parafusos.put("M12", new Double[]{13.5, 44.0, 1.7391, 3.0, 2.0});
        parafusos.put("M16", new Double[]{17.5, 56.0, 1.5166, 3.0, 2.0});
        parafusos.put("M20", new Double[]{22.0, 72.0, 1.3868, 3.0, 2.0});
        parafusos.put("M22", new Double[]{24.0, 80.0, 1.3338, 3.0, 2.0});
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GroupSecoesCorte = new javax.swing.ButtonGroup();
        GroupAngulacao = new javax.swing.ButtonGroup();
        GroupTesteParafuso = new javax.swing.ButtonGroup();
        GroupInclinacaoSim = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Inicio = new javax.swing.JPanel();
        LogoPrograma = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        Voltar = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        SecoesCorte = new javax.swing.JPanel();
        btn1SecaoCorte = new javax.swing.JToggleButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        Next = new javax.swing.JButton();
        PainelTipoMadeira = new javax.swing.JPanel();
        SerradaButton = new javax.swing.JToggleButton();
        RecompostaButton = new javax.swing.JToggleButton();
        PainelEspecieMadeira = new javax.swing.JPanel();
        ConiferasButton = new javax.swing.JToggleButton();
        FolhosasButton = new javax.swing.JToggleButton();
        ElementosMadeira = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        ComboElem1ClasseMadeira = new javax.swing.JComboBox();
        jLabel_classe_madeira_1 = new javax.swing.JLabel();
        jLabel_elemento1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ValorAngulo = new javax.swing.JTextField();
        jLabelUnEspessura = new javax.swing.JLabel();
        Fcok1 = new javax.swing.JLabel();
        UnFcok1 = new javax.swing.JLabel();
        ValorFc01 = new javax.swing.JLabel();
        Densidade1 = new javax.swing.JLabel();
        ValorDensidade1 = new javax.swing.JLabel();
        UnDensidade1 = new javax.swing.JLabel();
        ValorFvok1 = new javax.swing.JLabel();
        UnFv0k1 = new javax.swing.JLabel();
        Fv0k1 = new javax.swing.JLabel();
        Ec0m1 = new javax.swing.JLabel();
        UnEc0m1 = new javax.swing.JLabel();
        ValorEc0m1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        PossuiInclinacao1 = new javax.swing.JLabel();
        InclinacaoSim1 = new javax.swing.JRadioButton();
        PossuiInclinacao11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Espessura1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        ComboElem2ClasseMadeira = new javax.swing.JComboBox();
        jLabel_classe_madeira_2 = new javax.swing.JLabel();
        jLabel_elemento2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Espessura2 = new javax.swing.JTextField();
        jLabelUnEspessura1 = new javax.swing.JLabel();
        Fcok2 = new javax.swing.JLabel();
        UnFcok2 = new javax.swing.JLabel();
        ValorFc2 = new javax.swing.JLabel();
        Densidade2 = new javax.swing.JLabel();
        ValorDensidade2 = new javax.swing.JLabel();
        UnDensidade2 = new javax.swing.JLabel();
        ValorFvok2 = new javax.swing.JLabel();
        UnFv0k2 = new javax.swing.JLabel();
        Fv0k2 = new javax.swing.JLabel();
        Ec0m2 = new javax.swing.JLabel();
        UnEc0m2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        ValorEc0m2 = new javax.swing.JLabel();
        InclinacaoSim2 = new javax.swing.JRadioButton();
        PossuiInclinacao3 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        MadeiraFigura = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        ComboKmod1 = new javax.swing.JComboBox();
        ComboKmod3 = new javax.swing.JComboBox();
        ComboKmod2 = new javax.swing.JComboBox();
        Textkmod3 = new javax.swing.JLabel();
        Textkmod1 = new javax.swing.JLabel();
        Textkmod2 = new javax.swing.JLabel();
        Next2 = new javax.swing.JButton();
        ElementosMetalicos = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TamanhoParafuso = new javax.swing.JLabel();
        ComboTipoPrego = new javax.swing.JComboBox();
        Diametro = new javax.swing.JLabel();
        ValorDiametro = new javax.swing.JLabel();
        AreaParafuso = new javax.swing.JLabel();
        ValorComprimento = new javax.swing.JLabel();
        NumeroParafusos = new javax.swing.JLabel();
        ClasseAco = new javax.swing.JLabel();
        ComboAco = new javax.swing.JComboBox();
        Fyk = new javax.swing.JLabel();
        fuk = new javax.swing.JLabel();
        ValorFyk = new javax.swing.JLabel();
        ValorFuk = new javax.swing.JLabel();
        UnFyk = new javax.swing.JLabel();
        UnFuk = new javax.swing.JLabel();
        TesteParafuso = new javax.swing.JLabel();
        TesteParafusoSim = new javax.swing.JRadioButton();
        TesteParafusoNao = new javax.swing.JRadioButton();
        ComboQuantPregos = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        ButtonCalcular = new javax.swing.JButton();
        FiguraTipoParafuso = new javax.swing.JButton();
        ImagemTipoArruela = new javax.swing.JLabel();
        Resultado = new javax.swing.JPanel();
        LabelModeloFalha = new javax.swing.JLabel();
        LabelResultadoModeloFalha = new javax.swing.JLabel();
        LabelResistenciaLigacao = new javax.swing.JLabel();
        ResultadoFvk = new javax.swing.JLabel();
        FiguraResultadoModoFalha = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        ResultadoRk = new javax.swing.JLabel();
        ResultadoRd = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        Next3 = new javax.swing.JButton();
        Relatorio = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        RelatorioFinal = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        RelatorioSecaoCorte = new javax.swing.JLabel();
        RelatorioAngulacao = new javax.swing.JLabel();
        Relatoriofcok4 = new javax.swing.JLabel();
        RelatorioElem1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        RelatorioDensidade1 = new javax.swing.JLabel();
        RelatorioEc0m1 = new javax.swing.JLabel();
        Relatoriofv0k1 = new javax.swing.JLabel();
        Relatoriofcok1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        RelatorioClasseElem1 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        RelatorioEspessura1 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        Inclinado1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        RelatorioElem2 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        RelatorioEc0m2 = new javax.swing.JLabel();
        RelatorioDensidade2 = new javax.swing.JLabel();
        Relatoriofcok2 = new javax.swing.JLabel();
        Relatoriofv0k2 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        RelatorioClasseElem2 = new javax.swing.JLabel();
        RelatorioEspessura2 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        Inclinado2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        RelatorioCoeficientes = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        Relatoriokmod3 = new javax.swing.JLabel();
        RelatorioAngulo = new javax.swing.JLabel();
        RelatorioKmod1 = new javax.swing.JLabel();
        Relatoriokmod2 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        RelatorioParafuso = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        RelatorioTipoParafuso = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        RelatorioDiametro = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        RelatorioNParafusos = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        RelatorioClasseAco = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        Relatoriofaxrk = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        Relatoriofyk = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        Relatoriofuk = new javax.swing.JLabel();
        FiguraParafuso = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        RelatorioComprimento = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        RelatorioFibras = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        CalculoForca01 = new javax.swing.JLabel();
        CalculoForca02 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        RelatorioFc0d1 = new javax.swing.JLabel();
        RelatorioFc0d2 = new javax.swing.JLabel();
        RelatorioFaxrk = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        CalculoForcaAlfa1 = new javax.swing.JLabel();
        CalculoForca901 = new javax.swing.JLabel();
        CalculoForcaAlfa2 = new javax.swing.JLabel();
        CalculoForca902 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        RelatorioRd11 = new javax.swing.JLabel();
        RelatorioRd12 = new javax.swing.JLabel();
        RelatorioRd13 = new javax.swing.JLabel();
        RelatorioRd1 = new javax.swing.JLabel();
        RelatorioRd14 = new javax.swing.JLabel();
        RelatorioRd2 = new javax.swing.JLabel();
        RelatorioRd4 = new javax.swing.JLabel();
        RelatorioRd3 = new javax.swing.JLabel();
        RelatorioRd15 = new javax.swing.JLabel();
        RelatorioRd16 = new javax.swing.JLabel();
        RelatorioRd6 = new javax.swing.JLabel();
        RelatorioRd5 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        RelatorioMyd = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        RelatorioBeta = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        RelatorioRd23 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        RelatorioRd24 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        ModoFalha = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        RelatorioRk = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        FiguraSecoes = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        Data = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Consideracao1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        teste = new javax.swing.JTextArea();
        Hora = new javax.swing.JLabel();
        RLogo = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        RelatorioFvk = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        RelatorioRd = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabelStatusPrego = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TCD - Timber Connections Design");
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setIconImages(null);
        setMaximumSize(new java.awt.Dimension(900, 800));
        setMinimumSize(new java.awt.Dimension(820, 730));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(796, 680));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(900, 700));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(700, 600));
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(795, 660));
        jTabbedPane1.setRequestFocusEnabled(false);
        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusLost(evt);
            }
        });

        Inicio.setBackground(new java.awt.Color(204, 204, 204));
        Inicio.setPreferredSize(new java.awt.Dimension(647, 625));

        LogoPrograma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/Logo/logosket.png"))); // NOI18N

        jLabel97.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel97.setText("TCD - Timber Connections Design");

        Voltar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Voltar.setText("Voltar");
        Voltar.setToolTipText("Escolha esta opção para retornar a tela inicial.");
        Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoltarActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel40.setText("Cálculo de ligações pregadas entre elementos de madeira.");

        jButton4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton4.setText("Iniciar Cálculo");
        jButton4.setToolTipText("Escolha esta opção para iniciar o dimensionamento de sua ligação.");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout InicioLayout = new javax.swing.GroupLayout(Inicio);
        Inicio.setLayout(InicioLayout);
        InicioLayout.setHorizontalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioLayout.createSequentialGroup()
                            .addComponent(jLabel97)
                            .addGap(228, 228, 228))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioLayout.createSequentialGroup()
                            .addComponent(LogoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(198, 198, 198)))
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel40)
                        .addContainerGap(270, Short.MAX_VALUE))))
            .addGroup(InicioLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(Voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 140, Short.MAX_VALUE))
        );
        InicioLayout.setVerticalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicioLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(LogoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel97)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addGap(82, 82, 82)
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inicio", Inicio);

        SecoesCorte.setBackground(new java.awt.Color(204, 204, 204));
        SecoesCorte.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
        SecoesCorte.setMaximumSize(new java.awt.Dimension(40000, 40000));
        SecoesCorte.setPreferredSize(new java.awt.Dimension(770, 625));

        GroupSecoesCorte.add(btn1SecaoCorte);
        btn1SecaoCorte.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn1SecaoCorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensBotao/BotaoPregoSimples.png"))); // NOI18N
        btn1SecaoCorte.setText("Corte Simples ");
        btn1SecaoCorte.setToolTipText("Escolha esta opção se a ligação apresentar apenas uma seção de corte no prego.");
        btn1SecaoCorte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn1SecaoCorte.setInheritsPopupMenu(true);
        btn1SecaoCorte.setName(""); // NOI18N
        btn1SecaoCorte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn1SecaoCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1SecaoCorteActionPerformed(evt);
            }
        });

        GroupSecoesCorte.add(jToggleButton1);
        jToggleButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensBotao/BotaoPregoSimples2.png"))); // NOI18N
        jToggleButton1.setText("Corte Simples");
        jToggleButton1.setToolTipText("Escolha esta opção se a ligação apresentar apenas uma seção de corte no prego.");
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        GroupSecoesCorte.add(jToggleButton2);
        jToggleButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensBotao/Duplo.png"))); // NOI18N
        jToggleButton2.setText("Corte Duplo");
        jToggleButton2.setToolTipText("Escolha esta opção se a ligação apresentar apenas duas seções de corte no prego.");
        jToggleButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensBotao/Next.png"))); // NOI18N
        Next.setToolTipText("Clique para avançar.");
        Next.setEnabled(false);
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        PainelTipoMadeira.setBackground(new java.awt.Color(204, 204, 204));
        PainelTipoMadeira.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Tipo da Madeira", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 12))); // NOI18N
        PainelTipoMadeira.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        SerradaButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        SerradaButton.setText("Serrada/Roliça/MLC/Compensado");
        SerradaButton.setToolTipText("");
        SerradaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SerradaButtonActionPerformed(evt);
            }
        });

        RecompostaButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RecompostaButton.setText("Recomposta");
        RecompostaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecompostaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelTipoMadeiraLayout = new javax.swing.GroupLayout(PainelTipoMadeira);
        PainelTipoMadeira.setLayout(PainelTipoMadeiraLayout);
        PainelTipoMadeiraLayout.setHorizontalGroup(
            PainelTipoMadeiraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTipoMadeiraLayout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(PainelTipoMadeiraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelTipoMadeiraLayout.createSequentialGroup()
                        .addComponent(RecompostaButton)
                        .addGap(135, 135, 135))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelTipoMadeiraLayout.createSequentialGroup()
                        .addComponent(SerradaButton)
                        .addGap(74, 74, 74))))
        );
        PainelTipoMadeiraLayout.setVerticalGroup(
            PainelTipoMadeiraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTipoMadeiraLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(SerradaButton)
                .addGap(58, 58, 58)
                .addComponent(RecompostaButton)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        PainelEspecieMadeira.setBackground(new java.awt.Color(204, 204, 204));
        PainelEspecieMadeira.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Espécie da Madeira", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 12))); // NOI18N
        PainelEspecieMadeira.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        PainelEspecieMadeira.setName(""); // NOI18N

        ConiferasButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ConiferasButton.setText("Coníferas");
        ConiferasButton.setToolTipText("Alguns exemplos: Pinho-Bravo, Pinho-do-Paraná, Pinus (em geral).");
        ConiferasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConiferasButtonActionPerformed(evt);
            }
        });

        FolhosasButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        FolhosasButton.setText("Folhosas");
        FolhosasButton.setToolTipText("Alguns exemplos: Peroba-Rosa, Angico, Imbuia, Jatobá, Mogno, Cerejeira, Cedro, Freijó, Aroeira, Ipê, Pau-Marfim.");
        FolhosasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FolhosasButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelEspecieMadeiraLayout = new javax.swing.GroupLayout(PainelEspecieMadeira);
        PainelEspecieMadeira.setLayout(PainelEspecieMadeiraLayout);
        PainelEspecieMadeiraLayout.setHorizontalGroup(
            PainelEspecieMadeiraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEspecieMadeiraLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(PainelEspecieMadeiraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FolhosasButton)
                    .addComponent(ConiferasButton))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        PainelEspecieMadeiraLayout.setVerticalGroup(
            PainelEspecieMadeiraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEspecieMadeiraLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(ConiferasButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FolhosasButton)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout SecoesCorteLayout = new javax.swing.GroupLayout(SecoesCorte);
        SecoesCorte.setLayout(SecoesCorteLayout);
        SecoesCorteLayout.setHorizontalGroup(
            SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SecoesCorteLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btn1SecaoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(SecoesCorteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PainelEspecieMadeira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelTipoMadeira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SecoesCorteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Next, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        SecoesCorteLayout.setVerticalGroup(
            SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SecoesCorteLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn1SecaoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PainelTipoMadeira, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PainelEspecieMadeira, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Next)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Modelos de Ligação", SecoesCorte);

        ElementosMadeira.setBackground(new java.awt.Color(204, 204, 204));
        ElementosMadeira.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setPreferredSize(new java.awt.Dimension(385, 225));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ComboElem1ClasseMadeira.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboElem1ClasseMadeira.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha a Classe da Madeira" }));
        ComboElem1ClasseMadeira.setToolTipText("Defina a classe de madeira do elemento 1, baseado nas tabelas 2 e 3 da revisão da norma ABNT NBR 7190 (2011).");
        ComboElem1ClasseMadeira.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ComboElem1ClasseMadeiraFocusLost(evt);
            }
        });
        ComboElem1ClasseMadeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboElem1ClasseMadeiraActionPerformed(evt);
            }
        });
        ComboElem1ClasseMadeira.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ComboElem1ClasseMadeiraPropertyChange(evt);
            }
        });
        jPanel7.add(ComboElem1ClasseMadeira, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 35, 211, 29));

        jLabel_classe_madeira_1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel_classe_madeira_1.setText("Classe da Madeira:");
        jPanel7.add(jLabel_classe_madeira_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 45, -1, -1));

        jLabel_elemento1.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        jLabel_elemento1.setText("Elemento 1");
        jLabel_elemento1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(jLabel_elemento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 5, 88, 22));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Espessura (t1):");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 145, -1, -1));

        ValorAngulo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorAngulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ValorAngulo.setText("0");
        ValorAngulo.setToolTipText("Insira o ângulo entre os elementos 1 e 2 de madeira.");
        ValorAngulo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ValorAngulo(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValorAnguloFocusLost(evt);
            }
        });
        ValorAngulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ValorAnguloMouseClicked(evt);
            }
        });
        ValorAngulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValorAnguloActionPerformed(evt);
            }
        });
        ValorAngulo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ValorAnguloPropertyChange(evt);
            }
        });
        ValorAngulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ValorAnguloKeyTyped(evt);
            }
        });
        jPanel7.add(ValorAngulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 70, -1));

        jLabelUnEspessura.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabelUnEspessura.setText("(mm)");
        jPanel7.add(jLabelUnEspessura, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 147, -1, -1));

        Fcok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Fcok1.setText("fc0,k:");
        jPanel7.add(Fcok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        UnFcok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnFcok1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        UnFcok1.setText("(MPa)");
        jPanel7.add(UnFcok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));
        UnFcok1.getAccessibleContext().setAccessibleDescription("");

        ValorFc01.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorFc01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorFc01.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.add(ValorFc01, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 80, 40, 20));

        Densidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Densidade1.setText("Densidade:");
        jPanel7.add(Densidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));

        ValorDensidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorDensidade1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorDensidade1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.add(ValorDensidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 40, 20));

        UnDensidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnDensidade1.setText("(kg/m³)");
        jPanel7.add(UnDensidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 80, -1, -1));

        ValorFvok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorFvok1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorFvok1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.add(ValorFvok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 110, 40, 20));

        UnFv0k1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnFv0k1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        UnFv0k1.setText("(MPa)");
        jPanel7.add(UnFv0k1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        Fv0k1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Fv0k1.setText("fv0,k:");
        jPanel7.add(Fv0k1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        Ec0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Ec0m1.setText("Ec0,m:");
        jPanel7.add(Ec0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));

        UnEc0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnEc0m1.setText("(MPa)");
        jPanel7.add(UnEc0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 110, -1, -1));

        ValorEc0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorEc0m1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorEc0m1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ValorEc0m1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(ValorEc0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 50, 20));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel7.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 69, 10, 65));

        PossuiInclinacao1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        PossuiInclinacao1.setText("A força indicada na figura é paralela");
        jPanel7.add(PossuiInclinacao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 195, -1, -1));

        GroupInclinacaoSim.add(InclinacaoSim1);
        InclinacaoSim1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        InclinacaoSim1.setText("Sim");
        InclinacaoSim1.setToolTipText("Se existir inclinação, indique qual elemento está inclinado. ");
        InclinacaoSim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InclinacaoSim1ActionPerformed(evt);
            }
        });
        jPanel7.add(InclinacaoSim1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, -1, -1));

        PossuiInclinacao11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        PossuiInclinacao11.setText("às fibras do elemento 1?");
        jPanel7.add(PossuiInclinacao11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setText("Ângulo entre as peças:");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 175, 150, -1));

        Espessura1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Espessura1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Espessura1.setText("Digite a espessura");
        Espessura1.setToolTipText("Insira a espessura do elemento 1. O elemento 1 está indicado na imagem.");
        Espessura1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Espessura1(evt);
            }
        });
        Espessura1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Espessura1ActionPerformed(evt);
            }
        });
        Espessura1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                Espessura1PropertyChange(evt);
            }
        });
        Espessura1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Espessura1KeyTyped(evt);
            }
        });
        jPanel7.add(Espessura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 143, 130, -1));

        jLabel39.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel39.setText("(°)");
        jPanel7.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 170, 20, 20));

        ElementosMadeira.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 5, -1, 230));

        jPanel13.setBackground(new java.awt.Color(153, 153, 153));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setPreferredSize(new java.awt.Dimension(385, 225));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ComboElem2ClasseMadeira.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboElem2ClasseMadeira.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha a Classe da Madeira" }));
        ComboElem2ClasseMadeira.setToolTipText("Defina a classe de madeira do elemento 2, baseado nas tabelas 2 e 3 da revisão da norma ABNT NBR 7190 (2011).");
        ComboElem2ClasseMadeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboElem2ClasseMadeiraActionPerformed(evt);
            }
        });
        ComboElem2ClasseMadeira.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ComboElem2ClasseMadeiraPropertyChange(evt);
            }
        });
        jPanel13.add(ComboElem2ClasseMadeira, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 30, 211, 29));

        jLabel_classe_madeira_2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel_classe_madeira_2.setText("Classe da Madeira:");
        jPanel13.add(jLabel_classe_madeira_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel_elemento2.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        jLabel_elemento2.setText("Elemento 2");
        jPanel13.add(jLabel_elemento2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 5, 88, 22));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setText("Espessura (t2):");
        jPanel13.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        Espessura2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Espessura2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Espessura2.setText("Digite a espessura");
        Espessura2.setToolTipText("Insira a espessura do elemento 2. O elemento 2 está indicado na imagem.");
        Espessura2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                LimparExpessura2(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Espessura2FocusLost(evt);
            }
        });
        Espessura2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Espessura2ActionPerformed(evt);
            }
        });
        Espessura2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Espessura2KeyTyped(evt);
            }
        });
        jPanel13.add(Espessura2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 138, 126, -1));

        jLabelUnEspessura1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabelUnEspessura1.setText("(mm)");
        jPanel13.add(jLabelUnEspessura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 142, -1, -1));

        Fcok2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Fcok2.setText("fc0,k:");
        jPanel13.add(Fcok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 75, -1, -1));

        UnFcok2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnFcok2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        UnFcok2.setText("(MPa)");
        jPanel13.add(UnFcok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 75, -1, -1));

        ValorFc2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorFc2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorFc2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.add(ValorFc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 75, 40, 20));

        Densidade2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Densidade2.setText("Densidade:");
        jPanel13.add(Densidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 75, -1, -1));

        ValorDensidade2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorDensidade2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorDensidade2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.add(ValorDensidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 75, 40, 20));

        UnDensidade2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnDensidade2.setText("(kg/m³)");
        jPanel13.add(UnDensidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 75, -1, -1));

        ValorFvok2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorFvok2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorFvok2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.add(ValorFvok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 105, 40, 20));

        UnFv0k2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnFv0k2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        UnFv0k2.setText("(MPa)");
        jPanel13.add(UnFv0k2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 105, -1, -1));

        Fv0k2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Fv0k2.setText("fv0,k:");
        jPanel13.add(Fv0k2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 105, -1, -1));

        Ec0m2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Ec0m2.setText("Ec0,m:");
        jPanel13.add(Ec0m2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 105, -1, -1));

        UnEc0m2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnEc0m2.setText("(MPa)");
        jPanel13.add(UnEc0m2, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 105, -1, -1));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel13.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 66, 10, 65));

        ValorEc0m2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorEc0m2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorEc0m2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.add(ValorEc0m2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 105, 50, 20));

        GroupInclinacaoSim.add(InclinacaoSim2);
        InclinacaoSim2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        InclinacaoSim2.setSelected(true);
        InclinacaoSim2.setText("Sim");
        InclinacaoSim2.setToolTipText("Se existir inclinação, indique qual elemento está inclinado.");
        InclinacaoSim2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InclinacaoSim2ActionPerformed(evt);
            }
        });
        jPanel13.add(InclinacaoSim2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 175, -1, -1));

        PossuiInclinacao3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        PossuiInclinacao3.setText("A força indicada na figura é paralela");
        jPanel13.add(PossuiInclinacao3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel68.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel68.setText("às fibras do elemento 2?");
        jPanel13.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 185, -1, -1));

        ElementosMadeira.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 240, -1, 205));

        MadeiraFigura.setToolTipText("Esta imagem caracteriza os elementos de madeira.");
        MadeiraFigura.setContentAreaFilled(false);
        MadeiraFigura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MadeiraFiguraActionPerformed(evt);
            }
        });
        ElementosMadeira.add(MadeiraFigura, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 350, 280));

        jPanel12.setBackground(new java.awt.Color(153, 153, 153));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel101.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        jLabel101.setText("Coeficientes");
        jPanel12.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        jLabel102.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel102.setText("Classes de Carregamento (kmod1):");
        jPanel12.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 210, -1));

        jLabel103.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel103.setText("Classe de Umidade (kmod2):");
        jPanel12.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 200, -1));

        jLabel104.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel104.setText("Qualidade da Madeira (kmod3):");
        jPanel12.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 200, -1));

        ComboKmod1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboKmod1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha o Kmod 1" }));
        ComboKmod1.setToolTipText("Insira o valor do Kmod 1, o qual é definido pela tabela 4 da revisão da norma ABNT NBR 7190 (2011).");
        ComboKmod1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboKmod1ActionPerformed(evt);
            }
        });
        jPanel12.add(ComboKmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 340, 30));

        ComboKmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboKmod3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha o Kmod 3" }));
        ComboKmod3.setToolTipText("Insira o valor do Kmod 3, o qual é definido pelas tabelas 6 e 7 da revisão da norma ABNT NBR 7190 (2011).");
        ComboKmod3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboKmod3ActionPerformed(evt);
            }
        });
        jPanel12.add(ComboKmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 340, 30));

        ComboKmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboKmod2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha o Kmod 2" }));
        ComboKmod2.setToolTipText("Insira o valor do Kmod 2, o qual é definido pela tabela 5 da revisão da norma ABNT NBR 7190 (2011).");
        ComboKmod2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboKmod2ActionPerformed(evt);
            }
        });
        jPanel12.add(ComboKmod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 340, 30));

        Textkmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Textkmod3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Textkmod3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.add(Textkmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 125, 40, 20));

        Textkmod1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Textkmod1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Textkmod1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.add(Textkmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 45, 40, 20));

        Textkmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Textkmod2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Textkmod2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.add(Textkmod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 85, 40, 20));

        ElementosMadeira.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 450, 710, 160));

        Next2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensBotao/Next.png"))); // NOI18N
        Next2.setToolTipText("Clique para avançar.");
        Next2.setEnabled(false);
        Next2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Next2ActionPerformed(evt);
            }
        });
        ElementosMadeira.add(Next2, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 570, 40, -1));

        jTabbedPane1.addTab("Elementos da Ligação", ElementosMadeira);

        ElementosMetalicos.setBackground(new java.awt.Color(204, 204, 204));
        ElementosMetalicos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(153, 153, 153));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        jLabel1.setText("PREGO LISO COM CABEÇA");
        jPanel10.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        TamanhoParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TamanhoParafuso.setText("Dimensões:");
        jPanel10.add(TamanhoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        ComboTipoPrego.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboTipoPrego.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha as dimensões do prego", "17 x 21*", "17 x 24", "17 x 27*", "17 x 30", "18 x 11", "18 x 21", "18 x 24", "18 x 27* Caibro", "18 x 30* Caibro", "18 x 33", "18 x 36* Caibro", "19 x 15", "19 x 21", "19 x 27", "19 x 30", "19 x 33", "19 x 36* Outros", "19 x 39", "19 x 42", "20 x 30*", "20 x 33", "20 x 36", "20 x 39", "20 x 42*", "20 x 48", "21 x 33", "21 x 36", "21 x 42*", "21 x 45", "21 x 48", "21 x 54", "22 x 42* Tesoura", "22 x 45", "22 x 48* Tesoura", "22 x 54", "23 x 45", "23 x 54", "23 x 60", "23 x 66", "24 x 60", "24 x 66", "25 x 72", "26 x 72", "26 x 78", "26 x 84" }));
        ComboTipoPrego.setToolTipText("Escolha o tipo de parafuso utilizado, baseado na norma ISO 4016 (2001).");
        ComboTipoPrego.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ComboTipoPrego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTipoPregoActionPerformed(evt);
            }
        });
        jPanel10.add(ComboTipoPrego, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 40, 610, -1));

        Diametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Diametro.setText("Diâmetro:");
        jPanel10.add(Diametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));

        ValorDiametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorDiametro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorDiametro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.add(ValorDiametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 40, 20));

        AreaParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        AreaParafuso.setText("Comprimento:");
        jPanel10.add(AreaParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, -1, -1));

        ValorComprimento.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorComprimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorComprimento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.add(ValorComprimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 40, 20));

        NumeroParafusos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        NumeroParafusos.setText("Número de Pregos:");
        jPanel10.add(NumeroParafusos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        ClasseAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ClasseAco.setText("Classe do Aço:");
        jPanel10.add(ClasseAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, -1, -1));

        ComboAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboAco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha a Classe de Aço", "Baixo Teor de Carbono", " " }));
        ComboAco.setToolTipText("Defina a classe do aço do parafuso, baseado revisão da norma ABNT NBR 7190 (2011).");
        ComboAco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboAcoActionPerformed(evt);
            }
        });
        jPanel10.add(ComboAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 130, 221, -1));

        Fyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Fyk.setText("fy,k:");
        jPanel10.add(Fyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 165, -1, -1));

        fuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        fuk.setText("fu,k:");
        jPanel10.add(fuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 165, -1, -1));

        ValorFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorFyk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorFyk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.add(ValorFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 165, 40, 20));

        ValorFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorFuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorFuk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.add(ValorFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 165, 40, 20));

        UnFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnFyk.setText("(MPa)");
        jPanel10.add(UnFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 165, -1, -1));

        UnFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnFuk.setText("(MPa)");
        jPanel10.add(UnFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 165, -1, -1));

        TesteParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TesteParafuso.setText("Foram realizados ensaios na ligação em estudo que comprovem que o");
        TesteParafuso.setToolTipText("Considera-se ou não a força de arrancamento causada pelo parafuso na madeira. EUROCODE 5 (2004);");
        jPanel10.add(TesteParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        GroupTesteParafuso.add(TesteParafusoSim);
        TesteParafusoSim.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TesteParafusoSim.setText("Sim");
        TesteParafusoSim.setToolTipText("Escolha se a ligação considera ou não a força de arrancamento causada pelo parafuso na madeira.");
        TesteParafusoSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TesteParafusoSimActionPerformed(evt);
            }
        });
        jPanel10.add(TesteParafusoSim, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 215, 50, -1));

        GroupTesteParafuso.add(TesteParafusoNao);
        TesteParafusoNao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TesteParafusoNao.setText("Não");
        TesteParafusoNao.setToolTipText("Escolha se a ligação considera ou não a força de arrancamento causada pelo parafuso na madeira.\n");
        TesteParafusoNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TesteParafusoNaoActionPerformed(evt);
            }
        });
        jPanel10.add(TesteParafusoNao, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 215, 50, -1));

        ComboQuantPregos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboQuantPregos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha a quantidade de pregos", "2", "4", "5", "6", "8", "10", "12", "14" }));
        ComboQuantPregos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboQuantPregosActionPerformed(evt);
            }
        });
        jPanel10.add(ComboQuantPregos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 230, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setText("(mm)");
        jPanel10.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel10.setText("(mm)");
        jPanel10.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel11.setText("(*) - Pregos mais utilizados.");
        jPanel10.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 65, -1, -1));

        jLabel41.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel41.setText("efeito de confinamento possa ser utilizado?");
        jPanel10.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 225, -1, -1));

        ElementosMetalicos.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 780, 250));

        ButtonCalcular.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        ButtonCalcular.setText("CALCULAR LIGAÇÃO");
        ButtonCalcular.setToolTipText("Clique aqui para calcular sua ligação. Fique atento! Caso haja informações faltantes ou inconsistentes, o cálculo não será realizado e aparecerá uma mensagem.");
        ButtonCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCalcularActionPerformed(evt);
            }
        });
        ElementosMetalicos.add(ButtonCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, 210, 41));

        FiguraTipoParafuso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/Imagens/Prego.png"))); // NOI18N
        FiguraTipoParafuso.setContentAreaFilled(false);
        FiguraTipoParafuso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiguraTipoParafusoActionPerformed(evt);
            }
        });
        ElementosMetalicos.add(FiguraTipoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 340, 160));
        ElementosMetalicos.add(ImagemTipoArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 300, 150));

        jTabbedPane1.addTab("Conectores", ElementosMetalicos);

        Resultado.setBackground(new java.awt.Color(204, 204, 204));
        Resultado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabelModeloFalha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        LabelModeloFalha.setText("Modelo de falha:");
        Resultado.add(LabelModeloFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 110, 20));

        LabelResultadoModeloFalha.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Resultado.add(LabelResultadoModeloFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 660, 20));

        LabelResistenciaLigacao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        LabelResistenciaLigacao.setText("Fv,Rk:");
        Resultado.add(LabelResistenciaLigacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 60, 20));

        ResultadoFvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ResultadoFvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Resultado.add(ResultadoFvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 80, 20));

        FiguraResultadoModoFalha.setContentAreaFilled(false);
        FiguraResultadoModoFalha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiguraResultadoModoFalhaActionPerformed(evt);
            }
        });
        Resultado.add(FiguraResultadoModoFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 335, 245));

        jLabel74.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel74.setText("(N)");
        Resultado.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 60, 20));

        jLabel85.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel85.setText("Rk:");
        Resultado.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, 20));

        jLabel86.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel86.setText("(N)");
        Resultado.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 60, 20));

        jLabel89.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel89.setText("Rd:");
        Resultado.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, 20));

        jLabel96.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel96.setText("(N)");
        Resultado.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 60, 20));

        ResultadoRk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ResultadoRk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Resultado.add(ResultadoRk, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 80, 20));

        ResultadoRd.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ResultadoRd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Resultado.add(ResultadoRd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 80, 20));

        jLabel98.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel98.setText("*Fv,Rk = resistência característica de uma seção de corte por prego.");
        Resultado.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 450, -1));

        jLabel99.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel99.setText("*Rk = resistência característica da ligação considerando as quantidades de seções de corte e pregos. ");
        Resultado.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 670, -1));

        jLabel100.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel100.setText("*Rd = resistência de cálculo da ligação.");
        Resultado.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 670, -1));

        Next3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensBotao/Next.png"))); // NOI18N
        Next3.setToolTipText("Clique para avançar.");
        Next3.setEnabled(false);
        Next3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Next3ActionPerformed(evt);
            }
        });
        Resultado.add(Next3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 480, 40, -1));

        jTabbedPane1.addTab("Resultado", Resultado);

        Relatorio.setBackground(new java.awt.Color(204, 204, 204));
        Relatorio.setEnabled(false);

        RelatorioFinal.setBackground(new java.awt.Color(255, 255, 255));
        RelatorioFinal.setPreferredSize(new java.awt.Dimension(760, 1000));
        RelatorioFinal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("ELEMENTOS DE MADEIRA");
        RelatorioFinal.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setText("RELATÓRIO DE RESISTÊNCIA DA LIGAÇÃO ");
        RelatorioFinal.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 39, -1, -1));

        RelatorioSecaoCorte.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioSecaoCorte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RelatorioFinal.add(RelatorioSecaoCorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 80, 190, 14));

        RelatorioAngulacao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioAngulacao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RelatorioFinal.add(RelatorioAngulacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 100, 180, 20));
        RelatorioFinal.add(Relatoriofcok4, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 658, -1, -1));

        RelatorioElem1.setBackground(new java.awt.Color(255, 255, 255));
        RelatorioElem1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RelatorioElem1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Elemento 1:");
        RelatorioElem1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, 80, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("fc0,k:");
        RelatorioElem1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, -1, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel16.setText("fv0,k:");
        RelatorioElem1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel18.setText("Ec0,m:");
        RelatorioElem1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, -1, -1));

        jLabel19.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel19.setText("Densidade:");
        RelatorioElem1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 70, -1));

        RelatorioDensidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioDensidade1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioDensidade1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(RelatorioDensidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 85, 35, 14));

        RelatorioEc0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioEc0m1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioEc0m1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(RelatorioEc0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 65, 50, 14));

        Relatoriofv0k1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriofv0k1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Relatoriofv0k1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(Relatoriofv0k1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 45, 35, 14));

        Relatoriofcok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriofcok1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Relatoriofcok1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(Relatoriofcok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 25, 35, 14));

        jLabel20.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel20.setText("(MPa)");
        RelatorioElem1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 25, 40, -1));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel21.setText("(MPa)");
        RelatorioElem1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 45, 40, -1));

        jLabel22.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel22.setText("(MPa)");
        RelatorioElem1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 65, 40, -1));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel23.setText("(kg/m³)");
        RelatorioElem1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 85, 40, -1));
        RelatorioElem1.add(RelatorioClasseElem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 5, 40, 14));

        jLabel51.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel51.setText("Espessura:");
        RelatorioElem1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, -1, -1));

        RelatorioEspessura1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioEspessura1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(RelatorioEspessura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 105, 35, 14));

        jLabel69.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel69.setText("(mm)");
        RelatorioElem1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 105, 40, -1));

        Inclinado1.setBorder(null);
        Inclinado1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Inclinado1.setToolTipText("");
        Inclinado1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        Inclinado1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("Força paralela às fibras deste\nelemento.");
        Inclinado1.setViewportView(jTextArea2);

        RelatorioElem1.add(Inclinado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 122, 170, 30));

        RelatorioFinal.add(RelatorioElem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 175, 180, 155));

        RelatorioElem2.setBackground(new java.awt.Color(255, 255, 255));
        RelatorioElem2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RelatorioElem2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel30.setText("(MPa)");
        RelatorioElem2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 45, -1, -1));

        jLabel29.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel29.setText("(MPa)");
        RelatorioElem2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 25, -1, -1));

        jLabel32.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel32.setText("(kg/m³)");
        RelatorioElem2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 85, -1, -1));

        jLabel31.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel31.setText("(MPa)");
        RelatorioElem2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 65, -1, -1));

        RelatorioEc0m2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioEc0m2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioElem2.add(RelatorioEc0m2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 65, 50, 14));

        RelatorioDensidade2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioDensidade2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioElem2.add(RelatorioDensidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 85, 35, 14));

        Relatoriofcok2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriofcok2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioElem2.add(Relatoriofcok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 25, 35, 14));

        Relatoriofv0k2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriofv0k2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioElem2.add(Relatoriofv0k2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 45, 35, 14));

        jLabel27.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel27.setText("Ec0,m:");
        RelatorioElem2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, -1, -1));

        jLabel26.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel26.setText("fv0,k:");
        RelatorioElem2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, -1, -1));

        jLabel28.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel28.setText("Densidade:");
        RelatorioElem2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 70, -1));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setText("Elemento 2:");
        RelatorioElem2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));

        jLabel25.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel25.setText("fc0,k:");
        RelatorioElem2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, -1, -1));
        RelatorioElem2.add(RelatorioClasseElem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 5, 40, 14));

        RelatorioEspessura2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioEspessura2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioElem2.add(RelatorioEspessura2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 105, 35, 14));

        jLabel52.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel52.setText("Espessura:");
        RelatorioElem2.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, -1, -1));

        jLabel70.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel70.setText("(mm)");
        RelatorioElem2.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 105, -1, -1));

        Inclinado2.setBorder(null);
        Inclinado2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Inclinado2.setToolTipText("");
        Inclinado2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        Inclinado2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Força paralela às fibras deste\nelemento.");
        Inclinado2.setViewportView(jTextArea1);

        RelatorioElem2.add(Inclinado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 122, 170, 30));

        RelatorioFinal.add(RelatorioElem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 175, 180, 155));

        RelatorioCoeficientes.setBackground(new java.awt.Color(255, 255, 255));
        RelatorioCoeficientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RelatorioCoeficientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel88.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel88.setText("(°)");
        RelatorioCoeficientes.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, 20));

        Relatoriokmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriokmod3.setText("Densidade:");
        RelatorioCoeficientes.add(Relatoriokmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 75, 35, 14));

        RelatorioAngulo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioAngulo.setText("De");
        RelatorioCoeficientes.add(RelatorioAngulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 25, 20));

        RelatorioKmod1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioKmod1.setText("jLabel20");
        RelatorioCoeficientes.add(RelatorioKmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 25, 35, 14));

        Relatoriokmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriokmod2.setText("Densidade:");
        RelatorioCoeficientes.add(Relatoriokmod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 35, 14));

        jLabel90.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel90.setText("kmod 3:");
        RelatorioCoeficientes.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 75, 50, -1));

        jLabel91.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel91.setText("kmod 2:");
        RelatorioCoeficientes.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 50, 50, -1));

        jLabel92.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel92.setText("Ângulo:");
        RelatorioCoeficientes.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 100, 50, 20));

        jLabel93.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel93.setText("Coeficientes");
        RelatorioCoeficientes.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));

        jLabel94.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel94.setText("kmod 1:");
        RelatorioCoeficientes.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 25, 50, -1));

        jLabel35.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel35.setText("γm,ligação:");
        RelatorioCoeficientes.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 125, -1, -1));

        jLabel81.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel81.setText("1,4");
        RelatorioCoeficientes.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 125, -1, -1));

        RelatorioFinal.add(RelatorioCoeficientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 175, 180, 150));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("ELEMENTOS METÁLICOS");
        RelatorioFinal.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 333, -1, -1));

        RelatorioParafuso.setBackground(new java.awt.Color(255, 255, 255));
        RelatorioParafuso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RelatorioParafuso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel33.setText("Prego Liso com Cabeça");
        RelatorioParafuso.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));

        jLabel34.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel34.setText("Dimensões do Prego:");
        RelatorioParafuso.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, 14));

        RelatorioTipoParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioTipoParafuso.setText("jLabel35");
        RelatorioParafuso.add(RelatorioTipoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 230, -1));

        jLabel36.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel36.setText("d:");
        RelatorioParafuso.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 20, 14));

        RelatorioDiametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioDiametro.setText("jLabel37");
        RelatorioParafuso.add(RelatorioDiametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 52, 35, 14));

        jLabel37.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel37.setText("Número de Pregos:");
        RelatorioParafuso.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 100, -1));

        RelatorioNParafusos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioParafuso.add(RelatorioNParafusos, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 45, 25, 20));

        jLabel43.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel43.setText("Classe de Aço:");
        RelatorioParafuso.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 80, -1));

        RelatorioClasseAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioClasseAco.setText("jLabel44");
        RelatorioParafuso.add(RelatorioClasseAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 250, -1));

        jLabel38.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel38.setText("Consideração da Força de Arrancamento:");
        RelatorioParafuso.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 110, 210, -1));

        Relatoriofaxrk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriofaxrk.setText("jLabel44");
        RelatorioParafuso.add(Relatoriofaxrk, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 110, 35, 14));

        jLabel45.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel45.setText("fy,k:");
        RelatorioParafuso.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 30, 14));

        Relatoriofyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriofyk.setText("jLabel46");
        RelatorioParafuso.add(Relatoriofyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 90, 35, 14));

        jLabel46.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel46.setText("fu,k:");
        RelatorioParafuso.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 30, 14));

        Relatoriofuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriofuk.setText("jLabel47");
        RelatorioParafuso.add(Relatoriofuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 90, 35, 14));

        FiguraParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        FiguraParafuso.setText("jLabel35");
        RelatorioParafuso.add(FiguraParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 110, 30));

        jLabel71.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel71.setText("(mm)");
        RelatorioParafuso.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 50, 40, 14));

        jLabel72.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel72.setText("(MPa)");
        RelatorioParafuso.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 90, 40, -1));

        jLabel73.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel73.setText("(MPa)");
        RelatorioParafuso.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 40, 14));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        RelatorioParafuso.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 5, 14));

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        RelatorioParafuso.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 10, 14));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel7.setText("c:");
        RelatorioParafuso.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 20, -1));

        RelatorioComprimento.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioComprimento.setText("jLabel8");
        RelatorioParafuso.add(RelatorioComprimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 50, -1, 10));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel9.setText("(mm)");
        RelatorioParafuso.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 48, 30, -1));

        RelatorioFinal.add(RelatorioParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 355, 600, 135));
        RelatorioFinal.add(RelatorioFibras, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 313, -1, -1));

        jLabel44.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel44.setText("VALORES   CALCULADOS");
        RelatorioFinal.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 508, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel53.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel53.setText("Cálculos Preliminares");
        jPanel2.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, -1, -1));

        CalculoForca01.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        CalculoForca01.setText("fe0,k1:");
        jPanel2.add(CalculoForca01, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 35, 40, -1));

        CalculoForca02.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        CalculoForca02.setText("fe0,k2:");
        jPanel2.add(CalculoForca02, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 60, 40, -1));

        jLabel58.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel58.setText("Fax,rk:");
        jPanel2.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 85, 40, -1));

        RelatorioFc0d1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioFc0d1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioFc0d1.setText("jLabel59");
        jPanel2.add(RelatorioFc0d1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 35, 40, -1));

        RelatorioFc0d2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioFc0d2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioFc0d2.setText("jLabel59");
        jPanel2.add(RelatorioFc0d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 40, -1));

        RelatorioFaxrk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioFaxrk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioFaxrk.setText("jLabel59");
        jPanel2.add(RelatorioFaxrk, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 85, 50, -1));

        jLabel77.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel77.setText("(N)");
        jPanel2.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 85, 30, -1));

        jLabel78.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel78.setText("(MPa)");
        jPanel2.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 35, 40, -1));

        jLabel79.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel79.setText("(MPa)");
        jPanel2.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 40, -1));

        CalculoForcaAlfa1.setText("feα,k1:");
        jPanel2.add(CalculoForcaAlfa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 35, 40, -1));

        CalculoForca901.setText("fe90,k1:");
        jPanel2.add(CalculoForca901, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 35, 50, -1));

        CalculoForcaAlfa2.setText("feα,k2:");
        jPanel2.add(CalculoForcaAlfa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 60, 40, -1));

        CalculoForca902.setText("fe90,k2:");
        jPanel2.add(CalculoForca902, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 60, 50, -1));

        RelatorioFinal.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 160, 110));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel59.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel59.setText("Cálculo do Eurocode 5");
        jPanel3.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        RelatorioRd11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd11.setText("Fv,k1:");
        jPanel3.add(RelatorioRd11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 40, -1));

        RelatorioRd12.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd12.setText("Fv,k2:");
        jPanel3.add(RelatorioRd12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 40, -1));

        RelatorioRd13.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd13.setText("Fv,k3:");
        jPanel3.add(RelatorioRd13, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 60, 40, -1));

        RelatorioRd1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioRd1.setText("jLabel63");
        jPanel3.add(RelatorioRd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 50, -1));

        RelatorioRd14.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd14.setText("Fv,k4:");
        jPanel3.add(RelatorioRd14, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 80, 40, -1));

        RelatorioRd2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioRd2.setText("jLabel64");
        jPanel3.add(RelatorioRd2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 50, -1));

        RelatorioRd4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioRd4.setText("jLabel64");
        jPanel3.add(RelatorioRd4, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 80, 50, -1));

        RelatorioRd3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioRd3.setText("jLabel63");
        jPanel3.add(RelatorioRd3, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 60, 50, -1));

        RelatorioRd15.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd15.setText("Fv,k5:");
        jPanel3.add(RelatorioRd15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 40, -1));

        RelatorioRd16.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd16.setText("Fv,k6:");
        jPanel3.add(RelatorioRd16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 40, -1));

        RelatorioRd6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioRd6.setText("jLabel64");
        jPanel3.add(RelatorioRd6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 50, -1));

        RelatorioRd5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioRd5.setText("jLabel63");
        jPanel3.add(RelatorioRd5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 50, -1));

        jLabel56.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel56.setText("Myk:");
        jPanel3.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 30, -1));

        RelatorioMyd.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioMyd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioMyd.setText("jLabel59");
        jPanel3.add(RelatorioMyd, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 30, 70, -1));

        jLabel57.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel57.setText("β:");
        jPanel3.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 30, 15, -1));

        RelatorioBeta.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioBeta.setText("jLabel59");
        jPanel3.add(RelatorioBeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 30, -1, -1));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 360, 10));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 350, 10));

        RelatorioRd23.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd23.setText("Fv,k3:");
        jPanel3.add(RelatorioRd23, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 40, -1));

        jLabel54.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel54.setText("(N)");
        jPanel3.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 80, 30, -1));

        jLabel55.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel55.setText("(N)");
        jPanel3.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 60, 30, -1));

        jLabel60.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel60.setText("(N)");
        jPanel3.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 30, -1));

        jLabel61.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel61.setText("(N)");
        jPanel3.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 80, -1, -1));

        jLabel62.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel62.setText("(N)");
        jPanel3.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 30, -1));

        jLabel63.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel63.setText("(N)");
        jPanel3.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 60, -1, -1));

        RelatorioRd24.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd24.setText("Fv,k4:");
        jPanel3.add(RelatorioRd24, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 40, -1));

        jLabel64.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel64.setText("(N.mm)");
        jPanel3.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 30, 40, -1));

        RelatorioFinal.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 530, 400, 110));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel67.setText("RESULTADO:");
        RelatorioFinal.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 655, -1, -1));

        ModoFalha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RelatorioFinal.add(ModoFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, 170, 120));

        jLabel87.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel87.setText("Tipo de ruptura:");
        RelatorioFinal.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 665, -1, -1));

        jLabel95.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel95.setText("Rk:");
        RelatorioFinal.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 740, 70, -1));

        RelatorioRk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioFinal.add(RelatorioRk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 740, 70, 15));
        RelatorioFinal.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 600, 10));
        RelatorioFinal.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 135, 600, 10));

        FiguraSecoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RelatorioFinal.add(FiguraSecoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 80, 70));
        RelatorioFinal.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 600, 10));

        Data.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Data.setText("jLabel35");
        RelatorioFinal.add(Data, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 870, 80, -1));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        Consideracao1.setEditable(false);
        Consideracao1.setColumns(20);
        Consideracao1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Consideracao1.setLineWrap(true);
        Consideracao1.setRows(5);
        Consideracao1.setText("*Fv,Rk = resistência característica de uma seção de corte por prego.\n*Rk = resistência característica da ligação considerando as quantidades de seções de corte e pregos.\n*Rd = resistência de cálculo da ligação.");
        Consideracao1.setWrapStyleWord(true);
        Consideracao1.setBorder(null);
        jScrollPane2.setViewportView(Consideracao1);

        RelatorioFinal.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 810, 570, 50));

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        teste.setEditable(false);
        teste.setColumns(20);
        teste.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        teste.setLineWrap(true);
        teste.setRows(5);
        teste.setWrapStyleWord(true);
        teste.setAutoscrolls(false);
        jScrollPane3.setViewportView(teste);

        RelatorioFinal.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 660, 310, 30));

        Hora.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Hora.setText("jLabel65");
        RelatorioFinal.add(Hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 870, -1, -1));
        RelatorioFinal.add(RLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, 80));

        jLabel66.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel66.setText("Fv,Rk:");
        RelatorioFinal.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 720, 50, -1));

        RelatorioFvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioFvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioFvk.setText("jLabel67");
        RelatorioFinal.add(RelatorioFvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 720, 70, -1));

        jLabel80.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel80.setText("Rd:");
        RelatorioFinal.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 760, 50, -1));

        RelatorioRd.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioRd.setText("jLabel81");
        RelatorioFinal.add(RelatorioRd, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 760, 70, -1));

        jLabel82.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel82.setText("(N)");
        RelatorioFinal.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 760, -1, -1));

        jLabel83.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel83.setText("(N)");
        RelatorioFinal.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 720, -1, -1));

        jLabel84.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel84.setText("(N)");
        RelatorioFinal.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 740, -1, -1));

        jScrollPane1.setViewportView(RelatorioFinal);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensBotao/Print.png"))); // NOI18N
        jButton1.setToolTipText("Clique para imprimir o relatório.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensBotao/New.png"))); // NOI18N
        jButton2.setToolTipText("Clique para realizar novo cálculo.");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensBotao/Return.png"))); // NOI18N
        jButton3.setToolTipText("Clique para retornar a tela inicial.");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RelatorioLayout = new javax.swing.GroupLayout(Relatorio);
        Relatorio.setLayout(RelatorioLayout);
        RelatorioLayout.setHorizontalGroup(
            RelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RelatorioLayout.createSequentialGroup()
                .addGroup(RelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(RelatorioLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        RelatorioLayout.setVerticalGroup(
            RelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RelatorioLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Relatório", Relatorio);

        jTabbedPane1.setSelectedComponent(Inicio);
        Relatorio.setVisible(false);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);

        jLabelStatusPrego.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelStatusPrego.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelStatusPrego.setText("Clique em \"Iniciar Cálculo\" para começar o dimensionamento.");
        jLabelStatusPrego.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
        jLabelStatusPrego.setPreferredSize(new java.awt.Dimension(34, 30));
        getContentPane().add(jLabelStatusPrego, java.awt.BorderLayout.PAGE_END);

        getAccessibleContext().setAccessibleName("ProgramaMarcos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCalcularActionPerformed
        // COMANDOS PARA AVISAR QUE FALTAM DADOS PARA REALIZAR A LIGAÇÃO
//        
//        
//        if (ButtonCalcular.hasFocus()) {
//            
//            Data.setText(objDataHora.MostraData());
//            Hora.setText(objDataHora.MostraHora());
//            
//            jLabelStatusPrego.setText("Clique em avançar para continuar.");
//            jTabbedPane1.setSelectedComponent(Resultado);
//            jTabbedPane1.setEnabledAt(4, true);
//            Next3.setEnabled(true);
//
//            //Entradas madeiras
////            double fc0k1 = Integer.parseInt(ValorFc01.getText());
////            int fv0k1 = Integer.parseInt(ValorFvok1.getText());
////            int ec0m1 = Integer.parseInt(ValorEc0m1.getText());
////            double t1 = Integer.parseInt(Espessura1.getText());
////            double fc0k2 = Integer.parseInt(ValorFc2.getText());
////            int fv0k2 = Integer.parseInt(ValorFvok2.getText());
////            int ec0m2 = Integer.parseInt(ValorEc0m2.getText());
////            double t2 = Integer.parseInt(Espessura2.getText());
////            double falfa1 = 0.0;
////            double falfa2 = 0.0;
////            
////            if (npar > 8) {
////                nparafusos = (8.0 + ((2.0 / 3.0) * (npar - 8.0)));
////            } else {
////                nparafusos = npar;
////            }
////
////            //Entradas coeficientes
////            double angulograu = Double.parseDouble(ValorAngulo.getText().replace(",", "."));
////            double angulo = ((angulograu * Math.PI) / 180);
//            
//            RelatorioKmod1.setText(modeloLigacao.getKmod1().getValor() + "");
//            Relatoriokmod2.setText(modeloLigacao.getKmod2().getValor() + "");
//            Relatoriokmod3.setText(modeloLigacao.getKmod3().getValor() + "");
//            RelatorioEspessura1.setText(modeloLigacao.getElementoLigação1().getEspessura() + "");
//            RelatorioEspessura2.setText(modeloLigacao.getElementoLigação2().getEspessura() + "");
//            RelatorioAngulo.setText(modeloLigacao.getAngulo().getValorGrau() + "");
//            
//            
//
//            // ELEMENTOS PARA VERIFICAR O QUE O PROGRAMA ESTÁ FAZENDO - RELACIONADO A OBTENÇÃO DE DADOS
//            System.out.println("Fc0k1=" + fc0k1);
//            System.out.println("Fv0k1=" + fv0k1);
//            System.out.println("Ec0m1=" + ec0m1);
//            System.out.println("t1=" + t1);
//            System.out.println("Fc0k2=" + fc0k2);
//            System.out.println("Fv0k2=" + fv0k2);
//            System.out.println("Ec0m2=" + ec0m2);
//            System.out.println("t2=" + t2);
//            
//            System.out.println("Ângulo=" + angulo);
//            //RelatorioFcok.setText(""+fc0k1);
//
////            //Entradas Parafusos
////            d = Double.parseDouble(ValorDiametro.getText().replace(",", "."));
////            c = Double.parseDouble(ValorComprimento.getText().replace(",", "."));
////            int fyk = Integer.parseInt(ValorFyk.getText());
////            int fuk = Integer.parseInt(ValorFuk.getText());
//////        int nparafusos = npar;;
////            beta = fc0k2 / fc0k1;
////            fe0d1 = fc0k1;
////            fe0d2 = fc0k2;
////            double fe90d1 = (0.25 * fe0d1 * alfa);
////            double fe90d2 = (0.25 * fe0d2 * alfa);
//
//            // ELEMENTOS PARA VERIFICAR O QUE O PROGRAMA ESTÁ FAZENDO - RELACIONADO A OBTENÇÃO DE DADOS
//            System.out.println("d=" + d);
//            System.out.println("c=" + c);
//            System.out.println("Numero Parafusos=" + nparafusos);
//            System.out.println("Fyk=" + fyk);
//            System.out.println("Fuk=" + fuk);
//            System.out.println("d1=" + d1);
//            System.out.println("d2=" + d2);
//            System.out.println("beta=" + beta);
//            System.out.println("Fe0d1=" + fe0d1);
//            System.out.println("Fe0d2=" + fe0d2);
//            System.out.println("Fe90d1=" + fe90d1);
//            System.out.println("Fe90d2=" + fe90d2);
//            System.out.println("alfa=" + alfa);
//            
//            String ImagemFalha = "";
//            String Tipo = "";
//
////            //INICIA-SE O CÁLCULO DA LIGAÇÃO PARA UMA SEÇÃO DE CORTE PARALELA
////            if ((modeloLigacao == ModeloLigacao.CORTE_SIMPLES || modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES) && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PARALELO) {
////                UmaSecaoCorteParaleloController resultado = new UmaSecaoCorteParaleloController();//Pegar os valores das outras classes
////                //Considerar ou não o Kmod1 de FaxRk
////                // jLabel86.setIcon();
////                if (m) {
////                    valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
////                    valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);
////                    
////                }
////                
////                if (valorFaxrk > 0) {
////                    valorFaxrkFinal = valorFaxrk;
////                    
////                }
////                
////                if (valorFaxrkPrego > 0) {
////                    valorFaxrkFinal = valorFaxrkPrego;
////                    
////                }
////
////                //double FaxRk = resultado.ValorFaxrk(d, fyd, d2, d1, fe0d1);
////                double Myd = resultado.ValorMyd(fuk, d);
////                rd1 = resultado.EmbMenorPeca(fe0d1, t1, d);
////                rd2 = resultado.EmbMaiorPeca(fe0d1, t2, d, beta);
////                rd3 = resultado.EmbDuasPecas(fe0d1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
////                rd4 = resultado.DeformPinoMaiorPeca(fe0d1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
////                rd5 = resultado.DeformPinoMenorPeca(fe0d1, t2, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
////                rd6 = resultado.DeformPinoNasDuas(beta, Myd, fe0d1, d, valorFaxrk, valorFaxrkPrego);
//                System.out.println("Abriu = 1 Seção de Corte Paralela!");
//                System.out.println("Faxrk =" + valorFaxrkFinal);
//                System.out.println("Myd =" + Myd);
//                System.out.println("Rd1 =" + rd1);
//                System.out.println("Rd2 =" + rd2);
//                System.out.println("Rd3 =" + rd3);
//                System.out.println("Rd4 =" + rd4);
//                System.out.println("Rd5 =" + rd5);
//                System.out.println("Rd6 =" + rd6);
//
//                //Verificar o menor valor de resistência
////                Rdmin = rd1;
//                Tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
//                ImagemFalha = "1.1P.png";
//                
////                if (rd2 < Rdmin || rd2 == Rdmin) {
////                    Rdmin = rd2;
//                    Tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
//                    ImagemFalha = "1.2P.png";
//                    
//                }
////                if (rd3 < Rdmin) {
////                    Rdmin = rd3;
//                    Tipo = "Embutimento do pino metálico nas duas peças, devido ao giro do pino metálico.";
//                    ImagemFalha = "1.3P.png";
//                }
////                if (rd4 < Rdmin) {
////                    Rdmin = rd4;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2.";
//                    ImagemFalha = "1.4P.png";
//                }
////                if (rd5 < Rdmin) {
////                    Rdmin = rd5;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 1.";
//                    ImagemFalha = "1.5P.png";
//                }
////                if (rd6 < Rdmin) {
////                    Rdmin = rd6;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos.";
//                    ImagemFalha = "1.6P.png";
//                }
//                System.out.println("Rdmin =" + Rdmin);
//                System.out.println("Tipo =" + Tipo);
//                LabelResultadoModeloFalha.setText(Tipo);
//
//                //Calcular o valor total da ligação
////                Rdlig = nparafusos * 1 * Rdmin;
////                Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
//
//                System.out.println("Rdlig =" + Rdlig);
//                
//                RelatorioFc0d1.setText(String.format("%.1f", fe0d1));
//                RelatorioFc0d2.setText(String.format("%.1f", fe0d2));
//                jScrollPane2.setVisible(true);
//                
//                Inclinado1.setVisible(false);
//                Inclinado2.setVisible(false);
//                
//                RelatorioRd13.setVisible(true);
//                RelatorioRd14.setVisible(true);
//                RelatorioRd3.setVisible(true);
//                RelatorioRd4.setVisible(true);
//                jLabel60.setVisible(true);
//                jLabel62.setVisible(true);
//                RelatorioRd15.setVisible(true);
//                RelatorioRd16.setVisible(true);
//                RelatorioRd23.setVisible(false);
//                RelatorioRd24.setVisible(false);
//
//                //PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO
//                RelatorioRd1.setText(String.format("%.0f", rd1));
//                RelatorioRd2.setText(String.format("%.0f", rd2));
//                RelatorioRd3.setText(String.format("%.0f", rd3));
//                RelatorioRd4.setText(String.format("%.0f", rd4));
//                RelatorioRd5.setText(String.format("%.0f", rd5));
//                RelatorioRd6.setText(String.format("%.0f", rd6));
//                RelatorioMyd.setText(String.format("%.0f", Myd));
//                RelatorioBeta.setText(String.format("%.3f", beta));
//                RelatorioFvk.setText(String.format("%.0f", Rdmin));
//                ResultadoFvk.setText(String.format("%.0f", Rdmin));
//                RelatorioRk.setText(String.format("%.0f", Rdlig));
//                ResultadoRk.setText(String.format("%.0f", Rdlig));
//                RelatorioFaxrk.setText(String.format("%.0f", valorFaxrkFinal));
//                RelatorioRd.setText(String.format("%.0f", Rvd));
//                ResultadoRd.setText(String.format("%.0f", Rvd));
//                teste.setText(Tipo);
//            }
//
////            //INICIA-SE O CÁLCULO DA LIGAÇÃO PARA UMA SEÇÃO DE CORTE INCLINADA
////            if ((modeloLigacao == ModeloLigacao.CORTE_SIMPLES || modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES) && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
////                UmaSecaoCorteInclinadaController resultado = new UmaSecaoCorteInclinadaController();//Pegar os valores das outras classes
////                //Considerar ou não o Kmod1 de FaxRk
////                if (m) {
////                    valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
////                    valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);
////                    
////                }
////                
////                if (valorFaxrk > 0) {
////                    valorFaxrkFinal = valorFaxrk;
////                    
////                }
////                
////                if (valorFaxrkPrego > 0) {
////                    valorFaxrkFinal = valorFaxrkPrego;
////                    
////                }
////                //Valor utilizado de força
////                if (IncSim1) {
////                    falfa2 = ((fe0d2 * fe90d2) / ((fe0d2 * (Math.pow(Math.sin(angulo), 2))) + (fe90d2 * (Math.pow(Math.cos(angulo), 2)))));
////                    falfa1 = fe0d1;
//                    Inclinado2.setVisible(true);
//                    Inclinado1.setVisible(false);
////                }
////                if (IncSim2) {
////                    falfa1 = ((fe0d1 * fe90d1) / ((fe0d1 * (Math.pow(Math.sin(angulo), 2))) + (fe90d1 * (Math.pow(Math.cos(angulo), 2)))));
////                    falfa2 = fe0d2;
//                    Inclinado2.setVisible(false);
//                    Inclinado1.setVisible(true);
////                }
////                beta = falfa2 / falfa1;
////                //double FaxRk = resultado.ValorFaxrk(d, fyd, d2, d1, fe0d1);
////                double Myd = resultado.ValorMyd(fuk, d);
////                rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
////                rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
////                rd3 = resultado.EmbDuasPecas(falfa1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
////                rd4 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
////                rd5 = resultado.DeformPinoMenorPeca(falfa1, t2, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
////                rd6 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);
//                System.out.println("Abriu = 1 Seção de Corte Perpendicular!");
//                System.out.println("Faxrk =" + valorFaxrkFinal);
//                System.out.println("Myd =" + Myd);
//                System.out.println("Rd1 =" + rd1);
//                System.out.println("Rd2 =" + rd2);
//                System.out.println("Rd3 =" + rd3);
//                System.out.println("Rd4 =" + rd4);
//                System.out.println("Rd5 =" + rd5);
//                System.out.println("Rd6 =" + rd6);
//                System.out.println("Falfa1 =" + falfa1);
//                System.out.println("Falfa2 =" + falfa2);
////
////                //Verificar o menor Kmod1 de resistência
////                Rdmin = rd1;
//                Tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
//                ImagemFalha = "1.1P.png";
////                
////                if (rd2 < Rdmin || rd2 == Rdmin) {
////                    Rdmin = rd2;
//                    Tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
//                    ImagemFalha = "1.2P.png";
////                }
////                if (rd3 < Rdmin) {
////                    Rdmin = rd3;
//                    Tipo = "Embutimento do pino metálico nas duas peças, devido ao giro do pino metálico.";
//                    ImagemFalha = "1.3P.png";
////                }
////                if (rd4 < Rdmin) {
////                    Rdmin = rd4;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2.";
//                    ImagemFalha = "1.4P.png";
////                }
////                if (rd5 < Rdmin) {
////                    Rdmin = rd5;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 1.";
//                    ImagemFalha = "1.5P.png";
////                }
////                if (rd6 < Rdmin) {
////                    Rdmin = rd6;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos.";
//                    ImagemFalha = "1.6P.png";
////                }
//                System.out.println("Rdmin =" + Rdmin);
//                System.out.println("Tipo =" + Tipo);
//                LabelResultadoModeloFalha.setText(Tipo);
//                
//                RelatorioFc0d1.setText(String.format("%.1f", falfa1));
//                RelatorioFc0d2.setText(String.format("%.1f", falfa2));
//                
//                RelatorioRd1.setText(String.format("%.0f", rd1));
//                RelatorioRd2.setText(String.format("%.0f", rd2));
//                RelatorioRd3.setText(String.format("%.0f", rd3));
//                RelatorioRd4.setText(String.format("%.0f", rd4));
//                RelatorioRd5.setText(String.format("%.0f", rd5));
//                RelatorioRd6.setText(String.format("%.0f", rd6));
//
////                //Calcular o Kmod1 total da ligação
////                Rdlig = nparafusos * 1 * Rdmin;
////                Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
//
//                System.out.println("Rdlig =" + Rdlig);
//                
//                RelatorioRd13.setVisible(true);
//                RelatorioRd14.setVisible(true);
//                RelatorioRd3.setVisible(true);
//                RelatorioRd4.setVisible(true);
//                jLabel60.setVisible(true);
//                jLabel62.setVisible(true);
//                jScrollPane2.setVisible(true);
//                RelatorioRd15.setVisible(true);
//                RelatorioRd16.setVisible(true);
//                RelatorioRd23.setVisible(false);
//                RelatorioRd24.setVisible(false);
//
//                //PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO
//                RelatorioMyd.setText(String.format("%.0f", Myd));
//                RelatorioBeta.setText(String.format("%.3f", beta));
//                RelatorioFvk.setText(String.format("%.0f", Rdmin));
//                ResultadoFvk.setText(String.format("%.0f", Rdmin));
//                RelatorioRk.setText(String.format("%.0f", Rdlig));
//                ResultadoRk.setText(String.format("%.0f", Rdlig));
//                RelatorioFaxrk.setText(String.format("%.0f", valorFaxrkFinal));
//                RelatorioRd.setText(String.format("%.0f", Rvd));
//                ResultadoRd.setText(String.format("%.0f", Rvd));
//                teste.setText(Tipo);
//            }
//
////            //INICIA-SE O CÁLCULO DA LIGAÇÃO PARA UMA SEÇÃO DE CORTE PERPENDICULAR
////            if ((modeloLigacao == ModeloLigacao.CORTE_SIMPLES || modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES) && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
////                UmaSecaoCortePerpendicularController resultado = new UmaSecaoCortePerpendicularController();//Pegar os valores das outras classes
////                //Considerar ou não o Kmod1 de FaxRk
////                if (m) {
////                    valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
////                    valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);
////                    
////                }
////                
////                if (valorFaxrk > 0) {
////                    valorFaxrkFinal = valorFaxrk;
////                    
////                }
////                
////                if (valorFaxrkPrego > 0) {
////                    valorFaxrkFinal = valorFaxrkPrego;
////                    
////                }
////                
////                if (IncSim1) {
////                    falfa1 = fe0d1;
////                    falfa2 = fe90d2;
//                    Inclinado2.setVisible(true);
//                    Inclinado1.setVisible(false);
////                }
////                if (IncSim2) {
////                    falfa2 = fe0d2;
////                    falfa1 = fe90d1;
//                    Inclinado2.setVisible(false);
//                    Inclinado1.setVisible(true);
////                }
////                beta = falfa2 / falfa1;
////                //double FaxRk = resultado.ValorFaxrk(d, fyd, d2, d1, fe0d1);
////                double Myd = resultado.ValorMyd(fuk, d);
////                rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
////                rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
////                rd3 = resultado.EmbDuasPecas(falfa1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
////                rd4 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
////                rd5 = resultado.DeformPinoMenorPeca(falfa1, t2, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
////                rd6 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);
//                System.out.println("Abriu = 1 Seção de Corte Perpendicular!");
//                System.out.println("Faxrk =" + valorFaxrkFinal);
//                System.out.println("Myd =" + Myd);
//                System.out.println("Rd1 =" + rd1);
//                System.out.println("Rd2 =" + rd2);
//                System.out.println("Rd3 =" + rd3);
//                System.out.println("Rd4 =" + rd4);
//                System.out.println("Rd5 =" + rd5);
//                System.out.println("Rd6 =" + rd6);
//
////                //Verificar o menor Kmod1 de resistência
////                Rdmin = rd1;
//                Tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
//                ImagemFalha = "1.1P.png";
////                
////                if (rd2 < Rdmin || rd2 == Rdmin) {
////                    Rdmin = rd2;
//                    Tipo = "Embutimento do pino metálico no elemento 2 de madeira.";;
//                    ImagemFalha = "1.2P.png";
////                    
////                }
////                if (rd3 < Rdmin) {
////                    Rdmin = rd3;
//                    Tipo = "Embutimento do pino metálico nas duas peças, devido ao giro do pino metálico.";
//                    ImagemFalha = "1.3P.png";
////                }
////                if (rd4 < Rdmin) {
////                    Rdmin = rd4;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2.";
//                    ImagemFalha = "1.4P.png";
////                }
////                if (rd5 < Rdmin) {
////                    Rdmin = rd5;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 1.";
//                    ImagemFalha = "1.5P.png";
////                }
////                if (rd6 < Rdmin) {
////                    Rdmin = rd6;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos.";
//                    ImagemFalha = "1.6P.png";
////                }
//                System.out.println("Rdmin =" + Rdmin);
//                System.out.println("Tipo =" + Tipo);
//                LabelResultadoModeloFalha.setText(Tipo);
//                
//                RelatorioFc0d1.setText(String.format("%.1f", falfa1));
//                RelatorioFc0d2.setText(String.format("%.1f", falfa2));
//                
//                RelatorioRd1.setText(String.format("%.0f", rd1));
//                RelatorioRd2.setText(String.format("%.0f", rd2));
//                RelatorioRd3.setText(String.format("%.0f", rd3));
//                RelatorioRd4.setText(String.format("%.0f", rd4));
//                RelatorioRd5.setText(String.format("%.0f", rd5));
//                RelatorioRd6.setText(String.format("%.0f", rd6));
//
////                //Calcular o Kmod1 total da ligação
////                Rdlig = nparafusos * 1 * Rdmin;
//////gabri                Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
//
//                System.out.println("Rdlig =" + Rdlig);
//                
//                RelatorioRd13.setVisible(true);
//                RelatorioRd14.setVisible(true);
//                RelatorioRd3.setVisible(true);
//                RelatorioRd4.setVisible(true);
//                jLabel60.setVisible(true);
//                jLabel62.setVisible(true);
//                jScrollPane2.setVisible(true);
//                RelatorioRd15.setVisible(true);
//                RelatorioRd16.setVisible(true);
//                RelatorioRd23.setVisible(false);
//                RelatorioRd24.setVisible(false);
//
//                //PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO
//                RelatorioMyd.setText(String.format("%.0f", Myd));
//                RelatorioBeta.setText(String.format("%.3f", beta));
//                RelatorioFvk.setText(String.format("%.0f", Rdmin));
//                ResultadoFvk.setText(String.format("%.0f", Rdmin));
//                RelatorioRk.setText(String.format("%.0f", Rdlig));
//                ResultadoRk.setText(String.format("%.0f", Rdlig));
//                RelatorioFaxrk.setText(String.format("%.0f", valorFaxrkFinal));
//                RelatorioRd.setText(String.format("%.0f", Rvd));
//                ResultadoRd.setText(String.format("%.0f", Rvd));
//                teste.setText(Tipo);
//            }
//
////            //INICIA-SE O CÁLCULO DA LIGAÇÃO PARA DUAS SEÇÕES DE CORTE PARALELA
////            if (modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PARALELO) {
////                DuasSecoesCorteParaleloController resultado = new DuasSecoesCorteParaleloController(); //Pegar os valores das outras classes
////                //Considerar ou não o Kmod1 de FaxRk
////
////                if (m) {
////                    valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
////                    valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);
////                    
////                }
////                
////                if (valorFaxrk > 0) {
////                    valorFaxrkFinal = valorFaxrk;
////                    
////                }
////                
////                if (valorFaxrkPrego > 0) {
////                    valorFaxrkFinal = valorFaxrkPrego;
////                    
////                }
////
////                //double FaxRk = resultado.ValorFaxrk(d, fyk, d2, d1, fe0d1);
////                double Myd = resultado.ValorMyd(fuk, d);
////                rd1 = resultado.EmbMenorPeca(fe0d1, t1, d);
////                rd2 = resultado.EmbMaiorPeca(fe0d1, t2, d, beta);
////                rd3 = resultado.DeformPinoMaiorPeca(fe0d1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
////                rd4 = resultado.DeformPinoNasDuas(beta, Myd, fe0d1, d, valorFaxrk, valorFaxrkPrego);
//                System.out.println("Abriu = 2 Seções de corte Paralelas!");
//                System.out.println("Faxrk =" + valorFaxrkFinal);
//                System.out.println("Myd =" + Myd);
//                System.out.println("Rd1 =" + rd1);
//                System.out.println("Rd2 =" + rd2);
//                System.out.println("Rd3 =" + rd3);
//                System.out.println("Rd4 =" + rd4);
//
////                //Verificar o menor Kmod1 de resistência
////                Rdmin = rd1;
//                Tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
//                ImagemFalha = "2.1P.png";
////                
////                if (rd2 < Rdmin || rd2 == Rdmin) {
////                    Rdmin = rd2;
//                    Tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
//                    ImagemFalha = "2.2P.png";
////                }
////                if (rd3 < Rdmin) {
////                    Rdmin = rd3;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2.";
//                    ImagemFalha = "2.3P.png";
////                }
////                if (rd4 < Rdmin) {
////                    Rdmin = rd4;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos.";
//                    ImagemFalha = "2.4P.png";
////                }
//                System.out.println("Rdmin =" + Rdmin);
//                System.out.println("Tipo =" + Tipo);
//                LabelResultadoModeloFalha.setText(Tipo);
//                
//                RelatorioFc0d1.setText(String.format("%.1f", fe0d1));
//                RelatorioFc0d2.setText(String.format("%.1f", fe0d2));
//                
//                RelatorioRd1.setText(String.format("%.0f", rd1));
//                RelatorioRd2.setText(String.format("%.0f", rd2));
//                RelatorioRd5.setText(String.format("%.0f", rd3));
//                RelatorioRd6.setText(String.format("%.0f", rd4));
//
////                //Calcular o Kmod1 total da ligação
////                Rdlig = nparafusos * 2 * Rdmin;
////                Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
//
//                System.out.println("Rdlig =" + Rdlig);
//                
//                jScrollPane2.setVisible(true);
//                RelatorioRd13.setVisible(false);
//                RelatorioRd14.setVisible(false);
//                RelatorioRd3.setVisible(false);
//                RelatorioRd4.setVisible(false);
//                jLabel60.setVisible(false);
//                jLabel62.setVisible(false);
//                RelatorioRd15.setVisible(false);
//                RelatorioRd16.setVisible(false);
//                RelatorioRd23.setVisible(true);
//                RelatorioRd24.setVisible(true);
//                
//                Inclinado1.setVisible(false);
//                Inclinado2.setVisible(false);
//
//                //PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO
//                RelatorioMyd.setText(String.format("%.0f", Myd));
//                RelatorioBeta.setText(String.format("%.3f", beta));
//                RelatorioFvk.setText(String.format("%.0f", Rdmin));
//                ResultadoFvk.setText(String.format("%.0f", Rdmin));
//                RelatorioRk.setText(String.format("%.0f", Rdlig));
//                ResultadoRk.setText(String.format("%.0f", Rdlig));
//                RelatorioFaxrk.setText(String.format("%.0f", valorFaxrkFinal));
//                RelatorioRd.setText(String.format("%.0f", Rvd));
//                ResultadoRd.setText(String.format("%.0f", Rvd));
//                teste.setText(Tipo);
//            }
//
////            //INICIA-SE O CÁLCULO DA LIGAÇÃO PARA DUAS SEÇÕES DE CORTE INCLINADA
////            if (modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
////                DuasSecoesCorteInclinadaController resultado = new DuasSecoesCorteInclinadaController(); //Pegar os valores das outras classes
////                //Considerar ou não o Kmod1 de FaxRk
////
////                if (m) {
////                    valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
////                    valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);
////                    
////                }
////                
////                if (valorFaxrk > 0) {
////                    valorFaxrkFinal = valorFaxrk;
////                    
////                }
////                
////                if (valorFaxrkPrego > 0) {
////                    valorFaxrkFinal = valorFaxrkPrego;
////                    
////                }
//
//                //Valor utilizado de força
////                if (IncSim1) {
////                    falfa2 = ((fe0d2 * fe90d2) / ((fe0d2 * (Math.pow(Math.sin(angulo), 2))) + (fe90d2 * (Math.pow(Math.cos(angulo), 2)))));
////                    falfa1 = fe0d1;
//                    Inclinado2.setVisible(true);
//                    Inclinado1.setVisible(false);
//                }
////                if (IncSim2) {
////                    falfa1 = ((fe0d1 * fe90d1) / ((fe0d1 * (Math.pow(Math.sin(angulo), 2))) + (fe90d1 * (Math.pow(Math.cos(angulo), 2)))));
////                    falfa2 = fe0d2;
//                    Inclinado2.setVisible(false);
//                    Inclinado1.setVisible(true);
//                }
////                beta = falfa2 / falfa1;
////
////                //double FaxRk = resultado.ValorFaxrk(d, fyk, d2, d1, fe0d1);
////                double Myd = resultado.ValorMyd(fuk, d);
////                rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
////                rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
////                rd3 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
////                rd4 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);
//                System.out.println("Abriu = 2 Seções de corte Paralelas!");
//                System.out.println("Faxrk =" + valorFaxrkFinal);
//                System.out.println("Myd =" + Myd);
//                System.out.println("Rd1 =" + rd1);
//                System.out.println("Rd2 =" + rd2);
//                System.out.println("Rd3 =" + rd3);
//                System.out.println("Rd4 =" + rd4);
//                System.out.println("Falfa1 =" + falfa1);
//                System.out.println("Falfa2 =" + falfa2);
//                System.out.println("beta =" + beta);
//
//                //Verificar o menor Kmod1 de resistência
////                Rdmin = rd1;
//                Tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
//                ImagemFalha = "2.1P.png";
//                
////                if (rd2 < Rdmin || rd2 == Rdmin) {
////                    Rdmin = rd2;
//                    Tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
//                    ImagemFalha = "2.2P.png";
//                }
////                if (rd3 < Rdmin) {
////                    Rdmin = rd3;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2.";
//                    ImagemFalha = "2.3P.png";
//                }
////                if (rd4 < Rdmin) {
////                    Rdmin = rd4;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos.";
//                    ImagemFalha = "2.4P.png";
//                }
//                System.out.println("Rdmin =" + Rdmin);
//                System.out.println("Tipo =" + Tipo);
//                LabelResultadoModeloFalha.setText(Tipo);
//                
//                RelatorioFc0d1.setText(String.format("%.1f", falfa1));
//                RelatorioFc0d2.setText(String.format("%.1f", falfa2));
//                
//                RelatorioRd1.setText(String.format("%.0f", rd1));
//                RelatorioRd2.setText(String.format("%.0f", rd2));
//                RelatorioRd5.setText(String.format("%.0f", rd3));
//                RelatorioRd6.setText(String.format("%.0f", rd4));
//
//                //Calcular o Kmod1 total da ligação
////                Rdlig = nparafusos * 2 * Rdmin;
////                Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
//
//                System.out.println("Rdlig =" + Rdlig);
//                
//                jScrollPane2.setVisible(true);
//                RelatorioRd13.setVisible(false);
//                RelatorioRd14.setVisible(false);
//                RelatorioRd3.setVisible(false);
//                RelatorioRd4.setVisible(false);
//                jLabel60.setVisible(false);
//                jLabel62.setVisible(false);
//                RelatorioRd15.setVisible(false);
//                RelatorioRd16.setVisible(false);
//                RelatorioRd23.setVisible(true);
//                RelatorioRd24.setVisible(true);
//
//                //PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO
//                RelatorioMyd.setText(String.format("%.0f", Myd));
//                RelatorioBeta.setText(String.format("%.3f", beta));
//                RelatorioFvk.setText(String.format("%.0f", Rdmin));
//                ResultadoFvk.setText(String.format("%.0f", Rdmin));
//                RelatorioRk.setText(String.format("%.0f", Rdlig));
//                ResultadoRk.setText(String.format("%.0f", Rdlig));
//                RelatorioFaxrk.setText(String.format("%.0f", valorFaxrkFinal));
//                RelatorioRd.setText(String.format("%.0f", Rvd));
//                ResultadoRd.setText(String.format("%.0f", Rvd));
//                teste.setText(Tipo);
//            }
//
////            //INICIA-SE O CÁLCULO DA LIGAÇÃO PARA DUAS SEÇÕES DE CORTE PERPENDICULAR
////            if (modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
////                DuasSecoesCortePerpendicularController resultado = new DuasSecoesCortePerpendicularController(); //Pegar os valores das outras classes
////                //Considerar ou não o Kmod1 de FaxRk
////                if (m) {
////                    valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
////                    valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);
////                    
////                }
////                
////                if (valorFaxrk > 0) {
////                    valorFaxrkFinal = valorFaxrk;
////                    
////                }
////                
////                if (valorFaxrkPrego > 0) {
////                    valorFaxrkFinal = valorFaxrkPrego;
////                    
////                }
//                
////                if (IncSim1) {
////                    falfa1 = fe0d1;
////                    falfa2 = fe90d2;
//                    Inclinado2.setVisible(true);
//                    Inclinado1.setVisible(false);
//                }
////                if (IncSim2) {
////                    falfa2 = fe0d2;
////                    falfa1 = fe90d1;
//                    Inclinado2.setVisible(false);
//                    Inclinado1.setVisible(true);
//                }
////                beta = falfa2 / falfa1;
////                //double FaxRk = resultado.ValorFaxrk(d, fyk, d2, d1, fe0d1);
////                double Myd = resultado.ValorMyd(fuk, d);
////                rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
////                rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
////                rd3 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
////                rd4 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);
//                System.out.println("Abriu = 2 Seções de corte Perpendicular!");
//                System.out.println("Faxrk =" + valorFaxrkFinal);
//                System.out.println("Myd =" + Myd);
//                System.out.println("Rd1 =" + rd1);
//                System.out.println("Rd2 =" + rd2);
//                System.out.println("Rd3 =" + rd3);
//                System.out.println("Rd4 =" + rd4);
//
//                //Verificar o menor Kmod1 de resistência
////                Rdmin = rd1;
//                Tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
//                ImagemFalha = "2.1P.png";
//                
////                if (rd2 < Rdmin || rd2 == Rdmin) {
////                    Rdmin = rd2;
//                    Tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
//                    ImagemFalha = "2.2P.png";
////                }
////                if (rd3 < Rdmin) {
////                    Rdmin = rd3;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2.";
//                    ImagemFalha = "2.3P.png";
//                }
////                if (rd4 < Rdmin) {
////                    Rdmin = rd4;
//                    Tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos.";
//                    ImagemFalha = "2.4P.png";
//                }
//                System.out.println("Rdmin =" + Rdmin);
//                System.out.println("Tipo =" + Tipo);
//                LabelResultadoModeloFalha.setText(Tipo);
//                
//                RelatorioFc0d1.setText(String.format("%.1f", falfa1));
//                RelatorioFc0d2.setText(String.format("%.1f", falfa2));
//                
//                RelatorioRd1.setText(String.format("%.0f", rd1));
//                RelatorioRd2.setText(String.format("%.0f", rd2));
//                RelatorioRd5.setText(String.format("%.0f", rd3));
//                RelatorioRd6.setText(String.format("%.0f", rd4));
//
//                //Calcular o Kmod1 total da ligação
////                Rdlig = nparafusos * 2 * Rdmin;
////                Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
//
//                System.out.println("Rdlig =" + Rdlig);
//                
//                jScrollPane2.setVisible(true);
//                RelatorioRd13.setVisible(false);
//                RelatorioRd14.setVisible(false);
//                RelatorioRd3.setVisible(false);
//                RelatorioRd4.setVisible(false);
//                jLabel60.setVisible(false);
//                jLabel62.setVisible(false);
//                RelatorioRd15.setVisible(false);
//                RelatorioRd16.setVisible(false);
//                RelatorioRd23.setVisible(true);
//                RelatorioRd24.setVisible(true);
//
//                //PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO
//                RelatorioMyd.setText(String.format("%.0f", Myd));
//                RelatorioBeta.setText(String.format("%.3f", beta));
//                RelatorioFvk.setText(String.format("%.0f", Rdmin));
//                ResultadoFvk.setText(String.format("%.0f", Rdmin));
//                RelatorioRk.setText(String.format("%.0f", Rdlig));
//                ResultadoRk.setText(String.format("%.0f", Rdlig));
//                RelatorioFaxrk.setText(String.format("%.0f", valorFaxrkFinal));
//                RelatorioRd.setText(String.format("%.0f", Rvd));
//                ResultadoRd.setText(String.format("%.0f", Rvd));
//                teste.setText(Tipo);
//                //RelatorioRd13.
//            }
//
//            //COM ESSAS FUNÇÕES ALTERAM-SE AS IMAGENS DO RELATÓRIO
//            FiguraResultadoModoFalha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensFalhas/" + ImagemFalha))); // NOI18N
//            FiguraParafuso.setIcon(new ImageIcon(((ImageIcon) FiguraTipoParafuso.getIcon()).getImage().getScaledInstance(110, 25, Image.SCALE_SMOOTH)));
//            FiguraParafuso.setIcon(new ImageIcon(((ImageIcon) FiguraTipoParafuso.getIcon()).getImage().getScaledInstance(110, 25, Image.SCALE_SMOOTH)));
//            ModoFalha.setIcon(new ImageIcon(((ImageIcon) FiguraResultadoModoFalha.getIcon()).getImage().getScaledInstance(84, 120, Image.SCALE_SMOOTH)));
//            RLogo.setIcon(new ImageIcon(((ImageIcon) LogoPrograma.getIcon()).getImage().getScaledInstance(125, 80, Image.SCALE_SMOOTH)));
//        }

    }//GEN-LAST:event_ButtonCalcularActionPerformed

    private void TesteParafusoNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TesteParafusoNaoActionPerformed
        // TODO add your handling code here:
        Relatoriofaxrk.setText("Não");
    }//GEN-LAST:event_TesteParafusoNaoActionPerformed

    private void TesteParafusoSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TesteParafusoSimActionPerformed
        JOptionPane.showMessageDialog(this, "Será considerado no cálculo o efeito não linear de compressão provocado pela arruela devido a rotação\n do pino metálico e de tração do pino metálico, conhecido como efeito de corda (Fax,rk). ");
        // TODO add your handling code here:
        m = true;
        Relatoriofaxrk.setText("Sim");
    }//GEN-LAST:event_TesteParafusoSimActionPerformed

    private void ComboAcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboAcoActionPerformed
        // TODO add your handling code here:
        ValorFyk.setText(modeloLigacao.getConectores().getClasseAcoPrego().getFyk() + "");
        ValorFuk.setText(modeloLigacao.getConectores().getClasseAcoPrego().getFuk() + "");
        RelatorioClasseAco.setText(modeloLigacao.getConectores().getClasseAcoPrego().getNome() + "");
        Relatoriofyk.setText(modeloLigacao.getConectores().getClasseAcoPrego().getFyk() + "");
        Relatoriofuk.setText(modeloLigacao.getConectores().getClasseAcoPrego().getFuk() + "");
        atualizaButtonCalcular();
        
//        switch (ComboAco.getSelectedIndex()) {
//            case 0:
//                ValorFyk.setText("-");
//                ValorFuk.setText("-");
//                break;
//            case 1:
//                ValorFyk.setText("600");
//                ValorFuk.setText("600");
//                RelatorioClasseAco.setText("Aço de Baixo Teor de Carbono");
//                Relatoriofyk.setText("600");
//                Relatoriofuk.setText("600");
//                break;
//            
//        }
    }//GEN-LAST:event_ComboAcoActionPerformed

    private void ComboTipoPregoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTipoPregoActionPerformed
        // TODO add your handling code here:
        ValorDiametro.setText(modeloLigacao.getConectores().getTipoPrego().getDiametro() + "");
        ValorComprimento.setText(modeloLigacao.getConectores().getTipoPrego().getComprimento() + "");
        RelatorioTipoParafuso.setText(modeloLigacao.getConectores().getTipoPrego().getNome() + "");
        RelatorioDiametro.setText(modeloLigacao.getElementoLigação2().getClasseMadeira().getfc0k() + "");
        RelatorioComprimento.setText(modeloLigacao.getConectores().getTipoPrego().getComprimento() + "");

        atualizaButtonCalcular();
        
//        switch (ComboTipoPrego.getSelectedIndex()) {
//            case 0: //Escolha o tipo de Prego
//                ValorDiametro.setText("-");
//                ValorComprimento.setText("-");
//                d = 0.0;
//                break;
//            case 1:
//                ValorDiametro.setText("3,0");
//                ValorComprimento.setText("48,3");
//                RelatorioTipoParafuso.setText("17 x 21");
//                RelatorioDiametro.setText("3,0");
//                RelatorioComprimento.setText("48,3");
//                d = 3.0;
//                c = 48.3;
//                alfa = 2.5;
//                break;
//            case 2:
//                ValorDiametro.setText("3,0");
//                ValorComprimento.setText("55,2");
//                RelatorioTipoParafuso.setText("17 x 24");
//                RelatorioDiametro.setText("3,0");
//                RelatorioComprimento.setText("55,2");
//                d = 3.0;
//                c = 55.2;
//                alfa = 2.5;
//                break;
//            case 3:
//                ValorDiametro.setText("3,0");
//                ValorComprimento.setText("62,1");
//                RelatorioTipoParafuso.setText("17 x 27");
//                RelatorioDiametro.setText("3,0");
//                RelatorioComprimento.setText("62,1");
//                d = 3.0;
//                c = 62.1;
//                alfa = 2.5;
//                break;
//            case 4:
//                ValorDiametro.setText("3,0");
//                ValorComprimento.setText("69,0");
//                RelatorioTipoParafuso.setText("17 x 30");
//                RelatorioDiametro.setText("3,0");
//                RelatorioComprimento.setText("69,0");
//                d = 3.0;
//                c = 69.0;
//                alfa = 2.5;
//                break;
//            case 5:
//                ValorDiametro.setText("3,4");
//                ValorComprimento.setText("25,3");
//                RelatorioTipoParafuso.setText("18 x 11");
//                RelatorioDiametro.setText("3,4");
//                RelatorioComprimento.setText("25,3");
//                d = 3.4;
//                c = 25.3;
//                alfa = 2.5;
//                break;
//            case 6:
//                ValorDiametro.setText("3,4");
//                ValorComprimento.setText("48,3");
//                RelatorioTipoParafuso.setText("18 x 21");
//                RelatorioDiametro.setText("3,4");
//                RelatorioComprimento.setText("48,3");
//                d = 3.4;
//                c = 48.3;
//                alfa = 2.5;
//                break;
//            case 7:
//                ValorDiametro.setText("3,4");
//                ValorComprimento.setText("55,2");
//                RelatorioTipoParafuso.setText("18 x 24");
//                RelatorioDiametro.setText("3,4");
//                RelatorioComprimento.setText("55,2");
//                d = 3.4;
//                c = 55.2;
//                alfa = 2.5;
//                break;
//            case 8:
//                ValorDiametro.setText("3,4");
//                ValorComprimento.setText("62,1");
//                RelatorioTipoParafuso.setText("18 x 27");
//                RelatorioDiametro.setText("3,4");
//                RelatorioComprimento.setText("62,1");
//                d = 3.4;
//                c = 62.1;
//                alfa = 2.5;
//                break;
//            case 9:
//                ValorDiametro.setText("3,4");
//                ValorComprimento.setText("69,0");
//                RelatorioTipoParafuso.setText("18 x 30");
//                RelatorioDiametro.setText("3,4");
//                RelatorioComprimento.setText("69,0");
//                d = 3.4;
//                c = 69.0;
//                alfa = 2.5;
//                break;
//            case 10:
//                ValorDiametro.setText("3,4");
//                ValorComprimento.setText("75,9");
//                RelatorioTipoParafuso.setText("18 x 33");
//                RelatorioDiametro.setText("3,4");
//                RelatorioComprimento.setText("75,9");
//                d = 3.4;
//                c = 75.9;
//                alfa = 2.5;
//                break;
//            case 11:
//                ValorDiametro.setText("3,4");
//                ValorComprimento.setText("82,8");
//                RelatorioTipoParafuso.setText("18 x 36");
//                RelatorioDiametro.setText("3,4");
//                RelatorioComprimento.setText("82,8");
//                d = 3.4;
//                c = 82.8;
//                alfa = 2.5;
//                break;
//            case 12:
//                ValorDiametro.setText("3,9");
//                ValorComprimento.setText("34,5");
//                RelatorioTipoParafuso.setText("19 x 15");
//                RelatorioDiametro.setText("3,9");
//                RelatorioComprimento.setText("34,5");
//                d = 3.9;
//                c = 34.5;
//                alfa = 2.5;
//                break;
//            case 13:
//                ValorDiametro.setText("3,9");
//                ValorComprimento.setText("48,3");
//                RelatorioTipoParafuso.setText("19 x 21");
//                RelatorioDiametro.setText("36,0");
//                RelatorioComprimento.setText("48,3");
//                d = 3.9;
//                c = 48.3;
//                alfa = 2.5;
//                break;
//            case 14:
//                ValorDiametro.setText("3,9");
//                ValorComprimento.setText("62,1");
//                RelatorioTipoParafuso.setText("19 x 27");
//                RelatorioDiametro.setText("3,9");
//                RelatorioComprimento.setText("62,1");
//                d = 3.9;
//                c = 62.1;
//                alfa = 2.5;
//                break;
//            case 15:
//                ValorDiametro.setText("3,9");
//                ValorComprimento.setText("69,0");
//                RelatorioTipoParafuso.setText("19 x 30");
//                RelatorioDiametro.setText("3,9");
//                RelatorioComprimento.setText("69,0");
//                d = 3.9;
//                c = 69.0;
//                alfa = 2.5;
//                break;
//            case 16:
//                ValorDiametro.setText("3,9");
//                ValorComprimento.setText("75,9");
//                RelatorioTipoParafuso.setText("19 x 33");
//                RelatorioDiametro.setText("3,9");
//                RelatorioComprimento.setText("75,9");
//                d = 3.9;
//                c = 75.9;
//                alfa = 2.5;
//                break;
//            case 17:
//                ValorDiametro.setText("3,9");
//                ValorComprimento.setText("82,8");
//                RelatorioTipoParafuso.setText("19 x 36");
//                RelatorioDiametro.setText("3,9");
//                RelatorioComprimento.setText("82,8");
//                d = 3.9;
//                c = 82.8;
//                alfa = 2.5;
//                break;
//            case 18:
//                ValorDiametro.setText("3,9");
//                ValorComprimento.setText("89,7");
//                RelatorioTipoParafuso.setText("19 x 39");
//                RelatorioDiametro.setText("3,9");
//                RelatorioComprimento.setText("89,7");
//                d = 3.9;
//                c = 89.7;
//                alfa = 2.5;
//                break;
//            case 19:
//                ValorDiametro.setText("3,9");
//                ValorComprimento.setText("96,6");
//                RelatorioTipoParafuso.setText("19 x 42");
//                RelatorioDiametro.setText("3,9");
//                RelatorioComprimento.setText("96,6");
//                d = 3.9;
//                c = 96.6;
//                alfa = 2.5;
//                break;
//            case 20:
//                ValorDiametro.setText("4,4");
//                ValorComprimento.setText("69,0");
//                RelatorioTipoParafuso.setText("20 x 30");
//                RelatorioDiametro.setText("4,4");
//                RelatorioComprimento.setText("69,0");
//                d = 4.4;
//                c = 69.0;
//                alfa = 2.5;
//                break;
//            case 21:
//                ValorDiametro.setText("4,4");
//                ValorComprimento.setText("75,9");
//                RelatorioTipoParafuso.setText("20 x 33");
//                RelatorioDiametro.setText("4,4");
//                RelatorioComprimento.setText("75,9");
//                d = 4.4;
//                c = 75.9;
//                alfa = 2.5;
//                break;
//            case 22:
//                ValorDiametro.setText("4,4");
//                ValorComprimento.setText("82,8");
//                RelatorioTipoParafuso.setText("20 x 36");
//                RelatorioDiametro.setText("4,4");
//                RelatorioComprimento.setText("82,8");
//                d = 4.4;
//                c = 82.8;
//                alfa = 2.5;
//                break;
//            case 23:
//                ValorDiametro.setText("4,4");
//                ValorComprimento.setText("89,7");
//                RelatorioTipoParafuso.setText("20 x 39");
//                RelatorioDiametro.setText("4,4");
//                RelatorioComprimento.setText("89,7");
//                d = 4.4;
//                c = 89.7;
//                alfa = 2.5;
//                break;
//            case 24:
//                ValorDiametro.setText("4,4");
//                ValorComprimento.setText("96,6");
//                RelatorioTipoParafuso.setText("20 x 42");
//                RelatorioDiametro.setText("4,4");
//                RelatorioComprimento.setText("96,6");
//                d = 4.4;
//                c = 96.6;
//                alfa = 2.5;
//                break;
//            case 25:
//                ValorDiametro.setText("4,4");
//                ValorComprimento.setText("110,4");
//                RelatorioTipoParafuso.setText("20 x 48");
//                RelatorioDiametro.setText("4,4");
//                RelatorioComprimento.setText("110,4");
//                d = 4.4;
//                c = 110.4;
//                alfa = 2.5;
//                break;
//            case 26:
//                ValorDiametro.setText("4,9");
//                ValorComprimento.setText("75,9");
//                RelatorioTipoParafuso.setText("21 x 33");
//                RelatorioDiametro.setText("4,9");
//                RelatorioComprimento.setText("75,9");
//                d = 4.9;
//                c = 75.9;
//                alfa = 2.5;
//                break;
//            case 27:
//                ValorDiametro.setText("4,9");
//                ValorComprimento.setText("82,8");
//                RelatorioTipoParafuso.setText("21 x 36");
//                RelatorioDiametro.setText("4,9");
//                RelatorioComprimento.setText("82,8");
//                d = 4.9;
//                c = 82.8;
//                alfa = 2.5;
//                break;
//            case 28:
//                ValorDiametro.setText("4,9");
//                ValorComprimento.setText("96,6");
//                RelatorioTipoParafuso.setText("21 x 42");
//                RelatorioDiametro.setText("4,9");
//                RelatorioComprimento.setText("96,6");
//                d = 4.9;
//                c = 96.6;
//                alfa = 2.5;
//                break;
//            case 29:
//                ValorDiametro.setText("4,9");
//                ValorComprimento.setText("103,5");
//                RelatorioTipoParafuso.setText("21 x 45");
//                RelatorioDiametro.setText("4,9");
//                RelatorioComprimento.setText("103,5");
//                d = 4.9;
//                c = 103.5;
//                alfa = 2.5;
//                break;
//            case 30:
//                ValorDiametro.setText("4,9");
//                ValorComprimento.setText("110,4");
//                RelatorioTipoParafuso.setText("21 x 48");
//                RelatorioDiametro.setText("4,9");
//                RelatorioComprimento.setText("110,4");
//                d = 4.9;
//                c = 110.4;
//                alfa = 2.5;
//                break;
//            case 31:
//                ValorDiametro.setText("4,9");
//                ValorComprimento.setText("124,2");
//                RelatorioTipoParafuso.setText("21 x 54");
//                RelatorioDiametro.setText("4,9");
//                RelatorioComprimento.setText("124,2");
//                d = 4.9;
//                c = 124.2;
//                alfa = 2.5;
//                break;
//            case 32:
//                ValorDiametro.setText("5,4");
//                ValorComprimento.setText("96,6");
//                RelatorioTipoParafuso.setText("22 x 42");
//                RelatorioDiametro.setText("5,4");
//                RelatorioComprimento.setText("96,6");
//                d = 5.4;
//                c = 96.6;
//                alfa = 2.5;
//                break;
//            case 33:
//                ValorDiametro.setText("5,4");
//                ValorComprimento.setText("103,5");
//                RelatorioTipoParafuso.setText("22 x 45");
//                RelatorioDiametro.setText("5,4");
//                RelatorioComprimento.setText("103,5");
//                d = 5.4;
//                c = 103.5;
//                alfa = 2.5;
//                break;
//            case 34:
//                ValorDiametro.setText("5,4");
//                ValorComprimento.setText("110,4");
//                RelatorioTipoParafuso.setText("22 x 48");
//                RelatorioDiametro.setText("5,4");
//                RelatorioComprimento.setText("110,4");
//                d = 5.4;
//                c = 110.4;
//                alfa = 2.5;
//                break;
//            case 35:
//                ValorDiametro.setText("5,4");
//                ValorComprimento.setText("124,2");
//                RelatorioTipoParafuso.setText("22 x 54");
//                RelatorioDiametro.setText("5,4");
//                RelatorioComprimento.setText("124,2");
//                d = 5.4;
//                c = 124.2;
//                alfa = 2.5;
//                break;
//            case 36:
//                ValorDiametro.setText("5,9");
//                ValorComprimento.setText("103,5");
//                RelatorioTipoParafuso.setText("23 x 45");
//                RelatorioDiametro.setText("5,9");
//                RelatorioComprimento.setText("103,5");
//                d = 5.9;
//                c = 103.5;
//                alfa = 2.5;
//                break;
//            case 37:
//                ValorDiametro.setText("5,9");
//                ValorComprimento.setText("124,2");
//                RelatorioTipoParafuso.setText("23 x 54");
//                RelatorioDiametro.setText("5,9");
//                RelatorioComprimento.setText("124,2");
//                d = 5.9;
//                c = 124.2;
//                alfa = 2.5;
//                break;
//            case 38:
//                ValorDiametro.setText("5,9");
//                ValorComprimento.setText("138,0");
//                RelatorioTipoParafuso.setText("23 x 60");
//                RelatorioDiametro.setText("5,9");
//                RelatorioComprimento.setText("138,0");
//                d = 5.9;
//                c = 138.0;
//                alfa = 2.5;
//                break;
//            case 39:
//                ValorDiametro.setText("5,9");
//                ValorComprimento.setText("151,8");
//                RelatorioTipoParafuso.setText("23 x 66");
//                RelatorioDiametro.setText("5,9");
//                RelatorioComprimento.setText("151,8");
//                d = 5.9;
//                c = 151.8;
//                alfa = 2.5;
//                break;
//            case 40:
//                ValorDiametro.setText("6,4");
//                ValorComprimento.setText("138,0");
//                RelatorioTipoParafuso.setText("24 x 60");
//                RelatorioDiametro.setText("6,4");
//                RelatorioComprimento.setText("138,0");
//                d = 6.4;
//                c = 138.0;
//                alfa = 2.5;
//                break;
//            case 41:
//                ValorDiametro.setText("6,4");
//                ValorComprimento.setText("151,8");
//                RelatorioTipoParafuso.setText("24 x 66");
//                RelatorioDiametro.setText("6,4");
//                RelatorioComprimento.setText("151,8");
//                d = 6.4;
//                c = 151.8;
//                alfa = 2.5;
//                break;
//            case 42:
//                ValorDiametro.setText("7,0");
//                ValorComprimento.setText("165,6");
//                RelatorioTipoParafuso.setText("25 x 72");
//                RelatorioDiametro.setText("7,0");
//                RelatorioComprimento.setText("165,6");
//                d = 7.0;
//                c = 165.6;
//                alfa = 2.39;
//                break;
//            case 43:
//                ValorDiametro.setText("7,6");
//                ValorComprimento.setText("165,6");
//                RelatorioTipoParafuso.setText("26 x 72");
//                RelatorioDiametro.setText("7,6");
//                RelatorioComprimento.setText("165,6");
//                d = 7.6;
//                c = 165.6;
//                alfa = 2.29;
//                break;
//            case 44:
//                ValorDiametro.setText("7,6");
//                ValorComprimento.setText("179,4");
//                RelatorioTipoParafuso.setText("26 x 78");
//                RelatorioDiametro.setText("7,6");
//                RelatorioComprimento.setText("179,4");
//                d = 7.6;
//                c = 179.4;
//                alfa = 2.29;
//                break;
//            case 45:
//                ValorDiametro.setText("7,6");
//                ValorComprimento.setText("193,2");
//                RelatorioTipoParafuso.setText("26 x 84");
//                RelatorioDiametro.setText("7,6");
//                RelatorioComprimento.setText("193,2");
//                d = 7.6;
//                c = 193.2;
//                alfa = 2.29;
//                break;
//            
//        }
    }//GEN-LAST:event_ComboTipoPregoActionPerformed

    private void LimparExpessura2(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LimparExpessura2
        // TODO add your handling code here:
        if (Espessura2.getText().length() > 4) {
            Espessura2.setText("");
        }
    }//GEN-LAST:event_LimparExpessura2

    private void ComboElem2ClasseMadeiraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ComboElem2ClasseMadeiraPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboElem2ClasseMadeiraPropertyChange

    private void ComboElem2ClasseMadeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboElem2ClasseMadeiraActionPerformed
        // TODO add your handling code here:
//        switch (ComboElem2ClasseMadeira.getSelectedIndex()) {
//            case 0:
//                modeloLigacao.getElementoLigação2().setClasseMadeira(null);
//                ValorFc01.setText("-");
//                ValorDensidade1.setText("-");
//                ValorFvok1.setText("-");
//                ValorEc0m1.setText("-");
//                break;
//            default:
                
        ValorFc2.setText(modeloLigacao.getElementoLigação2().getClasseMadeira().getfc0k() + "");
        ValorDensidade2.setText(modeloLigacao.getElementoLigação2().getClasseMadeira().getDensidade() + "");
        ValorFvok2.setText(modeloLigacao.getElementoLigação2().getClasseMadeira().getfv0k() + "");
        ValorEc0m2.setText(modeloLigacao.getElementoLigação2().getClasseMadeira().getec0m() + "");
        RelatorioClasseElem2.setText(modeloLigacao.getElementoLigação2().getClasseMadeira().name());
        Relatoriofcok2.setText(modeloLigacao.getElementoLigação2().getClasseMadeira().getfc0k() + "");
        RelatorioDensidade2.setText(modeloLigacao.getElementoLigação2().getClasseMadeira().getDensidade() + "");
        Relatoriofv0k2.setText(modeloLigacao.getElementoLigação2().getClasseMadeira().getfv0k() + "");
        RelatorioEc0m2.setText(modeloLigacao.getElementoLigação2().getClasseMadeira().getec0m() + "");
        
        
        atualizaNextElementosLigacao();
    }//GEN-LAST:event_ComboElem2ClasseMadeiraActionPerformed

    private void ValorAngulo(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValorAngulo
        // TODO add your handling code here:
        if (ValorAngulo.getText().length() > 4) {
            ValorAngulo.setText("0");
        }
    }//GEN-LAST:event_ValorAngulo

    private void ComboElem1ClasseMadeiraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ComboElem1ClasseMadeiraPropertyChange

    }//GEN-LAST:event_ComboElem1ClasseMadeiraPropertyChange

    private void ComboElem1ClasseMadeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboElem1ClasseMadeiraActionPerformed
      
    }//GEN-LAST:event_ComboElem1ClasseMadeiraActionPerformed

    private void btn1SecaoCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1SecaoCorteActionPerformed
        // TODO add your handling code here:
        modeloLigacao = ModeloLigacao.CORTE_SIMPLES;
        MadeiraFigura.setVisible(true);
        InclinacaoSim1.setEnabled(true);
        InclinacaoSim2.setEnabled(true);
        jLabelStatusPrego.setText("Escolha a espécie da madeira para continuar.");
        ConiferasButton.setEnabled(true);
        FolhosasButton.setEnabled(true);
        
        RelatorioSecaoCorte.setText("Ligação com Corte Simples");
        MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensDirecao/2Paralelo.png"))); // NOI18N
        FiguraSecoes.setIcon(new ImageIcon(((ImageIcon) btn1SecaoCorte.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));
        
        System.out.println("modeloLigacao =" + modeloLigacao);
        System.out.println("NumSecao =" + modeloLigacao.getNumSecao());

        //double x = valor_faxrk(1, 250, Double.parseDouble("6.5"), 12, 250);
        //JOptionPane.showMessageDialog(this, "Esta habilitado a= ["+a.booleanValue()+"] b=["+b.booleanValue()+"] c=["+c.booleanValue()+"]");
        //JOptionPane.showMessageDialog(this, "Kmod1 da função= ["+Double.toString(x)+"");

    }//GEN-LAST:event_btn1SecaoCorteActionPerformed

    private void InclinacaoSim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InclinacaoSim1ActionPerformed
        // TODO add your handling code here:
        IncSim1 = false;
        IncSim2 = true;
        
        MadeiraFigura.setVisible(true);
        String TrocaFigura = "";
        
        if (modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
            CalculoForca01.setVisible(true);
            CalculoForca02.setVisible(false);
            CalculoForca901.setVisible(false);
            CalculoForca902.setVisible(false);
            CalculoForcaAlfa1.setVisible(false);
            CalculoForcaAlfa2.setVisible(true);
            
            TrocaFigura = "1InclinadoElem2P.png"; // NOI18N 

        }
        
        if (modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
            CalculoForca01.setVisible(true);
            CalculoForca02.setVisible(false);
            CalculoForca901.setVisible(false);
            CalculoForca902.setVisible(false);
            CalculoForcaAlfa1.setVisible(false);
            CalculoForcaAlfa2.setVisible(true);
            
            TrocaFigura = "2InclinadoElem2P.png"; // NOI18N  

        }
        
        if (modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
            CalculoForca01.setVisible(true);
            CalculoForca02.setVisible(false);
            CalculoForca901.setVisible(false);
            CalculoForca902.setVisible(true);
            CalculoForcaAlfa1.setVisible(false);
            CalculoForcaAlfa2.setVisible(false);
            
            TrocaFigura = "1PerpendicularElem2P.png"; // NOI18N            

        }
        
        if (modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
            CalculoForca01.setVisible(true);
            CalculoForca02.setVisible(false);
            CalculoForca901.setVisible(false);
            CalculoForca902.setVisible(true);
            CalculoForcaAlfa1.setVisible(false);
            CalculoForcaAlfa2.setVisible(false);
            
            TrocaFigura = "2PerpendicularElem2P.png"; // NOI18N 

        }
        
        if (modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
            CalculoForca01.setVisible(true);
            CalculoForca02.setVisible(false);
            CalculoForca901.setVisible(false);
            CalculoForca902.setVisible(false);
            CalculoForcaAlfa1.setVisible(false);
            CalculoForcaAlfa2.setVisible(true);
            
            TrocaFigura = "2InclinadoElem2P.png"; // NOI18N            

        }
        if (modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
            CalculoForca01.setVisible(true);
            CalculoForca02.setVisible(false);
            CalculoForca901.setVisible(false);
            CalculoForca902.setVisible(true);
            CalculoForcaAlfa1.setVisible(false);
            CalculoForcaAlfa2.setVisible(false);
            
            TrocaFigura = "2PerpendicularElem2P.png"; // NOI18N            

        }
        MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensDirecao/" + TrocaFigura))); // NOI18N

    }//GEN-LAST:event_InclinacaoSim1ActionPerformed

    private void InclinacaoSim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InclinacaoSim2ActionPerformed
        // TODO add your handling code here:
        IncSim1 = true;
        IncSim2 = false;
        
        MadeiraFigura.setVisible(true);
        String TrocaFigura = "";
        
        if (modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
            CalculoForca01.setVisible(false);
            CalculoForca02.setVisible(true);
            CalculoForca901.setVisible(false);
            CalculoForca902.setVisible(false);
            CalculoForcaAlfa1.setVisible(true);
            CalculoForcaAlfa2.setVisible(false);
            
            TrocaFigura = "1InclinadoElem1P.png"; // NOI18N            

        }
        
        if (modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
            CalculoForca01.setVisible(false);
            CalculoForca02.setVisible(true);
            CalculoForca901.setVisible(false);
            CalculoForca902.setVisible(false);
            CalculoForcaAlfa1.setVisible(true);
            CalculoForcaAlfa2.setVisible(false);
            
            TrocaFigura = "2InclinadoElem1P.png"; // NOI18N            

        }
        if (modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
            CalculoForca01.setVisible(false);
            CalculoForca02.setVisible(true);
            CalculoForca901.setVisible(true);
            CalculoForca902.setVisible(false);
            CalculoForcaAlfa1.setVisible(false);
            CalculoForcaAlfa2.setVisible(false);
            
            TrocaFigura = "1PerpendicularElem1P.png"; // NOI18N            

        }
        
        if (modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
            CalculoForca01.setVisible(false);
            CalculoForca02.setVisible(true);
            CalculoForca901.setVisible(true);
            CalculoForca902.setVisible(false);
            CalculoForcaAlfa1.setVisible(false);
            CalculoForcaAlfa2.setVisible(false);
            
            TrocaFigura = "2PerpendicularElem1P.png"; // NOI18N            

        }
        if (modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
            CalculoForca01.setVisible(false);
            CalculoForca02.setVisible(true);
            CalculoForca901.setVisible(false);
            CalculoForca902.setVisible(false);
            CalculoForcaAlfa1.setVisible(true);
            CalculoForcaAlfa2.setVisible(false);
            
            TrocaFigura = "2InclinadoElem1P.png"; // NOI18N            

        }
        if (modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
            CalculoForca01.setVisible(false);
            CalculoForca02.setVisible(true);
            CalculoForca901.setVisible(true);
            CalculoForca902.setVisible(false);
            CalculoForcaAlfa1.setVisible(false);
            CalculoForcaAlfa2.setVisible(false);
            
            TrocaFigura = "2PerpendicularElem1P.png"; // NOI18N            

        }
        MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensDirecao/" + TrocaFigura))); // NOI18N

    }//GEN-LAST:event_InclinacaoSim2ActionPerformed

    private void ValorAnguloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ValorAnguloKeyTyped
        // TODO add your handling code here:
        if ((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') || evt.getKeyChar() == ',') {
            return;
        }
        evt.consume();

    }//GEN-LAST:event_ValorAnguloKeyTyped

    private void Espessura2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Espessura2KeyTyped
        // TODO add your handling code here:
        if ((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') || evt.getKeyChar() == ',') {
            return;
        }
        evt.consume();

    }//GEN-LAST:event_Espessura2KeyTyped

    private void ValorAnguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValorAnguloActionPerformed
        // TODO add your handling code here:
        //modeloLigacao.getAngulo().setValorGrau(Double.parseDouble(ValorAngulo.getText().replace(",", ".")));
        RelatorioAngulo.setText(modeloLigacao.getAngulo().getValorGrau() + "");
        atualizaNextElementosLigacao();
    }//GEN-LAST:event_ValorAnguloActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        Relatorio.setVisible(false);
    }//GEN-LAST:event_formWindowActivated

    private void FiguraTipoParafusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiguraTipoParafusoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FiguraTipoParafusoActionPerformed

    private void MadeiraFiguraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MadeiraFiguraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MadeiraFiguraActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        // TODO add your handling code here:

        switch (jTabbedPane1.getSelectedIndex()) {
            case 2:
                
                ValorAngulo.setText("0");
                MadeiraFigura.setVisible(true);
                String FiguraMadeira = "";
                
                if (modeloLigacao == ModeloLigacao.CORTE_SIMPLES) {
                    
                    FiguraMadeira = "1ParaleloP.png";
                    
                }
                if (modeloLigacao == ModeloLigacao.CORTE_DUPLO) {
                    
                    FiguraMadeira = "2ParaleloP.png";
                    
                }
                if (modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES) {
                    FiguraMadeira = "2ParaleloP.png";
                    
                }
                
                MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensDirecao/" + FiguraMadeira))); // NOI18N

                // Remove todos os elementos a partir do segundo
                while (ComboKmod1.getModel().getSize() > 1) {
                    ComboKmod1.removeItemAt(1);
                }
                
                for (Kmod1 kmod1 : Kmod1.values()) {
                    if (kmod1.getTipoMadeira() == modeloLigacao.getTipoMadeira()) {
                        ComboKmod1.addItem(kmod1);
                    }
                }
                ComboKmod1.addItem(Kmod1.OUTRO);
                
                while (ComboKmod2.getModel().getSize() > 1) {
                    ComboKmod2.removeItemAt(1);
                }
                
                for (Kmod2 kmod2 : Kmod2.values()) {
                    if (kmod2.getTipoMadeira() == modeloLigacao.getTipoMadeira()) {
                        ComboKmod2.addItem(kmod2);
                    }
                }
                ComboKmod2.addItem(Kmod2.OUTRO);
                
                while (ComboKmod3.getModel().getSize() > 1) {
                    ComboKmod3.removeItemAt(1);
                }
                
                for (Kmod3 kmod3 : Kmod3.values()) {
                    if (kmod3.getEspecieMadeira() == modeloLigacao.getEspecieMadeira()) {
                        ComboKmod3.addItem(kmod3);
                    }
                }
                ComboKmod3.addItem(Kmod1.OUTRO);
                
                break;
            
            case 3:
                // TODO add your handling code here:
                double t1 = modeloLigacao.getElementoLigação1().getEspessura();
                double t2 = modeloLigacao.getElementoLigação2().getEspessura();
                
                while (ComboTipoPrego.getModel().getSize() > 1) {
                    ComboTipoPrego.removeItemAt(1);
                }
                
                boolean entrouDiametro = false;
               
                for (TipoPrego tipoPrego : TipoPrego.values()) {
                    boolean passou = false;
                    
                    if (tipoPrego.getDiametro() < t1 / 5) {
                        entrouDiametro = true;
                        double tp;
                        switch (modeloLigacao) {
                            case CORTE_SIMPLES:
                                tp = tipoPrego.getComprimento() - t1;
                                if (tp > 12 * tipoPrego.getDiametro()) {
                                    passou = true;
                                }
                                break;
                            case DUPLO_CORTE_SIMPLES:
                                tp = tipoPrego.getComprimento() - t1;
                                if (tp > 12 * tipoPrego.getDiametro() && tp < t2) {
                                    passou = true;
                                }
                                break;
                            
                            case CORTE_DUPLO:
                                tp = tipoPrego.getComprimento() - (t1 + t2);
                                if (tp > 12 * tipoPrego.getDiametro()) {
                                    passou = true;
                                }
                                break;
                        }
                    }
                    
                    if (passou) {
                        ComboTipoPrego.addItem(tipoPrego);
                    }
                }
                if (ComboTipoPrego.getItemCount() ==1 && !entrouDiametro){
                    jLabelStatusPrego.setText("Nenhum prego é compatível com as espessuras inseridas, experimente aumentar as espessuras.");
                }
                 if (ComboTipoPrego.getItemCount() ==1 && entrouDiametro){
                    jLabelStatusPrego.setText("Nenhum prego é compatível com as espessuras inseridas, experimente diminuir as espessuras.");
                }
                
                break;
            
        }

    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void ComboQuantPregosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboQuantPregosActionPerformed
        // TODO add your handling code here:
        RelatorioNParafusos.setText(modeloLigacao.getConectores().getQuantidadePrego().getNome() + "");
        atualizaButtonCalcular();
        
//        switch (ComboQuantPregos.getSelectedIndex()) {
//            case 0:
//                npar = 0;
//                RelatorioNParafusos.setText("0");
//            case 1:
//                npar = 2;
//                RelatorioNParafusos.setText("2");
//                break;
//            case 2:
//                npar = 4;
//                RelatorioNParafusos.setText("4");
//                break;
//            case 3:
//                npar = 5;
//                RelatorioNParafusos.setText("5");
//                break;
//            case 4:
//                npar = 6;
//                RelatorioNParafusos.setText("6");
//                break;
//            case 5:
//                npar = 8;
//                RelatorioNParafusos.setText("8");
//                break;
//            case 6:
//                npar = 10;
//                RelatorioNParafusos.setText("10");
//                break;
//            case 7:
//                npar = 12;
//                RelatorioNParafusos.setText("12");
//                break;
//            case 8:
//                npar = 14;
//                RelatorioNParafusos.setText("14");
//                break;
//        }
    }//GEN-LAST:event_ComboQuantPregosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Printable p = new Printable() {
            
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    /* We have only one page, and 'page' is zero-based */
                    return NO_SUCH_PAGE;
                }

//        pageFormat.setOrientation(pageIndex);
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                
                g2d.transform(AffineTransform.getScaleInstance(0.9, 0.9)); //Reduz a forma em 90% para dar certo no tamanho da página
                RelatorioFinal.printAll(g2d);
//        jPanel1.printComponents(g2d);
                return PAGE_EXISTS;
            }
        };
        
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(p);
        
        boolean ok = job.printDialog();
        
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                /* The job did not successfully complete */
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedComponent(Inicio);
        GroupSecoesCorte.clearSelection();
        GroupTesteParafuso.clearSelection();
        Espessura1.setText("Digite a espessura");
        ValorAngulo.setText("0");
        ComboQuantPregos.setSelectedIndex(0);
        ComboTipoPrego.setSelectedIndex(0);
        ComboAco.setSelectedIndex(0);
        ComboElem1ClasseMadeira.setSelectedIndex(0);
        ComboElem2ClasseMadeira.setSelectedIndex(0);
        ComboKmod1.setSelectedIndex(0);
        ComboKmod2.setSelectedIndex(0);
        ComboKmod3.setSelectedIndex(0);
        jLabelStatusPrego.setText("Clique em \"Iniciar Cálculo\" para começar o dimensionamento.");
        modeloLigacao.getAngulo().setValorGrau(0);
        m = false;
        alfa = 0;
        d = 0;
        c = 0;
        tp = 0;
        d1 = 0;
        d2 = 0;
        forcaaplicada = 0;
        nparafusos = 0.0;
//        kmod1 = 0;
//        kmod2 = 0;
//        kmod3 = 0;
        rd1 = 0;
        rd2 = 0;
        rd3 = 0;
        rd4 = 0;
        rd5 = 0;
        rd6 = 0;
        Rdmin = 0;
        Rdlig = 0;
        Rvd = 0;
        valorFaxrk = 0;
        valorFaxrkPrego = 0;
        valorFaxrkFinal = 0;
        npar = 0;
        IncSim1 = false;
        IncSim2 = true;
        
        ImagemTipoArruela.setVisible(false);
        CalculoForca01.setVisible(true);
        CalculoForca02.setVisible(true);
        CalculoForca901.setVisible(false);
        CalculoForca902.setVisible(false);
        CalculoForcaAlfa1.setVisible(false);
        CalculoForcaAlfa2.setVisible(false);
        Inclinado2.setVisible(false);
        Inclinado2.setVisible(false);
        
        MadeiraFigura.setVisible(true);
        
        RelatorioRd13.setVisible(true);
        RelatorioRd14.setVisible(true);
        RelatorioRd3.setVisible(true);
        RelatorioRd4.setVisible(true);
        jLabel60.setVisible(true);
        jLabel62.setVisible(true);
        
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(3, false);
        jTabbedPane1.setEnabledAt(4, false);
        jTabbedPane1.setEnabledAt(5, false);
        Next.setEnabled(false);
        Next2.setEnabled(false);
        Next3.setEnabled(false);
        ButtonCalcular.setEnabled(false);
        SerradaButton.setEnabled(false);
        RecompostaButton.setEnabled(false);
        btn1SecaoCorte.setEnabled(false);
        jToggleButton1.setEnabled(false);
        jToggleButton2.setEnabled(false);
        ConiferasButton.setEnabled(false);
        FolhosasButton.setEnabled(false);
        SerradaButton.setEnabled(false);
        RecompostaButton.setEnabled(false);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void ValorAnguloPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ValorAnguloPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_ValorAnguloPropertyChange

    private void VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        TelaInicial2 telainicial = new TelaInicial2();
        telainicial.setVisible(true);
    }//GEN-LAST:event_VoltarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        TelaInicial2 telainicial = new TelaInicial2();
        telainicial.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ComboKmod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod1ActionPerformed
  
        if (modeloLigacao.getKmod1() == Kmod1.OUTRO) {

            double kmod1 = -1;

            do {
                String value = (String) JOptionPane.showInputDialog(this, "Entre com um valor entre 0 e 1.", "Kmod 1", JOptionPane.INFORMATION_MESSAGE, null, null, "Digite o valor");
                try {
                    kmod1 = Double.parseDouble(value.replace(",", "."));
                } catch (RuntimeException e) {

                }
                jLabelStatusPrego.setText("Entre com um valor válido, entre 0 e 1.");
            } while (kmod1 > 1 || kmod1 <= 0);
            modeloLigacao.getKmod1().setValor(kmod1);
            jLabelStatusPrego.setText("");
        }
        Textkmod1.setText(modeloLigacao.getKmod1().getValor() + "");
        RelatorioKmod1.setText(modeloLigacao.getKmod1().getValor() + "");
    

        atualizaNextElementosLigacao();
    }//GEN-LAST:event_ComboKmod1ActionPerformed

    private void ComboKmod3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod3ActionPerformed
        // TODO add your handling code here:
//        switch (ComboKmod3.getSelectedIndex()) {
//            case 0:
//                modeloLigacao.setKmod3(null);
//                Textkmod3.setText("-");
//                break;
//            default:
//                modeloLigacao.setKmod3((Kmod3) ComboKmod3.getSelectedItem());
                
        if (modeloLigacao.getKmod3() == Kmod3.OUTRO) {

            double kmod3 = -1;

            do {
                String value = (String) JOptionPane.showInputDialog(this, "Entre com um valor entre 0 e 1.", "Kmod 3", JOptionPane.INFORMATION_MESSAGE, null, null, "Digite o valor");
                try {
                    kmod3 = Double.parseDouble(value.replace(",", "."));
                } catch (NumberFormatException e) {

                }
                jLabelStatusPrego.setText("Entre com um valor válido, entre 0 e 1.");
            } while (kmod3 > 1 || kmod3 <= 0);
            modeloLigacao.getKmod3().setValor(kmod3);
            jLabelStatusPrego.setText("");
        }
        Textkmod3.setText(modeloLigacao.getKmod3().getValor() + "");
        Relatoriokmod3.setText(modeloLigacao.getKmod3().getValor() + "");

        atualizaNextElementosLigacao();
    }//GEN-LAST:event_ComboKmod3ActionPerformed

    private void ComboKmod2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod2ActionPerformed
        // TODO add your handling code here:
//        switch (ComboKmod2.getSelectedIndex()) {
//            case 0:
//                modeloLigacao.setKmod2(null);
//                Textkmod2.setText("-");
//                break;
//            default:
//                modeloLigacao.setKmod2((Kmod2) ComboKmod2.getSelectedItem());
                
        if (modeloLigacao.getKmod2() == Kmod2.OUTRO) {

            double kmod2 = -1;

            do {
                String value = (String) JOptionPane.showInputDialog(this, "Entre com um valor entre 0 e 1.", "Kmod 2", JOptionPane.INFORMATION_MESSAGE, null, null, "Digite o valor");
                try {
                    kmod2 = Double.parseDouble(value.replace(",", "."));
                } catch (NumberFormatException e) {

                }
                jLabelStatusPrego.setText("Entre com um valor válido, entre 0 e 1.");
            } while (kmod2 > 1 || kmod2 <= 0);
            modeloLigacao.getKmod2().setValor(kmod2);
            jLabelStatusPrego.setText("");
        }
        Textkmod2.setText(modeloLigacao.getKmod2().getValor() + "");
        Relatoriokmod2.setText(modeloLigacao.getKmod2().getValor() + "");

        atualizaNextElementosLigacao();
    }//GEN-LAST:event_ComboKmod2ActionPerformed

    private void Espessura1(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Espessura1
    }//GEN-LAST:event_Espessura1

    private void Espessura1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Espessura1ActionPerformed
        // TODO add your handling code here:
        RelatorioEspessura1.setText(modeloLigacao.getElementoLigação1().getEspessura() + "");
        atualizaNextElementosLigacao();
    }//GEN-LAST:event_Espessura1ActionPerformed

    private void Espessura1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Espessura1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_Espessura1PropertyChange

    private void Espessura1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Espessura1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_Espessura1KeyTyped

    private void Espessura2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Espessura2ActionPerformed
        // TODO add your handling code here:
        RelatorioEspessura2.setText(modeloLigacao.getElementoLigação2().getEspessura() + "");
        atualizaNextElementosLigacao();
    }//GEN-LAST:event_Espessura2ActionPerformed

    private void FiguraResultadoModoFalhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiguraResultadoModoFalhaActionPerformed
        // TODO add your handling code here:
        atualizaNextElementosLigacao();
    }//GEN-LAST:event_FiguraResultadoModoFalhaActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        modeloLigacao = ModeloLigacao.DUPLO_CORTE_SIMPLES;
        MadeiraFigura.setVisible(true);
        InclinacaoSim1.setEnabled(true);
        InclinacaoSim2.setEnabled(true);
        jLabelStatusPrego.setText("Escolha a espécie da madeira para continuar.");
        ConiferasButton.setEnabled(true);
        FolhosasButton.setEnabled(true);
        
        Next.setEnabled(true);
        RelatorioSecaoCorte.setText("Ligação com Corte Simples");
        MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensDirecao/2ParaleloP.png"))); // NOI18N
        FiguraSecoes.setIcon(new ImageIcon(((ImageIcon) btn1SecaoCorte.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));

        //double x = valor_faxrk(1, 250, Double.parseDouble("6.5"), 12, 250);
        //JOptionPane.showMessageDialog(this, "Esta habilitado a= ["+a.booleanValue()+"] b=["+b.booleanValue()+"] c=["+c.booleanValue()+"]");
        //JOptionPane.showMessageDialog(this, "Kmod1 da função= ["+Double.toString(x)+"");

    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void Espessura2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Espessura2FocusLost
        // TODO add your handling code here:
        double t1 = Integer.parseInt(Espessura1.getText());
        double t2 = Integer.parseInt(Espessura2.getText());
        
        if (t2 < t1) {
            String msg = "";
            
            msg += ".";
            
            if (!msg.isEmpty()) {
                JOptionPane.showMessageDialog(this, "- A espessura do Elemento 1 deve ser menor ou igual a espessura do Elemento 2\n" + msg);
            }
            t1 = 0;
            t2 = 0;
            ComboKmod1.setEnabled(false);
            ComboKmod2.setEnabled(false);
            ComboKmod3.setEnabled(false);
        } else {
            ComboKmod1.setEnabled(true);
            ComboKmod2.setEnabled(true);
            ComboKmod3.setEnabled(true);
        }
    }//GEN-LAST:event_Espessura2FocusLost
    
    private boolean verificaprego(double espessuraElemento1, double espessuraElemento2, double comprimentoPrego, double diametroPrego) {
        return true;
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setSelectedComponent(SecoesCorte);
        jLabelStatusPrego.setText("Escolha a quantidade de seções de corte no prego.");
        btn1SecaoCorte.setEnabled(true);
        jToggleButton1.setEnabled(true);
        jToggleButton2.setEnabled(true);
        

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        modeloLigacao = ModeloLigacao.CORTE_DUPLO;
        MadeiraFigura.setVisible(true);
        InclinacaoSim1.setEnabled(true);
        InclinacaoSim2.setEnabled(true);
        jLabelStatusPrego.setText("Escolha a espécie da madeira para continuar.");
        ConiferasButton.setEnabled(true);
        FolhosasButton.setEnabled(true);
        
        Next.setEnabled(true);
        RelatorioSecaoCorte.setText("Ligação com Corte Duplo");
        MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensDirecao/2Paralelo.png"))); // NOI18N
        FiguraSecoes.setIcon(new ImageIcon(((ImageIcon) btn1SecaoCorte.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));

        //double x = valor_faxrk(1, 250, Double.parseDouble("6.5"), 12, 250);
        //JOptionPane.showMessageDialog(this, "Esta habilitado a= ["+a.booleanValue()+"] b=["+b.booleanValue()+"] c=["+c.booleanValue()+"]");
        //JOptionPane.showMessageDialog(this, "Kmod1 da função= ["+Double.toString(x)+"");
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void ValorAnguloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValorAnguloFocusLost
        // TODO add your handling code here:
        modeloLigacao.getAngulo().setValorGrau(Double.parseDouble(ValorAngulo.getText().replace(",", ".")));
        
        MadeiraFigura.setVisible(true);
        String FiguraMadeira = "";
        
        switch (modeloLigacao) {
            case CORTE_SIMPLES:
                switch (modeloLigacao.getAngulo().getTipoAngulo()) {
                    case PARALELO:
                        FiguraMadeira = "1ParaleloP.png";
                        InclinacaoSim1.setEnabled(false);
                        InclinacaoSim2.setEnabled(false);
                        RelatorioAngulacao.setText("Direção Paralela");
                        CalculoForca01.setVisible(true);
                        CalculoForca901.setVisible(false);// excluído o boolean paralelo, inclinado ou perpendicular recebe true
                        CalculoForcaAlfa1.setVisible(false);
                        break;
                    case PERPENDICULAR:
                        FiguraMadeira = "1PerpendicularElem1P.png";
                        InclinacaoSim1.setEnabled(true);
                        InclinacaoSim2.setEnabled(true);
                        RelatorioAngulacao.setText("Direção Perpendicular");
                        CalculoForca01.setVisible(false);
                        CalculoForca901.setVisible(true);
                        CalculoForcaAlfa1.setVisible(false);
                        break;
                    case INCLINADO:
                        InclinacaoSim1.setEnabled(true);
                        InclinacaoSim2.setEnabled(true);
                        RelatorioAngulacao.setText("Direção Inclinada");
                        CalculoForca01.setVisible(false);
                        CalculoForca901.setVisible(false);
                        CalculoForcaAlfa1.setVisible(true);
                        break;
                }
                break;
            case CORTE_DUPLO:
                switch (modeloLigacao.getAngulo().getTipoAngulo()) {
                    case PARALELO:
                        FiguraMadeira = "2ParaleloP.png";
                        InclinacaoSim1.setEnabled(false);
                        InclinacaoSim2.setEnabled(false);
                        RelatorioAngulacao.setText("Direção Paralela");
                        CalculoForca01.setVisible(true);
                        CalculoForca901.setVisible(false);
                        CalculoForcaAlfa1.setVisible(false);
                        break;
                    case PERPENDICULAR:
                        FiguraMadeira = "2PerpendicularElem1P.png";
                        InclinacaoSim1.setEnabled(true);
                        InclinacaoSim2.setEnabled(true);
                        RelatorioAngulacao.setText("Direção Perpendicular");
                        CalculoForca01.setVisible(false);
                        CalculoForca901.setVisible(true);
                        CalculoForcaAlfa1.setVisible(false);
                        break;
                    case INCLINADO:
                        FiguraMadeira = "2InclinadoElem1P.png";
                        InclinacaoSim1.setEnabled(true);
                        InclinacaoSim2.setEnabled(true);
                        RelatorioAngulacao.setText("Direção Inclinada");
                        CalculoForca01.setVisible(false);
                        CalculoForca901.setVisible(false);
                        CalculoForcaAlfa1.setVisible(true);
                        break;
                }
                break;
            case DUPLO_CORTE_SIMPLES:
                switch (modeloLigacao.getAngulo().getTipoAngulo()) {
                    case PARALELO:
                        FiguraMadeira = "2ParaleloP.png";
                        InclinacaoSim1.setEnabled(false);
                        InclinacaoSim2.setEnabled(false);
                        RelatorioAngulacao.setText("Direção Paralela");
                        CalculoForca01.setVisible(true);
                        CalculoForca901.setVisible(false);
                        CalculoForcaAlfa1.setVisible(false);
                        break;
                    case PERPENDICULAR:
                        FiguraMadeira = "2PerpendicularElem1P.png";
                        InclinacaoSim1.setEnabled(true);
                        InclinacaoSim2.setEnabled(true);
                        RelatorioAngulacao.setText("Direção Perpendicular");
                        CalculoForca01.setVisible(false);
                        CalculoForca901.setVisible(true);
                        CalculoForcaAlfa1.setVisible(false);
                        break;
                    case INCLINADO:
                        FiguraMadeira = "2InclinadoElem1P.png";
                        InclinacaoSim1.setEnabled(true);
                        InclinacaoSim2.setEnabled(true);
                        RelatorioAngulacao.setText("Direção Inclinada");
                        CalculoForca01.setVisible(false);
                        CalculoForca901.setVisible(false);
                        CalculoForcaAlfa1.setVisible(true);
                        break;
                }
                break;
        }
        MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/examples/ImagensDirecao/" + FiguraMadeira))); // NOI18N
        MadeiraFigura.doClick();
    }//GEN-LAST:event_ValorAnguloFocusLost

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setEnabledAt(2, true);
        jTabbedPane1.setSelectedComponent(ElementosMadeira);
        jLabelStatusPrego.setText("Preencha todos os campos sem exceção.");
    }//GEN-LAST:event_NextActionPerformed

    private void Next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Next2ActionPerformed
        // TODO add your handling code here:
        Espessura1.getInputVerifier().shouldYieldFocus(Espessura1);
        Espessura2.getInputVerifier().shouldYieldFocus(Espessura2);
        ValorAngulo.getInputVerifier().shouldYieldFocus(ValorAngulo);
        ComboElem1ClasseMadeira.getInputVerifier().shouldYieldFocus(ComboElem1ClasseMadeira);
        ComboElem2ClasseMadeira.getInputVerifier().shouldYieldFocus(ComboElem2ClasseMadeira);
        ComboKmod1.getInputVerifier().shouldYieldFocus(ComboKmod1);
        ComboKmod2.getInputVerifier().shouldYieldFocus(ComboKmod2);
        ComboKmod3.getInputVerifier().shouldYieldFocus(ComboKmod3);
        
        if (Next2.hasFocus()) {
            jTabbedPane1.setEnabledAt(3, true);
            jTabbedPane1.setSelectedComponent(ElementosMetalicos);
            jLabelStatusPrego.setText("Preencha todos os campos sem exceção.");
        }
    }//GEN-LAST:event_Next2ActionPerformed

    private void Next3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Next3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setEnabledAt(5, true);
        jTabbedPane1.setSelectedComponent(Relatorio);
        jLabelStatusPrego.setText("Verifique o relatório do dimensionamento. Fique atento para as opções fornecidas nos botões.");

    }//GEN-LAST:event_Next3ActionPerformed

    private void ValorAnguloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ValorAnguloMouseClicked
        // TODO add your handling code here:
        jLabelStatusPrego.setText("Digite o ângulo entre as peças e aperte a tecla TAB.");
    }//GEN-LAST:event_ValorAnguloMouseClicked

    private void ConiferasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConiferasButtonActionPerformed
        modeloLigacao.setEspecieMadeira(EspecieMadeira.CONIFERA);
        jLabelStatusPrego.setText("Escolha o tipo de madeira para continuar.");
        SerradaButton.setEnabled(true);
        RecompostaButton.setEnabled(true);
        

    }//GEN-LAST:event_ConiferasButtonActionPerformed

    private void FolhosasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FolhosasButtonActionPerformed
        modeloLigacao.setEspecieMadeira(EspecieMadeira.FOLHOSA);
        jLabelStatusPrego.setText("Escolha o tipo de madeira para continuar.");
        SerradaButton.setEnabled(true);
        RecompostaButton.setEnabled(true);

    }//GEN-LAST:event_FolhosasButtonActionPerformed

    private void SerradaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SerradaButtonActionPerformed
        // TODO add your handling code here:
        modeloLigacao.setTipoMadeira(TipoMadeira.SERRADA);
        jLabelStatusPrego.setText("Clique em avançar para continuar.");
        Next.setEnabled(true);

    }//GEN-LAST:event_SerradaButtonActionPerformed

    private void RecompostaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecompostaButtonActionPerformed
        // TODO add your handling code here:
        modeloLigacao.setTipoMadeira(TipoMadeira.RECOMPOSTA);
        jLabelStatusPrego.setText("Clique em avançar para continuar.");
        Next.setEnabled(true);

    }//GEN-LAST:event_RecompostaButtonActionPerformed

    private void jTabbedPane1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1FocusLost

    private void ComboElem1ClasseMadeiraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ComboElem1ClasseMadeiraFocusLost
 if (((VerificadoresPrego)ComboElem1ClasseMadeira.getInputVerifier()).isVerified()){
           
       
        ValorFc01.setText(modeloLigacao.getElementoLigação1().getClasseMadeira().getfc0k() + "");
        ValorDensidade1.setText(modeloLigacao.getElementoLigação1().getClasseMadeira().getDensidade() + "");
        ValorFvok1.setText(modeloLigacao.getElementoLigação1().getClasseMadeira().getfv0k() + "");
        ValorEc0m1.setText(modeloLigacao.getElementoLigação1().getClasseMadeira().getec0m() + "");
        RelatorioClasseElem1.setText(modeloLigacao.getElementoLigação1().getClasseMadeira().name());
        Relatoriofcok1.setText(modeloLigacao.getElementoLigação1().getClasseMadeira().getfc0k() + "");
        RelatorioDensidade1.setText(modeloLigacao.getElementoLigação1().getClasseMadeira().getDensidade() + "");
        Relatoriofv0k1.setText(modeloLigacao.getElementoLigação1().getClasseMadeira().getfv0k() + "");
        RelatorioEc0m1.setText(modeloLigacao.getElementoLigação1().getClasseMadeira().getec0m() + "");
       }
        
       // TODO add your handling code here:

    atualizaNextElementosLigacao();        // TODO add your handling code here:
    }//GEN-LAST:event_ComboElem1ClasseMadeiraFocusLost
    /**/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                    
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrego().setVisible(true);
            }
        });

//        Date sysDate = new Date();
//SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
//dateFormat.format(sysDate);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AreaParafuso;
    private javax.swing.JButton ButtonCalcular;
    private javax.swing.JLabel CalculoForca01;
    private javax.swing.JLabel CalculoForca02;
    private javax.swing.JLabel CalculoForca901;
    private javax.swing.JLabel CalculoForca902;
    private javax.swing.JLabel CalculoForcaAlfa1;
    private javax.swing.JLabel CalculoForcaAlfa2;
    private javax.swing.JLabel ClasseAco;
    private javax.swing.JComboBox ComboAco;
    private javax.swing.JComboBox ComboElem1ClasseMadeira;
    private javax.swing.JComboBox ComboElem2ClasseMadeira;
    private javax.swing.JComboBox ComboKmod1;
    private javax.swing.JComboBox ComboKmod2;
    private javax.swing.JComboBox ComboKmod3;
    private javax.swing.JComboBox ComboQuantPregos;
    private javax.swing.JComboBox ComboTipoPrego;
    private javax.swing.JToggleButton ConiferasButton;
    private javax.swing.JTextArea Consideracao1;
    private javax.swing.JLabel Data;
    private javax.swing.JLabel Densidade1;
    private javax.swing.JLabel Densidade2;
    private javax.swing.JLabel Diametro;
    private javax.swing.JLabel Ec0m1;
    private javax.swing.JLabel Ec0m2;
    private javax.swing.JPanel ElementosMadeira;
    private javax.swing.JPanel ElementosMetalicos;
    private javax.swing.JTextField Espessura1;
    private javax.swing.JTextField Espessura2;
    private javax.swing.JLabel Fcok1;
    private javax.swing.JLabel Fcok2;
    private javax.swing.JLabel FiguraParafuso;
    private javax.swing.JButton FiguraResultadoModoFalha;
    private javax.swing.JLabel FiguraSecoes;
    private javax.swing.JButton FiguraTipoParafuso;
    private javax.swing.JToggleButton FolhosasButton;
    private javax.swing.JLabel Fv0k1;
    private javax.swing.JLabel Fv0k2;
    private javax.swing.JLabel Fyk;
    private javax.swing.ButtonGroup GroupAngulacao;
    private javax.swing.ButtonGroup GroupInclinacaoSim;
    private javax.swing.ButtonGroup GroupSecoesCorte;
    private javax.swing.ButtonGroup GroupTesteParafuso;
    private javax.swing.JLabel Hora;
    private javax.swing.JLabel ImagemTipoArruela;
    private javax.swing.JRadioButton InclinacaoSim1;
    private javax.swing.JRadioButton InclinacaoSim2;
    private javax.swing.JScrollPane Inclinado1;
    private javax.swing.JScrollPane Inclinado2;
    private javax.swing.JPanel Inicio;
    private javax.swing.JLabel LabelModeloFalha;
    private javax.swing.JLabel LabelResistenciaLigacao;
    private javax.swing.JLabel LabelResultadoModeloFalha;
    private javax.swing.JLabel LogoPrograma;
    private javax.swing.JButton MadeiraFigura;
    private javax.swing.JLabel ModoFalha;
    private javax.swing.JButton Next;
    private javax.swing.JButton Next2;
    private javax.swing.JButton Next3;
    private javax.swing.JLabel NumeroParafusos;
    private javax.swing.JPanel PainelEspecieMadeira;
    private javax.swing.JPanel PainelTipoMadeira;
    private javax.swing.JLabel PossuiInclinacao1;
    private javax.swing.JLabel PossuiInclinacao11;
    private javax.swing.JLabel PossuiInclinacao3;
    private javax.swing.JLabel RLogo;
    private javax.swing.JToggleButton RecompostaButton;
    private javax.swing.JPanel Relatorio;
    private javax.swing.JLabel RelatorioAngulacao;
    private javax.swing.JLabel RelatorioAngulo;
    private javax.swing.JLabel RelatorioBeta;
    private javax.swing.JLabel RelatorioClasseAco;
    private javax.swing.JLabel RelatorioClasseElem1;
    private javax.swing.JLabel RelatorioClasseElem2;
    private javax.swing.JPanel RelatorioCoeficientes;
    private javax.swing.JLabel RelatorioComprimento;
    private javax.swing.JLabel RelatorioDensidade1;
    private javax.swing.JLabel RelatorioDensidade2;
    private javax.swing.JLabel RelatorioDiametro;
    private javax.swing.JLabel RelatorioEc0m1;
    private javax.swing.JLabel RelatorioEc0m2;
    private javax.swing.JPanel RelatorioElem1;
    private javax.swing.JPanel RelatorioElem2;
    private javax.swing.JLabel RelatorioEspessura1;
    private javax.swing.JLabel RelatorioEspessura2;
    private javax.swing.JLabel RelatorioFaxrk;
    private javax.swing.JLabel RelatorioFc0d1;
    private javax.swing.JLabel RelatorioFc0d2;
    private javax.swing.JLabel RelatorioFibras;
    private javax.swing.JPanel RelatorioFinal;
    private javax.swing.JLabel RelatorioFvk;
    private javax.swing.JLabel RelatorioKmod1;
    private javax.swing.JLabel RelatorioMyd;
    private javax.swing.JLabel RelatorioNParafusos;
    private javax.swing.JPanel RelatorioParafuso;
    private javax.swing.JLabel RelatorioRd;
    private javax.swing.JLabel RelatorioRd1;
    private javax.swing.JLabel RelatorioRd11;
    private javax.swing.JLabel RelatorioRd12;
    private javax.swing.JLabel RelatorioRd13;
    private javax.swing.JLabel RelatorioRd14;
    private javax.swing.JLabel RelatorioRd15;
    private javax.swing.JLabel RelatorioRd16;
    private javax.swing.JLabel RelatorioRd2;
    private javax.swing.JLabel RelatorioRd23;
    private javax.swing.JLabel RelatorioRd24;
    private javax.swing.JLabel RelatorioRd3;
    private javax.swing.JLabel RelatorioRd4;
    private javax.swing.JLabel RelatorioRd5;
    private javax.swing.JLabel RelatorioRd6;
    private javax.swing.JLabel RelatorioRk;
    private javax.swing.JLabel RelatorioSecaoCorte;
    private javax.swing.JLabel RelatorioTipoParafuso;
    private javax.swing.JLabel Relatoriofaxrk;
    private javax.swing.JLabel Relatoriofcok1;
    private javax.swing.JLabel Relatoriofcok2;
    private javax.swing.JLabel Relatoriofcok4;
    private javax.swing.JLabel Relatoriofuk;
    private javax.swing.JLabel Relatoriofv0k1;
    private javax.swing.JLabel Relatoriofv0k2;
    private javax.swing.JLabel Relatoriofyk;
    private javax.swing.JLabel Relatoriokmod2;
    private javax.swing.JLabel Relatoriokmod3;
    private javax.swing.JPanel Resultado;
    private javax.swing.JLabel ResultadoFvk;
    private javax.swing.JLabel ResultadoRd;
    private javax.swing.JLabel ResultadoRk;
    private javax.swing.JPanel SecoesCorte;
    private javax.swing.JToggleButton SerradaButton;
    private javax.swing.JLabel TamanhoParafuso;
    private javax.swing.JLabel TesteParafuso;
    private javax.swing.JRadioButton TesteParafusoNao;
    private javax.swing.JRadioButton TesteParafusoSim;
    private javax.swing.JLabel Textkmod1;
    private javax.swing.JLabel Textkmod2;
    private javax.swing.JLabel Textkmod3;
    private javax.swing.JLabel UnDensidade1;
    private javax.swing.JLabel UnDensidade2;
    private javax.swing.JLabel UnEc0m1;
    private javax.swing.JLabel UnEc0m2;
    private javax.swing.JLabel UnFcok1;
    private javax.swing.JLabel UnFcok2;
    private javax.swing.JLabel UnFuk;
    private javax.swing.JLabel UnFv0k1;
    private javax.swing.JLabel UnFv0k2;
    private javax.swing.JLabel UnFyk;
    private javax.swing.JTextField ValorAngulo;
    private javax.swing.JLabel ValorComprimento;
    private javax.swing.JLabel ValorDensidade1;
    private javax.swing.JLabel ValorDensidade2;
    private javax.swing.JLabel ValorDiametro;
    private javax.swing.JLabel ValorEc0m1;
    private javax.swing.JLabel ValorEc0m2;
    private javax.swing.JLabel ValorFc01;
    private javax.swing.JLabel ValorFc2;
    private javax.swing.JLabel ValorFuk;
    private javax.swing.JLabel ValorFvok1;
    private javax.swing.JLabel ValorFvok2;
    private javax.swing.JLabel ValorFyk;
    private javax.swing.JButton Voltar;
    private javax.swing.JToggleButton btn1SecaoCorte;
    private javax.swing.JLabel fuk;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLabelStatusPrego;
    private javax.swing.JLabel jLabelUnEspessura;
    private javax.swing.JLabel jLabelUnEspessura1;
    private javax.swing.JLabel jLabel_classe_madeira_1;
    private javax.swing.JLabel jLabel_classe_madeira_2;
    private javax.swing.JLabel jLabel_elemento1;
    private javax.swing.JLabel jLabel_elemento2;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JTextArea teste;
    // End of variables declaration//GEN-END:variables

    public int calc() {
        double pi = Math.PI;
        Math.random();
        Math.sqrt(pi);
        
        return 0;
    }

    private void atualizaNextElementosLigacao() {
      
        Next2.setEnabled(
                ((VerificadoresPrego) Espessura1.getInputVerifier()).isVerified()
                && ((VerificadoresPrego) Espessura2.getInputVerifier()).isVerified()
                && ((VerificadoresPrego) ValorAngulo.getInputVerifier()).isVerified()
                && ((VerificadoresPrego) ComboElem1ClasseMadeira.getInputVerifier()).isVerified()
                && ((VerificadoresPrego) ComboElem2ClasseMadeira.getInputVerifier()).isVerified()
                && ((VerificadoresPrego) ComboKmod1.getInputVerifier()).isVerified()
                && ((VerificadoresPrego) ComboKmod2.getInputVerifier()).isVerified()
                && ((VerificadoresPrego) ComboKmod3.getInputVerifier()).isVerified()
        );

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void atualizaButtonCalcular() {
      
        ButtonCalcular.setEnabled(
                ((VerificadoresPrego) ComboTipoPrego.getInputVerifier()).isVerified()
                && ((VerificadoresPrego) ComboQuantPregos.getInputVerifier()).isVerified()
                && ((VerificadoresPrego) ComboAco.getInputVerifier()).isVerified()
                
        );

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ModeloLigacao getModeloLigacao() {
        return this.modeloLigacao;
    }
    
}
