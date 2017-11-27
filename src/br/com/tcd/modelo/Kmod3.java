/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.modelo;

import br.com.tcd.modelo.EspecieMadeira;

/**
 *
 * @author Gabriela Mello
 */
public enum Kmod3 {

	ESCOLHA_KMOD("Escolha o Kmod 3", 0d, null),
    SE_V("SE - Classificação Visual",0.9, EspecieMadeira.FOLHOSA),
    S1_V("S1 - Classificação Visual",0.85, EspecieMadeira.FOLHOSA), 
    S2_V("S2 - Classificação Visual", 0.80, EspecieMadeira.FOLHOSA), 
    S3_V("S3 - Classificação Visual", 0.75, EspecieMadeira.FOLHOSA), 
    SE_VM("SE - Classificação Visual e Mecânica", 1.0, EspecieMadeira.FOLHOSA), 
    S1_VM("S1 - Classificação Visual e Mecânica", 0.95, EspecieMadeira.FOLHOSA), 
    S2_VM("S2 - Classificação Visual e Mecânica", 0.90, EspecieMadeira.FOLHOSA), 
    S3_VM("S3 - Classificação Visual e Mecânica", 0.85, EspecieMadeira.FOLHOSA),
    NC("Não Classificada", 0.70, EspecieMadeira.FOLHOSA),
    SED_V("SE - Classificação Visual", 0.7, EspecieMadeira.CONIFERA), 
    S1D_V("S1 - Classificação Visual", 0.60, EspecieMadeira.CONIFERA), 
    S2D_V("S2 - Classificação Visual", 0.50, EspecieMadeira.CONIFERA), 
    S3D_V("S3 - Classificação Visual", 0.40, EspecieMadeira.CONIFERA),
    SEND_V("SE - Classificação Visual", 0.60, EspecieMadeira.CONIFERA), 
    S1ND_V("S1 - Classificação Visual", 0.50, EspecieMadeira.CONIFERA), 
    S2ND_V("S2 - Classificação Visual",0.40, EspecieMadeira.CONIFERA), 
    S3ND_V("S3 - Classificação Visual", 0.30, EspecieMadeira.CONIFERA),
    SED_VM("SE - Classificação Visual e Mecânica", 0.9, EspecieMadeira.CONIFERA), 
    S1D_VM("S1 - Classificação Visual e Mecânica", 0.80, EspecieMadeira.CONIFERA), 
    S2D_VM("S2 - Classificação Visual e Mecânica", 0.70, EspecieMadeira.CONIFERA), 
    S3D_VM("S3 - Classificação Visual e Mecânica", 0.60, EspecieMadeira.CONIFERA),
    SEND_VM("SE - Classificação Visual e Mecânica", 0.80, EspecieMadeira.CONIFERA), 
    S1ND_VM("S1 - Classificação Visual e Mecânica", 0.70, EspecieMadeira.CONIFERA), 
    S2ND_VM("S2 - Classificação Visual e Mecânica", 0.60, EspecieMadeira.CONIFERA), 
    S3ND_VM("S3 - Classificação Visual e Mecânica", 0.50, EspecieMadeira.CONIFERA),
    OUTRO("Outro", 0.0, EspecieMadeira.OUTRO);
    
    private String nome;
    private double valor;
    private EspecieMadeira especieMadeira;

    private Kmod3(String nome, double kmod3, EspecieMadeira especieMadeira) {
        this.nome = nome;
        this.valor = kmod3 ;
        this.especieMadeira = especieMadeira;
    }
     
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getValor(){
        return valor;}
    
    public void setValor(double kmod3) {
        this.valor = kmod3;
    }

    public EspecieMadeira getEspecieMadeira() {
        return especieMadeira;
    }

    public void setEspecieMadeira(EspecieMadeira especieMadeira) {
        this.especieMadeira = especieMadeira;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
    
}
