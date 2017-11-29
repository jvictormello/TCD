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

import br.com.tcd.enumeration.ClasseAco;
import br.com.tcd.enumeration.ClasseAcoParafuso;
import br.com.tcd.enumeration.ClasseMadeira;
import br.com.tcd.enumeration.EspecieMadeira;
import br.com.tcd.enumeration.EspessuraChapaAco;
import br.com.tcd.enumeration.Kmod1;
import br.com.tcd.enumeration.Kmod2;
import br.com.tcd.enumeration.Kmod3;
import br.com.tcd.enumeration.ModeloLigacao;
import br.com.tcd.enumeration.QuantidadeParafuso;
import br.com.tcd.enumeration.TipoArruela;
import br.com.tcd.enumeration.TipoArruelaAco;
import br.com.tcd.enumeration.TipoMadeira;
import br.com.tcd.enumeration.TipoParafuso;
import br.com.tcd.interfaces.ModeloLigacaoProvider;
import br.com.tcd.modelo.CalculoModeloLigacao;
import br.com.tcd.modelo.Conectores;
import br.com.tcd.util.ClsDataHora;
import br.com.tcd.verifier.Verificador;
import br.com.tcd.verifier.VerificadorComboAcoParafuso;
import br.com.tcd.verifier.VerificadorComboArruelaAco;
import br.com.tcd.verifier.VerificadorComboArruelas;
import br.com.tcd.verifier.VerificadorComboClasseElem1;
import br.com.tcd.verifier.VerificadorComboClasseElemAco;
import br.com.tcd.verifier.VerificadorComboEspessuraAco;
import br.com.tcd.verifier.VerificadorComboQuantParafuso;
import br.com.tcd.verifier.VerificadorComboTipoParafuso;
import br.com.tcd.verifier.VerificadorEspessura1;
import br.com.tcd.verifier.VerificadorKmod1;
import br.com.tcd.verifier.VerificadorKmod2;
import br.com.tcd.verifier.VerificadorKmod3;
import br.com.tcd.verifier.VerificadorValorAngulo;

/**
 *
 * @author MarcosVinicius
 */
public class TelaAco extends javax.swing.JFrame implements ModeloLigacaoProvider {

	private static final long serialVersionUID = 1679732631032489300L;

	private ModeloLigacao modeloLigacao;
	private CalculoModeloLigacao calculoModeloLigacao;
	private Boolean m;
	private Boolean incSim1;
	private Boolean incSim2;
	private ClsDataHora objDataHora = new ClsDataHora();

