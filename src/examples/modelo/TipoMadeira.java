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
public enum TipoMadeira {
    SERRADA ("Serrada"), 
    RECOMPOSTA ("Recomposta"),
    OUTRO("Outro");
    
    private String nome;

    private TipoMadeira(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    
    
}
