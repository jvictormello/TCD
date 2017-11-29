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
public enum TipoArruela {

	ESCOLHA_TIPO_ARRUELA("Escolha o Tipo de Arruela", null, null, null, null, null, null),
	M10_440R("M10 (DIN 440 R)", TipoParafuso.M10, 11.0, 34.0, 1.9078, 2.0, 1.0),
	M10_440V("M10 (DIN 440 V)", TipoParafuso.M10, 11.0, 34.0, 1.9078, 3.0, 2.0),
	M10_436("M10 (DIN 436)", TipoParafuso.M10, 11.0, 30.0, 1.9078, 1.0, 3.0),
	M12_440R("M12 (DIN 440 R)", TipoParafuso.M12, 13.5, 44.0, 1.7391, 2.0, 1.0),
	M12_440V("M12 (DIN 440 V)", TipoParafuso.M12, 13.5, 44.0, 1.7391, 3.0, 2.0),
	M12_436("M12 (DIN 436)", TipoParafuso.M12, 13.5, 40.0, 1.7391, 1.0, 3.0),
	M16_440R("M16 (DIN 440 R)", TipoParafuso.M16, 17.5, 56.0, 1.5166, 2.0, 1.0),
	M16_440V("M16 (DIN 440 V)", TipoParafuso.M16, 17.5, 56.0, 1.5166, 3.0, 2.0),
	M16_436("M16 (DIN 436)", TipoParafuso.M16, 17.5, 50.0, 1.5166, 1.0, 3.0),
	M20_440R("M20 (DIN 440 R)", TipoParafuso.M20, 22.0, 72.0, 1.3868, 2.0, 1.0),
	M20_440V("M20 (DIN 440 V)", TipoParafuso.M20, 22.0, 72.0, 1.3868, 3.0, 2.0),
	M20_436("M20 (DIN 436)", TipoParafuso.M20, 22.0, 60.0, 1.3868, 1.0, 3.0),
	M22_440R("M22 (DIN 440 R)", TipoParafuso.M22, 24.0, 80.0, 1.3338, 2.0, 1.0),
	M22_440V("M22 (DIN 440 V)", TipoParafuso.M22, 24.0, 80.0, 1.3338, 3.0, 2.0),
	M22_436("M22 (DIN 436)", TipoParafuso.M22, 24.0, 70.0, 1.3338, 1.0, 3.0),
	M24_440R("M24 (DIN 440 R)", TipoParafuso.M24, 26.0, 85.0, 1.2963, 2.0, 1.0),
	M24_436("M24 (DIN 436)", TipoParafuso.M24, 26.0, 80.0, 1.2963, 1.0, 3.0),
	M27_440R("M27 (DIN 440 R)", TipoParafuso.M27, 30.0, 98.0, 1.25, 2.0, 1.0),
	M27_436("M27 (DIN 436)", TipoParafuso.M27, 30.0, 90.0, 1.25, 1.0, 3.0),
	M30_440R("M30 (DIN 440 R)", TipoParafuso.M30, 33.0, 105.0, 1.2125, 2.0, 1.0),
	M30_436("M30 (DIN 436)", TipoParafuso.M30, 33.0, 95.0, 1.2125, 1.0, 3.0),
	M33_440R("M33 (DIN 440 R)", TipoParafuso.M33, 36.0, 112.0, 1.1805, 2.0, 1.0),
	M36_440R("M36 (DIN 440 R)", TipoParafuso.M36, 39.0, 125.0, 1.1567, 2.0, 1.0);

	private String nome;
	private TipoParafuso tipoParafuso;
	private Double d1;
	private Double d2;
	private Double alfa;
	private Double imagemArruela;
	private Double arruela;

	private TipoArruela(String nome, TipoParafuso tipoParafuso, Double d1, Double d2, Double alfa, Double imagemArruela, Double arruela) {
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
