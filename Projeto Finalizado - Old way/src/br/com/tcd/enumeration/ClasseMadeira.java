/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.enumeration;

/**
 *
 * @author Gabriela Mello
 */
public enum ClasseMadeira {
	ESCOLHA_CLASSE_MADEIRA("Escolha a Classe da Madeira",0,0,0,0),
    C20("C-20",20,500,4,3500),
    C25("C-25",25,550,5,8500),
    C30("C-30",30,600,6,14500),
    D20("D-20",20,650,4,9500),
    D30("D-30",30,800,5,14500),
    D40("D-40",40,950,6,19500),
    D50("D-50",50,970,7,22000),
    D60("D-60",60,1000,8,24500);
    
    private String nome;
    private int fc0k;
    private int Densidade;
    private int fv0k;
    private int ec0m;
    
    private ClasseMadeira (String nome,int fc0k, int Densidade, int fv0k, int ec0m){
        this.nome = nome;
        this.fc0k = fc0k;
        this.Densidade = Densidade;
        this.fv0k = fv0k;
        this.ec0m = ec0m;
        
}
    
    public String getNome() {
        return nome;
    }
    
    public int getfc0k(){
        return this.fc0k;}
   
    public int getDensidade(){
        return this.Densidade;}
    
    public int getfv0k(){
        return this.fv0k;}
    
    public int getec0m(){
        return this.ec0m;}

    @Override
    public String toString() {
        return this.nome;
    }
    
    
    
}
