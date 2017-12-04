/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.controller;
//teste

/**
 *
 * @author MarcosVinicius
 */
public class DuasSecoesCorteInclinadaController {
	
	public double ValorFaxrk(double d, int fuk, double d2, double d1, double fe90d1, double arruela) { //Verificar como se utiliza esse parâmetro
		double valor1 = (0.75 * ((Math.PI * (Math.pow(d, 2))) / 4) * fuk);
		//print 'Faxuk (Resistência de tração do parafuso) = ', round(valor1,5)
		double valor2 = 0;
		if(arruela == 1.0) {
			valor2 = ((((Math.PI * (Math.pow(d2, 2)) / 4) - ((Math.PI * (Math.pow(d1, 2)) / 4))) * 3 * fe90d1));
			//print 'Faxuk (Resistência ao embutimento da arruela na madeira) = ', round(valor2,5)
		}
		if(arruela == 2.0) {
			valor2 = ((((Math.PI * (Math.pow(d2, 2)) / 4) - (Math.pow(d1, 2))) * 3 * fe90d1));

		}
		if(arruela == 3.0) {
			valor2 = (((Math.pow(d2, 2) - (Math.pow(d1, 2))) * 3 * fe90d1));

		}
		return (valor1 <= valor2) ? valor1 : valor2;
	}

	public double ValorFaxrkPrego(double d, int fuk) { //Verificar como se utiliza esse parâmetro
		double valorP = (0.75 * ((Math.PI * (Math.pow(d, 2))) / 4) * fuk);
		//print 'Faxuk (Resistência de tração do parafuso) = ', round(valor1,5)

		return valorP;
	}

	public double ValorMyd(int fuk, double d) {
		double myd = (0.3 * (fuk) * ((Math.pow(d, 2.6))));
		return myd;
	}

	//Cálculo EQ 30 - Embutimento menor peça de madeira
	public double EmbMenorPeca(double falfa1, double t1, double d) {
		double rd1 = (falfa1) * t1 * d;
		return rd1;
	}

	//Cálculo EQ 31 - Embutimento maior peça de madeira
	public double EmbMaiorPeca(double falfa1, double t2, double d, double beta) {
		double rd2 = 0.5 * falfa1 * t2 * d * beta;
		return rd2;

	}

	//Cálculo EQ 32 - Embutimento nas duas peças e flexao do pino na maior
	public double DeformPinoMaiorPeca(double falfa1, double t1, double d, double beta, double myd, double valorFaxrk, double valorFaxrkPrego) {
		double johansen4 = ((falfa1 * t1 * d) / (2 + beta)) * (Math.sqrt((2 * beta * (1 + beta)) + ((4 * beta * (2 + beta) * myd) / (falfa1 * d * (Math.pow((t1), 2))))) - beta);
		if(valorFaxrk > 0) {
			if((valorFaxrk / 4) < (0.25 * johansen4)) {
				return (1.05 * johansen4) + (valorFaxrk / 4);
			} else {
				return (1.05 * johansen4) + (0.25 * johansen4);
			}
		}

		if(valorFaxrkPrego > 0) {
			if((valorFaxrkPrego / 4) < (0.15 * johansen4)) {
				return johansen4 + (valorFaxrkPrego / 4);
			} else {
				return johansen4 + (0.15 * johansen4);
			}
		}
		return(1.05 * johansen4);

	}

	//Cálculo EQ 33 - Embutimento nas duas peças e flexao nas duas
	public double DeformPinoNasDuas(double beta, double myd, double falfa1, double d, double valorFaxrk, double valorFaxrkPrego) {
		double johansen6 = (Math.sqrt((2 * beta) / (1 + beta))) * (Math.sqrt(2 * myd * falfa1 * d));
		if(valorFaxrk > 0) {
			if((valorFaxrk / 4) < (0.25 * johansen6)) {
				return (1.15 * johansen6) + (valorFaxrk / 4);
			} else {
				return (1.15 * johansen6) + (0.25 * johansen6);
			}
		}

		if(valorFaxrkPrego > 0) {
			if((valorFaxrkPrego / 4) < (0.15 * johansen6)) {
				return johansen6 + (valorFaxrkPrego / 4);
			} else {
				return johansen6 + (0.15 * johansen6);
			}
		}
		return(1.15 * johansen6);

	}

}
