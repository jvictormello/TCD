/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.controller;

/**
 *
 * @author MarcosVinicius
 */
public class UmaSecaoCorteFinaController {
	
	public double ValorFaxrk(double d, int fuk) { //Verificar como se utiliza esse parâmetro
		double valor1 = (0.75 * ((Math.PI * (Math.pow(d, 2))) / 4) * fuk);
		//print 'Faxuk (Resistência de tração do parafuso) = ', round(valor1,5)
		return valor1;
	}

	public double ValorMyk(int fuk, double d) {
		double myk = (0.3 * (fuk) * ((Math.pow(d, 2.6)))); //Multiplica-se o d por 10 para deixar o momento em N.mm
		//System.out.println("Md ="+ myd);
		return myk;
	}

	//Cálculo EQ 24 - Embutimento peça de madeira
	public double EmbPecaMadeira(double fek, double t1, double d) {
		double fvk1 = 0.4 * (fek) * t1 * d;
		return fvk1;
	}

	//Cálculo EQ 25 - Flexão do Pino na peça de madeira
	public double DeformPinoPecaMadeira(double fek, double d, double myk, double valorFaxrk) {
		double johansen1 = (Math.sqrt((2 * myk * fek * d)));

		if(valorFaxrk > 0) {
			if(valorFaxrk < johansen1) {
				return (1.15 * johansen1) + (valorFaxrk / 4);
			} else {
				return (1.15 * johansen1) + (johansen1 / 4);
			}
		}
		return(1.15 * johansen1);

	}
}
