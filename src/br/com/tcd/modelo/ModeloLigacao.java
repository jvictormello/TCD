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
 public enum ModeloLigacao {
    CORTE_SIMPLES(1),
    CORTE_DUPLO(2), 
    DUPLO_CORTE_SIMPLES(3);
    
    
    private ModeloLigacao(int numSecao){
        this.numSecao = numSecao;
        this.angulo = new Angulo();
    }
    
    private int numSecao;
    private Angulo angulo;
    private ElementoLigação elementoLigação1 = new ElementoLigação();
    private ElementoLigação elementoLigação2 = new ElementoLigação();
    private EspecieMadeira especieMadeira;
    private TipoMadeira tipoMadeira;
    private Conectores conectores;    
    private Kmod1 kmod1;
    private Kmod2 kmod2;
    private Kmod3 kmod3;
        
    public int getNumSecao(){
        return this.numSecao;}
    
    public void setAngulo(Angulo angulo){
        this.angulo = angulo;
    }
    public Angulo getAngulo(){
        return this.angulo;
    }

    public ElementoLigação getElementoLigação1() {
        return this.elementoLigação1;
    }

    public ElementoLigação getElementoLigação2() {
        return this.elementoLigação2;
    }

    public EspecieMadeira getEspecieMadeira() {
        return this.especieMadeira;
    }

    public void setEspecieMadeira(EspecieMadeira especieMadeira) {
        this.especieMadeira = especieMadeira;
    }

    public Kmod1 getKmod1() {
        return this.kmod1;
    }

    public void setKmod1(Kmod1 kmod1) {
        this.kmod1 = kmod1;
    }

    public Kmod2 getKmod2() {
        return this.kmod2;
    }

    public void setKmod2(Kmod2 kmod2) {
        this.kmod2 = kmod2;
    }

    public Kmod3 getKmod3() {
        return this.kmod3;
    }

    public void setKmod3(Kmod3 kmod3) {
        this.kmod3 = kmod3;
    }

    public TipoMadeira getTipoMadeira() {
        return this.tipoMadeira;
    }

    public void setTipoMadeira(TipoMadeira tipoMadeira) {
        this.tipoMadeira = tipoMadeira;
    }

    public Conectores getConectores() {
        return this.conectores;
    }

    public void setConectores(Conectores conectores) {
        this.conectores = conectores;
    }

    public void setAngulo(float value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public float getBeta(){
        return this.elementoLigação2.getClasseMadeira().getfc0k()/this.elementoLigação1.getClasseMadeira().getfc0k();
    }
    
    
    
}
