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
public enum ClasseAcoPrego {
    BaixoTeor("Aço de Baixo Teor de Carbono",600,600);
    
    private String nome;
    private int fyk;
    private int fuk;

    private ClasseAcoPrego(String nome, int fyk, int fuk) {
        this.nome = nome;
        this.fyk = fyk;
        this.fuk = fuk;
    }

    public String getNome() {
        return nome;
    }

    public static ClasseAcoPrego getBaixoTeor() {
        return BaixoTeor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFyk() {
        return fyk;
    }

    public void setFyk(int fyk) {
        this.fyk = fyk;
    }

    public int getFuk() {
        return fuk;
    }

    public void setFuk(int fuk) {
        this.fuk = fuk;
    }
    
    
}
