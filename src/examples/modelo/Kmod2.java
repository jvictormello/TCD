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
public enum Kmod2 {
    UMIDADE1_S("Teor de umidade 1", 1.0, TipoMadeira.SERRADA),
    UMIDADE2_S("Teor de umidade 2", 0.90, TipoMadeira.SERRADA),
    UMIDADE3_S("Teor de umidade 3", 0.80, TipoMadeira.SERRADA),
    UMIDADE4_S("Teor de umidade 4", 0.70, TipoMadeira.SERRADA),
    UMIDADE1_R("Teor de umidade 1", 1.0,TipoMadeira.RECOMPOSTA),
    UMIDADE2_R("Teor de umidade 2", 0.95, TipoMadeira.RECOMPOSTA),
    UMIDADE_R("Teor de umidade 3", 0.93, TipoMadeira.RECOMPOSTA),
    UMIDADE4_R("Teor de umidade 4", 0.90, TipoMadeira.RECOMPOSTA),
    OUTRO("Outro", 0.0, TipoMadeira.OUTRO);

    private String nome;
    private double valor;
    private TipoMadeira tipoMadeira;

    private Kmod2(String nome, double kmod2, TipoMadeira tipoMadeira) {
        this.nome = nome;
        this.valor = kmod2;
        this.tipoMadeira = tipoMadeira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoMadeira getTipoMadeira() {
        return tipoMadeira;
    }

    public void setTipoMadeira(TipoMadeira tipoMadeira) {
        this.tipoMadeira = tipoMadeira;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
    
    
    

}
