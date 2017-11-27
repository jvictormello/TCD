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
public class Angulo {
    
    public static enum TipoAngulo{
        PARALELO, PERPENDICULAR, INCLINADO;
    }
    
    private double valorGrau = 0;
    
    public void setValorRad(double valor){
        this.valorGrau = Math.toDegrees(valor);
    }
    
    public double getValorRad(){
        return Math.toRadians(valorGrau);
    }

    public void setValorGrau(double valor){
        this.valorGrau = valor%360;
        if (this.valorGrau<0){
            this.valorGrau = 360-this.valorGrau;//Converte ângulo negativo em positivos
          
        }
    }
    public double getValorGrau(){
        return valorGrau;
    }
    
    public TipoAngulo getTipoAngulo(){
        if (valorGrau == 0 || valorGrau == 180)return TipoAngulo.PARALELO;
        if (valorGrau == 90 || valorGrau == 270)return TipoAngulo.PERPENDICULAR;
        return TipoAngulo.INCLINADO;
    }
    
        
    
}
