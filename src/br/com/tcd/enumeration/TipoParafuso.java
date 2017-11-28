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
	M10("M10", 10.0, 58.0),
	M12("M12", 12.0, 84.3),
	M16("M16", 16.0, 157.0),
	M20("M20", 20.0, 245.0),
	M22("M22", 22.0, 303.0),
	M24("M24", 24.0, 353.0),
	M27("M27", 27.0, 459.0),
	M30("M30", 30.0, 561.0),
	M33("M33", 33.0, 694.0),
	M36("M36", 36.0, 817.0);
	
	private String nome;
	private double DiametroParafuso;
	private double ComprimentoParafuso;

	private TipoParafuso(String nome, double DiametroParafuso, double ComprimentoParafuso) {
		this.nome = nome;
		this.DiametroParafuso = DiametroParafuso;
		this.ComprimentoParafuso = ComprimentoParafuso;

	}

	public double getDiametro() {
		return this.DiametroParafuso;
	}

	public double getComprimento() {
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
