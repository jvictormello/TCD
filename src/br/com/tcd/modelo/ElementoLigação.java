/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.modelo;

/**
 *
 * @author Gabriela Mello
 */
 public class ElementoLigação {
     private ClasseMadeira classeMadeira;
     private float espessura;
     private boolean forcaParalelaAFibra = false;
     

    public ClasseMadeira getClasseMadeira() {
        return classeMadeira;
    }

    public void setClasseMadeira(ClasseMadeira classeMadeira) {
        this.classeMadeira = classeMadeira;
    }

    public float getEspessura() {
        return espessura;
    }

    public void setEspessura(float espessura) {
        this.espessura = espessura;
    }

    public boolean isForcaParalelaAFibra() {
        return forcaParalelaAFibra;
    }

    public void setForcaParalelaAFibra(boolean forcaParaleraAFibra) {
        this.forcaParalelaAFibra = forcaParalelaAFibra;
    }
     
     
}
