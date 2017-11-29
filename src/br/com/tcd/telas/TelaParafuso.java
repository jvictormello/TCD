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

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.com.tcd.enumeration.ClasseAcoParafuso;
import br.com.tcd.enumeration.ClasseMadeira;
import br.com.tcd.enumeration.EspecieMadeira;
import br.com.tcd.enumeration.Kmod1;
import br.com.tcd.enumeration.Kmod2;
import br.com.tcd.enumeration.Kmod3;
import br.com.tcd.enumeration.ModeloLigacao;
import br.com.tcd.enumeration.QuantidadeParafuso;
import br.com.tcd.enumeration.TipoArruela;
import br.com.tcd.enumeration.TipoMadeira;
import br.com.tcd.enumeration.TipoParafuso;
import br.com.tcd.interfaces.ModeloLigacaoProvider;
import br.com.tcd.modelo.Angulo;
import br.com.tcd.modelo.CalculoModeloLigacao;
import br.com.tcd.modelo.Conectores;
import br.com.tcd.util.ClsDataHora;
import br.com.tcd.verifier.Verificador;
import br.com.tcd.verifier.VerificadorComboAcoParafuso;
import br.com.tcd.verifier.VerificadorComboArruelas;
import br.com.tcd.verifier.VerificadorComboClasseElem1;
import br.com.tcd.verifier.VerificadorComboClasseElem2;
import br.com.tcd.verifier.VerificadorComboQuantParafuso;
import br.com.tcd.verifier.VerificadorComboTipoParafuso;
import br.com.tcd.verifier.VerificadorEspessura1;
import br.com.tcd.verifier.VerificadorEspessura2;
import br.com.tcd.verifier.VerificadorKmod1;
import br.com.tcd.verifier.VerificadorKmod2;
import br.com.tcd.verifier.VerificadorKmod3;
import br.com.tcd.verifier.VerificadorValorAngulo;

/**
 *
 * @author MarcosVinicius
 */
public class TelaParafuso extends javax.swing.JFrame implements ModeloLigacaoProvider {

	private static final long serialVersionUID = 7130615706326642820L;

	/**
	 * Creates new form TelaParafuso
	 */
	private ModeloLigacao modeloLigacao;
	private CalculoModeloLigacao calculoModeloLigacao;
	private Boolean m;
	private Boolean incSim1;
	private Boolean incSim2;
	private ClsDataHora objDataHora = new ClsDataHora();

	public TelaParafuso() {
		try {
			initComponents();
			iniciarVariaveis();
		} catch (Exception e) {
			e.printStackTrace();

			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}

	private void iniciarVariaveis() {
		m = false;
		incSim1 = false;
		incSim2 = true;

		imagemTipoArruela.setVisible(false);
		calculoForca01.setVisible(true);
		calculoForca02.setVisible(true);
		calculoForca901.setVisible(false);
		calculoForca902.setVisible(false);
		calculoForcaAlfa1.setVisible(false);
		calculoForcaAlfa2.setVisible(false);
		inclinado1.setVisible(false);
		inclinado2.setVisible(false);

		madeiraFigura.setVisible(true);

		relatorioRd13.setVisible(true);
		relatorioRd14.setVisible(true);
		relatorioRd3.setVisible(true);
		relatorioRd4.setVisible(true);
		jLabel60.setVisible(true);
		jLabel62.setVisible(true);

		jTabbedPane1.setEnabledAt(1, false);
		jTabbedPane1.setEnabledAt(2, false);
		jTabbedPane1.setEnabledAt(3, false);
		jTabbedPane1.setEnabledAt(4, false);
		jTabbedPane1.setEnabledAt(5, false);
		next.setEnabled(false);
		next2.setEnabled(false);
		next3.setEnabled(false);
		inclinacaoSim1.setEnabled(false);
		inclinacaoSim2.setEnabled(false);

		painelEspecieMadeira = new javax.swing.JPanel();
		painelTipoMadeira = new javax.swing.JPanel();
		serradaButton.setEnabled(false);
		recompostaButton.setEnabled(false);
		coniferasButton.setEnabled(false);
		folhosasButton.setEnabled(false);

		comboKmod1.setEnabled(false);
		comboKmod2.setEnabled(false);
		comboKmod3.setEnabled(false);

		espessura1.setInputVerifier(new VerificadorEspessura1(this.jProgressBarStatus, this));
		espessura2.setInputVerifier(new VerificadorEspessura2(this.jProgressBarStatus, this));
		valorAngulo.setInputVerifier(new VerificadorValorAngulo(this.jProgressBarStatus, this));
		comboElem1.setInputVerifier(new VerificadorComboClasseElem1(this.jProgressBarStatus, this));
		comboElem2.setInputVerifier(new VerificadorComboClasseElem2(this.jProgressBarStatus, this));
		comboKmod1.setInputVerifier(new VerificadorKmod1(this.jProgressBarStatus, this));
		comboKmod2.setInputVerifier(new VerificadorKmod2(this.jProgressBarStatus, this));
		comboKmod3.setInputVerifier(new VerificadorKmod3(this.jProgressBarStatus, this));
		comboQuantParafuso.setInputVerifier(new VerificadorComboQuantParafuso(this.jProgressBarStatus, this));
		comboTipoParafuso.setInputVerifier(new VerificadorComboTipoParafuso(this.jProgressBarStatus, this));
		comboAco.setInputVerifier(new VerificadorComboAcoParafuso(this.jProgressBarStatus, this));
		comboArruelas.setInputVerifier(new VerificadorComboArruelas(this.jProgressBarStatus, this));

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		groupSecoesCorte = new javax.swing.ButtonGroup();
		groupAngulacao = new javax.swing.ButtonGroup();
		groupTesteParafuso = new javax.swing.ButtonGroup();
		groupInclinacaoSim = new javax.swing.ButtonGroup();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		inicio = new javax.swing.JPanel();
		logoPrograma = new javax.swing.JLabel();
		jLabel97 = new javax.swing.JLabel();
		voltar = new javax.swing.JButton();
		jLabel40 = new javax.swing.JLabel();
		jButton4 = new javax.swing.JButton();
		secoesCorte = new javax.swing.JPanel();
		btn1SecaoCorte = new javax.swing.JToggleButton();
		btn2SecaoCorte = new javax.swing.JToggleButton();
		next = new javax.swing.JButton();
		elementosMadeira = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		comboElem1 = new javax.swing.JComboBox<ClasseMadeira>();
		jLabel_classe_madeira_1 = new javax.swing.JLabel();
		jLabel_elemento1 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		valorAngulo = new javax.swing.JTextField();
		jLabelUnEspessura = new javax.swing.JLabel();
		fcok1 = new javax.swing.JLabel();
		unFcok1 = new javax.swing.JLabel();
		valorFc01 = new javax.swing.JLabel();
		densidade1 = new javax.swing.JLabel();
		valorDensidade1 = new javax.swing.JLabel();
		undensidade1 = new javax.swing.JLabel();
		valorFvok1 = new javax.swing.JLabel();
		unFv0k1 = new javax.swing.JLabel();
		fv0k1 = new javax.swing.JLabel();
		ec0m1 = new javax.swing.JLabel();
		unEc0m1 = new javax.swing.JLabel();
		valorEc0m1 = new javax.swing.JLabel();
		jSeparator1 = new javax.swing.JSeparator();
		possuiInclinacao1 = new javax.swing.JLabel();
		inclinacaoSim1 = new javax.swing.JRadioButton();
		possuiInclinacao11 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		espessura1 = new javax.swing.JTextField();
		jLabel39 = new javax.swing.JLabel();
		jPanel13 = new javax.swing.JPanel();
		comboElem2 = new javax.swing.JComboBox<ClasseMadeira>();
		jLabel_classe_madeira_2 = new javax.swing.JLabel();
		jLabel_elemento2 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		espessura2 = new javax.swing.JTextField();
		jLabelUnEspessura1 = new javax.swing.JLabel();
		fcok2 = new javax.swing.JLabel();
		unFcok2 = new javax.swing.JLabel();
		valorFc2 = new javax.swing.JLabel();
		densidade2 = new javax.swing.JLabel();
		valorDensidade2 = new javax.swing.JLabel();
		undensidade2 = new javax.swing.JLabel();
		valorFvok2 = new javax.swing.JLabel();
		unFv0k2 = new javax.swing.JLabel();
		fv0k2 = new javax.swing.JLabel();
		ec0m2 = new javax.swing.JLabel();
		unEc0m2 = new javax.swing.JLabel();
		jSeparator2 = new javax.swing.JSeparator();
		valorEc0m2 = new javax.swing.JLabel();
		inclinacaoSim2 = new javax.swing.JRadioButton();
		possuiInclinacao3 = new javax.swing.JLabel();
		jLabel68 = new javax.swing.JLabel();
		madeiraFigura = new javax.swing.JButton();
		jPanel12 = new javax.swing.JPanel();
		jLabel101 = new javax.swing.JLabel();
		jLabel102 = new javax.swing.JLabel();
		jLabel103 = new javax.swing.JLabel();
		jLabel104 = new javax.swing.JLabel();
		comboKmod1 = new javax.swing.JComboBox<Kmod1>();
		comboKmod3 = new javax.swing.JComboBox<Kmod3>();
		comboKmod2 = new javax.swing.JComboBox<Kmod2>();
		textkmod3 = new javax.swing.JLabel();
		textkmod1 = new javax.swing.JLabel();
		textkmod2 = new javax.swing.JLabel();
		next2 = new javax.swing.JButton();
		elementosMetalicos = new javax.swing.JPanel();
		jPanel10 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		tamanhoParafuso = new javax.swing.JLabel();
		comboTipoParafuso = new javax.swing.JComboBox<TipoParafuso>();
		diametro = new javax.swing.JLabel();
		valorDiametro = new javax.swing.JLabel();
		areaParafuso = new javax.swing.JLabel();
		valorArea = new javax.swing.JLabel();
		numeroParafusos = new javax.swing.JLabel();
		classeAco = new javax.swing.JLabel();
		comboAco = new javax.swing.JComboBox<ClasseAcoParafuso>();
		fyk = new javax.swing.JLabel();
		fuk = new javax.swing.JLabel();
		valorFyk = new javax.swing.JLabel();
		valorFuk = new javax.swing.JLabel();
		unFyk = new javax.swing.JLabel();
		unFuk = new javax.swing.JLabel();
		testeParafuso = new javax.swing.JLabel();
		testeParafusoSim = new javax.swing.JRadioButton();
		testeParafusoNao = new javax.swing.JRadioButton();
		comboQuantParafuso = new javax.swing.JComboBox<QuantidadeParafuso>();
		jLabel65 = new javax.swing.JLabel();
		jPanel11 = new javax.swing.JPanel();
		arruelas = new javax.swing.JLabel();
		tipoArruela = new javax.swing.JLabel();
		comboArruelas = new javax.swing.JComboBox<TipoArruela>();
		d1Arruelas = new javax.swing.JLabel();
		d2Arruelas = new javax.swing.JLabel();
		valorD1Arruelas = new javax.swing.JLabel();
		valorD2Arruelas = new javax.swing.JLabel();
		unD1Arruelas = new javax.swing.JLabel();
		unD2Arruelas = new javax.swing.JLabel();
		buttonCalcular = new javax.swing.JButton();
		figuraTipoParafuso = new javax.swing.JButton();
		imagemTipoArruela = new javax.swing.JLabel();
		resultado = new javax.swing.JPanel();
		labelModeloFalha = new javax.swing.JLabel();
		labelresultadoModeloFalha = new javax.swing.JLabel();
		labelResistenciaLigacao = new javax.swing.JLabel();
		resultadoFvk = new javax.swing.JLabel();
		figuraResultadoModoFalha = new javax.swing.JButton();
		jLabel74 = new javax.swing.JLabel();
		jLabel85 = new javax.swing.JLabel();
		jLabel86 = new javax.swing.JLabel();
		jLabel89 = new javax.swing.JLabel();
		jLabel96 = new javax.swing.JLabel();
		resultadoRk = new javax.swing.JLabel();
		resultadoRd = new javax.swing.JLabel();
		jLabel98 = new javax.swing.JLabel();
		jLabel99 = new javax.swing.JLabel();
		jLabel100 = new javax.swing.JLabel();
		next3 = new javax.swing.JButton();
		relatorio = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		relatorioFinal = new javax.swing.JPanel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		relatorioSecaoCorte = new javax.swing.JLabel();
		relatorioAngulacao = new javax.swing.JLabel();
		relatoriofcok4 = new javax.swing.JLabel();
		relatorioElem1 = new javax.swing.JPanel();
		jLabel14 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		jLabel19 = new javax.swing.JLabel();
		relatorioDensidade1 = new javax.swing.JLabel();
		relatorioEc0m1 = new javax.swing.JLabel();
		relatoriofv0k1 = new javax.swing.JLabel();
		relatorioFcok1 = new javax.swing.JLabel();
		jLabel20 = new javax.swing.JLabel();
		jLabel21 = new javax.swing.JLabel();
		jLabel22 = new javax.swing.JLabel();
		jLabel23 = new javax.swing.JLabel();
		relatorioClasseElem1 = new javax.swing.JLabel();
		jLabel51 = new javax.swing.JLabel();
		relatorioEspessura1 = new javax.swing.JLabel();
		jLabel69 = new javax.swing.JLabel();
		inclinado1 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		relatorioElem2 = new javax.swing.JPanel();
		jLabel30 = new javax.swing.JLabel();
		jLabel29 = new javax.swing.JLabel();
		jLabel32 = new javax.swing.JLabel();
		jLabel31 = new javax.swing.JLabel();
		relatorioEc0m2 = new javax.swing.JLabel();
		relatorioDensidade2 = new javax.swing.JLabel();
		relatoriofcok2 = new javax.swing.JLabel();
		relatoriofv0k2 = new javax.swing.JLabel();
		jLabel27 = new javax.swing.JLabel();
		jLabel26 = new javax.swing.JLabel();
		jLabel28 = new javax.swing.JLabel();
		jLabel24 = new javax.swing.JLabel();
		jLabel25 = new javax.swing.JLabel();
		relatorioClasseElem2 = new javax.swing.JLabel();
		relatorioEspessura2 = new javax.swing.JLabel();
		jLabel52 = new javax.swing.JLabel();
		jLabel70 = new javax.swing.JLabel();
		inclinado2 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		relatorioCoeficientes = new javax.swing.JPanel();
		jLabel88 = new javax.swing.JLabel();
		relatoriokmod3 = new javax.swing.JLabel();
		relatorioAngulo = new javax.swing.JLabel();
		relatorioKmod1 = new javax.swing.JLabel();
		relatoriokmod2 = new javax.swing.JLabel();
		jLabel90 = new javax.swing.JLabel();
		jLabel91 = new javax.swing.JLabel();
		jLabel92 = new javax.swing.JLabel();
		jLabel93 = new javax.swing.JLabel();
		jLabel94 = new javax.swing.JLabel();
		jLabel35 = new javax.swing.JLabel();
		jLabel81 = new javax.swing.JLabel();
		jLabel17 = new javax.swing.JLabel();
		relatorioParafuso = new javax.swing.JPanel();
		jLabel33 = new javax.swing.JLabel();
		jLabel34 = new javax.swing.JLabel();
		relatorioTipoParafuso = new javax.swing.JLabel();
		jLabel36 = new javax.swing.JLabel();
		relatorioDiametro = new javax.swing.JLabel();
		jLabel37 = new javax.swing.JLabel();
		relatorioNParafusos = new javax.swing.JLabel();
		jLabel43 = new javax.swing.JLabel();
		relatorioClasseAco = new javax.swing.JLabel();
		jLabel38 = new javax.swing.JLabel();
		relatoriofaxrk = new javax.swing.JLabel();
		jLabel45 = new javax.swing.JLabel();
		relatoriofyk = new javax.swing.JLabel();
		jLabel46 = new javax.swing.JLabel();
		relatoriofuk = new javax.swing.JLabel();
		figuraParafuso = new javax.swing.JLabel();
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
		relatorioTipoArruela = new javax.swing.JLabel();
		relatorioD1 = new javax.swing.JLabel();
		relatorioD2 = new javax.swing.JLabel();
		figuraArruela = new javax.swing.JLabel();
		jLabel75 = new javax.swing.JLabel();
		jLabel76 = new javax.swing.JLabel();
		jSeparator10 = new javax.swing.JSeparator();
		relatorioFibras = new javax.swing.JLabel();
		jLabel44 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jLabel53 = new javax.swing.JLabel();
		calculoForca01 = new javax.swing.JLabel();
		calculoForca02 = new javax.swing.JLabel();
		jLabel58 = new javax.swing.JLabel();
		relatorioFc0d1 = new javax.swing.JLabel();
		relatorioFc0d2 = new javax.swing.JLabel();
		relatorioFaxrk = new javax.swing.JLabel();
		jLabel77 = new javax.swing.JLabel();
		jLabel78 = new javax.swing.JLabel();
		jLabel79 = new javax.swing.JLabel();
		calculoForcaAlfa1 = new javax.swing.JLabel();
		calculoForca901 = new javax.swing.JLabel();
		calculoForcaAlfa2 = new javax.swing.JLabel();
		calculoForca902 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		jLabel59 = new javax.swing.JLabel();
		relatorioRd11 = new javax.swing.JLabel();
		relatorioRd12 = new javax.swing.JLabel();
		relatorioRd13 = new javax.swing.JLabel();
		relatorioRd1 = new javax.swing.JLabel();
		relatorioRd14 = new javax.swing.JLabel();
		relatorioRd2 = new javax.swing.JLabel();
		relatorioRd4 = new javax.swing.JLabel();
		relatorioRd3 = new javax.swing.JLabel();
		relatorioRd15 = new javax.swing.JLabel();
		relatorioRd16 = new javax.swing.JLabel();
		relatorioRd6 = new javax.swing.JLabel();
		relatorioRd5 = new javax.swing.JLabel();
		jLabel56 = new javax.swing.JLabel();
		relatorioMyd = new javax.swing.JLabel();
		jLabel57 = new javax.swing.JLabel();
		relatorioBeta = new javax.swing.JLabel();
		jSeparator4 = new javax.swing.JSeparator();
		jSeparator5 = new javax.swing.JSeparator();
		relatorioRd23 = new javax.swing.JLabel();
		jLabel54 = new javax.swing.JLabel();
		jLabel55 = new javax.swing.JLabel();
		jLabel60 = new javax.swing.JLabel();
		jLabel61 = new javax.swing.JLabel();
		jLabel62 = new javax.swing.JLabel();
		jLabel63 = new javax.swing.JLabel();
		relatorioRd24 = new javax.swing.JLabel();
		jLabel64 = new javax.swing.JLabel();
		jLabel67 = new javax.swing.JLabel();
		modoFalha = new javax.swing.JLabel();
		jLabel87 = new javax.swing.JLabel();
		jLabel95 = new javax.swing.JLabel();
		relatorioRk = new javax.swing.JLabel();
		jSeparator6 = new javax.swing.JSeparator();
		jSeparator7 = new javax.swing.JSeparator();
		figuraSecoes = new javax.swing.JLabel();
		jSeparator9 = new javax.swing.JSeparator();
		data = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		consideracao1 = new javax.swing.JTextArea();
		jScrollPane3 = new javax.swing.JScrollPane();
		teste = new javax.swing.JTextArea();
		hora = new javax.swing.JLabel();
		rLogo = new javax.swing.JLabel();
		jLabel66 = new javax.swing.JLabel();
		relatorioFvk = new javax.swing.JLabel();
		jLabel80 = new javax.swing.JLabel();
		relatorioRd = new javax.swing.JLabel();
		jLabel82 = new javax.swing.JLabel();
		jLabel83 = new javax.swing.JLabel();
		jLabel84 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jProgressBarStatus = new javax.swing.JProgressBar(0, 4);
		jProgressBarStatus.setValue(0);
		painelTipoMadeira = new javax.swing.JPanel();
		serradaButton = new javax.swing.JToggleButton();
		recompostaButton = new javax.swing.JToggleButton();
		painelEspecieMadeira = new javax.swing.JPanel();
		coniferasButton = new javax.swing.JToggleButton();
		folhosasButton = new javax.swing.JToggleButton();

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
		});

