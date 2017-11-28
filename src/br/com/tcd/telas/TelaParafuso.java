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

import br.com.tcd.controller.DuasSecoesCorteInclinadaController;
import br.com.tcd.controller.DuasSecoesCorteParaleloController;
import br.com.tcd.controller.DuasSecoesCortePerpendicularController;
import br.com.tcd.controller.UmaSecaoCorteInclinadaController;
import br.com.tcd.controller.UmaSecaoCorteParaleloController;
import br.com.tcd.controller.UmaSecaoCortePerpendicularController;
import br.com.tcd.util.ClsDataHora;

/**
 *
 * @author MarcosVinicius
 */
public class TelaParafuso extends javax.swing.JFrame {
	private static Object dateFormat;

	/**
	 * Creates new form TelaParafuso
	 */
	public double d, d1, d2, t1, t2, forcaaplicada, kmod1, kmod2, kmod3, fe0d1, fe0d2, beta, valorFaxrk, valorFaxrkPrego, valorFaxrkFinal, alfa, Rdmin, Rdlig, rd1, rd2, rd3, rd4, rd5, rd6, Rvd,
	        nparafusos, arruela;
	public int fyd, npar;
	private Boolean s1, s2, apa, ai, ape, m, IncSim1, IncSim2, UmaSecao, DuasSecoes, Paralelo, Inclinado, Perpendicular;
	private int NumParafusos;

	ClsDataHora objDataHora = new ClsDataHora();
	private Map<String, Map<String, Double[]>> normas;

	public TelaParafuso() {
		s1 = false;
		s2 = false;
		apa = false;
		ai = false;
		ape = false;
		m = false;
		alfa = 0;
		d = 0;
		d1 = 0;
		d2 = 0;
		t1 = 0;
		t2 = 0;
		forcaaplicada = 0;
		nparafusos = 0.0;
		kmod1 = 0;
		kmod2 = 0;
		kmod3 = 0;
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
		UmaSecao = false;
		DuasSecoes = false;
		Paralelo = false;
		Inclinado = false;
		//Inclinado2 = false;
		Perpendicular = false;
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
		Inclinado1.setVisible(false);
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
		InclinacaoSim1.setEnabled(false);
		InclinacaoSim2.setEnabled(false);

		//        Espessura1.setInputVerifier(new Verificadores.VerificadorEspessura1(this.jLabelStatus));
		//        Espessura2.setInputVerifier(new Verificadores.VerificadorEspessura2(this.jLabelStatus));
		//        ValorAngulo.setInputVerifier(new Verificadores.VerificadorValorAngulo(this.jLabelStatus));
		//        ComboElem1.setInputVerifier(new Verificadores.VerificadorComboClasseElem1(this.jLabelStatus));
		//        ComboElem2.setInputVerifier(new Verificadores.VerificadorComboClasseElem2(this.jLabelStatus));
		//        ComboKmod1.setInputVerifier(new Verificadores.VerificadorKmod1(this.jLabelStatus));
		//        ComboKmod2.setInputVerifier(new Verificadores.VerificadorKmod2(this.jLabelStatus));
		//        ComboKmod3.setInputVerifier(new Verificadores.VerificadorKmod3(this.jLabelStatus,this.Next2));
		//        ComboQuantParafuso.setInputVerifier(new Verificadores.VerificadorComboQuantParafuso(this.jLabelStatus));
		//        ComboTipoParafuso.setInputVerifier(new Verificadores.VerificadorComboTipoParafuso(this.jLabelStatus));
		//        ComboAco.setInputVerifier(new Verificadores.VerificadorComboAcoParafuso(this.jLabelStatus));
		//        ComboArruelas.setInputVerifier(new Verificadores.VerificadorComboArruelas(this.jLabelStatus));

	}

	private void setNumParafusos(int x) { // alterar a variável global
		NumParafusos = x;

	}

	public int getNumParafusos() {
		return NumParafusos;
	}

