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
public enum Kmod1 {
    PERMANENTE_S("Permanente", 0.60, TipoMadeira.SERRADA),
    LONGA_DURACAO_S("Longa duração", 0.70, TipoMadeira.SERRADA), 
    MEDIA_DURACA0_S("Média duração", 0.80, TipoMadeira.SERRADA), 
    CURTA_DURACAO_S("Curta duração", 0.90, TipoMadeira.SERRADA), 
    INSTANTANEA_S("Instantânea", 1.1, TipoMadeira.SERRADA), 
    PERMANENTE_R("Permanente", 0.3, TipoMadeira.RECOMPOSTA), 
    LONGA_DURACAO_R("Longa duração", 0.45, TipoMadeira.RECOMPOSTA), 
    MEDIA_DURACA0_R("Média duração", 0.65, TipoMadeira.RECOMPOSTA), 
    CURTA_DURACAO_R("Curta duração", 0.90, TipoMadeira.RECOMPOSTA),
    INSTANTANEA_RECOMPOSTA("Intantânea", 1.1, TipoMadeira.RECOMPOSTA),
    OUTRO("Outro", 0.0, TipoMadeira.OUTRO);

    private String nome;
    private double valor;
    private TipoMadeira tipoMadeira;

    private Kmod1(String nome, double kmod1, TipoMadeira tipoMadeira) {
        this.nome = nome;
        this.valor = kmod1;
        this.tipoMadeira = tipoMadeira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getValor() {
        return this.valor;
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
