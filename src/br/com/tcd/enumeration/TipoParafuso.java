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
public enum TipoParafuso {

	ESCOLHA_TIPO_PARAFUSO("Escolha o Tipo de Parafuso", 0d, 0d),
	M10("M10", 1d, 58d),
	M12("M12", 1.2, 84.3),
	M16("M16", 1.6, 157d),
	M20("M20", 2d, 245d),
	M22("M22", 2.2, 303d),
	M24("M24", 2.4, 353d),
	M27("M27", 2.7, 459d),
	M30("M30", 3d, 561d),
	M33("M33", 3.3, 694d),
	M36("M36", 3.6, 817d);
	
	private String nome;
	private Double DiametroParafuso;
	private Double ComprimentoParafuso;

	private TipoParafuso(String nome, Double DiametroParafuso, Double ComprimentoParafuso) {
		this.nome = nome;
		this.DiametroParafuso = DiametroParafuso;
		this.ComprimentoParafuso = ComprimentoParafuso;

	}

	public Double getDiametro() {
		return this.DiametroParafuso;
	}

	public Double getComprimento() {
		return this.ComprimentoParafuso;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}

}
