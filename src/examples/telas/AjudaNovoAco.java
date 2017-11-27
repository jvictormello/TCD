/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.telas;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author MarcosVinicius
 */
public class AjudaNovoAco extends javax.swing.JFrame {

    /**
     * Creates new form AjudaNovo
     */
    public AjudaNovoAco() {
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

        ModeloEurocode = new javax.swing.JButton();
        ElementosMadeira = new javax.swing.JButton();
        ElementosMetalicos = new javax.swing.JButton();
        TccEscrito = new javax.swing.JButton();
        NormasUtilizadas = new javax.swing.JButton();
        Ajuda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajuda - TCD");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                FecharAjuda2(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                FecharAjuda(evt);
            }
        });

        ModeloEurocode.setText("Roteiro de Cálculo");
        ModeloEurocode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModeloEurocodeActionPerformed(evt);
            }
        });

        ElementosMadeira.setText("Elementos de Madeira");
        ElementosMadeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ElementosMadeiraActionPerformed(evt);
            }
        });

        ElementosMetalicos.setText("Elementos Metálicos");
        ElementosMetalicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ElementosMetalicosActionPerformed(evt);
            }
        });

        TccEscrito.setText("TCC completo");
        TccEscrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TccEscritoActionPerformed(evt);
            }
        });

        NormasUtilizadas.setText("Normas utilizadas");
        NormasUtilizadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NormasUtilizadasActionPerformed(evt);
            }
        });

        Ajuda.setText("Consulte o item desejado:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NormasUtilizadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TccEscrito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ElementosMetalicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ElementosMadeira, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ModeloEurocode, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(Ajuda)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(Ajuda)
                .addGap(18, 18, 18)
                .addComponent(ModeloEurocode)
                .addGap(18, 18, 18)
                .addComponent(ElementosMadeira)
                .addGap(18, 18, 18)
                .addComponent(ElementosMetalicos)
                .addGap(18, 18, 18)
                .addComponent(NormasUtilizadas)
                .addGap(18, 18, 18)
                .addComponent(TccEscrito)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FecharAjuda(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_FecharAjuda
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_FecharAjuda

    private void FecharAjuda2(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_FecharAjuda2
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_FecharAjuda2

    private void ModeloEurocodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModeloEurocodeActionPerformed
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
         
        Desktop desktop = Desktop.getDesktop();
                
        //let's try to open PDF file
        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath+"<-ESSE É O CAMINHO PARA O PROJETO");
        File file = new File(basePath+"\\ajuda/Madeira-Aço.pdf");
        if(file.exists()){ 
            
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(AjudaNovoAco.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("SUCESSO!");}
    }//GEN-LAST:event_ModeloEurocodeActionPerformed

    private void ElementosMadeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ElementosMadeiraActionPerformed
        // TODO add your handling code here:
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
         
        Desktop desktop = Desktop.getDesktop();
                
        //let's try to open PDF file
        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath+"<-ESSE É O CAMINHO PARA O PROJETO");
        File file = new File(basePath+"\\ajuda/ElementosMadeira.pdf");
        if(file.exists()){ 
            
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(AjudaNovoAco.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("SUCESSO!");}
    }//GEN-LAST:event_ElementosMadeiraActionPerformed

    private void ElementosMetalicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ElementosMetalicosActionPerformed
        // TODO add your handling code here:
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
         
        Desktop desktop = Desktop.getDesktop();
                
        //let's try to open PDF file
        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath+"<-ESSE É O CAMINHO PARA O PROJETO");
        File file = new File(basePath+"\\ajuda/ElementosMetalicos.pdf");
        if(file.exists()){ 
            
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(AjudaNovoAco.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("SUCESSO!");}
    }//GEN-LAST:event_ElementosMetalicosActionPerformed

    private void NormasUtilizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NormasUtilizadasActionPerformed
        // TODO add your handling code here:
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
         
        Desktop desktop = Desktop.getDesktop();
                
        //let's try to open PDF file
        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath+"<-ESSE É O CAMINHO PARA O PROJETO");
        File file = new File(basePath+"\\ajuda/NormasUtilizadas.pdf");
        if(file.exists()){ 
            
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(AjudaNovoAco.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("SUCESSO!");}
    }//GEN-LAST:event_NormasUtilizadasActionPerformed

    private void TccEscritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TccEscritoActionPerformed
        // TODO add your handling code here:
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
         
        Desktop desktop = Desktop.getDesktop();
                
        //let's try to open PDF file
        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath+"<-ESSE É O CAMINHO PARA O PROJETO");
        File file = new File(basePath+"\\ajuda/TCC.pdf");
        if(file.exists()){ 
            
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(AjudaNovoAco.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("SUCESSO!");}
    }//GEN-LAST:event_TccEscritoActionPerformed

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
            java.util.logging.Logger.getLogger(AjudaNovoAco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AjudaNovoAco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjudaNovoAco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjudaNovoAco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AjudaNovoAco().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ajuda;
    private javax.swing.JButton ElementosMadeira;
    private javax.swing.JButton ElementosMetalicos;
    private javax.swing.JButton ModeloEurocode;
    private javax.swing.JButton NormasUtilizadas;
    private javax.swing.JButton TccEscrito;
    // End of variables declaration//GEN-END:variables
}
