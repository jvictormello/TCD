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
public enum EspecieMadeira {
    FOLHOSA("Folhosa"), 
    CONIFERA("Conífera"),
    OUTRO("Outro");
    
    private String nome;

    private EspecieMadeira(String nome) {
        this.nome = nome;
    }
       
    public String getNome() {
        return nome;
    }
    
    
    
}