		inicio.setBackground(new java.awt.Color(204, 204, 204));

		logoPrograma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Logo/logosket.png"))); // NOI18N

		jLabel97.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
		jLabel97.setText("TCD - Timber Connections Design");

		voltar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		voltar.setText("Voltar");
		voltar.setToolTipText("Escolha esta opção para retornar a tela inicial.");
		voltar.addActionListener(new java.awt.event.ActionListener(){
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

		javax.swing.GroupLayout InicioLayout = new javax.swing.GroupLayout(inicio);
		inicio.setLayout(InicioLayout);
		InicioLayout.setHorizontalGroup(InicioLayout
		        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(InicioLayout.createSequentialGroup().addContainerGap(186, Short.MAX_VALUE).addGroup(InicioLayout
		                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioLayout.createSequentialGroup()
		                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(93, 93, 93)
		                        .addComponent(voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(161, 161, 161))
		                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
		                          InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(InicioLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel40))
		                                  .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
		                                                    InicioLayout
		                                                            .createSequentialGroup().addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                            .addGap(242, 242, 242))
		                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioLayout.createSequentialGroup()
		                                                  .addComponent(logoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(218, 218, 218)))))));
		InicioLayout.setVerticalGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(InicioLayout.createSequentialGroup().addGap(57, 57, 57).addComponent(logoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel97).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(jLabel40).addGap(78, 78, 78)
		                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(234, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Inicio", inicio);

		secoesCorte.setBackground(new java.awt.Color(204, 204, 204));

		groupSecoesCorte.add(btn1SecaoCorte);
		btn1SecaoCorte.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		btn1SecaoCorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/UmaSecaoParafuso.png"))); // NOI18N
		btn1SecaoCorte.setText("Corte Simples");
		btn1SecaoCorte.setToolTipText("Escolha esta opção se a ligação apresentar modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULARnas uma seção de corte no parafuso.");
		btn1SecaoCorte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btn1SecaoCorte.setName(""); // NOI18N
		btn1SecaoCorte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		btn1SecaoCorte.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn1SecaoCorteActionPerformed(evt);
			}
		});

		groupSecoesCorte.add(btn2SecaoCorte);
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

		next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/next.png"))); // NOI18N
		next.setToolTipText("Clique para avançar.");
		next.setEnabled(false);
		next.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nextActionPerformed(evt);
			}
		});

		painelTipoMadeira.setBackground(new java.awt.Color(204, 204, 204));
		painelTipoMadeira
		        .setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Tipo da Madeira",
		                                                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 12))); // NOI18N
		painelTipoMadeira.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

		serradaButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		serradaButton.setText("Serrada/Roliça/MLC/Compensado");
		serradaButton.setToolTipText("");
		serradaButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				serradaButtonActionPerformed(evt);
			}
		});

		recompostaButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		recompostaButton.setText("Recomposta");
		recompostaButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				recompostaButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout PainelTipoMadeiraLayout = new javax.swing.GroupLayout(painelTipoMadeira);
		painelTipoMadeira.setLayout(PainelTipoMadeiraLayout);
		PainelTipoMadeiraLayout.setHorizontalGroup(PainelTipoMadeiraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(PainelTipoMadeiraLayout.createSequentialGroup().addContainerGap(83, Short.MAX_VALUE)
		                .addGroup(PainelTipoMadeiraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
		                                  PainelTipoMadeiraLayout.createSequentialGroup().addComponent(recompostaButton).addGap(135, 135, 135))
		                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelTipoMadeiraLayout.createSequentialGroup().addComponent(serradaButton).addGap(74, 74, 74)))));
		PainelTipoMadeiraLayout.setVerticalGroup(PainelTipoMadeiraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(PainelTipoMadeiraLayout.createSequentialGroup()
		        .addGap(45, 45, 45).addComponent(serradaButton).addGap(58, 58, 58).addComponent(recompostaButton).addContainerGap(49, Short.MAX_VALUE)));

		painelEspecieMadeira.setBackground(new java.awt.Color(204, 204, 204));
		painelEspecieMadeira
		        .setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Espécie da Madeira",
		                                                                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 12))); // NOI18N
		painelEspecieMadeira.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		painelEspecieMadeira.setName(""); // NOI18N

		coniferasButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		coniferasButton.setText("Coníferas");
		coniferasButton.setToolTipText("Alguns exemplos: Pinho-Bravo, Pinho-do-Paraná, Pinus (em geral).");
		coniferasButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				coniferasButtonActionPerformed(evt);
			}
		});

		folhosasButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		folhosasButton.setText("Folhosas");
		folhosasButton.setToolTipText("Alguns exemplos: Peroba-Rosa, Angico, Imbuia, Jatobá, Mogno, Cerejeira, Cedro, Freijó, Aroeira, Ipê, Pau-Marfim.");
		folhosasButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				folhosasButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout PainelEspecieMadeiraLayout = new javax.swing.GroupLayout(painelEspecieMadeira);
		painelEspecieMadeira.setLayout(PainelEspecieMadeiraLayout);
		PainelEspecieMadeiraLayout.setHorizontalGroup(PainelEspecieMadeiraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(PainelEspecieMadeiraLayout.createSequentialGroup().addGap(127, 127, 127)
		                .addGroup(PainelEspecieMadeiraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(folhosasButton).addComponent(coniferasButton))
		                .addContainerGap(142, Short.MAX_VALUE)));
		PainelEspecieMadeiraLayout.setVerticalGroup(PainelEspecieMadeiraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(PainelEspecieMadeiraLayout.createSequentialGroup().addGap(44, 44, 44).addComponent(coniferasButton)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(folhosasButton).addGap(50, 50, 50)));

		javax.swing.GroupLayout SecoesCorteLayout = new javax.swing.GroupLayout(secoesCorte);
		secoesCorte.setLayout(SecoesCorteLayout);
		SecoesCorteLayout.setHorizontalGroup(SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(SecoesCorteLayout.createSequentialGroup().addGap(111, 111, 111)
		                .addComponent(btn1SecaoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(75, 75, 75)
		                .addComponent(btn2SecaoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(134, Short.MAX_VALUE))
		        .addGroup(SecoesCorteLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(painelEspecieMadeira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(painelTipoMadeira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SecoesCorteLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
		SecoesCorteLayout.setVerticalGroup(SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(SecoesCorteLayout.createSequentialGroup().addGap(18, 18, 18)
		                .addGroup(SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                        .addComponent(btn2SecaoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(btn1SecaoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(SecoesCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                        .addComponent(painelTipoMadeira, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                        .addComponent(painelEspecieMadeira, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(next).addContainerGap(41, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Modelos de Ligação", secoesCorte);

		elementosMadeira.setBackground(new java.awt.Color(204, 204, 204));
		elementosMadeira.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jPanel7.setBackground(new java.awt.Color(153, 153, 153));
		jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel7.setPreferredSize(new java.awt.Dimension(385, 225));
		jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		comboElem1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboElem1.setModel(new javax.swing.DefaultComboBoxModel<ClasseMadeira>(ClasseMadeira.values()));
		comboElem1.setToolTipText("Defina a classe de madeira do elemento 1, baseado nas tabelas 2 e 3 da revisão da norma ABNT NBR 7190 (2011).");
		comboElem1.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusLost(java.awt.event.FocusEvent evt) {
				comboElem1ClasseMadeiraFocusLost(evt);
			}
		});
		jPanel7.add(comboElem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 35, 211, 29));

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

		valorAngulo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorAngulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		valorAngulo.setText("0");
		valorAngulo.setToolTipText("Insira o ângulo entre os elementos 1 e 2 de madeira.");
		valorAngulo.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusGained(java.awt.event.FocusEvent evt) {
				valorAngulo(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				valorAnguloFocusLost(evt);
			}
		});
		valorAngulo.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				valorAnguloActionPerformed(evt);
			}
		});
		valorAngulo.addPropertyChangeListener(new java.beans.PropertyChangeListener(){
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				valorAnguloPropertyChange(evt);
			}
		});
		valorAngulo.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyTyped(java.awt.event.KeyEvent evt) {
				valorAnguloKeyTyped(evt);
			}
		});
		jPanel7.add(valorAngulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 70, -1));

		jLabelUnEspessura.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabelUnEspessura.setText("(mm)");
		jPanel7.add(jLabelUnEspessura, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 147, -1, -1));

		fcok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		fcok1.setText("fc0,k:");
		jPanel7.add(fcok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

		unFcok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unFcok1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		unFcok1.setText("(MPa)");
		jPanel7.add(unFcok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));
		unFcok1.getAccessibleContext().setAccessibleDescription("");

		valorFc01.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorFc01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorFc01.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel7.add(valorFc01, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 80, 40, 20));

		densidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		densidade1.setText("densidade:");
		jPanel7.add(densidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));

		valorDensidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorDensidade1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorDensidade1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel7.add(valorDensidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 40, 20));

		undensidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		undensidade1.setText("(kg/m³)");
		jPanel7.add(undensidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 80, -1, -1));

		valorFvok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorFvok1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorFvok1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel7.add(valorFvok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 110, 40, 20));

		unFv0k1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unFv0k1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		unFv0k1.setText("(MPa)");
		jPanel7.add(unFv0k1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

		fv0k1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		fv0k1.setText("fv0,k:");
		jPanel7.add(fv0k1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

		ec0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ec0m1.setText("Ec0,m:");
		jPanel7.add(ec0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));

		unEc0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unEc0m1.setText("(MPa)");
		jPanel7.add(unEc0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 110, -1, -1));

		valorEc0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorEc0m1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorEc0m1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		valorEc0m1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jPanel7.add(valorEc0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 50, 20));

		jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jPanel7.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 69, 10, 65));

		possuiInclinacao1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		possuiInclinacao1.setText("A força indicada na figura é paralela");
		jPanel7.add(possuiInclinacao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 195, -1, -1));

		groupInclinacaoSim.add(inclinacaoSim1);
		inclinacaoSim1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		inclinacaoSim1.setText("Sim");
		inclinacaoSim1.setToolTipText("Se existir inclinação, indique qual elemento está inclinado.");
		inclinacaoSim1.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				InclinacaoSim1ActionPerformed(evt);
			}
		});
		jPanel7.add(inclinacaoSim1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, -1, -1));

		possuiInclinacao11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		possuiInclinacao11.setText("às fibras do elemento 1?");
		jPanel7.add(possuiInclinacao11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

		jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel5.setText("Ângulo entre as peças:");
		jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 175, 150, -1));

		espessura1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		espessura1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		espessura1.setText("Digite a espessura");
		espessura1.setToolTipText("Insira a espessura do elemento 1. O elemento 1 está indicado na imagem.");
		espessura1.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusGained(java.awt.event.FocusEvent evt) {
				Espessura1(evt);
			}
		});
		espessura1.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Espessura1ActionPerformed(evt);
			}
		});
		espessura1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				Espessura1PropertyChange(evt);
			}
		});
		espessura1.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyTyped(java.awt.event.KeyEvent evt) {
				Espessura1KeyTyped(evt);
			}
		});
		jPanel7.add(espessura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 143, 130, -1));

		jLabel39.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel39.setText("(°)");
		jPanel7.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 170, 20, 20));

		elementosMadeira.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 5, -1, 230));

		jPanel13.setBackground(new java.awt.Color(153, 153, 153));
		jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel13.setPreferredSize(new java.awt.Dimension(385, 225));
		jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		comboElem2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboElem2.setModel(new javax.swing.DefaultComboBoxModel<ClasseMadeira>(ClasseMadeira.values()));
		comboElem2.setToolTipText("Defina a classe de madeira do elemento 2, baseado nas tabelas 2 e 3 da revisão da norma ABNT NBR 7190 (2017).");
		comboElem2.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusLost(java.awt.event.FocusEvent evt) {
				comboElem2ClasseMadeiraFocusLost(evt);
			}
		});
		jPanel13.add(comboElem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 30, 211, 29));

		jLabel_classe_madeira_2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel_classe_madeira_2.setText("Classe da Madeira:");
		jPanel13.add(jLabel_classe_madeira_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

		jLabel_elemento2.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
		jLabel_elemento2.setText("Elemento 2");
		jPanel13.add(jLabel_elemento2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 5, 88, 22));

		jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel4.setText("Espessura (t2):");
		jPanel13.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

		espessura2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		espessura2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		espessura2.setText("Digite a espessura");
		espessura2.setToolTipText("Insira a espessura do elemento 2. O elemento 2 está indicado na imagem.");
		espessura2.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusGained(java.awt.event.FocusEvent evt) {
				LimparExpessura2(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				espessura2FocusLost(evt);
			}
		});
		espessura2.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyTyped(java.awt.event.KeyEvent evt) {
				Espessura2KeyTyped(evt);
			}
		});
		jPanel13.add(espessura2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 138, 126, -1));

		jLabelUnEspessura1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabelUnEspessura1.setText("(mm)");
		jPanel13.add(jLabelUnEspessura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 140, -1, -1));

		fcok2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		fcok2.setText("fc0,k:");
		jPanel13.add(fcok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 75, -1, -1));

		unFcok2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unFcok2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		unFcok2.setText("(MPa)");
		jPanel13.add(unFcok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 75, -1, -1));

		valorFc2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorFc2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorFc2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel13.add(valorFc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 75, 40, 20));

		densidade2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		densidade2.setText("densidade:");
		jPanel13.add(densidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 75, -1, -1));

		valorDensidade2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorDensidade2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorDensidade2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel13.add(valorDensidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 75, 40, 20));

		undensidade2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		undensidade2.setText("(kg/m³)");
		jPanel13.add(undensidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 75, -1, -1));

		valorFvok2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorFvok2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorFvok2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel13.add(valorFvok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 105, 40, 20));

		unFv0k2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unFv0k2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		unFv0k2.setText("(MPa)");
		jPanel13.add(unFv0k2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 105, -1, -1));

		fv0k2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		fv0k2.setText("fv0,k:");
		jPanel13.add(fv0k2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 105, -1, -1));

		ec0m2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ec0m2.setText("Ec0,m:");
		jPanel13.add(ec0m2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 105, -1, -1));

		unEc0m2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unEc0m2.setText("(MPa)");
		jPanel13.add(unEc0m2, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 105, -1, -1));

		jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jPanel13.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 66, 10, 65));

		valorEc0m2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorEc0m2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorEc0m2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel13.add(valorEc0m2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 105, 50, 20));

		groupInclinacaoSim.add(inclinacaoSim2);
		inclinacaoSim2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		inclinacaoSim2.setSelected(true);
		inclinacaoSim2.setText("Sim");
		inclinacaoSim2.setToolTipText("Se existir inclinação, indique qual elemento está inclinado.");
		inclinacaoSim2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				InclinacaoSim2ActionPerformed(evt);
			}
		});
		jPanel13.add(inclinacaoSim2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 175, -1, -1));

		possuiInclinacao3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		possuiInclinacao3.setText("A força indicada na figura é paralela");
		jPanel13.add(possuiInclinacao3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

		jLabel68.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel68.setText("às fibras do elemento 2?");
		jPanel13.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 185, -1, -1));

		elementosMadeira.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 240, -1, 205));

		madeiraFigura.setToolTipText("Esta imagem caracteriza os elementos de madeira.");
		madeiraFigura.setContentAreaFilled(false);
		elementosMadeira.add(madeiraFigura, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 350, 280));

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

		comboKmod1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboKmod1.setModel(new javax.swing.DefaultComboBoxModel<Kmod1>(Kmod1.values()));
		comboKmod1.setToolTipText("Insira o valor do Kmod 1, o qual é definido pela tabela 4 da revisão da norma ABNT NBR 7190 (2011).");
		comboKmod1.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboKmod1ActionPerformed(evt);
			}
		});
		jPanel12.add(comboKmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 340, 30));

		comboKmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboKmod3.setModel(new javax.swing.DefaultComboBoxModel<Kmod3>(Kmod3.values()));
		comboKmod3.setToolTipText("Insira o valor do Kmod 3, o qual é definido pelas tabelas 6 e 7 da revisão da norma ABNT NBR 7190 (2011).");
		comboKmod3.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboKmod3ActionPerformed(evt);
			}
		});
		jPanel12.add(comboKmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 340, 30));

		comboKmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboKmod2.setModel(new javax.swing.DefaultComboBoxModel<Kmod2>(Kmod2.values()));
		comboKmod2.setToolTipText("Insira o valor do Kmod 2, o qual é definido pela tabela 5 da revisão da norma ABNT NBR 7190 (2011).");
		comboKmod2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboKmod2ActionPerformed(evt);
			}
		});
		jPanel12.add(comboKmod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 340, 30));

		textkmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		textkmod3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		textkmod3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel12.add(textkmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 125, 40, 20));

		textkmod1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		textkmod1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		textkmod1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel12.add(textkmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 45, 40, 20));

		textkmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		textkmod2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		textkmod2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel12.add(textkmod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 85, 40, 20));

		elementosMadeira.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 450, 710, 160));

		next2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/next.png"))); // NOI18N
		next2.setToolTipText("Clique para avançar.");
		next2.setEnabled(false);
		next2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				next2ActionPerformed(evt);
			}
		});
		elementosMadeira.add(next2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 570, 40, -1));

		jTabbedPane1.addTab("Elementos da Ligação", elementosMadeira);

		elementosMetalicos.setBackground(new java.awt.Color(204, 204, 204));
		elementosMetalicos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jPanel10.setBackground(new java.awt.Color(153, 153, 153));
		jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel1.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
		jLabel1.setText("PARAFUSO");
		jPanel10.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 13, -1, -1));

		tamanhoParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		tamanhoParafuso.setText("Tipo de Parafuso:");
		jPanel10.add(tamanhoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 43, -1, -1));

		comboTipoParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboTipoParafuso.setModel(new javax.swing.DefaultComboBoxModel<TipoParafuso>(TipoParafuso.values()));
		comboTipoParafuso.setToolTipText("Escolha o tipo de parafuso utilizado, baseado na norma EN ISO 4016 (2001).");
		comboTipoParafuso.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		comboTipoParafuso.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusLost(java.awt.event.FocusEvent evt) {
				ComboTipoParafusoFocusLost(evt);
			}
		});
		comboTipoParafuso.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboTipoParafusoActionPerformed(evt);
			}
		});
		jPanel10.add(comboTipoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 41, 252, -1));

		diametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		diametro.setText("Diâmetro (mm):");
		jPanel10.add(diametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 72, -1, -1));

		valorDiametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorDiametro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorDiametro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.add(valorDiametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 40, 20));

		areaParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		areaParafuso.setText("Área do Parafuso (mm²):");
		jPanel10.add(areaParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 72, -1, -1));

		valorArea.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.add(valorArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 40, 20));

		numeroParafusos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		numeroParafusos.setText("Número de Parafusos:");
		jPanel10.add(numeroParafusos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 102, -1, -1));

		classeAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		classeAco.setText("Classe do Aço:");
		jPanel10.add(classeAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 142, -1, -1));

		comboAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboAco.setModel(new javax.swing.DefaultComboBoxModel<ClasseAcoParafuso>(ClasseAcoParafuso.values()));
		comboAco.setToolTipText("Defina a classe do aço do parafuso, baseado na norma ABNT NBR 8800 (2008).");
		comboAco.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboAcoActionPerformed(evt);
			}
		});
		jPanel10.add(comboAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 139, 221, -1));

		fyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		fyk.setText("fy,k:");
		jPanel10.add(fyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 177, -1, -1));

		fuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		fuk.setText("fu,k:");
		jPanel10.add(fuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 177, -1, -1));

		valorFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorFyk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorFyk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.add(valorFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 175, 40, 20));

		valorFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorFuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorFuk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.add(valorFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 175, 40, 20));

		unFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unFyk.setText("(MPa)");
		jPanel10.add(unFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 177, -1, -1));

		unFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unFuk.setText("(MPa)");
		jPanel10.add(unFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 177, -1, -1));

		testeParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		testeParafuso.setText("Foram realizados ensaios na ligação em estudo que comprovem ");
		testeParafuso.setToolTipText("Considera-se ou não a força de arrancamento causada pelo parafuso na madeira. EUROCODE 5 (2004);");
		jPanel10.add(testeParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

		groupTesteParafuso.add(testeParafusoSim);
		testeParafusoSim.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		testeParafusoSim.setText("Sim");
		testeParafusoSim.setToolTipText("Escolha se a ligação considera ou não a força de arrancamento causada pelo parafuso na madeira.");
		testeParafusoSim.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TesteParafusoSimActionPerformed(evt);
			}
		});
		jPanel10.add(testeParafusoSim, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 235, 50, -1));

		groupTesteParafuso.add(testeParafusoNao);
		testeParafusoNao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		testeParafusoNao.setText("Não");
		testeParafusoNao.setToolTipText("Escolha se a ligação considera ou não a força de arrancamento causada pelo parafuso na madeira.");
		testeParafusoNao.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TesteParafusoNaoActionPerformed(evt);
			}
		});
		jPanel10.add(testeParafusoNao, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 235, 50, -1));

		comboQuantParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboQuantParafuso.setModel(new javax.swing.DefaultComboBoxModel<QuantidadeParafuso>(QuantidadeParafuso.values()));
		comboQuantParafuso.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboQuantParafusoActionPerformed(evt);
			}
		});
		jPanel10.add(comboQuantParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 230, -1));

		jLabel65.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel65.setText("que o efeito de confinamento possa ser utilizado?");
		jPanel10.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 215, -1, -1));

		elementosMetalicos.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 42, 425, 265));

		jPanel11.setBackground(new java.awt.Color(153, 153, 153));
		jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		arruelas.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
		arruelas.setText("ARRUELAS");
		jPanel11.add(arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 13, -1, -1));

		tipoArruela.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		tipoArruela.setText("Tipo de Arruela:");
		jPanel11.add(tipoArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 48, -1, -1));

		comboArruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboArruelas.setModel(new javax.swing.DefaultComboBoxModel<TipoArruela>(new TipoArruela[] {TipoArruela.ESCOLHA_TIPO_ARRUELA}));
		comboArruelas.setToolTipText("Defina o tipo de arruela, baseado nas normas DIN 440 R, DIN 440 V e DIN 436.");
		comboArruelas.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ComboArruelasActionPerformed(evt);
			}
		});
		jPanel11.add(comboArruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 45, 226, -1));

		d1Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		d1Arruelas.setText("Dimensão (d1):");
		jPanel11.add(d1Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 97, -1, -1));

		d2Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		d2Arruelas.setText("Dimensão (d2):");
		jPanel11.add(d2Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 97, -1, -1));

		valorD1Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorD1Arruelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorD1Arruelas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel11.add(valorD1Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 95, 40, 20));

		valorD2Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorD2Arruelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorD2Arruelas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel11.add(valorD2Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 95, 40, 20));

		unD1Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unD1Arruelas.setText("(mm)");
		jPanel11.add(unD1Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 97, -1, -1));

		unD2Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unD2Arruelas.setText("(mm)");
		jPanel11.add(unD2Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 97, -1, -1));

		elementosMetalicos.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 320, 425, 130));

		buttonCalcular.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
		buttonCalcular.setText("CALCULAR LIGAÇÃO");
		buttonCalcular.setToolTipText("Clique aqui para calcular sua ligação. Fique atento! Caso haja informações faltantes ou inconsistentes, o cálculo não será realizado e aparecerá uma mensagem.");
		buttonCalcular.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ButtonCalcularActionPerformed(evt);
			}
		});
		elementosMetalicos.add(buttonCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 468, 210, 41));

		figuraTipoParafuso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Imagens/Parafuso.png"))); // NOI18N
		figuraTipoParafuso.setContentAreaFilled(false);
		figuraTipoParafuso.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				FiguraTipoParafusoActionPerformed(evt);
			}
		});
		elementosMetalicos.add(figuraTipoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 83, 295, 150));
		elementosMetalicos.add(imagemTipoArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 300, 150));

		jTabbedPane1.addTab("Conectores", elementosMetalicos);

		resultado.setBackground(new java.awt.Color(204, 204, 204));
		resultado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		labelModeloFalha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		labelModeloFalha.setText("Modelo de falha:");
		resultado.add(labelModeloFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 120, 20));

		labelresultadoModeloFalha.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		resultado.add(labelresultadoModeloFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 660, 20));

		labelResistenciaLigacao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		labelResistenciaLigacao.setText("Fv,Rk:");
		resultado.add(labelResistenciaLigacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 60, 20));

		resultadoFvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		resultadoFvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		resultado.add(resultadoFvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 80, 20));

		figuraResultadoModoFalha.setContentAreaFilled(false);
		figuraResultadoModoFalha.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				FiguraresultadoModoFalhaActionPerformed(evt);
			}
		});
		resultado.add(figuraResultadoModoFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 335, 245));

		jLabel74.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel74.setText("(N)");
		resultado.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 60, 20));

		jLabel85.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel85.setText("Rk:");
		resultado.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, 20));

		jLabel86.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel86.setText("(N)");
		resultado.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 60, 20));

		jLabel89.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel89.setText("Rd:");
		resultado.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, 20));

		jLabel96.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel96.setText("(N)");
		resultado.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 60, 20));

		resultadoRk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		resultadoRk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		resultado.add(resultadoRk, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 80, 20));

		resultadoRd.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		resultadoRd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		resultado.add(resultadoRd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 80, 20));

		jLabel98.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel98.setText("*Fv,Rk = resistência característica de uma seção de corte por parafuso.");
		resultado.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 450, -1));

		jLabel99.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel99.setText("*Rk = resistência característica da ligação considerando as quantidades de seções de corte e parafusos. ");
		resultado.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 670, -1));

		jLabel100.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel100.setText("*Rd = resistência de cálculo da ligação.");
		resultado.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 670, -1));

		next3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/next.png"))); // NOI18N
		next3.setToolTipText("Clique para avançar.");
		next3.setEnabled(false);
		next3.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				next3ActionPerformed(evt);
			}
		});
		resultado.add(next3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 480, 40, -1));

		jTabbedPane1.addTab("Resultado", resultado);

		relatorio.setBackground(new java.awt.Color(204, 204, 204));
		relatorio.setEnabled(false);

		relatorioFinal.setBackground(new java.awt.Color(255, 255, 255));
		relatorioFinal.setPreferredSize(new java.awt.Dimension(760, 1000));
		relatorioFinal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel12.setText("ELEMENTOS DA LIGAÇÃO");
		relatorioFinal.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 145, -1, -1));

		jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
		jLabel13.setText("RELATÓRIO DE RESISTÊNCIA DA LIGAÇÃO ");
		relatorioFinal.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 39, -1, -1));

		relatorioSecaoCorte.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioSecaoCorte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		relatorioFinal.add(relatorioSecaoCorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 190, 14));

		relatorioAngulacao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioAngulacao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		relatorioFinal.add(relatorioAngulacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 180, 14));
		relatorioFinal.add(relatoriofcok4, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 658, -1, -1));

		relatorioElem1.setBackground(new java.awt.Color(255, 255, 255));
		relatorioElem1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		relatorioElem1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel14.setText("Elemento 1:");
		relatorioElem1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, 80, -1));

		jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel2.setText("fc0,k:");
		relatorioElem1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, -1, -1));

		jLabel16.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel16.setText("fv0,k:");
		relatorioElem1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, -1, -1));

		jLabel18.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel18.setText("Ec0,m:");
		relatorioElem1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, -1, -1));

		jLabel19.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel19.setText("densidade:");
		relatorioElem1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 70, -1));

		relatorioDensidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioDensidade1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioDensidade1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatorioDensidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 85, 35, 14));

		relatorioEc0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioEc0m1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioEc0m1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatorioEc0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 65, 50, 14));

		relatoriofv0k1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatoriofv0k1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatoriofv0k1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatoriofv0k1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 45, 35, 14));

		relatorioFcok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFcok1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFcok1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatorioFcok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 25, 35, 14));

		jLabel20.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel20.setText("(MPa)");
		relatorioElem1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 25, 40, -1));

		jLabel21.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel21.setText("(MPa)");
		relatorioElem1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 45, 40, -1));

		jLabel22.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel22.setText("(MPa)");
		relatorioElem1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 65, 40, -1));

		jLabel23.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel23.setText("(kg/m³)");
		relatorioElem1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 85, 40, -1));
		relatorioElem1.add(relatorioClasseElem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 5, 40, 14));

		jLabel51.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel51.setText("Espessura:");
		relatorioElem1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, -1, -1));

		relatorioEspessura1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioEspessura1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatorioEspessura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 105, 35, 14));

		jLabel69.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel69.setText("(mm)");
		relatorioElem1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 105, 40, -1));

		inclinado1.setBorder(null);
		inclinado1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		inclinado1.setToolTipText("");
		inclinado1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		inclinado1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

		jTextArea2.setEditable(false);
		jTextArea2.setColumns(20);
		jTextArea2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
		jTextArea2.setLineWrap(true);
		jTextArea2.setRows(5);
		jTextArea2.setText("Força paralela às fibras deste\nelemento.");
		inclinado1.setViewportView(jTextArea2);

		relatorioElem1.add(inclinado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 122, 170, 30));

		relatorioFinal.add(relatorioElem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 175, 180, 155));

		relatorioElem2.setBackground(new java.awt.Color(255, 255, 255));
		relatorioElem2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		relatorioElem2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel30.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel30.setText("(MPa)");
		relatorioElem2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 45, -1, -1));

		jLabel29.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel29.setText("(MPa)");
		relatorioElem2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 25, -1, -1));

		jLabel32.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel32.setText("(kg/m³)");
		relatorioElem2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 85, -1, -1));

		jLabel31.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel31.setText("(MPa)");
		relatorioElem2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 65, -1, -1));

		relatorioEc0m2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioEc0m2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioElem2.add(relatorioEc0m2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 65, 50, 14));

		relatorioDensidade2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioDensidade2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioElem2.add(relatorioDensidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 85, 35, 14));

		relatoriofcok2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatoriofcok2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioElem2.add(relatoriofcok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 25, 35, 14));

		relatoriofv0k2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatoriofv0k2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioElem2.add(relatoriofv0k2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 45, 35, 14));

		jLabel27.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel27.setText("Ec0,m:");
		relatorioElem2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, -1, -1));

		jLabel26.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel26.setText("fv0,k:");
		relatorioElem2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, -1, -1));

		jLabel28.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel28.setText("densidade:");
		relatorioElem2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 70, -1));

		jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel24.setText("Elemento 2:");
		relatorioElem2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));

		jLabel25.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel25.setText("fc0,k:");
		relatorioElem2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, -1, -1));
		relatorioElem2.add(relatorioClasseElem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 5, 40, 14));

		relatorioEspessura2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioEspessura2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioElem2.add(relatorioEspessura2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 105, 35, 14));

		jLabel52.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel52.setText("Espessura:");
		relatorioElem2.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, -1, -1));

		jLabel70.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel70.setText("(mm)");
		relatorioElem2.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 105, -1, -1));

		inclinado2.setBorder(null);
		inclinado2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		inclinado2.setToolTipText("");
		inclinado2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		inclinado2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

		jTextArea1.setEditable(false);
		jTextArea1.setColumns(20);
		jTextArea1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
		jTextArea1.setLineWrap(true);
		jTextArea1.setRows(5);
		jTextArea1.setText("Força paralela às fibras deste\nelemento.");
		inclinado2.setViewportView(jTextArea1);

		relatorioElem2.add(inclinado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 122, 170, 30));

		relatorioFinal.add(relatorioElem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 175, 180, 155));

		relatorioCoeficientes.setBackground(new java.awt.Color(255, 255, 255));
		relatorioCoeficientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		relatorioCoeficientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel88.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel88.setText("(°)");
		relatorioCoeficientes.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, 20));

		relatoriokmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatoriokmod3.setText("densidade:");
		relatorioCoeficientes.add(relatoriokmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 75, 35, 14));

		relatorioAngulo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioAngulo.setText("De");
		relatorioCoeficientes.add(relatorioAngulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 25, 20));

		relatorioKmod1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioKmod1.setText("jLabel20");
		relatorioCoeficientes.add(relatorioKmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 25, 35, 14));

		relatoriokmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatoriokmod2.setText("densidade:");
		relatorioCoeficientes.add(relatoriokmod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 35, 14));

		jLabel90.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel90.setText("kmod3:");
		relatorioCoeficientes.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 75, 50, -1));

		jLabel91.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel91.setText("kmod2");
		relatorioCoeficientes.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 50, 50, -1));

		jLabel92.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel92.setText("Ângulo:");
		relatorioCoeficientes.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 100, 50, 20));

		jLabel93.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel93.setText("Coeficientes");
		relatorioCoeficientes.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));

		jLabel94.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel94.setText("kmod1:");
		relatorioCoeficientes.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 25, 50, -1));

		jLabel35.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel35.setText("γm,ligação:");
		relatorioCoeficientes.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 125, -1, -1));

		jLabel81.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel81.setText("1,4");
		relatorioCoeficientes.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 125, -1, -1));

		relatorioFinal.add(relatorioCoeficientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 175, 180, 150));

		jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel17.setText("CONECTORES");
		relatorioFinal.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 336, -1, -1));

		relatorioParafuso.setBackground(new java.awt.Color(255, 255, 255));
		relatorioParafuso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		relatorioParafuso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel33.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel33.setText("Parafuso");
		relatorioParafuso.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));

		jLabel34.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel34.setText("Tipo de Parafuso:");
		relatorioParafuso.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, 14));

		relatorioTipoParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioTipoParafuso.setText("jLabel35");
		relatorioParafuso.add(relatorioTipoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 230, -1));

		jLabel36.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel36.setText("d:");
		relatorioParafuso.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 20, 14));

		relatorioDiametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioDiametro.setText("jLabel37");
		relatorioParafuso.add(relatorioDiametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 35, 14));

		jLabel37.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel37.setText("Número de Parafusos:");
		relatorioParafuso.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 120, -1));

		relatorioNParafusos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioParafuso.add(relatorioNParafusos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 25, 14));

		jLabel43.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel43.setText("Classe de Aço:");
		relatorioParafuso.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 80, -1));

		relatorioClasseAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioClasseAco.setText("jLabel44");
		relatorioParafuso.add(relatorioClasseAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 250, -1));

		jLabel38.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel38.setText("Consideração da Força de Arrancamento:");
		relatorioParafuso.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 110, 210, -1));

		relatoriofaxrk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatoriofaxrk.setText("jLabel44");
		relatorioParafuso.add(relatoriofaxrk, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 110, 35, 14));

		jLabel45.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel45.setText("fy,k:");
		relatorioParafuso.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 30, 14));

		relatoriofyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatoriofyk.setText("jLabel46");
		relatorioParafuso.add(relatoriofyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 90, 35, 14));

		jLabel46.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel46.setText("fu,k:");
		relatorioParafuso.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 30, 14));

		relatoriofuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatoriofuk.setText("jLabel47");
		relatorioParafuso.add(relatoriofuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 90, 35, 14));

		figuraParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		figuraParafuso.setText("jLabel35");
		relatorioParafuso.add(figuraParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 110, 30));

		jLabel71.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel71.setText("(mm)");
		relatorioParafuso.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 50, 40, 14));

		jLabel72.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel72.setText("(MPa)");
		relatorioParafuso.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 90, 40, -1));

		jLabel73.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel73.setText("(MPa)");
		relatorioParafuso.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 40, 14));

		jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
		relatorioParafuso.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 5, 14));

		jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
		relatorioParafuso.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 10, 14));

		relatorioFinal.add(relatorioParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 355, 335, 135));

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

		relatorioTipoArruela.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioTipoArruela.setText("jLabel51");
		jPanel1.add(relatorioTipoArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, -1, -1));

		relatorioD1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioD1.setText("jLabel51");
		jPanel1.add(relatorioD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 95, 35, 14));

		relatorioD2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioD2.setText("jLabel51");
		jPanel1.add(relatorioD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 95, 35, 14));

		figuraArruela.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		figuraArruela.setText("jLabel74");
		jPanel1.add(figuraArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, 50));

		jLabel75.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel75.setText("(mm)");
		jPanel1.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 95, 30, -1));

		jLabel76.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel76.setText("(mm)");
		jPanel1.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 95, 40, -1));

		jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 95, 20, 14));

		relatorioFinal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 355, 230, 135));
		relatorioFinal.add(relatorioFibras, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 313, -1, -1));

		jLabel44.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel44.setText("VALORES   CALCULADOS");
		relatorioFinal.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 505, -1, -1));

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel53.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel53.setText("Cálculos Preliminares");
		jPanel2.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, -1, -1));

		calculoForca01.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		calculoForca01.setText("fe0,k1:");
		jPanel2.add(calculoForca01, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 35, 40, -1));

		calculoForca02.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		calculoForca02.setText("fe0,k2:");
		jPanel2.add(calculoForca02, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 60, 40, -1));

		jLabel58.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel58.setText("Fax,rk:");
		jPanel2.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 85, 40, -1));

		relatorioFc0d1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFc0d1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFc0d1.setText("jLabel59");
		jPanel2.add(relatorioFc0d1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 35, 40, -1));

		relatorioFc0d2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFc0d2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFc0d2.setText("jLabel59");
		jPanel2.add(relatorioFc0d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 40, -1));

		relatorioFaxrk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFaxrk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFaxrk.setText("jLabel59");
		jPanel2.add(relatorioFaxrk, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 85, 50, -1));

		jLabel77.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel77.setText("(N)");
		jPanel2.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 85, 30, -1));

		jLabel78.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel78.setText("(MPa)");
		jPanel2.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 35, 40, -1));

		jLabel79.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel79.setText("(MPa)");
		jPanel2.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 40, -1));

		calculoForcaAlfa1.setText("feα,k1:");
		jPanel2.add(calculoForcaAlfa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 35, 40, -1));

		calculoForca901.setText("fe90,k1:");
		jPanel2.add(calculoForca901, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 35, 50, -1));

		calculoForcaAlfa2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		calculoForcaAlfa2.setText("feα,k2:");
		jPanel2.add(calculoForcaAlfa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 60, 40, -1));

		calculoForca902.setText("fe90,k2:");
		jPanel2.add(calculoForca902, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 60, 50, -1));

		relatorioFinal.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 160, 110));

		jPanel3.setBackground(new java.awt.Color(255, 255, 255));
		jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel59.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel59.setText("Cálculo do Eurocode 5");
		jPanel3.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

		relatorioRd11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd11.setText("Fv,k1:");
		jPanel3.add(relatorioRd11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 40, -1));

		relatorioRd12.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd12.setText("Fv,k2:");
		jPanel3.add(relatorioRd12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 40, -1));

		relatorioRd13.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd13.setText("Fv,k3:");
		jPanel3.add(relatorioRd13, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 60, 40, -1));

		relatorioRd1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioRd1.setText("jLabel63");
		jPanel3.add(relatorioRd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 50, -1));

		relatorioRd14.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd14.setText("Fv,k4:");
		jPanel3.add(relatorioRd14, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 80, 40, -1));

		relatorioRd2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioRd2.setText("jLabel64");
		jPanel3.add(relatorioRd2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 50, -1));

		relatorioRd4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioRd4.setText("jLabel64");
		jPanel3.add(relatorioRd4, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 80, 50, -1));

		relatorioRd3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioRd3.setText("jLabel63");
		jPanel3.add(relatorioRd3, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 60, 50, -1));

		relatorioRd15.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd15.setText("Fv,k5:");
		jPanel3.add(relatorioRd15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 40, -1));

		relatorioRd16.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd16.setText("Fv,k6:");
		jPanel3.add(relatorioRd16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 40, -1));

		relatorioRd6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioRd6.setText("jLabel64");
		jPanel3.add(relatorioRd6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 50, -1));

		relatorioRd5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioRd5.setText("jLabel63");
		jPanel3.add(relatorioRd5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 50, -1));

		jLabel56.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel56.setText("Myk:");
		jPanel3.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 30, -1));

		relatorioMyd.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioMyd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioMyd.setText("jLabel59");
		jPanel3.add(relatorioMyd, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 30, 70, -1));

		jLabel57.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel57.setText("β:");
		jPanel3.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 30, 15, -1));

		relatorioBeta.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioBeta.setText("jLabel59");
		jPanel3.add(relatorioBeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 30, -1, -1));
		jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 360, 10));
		jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 350, 10));

		relatorioRd23.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd23.setText("Fv,k3:");
		jPanel3.add(relatorioRd23, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 40, -1));

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

		relatorioRd24.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd24.setText("Fv,k4:");
		jPanel3.add(relatorioRd24, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 40, -1));

		jLabel64.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel64.setText("(N.mm)");
		jPanel3.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 30, 40, -1));

		relatorioFinal.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 530, 400, 110));

		jLabel67.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel67.setText("RESULTADO:");
		relatorioFinal.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 655, -1, -1));

		modoFalha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		relatorioFinal.add(modoFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, 170, 120));

		jLabel87.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel87.setText("Tipo de ruptura:");
		relatorioFinal.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 670, -1, -1));

		jLabel95.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel95.setText("Rk:");
		relatorioFinal.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 740, 70, -1));

		relatorioRk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFinal.add(relatorioRk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 740, 70, 15));
		relatorioFinal.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 600, 10));
		relatorioFinal.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 135, 600, 10));

		figuraSecoes.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		figuraSecoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		relatorioFinal.add(figuraSecoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 60, 80, 70));
		relatorioFinal.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 600, 10));

		data.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		data.setText("jLabel35");
		relatorioFinal.add(data, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 870, 80, -1));

		jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		jScrollPane2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

		consideracao1.setEditable(false);
		consideracao1.setColumns(20);
		consideracao1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		consideracao1.setLineWrap(true);
		consideracao1.setRows(5);
		consideracao1
		        .setText("*Fv,Rk = resistência característica de uma seção de corte por parafuso.\n*Rk = resistência característica da ligação considerando as quantidades de seções de corte e parafusos.\n*Rd = resistência de cálculo da ligação.");
		consideracao1.setWrapStyleWord(true);
		consideracao1.setBorder(null);
		jScrollPane2.setViewportView(consideracao1);

		relatorioFinal.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 810, 570, 50));

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

		relatorioFinal.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 660, 310, 30));

		hora.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		hora.setText("jLabel65");
		relatorioFinal.add(hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 870, -1, -1));
		relatorioFinal.add(rLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, 80));

		jLabel66.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel66.setText("Fv,Rk:");
		relatorioFinal.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 720, 50, -1));

		relatorioFvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFvk.setText("jLabel67");
		relatorioFinal.add(relatorioFvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 720, 70, -1));

		jLabel80.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel80.setText("Rd:");
		relatorioFinal.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 760, 50, -1));

		relatorioRd.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioRd.setText("jLabel81");
		relatorioFinal.add(relatorioRd, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 760, 70, -1));

		jLabel82.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel82.setText("(N)");
		relatorioFinal.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 760, -1, -1));

		jLabel83.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel83.setText("(N)");
		relatorioFinal.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 720, -1, -1));

		jLabel84.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel84.setText("(N)");
		relatorioFinal.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 740, -1, -1));

		jScrollPane1.setViewportView(relatorioFinal);

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

		javax.swing.GroupLayout relatorioLayout = new javax.swing.GroupLayout(relatorio);
		relatorio.setLayout(relatorioLayout);
		relatorioLayout.setHorizontalGroup(relatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(relatorioLayout.createSequentialGroup().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 30,
		                                                                                                                                                                                 Short.MAX_VALUE))
		        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, relatorioLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
		relatorioLayout.setVerticalGroup(relatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(relatorioLayout.createSequentialGroup().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addGroup(relatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(170, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Relatório", relatorio);

		relatorio.setVisible(false);

		getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

		jProgressBarStatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jProgressBarStatus.setString("Clique em \"Iniciar Cálculo\" para começar o dimensionamento.");
		jProgressBarStatus.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
		jProgressBarStatus.setPreferredSize(new java.awt.Dimension(34, 30));
		jProgressBarStatus.setStringPainted(true);
		getContentPane().add(jProgressBarStatus, java.awt.BorderLayout.SOUTH);

		getAccessibleContext().setAccessibleName("ProgramaMarcos");

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
		relatorio.setVisible(false);
	}//GEN-LAST:event_formWindowActivated

	private void coniferasButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ConiferasButtonActionPerformed
		try {
			if(coniferasButton.isSelected()) {
				modeloLigacao.setEspecieMadeira(EspecieMadeira.CONIFERA);
				jProgressBarStatus.setString("Escolha o tipo de madeira para continuar.");
				serradaButton.setEnabled(true);
				recompostaButton.setEnabled(true);
				folhosasButton.setSelected(false);
			} else {
				next.setEnabled(false);
				serradaButton.setEnabled(false);
				recompostaButton.setEnabled(false);
				serradaButton.setSelected(false);
				recompostaButton.setSelected(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}// GEN-LAST:event_ConiferasButtonActionPerformed

	private void folhosasButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_FolhosasButtonActionPerformed
		try {
			if(folhosasButton.isSelected()) {
				modeloLigacao.setEspecieMadeira(EspecieMadeira.FOLHOSA);
				jProgressBarStatus.setString("Escolha o tipo de madeira para continuar.");
				serradaButton.setEnabled(true);
				recompostaButton.setEnabled(true);
				coniferasButton.setSelected(false);
			} else {
				next.setEnabled(false);
				serradaButton.setEnabled(false);
				recompostaButton.setEnabled(false);
				serradaButton.setSelected(false);
				recompostaButton.setSelected(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}// GEN-LAST:event_FolhosasButtonActionPerformed

	private void serradaButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_SerradaButtonActionPerformed
		try {
			if(serradaButton.isSelected()) {
				modeloLigacao.setTipoMadeira(TipoMadeira.SERRADA);
				jProgressBarStatus.setString("Clique em avançar para continuar.");
				recompostaButton.setSelected(false);
				next.setEnabled(true);
			} else {
				next.setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}// GEN-LAST:event_SerradaButtonActionPerformed

	private void recompostaButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_RecompostaButtonActionPerformed
		try {
			if(recompostaButton.isSelected()) {
				modeloLigacao.setTipoMadeira(TipoMadeira.SERRADA);
				jProgressBarStatus.setString("Clique em avançar para continuar.");
				serradaButton.setSelected(false);
				next.setEnabled(true);
			} else {
				next.setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}// GEN-LAST:event_RecompostaButtonActionPerformed

	@SuppressWarnings("incomplete-switch")
	private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
		try {
			switch(jTabbedPane1.getSelectedIndex()){
				case 2:
					valorAngulo.setText("0");
					madeiraFigura.setVisible(true);
					String FiguraMadeira = "";

					if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES) {
						FiguraMadeira = "1Paralelo.png";
					} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO) {
						FiguraMadeira = "2Paralelo.png";
					}

					madeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/" + FiguraMadeira))); // NOI18N

					// Remove todos os elementos a partir do segundo
					while(comboKmod1.getModel().getSize() > 1) {
						comboKmod1.removeItemAt(1);
					}

					for(Kmod1 kmod1 : Kmod1.values()) {
						if(kmod1.getTipoMadeira() == modeloLigacao.getTipoMadeira()) {
							comboKmod1.addItem(kmod1);
						}
					}
					comboKmod1.addItem(Kmod1.OUTRO);

					while(comboKmod2.getModel().getSize() > 1) {
						comboKmod2.removeItemAt(1);
					}

					for(Kmod2 kmod2 : Kmod2.values()) {
						if(kmod2.getTipoMadeira() == modeloLigacao.getTipoMadeira()) {
							comboKmod2.addItem(kmod2);
						}
					}
					comboKmod2.addItem(Kmod2.OUTRO);

					while(comboKmod3.getModel().getSize() > 1) {
						comboKmod3.removeItemAt(1);
					}

					for(Kmod3 kmod3 : Kmod3.values()) {
						if(kmod3.getEspecieMadeira() == modeloLigacao.getEspecieMadeira()) {
							comboKmod3.addItem(kmod3);
						}
					}
					comboKmod3.addItem(Kmod3.OUTRO);

					break;
				case 3:
					double t1 = modeloLigacao.getElementoLigacao1().getEspessura();
					double t2 = modeloLigacao.getElementoLigacao2().getEspessura();

					while(comboTipoParafuso.getModel().getSize() > 1) {
						comboTipoParafuso.removeItemAt(1);
					}

					boolean entrouDiametro = false;

					for(TipoParafuso tipoParafuso : TipoParafuso.values()) {
						boolean passou = false;

						if(tipoParafuso.getDiametro() < (t1 / 5)) {
							entrouDiametro = true;
							double tp;
							switch(modeloLigacao){
								case CORTE_SIMPLES:
									tp = tipoParafuso.getComprimento() - t1;
									if(tp >= 12 * tipoParafuso.getDiametro()) {
										passou = true;
									}
									break;
								case CORTE_DUPLO:
									tp = tipoParafuso.getComprimento() - (t1 + t2);
									if(tp >= 12 * tipoParafuso.getDiametro()) {
										passou = true;
									}
									break;
							}
						}

						if(passou) {
							comboTipoParafuso.addItem(tipoParafuso);
						}
					}

					if(comboTipoParafuso.getItemCount() == 1 && !entrouDiametro) {
						jProgressBarStatus.setString("Nenhum parafuso é compatível com as espessuras inseridas, experimente aumentar as espessuras.");
					}

					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_jTabbedPane1FocusGained

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
		TelaInicial telainicial = new TelaInicial();
		telainicial.setVisible(true);
		this.dispose();
	}//GEN-LAST:event_jButton3ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		TelaParafuso telaPrincipal = new TelaParafuso();
		telaPrincipal.setVisible(true);
		this.dispose();
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
				relatorioFinal.printAll(g2d);
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

	private void FiguraresultadoModoFalhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiguraresultadoModoFalhaActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_FiguraresultadoModoFalhaActionPerformed

	private void FiguraTipoParafusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiguraTipoParafusoActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_FiguraTipoParafusoActionPerformed

	private void ButtonCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCalcularActionPerformed
		try {
			if(buttonCalcular.hasFocus()) {
				figuraArruela.setIcon(new ImageIcon(((ImageIcon)imagemTipoArruela.getIcon()).getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH)));
				jProgressBarStatus.setString("Clique em avançar para continuar.");
				jTabbedPane1.setSelectedComponent(resultado);
				jTabbedPane1.setEnabledAt(4, true);
				next3.setEnabled(true);

				calculoModeloLigacao = CalculoModeloLigacao.getInstance();
				calculoModeloLigacao.calcularModeloLigacaoParafuso(modeloLigacao, incSim1, incSim2, m);

				data.setText(objDataHora.MostraData());
				hora.setText(objDataHora.MostraHora());

				relatorioFc0d1.setText(String.format("%.1f", calculoModeloLigacao.getFe0d1()));
				relatorioFc0d2.setText(String.format("%.1f", calculoModeloLigacao.getFe0d2()));
				relatorioRd1.setText(String.format("%.0f", calculoModeloLigacao.getRd1()));
				relatorioRd2.setText(String.format("%.0f", calculoModeloLigacao.getRd2()));
				relatorioRd3.setText(String.format("%.0f", calculoModeloLigacao.getRd3()));
				relatorioRd4.setText(String.format("%.0f", calculoModeloLigacao.getRd4()));
				relatorioRd5.setText(String.format("%.0f", calculoModeloLigacao.getRd5()));
				relatorioRd6.setText(String.format("%.0f", calculoModeloLigacao.getRd6()));
				relatorioMyd.setText(String.format("%.0f", calculoModeloLigacao.getMyd()));
				relatorioBeta.setText(String.format("%.3f", calculoModeloLigacao.getBeta()));
				relatorioFvk.setText(String.format("%.0f", calculoModeloLigacao.getRdmin()));
				resultadoFvk.setText(String.format("%.0f", calculoModeloLigacao.getRdmin()));
				relatorioRk.setText(String.format("%.0f", calculoModeloLigacao.getRdlig()));
				resultadoRk.setText(String.format("%.0f", calculoModeloLigacao.getRdlig()));
				relatorioFaxrk.setText(String.format("%.0f", calculoModeloLigacao.getValorFaxrk()));
				relatorioRd.setText(String.format("%.0f", calculoModeloLigacao.getRvd()));
				resultadoRd.setText(String.format("%.0f", calculoModeloLigacao.getRvd()));
				teste.setText(calculoModeloLigacao.getTipo());

				if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PARALELO) {
					jScrollPane2.setVisible(true);
					inclinado1.setVisible(false);
					inclinado2.setVisible(false);
					relatorioRd13.setVisible(true);
					relatorioRd14.setVisible(true);
					relatorioRd3.setVisible(true);
					relatorioRd4.setVisible(true);
					jLabel60.setVisible(true);
					jLabel62.setVisible(true);
					relatorioRd15.setVisible(true);
					relatorioRd16.setVisible(true);
					relatorioRd23.setVisible(false);
					relatorioRd24.setVisible(false);
				} else if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
					relatorioRd13.setVisible(true);
					relatorioRd14.setVisible(true);
					relatorioRd3.setVisible(true);
					relatorioRd4.setVisible(true);
					jLabel60.setVisible(true);
					jLabel62.setVisible(true);
					jScrollPane2.setVisible(true);
					relatorioRd15.setVisible(true);
					relatorioRd16.setVisible(true);
					relatorioRd23.setVisible(false);
					relatorioRd24.setVisible(false);
				} else if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
					relatorioRd13.setVisible(true);
					relatorioRd14.setVisible(true);
					relatorioRd3.setVisible(true);
					relatorioRd4.setVisible(true);
					jLabel60.setVisible(true);
					jLabel62.setVisible(true);
					jScrollPane2.setVisible(true);
					relatorioRd15.setVisible(true);
					relatorioRd16.setVisible(true);
					relatorioRd23.setVisible(false);
					relatorioRd24.setVisible(false);
				} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PARALELO) {
					jScrollPane2.setVisible(true);
					relatorioRd13.setVisible(false);
					relatorioRd14.setVisible(false);
					relatorioRd3.setVisible(false);
					relatorioRd4.setVisible(false);
					jLabel60.setVisible(false);
					jLabel62.setVisible(false);
					relatorioRd15.setVisible(false);
					relatorioRd16.setVisible(false);
					relatorioRd23.setVisible(true);
					relatorioRd24.setVisible(true);
					inclinado1.setVisible(false);
					inclinado2.setVisible(false);
				} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
					jScrollPane2.setVisible(true);
					relatorioRd13.setVisible(false);
					relatorioRd14.setVisible(false);
					relatorioRd3.setVisible(false);
					relatorioRd4.setVisible(false);
					jLabel60.setVisible(false);
					jLabel62.setVisible(false);
					relatorioRd15.setVisible(false);
					relatorioRd16.setVisible(false);
					relatorioRd23.setVisible(true);
					relatorioRd24.setVisible(true);
				} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
					jScrollPane2.setVisible(true);
					relatorioRd13.setVisible(false);
					relatorioRd14.setVisible(false);
					relatorioRd3.setVisible(false);
					relatorioRd4.setVisible(false);
					jLabel60.setVisible(false);
					jLabel62.setVisible(false);
					relatorioRd15.setVisible(false);
					relatorioRd16.setVisible(false);
					relatorioRd23.setVisible(true);
					relatorioRd24.setVisible(true);
				}

				//COM ESSAS FUNÇÕES ALTERAM-SE AS IMAGENS DO RELATÓRIO
				labelresultadoModeloFalha.setText(calculoModeloLigacao.getTipo());
				figuraResultadoModoFalha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensFalhas/" + calculoModeloLigacao.getImagemFalha()))); // NOI18N
				figuraParafuso.setIcon(new ImageIcon(((ImageIcon)figuraTipoParafuso.getIcon()).getImage().getScaledInstance(110, 25, Image.SCALE_SMOOTH)));
				figuraParafuso.setIcon(new ImageIcon(((ImageIcon)figuraTipoParafuso.getIcon()).getImage().getScaledInstance(110, 25, Image.SCALE_SMOOTH)));
				modoFalha.setIcon(new ImageIcon(((ImageIcon)figuraResultadoModoFalha.getIcon()).getImage().getScaledInstance(84, 120, Image.SCALE_SMOOTH)));
				rLogo.setIcon(new ImageIcon(((ImageIcon)logoPrograma.getIcon()).getImage().getScaledInstance(125, 80, Image.SCALE_SMOOTH)));

				jProgressBarStatus.setValue(3);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ButtonCalcularActionPerformed

	private void ComboArruelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboArruelasActionPerformed
		try {
			if(((Verificador)comboArruelas.getInputVerifier()).verify(comboArruelas)) {
				valorD1Arruelas.setText("" + modeloLigacao.getConectores().getTipoArruela().getD1());
				valorD2Arruelas.setText("" + modeloLigacao.getConectores().getTipoArruela().getD2());
				relatorioD1.setText("" + modeloLigacao.getConectores().getTipoArruela().getD1());
				relatorioD2.setText("" + modeloLigacao.getConectores().getTipoArruela().getD2());

				String imagemArruela = "";
				if(modeloLigacao.getConectores().getTipoArruela().getImagemArruela() == null) {
					imagemTipoArruela.setVisible(false);
				} else if(modeloLigacao.getConectores().getTipoArruela().getImagemArruela() == 1.0) {
					imagemTipoArruela.setVisible(true);
					relatorioTipoArruela.setText("DIN 436 - " + modeloLigacao.getConectores().getTipoParafuso().getNome());
					imagemArruela = "ImagemArruela436.png";
				} else if(modeloLigacao.getConectores().getTipoArruela().getImagemArruela() == 2.0) {
					imagemTipoArruela.setVisible(true);
					relatorioTipoArruela.setText("DIN 440 R - " + modeloLigacao.getConectores().getTipoParafuso().getNome());
					imagemArruela = "ImagemArruela440R2.png";
				} else if(modeloLigacao.getConectores().getTipoArruela().getImagemArruela() == 3.0) {
					imagemTipoArruela.setVisible(true);
					relatorioTipoArruela.setText("DIN 440 V - " + modeloLigacao.getConectores().getTipoParafuso().getNome());
					imagemArruela = "ImagemArruela440V.png";
				}

				imagemTipoArruela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Imagens/" + imagemArruela))); // NOI18N
			}

			atualizabuttonCalcular();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboArruelasActionPerformed

	private void ComboQuantParafusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboQuantParafusoActionPerformed
		try {
			if(((Verificador)comboQuantParafuso.getInputVerifier()).verify(comboQuantParafuso)) {
				relatorioNParafusos.setText(modeloLigacao.getConectores().getQuantidadeParafuso().getNome());
			}

			atualizabuttonCalcular();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboQuantParafusoActionPerformed

	private void TesteParafusoNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TesteParafusoNaoActionPerformed
		// TODO add your handling code here:
		relatoriofaxrk.setText("Não");
	}//GEN-LAST:event_TesteParafusoNaoActionPerformed

	private void TesteParafusoSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TesteParafusoSimActionPerformed
		JOptionPane
		        .showMessageDialog(this,
		                           "Será considerado no cálculo o efeito não linear de compressão provocado pela arruela devido a rotação\n do pino metálico e de tração do pino metálico, conhecido como efeito de corda (Fax,rk). ");
		// TODO add your handling code here:
		m = true;
		relatoriofaxrk.setText("Sim");
	}//GEN-LAST:event_TesteParafusoSimActionPerformed

	private void ComboAcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboAcoActionPerformed
		try {
			if(((Verificador)comboAco.getInputVerifier()).verify(comboAco)) {
				valorFyk.setText(modeloLigacao.getConectores().getClasseAcoParafuso().getFyk() + "");
				valorFuk.setText(modeloLigacao.getConectores().getClasseAcoParafuso().getFuk() + "");
				relatorioClasseAco.setText(modeloLigacao.getConectores().getClasseAcoParafuso().getNome() + "");
				relatoriofyk.setText(modeloLigacao.getConectores().getClasseAcoParafuso().getFyk() + "");
				relatoriofuk.setText(modeloLigacao.getConectores().getClasseAcoParafuso().getFuk() + "");
			}

			atualizabuttonCalcular();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboAcoActionPerformed

	private void ComboTipoParafusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTipoParafusoActionPerformed
		try {
			if(((Verificador)comboTipoParafuso.getInputVerifier()).verify(comboTipoParafuso)) {
				valorDiametro.setText(modeloLigacao.getConectores().getTipoParafuso().getDiametro().toString());
				valorArea.setText(modeloLigacao.getConectores().getTipoParafuso().getComprimento().toString());
			}

			atualizanextElementosLigacao();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboTipoParafusoActionPerformed

	private void ComboTipoParafusoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ComboTipoParafusoFocusLost
		if(((Verificador)comboTipoParafuso.getInputVerifier()).verify(comboTipoParafuso)) {
			while(comboArruelas.getModel().getSize() > 1) {
				comboArruelas.removeItemAt(1);
			}

			for(TipoArruela tipoArruela : TipoArruela.values()) {
				if(modeloLigacao.getConectores().getTipoParafuso().equals(tipoArruela.getTipoParafuso())) {
					comboArruelas.addItem(tipoArruela);
				}
			}
		}
	}//GEN-LAST:event_ComboTipoParafusoFocusLost

	private void ComboKmod2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod2ActionPerformed
		try {
			if(((Verificador)comboKmod2.getInputVerifier()).verify(comboKmod2)) {
				if(modeloLigacao.getKmod2() == Kmod2.OUTRO) {

					double kmod2 = -1;

					do {
						String value = (String)JOptionPane.showInputDialog(this, "Entre com um valor entre 0 e 1.", "Kmod 2", JOptionPane.INFORMATION_MESSAGE, null, null, "Digite o valor");
						try {
							kmod2 = Double.parseDouble(value.replace(",", "."));
						} catch (NumberFormatException e) {
							e.printStackTrace();

						}
						jProgressBarStatus.setString("Entre com um valor válido, entre 0 e 1.");
					} while(kmod2 > 1 || kmod2 <= 0);
					modeloLigacao.getKmod2().setValor(kmod2);
					jProgressBarStatus.setString("");
				}
				textkmod2.setText(modeloLigacao.getKmod2().getValor() + "");
				relatoriokmod2.setText(modeloLigacao.getKmod2().getValor() + "");
			}

			atualizanextElementosLigacao();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboKmod2ActionPerformed

	private void ComboKmod3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod3ActionPerformed
		try {
			if(((Verificador)comboKmod3.getInputVerifier()).verify(comboKmod3)) {
				if(modeloLigacao.getKmod3() == Kmod3.OUTRO) {

					double kmod3 = -1;

					do {
						String value = (String)JOptionPane.showInputDialog(this, "Entre com um valor entre 0 e 1.", "Kmod 3", JOptionPane.INFORMATION_MESSAGE, null, null, "Digite o valor");
						try {
							kmod3 = Double.parseDouble(value.replace(",", "."));
						} catch (NumberFormatException e) {
							e.printStackTrace();

						}
						jProgressBarStatus.setString("Entre com um valor válido, entre 0 e 1.");
					} while(kmod3 > 1 || kmod3 <= 0);
					modeloLigacao.getKmod3().setValor(kmod3);
					jProgressBarStatus.setString("");
				}
				textkmod3.setText(modeloLigacao.getKmod3().getValor() + "");
				relatoriokmod3.setText(modeloLigacao.getKmod3().getValor() + "");
			}

			atualizanextElementosLigacao();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboKmod3ActionPerformed

	private void ComboKmod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod1ActionPerformed
		try {
			if(((Verificador)comboKmod1.getInputVerifier()).verify(comboKmod1)) {
				if(modeloLigacao.getKmod1() == Kmod1.OUTRO) {

					double kmod1 = -1;

					do {
						String value = (String)JOptionPane.showInputDialog(this, "Entre com um valor entre 0 e 1.", "Kmod 1", JOptionPane.INFORMATION_MESSAGE, null, null, "Digite o valor");
						try {
							kmod1 = Double.parseDouble(value.replace(",", "."));
						} catch (RuntimeException e) {
							e.printStackTrace();

						}
						jProgressBarStatus.setString("Entre com um valor válido, entre 0 e 1.");
					} while(kmod1 > 1 || kmod1 <= 0);
					modeloLigacao.getKmod1().setValor(kmod1);
					jProgressBarStatus.setString("");
				}
				textkmod1.setText(modeloLigacao.getKmod1().getValor() + "");
				relatorioKmod1.setText(modeloLigacao.getKmod1().getValor() + "");
			}

			atualizanextElementosLigacao();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboKmod1ActionPerformed

	private void InclinacaoSim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InclinacaoSim2ActionPerformed
		try {
			incSim1 = true;
			incSim2 = false;

			madeiraFigura.setVisible(true);
			String TrocaFigura = "";

			if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
				calculoForca01.setVisible(false);
				calculoForca02.setVisible(true);
				calculoForca901.setVisible(false);
				calculoForca902.setVisible(false);
				calculoForcaAlfa1.setVisible(true);
				calculoForcaAlfa2.setVisible(false);

				TrocaFigura = "1InclinadoElem1.png"; // NOI18N
			} else if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
				calculoForca01.setVisible(false);
				calculoForca02.setVisible(true);
				calculoForca901.setVisible(true);
				calculoForca902.setVisible(false);
				calculoForcaAlfa1.setVisible(false);
				calculoForcaAlfa2.setVisible(false);

				TrocaFigura = "1PerpendicularElem1.png"; // NOI18N
			} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
				calculoForca01.setVisible(false);
				calculoForca02.setVisible(true);
				calculoForca901.setVisible(false);
				calculoForca902.setVisible(false);
				calculoForcaAlfa1.setVisible(true);
				calculoForcaAlfa2.setVisible(false);

				TrocaFigura = "2InclinadoElem1.png"; // NOI18N
			} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
				calculoForca01.setVisible(false);
				calculoForca02.setVisible(true);
				calculoForca901.setVisible(true);
				calculoForca902.setVisible(false);
				calculoForcaAlfa1.setVisible(false);
				calculoForcaAlfa2.setVisible(false);

				TrocaFigura = "2PerpendicularElem1.png"; // NOI18N
			}

			madeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/" + TrocaFigura))); // NOI18N
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_InclinacaoSim2ActionPerformed

	private void Espessura2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Espessura2KeyTyped
		// TODO add your handling code here:
		if((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') || evt.getKeyChar() == ',') {
			return;
		}
		evt.consume();
	}//GEN-LAST:event_Espessura2KeyTyped

	private void espessura2FocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_Espessura2FocusLost
		try {
			if(espessura1.getText() == null || espessura1.getText().equals("") || espessura2.getText() == null || espessura2.getText().equals("")) {
				comboKmod1.setEnabled(false);
				comboKmod2.setEnabled(false);
				comboKmod3.setEnabled(false);
			} else {
				double t1 = Integer.parseInt(espessura1.getText());
				double t2 = Integer.parseInt(espessura2.getText());

				if(t2 < t1) {
					String msg = "";

					msg += ".";

					if(!msg.isEmpty()) {
						JOptionPane.showMessageDialog(this, "- A espessura do Elemento 1 deve ser menor ou igual a espessura do Elemento 2\n" + msg);
					}
					t1 = 0;
					t2 = 0;
					comboKmod1.setEnabled(false);
					comboKmod2.setEnabled(false);
					comboKmod3.setEnabled(false);
				} else {
					comboKmod1.setEnabled(true);
					comboKmod2.setEnabled(true);
					comboKmod3.setEnabled(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}// GEN-LAST:event_Espessura2FocusLost

	private void LimparExpessura2(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LimparExpessura2
		// TODO add your handling code here:
		if(espessura2.getText().length() > 4) {
			espessura2.setText("");
		}
	}//GEN-LAST:event_LimparExpessura2

	private void comboElem2ClasseMadeiraFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_comboElem2ClasseMadeiraActionPerformed
		try {
			if(((Verificador)comboElem2.getInputVerifier()).isVerified()) {
				valorFc2.setText(modeloLigacao.getElementoLigacao2().getClasseMadeira().getfc0k() + "");
				valorDensidade2.setText(modeloLigacao.getElementoLigacao2().getClasseMadeira().getDensidade() + "");
				valorFvok2.setText(modeloLigacao.getElementoLigacao2().getClasseMadeira().getfv0k() + "");
				valorEc0m2.setText(modeloLigacao.getElementoLigacao2().getClasseMadeira().getec0m() + "");
				relatorioClasseElem2.setText(modeloLigacao.getElementoLigacao2().getClasseMadeira().name());
				relatoriofcok2.setText(modeloLigacao.getElementoLigacao2().getClasseMadeira().getfc0k() + "");
				relatorioDensidade2.setText(modeloLigacao.getElementoLigacao2().getClasseMadeira().getDensidade() + "");
				relatoriofv0k2.setText(modeloLigacao.getElementoLigacao2().getClasseMadeira().getfv0k() + "");
				relatorioEc0m2.setText(modeloLigacao.getElementoLigacao2().getClasseMadeira().getec0m() + "");
			}

			atualizanextElementosLigacao();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}// GEN-LAST:event_comboElem2ClasseMadeiraActionPerformed

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
		if(espessura1.getText().length() > 4) {
			espessura1.setText("");
		}
	}//GEN-LAST:event_Espessura1

	private void InclinacaoSim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InclinacaoSim1ActionPerformed
		try {
			incSim1 = true;
			incSim2 = false;

			madeiraFigura.setVisible(true);
			String TrocaFigura = "";

			if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
				calculoForca01.setVisible(true);
				calculoForca02.setVisible(false);
				calculoForca901.setVisible(false);
				calculoForca902.setVisible(false);
				calculoForcaAlfa1.setVisible(false);
				calculoForcaAlfa2.setVisible(true);

				TrocaFigura = "1InclinadoElem2.png"; // NOI18N
			} else if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
				calculoForca01.setVisible(true);
				calculoForca02.setVisible(false);
				calculoForca901.setVisible(false);
				calculoForca902.setVisible(true);
				calculoForcaAlfa1.setVisible(false);
				calculoForcaAlfa2.setVisible(false);

				TrocaFigura = "1PerpendicularElem2.png"; // NOI18N
			} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
				calculoForca01.setVisible(true);
				calculoForca02.setVisible(false);
				calculoForca901.setVisible(false);
				calculoForca902.setVisible(false);
				calculoForcaAlfa1.setVisible(false);
				calculoForcaAlfa2.setVisible(true);

				TrocaFigura = "2InclinadoElem2.png"; // NOI18N
			} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
				calculoForca01.setVisible(true);
				calculoForca02.setVisible(false);
				calculoForca901.setVisible(false);
				calculoForca902.setVisible(true);
				calculoForcaAlfa1.setVisible(false);
				calculoForcaAlfa2.setVisible(false);

				TrocaFigura = "2PerpendicularElem2.png"; // NOI18N
			}

			madeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/" + TrocaFigura))); // NOI18N
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_InclinacaoSim1ActionPerformed

	private void valorAnguloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorAnguloKeyTyped
		// TODO add your handling code here:
		if((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') || evt.getKeyChar() == ',') {
			return;
		}
		evt.consume();
	}//GEN-LAST:event_valorAnguloKeyTyped

	private void valorAnguloPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_valorAnguloPropertyChange
		// TODO add your handling code here:
	}//GEN-LAST:event_valorAnguloPropertyChange

	private void valorAnguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorAnguloActionPerformed
		// TODO add your handling code here:

	}//GEN-LAST:event_valorAnguloActionPerformed

	private void valorAngulo(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_valorAngulo
		// TODO add your handling code here:
		inclinacaoSim1.setEnabled(false);
		inclinacaoSim2.setEnabled(false);

		if(valorAngulo.getText().length() > 4) {
			valorAngulo.setText("0");

		}
	}//GEN-LAST:event_valorAngulo

	private void comboElem1ClasseMadeiraFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_comboElem1ClasseMadeiraFocusLost
		try {
			if(((Verificador)comboElem1.getInputVerifier()).isVerified()) {
				valorFc01.setText(modeloLigacao.getElementoLigacao1().getClasseMadeira().getfc0k() + "");
				valorDensidade1.setText(modeloLigacao.getElementoLigacao1().getClasseMadeira().getDensidade() + "");
				valorFvok1.setText(modeloLigacao.getElementoLigacao1().getClasseMadeira().getfv0k() + "");
				valorEc0m1.setText(modeloLigacao.getElementoLigacao1().getClasseMadeira().getec0m() + "");
				relatorioClasseElem1.setText(modeloLigacao.getElementoLigacao1().getClasseMadeira().name());
				relatorioFcok1.setText(modeloLigacao.getElementoLigacao1().getClasseMadeira().getfc0k() + "");
				relatorioDensidade1.setText(modeloLigacao.getElementoLigacao1().getClasseMadeira().getDensidade() + "");
				relatoriofv0k1.setText(modeloLigacao.getElementoLigacao1().getClasseMadeira().getfv0k() + "");
				relatorioEc0m1.setText(modeloLigacao.getElementoLigacao1().getClasseMadeira().getec0m() + "");
			}

			atualizanextElementosLigacao();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}// GEN-LAST:event_comboElem1ClasseMadeiraFocusLost

	private void btn2SecaoCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2SecaoCorteActionPerformed
		try {
			modeloLigacao = ModeloLigacao.CORTE_DUPLO;

			madeiraFigura.setVisible(true);
			inclinacaoSim1.setEnabled(true);
			inclinacaoSim2.setEnabled(true);
			coniferasButton.setEnabled(true);
			folhosasButton.setEnabled(true);
			jProgressBarStatus.setString("Escolha a espécie da madeira para continuar.");

			relatorioSecaoCorte.setText("Ligação com corte duplo");
			madeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/2Paralelo.png"))); // NOI18N
			figuraSecoes.setIcon(new ImageIcon(((ImageIcon)btn2SecaoCorte.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_btn2SecaoCorteActionPerformed

	private void btn1SecaoCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1SecaoCorteActionPerformed
		try {
			modeloLigacao = ModeloLigacao.CORTE_SIMPLES;

			madeiraFigura.setVisible(true);
			inclinacaoSim1.setEnabled(true);
			inclinacaoSim2.setEnabled(true);
			coniferasButton.setEnabled(true);
			folhosasButton.setEnabled(true);
			jProgressBarStatus.setString("Escolha a espécie da madeira para continuar.");

			relatorioSecaoCorte.setText("Ligação com corte simples");
			madeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/1Paralelo.png"))); // NOI18N
			figuraSecoes.setIcon(new ImageIcon(((ImageIcon)btn1SecaoCorte.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_btn1SecaoCorteActionPerformed

	@SuppressWarnings("incomplete-switch")
	private void valorAnguloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_valorAnguloFocusLost
		try {
			modeloLigacao.getAngulo().setValorGrau(Double.parseDouble(valorAngulo.getText().replace(",", ".")));

			madeiraFigura.setVisible(true);
			String figuraMadeira = "";

			switch(modeloLigacao){
				case CORTE_SIMPLES:
					switch(modeloLigacao.getAngulo().getTipoAngulo()){
						case PARALELO:
							figuraMadeira = "1Paralelo.png";
							inclinacaoSim1.setEnabled(false);
							inclinacaoSim2.setEnabled(false);
							relatorioAngulacao.setText("Direção Paralela");
							calculoForca01.setVisible(true);
							calculoForca901.setVisible(false);
							calculoForcaAlfa1.setVisible(false);
							break;
						case PERPENDICULAR:
							figuraMadeira = "1PerpendicularElem1.png";
							inclinacaoSim1.setEnabled(true);
							inclinacaoSim2.setEnabled(true);
							relatorioAngulacao.setText("Direção Perpendicular");
							calculoForca01.setVisible(false);
							calculoForca901.setVisible(true);
							calculoForcaAlfa1.setVisible(false);
							break;
						case INCLINADO:
							figuraMadeira = "1InclinadoElem1.png";
							inclinacaoSim1.setEnabled(true);
							inclinacaoSim2.setEnabled(true);
							relatorioAngulacao.setText("Direção Inclinada");
							calculoForca01.setVisible(false);
							calculoForca901.setVisible(false);
							calculoForcaAlfa1.setVisible(true);
							break;
					}
					break;
				case CORTE_DUPLO:
					switch(modeloLigacao.getAngulo().getTipoAngulo()){
						case PARALELO:
							figuraMadeira = "2Paralelo.png";
							inclinacaoSim1.setEnabled(false);
							inclinacaoSim2.setEnabled(false);
							relatorioAngulacao.setText("Direção Paralela");
							calculoForca01.setVisible(true);
							calculoForca901.setVisible(false);
							calculoForcaAlfa1.setVisible(false);
							break;
						case PERPENDICULAR:
							figuraMadeira = "2PerpendicularElem1.png";
							inclinacaoSim1.setEnabled(true);
							inclinacaoSim2.setEnabled(true);
							relatorioAngulacao.setText("Direção Perpendicular");
							calculoForca01.setVisible(false);
							calculoForca901.setVisible(true);
							calculoForcaAlfa1.setVisible(false);
							break;
						case INCLINADO:
							figuraMadeira = "2InclinadoElem1.png";
							inclinacaoSim1.setEnabled(true);
							inclinacaoSim2.setEnabled(true);
							relatorioAngulacao.setText("Direção Inclinada");
							calculoForca01.setVisible(false);
							calculoForca901.setVisible(false);
							calculoForcaAlfa1.setVisible(true);
							break;
					}
					break;
			}

			madeiraFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/" + figuraMadeira))); // NOI18N
			madeiraFigura.doClick();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_valorAnguloFocusLost

	private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
		try {
			jTabbedPane1.setEnabledAt(2, true);
			jTabbedPane1.setSelectedComponent(elementosMadeira);
			jProgressBarStatus.setString("Preencha todos os campos sem exceção.");
			jProgressBarStatus.setValue(1);
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_nextActionPerformed

	private void next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next2ActionPerformed
		try {
			espessura1.getInputVerifier().shouldYieldFocus(espessura1);
			espessura2.getInputVerifier().shouldYieldFocus(espessura2);
			valorAngulo.getInputVerifier().shouldYieldFocus(valorAngulo);
			comboElem1.getInputVerifier().shouldYieldFocus(comboElem1);
			comboElem2.getInputVerifier().shouldYieldFocus(comboElem2);
			comboKmod1.getInputVerifier().shouldYieldFocus(comboKmod1);
			comboKmod2.getInputVerifier().shouldYieldFocus(comboKmod2);
			comboKmod3.getInputVerifier().shouldYieldFocus(comboKmod3);

			modeloLigacao.setConectores(new Conectores());

			if(next2.hasFocus()) {
				jTabbedPane1.setEnabledAt(3, true);
				jTabbedPane1.setSelectedComponent(elementosMetalicos);
				jProgressBarStatus.setString("Preencha todos os campos sem exceção.");
				jProgressBarStatus.setValue(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_next2ActionPerformed

	private void next3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next3ActionPerformed
		try {
			jTabbedPane1.setEnabledAt(5, true);
			jTabbedPane1.setSelectedComponent(relatorio);
			jProgressBarStatus.setString("Verifique o relatório do dimensionamento. Fique atento para as opções fornecidas nos botões.");
			jProgressBarStatus.setValue(4);
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_next3ActionPerformed

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
		jTabbedPane1.setEnabledAt(1, true);
		jTabbedPane1.setSelectedComponent(secoesCorte);
		jProgressBarStatus.setString("Escolha a quantidade de seções de corte no parafuso.");
	}//GEN-LAST:event_jButton4ActionPerformed

	private void VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarActionPerformed
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

	public ModeloLigacao getModeloLigacao() {
		return this.modeloLigacao;
	}

	private void atualizanextElementosLigacao() {
		next2.setEnabled(((Verificador)espessura1.getInputVerifier()).isVerified() && ((Verificador)espessura2.getInputVerifier()).isVerified()
		                 && ((Verificador)valorAngulo.getInputVerifier()).isVerified() && ((Verificador)comboElem1.getInputVerifier()).isVerified()
		                 && ((Verificador)comboElem2.getInputVerifier()).isVerified() && ((Verificador)comboKmod1.getInputVerifier()).isVerified()
		                 && ((Verificador)comboKmod2.getInputVerifier()).isVerified() && ((Verificador)comboKmod3.getInputVerifier()).isVerified());
	}

	private void atualizabuttonCalcular() {
		buttonCalcular.setEnabled(((Verificador)comboTipoParafuso.getInputVerifier()).isVerified() && ((Verificador)comboQuantParafuso.getInputVerifier()).isVerified()
		                          && ((Verificador)comboArruelas.getInputVerifier()).isVerified() && ((Verificador)comboAco.getInputVerifier()).isVerified()

		);
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.ButtonGroup groupAngulacao;
	private javax.swing.ButtonGroup groupInclinacaoSim;
	private javax.swing.ButtonGroup groupSecoesCorte;
	private javax.swing.ButtonGroup groupTesteParafuso;
	private javax.swing.JButton buttonCalcular;
	private javax.swing.JButton figuraResultadoModoFalha;
	private javax.swing.JButton figuraTipoParafuso;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton madeiraFigura;
	private javax.swing.JButton next;
	private javax.swing.JButton next2;
	private javax.swing.JButton next3;
	private javax.swing.JButton voltar;
	private javax.swing.JComboBox<ClasseAcoParafuso> comboAco;
	private javax.swing.JComboBox<TipoArruela> comboArruelas;
	private javax.swing.JComboBox<ClasseMadeira> comboElem1;
	private javax.swing.JComboBox<ClasseMadeira> comboElem2;
	private javax.swing.JComboBox<Kmod1> comboKmod1;
	private javax.swing.JComboBox<Kmod2> comboKmod2;
	private javax.swing.JComboBox<Kmod3> comboKmod3;
	private javax.swing.JComboBox<QuantidadeParafuso> comboQuantParafuso;
	private javax.swing.JComboBox<TipoParafuso> comboTipoParafuso;
	private javax.swing.JLabel areaParafuso;
	private javax.swing.JLabel arruelas;
	private javax.swing.JLabel calculoForca01;
	private javax.swing.JLabel calculoForca02;
	private javax.swing.JLabel calculoForca901;
	private javax.swing.JLabel calculoForca902;
	private javax.swing.JLabel calculoForcaAlfa1;
	private javax.swing.JLabel calculoForcaAlfa2;
	private javax.swing.JLabel classeAco;
	private javax.swing.JLabel d1Arruelas;
	private javax.swing.JLabel d2Arruelas;
	private javax.swing.JLabel data;
	private javax.swing.JLabel densidade1;
	private javax.swing.JLabel densidade2;
	private javax.swing.JLabel diametro;
	private javax.swing.JLabel ec0m1;
	private javax.swing.JLabel ec0m2;
	private javax.swing.JLabel fcok1;
	private javax.swing.JLabel fcok2;
	private javax.swing.JLabel figuraArruela;
	private javax.swing.JLabel figuraParafuso;
	private javax.swing.JLabel figuraSecoes;
	private javax.swing.JLabel fuk;
	private javax.swing.JLabel fv0k1;
	private javax.swing.JLabel fv0k2;
	private javax.swing.JLabel fyk;
	private javax.swing.JLabel hora;
	private javax.swing.JLabel imagemTipoArruela;
	private javax.swing.JLabel jLabel_classe_madeira_1;
	private javax.swing.JLabel jLabel_classe_madeira_2;
	private javax.swing.JLabel jLabel_elemento1;
	private javax.swing.JLabel jLabel_elemento2;
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
	private javax.swing.JLabel jLabelUnEspessura;
	private javax.swing.JLabel jLabelUnEspessura1;
	private javax.swing.JLabel labelModeloFalha;
	private javax.swing.JLabel labelResistenciaLigacao;
	private javax.swing.JLabel labelresultadoModeloFalha;
	private javax.swing.JLabel logoPrograma;
	private javax.swing.JLabel modoFalha;
	private javax.swing.JLabel numeroParafusos;
	private javax.swing.JLabel possuiInclinacao1;
	private javax.swing.JLabel possuiInclinacao11;
	private javax.swing.JLabel possuiInclinacao3;
	private javax.swing.JLabel relatorioAngulacao;
	private javax.swing.JLabel relatorioAngulo;
	private javax.swing.JLabel relatorioBeta;
	private javax.swing.JLabel relatorioClasseAco;
	private javax.swing.JLabel relatorioClasseElem1;
	private javax.swing.JLabel relatorioClasseElem2;
	private javax.swing.JLabel relatorioD1;
	private javax.swing.JLabel relatorioD2;
	private javax.swing.JLabel relatorioDensidade1;
	private javax.swing.JLabel relatorioDensidade2;
	private javax.swing.JLabel relatorioDiametro;
	private javax.swing.JLabel relatorioEc0m1;
	private javax.swing.JLabel relatorioEc0m2;
	private javax.swing.JLabel relatorioEspessura1;
	private javax.swing.JLabel relatorioEspessura2;
	private javax.swing.JLabel relatorioFaxrk;
	private javax.swing.JLabel relatoriofaxrk;
	private javax.swing.JLabel relatorioFc0d1;
	private javax.swing.JLabel relatorioFc0d2;
	private javax.swing.JLabel relatorioFcok1;
	private javax.swing.JLabel relatoriofcok2;
	private javax.swing.JLabel relatoriofcok4;
	private javax.swing.JLabel relatorioFibras;
	private javax.swing.JLabel relatoriofuk;
	private javax.swing.JLabel relatoriofv0k1;
	private javax.swing.JLabel relatoriofv0k2;
	private javax.swing.JLabel relatorioFvk;
	private javax.swing.JLabel relatoriofyk;
	private javax.swing.JLabel relatorioKmod1;
	private javax.swing.JLabel relatoriokmod2;
	private javax.swing.JLabel relatoriokmod3;
	private javax.swing.JLabel relatorioMyd;
	private javax.swing.JLabel relatorioNParafusos;
	private javax.swing.JLabel relatorioRd;
	private javax.swing.JLabel relatorioRd1;
	private javax.swing.JLabel relatorioRd11;
	private javax.swing.JLabel relatorioRd12;
	private javax.swing.JLabel relatorioRd13;
	private javax.swing.JLabel relatorioRd14;
	private javax.swing.JLabel relatorioRd15;
	private javax.swing.JLabel relatorioRd16;
	private javax.swing.JLabel relatorioRd2;
	private javax.swing.JLabel relatorioRd23;
	private javax.swing.JLabel relatorioRd24;
	private javax.swing.JLabel relatorioRd3;
	private javax.swing.JLabel relatorioRd4;
	private javax.swing.JLabel relatorioRd5;
	private javax.swing.JLabel relatorioRd6;
	private javax.swing.JLabel relatorioRk;
	private javax.swing.JLabel relatorioSecaoCorte;
	private javax.swing.JLabel relatorioTipoArruela;
	private javax.swing.JLabel relatorioTipoParafuso;
	private javax.swing.JLabel resultadoFvk;
	private javax.swing.JLabel resultadoRd;
	private javax.swing.JLabel resultadoRk;
	private javax.swing.JLabel rLogo;
	private javax.swing.JLabel tamanhoParafuso;
	private javax.swing.JLabel testeParafuso;
	private javax.swing.JLabel textkmod1;
	private javax.swing.JLabel textkmod2;
	private javax.swing.JLabel textkmod3;
	private javax.swing.JLabel tipoArruela;
	private javax.swing.JLabel unD1Arruelas;
	private javax.swing.JLabel unD2Arruelas;
	private javax.swing.JLabel undensidade1;
	private javax.swing.JLabel undensidade2;
	private javax.swing.JLabel unEc0m1;
	private javax.swing.JLabel unEc0m2;
	private javax.swing.JLabel unFcok1;
	private javax.swing.JLabel unFcok2;
	private javax.swing.JLabel unFuk;
	private javax.swing.JLabel unFv0k1;
	private javax.swing.JLabel unFv0k2;
	private javax.swing.JLabel unFyk;
	private javax.swing.JLabel valorArea;
	private javax.swing.JLabel valorD1Arruelas;
	private javax.swing.JLabel valorD2Arruelas;
	private javax.swing.JLabel valorDensidade1;
	private javax.swing.JLabel valorDensidade2;
	private javax.swing.JLabel valorDiametro;
	private javax.swing.JLabel valorEc0m1;
	private javax.swing.JLabel valorEc0m2;
	private javax.swing.JLabel valorFc01;
	private javax.swing.JLabel valorFc2;
	private javax.swing.JLabel valorFuk;
	private javax.swing.JLabel valorFvok1;
	private javax.swing.JLabel valorFvok2;
	private javax.swing.JLabel valorFyk;
	private javax.swing.JPanel elementosMadeira;
	private javax.swing.JPanel elementosMetalicos;
	private javax.swing.JPanel inicio;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel12;
	private javax.swing.JPanel jPanel13;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel painelEspecieMadeira;
	private javax.swing.JPanel painelTipoMadeira;
	private javax.swing.JPanel relatorio;
	private javax.swing.JPanel relatorioCoeficientes;
	private javax.swing.JPanel relatorioElem1;
	private javax.swing.JPanel relatorioElem2;
	private javax.swing.JPanel relatorioFinal;
	private javax.swing.JPanel relatorioParafuso;
	private javax.swing.JPanel resultado;
	private javax.swing.JPanel secoesCorte;
	private javax.swing.JProgressBar jProgressBarStatus;
	private javax.swing.JRadioButton inclinacaoSim1;
	private javax.swing.JRadioButton inclinacaoSim2;
	private javax.swing.JRadioButton testeParafusoNao;
	private javax.swing.JRadioButton testeParafusoSim;
	private javax.swing.JScrollPane inclinado1;
	private javax.swing.JScrollPane inclinado2;
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
	private javax.swing.JTextArea consideracao1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	private javax.swing.JTextArea teste;
	private javax.swing.JTextField espessura1;
	private javax.swing.JTextField espessura2;
	private javax.swing.JTextField valorAngulo;
	private javax.swing.JToggleButton btn1SecaoCorte;
	private javax.swing.JToggleButton btn2SecaoCorte;
	private javax.swing.JToggleButton coniferasButton;
	private javax.swing.JToggleButton folhosasButton;
	private javax.swing.JToggleButton recompostaButton;
	private javax.swing.JToggleButton serradaButton;
	// End of variables declaration//GEN-END:variables

}