	private void inicializaNormas() {
		this.normas = new HashMap<String, Map<String, Double[]>>();

		Map<String, Double[]> parafusos = new HashMap<String, Double[]>();
		normas.put("DIN 436", parafusos);
		parafusos.put("M10", new Double[] {11.0, 30.0, 1.9078, 1.0, 3.0});
		parafusos.put("M12", new Double[] {13.5, 40.0, 1.7391, 1.0, 3.0});
		parafusos.put("M16", new Double[] {17.5, 50.0, 1.5166, 1.0, 3.0});
		parafusos.put("M20", new Double[] {22.0, 60.0, 1.3868, 1.0, 3.0});
		parafusos.put("M22", new Double[] {24.0, 70.0, 1.3338, 1.0, 3.0});
		parafusos.put("M24", new Double[] {26.0, 80.0, 1.2963, 1.0, 3.0});
		parafusos.put("M27", new Double[] {30.0, 90.0, 1.25, 1.0, 3.0});
		parafusos.put("M30", new Double[] {33.0, 95.0, 1.2125, 1.0, 3.0});

		parafusos = new HashMap<String, Double[]>();
		normas.put("DIN 440 R", parafusos);
		parafusos.put("M10", new Double[] {11.0, 34.0, 1.9078, 2.0, 1.0});
		parafusos.put("M12", new Double[] {13.5, 44.0, 1.7391, 2.0, 1.0});
		parafusos.put("M16", new Double[] {17.5, 56.0, 1.5166, 2.0, 1.0});
		parafusos.put("M20", new Double[] {22.0, 72.0, 1.3868, 2.0, 1.0});
		parafusos.put("M22", new Double[] {24.0, 80.0, 1.3338, 2.0, 1.0});
		parafusos.put("M24", new Double[] {26.0, 85.0, 1.2963, 2.0, 1.0});
		parafusos.put("M27", new Double[] {30.0, 98.0, 1.25, 2.0, 1.0});
		parafusos.put("M30", new Double[] {33.0, 105.0, 1.2125, 2.0, 1.0});
		parafusos.put("M33", new Double[] {36.0, 112.0, 1.1805, 2.0, 1.0});
		parafusos.put("M36", new Double[] {39.0, 125.0, 1.1567, 2.0, 1.0});

		parafusos = new HashMap<String, Double[]>();
		normas.put("DIN 440 V", parafusos);
		parafusos.put("M10", new Double[] {11.0, 34.0, 1.9078, 3.0, 2.0});
		parafusos.put("M12", new Double[] {13.5, 44.0, 1.7391, 3.0, 2.0});
		parafusos.put("M16", new Double[] {17.5, 56.0, 1.5166, 3.0, 2.0});
		parafusos.put("M20", new Double[] {22.0, 72.0, 1.3868, 3.0, 2.0});
		parafusos.put("M22", new Double[] {24.0, 80.0, 1.3338, 3.0, 2.0});

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
		btn2SecaoCorte = new javax.swing.JToggleButton();
		Next = new javax.swing.JButton();
		ElementosMadeira = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		ComboElem1 = new javax.swing.JComboBox();
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
		ComboElem2 = new javax.swing.JComboBox();
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
		ComboArruelas = new javax.swing.JComboBox();
		D1Arruelas = new javax.swing.JLabel();
		D2Arruelas = new javax.swing.JLabel();
		ValorD1Arruelas = new javax.swing.JLabel();
		ValorD2Arruelas = new javax.swing.JLabel();
		UnD1Arruelas = new javax.swing.JLabel();
		UnD2Arruelas = new javax.swing.JLabel();
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
		jLabelStatus = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("TCD - Timber Connections Design");
		setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		setIconImages(null);
		setMaximumSize(new java.awt.Dimension(800, 600));
		setName(""); // NOI18N
		setPreferredSize(new java.awt.Dimension(825, 725));
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowActivated(java.awt.event.WindowEvent evt) {
				formWindowActivated(evt);
			}
		});

		jTabbedPane1.setBackground(new java.awt.Color(204, 255, 204));
		jTabbedPane1.setToolTipText("");
		jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTabbedPane1.setPreferredSize(new java.awt.Dimension(900, 700));
		jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusGained(java.awt.event.FocusEvent evt) {
				jTabbedPane1FocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				jTabbedPane1FocusLost(evt);
			}
		});

		Inicio.setBackground(new java.awt.Color(204, 204, 204));

		LogoPrograma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Logo/logosket.png"))); // NOI18N

		jLabel97.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
		jLabel97.setText("TCD - Timber Connections Design");

		Voltar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		Voltar.setText("Voltar");
		Voltar.setToolTipText("Escolha esta opção para retornar a tela inicial.");
		Voltar.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				VoltarActionPerformed(evt);
			}
		});

		jLabel40.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel40.setText("Cálculo de ligações parafusadas entre elementos de madeira.");

		jButton4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jButton4.setText("Iniciar Cálculo");
		jButton4.setToolTipText("Escolha esta opção para iniciar o dimensionamento de sua ligação.");
		jButton4.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout InicioLayout = new javax.swing.GroupLayout(Inicio);
		Inicio.setLayout(InicioLayout);
		InicioLayout.setHorizontalGroup(InicioLayout
		        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(InicioLayout.createSequentialGroup().addContainerGap(186, Short.MAX_VALUE).addGroup(InicioLayout
		                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioLayout.createSequentialGroup()
		                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(93, 93, 93)
		                        .addComponent(Voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(161, 161, 161))
		                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
		                          InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(InicioLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel40))
		                                  .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
		                                                    InicioLayout
		                                                            .createSequentialGroup().addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                            .addGap(242, 242, 242))
		                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioLayout.createSequentialGroup()
		                                                  .addComponent(LogoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(218, 218, 218)))))));
		InicioLayout.setVerticalGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(InicioLayout.createSequentialGroup().addGap(57, 57, 57).addComponent(LogoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel97).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(jLabel40).addGap(78, 78, 78)
		                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(Voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(234, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Inicio", Inicio);

		SecoesCorte.setBackground(new java.awt.Color(204, 204, 204));

		GroupSecoesCorte.add(btn1SecaoCorte);
		btn1SecaoCorte.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		btn1SecaoCorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/UmaSecaoParafuso.png"))); // NOI18N
		btn1SecaoCorte.setText("Corte Simples");
		btn1SecaoCorte.setToolTipText("Escolha esta opção se a ligação apresentar apenas uma seção de corte no parafuso.");
		btn1SecaoCorte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btn1SecaoCorte.setName(""); // NOI18N
		btn1SecaoCorte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		btn1SecaoCorte.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn1SecaoCorteActionPerformed(evt);
			}
		});

		GroupSecoesCorte.add(btn2SecaoCorte);
		btn2SecaoCorte.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		btn2SecaoCorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/DuasSecoeParafuso.png"))); // NOI18N
		btn2SecaoCorte.setText("Corte Duplo");
		btn2SecaoCorte.setToolTipText("Escolha esta opção se sua ligação apresentar duas seções de corte no parafuso.");
		btn2SecaoCorte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btn2SecaoCorte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		btn2SecaoCorte.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn2SecaoCorteActionPerformed(evt);
			}
		});

		Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/Next.png"))); // NOI18N
		Next.setToolTipText("Clique para avançar.");
		Next.setEnabled(false);
		Next.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				NextActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout SecoesCorteLayout = new javax.swing.GroupLayout(SecoesCorte);
		SecoesCorte.setLayout(SecoesCorteLayout);
		SecoesCorteLayout.setHorizontalGroup(SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(SecoesCorteLayout.createSequentialGroup().addGap(111, 111, 111)
		                .addComponent(btn1SecaoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(75, 75, 75)
		                .addComponent(btn2SecaoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(134, Short.MAX_VALUE))
		        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SecoesCorteLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(Next, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)));
		SecoesCorteLayout.setVerticalGroup(SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(SecoesCorteLayout.createSequentialGroup().addGap(39, 39, 39)
		                .addGroup(SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                        .addComponent(btn2SecaoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(btn1SecaoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(39, 39, 39).addComponent(Next).addContainerGap(249, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Modelos de Ligação", SecoesCorte);

		ElementosMadeira.setBackground(new java.awt.Color(204, 204, 204));
		ElementosMadeira.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jPanel7.setBackground(new java.awt.Color(153, 153, 153));
		jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel7.setPreferredSize(new java.awt.Dimension(385, 225));
		jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		ComboElem1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ComboElem1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Escolha a Classe da Madeira", "C-20", "C-25", "C-30", "D-20", "D-30", "D-40", "D-50", "D-60", " "}));
		ComboElem1.setToolTipText("Defina a classe de madeira do elemento 1, baseado nas tabelas 2 e 3 da revisão da norma ABNT NBR 7190 (2011).");
		ComboElem1.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboElem1ActionPerformed(evt);
			}
		});
		ComboElem1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				ComboElem1PropertyChange(evt);
			}
		});
		jPanel7.add(ComboElem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 35, 211, 29));

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
		ValorAngulo.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusGained(java.awt.event.FocusEvent evt) {
				ValorAngulo(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				ValorAnguloFocusLost(evt);
			}
		});
		ValorAngulo.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ValorAnguloActionPerformed(evt);
			}
		});
		ValorAngulo.addPropertyChangeListener(new java.beans.PropertyChangeListener(){
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				ValorAnguloPropertyChange(evt);
			}
		});
		ValorAngulo.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyTyped(java.awt.event.KeyEvent evt) {
				ValorAnguloKeyTyped(evt);
			}
		});
		jPanel7.add(ValorAngulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 70, -1));

		jLabelUnEspessura.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabelUnEspessura.setText("(mm)");
		jPanel7.add(jLabelUnEspessura, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 147, -1, -1));

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
		InclinacaoSim1.setToolTipText("Se existir inclinação, indique qual elemento está inclinado.");
		InclinacaoSim1.addActionListener(new java.awt.event.ActionListener(){
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
		Espessura1.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusGained(java.awt.event.FocusEvent evt) {
				Espessura1(evt);
			}
		});
		Espessura1.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Espessura1ActionPerformed(evt);
			}
		});
		Espessura1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				Espessura1PropertyChange(evt);
			}
		});
		Espessura1.addKeyListener(new java.awt.event.KeyAdapter(){
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

		ComboElem2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ComboElem2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Escolha a Classe da Madeira", "C-20", "C-25", "C-30", "D-20", "D-30", "D-40", "D-50", "D-60", " "}));
		ComboElem2.setToolTipText("Defina a classe de madeira do elemento 2, baseado nas tabelas 2 e 3 da revisão da norma ABNT NBR 7190 (2017).");
		ComboElem2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboElem2ActionPerformed(evt);
			}
		});
		ComboElem2.addPropertyChangeListener(new java.beans.PropertyChangeListener(){
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				ComboElem2PropertyChange(evt);
			}
		});
		jPanel13.add(ComboElem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 30, 211, 29));

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
		Espessura2.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusGained(java.awt.event.FocusEvent evt) {
				LimparExpessura2(evt);
			}
		});
		Espessura2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Espessura2ActionPerformed(evt);
			}
		});
		Espessura2.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyTyped(java.awt.event.KeyEvent evt) {
				Espessura2KeyTyped(evt);
			}
		});
		jPanel13.add(Espessura2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 138, 126, -1));

		jLabelUnEspessura1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabelUnEspessura1.setText("(mm)");
		jPanel13.add(jLabelUnEspessura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 140, -1, -1));

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
		InclinacaoSim2.addActionListener(new java.awt.event.ActionListener(){
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
		MadeiraFigura.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				MadeiraFiguraActionPerformed(evt);
			}
		});
		ElementosMadeira.add(MadeiraFigura, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 350, 280));

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
		ComboKmod1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Escolha o Kmod 1", "------ Madeira Serrada/Roliça/Laminada Colada/Compensada -----", "Permanente", "Longa Duração",
		                                                                       "Média Duração", "Curta Duração", "Duração Instantânea", "------ Madeira Recomposta -----", " Permanente",
		                                                                       " Longa Duração ", " Média Duração ", " Curta Duração", " Duração Instantânea", "Outro"}));
		ComboKmod1.setToolTipText("Insira o valor do Kmod 1, o qual é definido pela tabela 4 da revisão da norma ABNT NBR 7190 (2011).");
		ComboKmod1.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboKmod1ActionPerformed(evt);
			}
		});
		jPanel12.add(ComboKmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 340, 30));

		ComboKmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ComboKmod3
		        .setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Escolha o Kmod 3", "----- Folhosas  -----", "SE - Classificação Visual", "S1 - Classificação Visual",
		                                                                     "S2 - Classificação Visual", "S3 - Classificação Visual", "SE - Classificação Visual e Mecânica",
		                                                                     "S1 - Classificação Visual e Mecânica", "S2 - Classificação Visual e Mecânica", "S3 - Classificação Visual e Mecânica",
		                                                                     "NC - Não Classificada", "------ Coníferas  -----", "SE - D - Classificação Visual", "S1 - D - Classificação Visual",
		                                                                     "S2 - D - Classificação Visual", "S3 - D - Classificação Visual", "SE - ND - Classificação Visual",
		                                                                     "S1 - ND - Classificação Visual", "S2 - ND - Classificação Visual", "S3 - ND - Classificação Visual",
		                                                                     "SE - D - Classificação Visual e Mecânica", "S1 - D - Classificação Visual e Mecânica",
		                                                                     "S2 - D - Classificação Visual e Mecânica", "S3 - D - Classificação Visual e Mecânica",
		                                                                     "SE - ND - Classificação Visual e Mecânica", "S1 - ND - Classificação Visual e Mecânica", "S2 - ND", "S3 - ND", "Outro"}));
		ComboKmod3.setToolTipText("Insira o valor do Kmod 3, o qual é definido pelas tabelas 6 e 7 da revisão da norma ABNT NBR 7190 (2011).");
		ComboKmod3.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboKmod3ActionPerformed(evt);
			}
		});
		jPanel12.add(ComboKmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 340, 30));

		ComboKmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ComboKmod2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Escolha o Kmod 2", "------ Madeira Serrada/Roliça/Laminada Colada/Compensada -----", "Classe de Umidade 1",
		                                                                       "Classe de Umidade 2", "Classe de Umidade 3", "Classe de Umidade 4", "------ Madeira Recomposta -----",
		                                                                       " Classe de Umidade 1", " Classe de Umidade 2", " Classe de Umidade 3", " Classe de Umidade 4", "Outro"}));
		ComboKmod2.setToolTipText("Insira o valor do Kmod 2, o qual é definido pela tabela 5 da revisão da norma ABNT NBR 7190 (2011).");
		ComboKmod2.addActionListener(new java.awt.event.ActionListener(){
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

		Next2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/Next.png"))); // NOI18N
		Next2.setToolTipText("Clique para avançar.");
		Next2.setEnabled(false);
		Next2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Next2ActionPerformed(evt);
			}
		});
		ElementosMadeira.add(Next2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 570, 40, -1));

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
		ComboTipoParafuso.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Escolha o Tipo de Parafuso", "M10", "M12", "M16", "M20", "M22", "M24", "M27", "M30", "M33", "M36"}));
		ComboTipoParafuso.setToolTipText("Escolha o tipo de parafuso utilizado, baseado na norma EN ISO 4016 (2001).");
		ComboTipoParafuso.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		ComboTipoParafuso.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusLost(java.awt.event.FocusEvent evt) {
				ComboTipoParafusoFocusLost(evt);
			}
		});
		ComboTipoParafuso.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboTipoParafusoActionPerformed(evt);
			}
		});
		jPanel10.add(ComboTipoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 41, 252, -1));

		Diametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		Diametro.setText("Diâmetro (mm):");
		jPanel10.add(Diametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 72, -1, -1));

		ValorDiametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ValorDiametro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		ValorDiametro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.add(ValorDiametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 40, 20));

		AreaParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		AreaParafuso.setText("Área do Parafuso (mm²):");
		jPanel10.add(AreaParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 72, -1, -1));

		ValorArea.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ValorArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		ValorArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.add(ValorArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 40, 20));

		NumeroParafusos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		NumeroParafusos.setText("Número de Parafusos:");
		jPanel10.add(NumeroParafusos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 102, -1, -1));

		ClasseAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ClasseAco.setText("Classe do Aço:");
		jPanel10.add(ClasseAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 142, -1, -1));

		ComboAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ComboAco.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Escolha a Classe de Aço", "ISO 898-1 - Classe 4.6", "ISO 898-1 - Classe 8.8", "ISO 898-1 - Classe 10.9"}));
		ComboAco.setToolTipText("Defina a classe do aço do parafuso, baseado na norma ABNT NBR 8800 (2008).");
		ComboAco.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboAcoActionPerformed(evt);
			}
		});
		jPanel10.add(ComboAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 139, 221, -1));

		Fyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		Fyk.setText("fy,k:");
		jPanel10.add(Fyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 177, -1, -1));

		fuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		fuk.setText("fu,k:");
		jPanel10.add(fuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 177, -1, -1));

		ValorFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ValorFyk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		ValorFyk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.add(ValorFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 175, 40, 20));

		ValorFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ValorFuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		ValorFuk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.add(ValorFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 175, 40, 20));

		UnFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		UnFyk.setText("(MPa)");
		jPanel10.add(UnFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 177, -1, -1));

		UnFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		UnFuk.setText("(MPa)");
		jPanel10.add(UnFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 177, -1, -1));

		TesteParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		TesteParafuso.setText("Foram realizados ensaios na ligação em estudo que comprovem ");
		TesteParafuso.setToolTipText("Considera-se ou não a força de arrancamento causada pelo parafuso na madeira. EUROCODE 5 (2004);");
		jPanel10.add(TesteParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

		GroupTesteParafuso.add(TesteParafusoSim);
		TesteParafusoSim.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		TesteParafusoSim.setText("Sim");
		TesteParafusoSim.setToolTipText("Escolha se a ligação considera ou não a força de arrancamento causada pelo parafuso na madeira.");
		TesteParafusoSim.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TesteParafusoSimActionPerformed(evt);
			}
		});
		jPanel10.add(TesteParafusoSim, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 235, 50, -1));

		GroupTesteParafuso.add(TesteParafusoNao);
		TesteParafusoNao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		TesteParafusoNao.setText("Não");
		TesteParafusoNao.setToolTipText("Escolha se a ligação considera ou não a força de arrancamento causada pelo parafuso na madeira.");
		TesteParafusoNao.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TesteParafusoNaoActionPerformed(evt);
			}
		});
		jPanel10.add(TesteParafusoNao, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 235, 50, -1));

		ComboQuantParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ComboQuantParafuso.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Escolha a quantidade de parafusos", "2", "4", "5", "6", "8", "10", "12", "14"}));
		ComboQuantParafuso.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboQuantParafusoActionPerformed(evt);
			}
		});
		jPanel10.add(ComboQuantParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 230, -1));

		jLabel65.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel65.setText("que o efeito de confinamento possa ser utilizado?");
		jPanel10.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 215, -1, -1));

		ElementosMetalicos.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 42, 425, 265));

		jPanel11.setBackground(new java.awt.Color(153, 153, 153));
		jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		Arruelas.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
		Arruelas.setText("ARRUELAS");
		jPanel11.add(Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 13, -1, -1));

		TipoArruela.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		TipoArruela.setText("Tipo de Arruela:");
		jPanel11.add(TipoArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 48, -1, -1));

		ComboArruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ComboArruelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Escolha a Arruela"}));
		ComboArruelas.setToolTipText("Defina o tipo de arruela, baseado nas normas DIN 440 R, DIN 440 V e DIN 436.");
		ComboArruelas.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboArruelasActionPerformed(evt);
			}
		});
		jPanel11.add(ComboArruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 45, 226, -1));

		D1Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		D1Arruelas.setText("Dimensão (d1):");
		jPanel11.add(D1Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 97, -1, -1));

		D2Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		D2Arruelas.setText("Dimensão (d2):");
		jPanel11.add(D2Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 97, -1, -1));

		ValorD1Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ValorD1Arruelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		ValorD1Arruelas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel11.add(ValorD1Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 95, 40, 20));

		ValorD2Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ValorD2Arruelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		ValorD2Arruelas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel11.add(ValorD2Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 95, 40, 20));

		UnD1Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		UnD1Arruelas.setText("(mm)");
		jPanel11.add(UnD1Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 97, -1, -1));

		UnD2Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		UnD2Arruelas.setText("(mm)");
		jPanel11.add(UnD2Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 97, -1, -1));

		ElementosMetalicos.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 320, 425, 130));

		ButtonCalcular.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
		ButtonCalcular.setText("CALCULAR LIGAÇÃO");
		ButtonCalcular.setToolTipText("Clique aqui para calcular sua ligação. Fique atento! Caso haja informações faltantes ou inconsistentes, o cálculo não será realizado e aparecerá uma mensagem.");
		ButtonCalcular.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ButtonCalcularActionPerformed(evt);
			}
		});
		ElementosMetalicos.add(ButtonCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 468, 210, 41));

		FiguraTipoParafuso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Imagens/Parafuso.png"))); // NOI18N
		FiguraTipoParafuso.setContentAreaFilled(false);
		FiguraTipoParafuso.addActionListener(new java.awt.event.ActionListener(){
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
		Resultado.add(LabelModeloFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 120, 20));

		LabelResultadoModeloFalha.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		Resultado.add(LabelResultadoModeloFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 660, 20));

		LabelResistenciaLigacao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		LabelResistenciaLigacao.setText("Fv,Rk:");
		Resultado.add(LabelResistenciaLigacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 60, 20));

		ResultadoFvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ResultadoFvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		Resultado.add(ResultadoFvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 80, 20));

		FiguraResultadoModoFalha.setContentAreaFilled(false);
		FiguraResultadoModoFalha.addActionListener(new java.awt.event.ActionListener(){
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
		jLabel98.setText("*Fv,Rk = resistência característica de uma seção de corte por parafuso.");
		Resultado.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 450, -1));

		jLabel99.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel99.setText("*Rk = resistência característica da ligação considerando as quantidades de seções de corte e parafusos. ");
		Resultado.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 670, -1));

		jLabel100.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel100.setText("*Rd = resistência de cálculo da ligação.");
		Resultado.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 670, -1));

		Next3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/Next.png"))); // NOI18N
		Next3.setToolTipText("Clique para avançar.");
		Next3.setEnabled(false);
		Next3.addActionListener(new java.awt.event.ActionListener(){
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
		jLabel12.setText("ELEMENTOS DA LIGAÇÃO");
		RelatorioFinal.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 145, -1, -1));

		jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
		jLabel13.setText("RELATÓRIO DE RESISTÊNCIA DA LIGAÇÃO ");
		RelatorioFinal.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 39, -1, -1));

		RelatorioSecaoCorte.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		RelatorioSecaoCorte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		RelatorioFinal.add(RelatorioSecaoCorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 190, 14));

		RelatorioAngulacao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		RelatorioAngulacao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		RelatorioFinal.add(RelatorioAngulacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 180, 14));
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
		jLabel90.setText("kmod3:");
		RelatorioCoeficientes.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 75, 50, -1));

		jLabel91.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel91.setText("kmod2");
		RelatorioCoeficientes.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 50, 50, -1));

		jLabel92.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel92.setText("Ângulo:");
		RelatorioCoeficientes.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 100, 50, 20));

		jLabel93.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel93.setText("Coeficientes");
		RelatorioCoeficientes.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));

		jLabel94.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel94.setText("kmod1:");
		RelatorioCoeficientes.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 25, 50, -1));

		jLabel35.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel35.setText("γm,ligação:");
		RelatorioCoeficientes.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 125, -1, -1));

		jLabel81.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel81.setText("1,4");
		RelatorioCoeficientes.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 125, -1, -1));

		RelatorioFinal.add(RelatorioCoeficientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 175, 180, 150));

		jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel17.setText("CONECTORES");
		RelatorioFinal.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 336, -1, -1));

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
		RelatorioParafuso.add(RelatorioNParafusos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 25, 14));

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
		RelatorioParafuso.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 10, 14));

		RelatorioFinal.add(RelatorioParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 355, 335, 135));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel47.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel47.setText("Arruela");
		jPanel1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));

		jLabel48.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel48.setText("Tipo de arruela:");
		jPanel1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

		jLabel49.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel49.setText("d1:");
		jPanel1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, 20, 14));

		jLabel50.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel50.setText("d2:");
		jPanel1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 95, -1, -1));

		RelatorioTipoArruela.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		RelatorioTipoArruela.setText("jLabel51");
		jPanel1.add(RelatorioTipoArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, -1, -1));

		RelatorioD1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		RelatorioD1.setText("jLabel51");
		jPanel1.add(RelatorioD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 95, 35, 14));

		RelatorioD2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		RelatorioD2.setText("jLabel51");
		jPanel1.add(RelatorioD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 95, 35, 14));

		FiguraArruela.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		FiguraArruela.setText("jLabel74");
		jPanel1.add(FiguraArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, 50));

		jLabel75.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel75.setText("(mm)");
		jPanel1.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 95, 30, -1));

		jLabel76.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel76.setText("(mm)");
		jPanel1.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 95, 40, -1));

		jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 95, 20, 14));

		RelatorioFinal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 355, 230, 135));
		RelatorioFinal.add(RelatorioFibras, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 313, -1, -1));

		jLabel44.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel44.setText("VALORES   CALCULADOS");
		RelatorioFinal.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 505, -1, -1));

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

		CalculoForcaAlfa2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
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

		jLabel67.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel67.setText("RESULTADO:");
		RelatorioFinal.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 655, -1, -1));

		ModoFalha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		RelatorioFinal.add(ModoFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, 170, 120));

		jLabel87.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel87.setText("Tipo de ruptura:");
		RelatorioFinal.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 670, -1, -1));

		jLabel95.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel95.setText("Rk:");
		RelatorioFinal.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 740, 70, -1));

		RelatorioRk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		RelatorioRk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		RelatorioFinal.add(RelatorioRk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 740, 70, 15));
		RelatorioFinal.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 600, 10));
		RelatorioFinal.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 135, 600, 10));

		FiguraSecoes.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		FiguraSecoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		RelatorioFinal.add(FiguraSecoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 60, 80, 70));
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
		Consideracao1
		        .setText("*Fv,Rk = resistência característica de uma seção de corte por parafuso.\n*Rk = resistência característica da ligação considerando as quantidades de seções de corte e parafusos.\n*Rd = resistência de cálculo da ligação.");
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
		jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/Print.png"))); // NOI18N
		jButton1.setToolTipText("Clique para imprimir o relatório.");
		jButton1.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/New.png"))); // NOI18N
		jButton2.setToolTipText("Clique para realizar novo cálculo.");
		jButton2.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				jButton2MouseReleased(evt);
			}
		});
		jButton2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/Return.png"))); // NOI18N
		jButton3.setToolTipText("Clique para retornar a tela inicial.");
		jButton3.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout RelatorioLayout = new javax.swing.GroupLayout(Relatorio);
		Relatorio.setLayout(RelatorioLayout);
		RelatorioLayout.setHorizontalGroup(RelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(RelatorioLayout.createSequentialGroup().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 30,
		                                                                                                                                                                                 Short.MAX_VALUE))
		        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RelatorioLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
		RelatorioLayout.setVerticalGroup(RelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(RelatorioLayout.createSequentialGroup().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addGroup(RelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(170, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Relatório", Relatorio);

		Relatorio.setVisible(false);

		getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

		jLabelStatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelStatus.setText("Clique em \"Iniciar Cálculo\" para começar o dimensionamento.");
		jLabelStatus.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow"), 1, true));
		jLabelStatus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jLabelStatus.setMaximumSize(new java.awt.Dimension(601, 30));
		jLabelStatus.setMinimumSize(new java.awt.Dimension(601, 30));
		jLabelStatus.setPreferredSize(new java.awt.Dimension(601, 30));
		getContentPane().add(jLabelStatus, java.awt.BorderLayout.SOUTH);

		getAccessibleContext().setAccessibleName("ProgramaMarcos");

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
		// TODO add your handling code here:
		Relatorio.setVisible(false);
	}//GEN-LAST:event_formWindowActivated

	private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
		// TODO add your handling code here:

		if(jTabbedPane1.getSelectedIndex() == 2) {

			ValorAngulo.setText("0");
			MadeiraFigura.setVisible(true);
			String FiguraMadeira = "";

			if(s1) {

				FiguraMadeira = "1Paralelo.png";

			}
			if(s2) {

				FiguraMadeira = "2Paralelo.png";
			}

			MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/" + FiguraMadeira))); // NOI18N

		}
	}//GEN-LAST:event_jTabbedPane1FocusGained

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
		// TODO add your handling code here:
		this.dispose();
		TelaInicial telainicial = new TelaInicial();
		telainicial.setVisible(true);
	}//GEN-LAST:event_jButton3ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		// TODO add your handling code here:
		jTabbedPane1.setSelectedComponent(Inicio);
		GroupSecoesCorte.clearSelection();
		GroupTesteParafuso.clearSelection();
		Espessura1.setText("Digite a espessura");
		Espessura2.setText("Digite a espessura");
		ValorAngulo.setText("0");
		ComboQuantParafuso.setSelectedIndex(0);
		ComboTipoParafuso.setSelectedIndex(0);
		ComboAco.setSelectedIndex(0);
		ComboElem1.setSelectedIndex(0);
		ComboElem2.setSelectedIndex(0);
		ComboKmod1.setSelectedIndex(0);
		ComboKmod2.setSelectedIndex(0);
		ComboKmod3.setSelectedIndex(0);
		jLabelStatus.setText("Clique em \"Iniciar Cálculo\" para começar o dimensionamento.");
		s1 = false;
		s2 = false;
		apa = false;
		ai = false;
		ape = false;
		m = false;
		alfa = 0;
		d = 0;
		d1 = 0;
		d2 = 0;
		t1 = 0;
		t2 = 0;
		forcaaplicada = 0;
		nparafusos = 0.0;
		kmod1 = 0;
		kmod2 = 0;
		kmod3 = 0;
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
		UmaSecao = false;
		DuasSecoes = false;
		Paralelo = false;
		Inclinado = false;
		Perpendicular = false;

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
		InclinacaoSim1.setEnabled(false);
		InclinacaoSim2.setEnabled(false);

	}//GEN-LAST:event_jButton2ActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		Printable p = new Printable(){

			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				if(pageIndex > 0) { /* We have only one page, and 'page' is zero-based */
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

		if(ok) {
			try {
				job.print();
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
			}
		}
	}//GEN-LAST:event_jButton1ActionPerformed

	private void FiguraResultadoModoFalhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiguraResultadoModoFalhaActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_FiguraResultadoModoFalhaActionPerformed

	private void FiguraTipoParafusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiguraTipoParafusoActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_FiguraTipoParafusoActionPerformed

	private void ButtonCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCalcularActionPerformed
		Espessura1.getInputVerifier().shouldYieldFocus(Espessura1);
		Espessura2.getInputVerifier().shouldYieldFocus(Espessura2);
		ValorAngulo.getInputVerifier().shouldYieldFocus(ValorAngulo);
		ComboElem1.getInputVerifier().shouldYieldFocus(ComboElem1);
		ComboElem2.getInputVerifier().shouldYieldFocus(ComboElem2);
		ComboKmod1.getInputVerifier().shouldYieldFocus(ComboKmod1);
		ComboKmod2.getInputVerifier().shouldYieldFocus(ComboKmod2);
		ComboKmod3.getInputVerifier().shouldYieldFocus(ComboKmod3);
		ComboQuantParafuso.getInputVerifier().shouldYieldFocus(ComboQuantParafuso);
		ComboTipoParafuso.getInputVerifier().shouldYieldFocus(ComboTipoParafuso);
		ComboAco.getInputVerifier().shouldYieldFocus(ComboAco);
		ComboArruelas.getInputVerifier().shouldYieldFocus(ComboArruelas);

		if(ButtonCalcular.hasFocus()) {

			kmod1 = Double.parseDouble(Textkmod1.getText().replace(",", "."));
			kmod2 = Double.parseDouble(Textkmod2.getText().replace(",", "."));
			kmod3 = Double.parseDouble(Textkmod3.getText().replace(",", "."));

			Data.setText(objDataHora.MostraData());
			Hora.setText(objDataHora.MostraHora());

			FiguraArruela.setIcon(new ImageIcon(((ImageIcon)ImagemTipoArruela.getIcon()).getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH)));
			jLabelStatus.setText("Clique em avançar para continuar.");
			jTabbedPane1.setSelectedComponent(Resultado);
			jTabbedPane1.setEnabledAt(4, true);
			Next3.setEnabled(true);

			//Entradas madeiras
			double fc0k1 = Integer.parseInt(ValorFc01.getText());
			int fv0k1 = Integer.parseInt(ValorFvok1.getText());
			int ec0m1 = Integer.parseInt(ValorEc0m1.getText());
			t1 = Integer.parseInt(Espessura1.getText());
			double fc0k2 = Integer.parseInt(ValorFc2.getText());
			int fv0k2 = Integer.parseInt(ValorFvok2.getText());
			int ec0m2 = Integer.parseInt(ValorEc0m2.getText());
			t2 = Integer.parseInt(Espessura2.getText());
			double falfa1 = 0.0;
			double falfa2 = 0.0;

			if(npar > 8) {
				nparafusos = (8.0 + ((2.0 / 3.0) * (npar - 8.0)));
			} else {
				nparafusos = npar;
			}

			//Entradas coeficientes

			double angulograu = Double.parseDouble(ValorAngulo.getText().replace(",", "."));
			double angulo = ((angulograu * Math.PI) / 180);

			RelatorioKmod1.setText(Textkmod1.getText());
			Relatoriokmod2.setText(Textkmod2.getText());
			Relatoriokmod3.setText(Textkmod3.getText());
			RelatorioEspessura1.setText(Espessura1.getText());
			RelatorioEspessura2.setText(Espessura2.getText());
			RelatorioAngulo.setText(ValorAngulo.getText());

			// ELEMENTOS PARA VERIFICAR O QUE O PROGRAMA ESTÁ FAZENDO - RELACIONADO A OBTENÇÃO DE DADOS
			System.out.println("Fc0k1=" + fc0k1);
			System.out.println("Fv0k1=" + fv0k1);
			System.out.println("Ec0m1=" + ec0m1);
			System.out.println("t1=" + t1);
			System.out.println("Fc0k2=" + fc0k2);
			System.out.println("Fv0k2=" + fv0k2);
			System.out.println("Ec0m2=" + ec0m2);
			System.out.println("t2=" + t2);
			System.out.println("kmod1=" + kmod1);
			System.out.println("kmod2=" + kmod2);
			System.out.println("kmod3=" + kmod3);
			System.out.println("Ângulo=" + angulo);
			//RelatorioFcok.setText(""+fc0k1);

			//Entradas Parafusos
			d = Double.parseDouble(ValorDiametro.getText().replace(",", "."));
			int fyk = Integer.parseInt(ValorFyk.getText());
			int fuk = Integer.parseInt(ValorFuk.getText());
			//        int nparafusos = npar;;
			d1 = Double.parseDouble(ValorD1Arruelas.getText().replace(",", "."));
			d2 = Double.parseDouble(ValorD2Arruelas.getText().replace(",", "."));

			beta = fc0k2 / fc0k1;
			fe0d1 = fc0k1;
			fe0d2 = fc0k2;
			double fe90d1 = (0.25 * fe0d1 * alfa);
			double fe90d2 = (0.25 * fe0d2 * alfa);

			// ELEMENTOS PARA VERIFICAR O QUE O PROGRAMA ESTÁ FAZENDO - RELACIONADO A OBTENÇÃO DE DADOS
			System.out.println("d=" + d);
			System.out.println("Numero Parafusos=" + nparafusos);
			System.out.println("Fyk=" + fyk);
			System.out.println("Fuk=" + fuk);
			System.out.println("d1=" + d1);
			System.out.println("d2=" + d2);
			System.out.println("beta=" + beta);
			System.out.println("Fe0d1=" + fe0d1);
			System.out.println("Fe0d2=" + fe0d2);
			System.out.println("Fe90d1=" + fe90d1);
			System.out.println("Fe90d2=" + fe90d2);
			System.out.println("alfa=" + alfa);

			String Tipo = "";
			String ImagemFalha = "";

			//INICIA-SE O CÁLCULO DA LIGAÇÃO PARA UMA SEÇÃO DE CORTE PARALELA

			if(s1 && apa) {
				UmaSecaoCorteParaleloController resultado = new UmaSecaoCorteParaleloController();//Pegar os valores das outras classes
				//Considerar ou não o valor de FaxRk
				// jLabel86.setIcon();
				if(m) {
					valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
					valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

				}

				if(valorFaxrk > 0) {
					valorFaxrkFinal = valorFaxrk;

				}

				if(valorFaxrkPrego > 0) {
					valorFaxrkFinal = valorFaxrkPrego;

				}

				//double FaxRk = resultado.ValorFaxrk(d, fyd, d2, d1, fe0d1);
				double Myd = resultado.ValorMyd(fuk, d);
				rd1 = resultado.EmbMenorPeca(fe0d1, t1, d);
				rd2 = resultado.EmbMaiorPeca(fe0d1, t2, d, beta);
				rd3 = resultado.EmbDuasPecas(fe0d1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
				rd4 = resultado.DeformPinoMaiorPeca(fe0d1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
				rd5 = resultado.DeformPinoMenorPeca(fe0d1, t2, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
				rd6 = resultado.DeformPinoNasDuas(beta, Myd, fe0d1, d, valorFaxrk, valorFaxrkPrego);
				System.out.println("Abriu = 1 Seção de Corte Paralela!");
				System.out.println("Faxrk =" + valorFaxrk);
				System.out.println("Myd =" + Myd);
				System.out.println("Rd1 =" + rd1);
				System.out.println("Rd2 =" + rd2);
				System.out.println("Rd3 =" + rd3);
				System.out.println("Rd4 =" + rd4);
				System.out.println("Rd5 =" + rd5);
				System.out.println("Rd6 =" + rd6);

				//Verificar o menor valor de resistência

				Rdmin = rd1;
				Tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
				ImagemFalha = "1.1.png";

				if(rd2 < Rdmin || rd2 == Rdmin) {
					Rdmin = rd2;
					Tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
					ImagemFalha = "1.2.png";

				}
				if(rd3 < Rdmin) {
					Rdmin = rd3;
					Tipo = "Embutimento do pino metálico nos dois elementos de madeira";
					ImagemFalha = "1.3.png";
				}
				if(rd4 < Rdmin) {
					Rdmin = rd4;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2 de madeira";
					ImagemFalha = "1.4.png";
				}
				if(rd5 < Rdmin) {
					Rdmin = rd5;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 1 de madeira.";
					ImagemFalha = "1.5.png";
				}
				if(rd6 < Rdmin) {
					Rdmin = rd6;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos de madeira.";
					ImagemFalha = "1.6.png";
				}
				System.out.println("Rdmin =" + Rdmin);
				System.out.println("Tipo =" + Tipo);
				LabelResultadoModeloFalha.setText(Tipo);

				//Calcular o valor total da ligação
				Rdlig = nparafusos * 1 * Rdmin;
				Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

				System.out.println("Rdlig =" + Rdlig);

				RelatorioFc0d1.setText(String.format("%.1f", fe0d1));
				RelatorioFc0d2.setText(String.format("%.1f", fe0d2));
				jScrollPane2.setVisible(true);

				Inclinado1.setVisible(false);
				Inclinado2.setVisible(false);

				RelatorioRd13.setVisible(true);
				RelatorioRd14.setVisible(true);
				RelatorioRd3.setVisible(true);
				RelatorioRd4.setVisible(true);
				jLabel60.setVisible(true);
				jLabel62.setVisible(true);
				RelatorioRd15.setVisible(true);
				RelatorioRd16.setVisible(true);
				RelatorioRd23.setVisible(false);
				RelatorioRd24.setVisible(false);

				//PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO
				RelatorioRd1.setText(String.format("%.0f", rd1));
				RelatorioRd2.setText(String.format("%.0f", rd2));
				RelatorioRd3.setText(String.format("%.0f", rd3));
				RelatorioRd4.setText(String.format("%.0f", rd4));
				RelatorioRd5.setText(String.format("%.0f", rd5));
				RelatorioRd6.setText(String.format("%.0f", rd6));
				RelatorioMyd.setText(String.format("%.0f", Myd));
				RelatorioBeta.setText(String.format("%.3f", beta));
				RelatorioFvk.setText(String.format("%.0f", Rdmin));
				ResultadoFvk.setText(String.format("%.0f", Rdmin));
				RelatorioRk.setText(String.format("%.0f", Rdlig));
				ResultadoRk.setText(String.format("%.0f", Rdlig));
				RelatorioFaxrk.setText(String.format("%.0f", valorFaxrk));
				RelatorioRd.setText(String.format("%.0f", Rvd));
				ResultadoRd.setText(String.format("%.0f", Rvd));
				teste.setText(Tipo);
			}

			//INICIA-SE O CÁLCULO DA LIGAÇÃO PARA UMA SEÇÃO DE CORTE INCLINADA
			if(s1 && ai) {
				UmaSecaoCorteInclinadaController resultado = new UmaSecaoCorteInclinadaController();//Pegar os valores das outras classes
				//Considerar ou não o valor de FaxRk
				if(m) {
					valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
					valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

				}

				if(valorFaxrk > 0) {
					valorFaxrkFinal = valorFaxrk;

				}

				if(valorFaxrkPrego > 0) {
					valorFaxrkFinal = valorFaxrkPrego;

				}

				//Valor utilizado de força
				if(IncSim1) {
					falfa2 = ((fe0d2 * fe90d2) / ((fe0d2 * (Math.pow(Math.sin(angulo), 2))) + (fe90d2 * (Math.pow(Math.cos(angulo), 2)))));
					falfa1 = fe0d1;
					Inclinado2.setVisible(true);
					Inclinado1.setVisible(false);
				}
				if(IncSim2) {
					falfa1 = ((fe0d1 * fe90d1) / ((fe0d1 * (Math.pow(Math.sin(angulo), 2))) + (fe90d1 * (Math.pow(Math.cos(angulo), 2)))));
					falfa2 = fe0d2;
					Inclinado2.setVisible(false);
					Inclinado1.setVisible(true);
				}
				beta = falfa2 / falfa1;
				//double FaxRk = resultado.ValorFaxrk(d, fyd, d2, d1, fe0d1);
				double Myd = resultado.ValorMyd(fuk, d);
				rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
				rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
				rd3 = resultado.EmbDuasPecas(falfa1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
				rd4 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
				rd5 = resultado.DeformPinoMenorPeca(falfa1, t2, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
				rd6 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);
				System.out.println("Abriu = 1 Seção de Corte Perpendicular!");
				System.out.println("Faxrk =" + valorFaxrkFinal);
				System.out.println("Myd =" + Myd);
				System.out.println("Rd1 =" + rd1);
				System.out.println("Rd2 =" + rd2);
				System.out.println("Rd3 =" + rd3);
				System.out.println("Rd4 =" + rd4);
				System.out.println("Rd5 =" + rd5);
				System.out.println("Rd6 =" + rd6);
				System.out.println("Falfa1 =" + falfa1);
				System.out.println("Falfa2 =" + falfa2);

				//Verificar o menor valor de resistência

				Rdmin = rd1;
				Tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
				ImagemFalha = "1.1.png";

				if(rd2 < Rdmin || rd2 == Rdmin) {
					Rdmin = rd2;
					Tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
					ImagemFalha = "1.2.png";
				}
				if(rd3 < Rdmin) {
					Rdmin = rd3;
					Tipo = "Embutimento do pino metálico nos dois elementos de madeira.";
					ImagemFalha = "1.3.png";
				}
				if(rd4 < Rdmin) {
					Rdmin = rd4;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2 de madeira.";
					ImagemFalha = "1.4.png";
				}
				if(rd5 < Rdmin) {
					Rdmin = rd5;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 1 de madeira.";
					ImagemFalha = "1.5.png";
				}
				if(rd6 < Rdmin) {
					Rdmin = rd6;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos de madeira.";
					ImagemFalha = "1.6.png";
				}
				System.out.println("Rdmin =" + Rdmin);
				System.out.println("Tipo =" + Tipo);
				LabelResultadoModeloFalha.setText(Tipo);

				RelatorioFc0d1.setText(String.format("%.1f", falfa1));
				RelatorioFc0d2.setText(String.format("%.1f", falfa2));

				RelatorioRd1.setText(String.format("%.0f", rd1));
				RelatorioRd2.setText(String.format("%.0f", rd2));
				RelatorioRd3.setText(String.format("%.0f", rd3));
				RelatorioRd4.setText(String.format("%.0f", rd4));
				RelatorioRd5.setText(String.format("%.0f", rd5));
				RelatorioRd6.setText(String.format("%.0f", rd6));

				//Calcular o valor total da ligação
				Rdlig = nparafusos * 1 * Rdmin;
				Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

				System.out.println("Rdlig =" + Rdlig);

				RelatorioRd13.setVisible(true);
				RelatorioRd14.setVisible(true);
				RelatorioRd3.setVisible(true);
				RelatorioRd4.setVisible(true);
				jLabel60.setVisible(true);
				jLabel62.setVisible(true);
				jScrollPane2.setVisible(true);
				RelatorioRd15.setVisible(true);
				RelatorioRd16.setVisible(true);
				RelatorioRd23.setVisible(false);
				RelatorioRd24.setVisible(false);

				//PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO
				RelatorioMyd.setText(String.format("%.0f", Myd));
				RelatorioBeta.setText(String.format("%.3f", beta));
				RelatorioFvk.setText(String.format("%.0f", Rdmin));
				ResultadoFvk.setText(String.format("%.0f", Rdmin));
				RelatorioRk.setText(String.format("%.0f", Rdlig));
				ResultadoRk.setText(String.format("%.0f", Rdlig));
				RelatorioFaxrk.setText(String.format("%.0f", valorFaxrkFinal));
				RelatorioRd.setText(String.format("%.0f", Rvd));
				ResultadoRd.setText(String.format("%.0f", Rvd));
				teste.setText(Tipo);
			}

			//INICIA-SE O CÁLCULO DA LIGAÇÃO PARA UMA SEÇÃO DE CORTE PERPENDICULAR
			if(s1 && ape) {
				UmaSecaoCortePerpendicularController resultado = new UmaSecaoCortePerpendicularController();//Pegar os valores das outras classes
				//Considerar ou não o valor de FaxRk
				if(m) {
					valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
					valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

				}

				if(valorFaxrk > 0) {
					valorFaxrkFinal = valorFaxrk;

				}

				if(valorFaxrkPrego > 0) {
					valorFaxrkFinal = valorFaxrkPrego;

				}

				if(IncSim1) {
					falfa1 = fe0d1;
					falfa2 = fe90d2;
					Inclinado2.setVisible(true);
					Inclinado1.setVisible(false);
				}
				if(IncSim2) {
					falfa2 = fe0d2;
					falfa1 = fe90d1;
					Inclinado2.setVisible(false);
					Inclinado1.setVisible(true);
				}
				beta = falfa2 / falfa1;
				//double FaxRk = resultado.ValorFaxrk(d, fyd, d2, d1, fe0d1);
				double Myd = resultado.ValorMyd(fuk, d);
				rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
				rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
				rd3 = resultado.EmbDuasPecas(falfa1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
				rd4 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
				rd5 = resultado.DeformPinoMenorPeca(falfa1, t2, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
				rd6 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);
				System.out.println("Abriu = 1 Seção de Corte Perpendicular!");
				System.out.println("Faxrk =" + valorFaxrkFinal);
				System.out.println("Myd =" + Myd);
				System.out.println("Rd1 =" + rd1);
				System.out.println("Rd2 =" + rd2);
				System.out.println("Rd3 =" + rd3);
				System.out.println("Rd4 =" + rd4);
				System.out.println("Rd5 =" + rd5);
				System.out.println("Rd6 =" + rd6);

				//Verificar o menor valor de resistência

				Rdmin = rd1;
				Tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
				ImagemFalha = "1.1.png";

				if(rd2 < Rdmin || rd2 == Rdmin) {
					Rdmin = rd2;
					Tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
					ImagemFalha = "1.2.png";

				}
				if(rd3 < Rdmin) {
					Rdmin = rd3;
					Tipo = "Embutimento do pino metálico nos dois elementos de madeira.";
					ImagemFalha = "1.3.png";
				}
				if(rd4 < Rdmin) {
					Rdmin = rd4;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2 de madeira.";
					ImagemFalha = "1.4.png";
				}
				if(rd5 < Rdmin) {
					Rdmin = rd5;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 1 de madeira.";
					ImagemFalha = "1.5.png";
				}
				if(rd6 < Rdmin) {
					Rdmin = rd6;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos de madeira.";
					ImagemFalha = "1.6.png";
				}
				System.out.println("Rdmin =" + Rdmin);
				System.out.println("Tipo =" + Tipo);
				LabelResultadoModeloFalha.setText(Tipo);

				RelatorioFc0d1.setText(String.format("%.1f", falfa1));
				RelatorioFc0d2.setText(String.format("%.1f", falfa2));

				RelatorioRd1.setText(String.format("%.0f", rd1));
				RelatorioRd2.setText(String.format("%.0f", rd2));
				RelatorioRd3.setText(String.format("%.0f", rd3));
				RelatorioRd4.setText(String.format("%.0f", rd4));
				RelatorioRd5.setText(String.format("%.0f", rd5));
				RelatorioRd6.setText(String.format("%.0f", rd6));

				//Calcular o valor total da ligação
				Rdlig = nparafusos * 1 * Rdmin;
				Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

				System.out.println("Rdlig =" + Rdlig);

				RelatorioRd13.setVisible(true);
				RelatorioRd14.setVisible(true);
				RelatorioRd3.setVisible(true);
				RelatorioRd4.setVisible(true);
				jLabel60.setVisible(true);
				jLabel62.setVisible(true);
				jScrollPane2.setVisible(true);
				RelatorioRd15.setVisible(true);
				RelatorioRd16.setVisible(true);
				RelatorioRd23.setVisible(false);
				RelatorioRd24.setVisible(false);

				//PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO
				RelatorioMyd.setText(String.format("%.0f", Myd));
				RelatorioBeta.setText(String.format("%.3f", beta));
				RelatorioFvk.setText(String.format("%.0f", Rdmin));
				ResultadoFvk.setText(String.format("%.0f", Rdmin));
				RelatorioRk.setText(String.format("%.0f", Rdlig));
				ResultadoRk.setText(String.format("%.0f", Rdlig));
				RelatorioFaxrk.setText(String.format("%.0f", valorFaxrkFinal));
				RelatorioRd.setText(String.format("%.0f", Rvd));
				ResultadoRd.setText(String.format("%.0f", Rvd));
				teste.setText(Tipo);
			}

			//INICIA-SE O CÁLCULO DA LIGAÇÃO PARA DUAS SEÇÕES DE CORTE PARALELA
			if(s2 && apa) {
				DuasSecoesCorteParaleloController resultado = new DuasSecoesCorteParaleloController(); //Pegar os valores das outras classes
				//Considerar ou não o valor de FaxRk

				if(m) {
					valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
					valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

				}

				if(valorFaxrk > 0) {
					valorFaxrkFinal = valorFaxrk;

				}

				if(valorFaxrkPrego > 0) {
					valorFaxrkFinal = valorFaxrkPrego;

				}

				//double FaxRk = resultado.ValorFaxrk(d, fyk, d2, d1, fe0d1);
				double Myd = resultado.ValorMyd(fuk, d);
				rd1 = resultado.EmbMenorPeca(fe0d1, t1, d);
				rd2 = resultado.EmbMaiorPeca(fe0d1, t2, d, beta);
				rd3 = resultado.DeformPinoMaiorPeca(fe0d1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
				rd4 = resultado.DeformPinoNasDuas(beta, Myd, fe0d1, d, valorFaxrk, valorFaxrkPrego);
				System.out.println("Abriu = 2 Seções de corte Paralelas!");
				System.out.println("Faxrk =" + valorFaxrkFinal);
				System.out.println("Myd =" + Myd);
				System.out.println("Rd1 =" + rd1);
				System.out.println("Rd2 =" + rd2);
				System.out.println("Rd3 =" + rd3);
				System.out.println("Rd4 =" + rd4);

				//Verificar o menor valor de resistência

				Rdmin = rd1;
				Tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
				ImagemFalha = "2.1.png";

				if(rd2 < Rdmin || rd2 == Rdmin) {
					Rdmin = rd2;
					Tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
					ImagemFalha = "2.2.png";
				}
				if(rd3 < Rdmin) {
					Rdmin = rd3;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2 de madeira.";
					ImagemFalha = "2.3.png";
				}
				if(rd4 < Rdmin) {
					Rdmin = rd4;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos de madeira.";
					ImagemFalha = "2.4.png";
				}
				System.out.println("Rdmin =" + Rdmin);
				System.out.println("Tipo =" + Tipo);
				LabelResultadoModeloFalha.setText(Tipo);

				RelatorioFc0d1.setText(String.format("%.1f", fe0d1));
				RelatorioFc0d2.setText(String.format("%.1f", fe0d2));

				RelatorioRd1.setText(String.format("%.0f", rd1));
				RelatorioRd2.setText(String.format("%.0f", rd2));
				RelatorioRd5.setText(String.format("%.0f", rd3));
				RelatorioRd6.setText(String.format("%.0f", rd4));

				//Calcular o valor total da ligação
				Rdlig = nparafusos * 2 * Rdmin;
				Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

				System.out.println("Rdlig =" + Rdlig);

				jScrollPane2.setVisible(true);
				RelatorioRd13.setVisible(false);
				RelatorioRd14.setVisible(false);
				RelatorioRd3.setVisible(false);
				RelatorioRd4.setVisible(false);
				jLabel60.setVisible(false);
				jLabel62.setVisible(false);
				RelatorioRd15.setVisible(false);
				RelatorioRd16.setVisible(false);
				RelatorioRd23.setVisible(true);
				RelatorioRd24.setVisible(true);

				Inclinado1.setVisible(false);
				Inclinado2.setVisible(false);

				//PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO
				RelatorioMyd.setText(String.format("%.0f", Myd));
				RelatorioBeta.setText(String.format("%.3f", beta));
				RelatorioFvk.setText(String.format("%.0f", Rdmin));
				ResultadoFvk.setText(String.format("%.0f", Rdmin));
				RelatorioRk.setText(String.format("%.0f", Rdlig));
				ResultadoRk.setText(String.format("%.0f", Rdlig));
				RelatorioFaxrk.setText(String.format("%.0f", valorFaxrkFinal));
				RelatorioRd.setText(String.format("%.0f", Rvd));
				ResultadoRd.setText(String.format("%.0f", Rvd));
				teste.setText(Tipo);
			}

			//INICIA-SE O CÁLCULO DA LIGAÇÃO PARA DUAS SEÇÕES DE CORTE INCLINADA
			if(s2 && ai) {
				DuasSecoesCorteInclinadaController resultado = new DuasSecoesCorteInclinadaController(); //Pegar os valores das outras classes
				//Considerar ou não o valor de FaxRk

				if(m) {
					valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
					valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

				}

				if(valorFaxrk > 0) {
					valorFaxrkFinal = valorFaxrk;

				}

				if(valorFaxrkPrego > 0) {
					valorFaxrkFinal = valorFaxrkPrego;

				}

				//Valor utilizado de força
				if(IncSim1) {
					falfa2 = ((fe0d2 * fe90d2) / ((fe0d2 * (Math.pow(Math.sin(angulo), 2))) + (fe90d2 * (Math.pow(Math.cos(angulo), 2)))));
					falfa1 = fe0d1;
					Inclinado2.setVisible(true);
					Inclinado1.setVisible(false);
				}
				if(IncSim2) {
					falfa1 = ((fe0d1 * fe90d1) / ((fe0d1 * (Math.pow(Math.sin(angulo), 2))) + (fe90d1 * (Math.pow(Math.cos(angulo), 2)))));
					falfa2 = fe0d2;
					Inclinado2.setVisible(false);
					Inclinado1.setVisible(true);
				}
				beta = falfa2 / falfa1;

				//double FaxRk = resultado.ValorFaxrk(d, fyk, d2, d1, fe0d1);
				double Myd = resultado.ValorMyd(fuk, d);
				rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
				rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
				rd3 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
				rd4 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);
				System.out.println("Abriu = 2 Seções de corte Paralelas!");
				System.out.println("Faxrk =" + valorFaxrkFinal);
				System.out.println("Myd =" + Myd);
				System.out.println("Rd1 =" + rd1);
				System.out.println("Rd2 =" + rd2);
				System.out.println("Rd3 =" + rd3);
				System.out.println("Rd4 =" + rd4);
				System.out.println("Falfa1 =" + falfa1);
				System.out.println("Falfa2 =" + falfa2);
				System.out.println("beta =" + beta);

				//Verificar o menor valor de resistência

				Rdmin = rd1;
				Tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
				ImagemFalha = "2.1.png";

				if(rd2 < Rdmin || rd2 == Rdmin) {
					Rdmin = rd2;
					Tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
					ImagemFalha = "2.2.png";
				}
				if(rd3 < Rdmin) {
					Rdmin = rd3;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2 de madeira.";
					ImagemFalha = "2.3.png";
				}
				if(rd4 < Rdmin) {
					Rdmin = rd4;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos de madeira.";
					ImagemFalha = "2.4.png";
				}
				System.out.println("Rdmin =" + Rdmin);
				System.out.println("Tipo =" + Tipo);
				LabelResultadoModeloFalha.setText(Tipo);

				RelatorioFc0d1.setText(String.format("%.1f", falfa1));
				RelatorioFc0d2.setText(String.format("%.1f", falfa2));

				RelatorioRd1.setText(String.format("%.0f", rd1));
				RelatorioRd2.setText(String.format("%.0f", rd2));
				RelatorioRd5.setText(String.format("%.0f", rd3));
				RelatorioRd6.setText(String.format("%.0f", rd4));

				//Calcular o valor total da ligação
				Rdlig = nparafusos * 2 * Rdmin;
				Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

				System.out.println("Rdlig =" + Rdlig);

				jScrollPane2.setVisible(true);
				RelatorioRd13.setVisible(false);
				RelatorioRd14.setVisible(false);
				RelatorioRd3.setVisible(false);
				RelatorioRd4.setVisible(false);
				jLabel60.setVisible(false);
				jLabel62.setVisible(false);
				RelatorioRd15.setVisible(false);
				RelatorioRd16.setVisible(false);
				RelatorioRd23.setVisible(true);
				RelatorioRd24.setVisible(true);

				//PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO
				RelatorioMyd.setText(String.format("%.0f", Myd));
				RelatorioBeta.setText(String.format("%.3f", beta));
				RelatorioFvk.setText(String.format("%.0f", Rdmin));
				ResultadoFvk.setText(String.format("%.0f", Rdmin));
				RelatorioRk.setText(String.format("%.0f", Rdlig));
				ResultadoRk.setText(String.format("%.0f", Rdlig));
				RelatorioFaxrk.setText(String.format("%.0f", valorFaxrkFinal));
				RelatorioRd.setText(String.format("%.0f", Rvd));
				ResultadoRd.setText(String.format("%.0f", Rvd));
				teste.setText(Tipo);
			}

			//INICIA-SE O CÁLCULO DA LIGAÇÃO PARA DUAS SEÇÕES DE CORTE PERPENDICULAR
			if(s2 && ape) {
				DuasSecoesCortePerpendicularController resultado = new DuasSecoesCortePerpendicularController(); //Pegar os valores das outras classes
				//Considerar ou não o valor de FaxRk
				if(m) {
					valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
					valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

				}

				if(valorFaxrk > 0) {
					valorFaxrkFinal = valorFaxrk;

				}

				if(valorFaxrkPrego > 0) {
					valorFaxrkFinal = valorFaxrkPrego;

				}

				if(IncSim1) {
					falfa1 = fe0d1;
					falfa2 = fe90d2;
					Inclinado2.setVisible(true);
					Inclinado1.setVisible(false);
				}
				if(IncSim2) {
					falfa2 = fe0d2;
					falfa1 = fe90d1;
					Inclinado2.setVisible(false);
					Inclinado1.setVisible(true);
				}
				beta = falfa2 / falfa1;
				//double FaxRk = resultado.ValorFaxrk(d, fyk, d2, d1, fe0d1);
				double Myd = resultado.ValorMyd(fuk, d);
				rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
				rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
				rd3 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
				rd4 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);
				System.out.println("Abriu = 2 Seções de corte Perpendicular!");
				System.out.println("Faxrk =" + valorFaxrkFinal);
				System.out.println("Myd =" + Myd);
				System.out.println("Rd1 =" + rd1);
				System.out.println("Rd2 =" + rd2);
				System.out.println("Rd3 =" + rd3);
				System.out.println("Rd4 =" + rd4);

				//Verificar o menor valor de resistência

				Rdmin = rd1;
				Tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
				ImagemFalha = "2.1.png";

				if(rd2 < Rdmin || rd2 == Rdmin) {
					Rdmin = rd2;
					Tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
					ImagemFalha = "2.2.png";
				}
				if(rd3 < Rdmin) {
					Rdmin = rd3;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2 de madeira.";
					ImagemFalha = "2.3.png";
				}
				if(rd4 < Rdmin) {
					Rdmin = rd4;
					Tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos de madeira.";
					ImagemFalha = "2.4.png";
				}
				System.out.println("Rdmin =" + Rdmin);
				System.out.println("Tipo =" + Tipo);
				LabelResultadoModeloFalha.setText(Tipo);

				RelatorioFc0d1.setText(String.format("%.1f", falfa1));
				RelatorioFc0d2.setText(String.format("%.1f", falfa2));

				RelatorioRd1.setText(String.format("%.0f", rd1));
				RelatorioRd2.setText(String.format("%.0f", rd2));
				RelatorioRd5.setText(String.format("%.0f", rd3));
				RelatorioRd6.setText(String.format("%.0f", rd4));

				//Calcular o valor total da ligação
				Rdlig = nparafusos * 2 * Rdmin;
				Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

				System.out.println("Rdlig =" + Rdlig);

				jScrollPane2.setVisible(true);
				RelatorioRd13.setVisible(false);
				RelatorioRd14.setVisible(false);
				RelatorioRd3.setVisible(false);
				RelatorioRd4.setVisible(false);
				jLabel60.setVisible(false);
				jLabel62.setVisible(false);
				RelatorioRd15.setVisible(false);
				RelatorioRd16.setVisible(false);
				RelatorioRd23.setVisible(true);
				RelatorioRd24.setVisible(true);

				//PASSO NECESSÁRIO PARA DIMINUIR AS CASAS NA HORA DO RELATÓRIO
				RelatorioMyd.setText(String.format("%.0f", Myd));
				RelatorioBeta.setText(String.format("%.3f", beta));
				RelatorioFvk.setText(String.format("%.0f", Rdmin));
				ResultadoFvk.setText(String.format("%.0f", Rdmin));
				RelatorioRk.setText(String.format("%.0f", Rdlig));
				ResultadoRk.setText(String.format("%.0f", Rdlig));
				RelatorioFaxrk.setText(String.format("%.0f", valorFaxrkFinal));
				RelatorioRd.setText(String.format("%.0f", Rvd));
				ResultadoRd.setText(String.format("%.0f", Rvd));
				teste.setText(Tipo);
				//RelatorioRd13.
			}

			//COM ESSAS FUNÇÕES ALTERAM-SE AS IMAGENS DO RELATÓRIO
			FiguraResultadoModoFalha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensFalhas/" + ImagemFalha))); // NOI18N
			FiguraParafuso.setIcon(new ImageIcon(((ImageIcon)FiguraTipoParafuso.getIcon()).getImage().getScaledInstance(110, 25, Image.SCALE_SMOOTH)));
			FiguraParafuso.setIcon(new ImageIcon(((ImageIcon)FiguraTipoParafuso.getIcon()).getImage().getScaledInstance(110, 25, Image.SCALE_SMOOTH)));
			ModoFalha.setIcon(new ImageIcon(((ImageIcon)FiguraResultadoModoFalha.getIcon()).getImage().getScaledInstance(84, 120, Image.SCALE_SMOOTH)));
			RLogo.setIcon(new ImageIcon(((ImageIcon)LogoPrograma.getIcon()).getImage().getScaledInstance(125, 80, Image.SCALE_SMOOTH)));
		}
	}//GEN-LAST:event_ButtonCalcularActionPerformed

	private void ComboArruelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboArruelasActionPerformed
		System.out.println("cccccccccccccc=" + ComboArruelas.getSelectedIndex());
		double imagemarruela = 0.0;

		if(ComboArruelas.getSelectedIndex() > 0) {

			String norma = ComboArruelas.getSelectedItem().toString();
			// Remove o nome do parafuso e os parênteses
			norma = norma.substring(5).replace(")", "");
			System.out.println("norma=" + norma);

			String parafuso = ComboTipoParafuso.getSelectedItem().toString();

			d1 = normas.get(norma).get(parafuso)[0];
			d2 = normas.get(norma).get(parafuso)[1];
			alfa = normas.get(norma).get(parafuso)[2];
			imagemarruela = normas.get(norma).get(parafuso)[3];
			arruela = normas.get(norma).get(parafuso)[4];

			System.out.println("d1=" + d1);
			System.out.println("d2=" + d2);
			System.out.println("Alfa=" + alfa);
			System.out.println("Imagem=" + imagemarruela);

			ValorD1Arruelas.setText("" + d1);
			ValorD2Arruelas.setText("" + d2);
			RelatorioD1.setText("" + d1);
			RelatorioD2.setText("" + d2);
			String ImagemArruela = "";

			if(imagemarruela == 0.0) {
				ImagemTipoArruela.setVisible(false);

			}
			if(imagemarruela == 1.0) {
				ImagemTipoArruela.setVisible(true);
				RelatorioTipoArruela.setText("DIN 436 - " + parafuso);
				ImagemArruela = "ImagemArruela436.png";

			}
			if(imagemarruela == 2.0) {
				ImagemTipoArruela.setVisible(true);
				RelatorioTipoArruela.setText("DIN 440 R - " + parafuso);
				ImagemArruela = "ImagemArruela440R2.png";

			}
			if(imagemarruela == 3.0) {
				ImagemTipoArruela.setVisible(true);
				RelatorioTipoArruela.setText("DIN 440 V - " + parafuso);
				ImagemArruela = "ImagemArruela440V.png";

			}

			ImagemTipoArruela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Imagens/" + ImagemArruela))); // NOI18N

		}
	}//GEN-LAST:event_ComboArruelasActionPerformed

	private void ComboQuantParafusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboQuantParafusoActionPerformed
		// TODO add your handling code here:
		switch(ComboQuantParafuso.getSelectedIndex()){
			case 0:
				npar = 0;
				RelatorioNParafusos.setText("0");
			case 1:
				npar = 2;
				RelatorioNParafusos.setText("2");
				break;
			case 2:
				npar = 4;
				RelatorioNParafusos.setText("4");
				break;
			case 3:
				npar = 5;
				RelatorioNParafusos.setText("5");
				break;
			case 4:
				npar = 6;
				RelatorioNParafusos.setText("6");
				break;
			case 5:
				npar = 8;
				RelatorioNParafusos.setText("8");
				break;
			case 6:
				npar = 10;
				RelatorioNParafusos.setText("10");
				break;
			case 7:
				npar = 12;
				RelatorioNParafusos.setText("12");
				break;
			case 8:
				npar = 14;
				RelatorioNParafusos.setText("14");
				break;
		}
	}//GEN-LAST:event_ComboQuantParafusoActionPerformed

	private void TesteParafusoNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TesteParafusoNaoActionPerformed
		// TODO add your handling code here:
		Relatoriofaxrk.setText("Não");
	}//GEN-LAST:event_TesteParafusoNaoActionPerformed

	private void TesteParafusoSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TesteParafusoSimActionPerformed
		JOptionPane
		        .showMessageDialog(this,
		                           "Será considerado no cálculo o efeito não linear de compressão provocado pela arruela devido a rotação\n do pino metálico e de tração do pino metálico, conhecido como efeito de corda (Fax,rk). ");
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
		DefaultComboBoxModel model = (DefaultComboBoxModel)ComboArruelas.getModel();

		switch(ComboTipoParafuso.getSelectedIndex()){
			case 0: //Escolha o tipo de Parafuso
				ValorDiametro.setText("-");
				ValorArea.setText("-");
				ValorD1Arruelas.setText("-");
				ValorD2Arruelas.setText("-");
				model.removeAllElements();
				model.addElement("Escolha o Tipo de Arruela");
				break;
			case 1: //M10
				ValorDiametro.setText("10,0");
				ValorArea.setText("58,0");
				model.removeAllElements();
				model.addElement("Escolha o Tipo de Arruela");
				model.addElement("M10 (DIN 440 R)");
				model.addElement("M10 (DIN 440 V)");
				model.addElement("M10 (DIN 436)");
				ComboArruelas.setModel(model);
				RelatorioTipoParafuso.setText("ISO 4016 - M10");
				RelatorioDiametro.setText("10,0");
				break;
			case 2: //M12
				ValorDiametro.setText("12,0");
				ValorArea.setText("84,3");
				model.removeAllElements();
				model.addElement("Escolha o Tipo de Arruela");
				model.addElement("M12 (DIN 440 R)");
				model.addElement("M12 (DIN 440 V)");
				model.addElement("M12 (DIN 436)");
				ComboArruelas.setModel(model);
				RelatorioTipoParafuso.setText("ISO 4016 - M12");
				RelatorioDiametro.setText("12,0");
				break;
			case 3: //M16
				ValorDiametro.setText("16,0");
				ValorArea.setText("157,0");
				model.removeAllElements();
				model.addElement("Escolha o Tipo de Arruela");
				model.addElement("M16 (DIN 440 R)");
				model.addElement("M16 (DIN 440 V)");
				model.addElement("M16 (DIN 436)");
				ComboArruelas.setModel(model);
				RelatorioTipoParafuso.setText("ISO 4016 - M16");
				RelatorioDiametro.setText("16,0");
				break;
			case 4: //M20
				ValorDiametro.setText("20,0");
				ValorArea.setText("245,0");
				model.removeAllElements();
				model.addElement("Escolha o Tipo de Arruela");
				model.addElement("M20 (DIN 440 R)");
				model.addElement("M20 (DIN 440 V)");
				model.addElement("M20 (DIN 436)");
				ComboArruelas.setModel(model);
				RelatorioTipoParafuso.setText("ISO 4016 - M20");
				RelatorioDiametro.setText("20,0");
				break;
			case 5: //M22
				ValorDiametro.setText("22,0");
				ValorArea.setText("303,0");
				model.removeAllElements();
				model.addElement("Escolha o Tipo de Arruela");
				model.addElement("M22 (DIN 440 R)");
				model.addElement("M22 (DIN 440 V)");
				model.addElement("M22 (DIN 436)");
				ComboArruelas.setModel(model);
				RelatorioTipoParafuso.setText("ISO 4016 - M22");
				RelatorioDiametro.setText("22,0");
				break;
			case 6: //M24
				ValorDiametro.setText("24,0");
				ValorArea.setText("353,0");
				model.removeAllElements();
				model.addElement("Escolha o Tipo de Arruela");
				model.addElement("M24 (DIN 440 R)");
				model.addElement("M24 (DIN 436)");
				ComboArruelas.setModel(model);
				RelatorioTipoParafuso.setText("ISO 4016 - M24");
				RelatorioDiametro.setText("24,0");
				break;
			case 7: //M27
				ValorDiametro.setText("27,0");
				ValorArea.setText("459,0");
				model.removeAllElements();
				model.addElement("Escolha o Tipo de Arruela");
				model.addElement("M27 (DIN 440 R)");
				model.addElement("M27 (DIN 436)");
				ComboArruelas.setModel(model);
				RelatorioTipoParafuso.setText("ISO 4016 - M27");
				RelatorioDiametro.setText("27,0");
				break;
			case 8: //M30
				ValorDiametro.setText("30,0");
				ValorArea.setText("561,0");
				model.removeAllElements();
				model.addElement("Escolha o Tipo de Arruela");
				model.addElement("M30 (DIN 440 R)");
				model.addElement("M30 (DIN 436)");
				ComboArruelas.setModel(model);
				RelatorioTipoParafuso.setText("ISO 4016 - M30");
				RelatorioDiametro.setText("30,0");
				break;
			case 9: //M33
				ValorDiametro.setText("33,0");
				ValorArea.setText("694,0");
				model.removeAllElements();
				model.addElement("Escolha o Tipo de Arruela");
				model.addElement("M33 (DIN 440 R)");
				ComboArruelas.setModel(model);
				RelatorioTipoParafuso.setText("ISO 4016 - M33");
				RelatorioDiametro.setText("33,0");
				break;
			case 10: //M36
				ValorDiametro.setText("36,0");
				ValorArea.setText("817,0");
				model.removeAllElements();
				model.addElement("Escolha o Tipo de Arruela");
				model.addElement("M36 (DIN 440 R)");
				ComboArruelas.setModel(model);
				RelatorioTipoParafuso.setText("ISO 4016 - M36");
				RelatorioDiametro.setText("36,0");
				break;
		}
	}//GEN-LAST:event_ComboTipoParafusoActionPerformed

	private void ComboTipoParafusoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ComboTipoParafusoFocusLost
		// TODO add your handling code here:
		String msg = "";
		t1 = Integer.parseInt(Espessura1.getText());
		t2 = Integer.parseInt(Espessura2.getText());
		double dverificacao = Double.parseDouble(ValorDiametro.getText().replace(",", "."));
		double tmenor = t1;

		if(tmenor > t2) {
			tmenor = t2;

		}

		if(dverificacao >= (tmenor / 2)) {

			msg += "- O Diâmetro do parafuso não deve exceder a metade da menor espessura dos elementos da ligação, para evitar o fendilhamento da madeira.\n";

		}
		if(!msg.isEmpty()) {
			JOptionPane.showMessageDialog(this, "" + msg);
			dverificacao = 0;
			msg = "";
			ComboAco.setEnabled(false);
			ComboArruelas.setEnabled(false);
		} else {
			ComboAco.setEnabled(true);
			ComboArruelas.setEnabled(true);

		}
	}//GEN-LAST:event_ComboTipoParafusoFocusLost

	private void ComboKmod2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod2ActionPerformed
		// TODO add your handling code here:
		switch(ComboKmod2.getSelectedIndex()){
			case 0:
				Textkmod2.setText("-");
				break;
			case 1:
				Textkmod2.setText("");
			case 2:
				Textkmod2.setText("1.0");
				break;
			case 3:
				Textkmod2.setText("0.9");
				break;
			case 4:
				Textkmod2.setText("0.8");
				break;
			case 5:
				Textkmod2.setText("0.7");
				break;
			case 6:
				Textkmod2.setText("");
			case 7:
				Textkmod2.setText("1.0");
				break;
			case 8:
				Textkmod2.setText("0.95");
				break;
			case 9:
				Textkmod2.setText("0.93");
				break;
			case 10:
				Textkmod2.setText("0.9");
				break;
			case 11:
				do {
					kmod2 = -1;
					String value = (String)JOptionPane.showInputDialog(this, "Entre com um valor entre 0 e 1.", "Kmod 2", JOptionPane.INFORMATION_MESSAGE, null, null, "Digite o valor");
					Textkmod2.setText(value);
					try {
						kmod2 = Double.parseDouble(Textkmod2.getText().replace(",", "."));
					} catch (NumberFormatException e) {

					}
					jLabelStatus.setText("Entre com um valor válido, entre 0 e 1.");
				} while(kmod2 > 1 || kmod2 <= 0);
				jLabelStatus.setText("");
				break;
		}

	}//GEN-LAST:event_ComboKmod2ActionPerformed

	private void ComboKmod3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod3ActionPerformed
		// TODO add your handling code here:
		switch(ComboKmod3.getSelectedIndex()){
			case 0:
				Textkmod3.setText("-");
				Textkmod3.setEnabled(true);
				break;
			case 1:
				Textkmod3.setText("-");
				Textkmod3.setEnabled(false);
				break;
			case 2:
				Textkmod3.setText("0.9");
				Textkmod3.setEnabled(true);
				break;
			case 3:
				Textkmod3.setText("0.85");
				Textkmod3.setEnabled(true);
				break;
			case 4:
				Textkmod3.setText("0.80");
				Textkmod3.setEnabled(true);
				break;
			case 5:
				Textkmod3.setText("0.75");
				Textkmod3.setEnabled(true);
				break;
			case 6:
				Textkmod3.setText("1.0");
				Textkmod3.setEnabled(true);
				break;
			case 7:
				Textkmod3.setText("0.95");
				Textkmod3.setEnabled(true);
				break;
			case 8:
				Textkmod3.setText("0.9");
				Textkmod3.setEnabled(true);
				break;
			case 9:
				Textkmod3.setText("0.85");
				Textkmod3.setEnabled(true);
				break;
			case 10:
				Textkmod3.setText("0.7");
				Textkmod3.setEnabled(true);
				break;
			case 11:
				Textkmod3.setText("-");
				Textkmod3.setEnabled(false);
				break;
			case 12:
				Textkmod3.setText("0.7");
				Textkmod3.setEnabled(true);
				break;
			case 13:
				Textkmod3.setText("0.6");
				Textkmod3.setEnabled(true);
				break;
			case 14:
				Textkmod3.setText("0.5");
				Textkmod3.setEnabled(true);
				break;
			case 15:
				Textkmod3.setText("0.4");
				Textkmod3.setEnabled(true);
				break;
			case 16:
				Textkmod3.setText("0.6");
				Textkmod3.setEnabled(true);
				break;
			case 17:
				Textkmod3.setText("0.5");
				Textkmod3.setEnabled(true);
				break;
			case 18:
				Textkmod3.setText("0.4");
				Textkmod3.setEnabled(true);
				break;
			case 19:
				Textkmod3.setText("0.3");
				Textkmod3.setEnabled(true);
				break;
			case 20:
				Textkmod3.setText("0.9");
				Textkmod3.setEnabled(true);
				break;
			case 21:
				Textkmod3.setText("0.8");
				Textkmod3.setEnabled(true);
				break;
			case 22:
				Textkmod3.setText("0.7");
				Textkmod3.setEnabled(true);
				break;
			case 23:
				Textkmod3.setText("0.6");
				Textkmod3.setEnabled(true);
				break;
			case 24:
				Textkmod3.setText("0.8");
				Textkmod3.setEnabled(true);
				break;
			case 25:
				Textkmod3.setText("0.7");
				Textkmod3.setEnabled(true);
				break;
			case 26:
				Textkmod3.setText("0.6");
				Textkmod3.setEnabled(true);
				break;
			case 27:
				Textkmod3.setText("0.5");
				Textkmod3.setEnabled(true);
				break;
			case 28:
				do {
					kmod3 = -1;
					String value = (String)JOptionPane.showInputDialog(this, "Entre com um valor entre 0 e 1.", "Kmod 3", JOptionPane.INFORMATION_MESSAGE, null, null, "Digite o valor");
					Textkmod3.setText(value);
					try {
						kmod3 = Double.parseDouble(Textkmod3.getText().replace(",", "."));
					} catch (NumberFormatException e) {

					}
					jLabelStatus.setText("Entre com um valor válido, entre 0 e 1.");
				} while(kmod3 > 1 || kmod3 <= 0);
				jLabelStatus.setText("");
				break;
		}

	}//GEN-LAST:event_ComboKmod3ActionPerformed

	private void ComboKmod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod1ActionPerformed
		// TODO add your handling code here:
		switch(ComboKmod1.getSelectedIndex()){
			case 0:
				Textkmod1.setText("-");
				break;
			case 1:
				Textkmod1.setText("");
				break;
			case 2:
				Textkmod1.setText("0.6");
				break;
			case 3:
				Textkmod1.setText("0.7");
				break;
			case 4:
				Textkmod1.setText("0.8");
				break;
			case 5:
				Textkmod1.setText("0.9");
				break;
			case 6:
				Textkmod1.setText("1.1");
				break;
			case 7:
				Textkmod1.setText("");
				break;
			case 8:
				Textkmod1.setText("0.3");
				break;
			case 9:
				Textkmod1.setText("0.45");
				break;
			case 10:
				Textkmod1.setText("0.65");
				break;
			case 11:
				Textkmod1.setText("0.9");
				break;
			case 12:
				Textkmod1.setText("1.1");
				break;
			case 13:
				do {
					kmod1 = -1;
					String value = (String)JOptionPane.showInputDialog(this, "Entre com um valor entre 0 e 1.", "Kmod 1", JOptionPane.INFORMATION_MESSAGE, null, null, "Digite o valor");
					Textkmod1.setText(value);
					try {
						kmod1 = Double.parseDouble(Textkmod1.getText().replace(",", "."));
					} catch (NumberFormatException e) {

					}
					jLabelStatus.setText("Entre com um valor válido, entre 0 e 1.");
				} while(kmod1 > 1 || kmod1 <= 0);
				jLabelStatus.setText("");
				break;
		}

	}//GEN-LAST:event_ComboKmod1ActionPerformed

	private void MadeiraFiguraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MadeiraFiguraActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_MadeiraFiguraActionPerformed

	private void InclinacaoSim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InclinacaoSim2ActionPerformed
		// TODO add your handling code here:
		IncSim1 = false;
		IncSim2 = true;

		MadeiraFigura.setVisible(true);
		String TrocaFigura = "";

		if(s1 && ai) {
			CalculoForca01.setVisible(false);
			CalculoForca02.setVisible(true);
			CalculoForca901.setVisible(false);
			CalculoForca902.setVisible(false);
			CalculoForcaAlfa1.setVisible(true);
			CalculoForcaAlfa2.setVisible(false);

			TrocaFigura = "1InclinadoElem1.png"; // NOI18N

		}
		if(s1 && ape) {
			CalculoForca01.setVisible(false);
			CalculoForca02.setVisible(true);
			CalculoForca901.setVisible(true);
			CalculoForca902.setVisible(false);
			CalculoForcaAlfa1.setVisible(false);
			CalculoForcaAlfa2.setVisible(false);

			TrocaFigura = "1PerpendicularElem1.png"; // NOI18N

		}
		if(s2 && ai) {
			CalculoForca01.setVisible(false);
			CalculoForca02.setVisible(true);
			CalculoForca901.setVisible(false);
			CalculoForca902.setVisible(false);
			CalculoForcaAlfa1.setVisible(true);
			CalculoForcaAlfa2.setVisible(false);

			TrocaFigura = "2InclinadoElem1.png"; // NOI18N

		}
		if(s2 && ape) {
			CalculoForca01.setVisible(false);
			CalculoForca02.setVisible(true);
			CalculoForca901.setVisible(true);
			CalculoForca902.setVisible(false);
			CalculoForcaAlfa1.setVisible(false);
			CalculoForcaAlfa2.setVisible(false);

			TrocaFigura = "2PerpendicularElem1.png"; // NOI18N

		}
		MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/" + TrocaFigura))); // NOI18N
	}//GEN-LAST:event_InclinacaoSim2ActionPerformed

	private void Espessura2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Espessura2KeyTyped
		// TODO add your handling code here:
		if((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') || evt.getKeyChar() == ',') {
			return;
		}
		evt.consume();
	}//GEN-LAST:event_Espessura2KeyTyped

	private void Espessura2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Espessura2ActionPerformed
		// TODO add your handling code here:

	}//GEN-LAST:event_Espessura2ActionPerformed

	private void LimparExpessura2(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LimparExpessura2
		// TODO add your handling code here:
		if(Espessura2.getText().length() > 4) {
			Espessura2.setText("");
		}
	}//GEN-LAST:event_LimparExpessura2

	private void ComboElem2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ComboElem2PropertyChange
		// TODO add your handling code here:
	}//GEN-LAST:event_ComboElem2PropertyChange

	private void ComboElem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboElem2ActionPerformed
		// TODO add your handling code here:
		switch(ComboElem2.getSelectedIndex()){
			case 0:
				ValorFc2.setText("-");
				ValorDensidade2.setText("-");
				ValorFvok2.setText("-");
				ValorEc0m2.setText("-");

				break;
			case 1: //C-20
				ValorFc2.setText("20");
				ValorDensidade2.setText("500");
				ValorFvok2.setText("4");
				ValorEc0m2.setText("3500");
				RelatorioClasseElem2.setText("C-20");
				Relatoriofcok2.setText("20");
				Relatoriofv0k2.setText("4");
				RelatorioEc0m2.setText("3500");
				RelatorioDensidade2.setText("500");
				break;
			case 2: //C-25
				ValorFc2.setText("25");
				ValorFvok2.setText("5");
				ValorEc0m2.setText("8500");
				ValorDensidade2.setText("550");
				RelatorioClasseElem2.setText("C-25");
				Relatoriofcok2.setText("25");
				Relatoriofv0k2.setText("5");
				RelatorioEc0m2.setText("8500");
				RelatorioDensidade2.setText("550");
				break;
			case 3: //C-30
				ValorFc2.setText("30");
				ValorFvok2.setText("6");
				ValorEc0m2.setText("14500");
				ValorDensidade2.setText("600");
				RelatorioClasseElem2.setText("C-30");
				Relatoriofcok2.setText("30");
				Relatoriofv0k2.setText("6");
				RelatorioEc0m2.setText("14500");
				RelatorioDensidade2.setText("600");
				break;
			case 4: //D-20
				ValorFc2.setText("20");
				ValorFvok2.setText("4");
				ValorEc0m2.setText("9500");
				ValorDensidade2.setText("650");
				RelatorioClasseElem2.setText("D-20");
				Relatoriofcok2.setText("20");
				Relatoriofv0k2.setText("4");
				RelatorioEc0m2.setText("9500");
				RelatorioDensidade2.setText("650");
				break;
			case 5: //D-30
				ValorFc2.setText("30");
				ValorFvok2.setText("5");
				ValorEc0m2.setText("14500");
				ValorDensidade2.setText("800");
				RelatorioClasseElem2.setText("D-30");
				Relatoriofcok2.setText("30");
				Relatoriofv0k2.setText("5");
				RelatorioEc0m2.setText("14500");
				RelatorioDensidade2.setText("800");
				break;
			case 6: //D-40
				ValorFc2.setText("40");
				ValorFvok2.setText("6");
				ValorEc0m2.setText("19500");
				ValorDensidade2.setText("950");
				RelatorioClasseElem2.setText("D-40");
				Relatoriofcok2.setText("40");
				Relatoriofv0k2.setText("6");
				RelatorioEc0m2.setText("19500");
				RelatorioDensidade2.setText("950");
				break;
			case 7: //D-50
				ValorFc2.setText("50");
				ValorFvok2.setText("7");
				ValorEc0m2.setText("22000");
				ValorDensidade2.setText("970");
				RelatorioClasseElem2.setText("D-50");
				Relatoriofcok2.setText("50");
				Relatoriofv0k2.setText("7");
				RelatorioEc0m2.setText("22000");
				RelatorioDensidade2.setText("970");
				break;
			case 8: //C-30
				ValorFc2.setText("60");
				ValorFvok2.setText("8");
				ValorEc0m2.setText("24500");
				ValorDensidade2.setText("1000");
				RelatorioClasseElem2.setText("D-60");
				Relatoriofcok2.setText("60");
				Relatoriofv0k2.setText("8");
				RelatorioEc0m2.setText("24500");
				RelatorioDensidade2.setText("1000");
				break;
		}

	}//GEN-LAST:event_ComboElem2ActionPerformed

	private void Espessura1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Espessura1KeyTyped
		// TODO add your handling code here:
	}//GEN-LAST:event_Espessura1KeyTyped

	private void Espessura1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Espessura1PropertyChange
		// TODO add your handling code here:
	}//GEN-LAST:event_Espessura1PropertyChange

	private void Espessura1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Espessura1ActionPerformed
		// TODO add your handling code here:

	}//GEN-LAST:event_Espessura1ActionPerformed

	private void Espessura1(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Espessura1
		// TODO add your handling code here:
		if(Espessura1.getText().length() > 4) {
			Espessura1.setText("");
		}
	}//GEN-LAST:event_Espessura1

	private void InclinacaoSim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InclinacaoSim1ActionPerformed
		// TODO add your handling code here:
		IncSim1 = true;
		IncSim2 = false;

		MadeiraFigura.setVisible(true);
		String TrocaFigura = "";

		if(s1 && ai) {
			CalculoForca01.setVisible(true);
			CalculoForca02.setVisible(false);
			CalculoForca901.setVisible(false);
			CalculoForca902.setVisible(false);
			CalculoForcaAlfa1.setVisible(false);
			CalculoForcaAlfa2.setVisible(true);

			TrocaFigura = "1InclinadoElem2.png"; // NOI18N

		}
		if(s1 && ape) {
			CalculoForca01.setVisible(true);
			CalculoForca02.setVisible(false);
			CalculoForca901.setVisible(false);
			CalculoForca902.setVisible(true);
			CalculoForcaAlfa1.setVisible(false);
			CalculoForcaAlfa2.setVisible(false);

			TrocaFigura = "1PerpendicularElem2.png"; // NOI18N

		}
		if(s2 && ai) {
			CalculoForca01.setVisible(true);
			CalculoForca02.setVisible(false);
			CalculoForca901.setVisible(false);
			CalculoForca902.setVisible(false);
			CalculoForcaAlfa1.setVisible(false);
			CalculoForcaAlfa2.setVisible(true);

			TrocaFigura = "2InclinadoElem2.png"; // NOI18N

		}
		if(s2 && ape) {
			CalculoForca01.setVisible(true);
			CalculoForca02.setVisible(false);
			CalculoForca901.setVisible(false);
			CalculoForca902.setVisible(true);
			CalculoForcaAlfa1.setVisible(false);
			CalculoForcaAlfa2.setVisible(false);

			TrocaFigura = "2PerpendicularElem2.png"; // NOI18N

		}
		MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/" + TrocaFigura))); // NOI18N
	}//GEN-LAST:event_InclinacaoSim1ActionPerformed

	private void ValorAnguloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ValorAnguloKeyTyped
		// TODO add your handling code here:
		if((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') || evt.getKeyChar() == ',') {
			return;
		}
		evt.consume();
	}//GEN-LAST:event_ValorAnguloKeyTyped

	private void ValorAnguloPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ValorAnguloPropertyChange
		// TODO add your handling code here:
	}//GEN-LAST:event_ValorAnguloPropertyChange

	private void ValorAnguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValorAnguloActionPerformed
		// TODO add your handling code here:

	}//GEN-LAST:event_ValorAnguloActionPerformed

	private void ValorAngulo(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValorAngulo
		// TODO add your handling code here:
		InclinacaoSim1.setEnabled(false);
		InclinacaoSim2.setEnabled(false);

		if(ValorAngulo.getText().length() > 4) {
			ValorAngulo.setText("0");

		}
	}//GEN-LAST:event_ValorAngulo

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
			case 8: //D-60
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
		}

		// TODO add your handling code here:
	}//GEN-LAST:event_ComboElem1ActionPerformed

	private void btn2SecaoCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2SecaoCorteActionPerformed
		// TODO add your handling code here:
		s1 = false;
		s2 = true;
		DuasSecoes = true;
		UmaSecao = false;
		MadeiraFigura.setVisible(true);
		InclinacaoSim1.setEnabled(true);
		InclinacaoSim2.setEnabled(true);
		jLabelStatus.setText("Clique em avançar para continuar.");

		Next.setEnabled(true);
		RelatorioSecaoCorte.setText("Ligação com corte duplo");
		MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/2Paralelo.png"))); // NOI18N
		FiguraSecoes.setIcon(new ImageIcon(((ImageIcon)btn2SecaoCorte.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));

		System.out.println("s1 =" + s1);
		System.out.println("s2 =" + s2);
		System.out.println("UmaSecao =" + UmaSecao);
		System.out.println("DuasSecoes =" + DuasSecoes);

		// JOptionPane.showMessageDialog(this, "Esta habilitado a= ["+a.booleanValue()+"] b=["+b.booleanValue()+"] c=["+c.booleanValue()+"]");
	}//GEN-LAST:event_btn2SecaoCorteActionPerformed

	private void btn1SecaoCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1SecaoCorteActionPerformed
		// TODO add your handling code here:
		s1 = true;
		s2 = false;
		UmaSecao = true;
		DuasSecoes = false;
		MadeiraFigura.setVisible(true);
		InclinacaoSim1.setEnabled(true);
		InclinacaoSim2.setEnabled(true);
		jLabelStatus.setText("Clique em avançar para continuar.");

		Next.setEnabled(true);
		RelatorioSecaoCorte.setText("Ligação com corte simples");
		MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/1Paralelo.png"))); // NOI18N
		FiguraSecoes.setIcon(new ImageIcon(((ImageIcon)btn1SecaoCorte.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));

		System.out.println("s1 =" + s1);
		System.out.println("s2 =" + s2);
		System.out.println("UmaSecao =" + UmaSecao);
		System.out.println("DuasSecoes =" + DuasSecoes);

		//double x = valor_faxrk(1, 250, Double.parseDouble("6.5"), 12, 250);
		//JOptionPane.showMessageDialog(this, "Esta habilitado a= ["+a.booleanValue()+"] b=["+b.booleanValue()+"] c=["+c.booleanValue()+"]");
		//JOptionPane.showMessageDialog(this, "valor da função= ["+Double.toString(x)+"");
	}//GEN-LAST:event_btn1SecaoCorteActionPerformed

	private void ValorAnguloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValorAnguloFocusLost
		// TODO add your handling code here:
		double testeAngulo = Integer.parseInt(ValorAngulo.getText());

		double teste = Double.parseDouble(ValorAngulo.getText().replace(",", "."));

		if(teste > 90 || teste < 0) {
			ValorAngulo.setText("");
			testeAngulo = 0.0;
			jLabelStatus.setText("Digite um ângulo entre 0° e 90°.");
		}

		MadeiraFigura.setVisible(true);
		String FiguraMadeira = "";

		if(s1 == true) {
			if(testeAngulo == 0.0) {
				FiguraMadeira = "1Paralelo.png";
				apa = true;
				ai = false;
				ape = false;
				InclinacaoSim1.setEnabled(false);
				InclinacaoSim2.setEnabled(false);
				Paralelo = true;
				Inclinado = false;
				Perpendicular = false;
				RelatorioAngulacao.setText("Direção Paralela");
				CalculoForca01.setVisible(true);
				CalculoForca901.setVisible(false);
				CalculoForcaAlfa1.setVisible(false);

			}

			else {
				if(testeAngulo == 90.0) {
					FiguraMadeira = "1PerpendicularElem1.png";
					apa = false;
					ai = false;
					ape = true;
					InclinacaoSim1.setEnabled(true);
					InclinacaoSim2.setEnabled(true);
					Paralelo = false;
					Inclinado = false;
					Perpendicular = true;
					RelatorioAngulacao.setText("Direção Perpendicular");
					CalculoForca01.setVisible(false);
					CalculoForca901.setVisible(true);
					CalculoForcaAlfa1.setVisible(false);

				}

				else {
					FiguraMadeira = "1InclinadoElem1.png";
					apa = false;
					ai = true;
					ape = false;
					InclinacaoSim1.setEnabled(true);
					InclinacaoSim2.setEnabled(true);
					Paralelo = false;
					Inclinado = true;
					Perpendicular = false;
					RelatorioAngulacao.setText("Direção Inclinada");
					CalculoForca01.setVisible(false);
					CalculoForca901.setVisible(false);
					CalculoForcaAlfa1.setVisible(true);
				}

			}
		}

		if(s2 == true) {
			if(testeAngulo == 0.0) {
				FiguraMadeira = "2Paralelo.png";
				apa = true;
				ai = false;
				ape = false;
				InclinacaoSim1.setEnabled(false);
				InclinacaoSim2.setEnabled(false);
				Paralelo = true;
				Inclinado = false;
				Perpendicular = false;
				RelatorioAngulacao.setText("Direção Paralela");
				CalculoForca01.setVisible(true);
				CalculoForca901.setVisible(false);
				CalculoForcaAlfa1.setVisible(false);
			}

			else {
				if(testeAngulo == 90.0) {
					FiguraMadeira = "2PerpendicularElem1.png";
					apa = false;
					ai = false;
					ape = true;
					InclinacaoSim1.setEnabled(true);
					InclinacaoSim2.setEnabled(true);
					Paralelo = false;
					Inclinado = false;
					Perpendicular = true;
					RelatorioAngulacao.setText("Direção Perpendicular");
					CalculoForca01.setVisible(false);
					CalculoForca901.setVisible(true);
					CalculoForcaAlfa1.setVisible(false);
				} else {
					FiguraMadeira = "2InclinadoElem1.png";
					apa = false;
					ai = true;
					ape = false;
					InclinacaoSim1.setEnabled(true);
					InclinacaoSim2.setEnabled(true);
					Paralelo = false;
					Inclinado = true;
					Perpendicular = false;
					RelatorioAngulacao.setText("Direção Inclinada");
					CalculoForca01.setVisible(false);
					CalculoForca901.setVisible(false);
					CalculoForcaAlfa1.setVisible(true);
				}

			}
		}

		MadeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/" + FiguraMadeira))); // NOI18N
		MadeiraFigura.doClick();

	}//GEN-LAST:event_ValorAnguloFocusLost

	private void jTabbedPane1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusLost
		// TODO add your handling code here:
	}//GEN-LAST:event_jTabbedPane1FocusLost

	private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
		// TODO add your handling code here:
		jTabbedPane1.setEnabledAt(2, true);
		jTabbedPane1.setSelectedComponent(ElementosMadeira);
		jLabelStatus.setText("Preencha todos os campos sem exceção.");
	}//GEN-LAST:event_NextActionPerformed

	private void Next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Next2ActionPerformed
		// TODO add your handling code here:
		Espessura1.getInputVerifier().shouldYieldFocus(Espessura1);
		Espessura2.getInputVerifier().shouldYieldFocus(Espessura2);
		ValorAngulo.getInputVerifier().shouldYieldFocus(ValorAngulo);
		ComboElem1.getInputVerifier().shouldYieldFocus(ComboElem1);
		ComboElem2.getInputVerifier().shouldYieldFocus(ComboElem2);
		ComboKmod1.getInputVerifier().shouldYieldFocus(ComboKmod1);
		ComboKmod2.getInputVerifier().shouldYieldFocus(ComboKmod2);
		ComboKmod3.getInputVerifier().shouldYieldFocus(ComboKmod3);

		if(Next2.hasFocus()) {
			jTabbedPane1.setEnabledAt(3, true);
			jTabbedPane1.setSelectedComponent(ElementosMetalicos);
			jLabelStatus.setText("Preencha todos os campos sem exceção.");
		}
	}//GEN-LAST:event_Next2ActionPerformed

	private void Next3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Next3ActionPerformed
		// TODO add your handling code here:
		jTabbedPane1.setEnabledAt(5, true);
		jTabbedPane1.setSelectedComponent(Relatorio);
		jLabelStatus.setText("Verifique o relatório do dimensionamento. Fique atento para as opções fornecidas nos botões.");
	}//GEN-LAST:event_Next3ActionPerformed

	private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
		// TODO add your handling code here:

	}//GEN-LAST:event_jButton2MouseReleased

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
		// TODO add your handling code here:
		jTabbedPane1.setEnabledAt(1, true);
		jTabbedPane1.setSelectedComponent(SecoesCorte);
		jLabelStatus.setText("Escolha a quantidade de seções de corte no parafuso.");
	}//GEN-LAST:event_jButton4ActionPerformed

	private void VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarActionPerformed
		// TODO add your handling code here:
		this.dispose();
		TelaInicial telainicial = new TelaInicial();
		telainicial.setVisible(true);
	}//GEN-LAST:event_VoltarActionPerformed
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
			for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TelaParafuso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaParafuso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaParafuso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaParafuso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run() {
				new TelaParafuso().setVisible(true);
			}
		});

		//        Date sysDate = new Date();
		//SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
		//dateFormat.format(sysDate);

	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel AreaParafuso;
	private javax.swing.JLabel Arruelas;
	private javax.swing.JButton ButtonCalcular;
	private javax.swing.JLabel CalculoForca01;
	private javax.swing.JLabel CalculoForca02;
	private javax.swing.JLabel CalculoForca901;
	private javax.swing.JLabel CalculoForca902;
	private javax.swing.JLabel CalculoForcaAlfa1;
	private javax.swing.JLabel CalculoForcaAlfa2;
	private javax.swing.JLabel ClasseAco;
	private javax.swing.JComboBox ComboAco;
	private javax.swing.JComboBox ComboArruelas;
	private javax.swing.JComboBox ComboElem1;
	private javax.swing.JComboBox ComboElem2;
	private javax.swing.JComboBox ComboKmod1;
	private javax.swing.JComboBox ComboKmod2;
	private javax.swing.JComboBox ComboKmod3;
	private javax.swing.JComboBox ComboQuantParafuso;
	private javax.swing.JComboBox ComboTipoParafuso;
	private javax.swing.JTextArea Consideracao1;
	private javax.swing.JLabel D1Arruelas;
	private javax.swing.JLabel D2Arruelas;
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
	private javax.swing.JLabel FiguraArruela;
	private javax.swing.JLabel FiguraParafuso;
	private javax.swing.JButton FiguraResultadoModoFalha;
	private javax.swing.JLabel FiguraSecoes;
	private javax.swing.JButton FiguraTipoParafuso;
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
	private javax.swing.JLabel PossuiInclinacao1;
	private javax.swing.JLabel PossuiInclinacao11;
	private javax.swing.JLabel PossuiInclinacao3;
	private javax.swing.JLabel RLogo;
	private javax.swing.JPanel Relatorio;
	private javax.swing.JLabel RelatorioAngulacao;
	private javax.swing.JLabel RelatorioAngulo;
	private javax.swing.JLabel RelatorioBeta;
	private javax.swing.JLabel RelatorioClasseAco;
	private javax.swing.JLabel RelatorioClasseElem1;
	private javax.swing.JLabel RelatorioClasseElem2;
	private javax.swing.JPanel RelatorioCoeficientes;
	private javax.swing.JLabel RelatorioD1;
	private javax.swing.JLabel RelatorioD2;
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
	private javax.swing.JLabel RelatorioTipoArruela;
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
	private javax.swing.JLabel TamanhoParafuso;
	private javax.swing.JLabel TesteParafuso;
	private javax.swing.JRadioButton TesteParafusoNao;
	private javax.swing.JRadioButton TesteParafusoSim;
	private javax.swing.JLabel Textkmod1;
	private javax.swing.JLabel Textkmod2;
	private javax.swing.JLabel Textkmod3;
	private javax.swing.JLabel TipoArruela;
	private javax.swing.JLabel UnD1Arruelas;
	private javax.swing.JLabel UnD2Arruelas;
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
	private javax.swing.JLabel ValorArea;
	private javax.swing.JLabel ValorD1Arruelas;
	private javax.swing.JLabel ValorD2Arruelas;
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
	private javax.swing.JToggleButton btn2SecaoCorte;
	private javax.swing.JLabel fuk;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel100;
	private javax.swing.JLabel jLabel101;
	private javax.swing.JLabel jLabel102;
	private javax.swing.JLabel jLabel103;
	private javax.swing.JLabel jLabel104;
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
	private javax.swing.JLabel jLabel54;
	private javax.swing.JLabel jLabel55;
	private javax.swing.JLabel jLabel56;
	private javax.swing.JLabel jLabel57;
	private javax.swing.JLabel jLabel58;
	private javax.swing.JLabel jLabel59;
	private javax.swing.JLabel jLabel60;
	private javax.swing.JLabel jLabel61;
	private javax.swing.JLabel jLabel62;
	private javax.swing.JLabel jLabel63;
	private javax.swing.JLabel jLabel64;
	private javax.swing.JLabel jLabel65;
	private javax.swing.JLabel jLabel66;
	private javax.swing.JLabel jLabel67;
	private javax.swing.JLabel jLabel68;
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
	private javax.swing.JLabel jLabelStatus;
	private javax.swing.JLabel jLabelUnEspessura;
	private javax.swing.JLabel jLabelUnEspessura1;
	private javax.swing.JLabel jLabel_classe_madeira_1;
	private javax.swing.JLabel jLabel_classe_madeira_2;
	private javax.swing.JLabel jLabel_elemento1;
	private javax.swing.JLabel jLabel_elemento2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel12;
	private javax.swing.JPanel jPanel13;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator10;
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
	private javax.swing.JTextArea teste;
	// End of variables declaration//GEN-END:variables

	public int calc() {
		double pi = Math.PI;
		Math.random();
		Math.sqrt(pi);

		return 0;
	}

}
