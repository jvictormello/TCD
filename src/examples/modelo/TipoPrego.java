/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.modelo;

/**
 *
 * @author Gabriela Mello
 */
public enum TipoPrego {
    pr17x21("17x21*",3.0,48.3), 
    pr17x24("17x24",3.0,55.2), 
    pr17x27("17x27",3.0,62.1),
    pr17x30("17x30",3.0,69.0),
    pr18x11("18x11", 3.4,25.3), 
    pr18x21("18x21",3.4,48.3), 
    pr18x24("18x24",3.4,55.2),
    pr18x27("18x27",3.4,62.1), 
    pr18x30("18x30",3.4,69.0), 
    pr18x33("18x33",3.4,75.9), 
    pr18x36("18x36",3.4,82.8), 
    pr19x15("19x15",3.9,34.5), 
    pr19x21("19x21",3.9,48.3), 
    pr19x27("19x27",3.9,62.1),
    pr19x30("19x30",3.9,69.0), 
    pr19x33("19x33",3.9,75.9), 
    pr19x36("19x36*",3.9,82.8), 
    pr19x39("19x39",3.9,89.7), 
    pr19x42("19x42",3.9,96.6), 
    pr20x30("20x30*",4.4,69.0), 
    pr20x33("20x33",4.4,75.9),
    pr20x36("20x36",4.4,82.8), 
    pr20x39("20x39",4.4,89.7), 
    pr20x42("20x42*",4.4,9.6), 
    pr20x48("20x48",4.4,110.4), 
    pr21x33("21x33",4.9,75.9), 
    pr21x36("21x36",4.9,82.8), 
    pr21x42("21x42*",4.9,96.6),
    pr21x45("21x45",4.9,103.5), 
    pr21x48("21x48",4.9,110.4), 
    pr21x54("21x54",4.9,124.2), 
    pr22x42("22x42*",5.4,96.6), 
    pr22x45("22x45",5.4,103.5), 
    pr22x48("22x48*",5.4,110.4), 
    pr22x54("22x54",5.4,124.2), 
    pr23x45("23x45",5.9,103.5), 
    pr23x54("23x54",5.9,124.2),
    pr23x60("23x60",5.9,138.0), 
    pr23x66("23x66",5.9,151.8), 
    pr24x60("24x60",6.4,138.0), 
    pr24x66("24x66",6.4,152.8), 
    pr25x72("25x72",7.0,165.6), 
    pr26x72("26x72",7.6,165.6), 
    pr26x78("26x78",7.6,179.4), 
    pr26x84("26x84",7.6,193.2);
    
    private String nome;
    private double DiametroPrego;
    private double ComprimentoPrego;
    
    private TipoPrego (String nome, double DiametroPrego, double ComprimentoPrego){
        this.DiametroPrego = DiametroPrego;
        this.ComprimentoPrego = ComprimentoPrego;
    
}
    public double getDiametro(){
        return this.DiametroPrego;}
    
    public double getComprimento(){
        return this.ComprimentoPrego;}

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return this.nome; 
    }
    
    
    
}