	public TelaAco() {
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

		imagemTipoArruela.setVisible(false);
		calculoForca01.setVisible(true);
		calculoForca901.setVisible(false);
		calculoForcaAlfa1.setVisible(false);

		trocaFigura.setVisible(true);

		relatorioRd13.setVisible(true);
		relatorioFvk2.setVisible(true);
		jLabel62.setVisible(true);

		jTabbedPane1.setEnabledAt(1, false);
		jTabbedPane1.setEnabledAt(2, false);
		jTabbedPane1.setEnabledAt(3, false);
		jTabbedPane1.setEnabledAt(4, false);
		jTabbedPane1.setEnabledAt(5, false);
		next.setEnabled(false);
		next2.setEnabled(false);
		next3.setEnabled(false);

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
		comboChapaAco.setInputVerifier(new VerificadorComboEspessuraAco(this.jProgressBarStatus, this));
		valorAngulo.setInputVerifier(new VerificadorValorAngulo(this.jProgressBarStatus, this));
		comboElem1.setInputVerifier(new VerificadorComboClasseElem1(this.jProgressBarStatus, this));
		comboTipoChapaAco.setInputVerifier(new VerificadorComboClasseElemAco(this.jProgressBarStatus, this));
		comboKmod1.setInputVerifier(new VerificadorKmod1(this.jProgressBarStatus, this));
		comboKmod2.setInputVerifier(new VerificadorKmod2(this.jProgressBarStatus, this));
		comboKmod3.setInputVerifier(new VerificadorKmod3(this.jProgressBarStatus, this));
		comboQuantParafuso.setInputVerifier(new VerificadorComboQuantParafuso(this.jProgressBarStatus, this));
		comboTipoParafuso.setInputVerifier(new VerificadorComboTipoParafuso(this.jProgressBarStatus, this));
		comboAco.setInputVerifier(new VerificadorComboAcoParafuso(this.jProgressBarStatus, this));
		comboArruelasMadeira.setInputVerifier(new VerificadorComboArruelas(this.jProgressBarStatus, this));
		comboArruelasAco.setInputVerifier(new VerificadorComboArruelaAco(this.jProgressBarStatus, this));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		groupSecoesCorte = new javax.swing.ButtonGroup();
		groupTesteParafuso = new javax.swing.ButtonGroup();
		jProgressBarStatus = new javax.swing.JProgressBar(0, 4);
		jProgressBarStatus.setValue(0);
		jTabbedPane1 = new javax.swing.JTabbedPane();
		inicio = new javax.swing.JPanel();
		logoPrograma = new javax.swing.JLabel();
		jLabel97 = new javax.swing.JLabel();
		iniciarCalculo = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jLabel15 = new javax.swing.JLabel();
		secoesCorte = new javax.swing.JPanel();
		btnCorteSimples = new javax.swing.JToggleButton();
		btnCorteDuploAMA = new javax.swing.JToggleButton();
		btnCorteDuploMAM = new javax.swing.JToggleButton();
		next = new javax.swing.JButton();
		elementosMadeira = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		comboElem1 = new javax.swing.JComboBox<ClasseMadeira>();
		jLabel_classe_madeira_1 = new javax.swing.JLabel();
		jLabel_elemento1 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		espessura1 = new javax.swing.JTextField();
		jLabelUnEspessura = new javax.swing.JLabel();
		fcok1 = new javax.swing.JLabel();
		unFcok1 = new javax.swing.JLabel();
		valorFc01 = new javax.swing.JLabel();
		densidade1 = new javax.swing.JLabel();
		valorDensidade1 = new javax.swing.JLabel();
		unDensidade1 = new javax.swing.JLabel();
		valorFvok1 = new javax.swing.JLabel();
		unFv0k1 = new javax.swing.JLabel();
		fv0k1 = new javax.swing.JLabel();
		ec0m1 = new javax.swing.JLabel();
		unEc0m1 = new javax.swing.JLabel();
		valorEc0m1 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		valorAngulo = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		jSeparator5 = new javax.swing.JSeparator();
		jPanel9 = new javax.swing.JPanel();
		jLabel39 = new javax.swing.JLabel();
		jLabel40 = new javax.swing.JLabel();
		jLabel41 = new javax.swing.JLabel();
		jLabel42 = new javax.swing.JLabel();
		comboKmod1 = new javax.swing.JComboBox<Kmod1>();
		comboKmod3 = new javax.swing.JComboBox<Kmod3>();
		comboKmod2 = new javax.swing.JComboBox<Kmod2>();
		textKmod3 = new javax.swing.JLabel();
		textKmod1 = new javax.swing.JLabel();
		textKmod2 = new javax.swing.JLabel();
		jPanel13 = new javax.swing.JPanel();
		comboTipoChapaAco = new javax.swing.JComboBox<ClasseAco>();
		jLabel_classe_madeira_2 = new javax.swing.JLabel();
		jLabel_elemento2 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabelUnEspessura1 = new javax.swing.JLabel();
		fcok2 = new javax.swing.JLabel();
		unFcok2 = new javax.swing.JLabel();
		valorChapaFyk = new javax.swing.JLabel();
		densidade2 = new javax.swing.JLabel();
		unDensidade2 = new javax.swing.JLabel();
		jSeparator2 = new javax.swing.JSeparator();
		comboChapaAco = new javax.swing.JComboBox<EspessuraChapaAco>();
		valorChapaFuk = new javax.swing.JLabel();
		trocaFigura = new javax.swing.JButton();
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
		comboArruelasMadeira = new javax.swing.JComboBox<TipoArruela>();
		d1Arruelas = new javax.swing.JLabel();
		d2Arruelas = new javax.swing.JLabel();
		valorD1Arruelas = new javax.swing.JLabel();
		valorD2Arruelas = new javax.swing.JLabel();
		unD1Arruelas = new javax.swing.JLabel();
		unD2Arruelas = new javax.swing.JLabel();
		comboArruelasAco = new javax.swing.JComboBox<TipoArruelaAco>();
		buttonCalcular = new javax.swing.JButton();
		figuraTipoParafuso = new javax.swing.JButton();
		imagemTipoArruela = new javax.swing.JLabel();
		resultado = new javax.swing.JPanel();
		labelModeloFalha = new javax.swing.JLabel();
		labelResultadoModeloFalha = new javax.swing.JLabel();
		labelResistenciaLigacao = new javax.swing.JLabel();
		resultadoFvk = new javax.swing.JLabel();
		figuraResultadoModoFalha = new javax.swing.JButton();
		jLabel74 = new javax.swing.JLabel();
		jLabel85 = new javax.swing.JLabel();
		jLabel86 = new javax.swing.JLabel();
		jLabel89 = new javax.swing.JLabel();
		jLabel96 = new javax.swing.JLabel();
		resultadoRvk = new javax.swing.JLabel();
		resultadoRd = new javax.swing.JLabel();
		jLabel98 = new javax.swing.JLabel();
		jLabel99 = new javax.swing.JLabel();
		jLabel100 = new javax.swing.JLabel();
		labelEspessuraChapa = new javax.swing.JLabel();
		next3 = new javax.swing.JButton();
		relatorio = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		relatorioFinal = new javax.swing.JPanel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		relatorioSecaoCorte = new javax.swing.JLabel();
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
		jSeparator12 = new javax.swing.JSeparator();
		jLabel25 = new javax.swing.JLabel();
		relatorioChapaFyk = new javax.swing.JLabel();
		jLabel29 = new javax.swing.JLabel();
		relatorioChapaFuk = new javax.swing.JLabel();
		jLabel26 = new javax.swing.JLabel();
		jLabel30 = new javax.swing.JLabel();
		jLabel52 = new javax.swing.JLabel();
		relatorioChapaEspessura = new javax.swing.JLabel();
		jLabel70 = new javax.swing.JLabel();
		jLabel24 = new javax.swing.JLabel();
		labelTipoChapaAco = new javax.swing.JLabel();
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
		jLabel58 = new javax.swing.JLabel();
		relatorioFc0d1 = new javax.swing.JLabel();
		relatorioFaxrk = new javax.swing.JLabel();
		jLabel77 = new javax.swing.JLabel();
		jLabel78 = new javax.swing.JLabel();
		calculoForcaAlfa1 = new javax.swing.JLabel();
		calculoForca901 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		jLabel59 = new javax.swing.JLabel();
		relatorioRd11 = new javax.swing.JLabel();
		relatorioRd13 = new javax.swing.JLabel();
		relatorioFvk1 = new javax.swing.JLabel();
		relatorioFvk2 = new javax.swing.JLabel();
		relatorioFvk3 = new javax.swing.JLabel();
		jLabel56 = new javax.swing.JLabel();
		relatorioMyd = new javax.swing.JLabel();
		relatorioRd23 = new javax.swing.JLabel();
		jLabel55 = new javax.swing.JLabel();
		jLabel62 = new javax.swing.JLabel();
		jLabel63 = new javax.swing.JLabel();
		jLabel64 = new javax.swing.JLabel();
		labelInter1 = new javax.swing.JLabel();
		labelInt2 = new javax.swing.JLabel();
		jSeparator3 = new javax.swing.JSeparator();
		jSeparator4 = new javax.swing.JSeparator();
		jLabel67 = new javax.swing.JLabel();
		modoFalha = new javax.swing.JLabel();
		jLabel87 = new javax.swing.JLabel();
		jLabel95 = new javax.swing.JLabel();
		relatorioRvk = new javax.swing.JLabel();
		jSeparator6 = new javax.swing.JSeparator();
		jSeparator7 = new javax.swing.JSeparator();
		figuraSecoes = new javax.swing.JLabel();
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
		labelrelatorioChapaAco = new javax.swing.JLabel();
		jSeparator9 = new javax.swing.JSeparator();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
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
		setMaximumSize(new java.awt.Dimension(900, 700));
		setMinimumSize(new java.awt.Dimension(900, 700));
		setName(""); // NOI18N
		setPreferredSize(new java.awt.Dimension(825, 725));
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowActivated(java.awt.event.WindowEvent evt) {
				formWindowActivated(evt);
			}
		});

		jTabbedPane1.setToolTipText("");
		jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jTabbedPane1.setMaximumSize(new java.awt.Dimension(50000, 50000));
		jTabbedPane1.setMinimumSize(new java.awt.Dimension(800, 700));
		jTabbedPane1.setPreferredSize(new java.awt.Dimension(877, 700));
		jTabbedPane1.setRequestFocusEnabled(false);
		jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusGained(java.awt.event.FocusEvent evt) {
				jTabbedPane1FocusGained(evt);
			}
		});

		jProgressBarStatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		jProgressBarStatus.setString("Clique em \"Iniciar Cálculo\" para começar o dimensionamento.");
		jProgressBarStatus.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
		jProgressBarStatus.setPreferredSize(new java.awt.Dimension(34, 30));
		jProgressBarStatus.setStringPainted(true);
		getContentPane().add(jProgressBarStatus, java.awt.BorderLayout.SOUTH);

		inicio.setBackground(new java.awt.Color(204, 204, 204));

		logoPrograma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Logo/logosket.png"))); // NOI18N

		jLabel97.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
		jLabel97.setText("TCD - Timber Connections Design");

		iniciarCalculo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		iniciarCalculo.setText("Iniciar Cálculo");
		iniciarCalculo.setToolTipText("Escolha esta opção para iniciar o dimensionamento de sua ligação.");
		iniciarCalculo.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				iniciarCalculoActionPerformed(evt);
			}
		});

		jButton4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jButton4.setText("Voltar");
		jButton4.setToolTipText("Escolha esta opção para retornar a tela inicial.");
		jButton4.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jLabel15.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel15.setText("Cálculo de ligações parafusadas entre elementos de madeira e aço.");

		javax.swing.GroupLayout InicioLayout = new javax.swing.GroupLayout(inicio);
		inicio.setLayout(InicioLayout);
		InicioLayout
		        .setHorizontalGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioLayout.createSequentialGroup()
		                        .addComponent(logoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(254, 254, 254))
		                .addGroup(InicioLayout.createSequentialGroup()
		                        .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                .addGroup(InicioLayout.createSequentialGroup().addGap(161, 161, 161)
		                                        .addComponent(iniciarCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(102, 102, 102)
		                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                .addGroup(InicioLayout.createSequentialGroup().addGap(234, 234, 234).addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                                        .addComponent(jLabel15).addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))))
		                        .addGap(193, 193, 193)));
		InicioLayout.setVerticalGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(InicioLayout.createSequentialGroup().addGap(65, 65, 65).addComponent(logoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel97).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(jLabel15).addGap(95, 95, 95)
		                .addGroup(InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                        .addComponent(iniciarCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(118, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Inicio", inicio);

		secoesCorte.setBackground(new java.awt.Color(204, 204, 204));
		secoesCorte.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		secoesCorte.setMaximumSize(new java.awt.Dimension(50000, 50000));

		groupSecoesCorte.add(btnCorteSimples);
		btnCorteSimples.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		btnCorteSimples.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/UmaSecaoAco.png"))); // NOI18N
		btnCorteSimples.setText("Corte Simples");
		btnCorteSimples.setToolTipText("Escolha esta opção se a ligação apresentar apenas uma seção de corte no parafuso.");
		btnCorteSimples.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btnCorteSimples.setName(""); // NOI18N
		btnCorteSimples.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		btnCorteSimples.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCorteSimplesActionPerformed(evt);
			}
		});

		groupSecoesCorte.add(btnCorteDuploAMA);
		btnCorteDuploAMA.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		btnCorteDuploAMA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/DuasSecoesAcoFora.png"))); // NOI18N
		btnCorteDuploAMA.setText("Corte Duplo - Aço/Madeira/Aço");
		btnCorteDuploAMA.setToolTipText("Escolha esta opção se a ligação apresentar duas seções de corte no parafuso, com chapas de aço externamente ao elemento de madeira.");
		btnCorteDuploAMA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btnCorteDuploAMA.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		btnCorteDuploAMA.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCorteDuploAMAActionPerformed(evt);
			}
		});

		groupSecoesCorte.add(btnCorteDuploMAM);
		btnCorteDuploMAM.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		btnCorteDuploMAM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/DuasSecoesAcoDentro.png"))); // NOI18N
		btnCorteDuploMAM.setText("Corte Duplo - Madeira/Aço/Madeira");
		btnCorteDuploMAM.setToolTipText("Escolha esta opção se a ligação apresentar duas seções de corte no parafuso, com chapas de aço internamente aos elementos de madeira.");
		btnCorteDuploMAM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btnCorteDuploMAM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		btnCorteDuploMAM.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCorteDuploMAMActionPerformed(evt);
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
		        .addGroup(SecoesCorteLayout.createSequentialGroup().addGap(6, 6, 6).addComponent(btnCorteSimples, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(btnCorteDuploAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(btnCorteDuploMAM, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
		                        .addComponent(btnCorteDuploMAM, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(btnCorteDuploAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(btnCorteSimples, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
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
		comboElem1.setToolTipText("Defina a classe do elemento de madeira, baseado nas tabelas 2 e 3 da revisão da norma NBR 7190 (2011).");
		comboElem1.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusLost(java.awt.event.FocusEvent evt) {
				comboElem1ClasseMadeiraFocusLost(evt);
			}
		});
		jPanel7.add(comboElem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 250, 29));

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

		espessura1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		espessura1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		espessura1.setText("Digite a espessura");
		espessura1.setToolTipText("Insira a espessura do(s) elemento(s) de madeira.");
		espessura1.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusGained(java.awt.event.FocusEvent evt) {
				espessura1(evt);
			}
		});
		espessura1.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyTyped(java.awt.event.KeyEvent evt) {
				espessura1KeyTyped(evt);
			}
		});
		jPanel7.add(espessura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 142, 130, -1));

		jLabelUnEspessura.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabelUnEspessura.setText("(mm)");
		jPanel7.add(jLabelUnEspessura, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 145, -1, -1));

		fcok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		fcok1.setText("fc0,k:");
		jPanel7.add(fcok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, -1, -1));

		unFcok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unFcok1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		unFcok1.setText("(MPa)");
		jPanel7.add(unFcok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 85, -1, -1));
		unFcok1.getAccessibleContext().setAccessibleDescription("");

		valorFc01.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorFc01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorFc01.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel7.add(valorFc01, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 85, 40, 20));

		densidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		densidade1.setText("Densidade:");
		jPanel7.add(densidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 85, -1, -1));

		valorDensidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorDensidade1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorDensidade1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel7.add(valorDensidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 85, 40, 20));

		unDensidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unDensidade1.setText("(kg/m³)");
		jPanel7.add(unDensidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 85, -1, -1));

		valorFvok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorFvok1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorFvok1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel7.add(valorFvok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 115, 40, 20));

		unFv0k1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unFv0k1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		unFv0k1.setText("(MPa)");
		jPanel7.add(unFv0k1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 115, -1, -1));

		fv0k1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		fv0k1.setText("fv0,k:");
		jPanel7.add(fv0k1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 115, -1, -1));

		ec0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		ec0m1.setText("Ec0,m:");
		jPanel7.add(ec0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 115, -1, -1));

		unEc0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unEc0m1.setText("(MPa)");
		jPanel7.add(unEc0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 115, -1, -1));

		valorEc0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorEc0m1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorEc0m1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		valorEc0m1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jPanel7.add(valorEc0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 115, 50, 20));

		jLabel11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel11.setText("Ângulo entre as peças:");
		jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 173, -1, -1));

		valorAngulo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorAngulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		valorAngulo.setText("0");
		valorAngulo.setToolTipText("Insira o ângulo entre os elementos de madeira e aço.");
		valorAngulo.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusGained(java.awt.event.FocusEvent evt) {
				valorAngulo(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				valorAnguloFocusLost(evt);
			}
		});
		valorAngulo.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyTyped(java.awt.event.KeyEvent evt) {
				valorAnguloKeyTyped(evt);
			}
		});
		jPanel7.add(valorAngulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 171, 63, -1));

		jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel5.setText("(°)");
		jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 172, 20, 20));

		jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jPanel7.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 10, 50));

		elementosMadeira.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 5, -1, 200));

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

		comboKmod1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboKmod1.setModel(new javax.swing.DefaultComboBoxModel<Kmod1>(Kmod1.values()));
		comboKmod1.setToolTipText("Insira o valor do Kmod 1, o qual é definido pela tabela 4 da revisão da norma ABNT NBR 7190 (2011).");
		comboKmod1.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboKmod1ActionPerformed(evt);
			}
		});
		jPanel9.add(comboKmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 400, 30));

		comboKmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboKmod3.setModel(new javax.swing.DefaultComboBoxModel<Kmod3>(Kmod3.values()));
		comboKmod3.setToolTipText("Insira o valor do Kmod 3, o qual é definido pelas tabelas 6 e 7 da revisão da norma ABNT NBR 7190 (2011).");
		comboKmod3.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboKmod3ActionPerformed(evt);
			}
		});
		jPanel9.add(comboKmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 400, 30));

		comboKmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboKmod2.setModel(new javax.swing.DefaultComboBoxModel<Kmod2>(Kmod2.values()));
		comboKmod2.setToolTipText("Insira o valor do Kmod 2, o qual é definido pela tabela 5 da revisão da norma ABNT NBR 7190 (2011).");
		comboKmod2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboKmod2ActionPerformed(evt);
			}
		});
		jPanel9.add(comboKmod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 400, 30));

		textKmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		textKmod3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		textKmod3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel9.add(textKmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 125, 40, 20));

		textKmod1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		textKmod1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		textKmod1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel9.add(textKmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 45, 40, 20));

		textKmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		textKmod2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		textKmod2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel9.add(textKmod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 85, 40, 20));

		elementosMadeira.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 365, 780, 160));

		jPanel13.setBackground(new java.awt.Color(153, 153, 153));
		jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel13.setPreferredSize(new java.awt.Dimension(385, 225));
		jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		comboTipoChapaAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboTipoChapaAco.setModel(new javax.swing.DefaultComboBoxModel<ClasseAco>(ClasseAco.values()));
		comboTipoChapaAco.setToolTipText("Defina a classe do aço, baseado na norma ABNT NBR 8800 (2008).");
		comboTipoChapaAco.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboTipoChapaAcoActionPerformed(evt);
			}
		});
		jPanel13.add(comboTipoChapaAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 270, 29));

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

		fcok2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		fcok2.setText("fy,k:");
		jPanel13.add(fcok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, -1, -1));

		unFcok2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unFcok2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		unFcok2.setText("(MPa)");
		jPanel13.add(unFcok2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 85, -1, -1));

		valorChapaFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorChapaFyk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorChapaFyk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel13.add(valorChapaFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 85, 40, 20));

		densidade2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		densidade2.setText("fu,k");
		jPanel13.add(densidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 85, -1, -1));

		unDensidade2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unDensidade2.setText("(MPa)");
		jPanel13.add(unDensidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 85, -1, -1));

		jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jPanel13.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 75, 10, 30));

		comboChapaAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboChapaAco.setModel(new javax.swing.DefaultComboBoxModel<EspessuraChapaAco>(EspessuraChapaAco.values()));
		comboChapaAco.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboChapaAcoActionPerformed(evt);
			}
		});
		jPanel13.add(comboChapaAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 115, 211, 29));

		valorChapaFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorChapaFuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorChapaFuk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel13.add(valorChapaFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 85, 40, 20));

		elementosMadeira.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 210, -1, 150));

		trocaFigura.setToolTipText("Esta imagem caracteriza os elementos de madeira.");
		trocaFigura.setContentAreaFilled(false);
		elementosMadeira.add(trocaFigura, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 370, 280));

		next2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/next.png"))); // NOI18N
		next2.setToolTipText("Clique para avançar.");
		next2.setEnabled(false);
		next2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				next2ActionPerformed(evt);
			}
		});
		elementosMadeira.add(next2, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 485, 40, -1));

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
				comboTipoParafusoFocusLost(evt);
			}
		});
		comboTipoParafuso.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboTipoParafusoActionPerformed(evt);
			}
		});
		jPanel10.add(comboTipoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 41, 270, -1));

		diametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		diametro.setText("Diâmetro (mm):");
		jPanel10.add(diametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

		valorDiametro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorDiametro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorDiametro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.add(valorDiametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 40, 20));

		areaParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		areaParafuso.setText("Área do Parafuso (mm²):");
		jPanel10.add(areaParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

		valorArea.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.add(valorArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 70, 40, 20));

		numeroParafusos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		numeroParafusos.setText("Número de Parafusos:");
		jPanel10.add(numeroParafusos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 103, -1, -1));

		classeAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		classeAco.setText("Classe do Aço:");
		jPanel10.add(classeAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 142, -1, -1));

		comboAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboAco.setModel(new javax.swing.DefaultComboBoxModel<ClasseAcoParafuso>(ClasseAcoParafuso.values()));
		comboAco.setToolTipText("Defina a classe do aço do parafuso, baseado na norma ABNT NBR 8800 (2008).");
		comboAco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		comboAco.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboAcoActionPerformed(evt);
			}
		});
		jPanel10.add(comboAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 140, 290, -1));

		fyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		fyk.setText("fy,k:");
		jPanel10.add(fyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 175, -1, -1));

		fuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		fuk.setText("fu,k:");
		jPanel10.add(fuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 175, -1, -1));

		valorFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorFyk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorFyk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.add(valorFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 175, 40, 20));

		valorFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorFuk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorFuk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel10.add(valorFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 175, 40, 20));

		unFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unFyk.setText("(MPa)");
		jPanel10.add(unFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 175, -1, -1));

		unFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unFuk.setText("(MPa)");
		jPanel10.add(unFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 175, -1, -1));

		testeParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		testeParafuso.setText("Foram realizados ensaios na ligação em estudo que comprovem ");
		testeParafuso.setToolTipText("Considera-se ou não a força de arrancamento causada pelo parafuso na madeira. EUROCODE 5 (2004);");
		jPanel10.add(testeParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 200, -1, -1));

		groupTesteParafuso.add(testeParafusoSim);
		testeParafusoSim.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		testeParafusoSim.setText("Sim");
		testeParafusoSim.setToolTipText("Escolha se a ligação considera ou não a força de arrancamento causada pelo parafuso na madeira.");
		testeParafusoSim.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				testeParafusoSimActionPerformed(evt);
			}
		});
		jPanel10.add(testeParafusoSim, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 235, 50, -1));

		groupTesteParafuso.add(testeParafusoNao);
		testeParafusoNao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		testeParafusoNao.setText("Não");
		testeParafusoNao.setToolTipText("Escolha se a ligação considera ou não a força de arrancamento causada pelo parafuso na madeira.");
		testeParafusoNao.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				testeParafusoNaoActionPerformed(evt);
			}
		});
		jPanel10.add(testeParafusoNao, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 235, 50, -1));

		comboQuantParafuso.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboQuantParafuso.setModel(new javax.swing.DefaultComboBoxModel<QuantidadeParafuso>(QuantidadeParafuso.values()));
		comboQuantParafuso.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboQuantParafusoActionPerformed(evt);
			}
		});
		jPanel10.add(comboQuantParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 245, -1));

		jLabel65.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel65.setText("que o efeito de confinamento possa ser utilizado?");
		jPanel10.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 215, -1, -1));

		elementosMetalicos.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 43, 425, 265));

		jPanel11.setBackground(new java.awt.Color(153, 153, 153));
		jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		arruelas.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
		arruelas.setText("ARRUELAS");
		jPanel11.add(arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 13, -1, -1));

		tipoArruela.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		tipoArruela.setText("Tipo de Arruela:");
		jPanel11.add(tipoArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 45, -1, -1));

		comboArruelasMadeira.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboArruelasMadeira.setModel(new javax.swing.DefaultComboBoxModel<TipoArruela>(TipoArruela.values()));
		comboArruelasMadeira.setToolTipText("Defina o tipo de arruela, baseado nas normas DIN 440 R, DIN 440 V e DIN 436.");
		comboArruelasMadeira.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboArruelasMadeiraActionPerformed(evt);
			}
		});
		jPanel11.add(comboArruelasMadeira, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 270, -1));

		d1Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		d1Arruelas.setText("Dimensão (d1):");
		jPanel11.add(d1Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 95, -1, -1));

		d2Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		d2Arruelas.setText("Dimensão (d2):");
		jPanel11.add(d2Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 95, -1, -1));

		valorD1Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorD1Arruelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorD1Arruelas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel11.add(valorD1Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 95, 40, 20));

		valorD2Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		valorD2Arruelas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		valorD2Arruelas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jPanel11.add(valorD2Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 95, 40, 20));

		unD1Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unD1Arruelas.setText("(mm)");
		jPanel11.add(unD1Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 95, -1, -1));

		unD2Arruelas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		unD2Arruelas.setText("(mm)");
		jPanel11.add(unD2Arruelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 95, -1, -1));

		comboArruelasAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		comboArruelasAco.setModel(new javax.swing.DefaultComboBoxModel<TipoArruelaAco>(TipoArruelaAco.values()));
		comboArruelasAco.setToolTipText("Defina o tipo de arruela, baseado nas normas DIN 125.");
		comboArruelasAco.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboArruelasAcoActionPerformed(evt);
			}
		});
		jPanel11.add(comboArruelasAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 270, -1));

		elementosMetalicos.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 320, 425, 130));

		buttonCalcular.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
		buttonCalcular.setText("CALCULAR LIGAÇÃO");
		buttonCalcular.setToolTipText("Clique aqui para calcular sua ligação. Fique atento! Caso haja informações faltantes ou inconsistentes, o cálculo não será realizado e aparecerá uma mensagem.");
		buttonCalcular.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonCalcularActionPerformed(evt);
			}
		});
		elementosMetalicos.add(buttonCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 468, 210, 41));

		figuraTipoParafuso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Imagens/Parafuso.png"))); // NOI18N
		figuraTipoParafuso.setContentAreaFilled(false);
		elementosMetalicos.add(figuraTipoParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 83, 295, 150));
		elementosMetalicos.add(imagemTipoArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 300, 150));

		jTabbedPane1.addTab("Conectores", elementosMetalicos);

		resultado.setBackground(new java.awt.Color(204, 204, 204));
		resultado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		labelModeloFalha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		labelModeloFalha.setText("Modelo de falha:");
		resultado.add(labelModeloFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 110, 20));

		labelResultadoModeloFalha.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		resultado.add(labelResultadoModeloFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 660, 20));

		labelResistenciaLigacao.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		labelResistenciaLigacao.setText("Fv,Rk:");
		resultado.add(labelResistenciaLigacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 60, 20));

		resultadoFvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		resultadoFvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		resultado.add(resultadoFvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 80, 20));

		figuraResultadoModoFalha.setContentAreaFilled(false);
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

		resultadoRvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		resultadoRvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		resultado.add(resultadoRvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 80, 20));

		resultadoRd.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		resultadoRd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		resultado.add(resultadoRd, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 80, 20));

		jLabel98.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel98.setText("Fv,Rk = resistência característica de uma seção de corte por parafuso");
		resultado.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 450, -1));

		jLabel99.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel99.setText("Rk = resistência característica da ligação considerando as quantidades de seções de corte e parafusos. ");
		resultado.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 670, -1));

		jLabel100.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel100.setText("Rd = resistência de cálculo da ligação.");
		resultado.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 670, -1));

		labelEspessuraChapa.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		resultado.add(labelEspessuraChapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 330, 20));

		next3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensBotao/next.png"))); // NOI18N
		next3.setToolTipText("Clique para avançar.");
		next3.setEnabled(false);
		next3.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				next3ActionPerformed(evt);
			}
		});
		resultado.add(next3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 480, 40, -1));

		jTabbedPane1.addTab("Resultado", resultado);

		relatorio.setBackground(new java.awt.Color(204, 204, 204));
		relatorio.setEnabled(false);

		relatorioFinal.setBackground(new java.awt.Color(255, 255, 255));
		relatorioFinal.setPreferredSize(new java.awt.Dimension(760, 1000));
		relatorioFinal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel12.setText("ELEMENTOS DA LIGAÇÃO");
		relatorioFinal.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 220, -1));

		jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
		jLabel13.setText("RELATÓRIO DE RESISTÊNCIA DA LIGAÇÃO ");
		relatorioFinal.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 39, -1, -1));

		relatorioSecaoCorte.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioSecaoCorte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		relatorioFinal.add(relatorioSecaoCorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 80, 250, 14));
		relatorioFinal.add(relatoriofcok4, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 658, -1, -1));

		relatorioElem1.setBackground(new java.awt.Color(255, 255, 255));
		relatorioElem1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		relatorioElem1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel14.setText("Madeira");
		relatorioElem1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 5, 80, -1));

		jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel2.setText("fc0,k:");
		relatorioElem1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

		jLabel16.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel16.setText("fv0,k:");
		relatorioElem1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

		jLabel18.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel18.setText("Ec0,m:");
		relatorioElem1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

		jLabel19.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel19.setText("Densidade:");
		relatorioElem1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 70, -1));

		relatorioDensidade1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioDensidade1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioDensidade1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatorioDensidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 50, 14));

		relatorioEc0m1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioEc0m1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioEc0m1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatorioEc0m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 50, 14));

		relatoriofv0k1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatoriofv0k1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatoriofv0k1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatoriofv0k1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 50, 14));

		relatorioFcok1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFcok1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFcok1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatorioFcok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 50, 14));

		jLabel20.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel20.setText("(MPa)");
		relatorioElem1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 40, -1));

		jLabel21.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel21.setText("(MPa)");
		relatorioElem1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 40, -1));

		jLabel22.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel22.setText("(MPa)");
		relatorioElem1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 40, -1));

		jLabel23.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel23.setText("(kg/m³)");
		relatorioElem1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 40, -1));
		relatorioElem1.add(relatorioClasseElem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 5, 40, 14));

		jLabel51.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel51.setText("Espessura:");
		relatorioElem1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

		relatorioEspessura1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioEspessura1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatorioEspessura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 50, 14));

		jLabel69.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel69.setText("(mm)");
		relatorioElem1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 40, -1));

		jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
		relatorioElem1.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 20, 90));

		jLabel25.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel25.setText("fy,k:");
		relatorioElem1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, 14));

		relatorioChapaFyk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioChapaFyk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatorioChapaFyk, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 35, 14));

		jLabel29.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel29.setText("(MPa)");
		relatorioElem1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

		relatorioChapaFuk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioChapaFuk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatorioChapaFuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 35, 14));

		jLabel26.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel26.setText("fu,k:");
		relatorioElem1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, -1));

		jLabel30.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel30.setText("(MPa)");
		relatorioElem1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

		jLabel52.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel52.setText("Espessura:");
		relatorioElem1.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

		relatorioChapaEspessura.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioChapaEspessura.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioElem1.add(relatorioChapaEspessura, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 35, 14));

		jLabel70.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel70.setText("(mm)");
		relatorioElem1.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

		jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel24.setText("Aço");
		relatorioElem1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 5, -1, -1));

		labelTipoChapaAco.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
		labelTipoChapaAco.setText("ASTM A36");
		relatorioElem1.add(labelTipoChapaAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 5, 60, 14));

		relatorioFinal.add(relatorioElem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 320, 120));

		relatorioCoeficientes.setBackground(new java.awt.Color(255, 255, 255));
		relatorioCoeficientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		relatorioCoeficientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel88.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel88.setText("(°)");
		relatorioCoeficientes.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

		relatoriokmod3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatoriokmod3.setText("Densidade:");
		relatorioCoeficientes.add(relatoriokmod3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 35, 14));

		relatorioAngulo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioAngulo.setText("De");
		relatorioCoeficientes.add(relatorioAngulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 25, 15));

		relatorioKmod1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioKmod1.setText("jLabel20");
		relatorioCoeficientes.add(relatorioKmod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 35, 14));

		relatoriokmod2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatoriokmod2.setText("Densidade:");
		relatorioCoeficientes.add(relatoriokmod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 35, 14));

		jLabel90.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel90.setText("kmod 3:");
		relatorioCoeficientes.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 50, -1));

		jLabel91.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel91.setText("kmod 2");
		relatorioCoeficientes.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 50, -1));

		jLabel92.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel92.setText("Ângulo:");
		relatorioCoeficientes.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 50, -1));

		jLabel93.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel93.setText("Coeficientes");
		relatorioCoeficientes.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 5, -1, -1));

		jLabel94.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel94.setText("kmod 1:");
		relatorioCoeficientes.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 50, -1));

		jLabel35.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel35.setText("γm,ligação:");
		relatorioCoeficientes.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

		jLabel81.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel81.setText("1,4");
		relatorioCoeficientes.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

		relatorioFinal.add(relatorioCoeficientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 170, 270, 120));

		jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel17.setText("ELEMENTOS METÁLICOS");
		relatorioFinal.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 305, 200, -1));

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
		relatorioParafuso.add(relatorioNParafusos, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 25, 14));

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
		relatorioParafuso.add(figuraParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 110, 30));

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

		relatorioFinal.add(relatorioParafuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 335, 320, 135));

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

		relatorioTipoArruela.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioTipoArruela.setText("jLabel51");
		jPanel1.add(relatorioTipoArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

		relatorioD1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioD1.setText("jLabel51");
		jPanel1.add(relatorioD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 110, 35, 14));

		relatorioD2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioD2.setText("jLabel51");
		jPanel1.add(relatorioD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 35, 14));

		figuraArruela.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		figuraArruela.setText("jLabel74");
		jPanel1.add(figuraArruela, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 100, 50));

		jLabel75.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel75.setText("(mm)");
		jPanel1.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 30, -1));

		jLabel76.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel76.setText("(mm)");
		jPanel1.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 40, -1));

		jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 20, 20));

		relatorioFinal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 335, 270, 135));
		relatorioFinal.add(relatorioFibras, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 313, -1, -1));

		jLabel44.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel44.setText("VALORES  CALCULADOS");
		relatorioFinal.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 485, 200, -1));

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel53.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel53.setText("Cálculos Preliminares");
		jPanel2.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));

		calculoForca01.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		calculoForca01.setText("fe0,k1:");
		jPanel2.add(calculoForca01, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 40, -1));

		jLabel58.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel58.setText("Fax,rk:");
		jPanel2.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 40, -1));

		relatorioFc0d1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFc0d1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFc0d1.setText("jLabel59");
		jPanel2.add(relatorioFc0d1, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 25, 40, -1));

		relatorioFaxrk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFaxrk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFaxrk.setText("jLabel59");
		jPanel2.add(relatorioFaxrk, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 45, 50, -1));

		jLabel77.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel77.setText("(N)");
		jPanel2.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 45, 30, -1));

		jLabel78.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel78.setText("(MPa)");
		jPanel2.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 25, 40, -1));

		calculoForcaAlfa1.setText("feα,k1:");
		jPanel2.add(calculoForcaAlfa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 40, -1));

		calculoForca901.setText("fe90,k1:");
		jPanel2.add(calculoForca901, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 50, -1));

		relatorioFinal.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 505, 180, 65));

		jPanel3.setBackground(new java.awt.Color(255, 255, 255));
		jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel59.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel59.setText("Cálculo do Eurocode 5");
		jPanel3.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 5, -1, -1));

		relatorioRd11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd11.setText("Fv,k1:");
		jPanel3.add(relatorioRd11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, 40, -1));

		relatorioRd13.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd13.setText("Fv,k2:");
		jPanel3.add(relatorioRd13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 45, 40, -1));

		relatorioFvk1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFvk1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFvk1.setText("jLabel63");
		jPanel3.add(relatorioFvk1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 45, 50, -1));

		relatorioFvk2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFvk2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFvk2.setText("jLabel63");
		jPanel3.add(relatorioFvk2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 45, 50, -1));

		relatorioFvk3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFvk3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFvk3.setText("jLabel63");
		jPanel3.add(relatorioFvk3, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 45, 50, -1));

		jLabel56.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel56.setText("Myk:");
		jPanel3.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 25, 30, -1));

		relatorioMyd.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioMyd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioMyd.setText("jLabel59");
		jPanel3.add(relatorioMyd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 25, 50, -1));

		relatorioRd23.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd23.setText("Fv,k3:");
		jPanel3.add(relatorioRd23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 45, 40, -1));

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

		labelInter1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		labelInter1.setText("Fv,kmin1:");
		jPanel3.add(labelInter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, -1, -1));

		labelInt2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		labelInt2.setText("Fv,kmin2:");
		jPanel3.add(labelInt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 45, -1, -1));

		jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 45, 10, 15));

		jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 45, 10, 15));

		relatorioFinal.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 505, 410, 65));

		jLabel67.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
		jLabel67.setText("RESULTADO:");
		relatorioFinal.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 605, -1, -1));

		modoFalha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		relatorioFinal.add(modoFalha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 170, 140));

		jLabel87.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel87.setText("Tipo de ruptura:");
		relatorioFinal.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 625, -1, -1));

		jLabel95.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel95.setText("Rk:");
		relatorioFinal.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 690, 70, -1));

		relatorioRvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFinal.add(relatorioRvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 690, 70, 15));
		relatorioFinal.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 600, 10));
		relatorioFinal.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 600, 10));

		figuraSecoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		relatorioFinal.add(figuraSecoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 80, 70));

		data.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		data.setText("jLabel35");
		relatorioFinal.add(data, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 840, 80, -1));

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

		relatorioFinal.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 780, 570, 50));

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

		relatorioFinal.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 620, 310, 30));

		hora.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		hora.setText("jLabel65");
		relatorioFinal.add(hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 840, -1, -1));
		relatorioFinal.add(rLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, 80));

		jLabel66.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel66.setText("Fv,Rk:");
		relatorioFinal.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 670, 50, -1));

		relatorioFvk.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFvk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioFvk.setText("jLabel67");
		relatorioFinal.add(relatorioFvk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 670, 70, -1));

		jLabel80.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel80.setText("Rd:");
		relatorioFinal.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 710, 50, -1));

		relatorioRd.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioRd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		relatorioRd.setText("jLabel81");
		relatorioFinal.add(relatorioRd, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 710, 70, -1));

		jLabel82.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel82.setText("(N)");
		relatorioFinal.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 710, -1, -1));

		jLabel83.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel83.setText("(N)");
		relatorioFinal.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 670, -1, -1));

		jLabel84.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel84.setText("(N)");
		relatorioFinal.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 690, -1, -1));

		labelrelatorioChapaAco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		relatorioFinal.add(labelrelatorioChapaAco, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 100, 250, 14));
		relatorioFinal.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 600, 10));

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
		jButton2.setToolTipText("Clique para realizar novo cálculo.\n");
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
		relatorioLayout.setHorizontalGroup(relatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(relatorioLayout.createSequentialGroup().addGroup(relatorioLayout
		        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
		        .addGroup(relatorioLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
		        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(0, 0, Short.MAX_VALUE)));
		relatorioLayout
		        .setVerticalGroup(relatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                .addGroup(relatorioLayout.createSequentialGroup().addGroup(relatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(relatorioLayout
		                        .createSequentialGroup().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(relatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jButton2,
		                                                                                                                                                         javax.swing.GroupLayout.Alignment.TRAILING,
		                                                                                                                                                         javax.swing.GroupLayout.PREFERRED_SIZE,
		                                                                                                                                                         40,
		                                                                                                                                                         javax.swing.GroupLayout.PREFERRED_SIZE)))
		                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(78, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Relatório", relatorio);

		jTabbedPane1.setSelectedComponent(inicio);
		relatorio.setVisible(false);

		getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

		getAccessibleContext().setAccessibleName("ProgramaMarcos");

		pack();
	}// </editor-fold>//GEN-END:initComponents

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

	private void buttonCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCalcularActionPerformed
		try {
			if(buttonCalcular.hasFocus()) {
				figuraArruela.setIcon(new ImageIcon(((ImageIcon)imagemTipoArruela.getIcon()).getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH)));
				labelInter1.setVisible(false);
				labelInt2.setVisible(false);
				jProgressBarStatus.setString("Clique em avançar para continuar.");
				jTabbedPane1.setSelectedComponent(resultado);
				jTabbedPane1.setEnabledAt(4, true);
				next3.setEnabled(true);

				calculoModeloLigacao = CalculoModeloLigacao.getInstance();
				calculoModeloLigacao.calcularModeloLigacaoAco(modeloLigacao, incSim1, incSim2, m);

				data.setText(objDataHora.MostraData());
				hora.setText(objDataHora.MostraHora());

				relatorioAngulo.setText(String.format("%.1f", calculoModeloLigacao.getAngulo()));
				relatorioKmod1.setText(textKmod1.getText());
				relatoriokmod2.setText(textKmod2.getText());
				relatoriokmod3.setText(textKmod3.getText());
				relatorioEspessura1.setText(espessura1.getText());
				relatorioFc0d1.setText(String.format("%.1f", calculoModeloLigacao.getFe0d1()));
				relatorioFaxrk.setText(String.format("%.0f", calculoModeloLigacao.getValorFaxrk()));
				resultadoFvk.setText(String.format("%.0f", calculoModeloLigacao.getFvkmin()));
				resultadoRvk.setText(String.format("%.0f", calculoModeloLigacao.getRdlig()));
				resultadoRd.setText(String.format("%.0f", calculoModeloLigacao.getRvd()));
				relatorioMyd.setText(String.format("%.0f", calculoModeloLigacao.getMyd()));
				relatorioFvk1.setText(String.format("%.0f", calculoModeloLigacao.getFv0k1()));
				relatorioFvk3.setText(String.format("%.0f", calculoModeloLigacao.getFv0k2()));
				relatorioFvk.setText(String.format("%.0f", calculoModeloLigacao.getFvkmin()));
				relatorioRvk.setText(String.format("%.0f", calculoModeloLigacao.getRdlig()));
				relatorioRd.setText(String.format("%.0f", calculoModeloLigacao.getRvd()));
				labelEspessuraChapa.setText("Ligação com chapa fina.");
				labelrelatorioChapaAco.setText("Ligação com chapa fina.");
				labelResultadoModeloFalha.setText(calculoModeloLigacao.getTipo());
				teste.setText(calculoModeloLigacao.getTipo());

				//Verificação da chapa grossa ou fina
				boolean fina = false;
				boolean grossa = false;
				boolean indefinida = false;

				//INICIA-SE O CÁLCULO DA LIGAÇÃO PARA UMA SEÇÃO DE CORTE FINA
				if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && fina) {
					relatorioRd11.setVisible(true);
					relatorioRd13.setVisible(false);
					relatorioRd23.setVisible(true);
					relatorioFvk1.setVisible(true);
					relatorioFvk2.setVisible(false);
					relatorioFvk3.setVisible(true);
					jLabel55.setVisible(true);
					jLabel62.setVisible(false);
					jLabel63.setVisible(true);
					jScrollPane2.setVisible(true);
				} else if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && grossa) {
					relatorioRd11.setVisible(true);
					relatorioRd13.setVisible(true);
					relatorioRd23.setVisible(true);
					relatorioFvk1.setVisible(true);
					relatorioFvk2.setVisible(true);
					relatorioFvk3.setVisible(true);
					jLabel55.setVisible(true);
					jLabel62.setVisible(true);
					jLabel63.setVisible(true);
					jScrollPane2.setVisible(true);
				} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO_MADEIRA_ACO_MADEIRA) {
					relatorioRd11.setVisible(true);
					relatorioRd13.setVisible(true);
					relatorioRd23.setVisible(true);
					relatorioFvk1.setVisible(true);
					relatorioFvk2.setVisible(true);
					relatorioFvk3.setVisible(true);
					jLabel55.setVisible(true);
					jLabel62.setVisible(true);
					jLabel63.setVisible(true);
					jScrollPane2.setVisible(true);
				} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO_ACO_MADEIRA_ACO && fina) {
					relatorioRd11.setVisible(true);
					relatorioRd13.setVisible(false);
					relatorioRd23.setVisible(true);
					relatorioFvk1.setVisible(true);
					relatorioFvk2.setVisible(false);
					relatorioFvk3.setVisible(true);
					jLabel55.setVisible(true);
					jLabel62.setVisible(false);
					jLabel63.setVisible(true);
					jScrollPane2.setVisible(true);
				} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO_ACO_MADEIRA_ACO && grossa) {
					relatorioRd11.setVisible(true);
					relatorioRd13.setVisible(false);
					relatorioRd23.setVisible(true);
					relatorioFvk1.setVisible(true);
					relatorioFvk2.setVisible(false);
					relatorioFvk3.setVisible(true);
					jLabel55.setVisible(true);
					jLabel62.setVisible(false);
					jLabel63.setVisible(true);
					jScrollPane2.setVisible(true);
				} else if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && indefinida) {
					relatorioRd11.setVisible(false);
					relatorioRd13.setVisible(false);
					relatorioRd23.setVisible(false);
					labelInter1.setVisible(true);
					labelInt2.setVisible(true);
					relatorioFvk1.setVisible(true);
					relatorioFvk2.setVisible(false);
					relatorioFvk3.setVisible(true);
					jLabel55.setVisible(true);
					jLabel62.setVisible(false);
					jLabel63.setVisible(true);
					jScrollPane2.setVisible(true);
				} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO_ACO_MADEIRA_ACO && indefinida) {
					relatorioRd11.setVisible(false);
					relatorioRd13.setVisible(false);
					relatorioRd23.setVisible(false);
					labelInter1.setVisible(true);
					labelInt2.setVisible(true);
					relatorioFvk1.setVisible(true);
					relatorioFvk2.setVisible(false);
					relatorioFvk3.setVisible(true);
					jLabel55.setVisible(true);
					jLabel62.setVisible(false);
					jLabel63.setVisible(true);
					jScrollPane2.setVisible(true);
				}

				//COM ESSAS FUNÇÕES ALTERAM-SE AS IMAGENS DO RELATÓRIO
				figuraResultadoModoFalha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensFalhas/" + calculoModeloLigacao.getImagemFalha()))); // NOI18N
				figuraParafuso.setIcon(new ImageIcon(((ImageIcon)figuraTipoParafuso.getIcon()).getImage().getScaledInstance(110, 25, Image.SCALE_SMOOTH)));
				figuraParafuso.setIcon(new ImageIcon(((ImageIcon)figuraTipoParafuso.getIcon()).getImage().getScaledInstance(110, 25, Image.SCALE_SMOOTH)));
				modoFalha.setIcon(new ImageIcon(((ImageIcon)figuraResultadoModoFalha.getIcon()).getImage().getScaledInstance(66, 140, Image.SCALE_SMOOTH)));
				rLogo.setIcon(new ImageIcon(((ImageIcon)logoPrograma.getIcon()).getImage().getScaledInstance(125, 80, Image.SCALE_SMOOTH)));

				jProgressBarStatus.setValue(3);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ButtonCalcularActionPerformed

	private void comboArruelasMadeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboArruelasMadeiraActionPerformed
		try {
			if(((Verificador)comboArruelasMadeira.getInputVerifier()).verify(comboArruelasMadeira)) {
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
	}//GEN-LAST:event_comboArruelasMadeiraActionPerformed

	private void testeParafusoNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TesteParafusoNaoActionPerformed
		try {
			relatoriofaxrk.setText("Não");
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_TesteParafusoNaoActionPerformed

	private void testeParafusoSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TesteParafusoSimActionPerformed
		try {
			JOptionPane
			        .showMessageDialog(this,
			                           "Será considerado no cálculo o efeito não linear de compressão provocado pela arruela devido a rotação\n do pino metálico e de tração do pino metálico, conhecido como efeito de corda (Fax,rk). ");

			m = true;
			relatoriofaxrk.setText("Sim");
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_TesteParafusoSimActionPerformed

	private void comboAcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboAcoActionPerformed
		try {
			if(((Verificador)comboAco.getInputVerifier()).verify(comboAco)) {
				valorFyk.setText(modeloLigacao.getConectores().getClasseAcoParafuso().getFyk().toString());
				valorFuk.setText(modeloLigacao.getConectores().getClasseAcoParafuso().getFuk().toString());
				relatorioClasseAco.setText(modeloLigacao.getConectores().getClasseAcoParafuso().getNome());
				relatoriofyk.setText(modeloLigacao.getConectores().getClasseAcoParafuso().getFyk().toString());
				relatoriofuk.setText(modeloLigacao.getConectores().getClasseAcoParafuso().getFuk().toString());
			}

			atualizabuttonCalcular();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboAcoActionPerformed

	private void comboTipoParafusoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ComboTipoParafusoFocusLost
		try {
			if(((Verificador)comboTipoParafuso.getInputVerifier()).verify(comboTipoParafuso)) {
				while(comboArruelasMadeira.getModel().getSize() > 1) {
					comboArruelasMadeira.removeItemAt(1);
				}
				while(comboArruelasAco.getModel().getSize() > 1) {
					comboArruelasAco.removeItemAt(1);
				}

				for(TipoArruela tipoArruela : TipoArruela.values()) {
					if(modeloLigacao.getConectores().getTipoParafuso().equals(tipoArruela.getTipoParafuso())) {
						comboArruelasMadeira.addItem(tipoArruela);
					}
				}
				for(TipoArruelaAco tipoArruela : TipoArruelaAco.values()) {
					if(modeloLigacao.getConectores().getTipoParafuso().equals(tipoArruela.getTipoParafuso())) {
						comboArruelasAco.addItem(tipoArruela);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboTipoParafusoFocusLost

	private void comboTipoParafusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTipoParafusoActionPerformed
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

	private void espessura1(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Espessura1
		try {
			if(espessura1.getText().length() > 4) {
				espessura1.setText("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_Espessura1

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

	private void btnCorteDuploAMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorteDuploAMAActionPerformed
		try {
			modeloLigacao = ModeloLigacao.CORTE_DUPLO_MADEIRA_ACO_MADEIRA;
			comboArruelasMadeira.setVisible(false);
			comboArruelasAco.setVisible(true);
			jProgressBarStatus.setString("Escolha a espécie da madeira para continuar.");
			coniferasButton.setEnabled(true);
			folhosasButton.setEnabled(true);

			relatorioSecaoCorte.setText("Ligação com corte duplo - Aço/Madeira/Aço");
			trocaFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/DuasSecoesParaAma.png"))); // NOI18N
			figuraSecoes.setIcon(new ImageIcon(((ImageIcon)btnCorteDuploAMA.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_btnCorteDuploAMAActionPerformed

	private void espessura1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Espessura1KeyTyped
		try {
			if((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') || evt.getKeyChar() == ',') {
				return;
			}
			evt.consume();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_Espessura1KeyTyped

	private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
		try {
			relatorio.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_formWindowActivated

	private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
		try {
			switch(jTabbedPane1.getSelectedIndex()){
				case 2:
					valorAngulo.setText("0");

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
					double t2 = modeloLigacao.getChapaAco().getEspessuraChapaAco().getEspessura();

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
								default:
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

	private void comboQuantParafusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboQuantParafusoActionPerformed
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

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_jButton1ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		try {
			TelaAco telaAco = new TelaAco();
			telaAco.setVisible(true);
			this.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_jButton2ActionPerformed

	private void comboTipoChapaAcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTipoChapaAcoActionPerformed
		try {
			if(((Verificador)comboTipoChapaAco.getInputVerifier()).verify(comboTipoChapaAco)) {
				valorChapaFyk.setText(modeloLigacao.getChapaAco().getClasseAco().getFyk().toString());
				valorChapaFuk.setText(modeloLigacao.getChapaAco().getClasseAco().getFuk().toString());
				relatorioChapaFyk.setText(modeloLigacao.getChapaAco().getClasseAco().getFyk().toString());
				relatorioChapaFuk.setText(modeloLigacao.getChapaAco().getClasseAco().getFuk().toString());
			}

			atualizabuttonCalcular();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboTipoChapaAcoActionPerformed

	private void comboChapaAcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboChapaAcoActionPerformed
		try {
			if(((Verificador)comboChapaAco.getInputVerifier()).verify(comboChapaAco)) {
				relatorioChapaEspessura.setText(modeloLigacao.getChapaAco().getEspessuraChapaAco().getNome());
				comboKmod1.setEnabled(true);
				comboKmod2.setEnabled(true);
				comboKmod3.setEnabled(true);
			} else {
				comboKmod1.setEnabled(false);
				comboKmod2.setEnabled(false);
				comboKmod3.setEnabled(false);
			}

			atualizabuttonCalcular();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}

	private void iniciarCalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarCalculoActionPerformed
		try {
			jTabbedPane1.setEnabledAt(1, true);
			jTabbedPane1.setSelectedComponent(secoesCorte);
			jProgressBarStatus.setString("Escolha a quantidade de seções de corte no parafuso.");
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_IniciarCalculoActionPerformed

	private void btnCorteDuploMAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorteDuploMAMActionPerformed
		try {
			modeloLigacao = ModeloLigacao.CORTE_DUPLO_MADEIRA_ACO_MADEIRA;
			comboArruelasMadeira.setVisible(true);
			comboArruelasAco.setVisible(false);
			jProgressBarStatus.setString("Escolha a espécie da madeira para continuar.");
			coniferasButton.setEnabled(true);
			folhosasButton.setEnabled(true);

			relatorioSecaoCorte.setText("Ligação com corte duplo - Madeira/Aço/Madeira");
			trocaFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/DuasSecoesParaMam.png"))); // NOI18N
			figuraSecoes.setIcon(new ImageIcon(((ImageIcon)btnCorteDuploMAM.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_btnCorteDuploMAMActionPerformed

	private void valorAngulo(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_valorAngulo
		try {
			if(valorAngulo.getText().length() > 4) {
				valorAngulo.setText("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_valorAngulo

	@SuppressWarnings("incomplete-switch")
	private void valorAnguloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_valorAnguloFocusLost
		try {
			modeloLigacao.getAngulo().setValorGrau(Double.parseDouble(valorAngulo.getText().replace(",", ".")));

			String figuraMadeira = "";

			switch(modeloLigacao){
				case CORTE_SIMPLES:
					switch(modeloLigacao.getAngulo().getTipoAngulo()){
						case PARALELO:
							figuraMadeira = "UmaSecaoParalelo.png";
							calculoForca01.setVisible(true);
							calculoForca901.setVisible(false);
							calculoForcaAlfa1.setVisible(false);
							break;
						case PERPENDICULAR:
							figuraMadeira = "UmaSecaoPerpendicular.png";
							calculoForca01.setVisible(false);
							calculoForca901.setVisible(true);
							calculoForcaAlfa1.setVisible(false);
							break;
						case INCLINADO:
							figuraMadeira = "UmaSecaoInclinado.png";
							calculoForca01.setVisible(false);
							calculoForca901.setVisible(false);
							calculoForcaAlfa1.setVisible(true);
							break;
					}
					break;
				case CORTE_DUPLO_ACO_MADEIRA_ACO:
					switch(modeloLigacao.getAngulo().getTipoAngulo()){
						case PARALELO:
							figuraMadeira = "DuasSecoesParaAma.png";
							calculoForca01.setVisible(true);
							calculoForca901.setVisible(false);
							calculoForcaAlfa1.setVisible(false);
							break;
						case PERPENDICULAR:
							figuraMadeira = "DuasSecoesPerpAma.png";
							calculoForca01.setVisible(false);
							calculoForca901.setVisible(true);
							calculoForcaAlfa1.setVisible(false);
							break;
						case INCLINADO:
							figuraMadeira = "DuasSecoesIncliAma.png";
							calculoForca01.setVisible(false);
							calculoForca901.setVisible(false);
							calculoForcaAlfa1.setVisible(true);
							break;
					}
					break;
				case CORTE_DUPLO_MADEIRA_ACO_MADEIRA:
					switch(modeloLigacao.getAngulo().getTipoAngulo()){
						case PARALELO:
							figuraMadeira = "DuasSecoesParaMam.png";
							calculoForca01.setVisible(true);
							calculoForca901.setVisible(false);
							calculoForcaAlfa1.setVisible(false);
							break;
						case PERPENDICULAR:
							figuraMadeira = "DuasSecoesPerpMam.png";
							calculoForca01.setVisible(false);
							calculoForca901.setVisible(true);
							calculoForcaAlfa1.setVisible(false);
							break;
						case INCLINADO:
							figuraMadeira = "DuasSecoesIncliMam.png";
							calculoForca01.setVisible(false);
							calculoForca901.setVisible(false);
							calculoForcaAlfa1.setVisible(true);
							break;
					}
					break;
			}

			trocaFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/" + figuraMadeira))); // NOI18N
			trocaFigura.doClick();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_valorAnguloFocusLost

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
		try {
			this.dispose();
			TelaInicial telainicial = new TelaInicial();
			telainicial.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_jButton4ActionPerformed

	private void valorAnguloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorAnguloKeyTyped
		try {
			if((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') || evt.getKeyChar() == ',') {
				return;
			}
			evt.consume();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_valorAnguloKeyTyped

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
		try {
			this.dispose();
			TelaInicial telainicial = new TelaInicial();
			telainicial.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_jButton3ActionPerformed

	private void comboKmod2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod2ActionPerformed
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
				textKmod2.setText(modeloLigacao.getKmod2().getValor() + "");
				relatoriokmod2.setText(modeloLigacao.getKmod2().getValor() + "");
			}

			atualizanextElementosLigacao();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboKmod2ActionPerformed

	private void comboKmod3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod3ActionPerformed
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
				textKmod3.setText(modeloLigacao.getKmod3().getValor() + "");
				relatoriokmod3.setText(modeloLigacao.getKmod3().getValor() + "");
			}

			atualizanextElementosLigacao();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboKmod3ActionPerformed

	private void comboKmod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboKmod1ActionPerformed
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
				textKmod1.setText(modeloLigacao.getKmod1().getValor() + "");
				relatorioKmod1.setText(modeloLigacao.getKmod1().getValor() + "");
			}

			atualizanextElementosLigacao();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_ComboKmod1ActionPerformed

	private void comboArruelasAcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboArruelasAcoActionPerformed
		try {
			if(((Verificador)comboArruelasAco.getInputVerifier()).verify(comboArruelasAco)) {
				valorD1Arruelas.setText("" + modeloLigacao.getConectores().getTipoArruelaAco().getD1());
				valorD2Arruelas.setText("" + modeloLigacao.getConectores().getTipoArruelaAco().getD2());
				relatorioD1.setText("" + modeloLigacao.getConectores().getTipoArruelaAco().getD1());
				relatorioD2.setText("" + modeloLigacao.getConectores().getTipoArruelaAco().getD2());

				String imagemArruela = "";
				if(modeloLigacao.getConectores().getTipoArruelaAco().getImagemArruela() == null) {
					imagemTipoArruela.setVisible(false);
				} else if(modeloLigacao.getConectores().getTipoArruelaAco().getImagemArruela() == 2.0) {
					imagemTipoArruela.setVisible(true);
					relatorioTipoArruela.setText("DIN 440 R - " + modeloLigacao.getConectores().getTipoParafuso().getNome());
					imagemArruela = "ImagemArruela440R2.png";
				}

				imagemTipoArruela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Imagens/" + imagemArruela))); // NOI18N
			}

			atualizabuttonCalcular();
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_comboArruelasAcoActionPerformed

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
			comboChapaAco.getInputVerifier().shouldYieldFocus(comboChapaAco);
			valorAngulo.getInputVerifier().shouldYieldFocus(valorAngulo);
			comboElem1.getInputVerifier().shouldYieldFocus(comboElem1);
			comboTipoChapaAco.getInputVerifier().shouldYieldFocus(comboTipoChapaAco);
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

	private void btnCorteSimplesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorteSimplesActionPerformed
		try {
			modeloLigacao = ModeloLigacao.CORTE_SIMPLES;
			comboArruelasMadeira.setVisible(true);
			comboArruelasAco.setVisible(false);
			jProgressBarStatus.setString("Escolha a espécie da madeira para continuar.");
			coniferasButton.setEnabled(true);
			folhosasButton.setEnabled(true);

			relatorioSecaoCorte.setText("Ligação com Corte Simples");
			trocaFigura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/UmaSecaoParalelo.png"))); // NOI18N
			figuraSecoes.setIcon(new ImageIcon(((ImageIcon)btnCorteSimples.getIcon()).getImage().getScaledInstance(55, 60, Image.SCALE_SMOOTH)));

			System.out.println("modeloLigacao =" + modeloLigacao);
			System.out.println("NumSecao =" + modeloLigacao.getNumSecao());
		} catch (Exception e) {
			e.printStackTrace();
			jProgressBarStatus.setString("Erro ao " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}//GEN-LAST:event_btnCorteSimplesActionPerformed

	//}   Esse colchetes ferrou com nossa vida por mais de 1 mês!! Descanse em paz, seu trabalho foi cumprido.
	/**/
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TelaAco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaAco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaAco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaAco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run() {
				new TelaAco().setVisible(true);
			}
		});
	}

	public ModeloLigacao getModeloLigacao() {
		return this.modeloLigacao;
	}

	private void atualizanextElementosLigacao() {
		next2.setEnabled(((Verificador)espessura1.getInputVerifier()).isVerified() && ((Verificador)valorAngulo.getInputVerifier()).isVerified()
		                 && ((Verificador)comboElem1.getInputVerifier()).isVerified() && ((Verificador)comboChapaAco.getInputVerifier()).isVerified()
		                 && ((Verificador)comboTipoChapaAco.getInputVerifier()).isVerified() && ((Verificador)comboKmod1.getInputVerifier()).isVerified()
		                 && ((Verificador)comboKmod2.getInputVerifier()).isVerified() && ((Verificador)comboKmod3.getInputVerifier()).isVerified());
	}

	private void atualizabuttonCalcular() {
		buttonCalcular.setEnabled(((Verificador)comboTipoParafuso.getInputVerifier()).isVerified() && ((Verificador)comboQuantParafuso.getInputVerifier()).isVerified()
		                          && (((Verificador)comboArruelasAco.getInputVerifier()).isVerified() || ((Verificador)comboArruelasMadeira.getInputVerifier()).isVerified())
		                          && ((Verificador)comboAco.getInputVerifier()).isVerified()

		);
	};

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel areaParafuso;
	private javax.swing.JLabel arruelas;
	private javax.swing.JButton buttonCalcular;
	private javax.swing.JLabel calculoForca01;
	private javax.swing.JLabel calculoForca901;
	private javax.swing.JLabel calculoForcaAlfa1;
	private javax.swing.JLabel classeAco;
	private javax.swing.JComboBox<ClasseAcoParafuso> comboAco;
	private javax.swing.JComboBox<TipoArruelaAco> comboArruelasAco;
	private javax.swing.JComboBox<TipoArruela> comboArruelasMadeira;
	private javax.swing.JComboBox<EspessuraChapaAco> comboChapaAco;
	private javax.swing.JComboBox<ClasseMadeira> comboElem1;
	private javax.swing.JComboBox<Kmod1> comboKmod1;
	private javax.swing.JComboBox<Kmod2> comboKmod2;
	private javax.swing.JComboBox<Kmod3> comboKmod3;
	private javax.swing.JComboBox<QuantidadeParafuso> comboQuantParafuso;
	private javax.swing.JComboBox<ClasseAco> comboTipoChapaAco;
	private javax.swing.JComboBox<TipoParafuso> comboTipoParafuso;
	private javax.swing.JTextArea consideracao1;
	private javax.swing.JLabel d1Arruelas;
	private javax.swing.JLabel d2Arruelas;
	private javax.swing.JLabel data;
	private javax.swing.JLabel densidade1;
	private javax.swing.JLabel densidade2;
	private javax.swing.JLabel diametro;
	private javax.swing.JLabel ec0m1;
	private javax.swing.JPanel elementosMadeira;
	private javax.swing.JPanel elementosMetalicos;
	private javax.swing.JTextField espessura1;
	private javax.swing.JLabel fcok1;
	private javax.swing.JLabel fcok2;
	private javax.swing.JLabel figuraArruela;
	private javax.swing.JLabel figuraParafuso;
	private javax.swing.JButton figuraResultadoModoFalha;
	private javax.swing.JLabel figuraSecoes;
	private javax.swing.JButton figuraTipoParafuso;
	private javax.swing.JLabel fv0k1;
	private javax.swing.JLabel fyk;
	private javax.swing.ButtonGroup groupSecoesCorte;
	private javax.swing.ButtonGroup groupTesteParafuso;
	private javax.swing.JLabel hora;
	private javax.swing.JLabel imagemTipoArruela;
	private javax.swing.JButton iniciarCalculo;
	private javax.swing.JPanel inicio;
	private javax.swing.JLabel labelEspessuraChapa;
	private javax.swing.JLabel labelInt2;
	private javax.swing.JLabel labelInter1;
	private javax.swing.JLabel labelModeloFalha;
	private javax.swing.JLabel labelrelatorioChapaAco;
	private javax.swing.JLabel labelResistenciaLigacao;
	private javax.swing.JLabel labelResultadoModeloFalha;
	private javax.swing.JLabel labelTipoChapaAco;
	private javax.swing.JLabel logoPrograma;
	private javax.swing.JLabel modoFalha;
	private javax.swing.JButton next;
	private javax.swing.JButton next2;
	private javax.swing.JButton next3;
	private javax.swing.JLabel numeroParafusos;
	private javax.swing.JLabel rLogo;
	private javax.swing.JPanel painelEspecieMadeira;
	private javax.swing.JPanel painelTipoMadeira;
	private javax.swing.JPanel relatorio;
	private javax.swing.JLabel relatorioAngulo;
	private javax.swing.JLabel relatorioChapaEspessura;
	private javax.swing.JLabel relatorioChapaFuk;
	private javax.swing.JLabel relatorioChapaFyk;
	private javax.swing.JLabel relatorioClasseAco;
	private javax.swing.JLabel relatorioClasseElem1;
	private javax.swing.JPanel relatorioCoeficientes;
	private javax.swing.JLabel relatorioD1;
	private javax.swing.JLabel relatorioD2;
	private javax.swing.JLabel relatorioDensidade1;
	private javax.swing.JLabel relatorioDiametro;
	private javax.swing.JLabel relatorioEc0m1;
	private javax.swing.JPanel relatorioElem1;
	private javax.swing.JLabel relatorioEspessura1;
	private javax.swing.JLabel relatorioFaxrk;
	private javax.swing.JLabel relatorioFc0d1;
	private javax.swing.JLabel relatorioFibras;
	private javax.swing.JPanel relatorioFinal;
	private javax.swing.JLabel relatorioFvk;
	private javax.swing.JLabel relatorioFvk1;
	private javax.swing.JLabel relatorioFvk2;
	private javax.swing.JLabel relatorioFvk3;
	private javax.swing.JLabel relatorioKmod1;
	private javax.swing.JLabel relatorioMyd;
	private javax.swing.JLabel relatorioNParafusos;
	private javax.swing.JPanel relatorioParafuso;
	private javax.swing.JLabel relatorioRd;
	private javax.swing.JLabel relatorioRd11;
	private javax.swing.JLabel relatorioRd13;
	private javax.swing.JLabel relatorioRd23;
	private javax.swing.JLabel relatorioRvk;
	private javax.swing.JLabel relatorioSecaoCorte;
	private javax.swing.JLabel relatorioTipoArruela;
	private javax.swing.JLabel relatorioTipoParafuso;
	private javax.swing.JLabel relatoriofaxrk;
	private javax.swing.JLabel relatorioFcok1;
	private javax.swing.JLabel relatoriofcok4;
	private javax.swing.JLabel relatoriofuk;
	private javax.swing.JLabel relatoriofv0k1;
	private javax.swing.JLabel relatoriofyk;
	private javax.swing.JLabel relatoriokmod2;
	private javax.swing.JLabel relatoriokmod3;
	private javax.swing.JPanel resultado;
	private javax.swing.JLabel resultadoFvk;
	private javax.swing.JLabel resultadoRd;
	private javax.swing.JLabel resultadoRvk;
	private javax.swing.JPanel secoesCorte;
	private javax.swing.JLabel tamanhoParafuso;
	private javax.swing.JLabel testeParafuso;
	private javax.swing.JRadioButton testeParafusoNao;
	private javax.swing.JRadioButton testeParafusoSim;
	private javax.swing.JLabel textKmod1;
	private javax.swing.JLabel textKmod2;
	private javax.swing.JLabel textKmod3;
	private javax.swing.JLabel tipoArruela;
	private javax.swing.JButton trocaFigura;
	private javax.swing.JLabel unD1Arruelas;
	private javax.swing.JLabel unD2Arruelas;
	private javax.swing.JLabel unDensidade1;
	private javax.swing.JLabel unDensidade2;
	private javax.swing.JLabel unEc0m1;
	private javax.swing.JLabel unFcok1;
	private javax.swing.JLabel unFcok2;
	private javax.swing.JLabel unFuk;
	private javax.swing.JLabel unFv0k1;
	private javax.swing.JLabel unFyk;
	private javax.swing.JTextField valorAngulo;
	private javax.swing.JLabel valorArea;
	private javax.swing.JLabel valorChapaFuk;
	private javax.swing.JLabel valorChapaFyk;
	private javax.swing.JLabel valorD1Arruelas;
	private javax.swing.JLabel valorD2Arruelas;
	private javax.swing.JLabel valorDensidade1;
	private javax.swing.JLabel valorDiametro;
	private javax.swing.JLabel valorEc0m1;
	private javax.swing.JLabel valorFc01;
	private javax.swing.JLabel valorFuk;
	private javax.swing.JLabel valorFvok1;
	private javax.swing.JLabel valorFyk;
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
	private javax.swing.JProgressBar jProgressBarStatus;
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
	private javax.swing.JToggleButton coniferasButton;
	private javax.swing.JToggleButton folhosasButton;
	private javax.swing.JToggleButton recompostaButton;
	private javax.swing.JToggleButton serradaButton;
	// End of variables declaration//GEN-END:variables

	public int calc() {
		double pi = Math.PI;
		Math.random();
		Math.sqrt(pi);

		return 0;
	}

}
