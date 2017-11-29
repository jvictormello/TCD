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
public class UmaSecaoCorteGrossaController {

	public double ValorFaxrk(double d, int fuk) { //Verificar como se utiliza esse parâmetro
		double valor1 = (0.75 * ((Math.PI * (Math.pow(d, 2))) / 4) * fuk);

		return valor1;
	}

	public double ValorMyk(int fuk, double d) {
		double myk = (0.3 * (fuk) * ((Math.pow(d, 2.6)))); //Multiplica-se o d por 10 para deixar o momento em N.mm
		//System.out.println("Md ="+ myd);
		return myk;
	}

	//Cálculo EQ 26 - Embutimento na madeira
	public double EmbPecaMadeira(double fek, double d, double t1, double valorFaxrk, double myk) { //Multiplica-se por 10 para igualar as unidades e resultar em (N)
		double johansen2 = (fek * t1 * d) * ((Math.sqrt(2 + ((4 * myk) / (fek * (Math.pow(t1, 2)) * d)))) - 1);
		if(valorFaxrk > 0) {
			if(valorFaxrk < johansen2) {
				return johansen2 + (valorFaxrk / 4);
			} else {
				return johansen2 + (johansen2 / 4);
			}
		}
		return johansen2;

	}

	//Cálculo EQ 27 - Flexão no pino na peça de madeira
	public double DeformPino(double fek, double d, double myk, double valorFaxrk) {
		double johansen3 = (Math.sqrt(myk * fek * d));
		if(valorFaxrk > 0) {
			if(valorFaxrk < johansen3) {
				return (2.3 * johansen3) + (valorFaxrk / 4);
			} else {
				return (2.3 * johansen3) + (johansen3 / 4);
			}
		}
		return(2.3 * johansen3);

	}

	//Cálculo EQ 24 - Embutimento da peça de madeira
	public double EmbPecaMadeira5(double fek, double t1, double d) {
		double fvk5 = (fek) * t1 * d;
		return fvk5;
	}
}
