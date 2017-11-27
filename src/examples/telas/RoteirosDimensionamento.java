/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.telas;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriela Mello
 */
public class RoteirosDimensionamento extends javax.swing.JFrame {

    /**
     * Creates new form RoteirosDimensionamento
     */
    public RoteirosDimensionamento() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        RoteiroMadParaButton = new javax.swing.JButton();
        RoteiroMadPreButton = new javax.swing.JButton();
        RoteiroMadAcoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Escolha o roteiro desejado:");

        RoteiroMadParaButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RoteiroMadParaButton.setText("Madeira/Madeira - Parafusada");
        RoteiroMadParaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoteiroMadParaButtonActionPerformed(evt);
            }
        });

        RoteiroMadPreButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RoteiroMadPreButton.setText("Madeira/Madeira - Pregada");
        RoteiroMadPreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoteiroMadPreButtonActionPerformed(evt);
            }
        });

        RoteiroMadAcoButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RoteiroMadAcoButton.setText("Madeira/Aço - Parafusada");
        RoteiroMadAcoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoteiroMadAcoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RoteiroMadParaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(RoteiroMadPreButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(RoteiroMadAcoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(RoteiroMadParaButton)
                .addGap(41, 41, 41)
                .addComponent(RoteiroMadPreButton)
                .addGap(37, 37, 37)
                .addComponent(RoteiroMadAcoButton)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RoteiroMadParaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoteiroMadParaButtonActionPerformed
        // TODO add your handling code here:
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
         
        Desktop desktop = Desktop.getDesktop();
                
        //let's try to open PDF file
        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath+"<-ESSE É O CAMINHO PARA O PROJETO");
        File file = new File(basePath+"\\ajuda/MadeiraMadeiraParafusos.pdf");
        if(file.exists()){ 
            
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(AjudaNovoAco.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("SUCESSO!");}
    }//GEN-LAST:event_RoteiroMadParaButtonActionPerformed

    private void RoteiroMadPreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoteiroMadPreButtonActionPerformed
        // TODO add your handling code here:
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
         
        Desktop desktop = Desktop.getDesktop();
                
        //let's try to open PDF file
        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath+"<-ESSE É O CAMINHO PARA O PROJETO");
        File file = new File(basePath+"\\ajuda/MadeiraMadeiraPregos.pdf");
        if(file.exists()){ 
            
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(AjudaNovoAco.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("SUCESSO!");}
    }//GEN-LAST:event_RoteiroMadPreButtonActionPerformed

    private void RoteiroMadAcoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoteiroMadAcoButtonActionPerformed
        // TODO add your handling code here:
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
         
        Desktop desktop = Desktop.getDesktop();
                
        //let's try to open PDF file
        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath+"<-ESSE É O CAMINHO PARA O PROJETO");
        File file = new File(basePath+"\\ajuda/MadeiraAco.pdf");
        if(file.exists()){ 
            
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(AjudaNovoAco.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("SUCESSO!");}
    }//GEN-LAST:event_RoteiroMadAcoButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RoteirosDimensionamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoteirosDimensionamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoteirosDimensionamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoteirosDimensionamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoteirosDimensionamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RoteiroMadAcoButton;
    private javax.swing.JButton RoteiroMadParaButton;
    private javax.swing.JButton RoteiroMadPreButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
