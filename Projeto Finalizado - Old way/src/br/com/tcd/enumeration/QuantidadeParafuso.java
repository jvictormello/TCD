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
public enum QuantidadeParafuso {

	ESCOLHA_QUANTIDADE_PARAFUSO("Escolha a quantidade de parafusos", null),
	DOIS("2", 2),
	QUATRO("4", 4),
	CINCO("5", 5),
	SEIS("6", 6),
	OITO("8", 8),
	DEZ("10", 10),
	QUATORZE("14", 14);

	private String nome;
	private Integer qtd;

	private QuantidadeParafuso(String nome, Integer qtd) {
		this.nome = nome;
		this.qtd = qtd;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}

	public Integer getQtd() {
		return qtd;
	}

}
