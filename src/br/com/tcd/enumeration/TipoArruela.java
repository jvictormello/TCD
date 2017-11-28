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

	ESCOLHA_TIPO_ARRUELA("Escolha o Tipo de Arruela", null),
	M10_440R("M10 (DIN 440 R)", TipoParafuso.M10),
	M10_440V("M10 (DIN 440 V)", TipoParafuso.M10),
	M10_436("M10 (DIN 436)", TipoParafuso.M10),
	M12_440R("M12 (DIN 440 R)", TipoParafuso.M12),
	M12_440V("M12 (DIN 440 V)", TipoParafuso.M12),
	M12_436("M12 (DIN 436)", TipoParafuso.M12),
	M16_440R("M16 (DIN 440 R)", TipoParafuso.M16),
	M16_440V("M16 (DIN 440 V)", TipoParafuso.M16),
	M16_436("M16 (DIN 436)", TipoParafuso.M16),
	M20_440R("M20 (DIN 440 R)", TipoParafuso.M20),
	M20_440V("M20 (DIN 440 V)", TipoParafuso.M20),
	M20_436("M20 (DIN 436)", TipoParafuso.M20),
	M22_440R("M22 (DIN 440 R)", TipoParafuso.M22),
	M22_440V("M22 (DIN 440 V)", TipoParafuso.M22),
	M22_436("M22 (DIN 436)", TipoParafuso.M22),
	M24_440R("M24 (DIN 440 R)", TipoParafuso.M24),
	M24_436("M24 (DIN 436)", TipoParafuso.M24),
	M27_440R("M27 (DIN 440 R)", TipoParafuso.M27),
	M27_436("M27 (DIN 436)", TipoParafuso.M27),
	M30_440R("M30 (DIN 440 R)", TipoParafuso.M30),
	M30_436("M30 (DIN 436)", TipoParafuso.M30),
	M33_440R("M33 (DIN 440 R)", TipoParafuso.M33),
	M36_440R("M36 (DIN 440 R)", TipoParafuso.M36);

	private String nome;
	private TipoParafuso tipoParafuso;

	private TipoArruela(String nome, TipoParafuso tipoParafuso) {
		this.nome = nome;
		this.tipoParafuso = tipoParafuso;

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

}
