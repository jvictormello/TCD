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
public enum EspessuraChapaAco {

	ESCOLHA_ESPESSURA("Escolha a espessura", null),
	ESP1_4("1/4\"     (6,35mm)", 6.35),
	ESP5_16("5/16\"   (8,00 mm)", 8d),
	ESP3_8("3/8\"     (9,53 mm)", 9.53),
	ESP1_2("1/2\"     (12,70 mm)", 12.70),
	ESP5_8("5/8\"     (15,90 mm)", 15.90),
	ESP3_4("3/4\"     (19,10 mm)", 19.10),
	ESP7_8("7/8\"     (22,20 mm)", 22.20),
	ESP_1("1\"        (25,40 mm)", 25.40),
	ESP1_1_4("1 1/4\"  (31,80 mm)", 31.80),
	ESP1_1_2("1 1/2\"  (38,10 mm)", 38.10),
	ESP1_5_8("1 5/8\"  (41,30 mm)", 41.30),
	ESP1_3_4("1 3/4\"  (44,50 mm)", 44.50),
	ESP2("2\"        (50,80 mm)", 50.80),
	ESP2_1_4("2 1/4\"  (57,20 mm)", 57.20),
	ESP2_1_2("2 1/2\"  (63,50 mm)", 63.50),
	ESP3("3\"        (76,20 mm)", 76.20),
	ESP3_1_2("3 1/2\"  (88,90 mm)", 88.90),
	ESP4("4\"        (102,00 mm)", 102d);

	private String nome;
	private Double espessura;

	private EspessuraChapaAco(String nome, Double espessura) {
		this.nome = nome;
		this.espessura = espessura;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}

	public Double getEspessura() {
		return espessura;
	}

}
