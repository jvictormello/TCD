/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.telas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.com.tcd.controller.DuasSecoesChapaCentralController;
import br.com.tcd.controller.DuasSecoesChapaGrossaLateralController;
import br.com.tcd.controller.UmaSecaoCorteFinaController;
import br.com.tcd.controller.UmaSecaoCorteGrossaController;
import br.com.tcd.modelo.DuasSecoesChapaFinaLateralController;

/**
 *
 * @author MarcosVinicius
 */
public class TelaPrincipalAco extends javax.swing.JFrame {
    private static Object dateFormat;

    /**
     * Creates new form TelaPrincipal
     */
    public double d, d1, d2, kmod1, kmod2, fvk1, fvk2, fvk3, fvk4, fvk5, fvk6, fvk7, fvk8, fvk9, fvk10, fvk11,fvk12, kmod3, valorFaxrk, alfa, Fvkmin,Fvkmin2, Rvk, Rvd, nparafusos, arruela, t2;
    public int fyd, npar;
    private Boolean ss,sdama,sdmam,fina,grossa,indefinida, m;
    private int NumParafusos;
  
    ClsDataHora objDataHora = new ClsDataHora();
    private Map<String, Map<String, Double[]>> normas;
    private Map<String, Map<String, Double[]>> normas2;
       
