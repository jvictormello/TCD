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
public enum ClasseAcoParafuso {

	ESCOLHA_CLASSE_ACO("Escolha a Classe de Aço", 0, 0),
	CLASSE_4_6("ISO 898-1 - Classe 4.6", 235, 400),
	CLASSE_8_8("ISO 898-1 - Classe 8.8", 640, 800),
	CLASSE_10_9("ISO 898-1 - Classe 10.9", 900, 1000);

	private String nome;
	private Integer fyk;
	private Integer fuk;

	private ClasseAcoParafuso(String nome, Integer fyk, Integer fuk) {
		this.nome = nome;
		this.fyk = fyk;
		this.fuk = fuk;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getFyk() {
		return fyk;
	}

	public void setFyk(Integer fyk) {
		this.fyk = fyk;
	}

	public Integer getFuk() {
		return fuk;
	}

	public void setFuk(Integer fuk) {
		this.fuk = fuk;
	}

	@Override
	public String toString() {
		return this.nome;
	}

}
