package br.com.tcd.telas;

import java.io.File;

import br.com.tcd.util.Util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriela
 */
public class TelaApresentacao extends javax.swing.JFrame {

	private static final long serialVersionUID = -3988455620727465430L;

	/**
	 * Creates new form TelaApresentacao
	 */
	public TelaApresentacao() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		jLabel2 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jButtonIniciar = new javax.swing.JButton();
		jLabel6 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel17 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		jLabel19 = new javax.swing.JLabel();
		jLabel20 = new javax.swing.JLabel();
		jLabel21 = new javax.swing.JLabel();
		jLabel22 = new javax.swing.JLabel();
		jCheckBoxConcordo = new javax.swing.JCheckBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowOpened(java.awt.event.WindowEvent evt) {
				formWindowOpened(evt);
			}
		});

		jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
		jLabel2.setText("TCD - Timber Connections Design ");

		jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel5.setText("Desenvolvedores:  ");

		jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel7.setText("Marcos Vinicius Schwanz");

		jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel8.setText("Orientadores:         ");

		jLabel16.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jLabel16.setText("Versão Educacional 2.0");

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/ImagensDirecao/LOGOsketchup.png"))); // NOI18N
		jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

		jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Logo/LogoUtfpr.png"))); // NOI18N

		jLabel9.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
		jLabel9.setText("APOIO:");

		jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Logo/LogoGoverno.png"))); // NOI18N

		jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcd/Logo/LogoPet.png"))); // NOI18N

		jButtonIniciar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
		jButtonIniciar.setText("Iniciar");
		jButtonIniciar.setEnabled(false);
		jButtonIniciar.setPreferredSize(new java.awt.Dimension(97, 23));
		jButtonIniciar.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonIniciarActionPerformed(evt);
			}
		});

		jLabel6.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
		jLabel6.setText("Software desenvolvido para realizar cálculos de ligação do tipo madeira/madeira ou madeira/aço");

		jLabel13.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
		jLabel13.setText("com pinos metálicos segundo o modelo de cálculo da revisão da norma brasileira ABNT NBR");

		jLabel14.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
		jLabel14.setText("7190 (1997).");

		jLabel15.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
		jLabel15.setText("Para usar esta versão educacional, os usuários estão isentos de qualquer vínculo. Porém, os desen-");

		jLabel17.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
		jLabel17.setText("volvedores, parceiros ou qualquer outra instituição vinculada, não são responsáveis pela utilização");

		jLabel18.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
		jLabel18.setText("indevida do software ou seus resultados. Não havendo, portanto, qualquer responsabilidade sobre ");

		jLabel19.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
		jLabel19.setText("conclusões e resultados obtidos pelos usuários através do software.");

		jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel20.setText("Prof. Dr. Lucio Geronimo Valentin ");

		jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel21.setText("Gabriela Karina Morais de Mello");

		jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
		jLabel22.setText("Prof. Dr. Jorge Luís Nunes de Góes");

		jCheckBoxConcordo.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
		jCheckBoxConcordo.setText("Li e concordo com os termos.");
		jCheckBoxConcordo.setEnabled(false);
		jCheckBoxConcordo.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBoxConcordoActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		        .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                        .addGroup(layout.createSequentialGroup()
		                                .addGap(174,
		                                        174, 174)
		                                .addComponent(jCheckBoxConcordo))
		                        .addGroup(layout
		                                .createSequentialGroup().addGap(240,
		                                                                240, 240)
		                                .addComponent(jLabel9)))
		                .addGap(0,
		                        0, Short.MAX_VALUE))
		        .addGroup(layout
		                .createSequentialGroup().addGroup(layout.createParallelGroup(
		                                                                             javax.swing.GroupLayout.Alignment.LEADING)
		                        .addGroup(layout.createSequentialGroup()
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                        .addGroup(layout.createSequentialGroup()
		                                                .addGap(10, 10, 10).addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                .addGroup(layout
		                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                                        .addGroup(layout.createSequentialGroup()
		                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel2))
		                                                        .addGroup(layout.createSequentialGroup().addGap(95, 95, 95).addComponent(jLabel16))))
		                                        .addGroup(layout.createSequentialGroup().addGap(134, 134, 134)
		                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                                                        .addComponent(jButtonIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                        .addGroup(layout.createSequentialGroup()
		                                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
		                                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(11, 11, 11)))
		                                                .addGap(18, 18, 18).addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                                .addGap(0,
		                                        0, Short.MAX_VALUE))
		                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
		                                .createSequentialGroup().addGap(
		                                                                0, 26, Short.MAX_VALUE)
		                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
		                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 485,
		                                                              javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 485,
		                                                              javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 485,
		                                                              javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 485,
		                                                              javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING,
		                                                              javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 485,
		                                                              javax.swing.GroupLayout.PREFERRED_SIZE))
		                                        .addGroup(layout.createSequentialGroup()
		                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel5).addComponent(jLabel8)).addGap(18, 18, 18)
		                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel7).addComponent(jLabel21)
		                                                        .addComponent(jLabel22).addComponent(jLabel20))))
		                                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 485,
		                                                      javax.swing.GroupLayout.PREFERRED_SIZE))))
		                .addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
		        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                .addGroup(layout.createSequentialGroup().addGap(24, 24, 24).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel16))
		                .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
		        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel13)
		        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel14).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel15)
		        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel17).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel18)
		        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel19).addGap(17, 17, 17).addComponent(jCheckBoxConcordo).addGap(18, 18, 18)
		        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel21).addComponent(jLabel5))
		        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel7).addGap(14, 14, 14)
		        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel8).addComponent(jLabel22))
		        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel20)
		        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                .addGroup(layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(29, 29, 29))
		                .addGroup(layout.createSequentialGroup().addGap(30, 30, 30)
		                        .addComponent(jButtonIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
		                        .addComponent(jLabel9).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101,
		                                              javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(21, 21, 21)))))));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButtonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarActionPerformed
		Util.createAgreeFile();

		TelaInicial telainicial = new TelaInicial();
		telainicial.setVisible(true);
		this.dispose();
	}//GEN-LAST:event_jButtonIniciarActionPerformed

	private void jCheckBoxConcordoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxConcordoActionPerformed
		boolean concordo = jCheckBoxConcordo.isSelected();

		jButtonIniciar.setEnabled(concordo);
	}//GEN-LAST:event_jCheckBoxConcordoActionPerformed

	private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
		File file = Util.getAgreeFile();
		if(file.exists()) {
			jCheckBoxConcordo.setEnabled(false);
			jButtonIniciar.setVisible(false);
			jCheckBoxConcordo.setSelected(true);
		} else {
			jCheckBoxConcordo.setEnabled(true);
		}
	}//GEN-LAST:event_formWindowOpened

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		try {
			for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TelaApresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaApresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaApresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaApresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run() {
				File file = new File("Concordo.txt");
				if(file.exists()) {
					new TelaInicial().setVisible(true);
				} else {
					new TelaApresentacao().setVisible(true);
				}
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonIniciar;
	private javax.swing.JCheckBox jCheckBoxConcordo;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
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
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	// End of variables declaration//GEN-END:variables
}