    public TelaPrincipalAco() {
        ss = false;
        sdama = false;
        sdmam = false;
        fina = false;
        grossa = false;
        indefinida = false;
        m = false;
        alfa = 0;
        d = 0;
        d1 = 0;
        d2 = 0;
        nparafusos = 0.0;
        kmod1 = 0;
        kmod2 = 0;
        kmod3 = 0;
        fvk1 = 0;
        fvk2 = 0;
        fvk3 = 0;
        fvk4 = 0;
        fvk5 = 0;
        fvk6 = 0;
        fvk7 = 0;
        fvk8 = 0;
        fvk9 = 0;
        fvk10 = 0;
        fvk11 = 0;
        fvk12 = 0;
        Fvkmin = 0;
        Fvkmin2 = 0;
        Rvk = 0;
        Rvd = 0;
        valorFaxrk = 0;
        npar = 0;
        t2 = 0;
        
        

        initComponents();
        inicializaNormas();
        inicializaNormas2();
        ImagemTipoArruela.setVisible(false);
        CalculoForca01.setVisible(true);
        CalculoForca901.setVisible(false);
        CalculoForcaAlfa1.setVisible(false);
        
        
        TrocaFigura.setVisible(true);
        
        RelatorioRd13.setVisible(true);       
        RelatorioFvk2.setVisible(true);
        jLabel62.setVisible(true);
        
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(3, false);
        jTabbedPane1.setEnabledAt(4, false);
        jTabbedPane1.setEnabledAt(5, false);
        Next.setEnabled(false);
        Next2.setEnabled(false);
        Next3.setEnabled(false);
        
//        Espessura1.setInputVerifier(new Verificadores.VerificadorEspessura1(this.jLabelStatusAco));
//        ComboChapaAco.setInputVerifier(new Verificadores.VerificadorComboEspessuraAco(this.jLabelStatusAco));
//        ValorAngulo.setInputVerifier(new Verificadores.VerificadorValorAngulo(this.jLabelStatusAco));
//        ComboElem1.setInputVerifier(new Verificadores.VerificadorComboClasseElem1(this.jLabelStatusAco));
//        ComboTipoChapaAco.setInputVerifier(new Verificadores.VerificadorComboClasseElemAco(this.jLabelStatusAco));
//        ComboKmod1.setInputVerifier(new Verificadores.VerificadorKmod1(this.jLabelStatusAco));
//        ComboKmod2.setInputVerifier(new Verificadores.VerificadorKmod2(this.jLabelStatusAco));
//        ComboKmod3.setInputVerifier(new Verificadores.VerificadorKmod3(this.jLabelStatusAco, this.Next2));
//        ComboQuantParafuso.setInputVerifier(new Verificadores.VerificadorComboQuantParafuso(this.jLabelStatusAco));
//        ComboTipoParafuso.setInputVerifier(new Verificadores.VerificadorComboTipoParafuso(this.jLabelStatusAco));
//        ComboAco.setInputVerifier(new Verificadores.VerificadorComboAcoParafuso(this.jLabelStatusAco));
//        ComboArruelasMadeira.setInputVerifier(new Verificadores.VerificadorComboArruelas(this.jLabelStatusAco));
//        ComboArruelasAco.setInputVerifier(new Verificadores.VerificadorComboArruelaAco(this.jLabelStatusAco));
        
    }
    private void setNumParafusos(int x){ // alterar a variável global
        NumParafusos = x;
    
    }
    public int getNumParafusos(){
        return NumParafusos;
    }
    
    
    private void inicializaNormas(){
        this.normas = new HashMap<String, Map<String, Double[]>>();

        Map<String, Double[]> parafusos = new HashMap<String, Double[]>();
        normas.put("DIN 436", parafusos);
        parafusos.put("M10", new Double[]{11.0,30.0,1.9078,1.0,3.0});
        parafusos.put("M12", new Double[]{13.5,40.0,1.7391,1.0,3.0});
        parafusos.put("M16", new Double[]{17.5,50.0,1.5166,1.0,3.0});
        parafusos.put("M20", new Double[]{22.0,60.0,1.3868,1.0,3.0});
        parafusos.put("M22", new Double[]{24.0,70.0,1.3338,1.0,3.0});
        parafusos.put("M24", new Double[]{26.0,80.0,1.2963,1.0,3.0});
        parafusos.put("M27", new Double[]{30.0,90.0,1.25,1.0,3.0});
        parafusos.put("M30", new Double[]{33.0,95.0,1.2125,1.0,3.0});

        
        parafusos = new HashMap<String, Double[]>();
        normas.put("DIN 440 R", parafusos);
        parafusos.put("M10", new Double[]{11.0,34.0,1.9078,2.0,1.0});
        parafusos.put("M12", new Double[]{13.5,44.0,1.7391,2.0,1.0});
        parafusos.put("M16", new Double[]{17.5,56.0,1.5166,2.0,1.0});
        parafusos.put("M20", new Double[]{22.0,72.0,1.3868,2.0,1.0});
        parafusos.put("M22", new Double[]{24.0,80.0,1.3338,2.0,1.0});
        parafusos.put("M24", new Double[]{26.0,85.0,1.2963,2.0,1.0});
        parafusos.put("M27", new Double[]{30.0,98.0,1.25,2.0,1.0});
        parafusos.put("M30", new Double[]{33.0,105.0,1.2125,2.0,1.0});
        parafusos.put("M33", new Double[]{36.0,112.0,1.1805,2.0,1.0});
        parafusos.put("M36", new Double[]{39.0,125.0,1.1567,2.0,1.0});

 
        parafusos = new HashMap<String, Double[]>();
        normas.put("DIN 440 V", parafusos);
        parafusos.put("M10", new Double[]{11.0,34.0,1.9078,3.0,2.0});
        parafusos.put("M12", new Double[]{13.5,44.0,1.7391,3.0,2.0});
        parafusos.put("M16", new Double[]{17.5,56.0,1.5166,3.0,2.0});
        parafusos.put("M20", new Double[]{22.0,72.0,1.3868,3.0,2.0});
        parafusos.put("M22", new Double[]{24.0,80.0,1.3338,3.0,2.0});

        
    }
    private void inicializaNormas2(){
        this.normas2 = new HashMap<String, Map<String, Double[]>>();

        Map<String, Double[]> 
        parafusos = new HashMap<String, Double[]>();
        normas2.put("DIN 125", parafusos);
        parafusos.put("M10", new Double[]{10.5,20.0,1.9078,2.0,1.0});
        parafusos.put("M12", new Double[]{13.0,24.0,1.7391,2.0,1.0});
        parafusos.put("M16", new Double[]{17.0,30.0,1.5166,2.0,1.0});
        parafusos.put("M20", new Double[]{21.0,37.0,1.3868,2.0,1.0});
        parafusos.put("M22", new Double[]{23.0,39.0,1.3338,2.0,1.0});
        parafusos.put("M24", new Double[]{25.0,44.0,1.2963,2.0,1.0});
        parafusos.put("M27", new Double[]{28.0,50.0,1.25,2.0,1.0});
        parafusos.put("M30", new Double[]{31.0,56.0,1.2125,2.0,1.0});
        parafusos.put("M33", new Double[]{34.0,60.0,1.1805,2.0,1.0});
        parafusos.put("M36", new Double[]{37.0,66.0,1.1567,2.0,1.0});

        
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
        jLabelStatusAco = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Inicio = new javax.swing.JPanel();
        LogoPrograma = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        IniciarCalculo = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        SecoesCorte = new javax.swing.JPanel();
        btnCorteSimples = new javax.swing.JToggleButton();
        btnCorteDuploAMA = new javax.swing.JToggleButton();
        btnCorteDuploMAM = new javax.swing.JToggleButton();
        Next = new javax.swing.JButton();
        ElementosMadeira = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        ComboElem1 = new javax.swing.JComboBox();
        jLabel_classe_madeira_1 = new javax.swing.JLabel();
        jLabel_elemento1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Espessura1 = new javax.swing.JTextField();
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
        jLabel11 = new javax.swing.JLabel();
        ValorAngulo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        ComboKmod1 = new javax.swing.JComboBox();
        ComboKmod3 = new javax.swing.JComboBox();
        ComboKmod2 = new javax.swing.JComboBox();
        TextKmod3 = new javax.swing.JLabel();
        TextKmod1 = new javax.swing.JLabel();
        TextKmod2 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        ComboTipoChapaAco = new javax.swing.JComboBox();
        jLabel_classe_madeira_2 = new javax.swing.JLabel();
        jLabel_elemento2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelUnEspessura1 = new javax.swing.JLabel();
        Fcok2 = new javax.swing.JLabel();
        UnFcok2 = new javax.swing.JLabel();
        ValorChapaFyk = new javax.swing.JLabel();
        Densidade2 = new javax.swing.JLabel();
        UnDensidade2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        ComboChapaAco = new javax.swing.JComboBox();
        ValorChapaFuk = new javax.swing.JLabel();
        TrocaFigura = new javax.swing.JButton();
        Next2 = new javax.swing.JButton();
        ElementosMetalicos = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TamanhoParafuso = new javax.swing.JLabel();
        ComboTipoParafuso = new javax.swing.JComboBox();
        Diametro = new javax.swing.JLabel();
        ValorDiametro = new javax.swing.JLabel();
        AreaParafuso = new javax.swing.JLabel();
        ValorArea = new javax.swing.JLabel();
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
        ComboQuantParafuso = new javax.swing.JComboBox();
        jLabel65 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        Arruelas = new javax.swing.JLabel();
        TipoArruela = new javax.swing.JLabel();
        ComboArruelasMadeira = new javax.swing.JComboBox();
        D1Arruelas = new javax.swing.JLabel();
        D2Arruelas = new javax.swing.JLabel();
        ValorD1Arruelas = new javax.swing.JLabel();
        ValorD2Arruelas = new javax.swing.JLabel();
        UnD1Arruelas = new javax.swing.JLabel();
        UnD2Arruelas = new javax.swing.JLabel();
        ComboArruelasAco = new javax.swing.JComboBox();
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
        ResultadoRvk = new javax.swing.JLabel();
        ResultadoRd = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        LabelEspessuraChapa = new javax.swing.JLabel();
        Next3 = new javax.swing.JButton();
        Relatorio = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        RelatorioFinal = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        RelatorioSecaoCorte = new javax.swing.JLabel();
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
        jSeparator12 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        RelatorioChapaFyk = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        RelatorioChapaFuk = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        RelatorioChapaEspessura = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        LabelTipoChapaAco = new javax.swing.JLabel();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        RelatorioTipoArruela = new javax.swing.JLabel();
        RelatorioD1 = new javax.swing.JLabel();
        RelatorioD2 = new javax.swing.JLabel();
        FiguraArruela = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        RelatorioFibras = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        CalculoForca01 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        RelatorioFc0d1 = new javax.swing.JLabel();
        RelatorioFaxrk = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        CalculoForcaAlfa1 = new javax.swing.JLabel();
        CalculoForca901 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        RelatorioRd11 = new javax.swing.JLabel();
        RelatorioRd13 = new javax.swing.JLabel();
        RelatorioFvk1 = new javax.swing.JLabel();
        RelatorioFvk2 = new javax.swing.JLabel();
        RelatorioFvk3 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        RelatorioMyd = new javax.swing.JLabel();
        RelatorioRd23 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        LabelInter1 = new javax.swing.JLabel();
        LabelInt2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel67 = new javax.swing.JLabel();
        ModoFalha = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        RelatorioRvk = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        FiguraSecoes = new javax.swing.JLabel();
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
        LabelRelatorioChapaAco = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TCD - Timber Connections Design");
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setIconImages(null);
        setMaximumSize(new java.awt.Dimension(900, 700));
        setMinimumSize(new java.awt.Dimension(900, 625));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(825, 625));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabelStatusAco.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelStatusAco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelStatusAco.setText("Clique em \"Iniciar Cálculo\" para começar o dimensionamento.");
        jLabelStatusAco.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
        jLabelStatusAco.setPreferredSize(new java.awt.Dimension(38, 30));
        getContentPane().add(jLabelStatusAco, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(50000, 50000));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(800, 554));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(877, 603));
        jTabbedPane1.setRequestFocusEnabled(false);
        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });

        Inicio.setBackground(new java.awt.Color(204, 204, 204));

        LogoPrograma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Logo/logosket.png"))); // NOI18N

        jLabel97.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel97.setText("TCD - Timber Connections Design");

        IniciarCalculo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        IniciarCalculo.setText("Iniciar Cálculo");
        IniciarCalculo.setToolTipText("Escolha esta opção para iniciar o dimensionamento de sua ligação.");
        IniciarCalculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarCalculoActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton4.setText("Voltar");
        jButton4.setToolTipText("Escolha esta opção para retornar a tela inicial.");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel15.setText("Cálculo de ligações parafusadas entre elementos de madeira e aço.");

        javax.swing.GroupLayout InicioLayout = new javax.swing.GroupLayout(Inicio);
        Inicio.setLayout(InicioLayout);
        InicioLayout.setHorizontalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioLayout.createSequentialGroup()
                .addComponent(LogoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(254, 254, 254))
            .addGroup(InicioLayout.createSequentialGroup()
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(IniciarCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InicioLayout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(193, 193, 193))
        );
        InicioLayout.setVerticalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicioLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(LogoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel97)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(95, 95, 95)
                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IniciarCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inicio", Inicio);

        SecoesCorte.setBackground(new java.awt.Color(204, 204, 204));
        SecoesCorte.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        SecoesCorte.setMaximumSize(new java.awt.Dimension(50000, 50000));

        GroupSecoesCorte.add(btnCorteSimples);
        btnCorteSimples.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCorteSimples.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/telas/ImagensBotao/UmaSecaoAco.png"))); // NOI18N
        btnCorteSimples.setText("Corte Simples");
        btnCorteSimples.setToolTipText("Escolha esta opção se a ligação apresentar apenas uma seção de corte no parafuso.");
        btnCorteSimples.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCorteSimples.setName(""); // NOI18N
        btnCorteSimples.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCorteSimples.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorteSimplesActionPerformed(evt);
            }
        });

        GroupSecoesCorte.add(btnCorteDuploAMA);
        btnCorteDuploAMA.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCorteDuploAMA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/telas/ImagensBotao/DuasSecoesAcoFora.png"))); // NOI18N
        btnCorteDuploAMA.setText("Corte Duplo - Aço/Madeira/Aço");
        btnCorteDuploAMA.setToolTipText("Escolha esta opção se a ligação apresentar duas seções de corte no parafuso, com chapas de aço externamente ao elemento de madeira.");
        btnCorteDuploAMA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCorteDuploAMA.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCorteDuploAMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorteDuploAMAActionPerformed(evt);
            }
        });

        GroupSecoesCorte.add(btnCorteDuploMAM);
        btnCorteDuploMAM.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCorteDuploMAM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/telas/ImagensBotao/DuasSecoesAcoDentro.png"))); // NOI18N
        btnCorteDuploMAM.setText("Corte Duplo - Madeira/Aço/Madeira");
        btnCorteDuploMAM.setToolTipText("Escolha esta opção se a ligação apresentar duas seções de corte no parafuso, com chapas de aço internamente aos elementos de madeira.");
        btnCorteDuploMAM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCorteDuploMAM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCorteDuploMAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorteDuploMAMActionPerformed(evt);
            }
        });

        Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/telas/ImagensBotao/Next.png"))); // NOI18N
        Next.setToolTipText("Clique para avançar.");
        Next.setEnabled(false);
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SecoesCorteLayout = new javax.swing.GroupLayout(SecoesCorte);
        SecoesCorte.setLayout(SecoesCorteLayout);
        SecoesCorteLayout.setHorizontalGroup(
            SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SecoesCorteLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Next, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SecoesCorteLayout.createSequentialGroup()
                        .addComponent(btnCorteSimples, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCorteDuploAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCorteDuploMAM, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SecoesCorteLayout.setVerticalGroup(
            SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SecoesCorteLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCorteDuploMAM, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCorteDuploAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCorteSimples, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(Next)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Modelos de Ligação", SecoesCorte);

        ElementosMadeira.setBackground(new java.awt.Color(204, 204, 204));
        ElementosMadeira.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setPreferredSize(new java.awt.Dimension(385, 225));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ComboElem1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboElem1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha a Classe da Madeira", "C-20", "C-25", "C-30", "D-20", "D-30", "D-40", "D-50", "D-60", " " }));
        ComboElem1.setToolTipText("Defina a classe do elemento de madeira, baseado nas tabelas 2 e 3 da revisão da norma NBR 7190 (2011).");
        ComboElem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboElem1ActionPerformed(evt);
            }
        });
        ComboElem1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ComboElem1PropertyChange(evt);
            }
        });
        jPanel7.add(ComboElem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 250, 29));

        jLabel_classe_madeira_1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel_classe_madeira_1.setText("Classe da Madeira:");
        jPanel7.add(jLabel_classe_madeira_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel_elemento1.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        jLabel_elemento1.setText("Madeira");
        jLabel_elemento1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(jLabel_elemento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 60, 22));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Espessura (t1):");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, -1, -1));

        Espessura1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Espessura1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Espessura1.setText("Digite a espessura");
        Espessura1.setToolTipText("Insira a espessura do(s) elemento(s) de madeira.");
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
        jPanel7.add(Espessura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 142, 130, -1));

        jLabelUnEspessura.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabelUnEspessura.setText("(mm)");
        jPanel7.add(jLabelUnEspessura, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 145, -1, -1));

        Fcok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Fcok1.setText("fc0,k:");
        jPanel7.add(Fcok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, -1, -1));

        UnFcok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnFcok1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        UnFcok1.setText("(MPa)");
        jPanel7.add(UnFcok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 85, -1, -1));
        UnFcok1.getAccessibleContext().setAccessibleDescription("");

        ValorFc01.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorFc01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorFc01.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.add(ValorFc01, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 85, 40, 20));

        Densidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Densidade1.setText("Densidade:");
        jPanel7.add(Densidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 85, -1, -1));

        ValorDensidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorDensidade1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorDensidade1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.add(ValorDensidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 85, 40, 20));

        UnDensidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnDensidade1.setText("(kg/m³)");
        jPanel7.add(UnDensidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 85, -1, -1));

        ValorFvok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorFvok1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorFvok1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.add(ValorFvok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 115, 40, 20));

        UnFv0k1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnFv0k1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        UnFv0k1.setText("(MPa)");
        jPanel7.add(UnFv0k1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 115, -1, -1));

        Fv0k1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Fv0k1.setText("fv0,k:");
        jPanel7.add(Fv0k1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 115, -1, -1));

        Ec0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Ec0m1.setText("Ec0,m:");
        jPanel7.add(Ec0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 115, -1, -1));

        UnEc0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnEc0m1.setText("(MPa)");
        jPanel7.add(UnEc0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 115, -1, -1));

        ValorEc0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorEc0m1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorEc0m1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ValorEc0m1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(ValorEc0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 115, 50, 20));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel11.setText("Ângulo entre as peças:");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 173, -1, -1));

        ValorAngulo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorAngulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ValorAngulo.setText("0");
        ValorAngulo.setToolTipText("Insira o ângulo entre os elementos de madeira e aço.");
        ValorAngulo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValorAnguloFocusLost(evt);
            }
        });
        ValorAngulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValorAnguloActionPerformed(evt);
            }
        });
        ValorAngulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ValorAnguloKeyTyped(evt);
            }
        });
        jPanel7.add(ValorAngulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 171, 63, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setText("(°)");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 172, 20, 20));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel7.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 10, 50));

        ElementosMadeira.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 5, -1, 200));

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        jLabel39.setText("Coeficientes");
        jPanel9.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        jLabel40.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel40.setText("Classes de Carregamento (kmod1):");
        jPanel9.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 210, -1));

        jLabel41.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel41.setText("Classe de Umidade (kmod2):");
        jPanel9.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 200, -1));

        jLabel42.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel42.setText("Qualidade da Madeira (kmod3):");
        jPanel9.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 200, -1));

        ComboKmod1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboKmod1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha o Kmod 1", "------ Madeira Serrada/Roliça/Laminada Colada/Compensada -----", "Permanente", "Longa Duração", "Média Duração", "Curta Duração", "Duração Instantânea", "------ Madeira Recomposta -----", " Permanente", " Longa Duração", " Média Duração", " Curta Duração", " Duração Instantânea", "Outro" }));
        ComboKmod1.setToolTipText("Insira o valor do Kmod 1, o qual é definido pela tabela 4 da revisão da norma ABNT NBR 7190 (2011).");
        ComboKmod1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboKmod1ActionPerformed(evt);
            }
        });
        jPanel9.add(ComboKmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 400, 30));

        ComboKmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboKmod3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha o Kmod 3", "----- Folhosas  -----", "SE - Classificação Visual", "S1 - Classificação Visual", "S2 - Classificação Visual", "S3 - Classificação Visual", "SE - Classificação Visual e Mecânica", "S1 - Classificação Visual e Mecânica", "S2 - Classificação Visual e Mecânica", "S3 - Classificação Visual e Mecânica", "NC - Não Classificada", "------ Coníferas  -----", "SE - D - Classificação Visual", "S1 - D - Classificação Visual", "S2 - D - Classificação Visual", "S3 - D - Classificação Visual", "SE - ND - Classificação Visual", "S1 - ND - Classificação Visual", "S2 - ND - Classificação Visual", "S3 - ND - Classificação Visual", "SE - D - Classificação Visual e Mecânica", "S1 - D - Classificação Visual e Mecânica", "S2 - D - Classificação Visual e Mecânica", "S3 - D - Classificação Visual e Mecânica", "SE - ND - Classificação Visual e Mecânica", "S1 - ND - Classificação Visual e Mecânica", "S2 - ND", "S3 - ND", "Outro" }));
        ComboKmod3.setToolTipText("Insira o valor do Kmod 3, o qual é definido pelas tabelas 6 e 7 da revisão da norma ABNT NBR 7190 (2011).");
        ComboKmod3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboKmod3ActionPerformed(evt);
            }
        });
        jPanel9.add(ComboKmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 400, 30));

        ComboKmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboKmod2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha o Kmod 2", "------ Madeira Serrada/Roliça/Laminada Colada/Compensada -----", "Classe de Umidade 1", "Classe de Umidade 2", "Classe de Umidade 3", "Classe de Umidade 4", "------ Madeira Recomposta -----", " Classe de Umidade 1", " Classe de Umidade 2", " Classe de Umidade 3", " Classe de Umidade 4", "Outro" }));
        ComboKmod2.setToolTipText("Insira o valor do Kmod 2, o qual é definido pela tabela 5 da revisão da norma ABNT NBR 7190 (2011).");
        ComboKmod2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboKmod2ActionPerformed(evt);
            }
        });
        jPanel9.add(ComboKmod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 400, 30));

        TextKmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TextKmod3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextKmod3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.add(TextKmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 125, 40, 20));

        TextKmod1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TextKmod1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextKmod1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.add(TextKmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 45, 40, 20));

        TextKmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TextKmod2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextKmod2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.add(TextKmod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 85, 40, 20));

        ElementosMadeira.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 365, 780, 160));

        jPanel13.setBackground(new java.awt.Color(153, 153, 153));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setPreferredSize(new java.awt.Dimension(385, 225));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ComboTipoChapaAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboTipoChapaAco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha a Classe do Aço", "ASTM A36" }));
        ComboTipoChapaAco.setToolTipText("Defina a classe do aço, baseado na norma ABNT NBR 8800 (2008).");
        ComboTipoChapaAco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTipoChapaAcoActionPerformed(evt);
            }
        });
        ComboTipoChapaAco.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ComboTipoChapaAcoPropertyChange(evt);
            }
        });
        jPanel13.add(ComboTipoChapaAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 270, 29));

        jLabel_classe_madeira_2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel_classe_madeira_2.setText("Classe do Aço:");
        jPanel13.add(jLabel_classe_madeira_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, -1, -1));

        jLabel_elemento2.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        jLabel_elemento2.setText("Chapa de Aço");
        jPanel13.add(jLabel_elemento2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 100, 22));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setText("Espessura (t2):");
        jPanel13.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 123, -1, -1));

        jLabelUnEspessura1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabelUnEspessura1.setText("(pol)");
        jPanel13.add(jLabelUnEspessura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, -1, -1));

        Fcok2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Fcok2.setText("fy,k:");
        jPanel13.add(Fcok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, -1, -1));

        UnFcok2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnFcok2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        UnFcok2.setText("(MPa)");
        jPanel13.add(UnFcok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 85, -1, -1));

        ValorChapaFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorChapaFyk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorChapaFyk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.add(ValorChapaFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 85, 40, 20));

        Densidade2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Densidade2.setText("fu,k");
        jPanel13.add(Densidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 85, -1, -1));

        UnDensidade2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnDensidade2.setText("(MPa)");
        jPanel13.add(UnDensidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 85, -1, -1));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel13.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 75, 10, 30));

        ComboChapaAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboChapaAco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha a espessura", "1/4\"     (6,35mm)", "5/16\"   (8,00 mm)", "3/8\"     (9,53 mm)", "1/2\"     (12,70 mm)", "5/8\"     (15,90 mm)", "3/4\"     (19,10 mm)", "7/8\"     (22,20 mm)", "1\"        (25,40 mm)", "1 1/4\"  (31,80 mm)", "1 1/2\"  (38,10 mm)", "1 5/8\"  (41,30 mm)", "1 3/4\"  (44,50 mm)", "2\"        (50,80 mm)", "2 1/4\"  (57,20 mm)", "2 1/2\"  (63,50 mm)", "3\"        (76,20 mm)", "3 1/2\"  (88,90 mm)", "4\"        (102,00 mm)" }));
        ComboChapaAco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboChapaAcoActionPerformed(evt);
            }
        });
        jPanel13.add(ComboChapaAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 115, 211, 29));

        ValorChapaFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorChapaFuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorChapaFuk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.add(ValorChapaFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 85, 40, 20));

        ElementosMadeira.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 210, -1, 150));

        TrocaFigura.setToolTipText("Esta imagem caracteriza os elementos de madeira.");
        TrocaFigura.setContentAreaFilled(false);
        TrocaFigura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrocaFiguraActionPerformed(evt);
            }
        });
        ElementosMadeira.add(TrocaFigura, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 370, 280));

        Next2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/telas/ImagensBotao/Next.png"))); // NOI18N
        Next2.setToolTipText("Clique para avançar.");
        Next2.setEnabled(false);
        Next2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Next2ActionPerformed(evt);
            }
        });
        ElementosMadeira.add(Next2, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 485, 40, -1));

        jTabbedPane1.addTab("Elementos da Ligação", ElementosMadeira);

        ElementosMetalicos.setBackground(new java.awt.Color(204, 204, 204));
        ElementosMetalicos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(153, 153, 153));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        jLabel1.setText("PARAFUSO");
        jPanel10.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 13, -1, -1));

        TamanhoParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TamanhoParafuso.setText("Tipo de Parafuso:");
        jPanel10.add(TamanhoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 43, -1, -1));

        ComboTipoParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboTipoParafuso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha o Tipo de Parafuso", "M10", "M12", "M16", "M20", "M22", "M24", "M27", "M30", "M33", "M36" }));
        ComboTipoParafuso.setToolTipText("Escolha o tipo de parafuso utilizado, baseado na norma EN ISO 4016 (2001).");
        ComboTipoParafuso.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ComboTipoParafuso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTipoParafusoActionPerformed(evt);
            }
        });
        jPanel10.add(ComboTipoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 41, 270, -1));

        Diametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Diametro.setText("Diâmetro (mm):");
        jPanel10.add(Diametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        ValorDiametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorDiametro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorDiametro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.add(ValorDiametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 40, 20));

        AreaParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        AreaParafuso.setText("Área do Parafuso (mm²):");
        jPanel10.add(AreaParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        ValorArea.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.add(ValorArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 70, 40, 20));

        NumeroParafusos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        NumeroParafusos.setText("Número de Parafusos:");
        jPanel10.add(NumeroParafusos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 103, -1, -1));

        ClasseAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ClasseAco.setText("Classe do Aço:");
        jPanel10.add(ClasseAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 142, -1, -1));

        ComboAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboAco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha a Classe de Aço", "ISO 898-1 - Classe 4.6", "ISO 898-1 - Classe 8.8", "ISO 898-1 - Classe 10.9" }));
        ComboAco.setToolTipText("Defina a classe do aço do parafuso, baseado na norma ABNT NBR 8800 (2008).");
        ComboAco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ComboAco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboAcoActionPerformed(evt);
            }
        });
        jPanel10.add(ComboAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 140, 290, -1));

        Fyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Fyk.setText("fy,k:");
        jPanel10.add(Fyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 175, -1, -1));

        fuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        fuk.setText("fu,k:");
        jPanel10.add(fuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 175, -1, -1));

        ValorFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorFyk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorFyk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.add(ValorFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 175, 40, 20));

        ValorFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorFuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorFuk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.add(ValorFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 175, 40, 20));

        UnFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnFyk.setText("(MPa)");
        jPanel10.add(UnFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 175, -1, -1));

        UnFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnFuk.setText("(MPa)");
        jPanel10.add(UnFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 175, -1, -1));

        TesteParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TesteParafuso.setText("Foram realizados ensaios na ligação em estudo que comprovem ");
        TesteParafuso.setToolTipText("Considera-se ou não a força de arrancamento causada pelo parafuso na madeira. EUROCODE 5 (2004);");
        jPanel10.add(TesteParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 200, -1, -1));

        GroupTesteParafuso.add(TesteParafusoSim);
        TesteParafusoSim.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TesteParafusoSim.setText("Sim");
        TesteParafusoSim.setToolTipText("Escolha se a ligação considera ou não a força de arrancamento causada pelo parafuso na madeira.");
        TesteParafusoSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TesteParafusoSimActionPerformed(evt);
            }
        });
        jPanel10.add(TesteParafusoSim, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 235, 50, -1));

        GroupTesteParafuso.add(TesteParafusoNao);
        TesteParafusoNao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TesteParafusoNao.setText("Não");
        TesteParafusoNao.setToolTipText("Escolha se a ligação considera ou não a força de arrancamento causada pelo parafuso na madeira.");
        TesteParafusoNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TesteParafusoNaoActionPerformed(evt);
            }
        });
        jPanel10.add(TesteParafusoNao, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 235, 50, -1));

        ComboQuantParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboQuantParafuso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha a quantidade de parafusos", "1", "2", "4", "5", "6", "8", "10", "12", "14" }));
        ComboQuantParafuso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboQuantParafusoActionPerformed(evt);
            }
        });
        jPanel10.add(ComboQuantParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 245, -1));

        jLabel65.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel65.setText("que o efeito de confinamento possa ser utilizado?");
        jPanel10.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 215, -1, -1));

        ElementosMetalicos.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 43, 425, 265));

        jPanel11.setBackground(new java.awt.Color(153, 153, 153));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Arruelas.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        Arruelas.setText("ARRUELAS");
        jPanel11.add(Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 13, -1, -1));

        TipoArruela.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TipoArruela.setText("Tipo de Arruela:");
        jPanel11.add(TipoArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 45, -1, -1));

        ComboArruelasMadeira.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboArruelasMadeira.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha o Tipo de Arruela para Madeira" }));
        ComboArruelasMadeira.setToolTipText("Defina o tipo de arruela, baseado nas normas DIN 440 R, DIN 440 V e DIN 436.");
        ComboArruelasMadeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboArruelasMadeiraActionPerformed(evt);
            }
        });
        jPanel11.add(ComboArruelasMadeira, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 270, -1));

        D1Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        D1Arruelas.setText("Dimensão (d1):");
        jPanel11.add(D1Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 95, -1, -1));

        D2Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        D2Arruelas.setText("Dimensão (d2):");
        jPanel11.add(D2Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 95, -1, -1));

        ValorD1Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorD1Arruelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorD1Arruelas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.add(ValorD1Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 95, 40, 20));

        ValorD2Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ValorD2Arruelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValorD2Arruelas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.add(ValorD2Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 95, 40, 20));

        UnD1Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnD1Arruelas.setText("(mm)");
        jPanel11.add(UnD1Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 95, -1, -1));

        UnD2Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        UnD2Arruelas.setText("(mm)");
        jPanel11.add(UnD2Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 95, -1, -1));

        ComboArruelasAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ComboArruelasAco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha o TIpo de Arruela para Aço" }));
        ComboArruelasAco.setToolTipText("Defina o tipo de arruela, baseado nas normas DIN 125.");
        ComboArruelasAco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboArruelasAcoActionPerformed(evt);
            }
        });
        jPanel11.add(ComboArruelasAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 270, -1));

        ElementosMetalicos.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 320, 425, 130));

        ButtonCalcular.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        ButtonCalcular.setText("CALCULAR LIGAÇÃO");
        ButtonCalcular.setToolTipText("Clique aqui para calcular sua ligação. Fique atento! Caso haja informações faltantes ou inconsistentes, o cálculo não será realizado e aparecerá uma mensagem.");
        ButtonCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCalcularActionPerformed(evt);
            }
        });
        ElementosMetalicos.add(ButtonCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 468, 210, 41));

        FiguraTipoParafuso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/telas/Imagens/Parafuso.png"))); // NOI18N
        FiguraTipoParafuso.setContentAreaFilled(false);
        FiguraTipoParafuso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiguraTipoParafusoActionPerformed(evt);
            }
        });
        ElementosMetalicos.add(FiguraTipoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 83, 295, 150));
        ElementosMetalicos.add(ImagemTipoArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 300, 150));

        jTabbedPane1.addTab("Conectores", ElementosMetalicos);

        Resultado.setBackground(new java.awt.Color(204, 204, 204));
        Resultado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabelModeloFalha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        LabelModeloFalha.setText("Modelo de falha:");
        Resultado.add(LabelModeloFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 110, 20));

        LabelResultadoModeloFalha.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Resultado.add(LabelResultadoModeloFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 660, 20));

        LabelResistenciaLigacao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        LabelResistenciaLigacao.setText("Fv,Rk:");
        Resultado.add(LabelResistenciaLigacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 60, 20));

        ResultadoFvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ResultadoFvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Resultado.add(ResultadoFvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 80, 20));

        FiguraResultadoModoFalha.setContentAreaFilled(false);
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

        ResultadoRvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ResultadoRvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Resultado.add(ResultadoRvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 80, 20));

        ResultadoRd.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        ResultadoRd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Resultado.add(ResultadoRd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 80, 20));

        jLabel98.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel98.setText("Fv,Rk = resistência característica de uma seção de corte por parafuso");
        Resultado.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 450, -1));

        jLabel99.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel99.setText("Rk = resistência característica da ligação considerando as quantidades de seções de corte e parafusos. ");
        Resultado.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 670, -1));

        jLabel100.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel100.setText("Rd = resistência de cálculo da ligação.");
        Resultado.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 670, -1));

        LabelEspessuraChapa.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Resultado.add(LabelEspessuraChapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 330, 20));

        Next3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/telas/ImagensBotao/Next.png"))); // NOI18N
        Next3.setToolTipText("Clique para avançar.");
        Next3.setEnabled(false);
        Next3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Next3ActionPerformed(evt);
            }
        });
        Resultado.add(Next3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 480, 40, -1));

        jTabbedPane1.addTab("Resultado", Resultado);

        Relatorio.setBackground(new java.awt.Color(204, 204, 204));
        Relatorio.setEnabled(false);

        RelatorioFinal.setBackground(new java.awt.Color(255, 255, 255));
        RelatorioFinal.setPreferredSize(new java.awt.Dimension(760, 1000));
        RelatorioFinal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("ELEMENTOS DA LIGAÇÃO");
        RelatorioFinal.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 220, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setText("RELATÓRIO DE RESISTÊNCIA DA LIGAÇÃO ");
        RelatorioFinal.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 39, -1, -1));

        RelatorioSecaoCorte.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioSecaoCorte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RelatorioFinal.add(RelatorioSecaoCorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 80, 250, 14));
        RelatorioFinal.add(Relatoriofcok4, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 658, -1, -1));

        RelatorioElem1.setBackground(new java.awt.Color(255, 255, 255));
        RelatorioElem1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RelatorioElem1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Madeira");
        RelatorioElem1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 5, 80, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("fc0,k:");
        RelatorioElem1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel16.setText("fv0,k:");
        RelatorioElem1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel18.setText("Ec0,m:");
        RelatorioElem1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel19.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel19.setText("Densidade:");
        RelatorioElem1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 70, -1));

        RelatorioDensidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioDensidade1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioDensidade1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(RelatorioDensidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 50, 14));

        RelatorioEc0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioEc0m1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioEc0m1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(RelatorioEc0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 50, 14));

        Relatoriofv0k1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriofv0k1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Relatoriofv0k1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(Relatoriofv0k1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 50, 14));

        Relatoriofcok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriofcok1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Relatoriofcok1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(Relatoriofcok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 50, 14));

        jLabel20.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel20.setText("(MPa)");
        RelatorioElem1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 40, -1));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel21.setText("(MPa)");
        RelatorioElem1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 40, -1));

        jLabel22.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel22.setText("(MPa)");
        RelatorioElem1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 40, -1));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel23.setText("(kg/m³)");
        RelatorioElem1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 40, -1));
        RelatorioElem1.add(RelatorioClasseElem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 5, 40, 14));

        jLabel51.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel51.setText("Espessura:");
        RelatorioElem1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        RelatorioEspessura1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioEspessura1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(RelatorioEspessura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 50, 14));

        jLabel69.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel69.setText("(mm)");
        RelatorioElem1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 40, -1));

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        RelatorioElem1.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 20, 90));

        jLabel25.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel25.setText("fy,k:");
        RelatorioElem1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, 14));

        RelatorioChapaFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioChapaFyk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(RelatorioChapaFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 35, 14));

        jLabel29.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel29.setText("(MPa)");
        RelatorioElem1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        RelatorioChapaFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioChapaFuk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(RelatorioChapaFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 35, 14));

        jLabel26.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel26.setText("fu,k:");
        RelatorioElem1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, -1));

        jLabel30.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel30.setText("(MPa)");
        RelatorioElem1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        jLabel52.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel52.setText("Espessura:");
        RelatorioElem1.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        RelatorioChapaEspessura.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioChapaEspessura.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioElem1.add(RelatorioChapaEspessura, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 35, 14));

        jLabel70.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel70.setText("(mm)");
        RelatorioElem1.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setText("Aço");
        RelatorioElem1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 5, -1, -1));

        LabelTipoChapaAco.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        LabelTipoChapaAco.setText("ASTM A36");
        RelatorioElem1.add(LabelTipoChapaAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 5, 60, 14));

        RelatorioFinal.add(RelatorioElem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 320, 120));

        RelatorioCoeficientes.setBackground(new java.awt.Color(255, 255, 255));
        RelatorioCoeficientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RelatorioCoeficientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel88.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel88.setText("(°)");
        RelatorioCoeficientes.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        Relatoriokmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriokmod3.setText("Densidade:");
        RelatorioCoeficientes.add(Relatoriokmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 35, 14));

        RelatorioAngulo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioAngulo.setText("De");
        RelatorioCoeficientes.add(RelatorioAngulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 25, 15));

        RelatorioKmod1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioKmod1.setText("jLabel20");
        RelatorioCoeficientes.add(RelatorioKmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 35, 14));

        Relatoriokmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Relatoriokmod2.setText("Densidade:");
        RelatorioCoeficientes.add(Relatoriokmod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 35, 14));

        jLabel90.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel90.setText("kmod 3:");
        RelatorioCoeficientes.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 50, -1));

        jLabel91.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel91.setText("kmod 2");
        RelatorioCoeficientes.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 50, -1));

        jLabel92.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel92.setText("Ângulo:");
        RelatorioCoeficientes.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 50, -1));

        jLabel93.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel93.setText("Coeficientes");
        RelatorioCoeficientes.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 5, -1, -1));

        jLabel94.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel94.setText("kmod 1:");
        RelatorioCoeficientes.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 50, -1));

        jLabel35.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel35.setText("γm,ligação:");
        RelatorioCoeficientes.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        jLabel81.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel81.setText("1,4");
        RelatorioCoeficientes.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        RelatorioFinal.add(RelatorioCoeficientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 170, 270, 120));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("ELEMENTOS METÁLICOS");
        RelatorioFinal.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 305, 200, -1));

        RelatorioParafuso.setBackground(new java.awt.Color(255, 255, 255));
        RelatorioParafuso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RelatorioParafuso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel33.setText("Parafuso");
        RelatorioParafuso.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));

        jLabel34.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel34.setText("Tipo de Parafuso:");
        RelatorioParafuso.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, 14));

        RelatorioTipoParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioTipoParafuso.setText("jLabel35");
        RelatorioParafuso.add(RelatorioTipoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 230, -1));

        jLabel36.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel36.setText("d:");
        RelatorioParafuso.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 20, 14));

        RelatorioDiametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioDiametro.setText("jLabel37");
        RelatorioParafuso.add(RelatorioDiametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 35, 14));

        jLabel37.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel37.setText("Número de Parafusos:");
        RelatorioParafuso.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 120, -1));

        RelatorioNParafusos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioParafuso.add(RelatorioNParafusos, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 25, 14));

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
        RelatorioParafuso.add(FiguraParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 110, 30));

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
        RelatorioParafuso.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 10, 14));

        RelatorioFinal.add(RelatorioParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 335, 320, 135));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel47.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel47.setText("Arruela");
        jPanel1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));

        jLabel48.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel48.setText("Tipo de arruela:");
        jPanel1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel49.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel49.setText("d1:");
        jPanel1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 20, 14));

        jLabel50.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel50.setText("d2:");
        jPanel1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        RelatorioTipoArruela.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioTipoArruela.setText("jLabel51");
        jPanel1.add(RelatorioTipoArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        RelatorioD1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioD1.setText("jLabel51");
        jPanel1.add(RelatorioD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 110, 35, 14));

        RelatorioD2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioD2.setText("jLabel51");
        jPanel1.add(RelatorioD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 35, 14));

        FiguraArruela.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        FiguraArruela.setText("jLabel74");
        jPanel1.add(FiguraArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 100, 50));

        jLabel75.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel75.setText("(mm)");
        jPanel1.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 30, -1));

        jLabel76.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel76.setText("(mm)");
        jPanel1.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 40, -1));

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 20, 20));

        RelatorioFinal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 335, 270, 135));
        RelatorioFinal.add(RelatorioFibras, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 313, -1, -1));

        jLabel44.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel44.setText("VALORES  CALCULADOS");
        RelatorioFinal.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 485, 200, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel53.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel53.setText("Cálculos Preliminares");
        jPanel2.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));

        CalculoForca01.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        CalculoForca01.setText("fe0,k1:");
        jPanel2.add(CalculoForca01, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 40, -1));

        jLabel58.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel58.setText("Fax,rk:");
        jPanel2.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 40, -1));

        RelatorioFc0d1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioFc0d1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioFc0d1.setText("jLabel59");
        jPanel2.add(RelatorioFc0d1, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 25, 40, -1));

        RelatorioFaxrk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioFaxrk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioFaxrk.setText("jLabel59");
        jPanel2.add(RelatorioFaxrk, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 45, 50, -1));

        jLabel77.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel77.setText("(N)");
        jPanel2.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 45, 30, -1));

        jLabel78.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel78.setText("(MPa)");
        jPanel2.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 25, 40, -1));

        CalculoForcaAlfa1.setText("feα,k1:");
        jPanel2.add(CalculoForcaAlfa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 40, -1));

        CalculoForca901.setText("fe90,k1:");
        jPanel2.add(CalculoForca901, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 50, -1));

        RelatorioFinal.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 505, 180, 65));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel59.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel59.setText("Cálculo do Eurocode 5");
        jPanel3.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 5, -1, -1));

        RelatorioRd11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd11.setText("Fv,k1:");
        jPanel3.add(RelatorioRd11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, 40, -1));

        RelatorioRd13.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd13.setText("Fv,k2:");
        jPanel3.add(RelatorioRd13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 45, 40, -1));

        RelatorioFvk1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioFvk1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioFvk1.setText("jLabel63");
        jPanel3.add(RelatorioFvk1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 45, 50, -1));

        RelatorioFvk2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioFvk2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioFvk2.setText("jLabel63");
        jPanel3.add(RelatorioFvk2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 45, 50, -1));

        RelatorioFvk3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioFvk3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioFvk3.setText("jLabel63");
        jPanel3.add(RelatorioFvk3, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 45, 50, -1));

        jLabel56.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel56.setText("Myk:");
        jPanel3.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 25, 30, -1));

        RelatorioMyd.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioMyd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioMyd.setText("jLabel59");
        jPanel3.add(RelatorioMyd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 25, 50, -1));

        RelatorioRd23.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd23.setText("Fv,k3:");
        jPanel3.add(RelatorioRd23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 45, 40, -1));

        jLabel55.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel55.setText("(N)");
        jPanel3.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 45, 30, -1));

        jLabel62.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel62.setText("(N)");
        jPanel3.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 45, 30, -1));

        jLabel63.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel63.setText("(N)");
        jPanel3.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 45, -1, -1));

        jLabel64.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel64.setText("(N.mm)");
        jPanel3.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 25, 40, -1));

        LabelInter1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        LabelInter1.setText("Fv,kmin1:");
        jPanel3.add(LabelInter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, -1, -1));

        LabelInt2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        LabelInt2.setText("Fv,kmin2:");
        jPanel3.add(LabelInt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 45, -1, -1));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 45, 10, 15));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 45, 10, 15));

        RelatorioFinal.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 505, 410, 65));

        jLabel67.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel67.setText("RESULTADO:");
        RelatorioFinal.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 605, -1, -1));

        ModoFalha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RelatorioFinal.add(ModoFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 170, 140));

        jLabel87.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel87.setText("Tipo de ruptura:");
        RelatorioFinal.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 625, -1, -1));

        jLabel95.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel95.setText("Rk:");
        RelatorioFinal.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 690, 70, -1));

        RelatorioRvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioFinal.add(RelatorioRvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 690, 70, 15));
        RelatorioFinal.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 600, 10));
        RelatorioFinal.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 600, 10));

        FiguraSecoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RelatorioFinal.add(FiguraSecoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 80, 70));

        Data.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Data.setText("jLabel35");
        RelatorioFinal.add(Data, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 840, 80, -1));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        Consideracao1.setEditable(false);
        Consideracao1.setColumns(20);
        Consideracao1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Consideracao1.setLineWrap(true);
        Consideracao1.setRows(5);
        Consideracao1.setText("*Fv,Rk = resistência característica de uma seção de corte por parafuso.\n*Rk = resistência característica da ligação considerando as quantidades de seções de corte e parafusos.\n*Rd = resistência de cálculo da ligação.");
        Consideracao1.setWrapStyleWord(true);
        Consideracao1.setBorder(null);
        jScrollPane2.setViewportView(Consideracao1);

        RelatorioFinal.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 780, 570, 50));

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

        RelatorioFinal.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 620, 310, 30));

        Hora.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Hora.setText("jLabel65");
        RelatorioFinal.add(Hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 840, -1, -1));
        RelatorioFinal.add(RLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, 80));

        jLabel66.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel66.setText("Fv,Rk:");
        RelatorioFinal.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 670, 50, -1));

        RelatorioFvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioFvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioFvk.setText("jLabel67");
        RelatorioFinal.add(RelatorioFvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 670, 70, -1));

        jLabel80.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel80.setText("Rd:");
        RelatorioFinal.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 710, 50, -1));

        RelatorioRd.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioRd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RelatorioRd.setText("jLabel81");
        RelatorioFinal.add(RelatorioRd, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 710, 70, -1));

        jLabel82.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel82.setText("(N)");
        RelatorioFinal.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 710, -1, -1));

        jLabel83.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel83.setText("(N)");
        RelatorioFinal.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 670, -1, -1));

        jLabel84.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel84.setText("(N)");
        RelatorioFinal.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 690, -1, -1));

        LabelRelatorioChapaAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        RelatorioFinal.add(LabelRelatorioChapaAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 100, 250, 14));
        RelatorioFinal.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 600, 10));

        jScrollPane1.setViewportView(RelatorioFinal);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/telas/ImagensBotao/Print.png"))); // NOI18N
        jButton1.setToolTipText("Clique para imprimir o relatório.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/telas/ImagensBotao/New.png"))); // NOI18N
        jButton2.setToolTipText("Clique para realizar novo cálculo.\n");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/telas/ImagensBotao/Return.png"))); // NOI18N
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
                .addGroup(RelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RelatorioLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Relatório", Relatorio);

        jTabbedPane1.setSelectedComponent(Inicio);
        Relatorio.setVisible(false);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.NORTH);

        getAccessibleContext().setAccessibleName("ProgramaMarcos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCalcularActionPerformed
        // COMANDOS PARA AVISAR QUE FALTAM DADOS PARA REALIZAR A LIGAÇÃO
        //programa
        Espessura1.getInputVerifier().shouldYieldFocus(Espessura1);
        ComboChapaAco.getInputVerifier().shouldYieldFocus(ComboChapaAco);
        ValorAngulo.getInputVerifier().shouldYieldFocus(ValorAngulo);
        ComboElem1.getInputVerifier().shouldYieldFocus(ComboElem1);
        ComboTipoChapaAco.getInputVerifier().shouldYieldFocus(ComboTipoChapaAco);
        ComboKmod1.getInputVerifier().shouldYieldFocus(ComboKmod1);
        ComboKmod2.getInputVerifier().shouldYieldFocus(ComboKmod2);
        ComboKmod3.getInputVerifier().shouldYieldFocus(ComboKmod3);
        ComboQuantParafuso.getInputVerifier().shouldYieldFocus(ComboQuantParafuso);
        ComboTipoParafuso.getInputVerifier().shouldYieldFocus(ComboTipoParafuso);
        ComboAco.getInputVerifier().shouldYieldFocus(ComboAco);
        ComboArruelasAco.getInputVerifier().shouldYieldFocus(ComboArruelasAco);
        ComboArruelasMadeira.getInputVerifier().shouldYieldFocus(ComboArruelasMadeira);
        
        
        if (ButtonCalcular.hasFocus()){
        
    
        kmod1 = Double.parseDouble(TextKmod1.getText().replace(",", "."));
        kmod2 = Double.parseDouble(TextKmod2.getText().replace(",", "."));
        kmod3 = Double.parseDouble(TextKmod3.getText().replace(",", "."));
        
        
        Data.setText(objDataHora.MostraData());
        Hora.setText(objDataHora.MostraHora());
        
        FiguraArruela.setIcon(new ImageIcon(((ImageIcon)ImagemTipoArruela.getIcon()).getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH)));
        
      
        LabelInter1.setVisible(false);
        LabelInt2.setVisible(false);
        jLabelStatusAco.setText("Clique em avançar para continuar.");
        jTabbedPane1.setSelectedComponent(Resultado);
        jTabbedPane1.setEnabledAt(4, true);
        Next3.setEnabled(true);
        
        //Entradas madeiras
        double fc0k1 = Integer.parseInt(ValorFc01.getText());
        int fv0k1 = Integer.parseInt(ValorFvok1.getText());
        int ec0m1 = Integer.parseInt(ValorEc0m1.getText());
        double t1 = Integer.parseInt(Espessura1.getText());
        double chapafyk = Integer.parseInt(ValorChapaFyk.getText());
        double chapafuk = Integer.parseInt(ValorChapaFuk.getText());
        double angulograu = Integer.parseInt(ValorAngulo.getText());
        double angulo = angulograu*((Math.PI)/180);
        double falfa1 = 0.0;
        
        RelatorioAngulo.setText(String.format("%.1f", angulo));
        
        
        if (npar > 8){
            nparafusos = (8.0 +((2.0/3.0)*(npar - 8.0)));
        }
        else{
            nparafusos = npar;
        }
        
        //Entradas coeficientes
        kmod1 = Double.parseDouble(TextKmod1.getText().replace(",", "."));
        kmod2 = Double.parseDouble(TextKmod2.getText().replace(",", "."));
        kmod3 = Double.parseDouble(TextKmod3.getText().replace(",", "."));
        
        
        RelatorioKmod1.setText(TextKmod1.getText());
        Relatoriokmod2.setText(TextKmod2.getText());
        Relatoriokmod3.setText(TextKmod3.getText());
        RelatorioEspessura1.setText(Espessura1.getText());
        
        
        // ELEMENTOS PARA VERIFICAR O QUE O PROGRAMA ESTÁ FAZENDO - RELACIONADO A OBTENÇÃO DE DADOS
        System.out.println("Fc0k1=" + fc0k1);
        System.out.println("Fv0k1=" + fv0k1);
        System.out.println("Ec0m1=" + ec0m1);
        System.out.println("t1=" + t1);
        System.out.println("Chapa Fyk=" + chapafyk);
        System.out.println("Chapa Fuk=" + chapafuk);
        System.out.println("kmod1=" + kmod1);
        System.out.println("kmod2=" + kmod2);
        System.out.println("kmod3=" + kmod3);
        
        //RelatorioFcok.setText(""+fc0k1);

        //Entradas Parafusos
        d = Double.parseDouble(ValorDiametro.getText().replace(",", "."));
        int fyk = Integer.parseInt(ValorFyk.getText());
        int fuk = Integer.parseInt(ValorFuk.getText());
//        int nparafusos = npar;;
        d1 = Double.parseDouble(ValorD1Arruelas.getText().replace(",", "."));
        d2 = Double.parseDouble(ValorD2Arruelas.getText().replace(",", "."));

        double fek90 = (0.25*fc0k1*alfa);
        double fekalfa = ((fc0k1*fek90)/((fc0k1*(Math.pow((Math.sin(angulo)), 2)))+((fek90*(Math.pow((Math.cos(angulo)), 2))))));
        double fek = 0.0;
        if (angulograu == 0){
            fek = fc0k1;
        }
        if (angulograu == 90){
            fek = fek90;
        }
        if (angulograu > 0 && angulograu < 90){
            fek = fekalfa;
        }
        
        
        RelatorioFc0d1.setText(String.format("%.1f", fek));

        
        // ELEMENTOS PARA VERIFICAR O QUE O PROGRAMA ESTÁ FAZENDO - RELACIONADO A OBTENÇÃO DE DADOS
        System.out.println("d=" + d);
        System.out.println("Numero Parafusos=" + nparafusos);
        System.out.println("Fyk=" + fyk);
        System.out.println("Fuk=" + fuk);
        System.out.println("d1=" + d1);
        System.out.println("d2=" + d2);

        
        String ImagemFalha = "";
        String ImagemFalha2 = "";
        String ImagemFalhaFinal = "";
        String Tipofinal = "";
        
        
        //Verificação da chapa grossa ou fina
        if (t2 <= (0.5*d)){
            fina = true;
            
        }
        
        if (t2 >= d){
            grossa = true;
            
        }
        
        if ( t2 > (0.5*d) && t2 < d){
            indefinida = true;
            
        }
        
        //INICIA-SE O CÁLCULO DA LIGAÇÃO PARA UMA SEÇÃO DE CORTE FINA
        if (ss && fina){
            UmaSecaoCorteFinaController resultado = new UmaSecaoCorteFinaController();//Pegar os valores das outras classes
            //Considerar ou não o valor de FaxRk
            if (m){
                valorFaxrk = resultado.ValorFaxrk(d, fuk);
            }
            RelatorioFaxrk.setText(String.format("%.0f", valorFaxrk));
            
            double Myk = resultado.ValorMyk(fuk, d);
            fvk1 = resultado.EmbPecaMadeira(fek, t1, d);
            fvk2 = resultado.DeformPinoPecaMadeira(fek, d, Myk, valorFaxrk);
            System.out.println("Abriu = 1 Seção de Corte com Chapa Fina!");
            System.out.println("Faxrk =" + valorFaxrk);
            System.out.println("Myk =" + Myk);
            System.out.println("Fvk1 =" +fvk1);
            System.out.println("Fvk2 =" +fvk2);

            //Verificar o menor valor de resistência
            Fvkmin = fvk1;
            Tipofinal = "Embutimento do pino metálico no elemento de madeira.";
            ImagemFalhaFinal = "UmaSecaoEmbutimentoMadeira.png";
            if (fvk2<Fvkmin){
                Fvkmin = fvk2;
                Tipofinal = "Flexão do pino metálico com ocorrência de uma rótula plástica no elemento de madeira.";
                ImagemFalhaFinal = "UmaSecaoFlexaoPinoMadeira.png";
            }

            System.out.println("Fvkmin =" +Fvkmin);
            System.out.println("Tipo =" +Tipofinal);
            LabelResultadoModeloFalha.setText(Tipofinal);
            LabelEspessuraChapa.setText("Ligação com chapa fina.");
            LabelRelatorioChapaAco.setText("Ligação com chapa fina.");
            
           
            //Calcular o valor total da ligação
            Rvk = Fvkmin*1*nparafusos;
            Rvd = ((Rvk*kmod1*kmod2*kmod3)/1.4);

            System.out.println("Rvk =" +Rvk);
            ResultadoFvk.setText(String.format("%.0f", Fvkmin));
            ResultadoRvk.setText(String.format("%.0f", Rvk));
            ResultadoRd.setText(String.format("%.0f", Rvd));
            RelatorioMyd.setText(String.format("%.0f", Myk));
            RelatorioFvk1.setText(String.format("%.0f", fvk1));
            RelatorioFvk3.setText(String.format("%.0f", fvk2));
            RelatorioFvk.setText(String.format("%.0f", Fvkmin));
            RelatorioRvk.setText(String.format("%.0f", Rvk));
            RelatorioRd.setText(String.format("%.0f", Rvd));

        RelatorioRd11.setVisible(true);
        RelatorioRd13.setVisible(false);
        RelatorioRd23.setVisible(true);
        RelatorioFvk1.setVisible(true);
        RelatorioFvk2.setVisible(false);
        RelatorioFvk3.setVisible(true);
        jLabel55.setVisible(true);
        jLabel62.setVisible(false);
        jLabel63.setVisible(true);
        jScrollPane2.setVisible(true);

        
        //PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO


        teste.setText(Tipofinal);
        }
        
        //INICIA-SE O CÁLCULO DA LIGAÇÃO PARA UMA SEÇÃO DE CORTE GROSSA

        if (ss && grossa){
            UmaSecaoCorteGrossaController resultado = new UmaSecaoCorteGrossaController();//Pegar os valores das outras classes
            //Considerar ou não o valor de FaxRk
           // jLabel86.setIcon();
            if (m){
                valorFaxrk = resultado.ValorFaxrk(d, fuk);
            }
            RelatorioFaxrk.setText(String.format("%.0f", valorFaxrk));
            
            double Myk = resultado.ValorMyk(fuk, d);
            double fvk3 = resultado.EmbPecaMadeira(fek, d, t1, valorFaxrk, Myk);
            double fvk4 = resultado.DeformPino(fek, d, Myk, valorFaxrk);
            double fvk5 = resultado.EmbPecaMadeira5(fek, t1, d);
            System.out.println("Abriu = 1 Seção de Corte Grossa!");
            System.out.println("Faxrk =" + valorFaxrk);
            System.out.println("Myk =" + Myk);
            System.out.println("Fvk3 =" +fvk3);
            System.out.println("Fvk4 =" +fvk4);
            System.out.println("Fvk5 =" +fvk5);

            //Verificar o menor valor de resistência
            Fvkmin = fvk3;
            Tipofinal = "Flexão do pino metálico com ocorrência de uma rótula plástica no elemento de madeira.";
            ImagemFalhaFinal = "UmaSecaoFlexaoPinoAco.png";
            if (fvk4<Fvkmin){
                Fvkmin = fvk4;
                Tipofinal = "Flexão do pino metálico com ocorrência de duas rótulas plásticas no elemento de madeira.";
                ImagemFalhaFinal = "UmaSecaoDuasFlexoesPino.png";
            }
            if (fvk5<Fvkmin){
                Fvkmin = fvk5;
                Tipofinal = "Embutimento do pino metálico no elemento de madeira.";
                ImagemFalhaFinal = "UmaSecaoEmbutimentoMadeiraGrossa.png";
            }
            
            System.out.println("Fvkmin =" +Fvkmin);
            System.out.println("Tipo =" +Tipofinal);
            LabelResultadoModeloFalha.setText(Tipofinal);
            LabelEspessuraChapa.setText("Ligação com chapa grossa.");
            LabelRelatorioChapaAco.setText("Ligação com chapa grossa.");
            
        

            //Calcular o valor total da ligação
            Rvk = Fvkmin*1*nparafusos;
            Rvd = ((Rvk*kmod1*kmod2*kmod3)/1.4);

            System.out.println("Rvk =" +Rvk);
            ResultadoFvk.setText(String.format("%.0f", Fvkmin));
            ResultadoRvk.setText(String.format("%.0f", Rvk));
            ResultadoRd.setText(String.format("%.0f", Rvd));
            RelatorioMyd.setText(String.format("%.0f", Myk));
            RelatorioFvk1.setText(String.format("%.0f", fvk3));
            RelatorioFvk2.setText(String.format("%.0f", fvk4));
            RelatorioFvk3.setText(String.format("%.0f", fvk5));
            RelatorioFvk.setText(String.format("%.0f", Fvkmin));
            RelatorioRvk.setText(String.format("%.0f", Rvk));
            RelatorioRd.setText(String.format("%.0f", Rvd));

        RelatorioRd11.setVisible(true);
        RelatorioRd13.setVisible(true);
        RelatorioRd23.setVisible(true);
        RelatorioFvk1.setVisible(true);
        RelatorioFvk2.setVisible(true);
        RelatorioFvk3.setVisible(true);
        jLabel55.setVisible(true);
        jLabel62.setVisible(true);
        jLabel63.setVisible(true);
        jScrollPane2.setVisible(true);

           
        //PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO (ALTERAR DEPOIS)


        teste.setText(Tipofinal);
        }
        
           
        //INICIA-SE O CÁLCULO DA LIGAÇÃO PARA DUAS SEÇÕES COM CHAPA CENTRAL
        if (sdmam){
            DuasSecoesChapaCentralController resultado = new DuasSecoesChapaCentralController();//Pegar os valores das outras classes
            //Considerar ou não o valor de FaxRk
            if (m){
                valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fek, arruela);
            }
            RelatorioFaxrk.setText(String.format("%.0f", valorFaxrk));
            
            double Myk = resultado.ValorMyk(fuk, d);
            fvk6 = resultado.EmbMadeira(fek, t1, d);
            fvk7 = resultado.DeformUmaRotula(fek, d, t1, valorFaxrk, Myk);
            fvk8 = resultado.DeformDuasRotulas(fek, d, Myk, valorFaxrk);
            System.out.println("Abriu = Duas seções de corte com Chapa de Aço Central!");
            System.out.println("Faxrk =" + valorFaxrk);
            System.out.println("Myk =" + Myk);
            System.out.println("Fvk6 =" +fvk6);
            System.out.println("Fvk7 =" +fvk7);
            System.out.println("Fvk8 =" +fvk8);


            //Verificar o menor valor de resistência
            Fvkmin = fvk6;
            Tipofinal = "Embutimento do pino metálico nos elementos de madeira.";
            ImagemFalhaFinal = "DuasSecoesEmbutimentoMadeiraMam.png";
            if (fvk7<Fvkmin){
                Fvkmin = fvk7;
                Tipofinal = "Flexão dos pinos metálicos com uma rótula plástica no elemento de aço.";
                ImagemFalhaFinal = "DuasSecoesFlexaoPinoAcoMam.png";

            }
            if (fvk8<Fvkmin){
                Fvkmin = fvk8;
                Tipofinal = "Flexão dos pinos metálicos com duas rótulas plásticas nos elementos de madeira.";
                ImagemFalhaFinal = "DuasSecoesFlexaoDuplaPinoMam.png";
            }

            System.out.println("Fvkmin =" +Fvkmin);
            System.out.println("Tipo =" +Tipofinal);
            LabelResultadoModeloFalha.setText(Tipofinal);
            

            //Calcular o valor total da ligação
            Rvk = Fvkmin*2*nparafusos;
            Rvd = ((Rvk*kmod1*kmod2*kmod3)/1.4);

            System.out.println("Rvk =" +Rvk);
            ResultadoFvk.setText(String.format("%.0f", Fvkmin));
            ResultadoRvk.setText(String.format("%.0f", Rvk));
            ResultadoRd.setText(String.format("%.0f", Rvd));
            RelatorioMyd.setText(String.format("%.0f", Myk));
            RelatorioFvk1.setText(String.format("%.0f", fvk6));
            RelatorioFvk2.setText(String.format("%.0f", fvk7));
            RelatorioFvk3.setText(String.format("%.0f", fvk8));
            RelatorioFvk.setText(String.format("%.0f", Fvkmin));
            RelatorioRvk.setText(String.format("%.0f", Rvk));
            RelatorioRd.setText(String.format("%.0f", Rvd));

        RelatorioRd11.setVisible(true);
        RelatorioRd13.setVisible(true);
        RelatorioRd23.setVisible(true);
        RelatorioFvk1.setVisible(true);
        RelatorioFvk2.setVisible(true);
        RelatorioFvk3.setVisible(true);
        jLabel55.setVisible(true);
        jLabel62.setVisible(true);
        jLabel63.setVisible(true);
        jScrollPane2.setVisible(true);

        
        //PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO

        teste.setText(Tipofinal);
        }
        
        //INICIA-SE O CÁLCULO DA LIGAÇÃO PARA DUAS SEÇÕES COM CHAPA FINA LATERAL
        if (sdama && fina){
            DuasSecoesChapaFinaLateralController resultado = new DuasSecoesChapaFinaLateralController(); //Pegar os valores das outras classes
            //Considerar ou não o valor de FaxRk

            if (m){
                valorFaxrk = resultado.ValorFaxrk(d, fuk);
            }
            RelatorioFaxrk.setText(String.format("%.0f", valorFaxrk));
            
            //double FaxRk = resultado.ValorFaxrk(d, fyk, d2, d1, fe0d1);
            double Myk = resultado.ValorMyk(fuk, d);
            fvk9 = resultado.EmbMadeira(fek, t1, d);
            fvk10 = resultado.DeformUmaRotula(fek, d, Myk, valorFaxrk);
            System.out.println("Abriu = 2 Seções de corte com Chapa Lateral Fina!");
            System.out.println("Faxrk =" + valorFaxrk);
            System.out.println("Myk =" + Myk);
            System.out.println("Fvk9 =" +fvk9);
            System.out.println("Fvk10 =" +fvk10);

            //Verificar o menor valor de resistência
            Fvkmin = fvk9;
            Tipofinal = "Embutimento do pino metálico no elemento de madeira.";
            ImagemFalhaFinal = "DuasSecoesEmbutimentoMadeiraAma.png";
            if (fvk10<Fvkmin){
                Fvkmin = fvk10;
                Tipofinal = "Flexão dos pinos metálicos com duas rótulas plásticas nos elementos de aço.";
                ImagemFalhaFinal = "DuasSecoesFlexaoPinoAcoAma.png";
            }

            System.out.println("Fvkmin =" +Fvkmin);
            System.out.println("Tipo =" +Tipofinal);
            LabelResultadoModeloFalha.setText(Tipofinal);
            LabelEspessuraChapa.setText("Ligação com chapa fina");
            LabelRelatorioChapaAco.setText("Ligação com chapa fina");
            

        

            //Calcular o valor total da ligação
            Rvk = Fvkmin*2*nparafusos;
            Rvd = ((Rvk*kmod1*kmod2*kmod3)/1.4);

            System.out.println("Rvk =" +Rvk);
            ResultadoFvk.setText(String.format("%.0f", Fvkmin));
            ResultadoRvk.setText(String.format("%.0f", Rvk));
            ResultadoRd.setText(String.format("%.0f", Rvd));
            RelatorioMyd.setText(String.format("%.0f", Myk));
            RelatorioFvk1.setText(String.format("%.0f", fvk9));
            RelatorioFvk3.setText(String.format("%.0f", fvk10));
            RelatorioFvk.setText(String.format("%.0f", Fvkmin));
            RelatorioRvk.setText(String.format("%.0f", Rvk));
            RelatorioRd.setText(String.format("%.0f", Rvd));

        RelatorioRd11.setVisible(true);
        RelatorioRd13.setVisible(false);
        RelatorioRd23.setVisible(true);
        RelatorioFvk1.setVisible(true);
        RelatorioFvk2.setVisible(false);
        RelatorioFvk3.setVisible(true);
        jLabel55.setVisible(true);
        jLabel62.setVisible(false);
        jLabel63.setVisible(true);
        jScrollPane2.setVisible(true);
        

        //PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO


        teste.setText(Tipofinal);
        }
        
       //INICIA-SE O CÁLCULO DA LIGAÇÃO PARA DUAS SEÇÕES COM CHAPA GROSSA LATERAL
        if (sdama && grossa){
            DuasSecoesChapaGrossaLateralController resultado = new DuasSecoesChapaGrossaLateralController(); //Pegar os valores das outras classes
            //Considerar ou não o valor de FaxRk

            if (m){
                valorFaxrk = resultado.ValorFaxrk(d, fuk);
            }
            RelatorioFaxrk.setText(String.format("%.0f", valorFaxrk));
            
            double Myk = resultado.ValorMyk(fuk, d);
            fvk11 = resultado.EmbMadeira(fek, t1, d);
            fvk12 = resultado.DeformDuasRotulas(fek, d, Myk, valorFaxrk);
            System.out.println("Abriu = 2 Seções de corte com chapa de aço grossa e lateral!");
            System.out.println("Faxrk =" + valorFaxrk);
            System.out.println("Myk =" + Myk);
            System.out.println("Fvk11 =" +fvk11);
            System.out.println("Fvk12 =" +fvk12);

            //Verificar o menor valor de resistência
            Fvkmin = fvk11;
            Tipofinal = "Embutimento do pino metálico no elemento de madeira.";
            ImagemFalhaFinal = "DuasSecoesEmbutimentoMadeiraAma.png";
            if (fvk12<Fvkmin){
                Fvkmin = fvk12;
                Tipofinal = "Flexão dos pinos metálicos com duas rótulas plásticas no elemento de madeira.";
                ImagemFalhaFinal = "DuasSecoesFlexaoPinosDuplaAma.png";
            }

            System.out.println("Fvkmin =" +Fvkmin);
            System.out.println("Tipo =" +Tipofinal);
            LabelResultadoModeloFalha.setText(Tipofinal);
            LabelEspessuraChapa.setText("Ligação com chapa grossa.");
            LabelRelatorioChapaAco.setText("Ligação com chapa grossa.");
            

            //Calcular o valor total da ligação
            Rvk = Fvkmin*2*nparafusos;
            Rvd = ((Rvk*kmod1*kmod2*kmod3)/1.4);
                     
            System.out.println("Rvk =" +Rvk);
            ResultadoFvk.setText(String.format("%.0f", Fvkmin));
            ResultadoRvk.setText(String.format("%.0f", Rvk));
            ResultadoRd.setText(String.format("%.0f", Rvd));
            RelatorioMyd.setText(String.format("%.0f", Myk));
            RelatorioFvk1.setText(String.format("%.0f", fvk11));
            RelatorioFvk3.setText(String.format("%.0f", fvk12));
            RelatorioFvk.setText(String.format("%.0f", Fvkmin));
            RelatorioRvk.setText(String.format("%.0f", Rvk));
            RelatorioRd.setText(String.format("%.0f", Rvd));

        RelatorioRd11.setVisible(true);
        RelatorioRd13.setVisible(false);
        RelatorioRd23.setVisible(true);
        RelatorioFvk1.setVisible(true);
        RelatorioFvk2.setVisible(false);
        RelatorioFvk3.setVisible(true);
        jLabel55.setVisible(true);
        jLabel62.setVisible(false);
        jLabel63.setVisible(true);
        jScrollPane2.setVisible(true);


        //PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO

        teste.setText(Tipofinal);
        }
        
        //INICIA-SE O CÁLCULO COM INTERPOLAÇÃO UMA SEÇÃO
        if (ss && indefinida){
            LabelRelatorioChapaAco.setText("Cálculo realizado com interpolação linear.");
        
        //CALCULO PARA CHAPA FINA
            UmaSecaoCorteFinaController resultado = new UmaSecaoCorteFinaController();//Pegar os valores das outras classes
            //Considerar ou não o valor de FaxRk
            if (m){
                valorFaxrk = resultado.ValorFaxrk(d, fuk);
            }
            RelatorioFaxrk.setText(String.format("%.0f", valorFaxrk));
            
           
            
            double Myk = resultado.ValorMyk(fuk, d);
            fvk1 = resultado.EmbPecaMadeira(fek, t1, d);
            fvk2 = resultado.DeformPinoPecaMadeira(fek, d, Myk, valorFaxrk);
            System.out.println("Abriu = 1 Seção de Corte com Chapa Fina!");
            System.out.println("Faxrk =" + valorFaxrk);
            System.out.println("Myk =" + Myk);
            System.out.println("Fvk1 =" +fvk1);
            System.out.println("Fvk2 =" +fvk2);

            //Verificar o menor valor de resistência
            Fvkmin = fvk1;
            String Tipo = "Embutimento do pino metálico no elemento de madeira.";
            ImagemFalha = "UmaSecaoEmbutimentoMadeira.png";
            if (fvk2<Fvkmin){
                Fvkmin = fvk2;
                Tipo = "Flexão do pino metálico com ocorrência de uma rótula plástica no elemento de madeira.";
                ImagemFalha = "UmaSecaoFlexaoPinoMadeira.png";
            }

            System.out.println("Fvkmin =" +Fvkmin);
            System.out.println("Tipo =" +Tipo);
            
        //CALCULO CHAPA GROSSA    
        UmaSecaoCorteGrossaController resultado2 = new UmaSecaoCorteGrossaController();//Pegar os valores das outras classes
            //Considerar ou não o valor de FaxRk
           // jLabel86.setIcon();
            if (m){
                valorFaxrk = resultado.ValorFaxrk(d, fuk);
            }
            
            double Mykgrossa = resultado2.ValorMyk(fuk, d);
            double fvk3 = resultado2.EmbPecaMadeira(fek, d, t1, valorFaxrk, Myk);
            double fvk4 = resultado2.DeformPino(fek, d, Myk, valorFaxrk);
            double fvk5 = resultado2.EmbPecaMadeira5(fek, t1, d);
            System.out.println("Abriu = 1 Seção de Corte Grossa!");
            System.out.println("Faxrk =" + valorFaxrk);
            System.out.println("Myk =" + Mykgrossa);
            System.out.println("Fvk3 =" +fvk3);
            System.out.println("Fvk4 =" +fvk4);
            System.out.println("Fvk5 =" +fvk5);

            //Verificar o menor valor de resistência
            Fvkmin2 = fvk3;
            String Tipogrossa = "Flexão do pino metálico com ocorrência de uma rótula plástica no elemento de madeira.";
            ImagemFalha2 = "UmaSecaoFlexaoPinoAco.png";
            if (fvk4<Fvkmin2){
                Fvkmin2 = fvk4;
                Tipogrossa = "Flexão do pino metálico com ocorrência de duas rótulas plásticas no elemento de madeira.";
                ImagemFalha2 = "UmaSecaoDuasFlexoesPino.png";
            }
            if (fvk5<Fvkmin2){
                Fvkmin2 = fvk5;
                Tipogrossa = "Embutimento do pino metálico no elemento de madeira.";
                ImagemFalha2 = "UmaSecaoEmbutimentoMadeiraGrossa.png";
            }
            
            System.out.println("Fvkmin =" +Fvkmin2);
            System.out.println("Tipo =" +Tipogrossa);
            
            //CALCULO DA INTERPOLAÇÃO
            double FvkMinimo = Fvkmin;
            double FvkMaximo = Fvkmin2;
            String TipoMinimo = Tipo;
            String TipoMaximo = Tipogrossa;
            String ImagemMinima = ImagemFalha;
            String ImagemMaxima = ImagemFalha2;
            
         
            
            
            if (Fvkmin2 < Fvkmin){
                FvkMinimo = Fvkmin2;
                FvkMaximo = Fvkmin;
                TipoMinimo = Tipogrossa;
                TipoMaximo = Tipo;
                ImagemMinima = ImagemFalha2;
                ImagemMaxima = ImagemFalha;
            }
            double Fvkfinal = 0.0;
            
            if (Fvkmin == Fvkmin2){
                Fvkfinal =Fvkmin;
                Tipofinal = TipoMinimo;
                ImagemFalhaFinal = ImagemMinima;
            }
            if (Fvkmin != Fvkmin2){
                Fvkfinal = ((((FvkMaximo - FvkMinimo)*(t2-(0.5*d)))/(0.5*d)))+ FvkMinimo;
                double Fvkmedio = (FvkMaximo - FvkMinimo)/2;
                if (Fvkfinal < (FvkMinimo+Fvkmedio)){
                    Tipofinal = TipoMinimo;
                    ImagemFalhaFinal = ImagemMinima;
                }
                if (Fvkfinal > (FvkMinimo+Fvkmedio)){
                    Tipofinal = TipoMaximo;
                    ImagemFalhaFinal = ImagemMaxima;
                }
            }

            
            LabelResultadoModeloFalha.setText(Tipofinal);
            LabelEspessuraChapa.setText("Cálculo realizado com interpolação linear");
            LabelRelatorioChapaAco.setText("Cálculo realizado com interpolação linear");
            
           
            //Calcular o valor total da ligação
            Rvk = Fvkfinal*1*nparafusos;
            Rvd = ((Rvk*kmod1*kmod2*kmod3)/1.4);
            

            System.out.println("Rvk =" +Rvk);
            ResultadoFvk.setText(String.format("%.0f", Fvkfinal));
            ResultadoRvk.setText(String.format("%.0f", Rvk));
            ResultadoRd.setText(String.format("%.0f", Rvd));
            RelatorioMyd.setText(String.format("%.0f", Myk));
            RelatorioFvk1.setText(String.format("%.0f", Fvkmin));
            RelatorioFvk3.setText(String.format("%.0f", Fvkmin2));
            RelatorioFvk.setText(String.format("%.0f", Fvkfinal));
            RelatorioRvk.setText(String.format("%.0f", Rvk));
            RelatorioRd.setText(String.format("%.0f", Rvd));

        RelatorioRd11.setVisible(false);
        RelatorioRd13.setVisible(false);
        RelatorioRd23.setVisible(false);
        LabelInter1.setVisible(true);
        LabelInt2.setVisible(true);
        RelatorioFvk1.setVisible(true);
        RelatorioFvk2.setVisible(false);
        RelatorioFvk3.setVisible(true);
        jLabel55.setVisible(true);
        jLabel62.setVisible(false);
        jLabel63.setVisible(true);
        jScrollPane2.setVisible(true);
        
        teste.setText(Tipofinal);
        } 
        
        
        //INICIA-SE O CÁLCULO COM INTERPOLAÇÃO DUAS SEÇÕES
        if (sdama && indefinida){
            LabelRelatorioChapaAco.setText("Cálculo realizado com interpolação linear");
                
        //CHAPA FINA
            DuasSecoesChapaFinaLateralController resultado = new DuasSecoesChapaFinaLateralController(); //Pegar os valores das outras classes
            //Considerar ou não o valor de FaxRk

            if (m){
                valorFaxrk = resultado.ValorFaxrk(d, fuk);
            }
            RelatorioFaxrk.setText(String.format("%.0f", valorFaxrk));
            
            //double FaxRk = resultado.ValorFaxrk(d, fyk, d2, d1, fe0d1);
            double Myk = resultado.ValorMyk(fuk, d);
            fvk9 = resultado.EmbMadeira(fek, t1, d);
            fvk10 = resultado.DeformUmaRotula(fek, d, Myk, valorFaxrk);
            System.out.println("Abriu = 2 Seções de corte com Chapa Lateral Fina!");
            System.out.println("Faxrk =" + valorFaxrk);
            System.out.println("Myk =" + Myk);
            System.out.println("Fvk9 =" +fvk9);
            System.out.println("Fvk10 =" +fvk10);

            //Verificar o menor valor de resistência
            Fvkmin = fvk9;
            String Tipo = "Embutimento do pino metálico no elemento de madeira.";
            ImagemFalha = "DuasSecoesEmbutimentoMadeiraAma.png";
            if (fvk10<Fvkmin){
                Fvkmin = fvk10;
                Tipo = "Flexão dos pinos metálicos com uma rótula plástica no elemento de madeira.";
                ImagemFalha = "DuasSecoesFlexaoPinoAcoAma.png";
            }

            System.out.println("Fvkmin =" +Fvkmin);
            System.out.println("Tipo =" +Tipo);
        
        //CHAPA GROSSA
            DuasSecoesChapaGrossaLateralController resultado2 = new DuasSecoesChapaGrossaLateralController(); //Pegar os valores das outras classes
            //Considerar ou não o valor de FaxRk

            if (m){
                valorFaxrk = resultado.ValorFaxrk(d, fuk);
                RelatorioFaxrk.setText(String.format("%.0f", valorFaxrk));
            }
    
            double Mykgrossa = resultado2.ValorMyk(fuk, d);
            fvk11 = resultado2.EmbMadeira(fek, t1, d);
            fvk12 = resultado2.DeformDuasRotulas(fek, d, Myk, valorFaxrk);
            System.out.println("Abriu = 2 Seções de corte com chapa de aço grossa e lateral!");
            System.out.println("Faxrk =" + valorFaxrk);
            System.out.println("Myk =" + Mykgrossa);
            System.out.println("Fvk11 =" +fvk11);
            System.out.println("Fvk12 =" +fvk12);

            //Verificar o menor valor de resistência
            Fvkmin2 = fvk11;
            String Tipogrossa = "Embutimento do pino metálico no elemento de madeira.";
            ImagemFalha2 = "DuasSecoesEmbutimentoMadeiraAma.png";
            if (fvk12<Fvkmin2){
                Fvkmin2 = fvk12;
                Tipogrossa = "Flexão dos pinos metálicos com duas rótulas plásticas no elemento de madeira.";
                ImagemFalha2 = "DuasSecoesFlexaoPinosDuplaAma.png";
            }

            System.out.println("Fvkmin =" +Fvkmin2);
            System.out.println("Tipo =" +Tipo);

            //CALCULO INTERPOLAÇÃO
            double FvkMinimo = Fvkmin;
            double FvkMaximo = Fvkmin2;
            String TipoMinimo = Tipo;
            String TipoMaximo = Tipogrossa;
            String ImagemMinima = ImagemFalha;
            String ImagemMaxima = ImagemFalha2;
        
            
            
            if (Fvkmin2 < Fvkmin){
                FvkMinimo = Fvkmin2;
                FvkMaximo = Fvkmin;
                TipoMinimo = Tipogrossa;
                TipoMaximo = Tipo;
                ImagemMinima = ImagemFalha2;
                ImagemMaxima = ImagemFalha;
            }
            double Fvkfinal = 0.0;
            
            if (Fvkmin == Fvkmin2){
                Fvkfinal =Fvkmin;
                Tipofinal = TipoMinimo;
                ImagemFalhaFinal = ImagemMinima;
            }
            if (Fvkmin != Fvkmin2){
                Fvkfinal = ((((FvkMaximo - FvkMinimo)*(t2-(0.5*d)))/(0.5*d)))+ FvkMinimo;
                double Fvkmedio = (FvkMaximo - FvkMinimo)/2;
                if (Fvkfinal < (FvkMinimo+Fvkmedio)){
                    Tipofinal = TipoMinimo;
                    ImagemFalhaFinal = ImagemMinima;
                }
                if (Fvkfinal > (FvkMinimo+Fvkmedio)){
                    Tipofinal = TipoMaximo;
                    ImagemFalhaFinal = ImagemMaxima;
                }
            }
            
            
            LabelResultadoModeloFalha.setText(Tipofinal);
            LabelEspessuraChapa.setText("Cálculo realizado com interpolação linear");
            LabelRelatorioChapaAco.setText("Cálculo realizado com interpolação linear");
            
            //Calcular o valor total da ligação
            Rvk = Fvkfinal*2*nparafusos;
            Rvd = ((Rvk*kmod1*kmod2*kmod3)/1.4);
                     
            System.out.println("Rvk =" +Rvk);
            ResultadoFvk.setText(String.format("%.0f", Fvkfinal));
            ResultadoRvk.setText(String.format("%.0f", Rvk));
            ResultadoRd.setText(String.format("%.0f", Rvd));
            RelatorioMyd.setText(String.format("%.0f", Myk));
            RelatorioFvk1.setText(String.format("%.0f", Fvkmin));
            RelatorioFvk3.setText(String.format("%.0f", Fvkmin2));
            RelatorioFvk.setText(String.format("%.0f", Fvkfinal));
            RelatorioRvk.setText(String.format("%.0f", Rvk));
            RelatorioRd.setText(String.format("%.0f", Rvd));

        RelatorioRd11.setVisible(false);
        RelatorioRd13.setVisible(false);
        RelatorioRd23.setVisible(false);
        LabelInter1.setVisible(true);
        LabelInt2.setVisible(true);
        RelatorioFvk1.setVisible(true);
        RelatorioFvk2.setVisible(false);
        RelatorioFvk3.setVisible(true);
        jLabel55.setVisible(true);
        jLabel62.setVisible(false);
        jLabel63.setVisible(true);
        jScrollPane2.setVisible(true);
        
        teste.setText(Tipofinal);
        }    
        
        //COM ESSAS FUNÇÕES ALTERAM-SE AS IMAGENS DO RELATÓRIO
        FiguraResultadoModoFalha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensFalhas/" + ImagemFalhaFinal))); // NOI18N
        FiguraParafuso.setIcon(new ImageIcon(((ImageIcon)FiguraTipoParafuso.getIcon()).getImage().getScaledInstance(110, 25, Image.SCALE_SMOOTH)));
        FiguraParafuso.setIcon(new ImageIcon(((ImageIcon)FiguraTipoParafuso.getIcon()).getImage().getScaledInstance(110, 25, Image.SCALE_SMOOTH)));
        ModoFalha.setIcon(new ImageIcon(((ImageIcon)FiguraResultadoModoFalha.getIcon()).getImage().getScaledInstance(66, 140, Image.SCALE_SMOOTH)));
        RLogo.setIcon(new ImageIcon(((ImageIcon)LogoPrograma.getIcon()).getImage().getScaledInstance(125, 80, Image.SCALE_SMOOTH)));
        }
    }//GEN-LAST:event_ButtonCalcularActionPerformed

    private void ComboArruelasMadeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboArruelasMadeiraActionPerformed
        System.out.println("cccccccccccccc=" + ComboArruelasMadeira.getSelectedIndex());
        double imagemarruela = 0.0;
        
        if(ComboArruelasMadeira.getSelectedIndex() > 0){
            
            String norma = ComboArruelasMadeira.getSelectedItem().toString();
            // Remove o nome do parafuso e os parênteses
            norma = norma.substring(5).replace(")", "");
            System.out.println("norma=" + norma );

            String parafuso = ComboTipoParafuso.getSelectedItem().toString();

            d1 = normas.get(norma).get(parafuso)[0];
            d2 = normas.get(norma).get(parafuso)[1];
            alfa = normas.get(norma).get(parafuso)[2];
            imagemarruela = normas.get(norma).get(parafuso)[3];
            arruela = normas.get(norma).get(parafuso)[4];

            System.out.println("d1=" + d1);System.out.println("d2=" + d2);System.out.println("Alfa=" + alfa);System.out.println("Imagem=" + imagemarruela);

            ValorD1Arruelas.setText(""+d1);
            ValorD2Arruelas.setText(""+d2);
            RelatorioD1.setText(""+d1);
            RelatorioD2.setText(""+d2);
            String ImagemArruela = "";
            

            if (imagemarruela == 0.0){
                ImagemTipoArruela.setVisible(false);
                
            }
            if (imagemarruela == 1.0){
                ImagemTipoArruela.setVisible(true);
                RelatorioTipoArruela.setText("DIN 436 - "+parafuso);
                ImagemArruela = "ImagemArruela436.png";

                
            }
            if (imagemarruela == 2.0){
                ImagemTipoArruela.setVisible(true);
                RelatorioTipoArruela.setText("DIN 440 R - "+parafuso);
                ImagemArruela = "ImagemArruela440R2.png";

            }
            if (imagemarruela == 3.0){
                ImagemTipoArruela.setVisible(true);
                RelatorioTipoArruela.setText("DIN 440 V - "+parafuso);
                ImagemArruela = "ImagemArruela440V.png";

            }
            
            ImagemTipoArruela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Imagens/" + ImagemArruela))); // NOI18N

        
        }
    }//GEN-LAST:event_ComboArruelasMadeiraActionPerformed

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
        switch(ComboAco.getSelectedIndex()){
            case 0:
            ValorFyk.setText("-");
            ValorFuk.setText("-");
            break;
            case 1:
            ValorFyk.setText("235");
            ValorFuk.setText("400");
            RelatorioClasseAco.setText("ISO 898-1 - Classe 4.6");
            Relatoriofyk.setText("235");
            Relatoriofuk.setText("400");
            break;
            case 2:
            ValorFyk.setText("640");
            ValorFuk.setText("800");
            RelatorioClasseAco.setText("ISO 898-1 - Classe 8.8");
            Relatoriofyk.setText("640");
            Relatoriofuk.setText("800");
            break;
            case 3:
            ValorFyk.setText("900");
            ValorFuk.setText("1000");
            RelatorioClasseAco.setText("ISO 898-1 - Classe 10.9");
            Relatoriofyk.setText("900");
            Relatoriofuk.setText("1000");
            break;
        }
    }//GEN-LAST:event_ComboAcoActionPerformed

    private void ComboTipoParafusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTipoParafusoActionPerformed
        // TODO add your handling code here:
        DefaultComboBoxModel model = (DefaultComboBoxModel)ComboArruelasMadeira.getModel();
        DefaultComboBoxModel model2 = (DefaultComboBoxModel)ComboArruelasAco.getModel();

        switch(ComboTipoParafuso.getSelectedIndex()){
            case 0: //Escolha o tipo de Parafuso
            ValorDiametro.setText("-");
            ValorArea.setText("-");
            ValorD1Arruelas.setText("-");            
            ValorD2Arruelas.setText("-");
            model.removeAllElements();
            model.addElement("Escolha o Tipo de Arruela para Madeira");
            model2.removeAllElements();
            model2.addElement("Escolha o Tipo de Arruela para Aço");
            break;
            case 1: //M10
            ValorDiametro.setText("10,0");
            ValorArea.setText("58,0");
            model.removeAllElements();
            model.addElement("Escolha o Tipo de Arruela para Madeira");
            model.addElement("M10 (DIN 440 R)");
            model.addElement("M10 (DIN 440 V)");
            model.addElement("M10 (DIN 436)");
            model2.removeAllElements();
            model2.addElement("Escolha o Tipo de Arruela para Aço");
            model2.addElement("M10 (DIN 125)");
            ComboArruelasAco.setModel(model2);
            ComboArruelasMadeira.setModel(model);
            RelatorioTipoParafuso.setText("ISO 4016 - M10");
            RelatorioDiametro.setText("10,0");
            break;
            case 2: //M12
            ValorDiametro.setText("12,0");
            ValorArea.setText("84,3");
            model.removeAllElements();
            model.addElement("Escolha o Tipo de Arruela para Madeira");
            model.addElement("M12 (DIN 440 R)");
            model.addElement("M12 (DIN 440 V)");
            model.addElement("M12 (DIN 436)");
            model2.removeAllElements();
            model2.addElement("Escolha o Tipo de Arruela para Aço");
            model2.addElement("M12 (DIN 125)");
            ComboArruelasAco.setModel(model2);
            ComboArruelasMadeira.setModel(model);
            RelatorioTipoParafuso.setText("ISO 4016 - M12");
            RelatorioDiametro.setText("12,0");
            break;
            case 3: //M16
            ValorDiametro.setText("16,0");
            ValorArea.setText("157,0");
            model.removeAllElements();
            model.addElement("Escolha o Tipo de Arruela para Madeira");
            model.addElement("M16 (DIN 440 R)");
            model.addElement("M16 (DIN 440 V)");
            model.addElement("M16 (DIN 436)");
            model2.removeAllElements();
            model2.addElement("Escolha o Tipo de Arruela para Aço");
            model2.addElement("M16 (DIN 125)");
            ComboArruelasAco.setModel(model2);
            ComboArruelasMadeira.setModel(model);
            RelatorioTipoParafuso.setText("ISO 4016 - M16");
            RelatorioDiametro.setText("16,0");
            break;
            case 4: //M20
            ValorDiametro.setText("20,0");
            ValorArea.setText("245,0");
            model.removeAllElements();
            model.addElement("Escolha o Tipo de Arruela para Madeira");
            model.addElement("M20 (DIN 440 R)");
            model.addElement("M20 (DIN 440 V)");
            model.addElement("M20 (DIN 436)");
            model2.removeAllElements();
            model2.addElement("Escolha o Tipo de Arruela para Aço");
            model2.addElement("M20 (DIN 125)");
            ComboArruelasAco.setModel(model2);
            ComboArruelasMadeira.setModel(model);
            RelatorioTipoParafuso.setText("ISO 4016 - M20");
            RelatorioDiametro.setText("20,0");
            break;
            case 5: //M22
            ValorDiametro.setText("22,0");
            ValorArea.setText("303,0");
            model.removeAllElements();
            model.addElement("Escolha o Tipo de Arruela para Madeira");
            model.addElement("M22 (DIN 440 R)");
            model.addElement("M22 (DIN 440 V)");
            model.addElement("M22 (DIN 436)");
            model2.removeAllElements();
            model2.addElement("Escolha o Tipo de Arruela para Aço");
            model2.addElement("M22 (DIN 125)");
            ComboArruelasAco.setModel(model2);
            ComboArruelasMadeira.setModel(model);
            RelatorioTipoParafuso.setText("ISO 4016 - M22");
            RelatorioDiametro.setText("22,0");
            break;
            case 6: //M24
            ValorDiametro.setText("24,0");
            ValorArea.setText("353,0");
            model.removeAllElements();
            model.addElement("Escolha o Tipo de Arruela para Madeira");
            model.addElement("M24 (DIN 440 R)");
            model.addElement("M24 (DIN 436)");
            model2.removeAllElements();
            model2.addElement("Escolha o Tipo de Arruela para Aço");
            model2.addElement("M24 (DIN 125)");
            ComboArruelasAco.setModel(model2);
            ComboArruelasMadeira.setModel(model);
            RelatorioTipoParafuso.setText("ISO 4016 - M24");
            RelatorioDiametro.setText("24,0");
            break;
            case 7: //M27
            ValorDiametro.setText("27,0");
            ValorArea.setText("459,0");
            model.removeAllElements();
            model.addElement("Escolha o Tipo de Arruela para Madeira");
            model.addElement("M27 (DIN 440 R)");
            model.addElement("M27 (DIN 436)");
            model2.removeAllElements();
            model2.addElement("Escolha o Tipo de Arruela para Aço");
            model2.addElement("M27 (DIN 125)");
            ComboArruelasAco.setModel(model2);
            ComboArruelasMadeira.setModel(model);
            RelatorioTipoParafuso.setText("ISO 4016 - M27");
            RelatorioDiametro.setText("27,0");
            break;
            case 8: //M30
            ValorDiametro.setText("30,0");
            ValorArea.setText("561,0");
            model.removeAllElements();
            model.addElement("Escolha o Tipo de Arruela para Madeira");
            model.addElement("M30 (DIN 440 R)");
            model.addElement("M30 (DIN 436)");
            model2.removeAllElements();
            model2.addElement("Escolha o Tipo de Arruela para Aço");
            model2.addElement("M30 (DIN 125)");
            ComboArruelasAco.setModel(model2);
            ComboArruelasMadeira.setModel(model);
            RelatorioTipoParafuso.setText("ISO 4016 - M30");
            RelatorioDiametro.setText("30,0");
            break;
            case 9: //M33
            ValorDiametro.setText("33,0");
            ValorArea.setText("694,0");
            model.removeAllElements();
            model.addElement("Escolha o Tipo de Arruela para Madeira");
            model.addElement("M33 (DIN 440 R)");
            model2.removeAllElements();
            model2.addElement("Escolha o Tipo de Arruela para Aço");
            model2.addElement("M33 (DIN 125)");
            ComboArruelasAco.setModel(model2);
            ComboArruelasMadeira.setModel(model);
            RelatorioTipoParafuso.setText("ISO 4016 - M33");
            RelatorioDiametro.setText("33,0");
            break;
            case 10: //M36
            ValorDiametro.setText("36,0");
            ValorArea.setText("817,0");
            model.removeAllElements();
            model.addElement("Escolha o Tipo de Arruela para Madeira");
            model.addElement("M36 (DIN 440 R)");
            model2.removeAllElements();
            model2.addElement("Escolha o Tipo de Arruela para Aço");
            model2.addElement("M36 (DIN 125)");
            ComboArruelasAco.setModel(model2);
            ComboArruelasMadeira.setModel(model);
            RelatorioTipoParafuso.setText("ISO 4016 - M36");
            RelatorioDiametro.setText("36,0");
            break;
        }
    }//GEN-LAST:event_ComboTipoParafusoActionPerformed

    private void Espessura1(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Espessura1
        // TODO add your handling code here:
        if (Espessura1.getText().length()>4){
            Espessura1.setText("");
        }
    }//GEN-LAST:event_Espessura1

    private void ComboElem1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ComboElem1PropertyChange

    }//GEN-LAST:event_ComboElem1PropertyChange

    private void ComboElem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboElem1ActionPerformed
        switch(ComboElem1.getSelectedIndex()){
            case 0:
            ValorFc01.setText("-");
            ValorDensidade1.setText("-");
            ValorFvok1.setText("-");
            ValorEc0m1.setText("-");
            break;
            case 1: //C-20
            ValorFc01.setText("20");
            ValorDensidade1.setText("500");
            ValorFvok1.setText("4");
            ValorEc0m1.setText("3500");
            RelatorioClasseElem1.setText("C-20");
            Relatoriofcok1.setText("20");
            RelatorioDensidade1.setText("500");
            Relatoriofv0k1.setText("4");
            RelatorioEc0m1.setText("3500");
            break;
            case 2: //C-25
            ValorFc01.setText("25");
            ValorFvok1.setText("5");
            ValorEc0m1.setText("8500");
            ValorDensidade1.setText("550");
            RelatorioClasseElem1.setText("C-25");
            Relatoriofcok1.setText("25");
            Relatoriofv0k1.setText("5");
            RelatorioEc0m1.setText("8500");
            RelatorioDensidade1.setText("550");
            break;
            case 3: //C-30
            ValorFc01.setText("30");
            ValorFvok1.setText("6");
            ValorEc0m1.setText("14500");
            ValorDensidade1.setText("600");
            RelatorioClasseElem1.setText("C-30");
            Relatoriofcok1.setText("30");
            Relatoriofv0k1.setText("6");
            RelatorioEc0m1.setText("14500");
            RelatorioDensidade1.setText("600");
            break;
            case 4: //D-20
            ValorFc01.setText("20");
            ValorFvok1.setText("4");
            ValorEc0m1.setText("9500");
            ValorDensidade1.setText("650");
            RelatorioClasseElem1.setText("D-20");
            Relatoriofcok1.setText("20");
            Relatoriofv0k1.setText("4");
            RelatorioEc0m1.setText("9500");
            RelatorioDensidade1.setText("650");
            break;
            case 5: //D-30
            ValorFc01.setText("30");
            ValorFvok1.setText("5");
            ValorEc0m1.setText("14500");
            ValorDensidade1.setText("800");
            RelatorioClasseElem1.setText("D-30");
            Relatoriofcok1.setText("30");
            Relatoriofv0k1.setText("5");
            RelatorioEc0m1.setText("14500");
            RelatorioDensidade1.setText("800");
            break;
            case 6: //D-40
            ValorFc01.setText("40");
            ValorFvok1.setText("6");
            ValorEc0m1.setText("19500");
            ValorDensidade1.setText("950");
            RelatorioClasseElem1.setText("D-40");
            Relatoriofcok1.setText("40");
            Relatoriofv0k1.setText("6");
            RelatorioEc0m1.setText("19500");
            RelatorioDensidade1.setText("950");
            break;
            case 7: //D-50
            ValorFc01.setText("50");
            ValorFvok1.setText("7");
            ValorEc0m1.setText("22000");
            ValorDensidade1.setText("970");
            RelatorioClasseElem1.setText("D-50");
            Relatoriofcok1.setText("50");
            Relatoriofv0k1.setText("7");
            RelatorioEc0m1.setText("22000");
            RelatorioDensidade1.setText("970");
            break;
            case 8: //C-30
            ValorFc01.setText("60");
            ValorFvok1.setText("8");
            ValorEc0m1.setText("24500");
            ValorDensidade1.setText("1000");
            RelatorioClasseElem1.setText("D-60");
            Relatoriofcok1.setText("60");
            Relatoriofv0k1.setText("8");
            RelatorioEc0m1.setText("24500");
            RelatorioDensidade1.setText("1000");
            break;
        }    // TODO add your handling code here:
    }//GEN-LAST:event_ComboElem1ActionPerformed

    private void btnCorteDuploAMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorteDuploAMAActionPerformed
        // TODO add your handling code here:
        ss = false;
        sdama = true;
        sdmam = false;
        ComboArruelasMadeira.setVisible(false);
        ComboArruelasAco.setVisible(true);
        jLabelStatusAco.setText("Clique em avançar para continuar.");
        
        Next.setEnabled(true);
        RelatorioSecaoCorte.setText("Ligação com corte duplo - Aço/Madeira/Aço");
        TrocaFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/DuasSecoesParaAma.png"))); // NOI18N
        FiguraSecoes.setIcon(new ImageIcon(((ImageIcon)btnCorteDuploAMA.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));
        
        System.out.println("ss =" + ss);
        System.out.println("sdama =" + sdama);
        System.out.println("sdmam =" + sdmam);
        // JOptionPane.showMessageDialog(this, "Esta habilitado a= ["+a.booleanValue()+"] b=["+b.booleanValue()+"] c=["+c.booleanValue()+"]");
    }//GEN-LAST:event_btnCorteDuploAMAActionPerformed

    private void Espessura1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Espessura1KeyTyped
        // TODO add your handling code here:
        if ((evt.getKeyChar()>='0' && evt.getKeyChar()<='9')||evt.getKeyChar() == ','){
            return;
        }
        evt.consume();

    }//GEN-LAST:event_Espessura1KeyTyped
    
    private void Espessura1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Espessura1ActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_Espessura1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        Relatorio.setVisible(false);
    }//GEN-LAST:event_formWindowActivated

    private void FiguraTipoParafusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiguraTipoParafusoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FiguraTipoParafusoActionPerformed

    private void TrocaFiguraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrocaFiguraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TrocaFiguraActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void ComboQuantParafusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboQuantParafusoActionPerformed
        // TODO add your handling code here:
        switch(ComboQuantParafuso.getSelectedIndex()){
            case 0:
            npar = 0;
            RelatorioNParafusos.setText("0");
            case 1:
            npar = 1;
            RelatorioNParafusos.setText("1");
            case 2:
            npar = 2;
            RelatorioNParafusos.setText("2");
            break;
            case 3:
            npar = 4;
            RelatorioNParafusos.setText("4");
            break;
            case 4:
            npar = 5;
            RelatorioNParafusos.setText("5");
            break;
            case 5:
            npar = 6;
            RelatorioNParafusos.setText("6");
            break;
            case 6:
            npar = 8;
            RelatorioNParafusos.setText("8");
            break;
            case 7:
            npar = 10;
            RelatorioNParafusos.setText("10");
            break;
            case 8:
            npar = 12;
            RelatorioNParafusos.setText("12");
            break;
            case 9:
            npar = 14;
            RelatorioNParafusos.setText("14");
            break;
        }
    }//GEN-LAST:event_ComboQuantParafusoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Printable p = new Printable() {

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) { /* We have only one page, and 'page' is zero-based */
                     return NO_SUCH_PAGE;
                             }
        
