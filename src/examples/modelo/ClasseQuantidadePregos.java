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
public enum ClasseQuantidadePregos {
    dois("2",2),
    quatro("4",4),
    cinco("5",5),
    seis("6",6),
    oito("8",8),
    dez("10",10),
    doze("12",12),
    quatorze("14",14);
    
    private String nome;
    private int quantidadePregos;

    private ClasseQuantidadePregos(String nome, int quantidadePregos) {
        this.nome = nome;
        this.quantidadePregos = quantidadePregos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadePregos() {
        return quantidadePregos;
    }

    public void setQuantidadePregos(int quantidadePregos) {
        this.quantidadePregos = quantidadePregos;
    }
    
    
}
