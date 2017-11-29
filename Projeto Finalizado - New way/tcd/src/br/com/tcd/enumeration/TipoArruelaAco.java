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
public enum TipoArruelaAco {

	ESCOLHA_TIPO_ARRUELA("Escolha o Tipo de Arruela", null, null, null, null, null, null),
	M10("M10 (DIN 125)", TipoParafuso.M10, 10.5, 20.0, 1.9078, 2.0, 1.0),
	M12("M12 (DIN 125)", TipoParafuso.M12, 13.0, 24.0, 1.7391, 2.0, 1.0),
	M16("M16 (DIN 125)", TipoParafuso.M16, 17.0, 30.0, 1.5166, 2.0, 1.0),
	M20("M20 (DIN 125)", TipoParafuso.M20, 21.0, 37.0, 1.3868, 2.0, 1.0),
	M22("M22 (DIN 125)", TipoParafuso.M22, 23.0, 39.0, 1.3338, 2.0, 1.0),
	M24("M24 (DIN 125)", TipoParafuso.M24, 25.0, 44.0, 1.2963, 2.0, 1.0),
	M27("M27 (DIN 125)", TipoParafuso.M27, 28.0, 50.0, 1.25, 2.0, 1.0),
	M30("M30 (DIN 125)", TipoParafuso.M30, 31.0, 56.0, 1.2125, 2.0, 1.0),
	M33("M33 (DIN 125)", TipoParafuso.M33, 34.0, 60.0, 1.1805, 2.0, 1.0),
	M36("M36 (DIN 125)", TipoParafuso.M36, 37.0, 66.0, 1.1567, 2.0, 1.0);

	private String nome;
	private TipoParafuso tipoParafuso;
	private Double d1;
	private Double d2;
	private Double alfa;
	private Double imagemArruela;
	private Double arruela;

	private TipoArruelaAco(String nome, TipoParafuso tipoParafuso, Double d1, Double d2, Double alfa, Double imagemArruela, Double arruela) {
		this.nome = nome;
		this.tipoParafuso = tipoParafuso;
		this.d1 = d1;
		this.d2 = d2;
		this.alfa = alfa;
		this.imagemArruela = imagemArruela;
		this.arruela = arruela;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}

	public TipoParafuso getTipoParafuso() {
		return tipoParafuso;
	}

	public Double getD1() {
		return d1;
	}

	public Double getD2() {
		return d2;
	}

	public Double getAlfa() {
		return alfa;
	}

	public Double getImagemArruela() {
		return imagemArruela;
	}

	public Double getArruela() {
		return arruela;
	}

}