//        pageFormat.setOrientation(pageIndex);
        Graphics2D g2d = (Graphics2D)graphics;
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

           if (ok)
           {
             try
            {
              job.print();
            }
            catch (PrinterException ex)
            {
            /* The job did not successfully complete */
            }
          }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedComponent(Inicio);
        GroupSecoesCorte.clearSelection();
        GroupTesteParafuso.clearSelection();
        TextKmod1.setText("");
        TextKmod2.setText("");
        TextKmod3.setText("");
        Espessura1.setText("Digite a espessura");
        ValorAngulo.setText("0");
        ComboQuantParafuso.setSelectedIndex(0);
        ComboTipoParafuso.setSelectedIndex(0);
        ComboAco.setSelectedIndex(0);
        ComboElem1.setSelectedIndex(0);
        ComboTipoChapaAco.setSelectedIndex(0);
        ComboChapaAco.setSelectedIndex(0);
        ComboKmod1.setSelectedIndex(0);
        ComboKmod2.setSelectedIndex(0);
        ComboKmod3.setSelectedIndex(0);
        jLabelStatusAco.setText("Clique em \"Iniciar Cálculo\" para começar o dimensionamento.");
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(3, false);
        jTabbedPane1.setEnabledAt(4, false);
        jTabbedPane1.setEnabledAt(5, false);
        ImagemTipoArruela.setVisible(false);
        m = false;
        alfa = 0;
        d = 0;
        d1 = 0;
        d2 = 0;

        ss = false;
        sdama = false;
        sdmam = false;
        fina = false;
        grossa = false;
        indefinida = false;
        nparafusos = 0.0;
        kmod1 = 0;
        kmod2 = 0;
        kmod3 = 0;
        fvk1 = 0;
        fvk2 = 0;
        fvk3 = 0;
        fvk4 = 0;
        fvk5 = 0;
        fvk6 = 0;
        fvk7 = 0;
        fvk8 = 0;
        fvk9 = 0;
        fvk10 = 0;
        fvk11 = 0;
        fvk12 = 0;
        Fvkmin = 0;
        Fvkmin2 = 0;
        Rvk = 0;
        Rvd = 0;
        valorFaxrk = 0;
        npar = 0;
        t2 = 0;
        
        
        ImagemTipoArruela.setVisible(false);
        CalculoForca01.setVisible(true);
        CalculoForca901.setVisible(false);
        CalculoForcaAlfa1.setVisible(false);
        
        
        TrocaFigura.setVisible(true);
        
        RelatorioRd13.setVisible(true);       
        RelatorioFvk2.setVisible(true);
        jLabel62.setVisible(true);
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Espessura1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Espessura1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_Espessura1PropertyChange

    private void ComboTipoChapaAcoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ComboTipoChapaAcoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboTipoChapaAcoPropertyChange

    private void ComboTipoChapaAcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTipoChapaAcoActionPerformed
        // TODO add your handling code here:
        switch(ComboTipoChapaAco.getSelectedIndex()){
            case 0:
            ValorChapaFyk.setText("-");
            ValorChapaFuk.setText("-");


            break;
            case 1: //ASTM A36
            ValorChapaFyk.setText("250");
            ValorChapaFuk.setText("500");
         //   RelatorioClasseElem2.setText("ASTM A36");
            RelatorioChapaFyk.setText("250");
            RelatorioChapaFuk.setText("500");
            break;
        }
    }//GEN-LAST:event_ComboTipoChapaAcoActionPerformed

    private void ComboChapaAcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboChapaAcoActionPerformed
        // TODO add your handling code here:
         switch(ComboChapaAco.getSelectedIndex()){
            case 0:
                t2 = 0;
            break;
            case 1:
                t2 = 6.35;
                RelatorioChapaEspessura.setText("6,35");
            break;
            case 2:
                t2 = 8.0;
                RelatorioChapaEspessura.setText("8,00");
            break;
            case 3:
                t2 = 9.53;
                RelatorioChapaEspessura.setText("9,53");
            break;
            case 4:
                t2 = 12.7;
                RelatorioChapaEspessura.setText("12,70");
            break;
            case 5:
                t2 = 15.9;
                RelatorioChapaEspessura.setText("15,90");
            break;
            case 6:
                t2 = 19.1;
                RelatorioChapaEspessura.setText("19,10");
            break;
            case 7:
                t2 = 22.2;
                RelatorioChapaEspessura.setText("22,20");
            break;
            case 8:
                t2 = 25.4;
                RelatorioChapaEspessura.setText("25,40");
            break;
            case 9:
                t2 = 31.8;
                RelatorioChapaEspessura.setText("31,80");
            break;
            case 10:
                t2 = 38.1;
                RelatorioChapaEspessura.setText("38,10");
            break;
            case 11:
                t2 = 41.3;
                RelatorioChapaEspessura.setText("41,30");
            break;
            case 12:
                t2 = 44.5;
                RelatorioChapaEspessura.setText("44,50");
            break;
            case 13:
                t2 = 50.8;
                RelatorioChapaEspessura.setText("50,80");
            break;
            case 14:
                t2 = 57.2;
                RelatorioChapaEspessura.setText("57,20");
            break;
            case 15:
                t2 = 63.5;
                RelatorioChapaEspessura.setText("63,50");
            break;
            case 16:
                t2 = 76.2;
                RelatorioChapaEspessura.setText("76,20");
            break;
            case 17:
                t2 = 88.9;
                RelatorioChapaEspessura.setText("88,90");
            break;
            case 18:
                t2 = 102.0;
                RelatorioChapaEspessura.setText("102,00");
            break;
    }//GEN-LAST:event_ComboChapaAcoActionPerformed
    }
    private void IniciarCalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarCalculoActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setSelectedComponent(SecoesCorte);
        jLabelStatusAco.setText("Escolha a quantidade de seções de corte no parafuso.");

    }//GEN-LAST:event_IniciarCalculoActionPerformed

    private void btnCorteDuploMAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorteDuploMAMActionPerformed
        // TODO add your handling code here:
        ss = false;
        sdama = false;
        sdmam = true;
        ComboArruelasMadeira.setVisible(true);
        ComboArruelasAco.setVisible(false);
        jLabelStatusAco.setText("Clique em avançar para continuar.");
        
        Next.setEnabled(true);
        RelatorioSecaoCorte.setText("Ligação com corte duplo - Madeira/Aço/Madeira");
        TrocaFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/DuasSecoesParaMam.png"))); // NOI18N
        FiguraSecoes.setIcon(new ImageIcon(((ImageIcon)btnCorteDuploMAM.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));
        
        System.out.println("ss =" + ss);
        System.out.println("sdama =" + sdama);
        System.out.println("sdmam =" + sdmam);
    }//GEN-LAST:event_btnCorteDuploMAMActionPerformed

    private void ValorAnguloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValorAnguloFocusLost
        // TODO add your handling code here:
        double testeAngulo = Integer.parseInt(ValorAngulo.getText());
        
        double teste = Double.parseDouble(ValorAngulo.getText().replace(",", "."));
        
        
        if (teste > 90.0){
            ValorAngulo.setText("");
            testeAngulo = 0.0;
        }
        
        
        TrocaFigura.setVisible(true);
        String FiguraMadeira = "";
            

        if (ss == true){
            if (testeAngulo == 0.0){
            FiguraMadeira = "UmaSecaoParalelo.png";
            CalculoForca01.setVisible(true);
            CalculoForca901.setVisible(false);
            CalculoForcaAlfa1.setVisible(false);
        }
        else{
            if (testeAngulo == 90.0){
            FiguraMadeira = "UmaSecaoPerpendicular.png";
            CalculoForca01.setVisible(false);
            CalculoForca901.setVisible(true);
            CalculoForcaAlfa1.setVisible(false);
        }
        else{
            FiguraMadeira = "UmaSecaoInclinado.png";
            CalculoForca01.setVisible(false);
            CalculoForca901.setVisible(false);
            CalculoForcaAlfa1.setVisible(true);
            }
        }
        }
        if (sdama == true){
            if (testeAngulo == 0.0){
            FiguraMadeira = "DuasSecoesParaAma.png";
            CalculoForca01.setVisible(true);
            CalculoForca901.setVisible(false);
            CalculoForcaAlfa1.setVisible(false);
        }
        else{
            if (testeAngulo == 90.0){
            FiguraMadeira = "DuasSecoesPerpAma.png";
            CalculoForca01.setVisible(false);
            CalculoForca901.setVisible(true);
            CalculoForcaAlfa1.setVisible(false);
        }
        else{
            FiguraMadeira = "DuasSecoesIncliAma.png";
            CalculoForca01.setVisible(false);
            CalculoForca901.setVisible(false);
            CalculoForcaAlfa1.setVisible(true);
            }
        }
        }
        if (sdmam == true){
            if (testeAngulo == 0.0){
            FiguraMadeira = "DuasSecoesParaMam.png";
            CalculoForca01.setVisible(true);
            CalculoForca901.setVisible(false);
            CalculoForcaAlfa1.setVisible(false);
        }
        else{
            if (testeAngulo == 90.0){
            FiguraMadeira = "DuasSecoesPerpMam.png";
            CalculoForca01.setVisible(false);
            CalculoForca901.setVisible(true);
            CalculoForcaAlfa1.setVisible(false);
        }
        else{
            FiguraMadeira = "DuasSecoesIncliMam.png";
            CalculoForca01.setVisible(false);
            CalculoForca901.setVisible(false);
            CalculoForcaAlfa1.setVisible(true);
            }
        }
        }
        
        
        TrocaFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/"+FiguraMadeira))); // NOI18N
        TrocaFigura.doClick();
        
    }//GEN-LAST:event_ValorAnguloFocusLost

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        TelaInicial2 telainicial = new TelaInicial2();
        telainicial.setVisible(true);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ValorAnguloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ValorAnguloKeyTyped
        // TODO add your handling code here:
        if ((evt.getKeyChar()>='0' && evt.getKeyChar()<='9')||evt.getKeyChar() == ','){
            return;
        }
        evt.consume();
    }//GEN-LAST:event_ValorAnguloKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        TelaInicial2 telainicial = new TelaInicial2();
        telainicial.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ComboKmod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod1ActionPerformed
        // TODO add your handling code here:
        switch(ComboKmod1.getSelectedIndex()){
            case 0:
            TextKmod1.setText("-");
            break;
            case 1:
            TextKmod1.setText("");
            break;
            case 2:
            TextKmod1.setText("0.6");
            break;
            case 3:
            TextKmod1.setText("0.7");
            break;
            case 4:
            TextKmod1.setText("0.8");
            break;
            case 5:
            TextKmod1.setText("0.9");
            break;
            case 6:
            TextKmod1.setText("1.1");
            break;
            case 7:
            TextKmod1.setText("");
            break;
            case 8:
            TextKmod1.setText("0.3");
            break;
            case 9:
            TextKmod1.setText("0.45");
            break;
            case 10:
            TextKmod1.setText("0.65");
            break;
            case 11:
            TextKmod1.setText("0.9");
            break;
            case 12:
            TextKmod1.setText("1.1");
            break;
            case 13:
                do{
                    kmod1 = -1;
                    String value =  (String)JOptionPane.showInputDialog(this, "Entre com um valor entre 0 e 1.", "Kmod 1", JOptionPane.INFORMATION_MESSAGE, null, null, "Digite o valor");
                    TextKmod1.setText(value);
                    try{
                        kmod1 = Double.parseDouble(TextKmod1.getText().replace(",", "."));
                    }catch(NumberFormatException e){
                            
                            }
                    jLabelStatusAco.setText("Entre com um valor válido, entre 0 e 1.");
                }while (kmod1> 1 || kmod1<=0);
                jLabelStatusAco.setText("");
            break;
        }
    }//GEN-LAST:event_ComboKmod1ActionPerformed

    private void ComboKmod3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod3ActionPerformed
        // TODO add your handling code here:
        switch(ComboKmod3.getSelectedIndex()){
            case 0:
            TextKmod3.setText("-");
            TextKmod3.setEnabled(true);
            break;
            case 1:
            TextKmod3.setText("-");
            TextKmod3.setEnabled(false);
            break;
            case 2:
            TextKmod3.setText("0.9");
            TextKmod3.setEnabled(true);
            break;
            case 3:
            TextKmod3.setText("0.85");
            TextKmod3.setEnabled(true);
            break;
            case 4:
            TextKmod3.setText("0.80");
            TextKmod3.setEnabled(true);
            break;
            case 5:
            TextKmod3.setText("0.75");
            TextKmod3.setEnabled(true);
            break;
            case 6:
            TextKmod3.setText("1.0");
            TextKmod3.setEnabled(true);
            break;
            case 7:
            TextKmod3.setText("0.95");
            TextKmod3.setEnabled(true);
            break;
            case 8:
            TextKmod3.setText("0.9");
            TextKmod3.setEnabled(true);
            break;
            case 9:
            TextKmod3.setText("0.85");
            TextKmod3.setEnabled(true);
            break;
            case 10:
            TextKmod3.setText("0.7");
            TextKmod3.setEnabled(true);
            break;
            case 11:
            TextKmod3.setText("-");
            TextKmod3.setEnabled(false);
            break;
            case 12:
            TextKmod3.setText("0.7");
            TextKmod3.setEnabled(true);
            break;
            case 13:
            TextKmod3.setText("0.6");
            TextKmod3.setEnabled(true);
            break;
            case 14:
            TextKmod3.setText("0.5");
            TextKmod3.setEnabled(true);
            break;
            case 15:
            TextKmod3.setText("0.4");
            TextKmod3.setEnabled(true);
            break;
            case 16:
            TextKmod3.setText("0.6");
            TextKmod3.setEnabled(true);
            break;
            case 17:
            TextKmod3.setText("0.5");
            TextKmod3.setEnabled(true);
            break;
            case 18:
            TextKmod3.setText("0.4");
            TextKmod3.setEnabled(true);
            break;
            case 19:
            TextKmod3.setText("0.3");
            TextKmod3.setEnabled(true);
            break;
            case 20:
            TextKmod3.setText("0.9");
            TextKmod3.setEnabled(true);
            break;
            case 21:
            TextKmod3.setText("0.8");
            TextKmod3.setEnabled(true);
            break;
            case 22:
            TextKmod3.setText("0.7");
            TextKmod3.setEnabled(true);
            break;
            case 23:
            TextKmod3.setText("0.6");
            TextKmod3.setEnabled(true);
            break;
            case 24:
            TextKmod3.setText("0.8");
            TextKmod3.setEnabled(true);
            break;
            case 25:
            TextKmod3.setText("0.7");
            TextKmod3.setEnabled(true);
            break;
            case 26:
            TextKmod3.setText("0.6");
            TextKmod3.setEnabled(true);
            break;
            case 27:
            TextKmod3.setText("0.5");
            TextKmod3.setEnabled(true);
            break;
            case 28:
            do{
                    kmod3 = -1;
                    String value =  (String)JOptionPane.showInputDialog(this, "Entre com um valor entre 0 e 1.", "Kmod 3", JOptionPane.INFORMATION_MESSAGE, null, null, "Digite o valor");
                    TextKmod3.setText(value);
                    try{
                        kmod3 = Double.parseDouble(TextKmod3.getText().replace(",", "."));
                    }catch(NumberFormatException e){
                            
                            }
                    jLabelStatusAco.setText("Entre com um valor válido, entre 0 e 1.");
                }while (kmod3> 1 || kmod3<=0);
                jLabelStatusAco.setText("");
            break;
        }
    }//GEN-LAST:event_ComboKmod3ActionPerformed

    private void ComboKmod2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod2ActionPerformed
        // TODO add your handling code here:
        switch(ComboKmod2.getSelectedIndex()){
            case 0:
            TextKmod2.setText("-");
            break;
            case 1:
            TextKmod2.setText("");
            break;
            case 2:
            TextKmod2.setText("1.0");
            break;
            case 3:
            TextKmod2.setText("0.9");
            break;
            case 4:
            TextKmod2.setText("0.8");
            break;
            case 5:
            TextKmod2.setText("0.7");
            break;
            case 6:
            TextKmod2.setText("");
            break;
            case 7:
            TextKmod2.setText("1.0");
            break;
            case 8:
            TextKmod2.setText("0.95");
            break;
            case 9:
            TextKmod2.setText("0.93");
            break;
            case 10:
            TextKmod2.setText("0.9");
            break;
            case 11:
            do{
                    kmod2 = -1;
                    String value =  (String)JOptionPane.showInputDialog(this, "Entre com um valor entre 0 e 1.", "Kmod 2", JOptionPane.INFORMATION_MESSAGE, null, null, "Digite o valor");
                    TextKmod2.setText(value);
                    try{
                        kmod2 = Double.parseDouble(TextKmod2.getText().replace(",", "."));
                    }catch(NumberFormatException e){
                            
                            }
                    jLabelStatusAco.setText("Entre com um valor válido, entre 0 e 1.");
                }while (kmod2> 1 || kmod2<=0);
                jLabelStatusAco.setText("");
            break;
        }
    }//GEN-LAST:event_ComboKmod2ActionPerformed

    private void ComboArruelasAcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboArruelasAcoActionPerformed
        // TODO add your handling code here:
    System.out.println("cccccccccccccc=" + ComboArruelasAco.getSelectedIndex());
        double imagemarruela = 0.0;
        
        if(ComboArruelasAco.getSelectedIndex() > 0){
            
            String norma = ComboArruelasAco.getSelectedItem().toString();
            // Remove o nome do parafuso e os parênteses
            norma = norma.substring(5).replace(")", "");
            System.out.println("norma=" + norma );

            String parafuso = ComboTipoParafuso.getSelectedItem().toString();

            d1 = normas2.get(norma).get(parafuso)[0];
            d2 = normas2.get(norma).get(parafuso)[1];
            alfa = normas2.get(norma).get(parafuso)[2];
            imagemarruela = normas2.get(norma).get(parafuso)[3];
            arruela = normas2.get(norma).get(parafuso)[4];

            System.out.println("d1=" + d1);System.out.println("d2=" + d2);System.out.println("Alfa=" + alfa);System.out.println("Imagem=" + imagemarruela);

            ValorD1Arruelas.setText(""+d1);
            ValorD2Arruelas.setText(""+d2);
            RelatorioD1.setText(""+d1);
            RelatorioD2.setText(""+d2);
            String ImagemArruela = "";
            

            if (imagemarruela == 0.0){
                ImagemTipoArruela.setVisible(false);
                
            }
            if (imagemarruela == 2.0){
                ImagemTipoArruela.setVisible(true);
                RelatorioTipoArruela.setText("DIN 125 - "+parafuso);
                ImagemArruela = "ImagemArruela440R2.png";

            }
            
            ImagemTipoArruela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Imagens/" + ImagemArruela))); // NOI18N

        
        }
    }//GEN-LAST:event_ComboArruelasAcoActionPerformed

    private void ValorAnguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValorAnguloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ValorAnguloActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setEnabledAt(2, true);
        jTabbedPane1.setSelectedComponent(ElementosMadeira);
        jLabelStatusAco.setText("Preencha todos os campos sem exceção.");
    }//GEN-LAST:event_NextActionPerformed

    private void Next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Next2ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        Espessura1.getInputVerifier().shouldYieldFocus(Espessura1);
        ComboChapaAco.getInputVerifier().shouldYieldFocus(ComboChapaAco);
        ValorAngulo.getInputVerifier().shouldYieldFocus(ValorAngulo);
        ComboElem1.getInputVerifier().shouldYieldFocus(ComboElem1);
        ComboTipoChapaAco.getInputVerifier().shouldYieldFocus(ComboTipoChapaAco);
        ComboKmod1.getInputVerifier().shouldYieldFocus(ComboKmod1);
        ComboKmod2.getInputVerifier().shouldYieldFocus(ComboKmod2);
        ComboKmod3.getInputVerifier().shouldYieldFocus(ComboKmod3);
        
        
        if (Next2.hasFocus()){
        jTabbedPane1.setEnabledAt(3, true);
        jTabbedPane1.setSelectedComponent(ElementosMetalicos);
        jLabelStatusAco.setText("Preencha todos os campos sem exceção.");
        }
    }//GEN-LAST:event_Next2ActionPerformed

    private void Next3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Next3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setEnabledAt(5, true);
        jTabbedPane1.setSelectedComponent(Relatorio);
        jLabelStatusAco.setText("Verifique o relatório do dimensionamento. Fique atento para as opções fornecidas nos botões.");
    }//GEN-LAST:event_Next3ActionPerformed

    private void btnCorteSimplesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorteSimplesActionPerformed
        // TODO add your handling code here:
        ss = true;
        sdama = false;
        sdmam = false;
        ComboArruelasMadeira.setVisible(true);
        ComboArruelasAco.setVisible(false);
        jLabelStatusAco.setText("Clique em avançar para continuar.");

        Next.setEnabled(true);
        RelatorioSecaoCorte.setText("Ligação com corte simples");
        TrocaFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/UmaSecaoParalelo.png"))); // NOI18N
        FiguraSecoes.setIcon(new ImageIcon(((ImageIcon)btnCorteSimples.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));

        System.out.println("ss =" + ss);
        System.out.println("sdama =" + sdama);
        System.out.println("sdmam =" + sdmam);
        //double x = valor_faxrk(1, 250, Double.parseDouble("6.5"), 12, 250);
        //JOptionPane.showMessageDialog(this, "Esta habilitado a= ["+a.booleanValue()+"] b=["+b.booleanValue()+"] c=["+c.booleanValue()+"]");
        //JOptionPane.showMessageDialog(this, "valor da função= ["+Double.toString(x)+"");
    }//GEN-LAST:event_btnCorteSimplesActionPerformed

    //}   Esse colchetes ferrou com nossa vida por mais de 1 mês!! Descanse em paz, seu trabalho foi cumprido.
    /**/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){

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
            java.util.logging.Logger.getLogger(TelaPrincipalAco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalAco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalAco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalAco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipalAco().setVisible(true);
            }
        });
             
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AreaParafuso;
    private javax.swing.JLabel Arruelas;
    private javax.swing.JButton ButtonCalcular;
    private javax.swing.JLabel CalculoForca01;
    private javax.swing.JLabel CalculoForca901;
    private javax.swing.JLabel CalculoForcaAlfa1;
    private javax.swing.JLabel ClasseAco;
    private javax.swing.JComboBox ComboAco;
    private javax.swing.JComboBox ComboArruelasAco;
    private javax.swing.JComboBox ComboArruelasMadeira;
    private javax.swing.JComboBox ComboChapaAco;
    private javax.swing.JComboBox ComboElem1;
    private javax.swing.JComboBox ComboKmod1;
    private javax.swing.JComboBox ComboKmod2;
    private javax.swing.JComboBox ComboKmod3;
    private javax.swing.JComboBox ComboQuantParafuso;
    private javax.swing.JComboBox ComboTipoChapaAco;
    private javax.swing.JComboBox ComboTipoParafuso;
    private javax.swing.JTextArea Consideracao1;
    private javax.swing.JLabel D1Arruelas;
    private javax.swing.JLabel D2Arruelas;
    private javax.swing.JLabel Data;
    private javax.swing.JLabel Densidade1;
    private javax.swing.JLabel Densidade2;
    private javax.swing.JLabel Diametro;
    private javax.swing.JLabel Ec0m1;
    private javax.swing.JPanel ElementosMadeira;
    private javax.swing.JPanel ElementosMetalicos;
    private javax.swing.JTextField Espessura1;
    private javax.swing.JLabel Fcok1;
    private javax.swing.JLabel Fcok2;
    private javax.swing.JLabel FiguraArruela;
    private javax.swing.JLabel FiguraParafuso;
    private javax.swing.JButton FiguraResultadoModoFalha;
    private javax.swing.JLabel FiguraSecoes;
    private javax.swing.JButton FiguraTipoParafuso;
    private javax.swing.JLabel Fv0k1;
    private javax.swing.JLabel Fyk;
    private javax.swing.ButtonGroup GroupAngulacao;
    private javax.swing.ButtonGroup GroupInclinacaoSim;
    private javax.swing.ButtonGroup GroupSecoesCorte;
    private javax.swing.ButtonGroup GroupTesteParafuso;
    private javax.swing.JLabel Hora;
    private javax.swing.JLabel ImagemTipoArruela;
    private javax.swing.JButton IniciarCalculo;
    private javax.swing.JPanel Inicio;
    private javax.swing.JLabel LabelEspessuraChapa;
    private javax.swing.JLabel LabelInt2;
    private javax.swing.JLabel LabelInter1;
    private javax.swing.JLabel LabelModeloFalha;
    private javax.swing.JLabel LabelRelatorioChapaAco;
    private javax.swing.JLabel LabelResistenciaLigacao;
    private javax.swing.JLabel LabelResultadoModeloFalha;
    private javax.swing.JLabel LabelTipoChapaAco;
    private javax.swing.JLabel LogoPrograma;
    private javax.swing.JLabel ModoFalha;
    private javax.swing.JButton Next;
    private javax.swing.JButton Next2;
    private javax.swing.JButton Next3;
    private javax.swing.JLabel NumeroParafusos;
    private javax.swing.JLabel RLogo;
    private javax.swing.JPanel Relatorio;
    private javax.swing.JLabel RelatorioAngulo;
    private javax.swing.JLabel RelatorioChapaEspessura;
    private javax.swing.JLabel RelatorioChapaFuk;
    private javax.swing.JLabel RelatorioChapaFyk;
    private javax.swing.JLabel RelatorioClasseAco;
    private javax.swing.JLabel RelatorioClasseElem1;
    private javax.swing.JPanel RelatorioCoeficientes;
    private javax.swing.JLabel RelatorioD1;
    private javax.swing.JLabel RelatorioD2;
    private javax.swing.JLabel RelatorioDensidade1;
    private javax.swing.JLabel RelatorioDiametro;
    private javax.swing.JLabel RelatorioEc0m1;
    private javax.swing.JPanel RelatorioElem1;
    private javax.swing.JLabel RelatorioEspessura1;
    private javax.swing.JLabel RelatorioFaxrk;
    private javax.swing.JLabel RelatorioFc0d1;
    private javax.swing.JLabel RelatorioFibras;
    private javax.swing.JPanel RelatorioFinal;
    private javax.swing.JLabel RelatorioFvk;
    private javax.swing.JLabel RelatorioFvk1;
    private javax.swing.JLabel RelatorioFvk2;
    private javax.swing.JLabel RelatorioFvk3;
    private javax.swing.JLabel RelatorioKmod1;
    private javax.swing.JLabel RelatorioMyd;
    private javax.swing.JLabel RelatorioNParafusos;
    private javax.swing.JPanel RelatorioParafuso;
    private javax.swing.JLabel RelatorioRd;
    private javax.swing.JLabel RelatorioRd11;
    private javax.swing.JLabel RelatorioRd13;
    private javax.swing.JLabel RelatorioRd23;
    private javax.swing.JLabel RelatorioRvk;
    private javax.swing.JLabel RelatorioSecaoCorte;
    private javax.swing.JLabel RelatorioTipoArruela;
    private javax.swing.JLabel RelatorioTipoParafuso;
    private javax.swing.JLabel Relatoriofaxrk;
    private javax.swing.JLabel Relatoriofcok1;
    private javax.swing.JLabel Relatoriofcok4;
    private javax.swing.JLabel Relatoriofuk;
    private javax.swing.JLabel Relatoriofv0k1;
    private javax.swing.JLabel Relatoriofyk;
    private javax.swing.JLabel Relatoriokmod2;
    private javax.swing.JLabel Relatoriokmod3;
    private javax.swing.JPanel Resultado;
    private javax.swing.JLabel ResultadoFvk;
    private javax.swing.JLabel ResultadoRd;
    private javax.swing.JLabel ResultadoRvk;
    private javax.swing.JPanel SecoesCorte;
    private javax.swing.JLabel TamanhoParafuso;
    private javax.swing.JLabel TesteParafuso;
    private javax.swing.JRadioButton TesteParafusoNao;
    private javax.swing.JRadioButton TesteParafusoSim;
    private javax.swing.JLabel TextKmod1;
    private javax.swing.JLabel TextKmod2;
    private javax.swing.JLabel TextKmod3;
    private javax.swing.JLabel TipoArruela;
    private javax.swing.JButton TrocaFigura;
    private javax.swing.JLabel UnD1Arruelas;
    private javax.swing.JLabel UnD2Arruelas;
    private javax.swing.JLabel UnDensidade1;
    private javax.swing.JLabel UnDensidade2;
    private javax.swing.JLabel UnEc0m1;
    private javax.swing.JLabel UnFcok1;
    private javax.swing.JLabel UnFcok2;
    private javax.swing.JLabel UnFuk;
    private javax.swing.JLabel UnFv0k1;
    private javax.swing.JLabel UnFyk;
    private javax.swing.JTextField ValorAngulo;
    private javax.swing.JLabel ValorArea;
    private javax.swing.JLabel ValorChapaFuk;
    private javax.swing.JLabel ValorChapaFyk;
    private javax.swing.JLabel ValorD1Arruelas;
    private javax.swing.JLabel ValorD2Arruelas;
    private javax.swing.JLabel ValorDensidade1;
    private javax.swing.JLabel ValorDiametro;
    private javax.swing.JLabel ValorEc0m1;
    private javax.swing.JLabel ValorFc01;
    private javax.swing.JLabel ValorFuk;
    private javax.swing.JLabel ValorFvok1;
    private javax.swing.JLabel ValorFyk;
    private javax.swing.JToggleButton btnCorteDuploAMA;
    private javax.swing.JToggleButton btnCorteDuploMAM;
    private javax.swing.JToggleButton btnCorteSimples;
    private javax.swing.JLabel fuk;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
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
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
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
    private javax.swing.JLabel jLabelStatusAco;
    private javax.swing.JLabel jLabelUnEspessura;
    private javax.swing.JLabel jLabelUnEspessura1;
    private javax.swing.JLabel jLabel_classe_madeira_1;
    private javax.swing.JLabel jLabel_classe_madeira_2;
    private javax.swing.JLabel jLabel_elemento1;
    private javax.swing.JLabel jLabel_elemento2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea teste;
    // End of variables declaration//GEN-END:variables


public int calc(){
    double pi = Math.PI;
    Math.random();
    Math.sqrt(pi);
    
    return 0;
}





}



