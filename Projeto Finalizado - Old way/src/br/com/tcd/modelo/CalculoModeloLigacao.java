/*
O * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.modelo;

import br.com.tcd.controller.DuasSecoesChapaCentralController;
import br.com.tcd.controller.DuasSecoesChapaGrossaLateralController;
import br.com.tcd.controller.DuasSecoesCorteInclinadaController;
import br.com.tcd.controller.DuasSecoesCorteParaleloController;
import br.com.tcd.controller.DuasSecoesCortePerpendicularController;
import br.com.tcd.controller.UmaSecaoCorteFinaController;
import br.com.tcd.controller.UmaSecaoCorteGrossaController;
import br.com.tcd.controller.UmaSecaoCorteInclinadaController;
import br.com.tcd.controller.UmaSecaoCorteParaleloController;
import br.com.tcd.controller.UmaSecaoCortePerpendicularController;
import br.com.tcd.enumeration.ModeloLigacao;

/**
 *
 * @author Gabriela Mello
 */
public class CalculoModeloLigacao {

	private ModeloLigacao modeloLigacao;

	private double d = 0, c = 0, d1 = 0, tp = 0, d2 = 0, forcaaplicada = 0, fe0d1 = 0, fe0d2 = 0, fe90d1 = 0, fe90d2 = 0, beta = 0, valorFaxrk = 0, valorFaxrkPrego = 0, valorFaxrkFinal = 0, kmod1 = 0,
	        kmod2 = 0, kmod3 = 0, alfa = 0, Rdmin = 0, Rdlig = 0, rd1 = 0, rd2 = 0, rd3 = 0, rd4 = 0, rd5 = 0, rd6 = 0, Rvd = 0, npregos = 0, nparafusos = 0, fc0k1 = 0, fc0k2 = 0, fv0k1 = 0,
	        fv0k2 = 0, ec0m1 = 0, ec0m2 = 0, t1 = 0, t2 = 0, falfa1 = 0, falfa2 = 0, angulo = 0, arruela = 0, myd = 0, fvkmin = 0;

	private int fyd = 0, fyk = 0, nsecoes = 0, fuk = 0, npr = 0;

	private Boolean IncSim1, IncSim2, m;

	private String imagemFalha;
	private String tipo;
	private String espessuraChapa;
	private String relatorioChapaAco;

	public static CalculoModeloLigacao calculoModeloLigacao;

	public static CalculoModeloLigacao getInstance() {
		if(CalculoModeloLigacao.calculoModeloLigacao == null) {
			CalculoModeloLigacao.calculoModeloLigacao = new CalculoModeloLigacao();
		}
		return CalculoModeloLigacao.calculoModeloLigacao;
	}

	public void calcularModeloLigacaoPrego(ModeloLigacao modeloLigacao, Boolean IncSim1, Boolean IncSim2, Boolean m) {
		this.fc0k1 = modeloLigacao.getElementoLigacao1().getClasseMadeira().getfc0k();
		this.fc0k2 = modeloLigacao.getElementoLigacao2().getClasseMadeira().getfc0k();
		this.fv0k1 = modeloLigacao.getElementoLigacao1().getClasseMadeira().getfv0k();
		this.fv0k2 = modeloLigacao.getElementoLigacao2().getClasseMadeira().getfv0k();
		this.ec0m1 = modeloLigacao.getElementoLigacao1().getClasseMadeira().getec0m();
		this.ec0m2 = modeloLigacao.getElementoLigacao2().getClasseMadeira().getec0m();
		this.t1 = modeloLigacao.getElementoLigacao1().getEspessura();
		this.t2 = modeloLigacao.getElementoLigacao2().getEspessura();
		this.nsecoes = modeloLigacao.getNumSecao();
		this.kmod1 = modeloLigacao.getKmod1().getValor();
		this.kmod2 = modeloLigacao.getKmod2().getValor();
		this.kmod3 = modeloLigacao.getKmod3().getValor();
		this.npr = modeloLigacao.getConectores().getQuantidadePrego().getQuantidadePregos();

		if(npr > 8) {
			npregos = (8.0 + ((2.0 / 3.0) * (npr - 8.0)));
		} else {
			npregos = npr;
		}

		this.angulo = modeloLigacao.getAngulo().getValorRad();
		this.d = modeloLigacao.getConectores().getTipoPrego().getDiametro();
		this.c = modeloLigacao.getConectores().getTipoPrego().getComprimento();
		this.fyk = modeloLigacao.getConectores().getClasseAcoPrego().getFyk();
		this.fuk = modeloLigacao.getConectores().getClasseAcoPrego().getFuk();
		this.alfa = 2.5;
		this.beta = fc0k2 / fc0k1;
		this.fe0d1 = fc0k1;
		this.fe0d2 = fc0k2;
		this.fe90d1 = (0.25 * fe0d1 * alfa);
		this.fe90d2 = (0.25 * fe0d2 * alfa);

		// CÁLCULO PARA UMA SEÇÃO DE CORTE PARALELO
		if(((modeloLigacao == ModeloLigacao.CORTE_SIMPLES || modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES)) && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PARALELO) {

			UmaSecaoCorteParaleloController resultado = new UmaSecaoCorteParaleloController();

			// Valores de Faxrk
			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

			}

			if(valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;

			}

			if(valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;

			}

			// Recebendo os valores de Rd
			myd = resultado.ValorMyd(fuk, d);
			rd1 = resultado.EmbMenorPeca(fe0d1, t1, d);
			rd2 = resultado.EmbMaiorPeca(fe0d1, t2, d, beta);
			rd3 = resultado.EmbDuasPecas(fe0d1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
			rd4 = resultado.DeformPinoMaiorPeca(fe0d1, t1, d, beta, myd, valorFaxrk, valorFaxrkPrego);
			rd5 = resultado.DeformPinoMenorPeca(fe0d1, t2, d, beta, myd, valorFaxrk, valorFaxrkPrego);
			rd6 = resultado.DeformPinoNasDuas(beta, myd, fe0d1, d, valorFaxrk, valorFaxrkPrego);

			// Verifica o menor Rd
			Rdmin = rd1;

			if(rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;

			}
			if(rd3 < Rdmin) {
				Rdmin = rd3;

			}
			if(rd4 < Rdmin) {
				Rdmin = rd4;

			}
			if(rd5 < Rdmin) {
				Rdmin = rd5;

			}
			if(rd6 < Rdmin) {
				Rdmin = rd6;

			}

			// Valor de resistência da ligação
			Rdlig = npregos * nsecoes * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		}

		// CÁLCULO PARA UMA SEÇÃO INCLINADA
		if((modeloLigacao == ModeloLigacao.CORTE_SIMPLES || modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES) && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {

			UmaSecaoCorteInclinadaController resultado = new UmaSecaoCorteInclinadaController();

			// Valores de Faxrk
			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

			}

			if(valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;

			}

			if(valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;

			}
			// Valor utilizado de força
			if(IncSim1) {
				falfa2 = ((fe0d2 * fe90d2) / ((fe0d2 * (Math.pow(Math.sin(angulo), 2))) + (fe90d2 * (Math.pow(Math.cos(angulo), 2)))));
				falfa1 = fe0d1;

			}
			if(IncSim2) {
				falfa1 = ((fe0d1 * fe90d1) / ((fe0d1 * (Math.pow(Math.sin(angulo), 2))) + (fe90d1 * (Math.pow(Math.cos(angulo), 2)))));
				falfa2 = fe0d2;

			}
			beta = falfa2 / falfa1;
			// Recebe os Rd
			myd = resultado.ValorMyd(fuk, d);
			rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
			rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
			rd3 = resultado.EmbDuasPecas(falfa1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
			rd4 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, myd, valorFaxrk, valorFaxrkPrego);
			rd5 = resultado.DeformPinoMenorPeca(falfa1, t2, d, beta, myd, valorFaxrk, valorFaxrkPrego);
			rd6 = resultado.DeformPinoNasDuas(beta, myd, falfa1, d, valorFaxrk, valorFaxrkPrego);

			// Verifica o menor Rd
			Rdmin = rd1;

			tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
			imagemFalha = "1.1P.png";

			if(rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;
				tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
				imagemFalha = "1.2P.png";
			} else if(rd3 < Rdmin) {
				Rdmin = rd3;
				tipo = "Embutimento do pino metálico nas duas peças, devido ao giro do pino metálico.";
				imagemFalha = "1.3P.png";
			} else if(rd4 < Rdmin) {
				Rdmin = rd4;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2.";
				imagemFalha = "1.4P.png";
			} else if(rd5 < Rdmin) {
				Rdmin = rd5;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 1.";
				imagemFalha = "1.5P.png";
			} else if(rd6 < Rdmin) {
				Rdmin = rd6;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos.";
				imagemFalha = "1.6P.png";
			}

			// Valor de resistência da ligação
			Rdlig = npregos * nsecoes * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

		} else if((modeloLigacao == ModeloLigacao.CORTE_SIMPLES || modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES)
		          && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
			UmaSecaoCortePerpendicularController resultado = new UmaSecaoCortePerpendicularController();

			// Valores de Faxrk
			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

			}

			if(valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;

			}

			if(valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;

			}

			// Valor utilizado de força
			if(IncSim1) {
				falfa1 = fe0d1;
				falfa2 = fe90d2;

			}
			if(IncSim2) {
				falfa2 = fe0d2;
				falfa1 = fe90d1;

			}
			beta = falfa2 / falfa1;

			// Recebe os Rd
			myd = resultado.ValorMyd(fuk, d);
			rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
			rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
			rd3 = resultado.EmbDuasPecas(falfa1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
			rd4 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, myd, valorFaxrk, valorFaxrkPrego);
			rd5 = resultado.DeformPinoMenorPeca(falfa1, t2, d, beta, myd, valorFaxrk, valorFaxrkPrego);
			rd6 = resultado.DeformPinoNasDuas(beta, myd, falfa1, d, valorFaxrk, valorFaxrkPrego);

			// Verifica o menor Rd
			Rdmin = rd1;

			tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
			imagemFalha = "1.1P.png";

			if(rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;
				tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
				imagemFalha = "1.2P.png";
			} else if(rd3 < Rdmin) {
				Rdmin = rd3;
				tipo = "Embutimento do pino metálico nas duas peças, devido ao giro do pino metálico.";
				imagemFalha = "1.3P.png";
			} else if(rd4 < Rdmin) {
				Rdmin = rd4;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2.";
				imagemFalha = "1.4P.png";
			} else if(rd5 < Rdmin) {
				Rdmin = rd5;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 1.";
				imagemFalha = "1.5P.png";
			} else if(rd6 < Rdmin) {
				Rdmin = rd6;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos.";
				imagemFalha = "1.6P.png";
			}

			// Valor de resistência da ligação
			Rdlig = npregos * nsecoes * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		}

		// CÁLCULO DUAS SEÇÕES PARALELA
		if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PARALELO) {
			DuasSecoesCorteParaleloController resultado = new DuasSecoesCorteParaleloController();

			// Valores de Faxrk
			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

			}

			if(valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;

			}

			if(valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;

			}

			// Recebe os Rd
			myd = resultado.ValorMyd(fuk, d);
			rd1 = resultado.EmbMenorPeca(fe0d1, t1, d);
			rd2 = resultado.EmbMaiorPeca(fe0d1, t2, d, beta);
			rd3 = resultado.DeformPinoMaiorPeca(fe0d1, t1, d, beta, myd, valorFaxrk, valorFaxrkPrego);
			rd4 = resultado.DeformPinoNasDuas(beta, myd, fe0d1, d, valorFaxrk, valorFaxrkPrego);

			// Verifica o menor Rd
			Rdmin = rd1;

			tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
			imagemFalha = "2.1P.png";

			if(rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;
				tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
				imagemFalha = "2.2P.png";
			} else if(rd3 < Rdmin) {
				Rdmin = rd3;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2.";
				imagemFalha = "2.3P.png";
			} else if(rd4 < Rdmin) {
				Rdmin = rd4;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos.";
				imagemFalha = "2.4P.png";
			}

			// Valor de resistência da ligação
			Rdlig = npregos * nsecoes * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

		}

		// CÁLCULO DUAS SEÇÕES INCLINADA
		if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
			DuasSecoesCorteInclinadaController resultado = new DuasSecoesCorteInclinadaController();

			// Valores de Faxrk
			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

			}

			if(valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;

			}

			if(valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;

			}

			// Valor utilizado de força
			if(IncSim1) {
				falfa2 = ((fe0d2 * fe90d2) / ((fe0d2 * (Math.pow(Math.sin(angulo), 2))) + (fe90d2 * (Math.pow(Math.cos(angulo), 2)))));
				falfa1 = fe0d1;

			}
			if(IncSim2) {
				falfa1 = ((fe0d1 * fe90d1) / ((fe0d1 * (Math.pow(Math.sin(angulo), 2))) + (fe90d1 * (Math.pow(Math.cos(angulo), 2)))));
				falfa2 = fe0d2;

			}
			beta = falfa2 / falfa1;

			// Recebe os Rd
			myd = resultado.ValorMyd(fuk, d);
			rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
			rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
			rd3 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, myd, valorFaxrk, valorFaxrkPrego);
			rd4 = resultado.DeformPinoNasDuas(beta, myd, falfa1, d, valorFaxrk, valorFaxrkPrego);

			// Verifica o menor Rd
			Rdmin = rd1;

			tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
			imagemFalha = "2.1P.png";

			if(rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;
				tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
				imagemFalha = "2.2P.png";
			} else if(rd3 < Rdmin) {
				Rdmin = rd3;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2.";
				imagemFalha = "2.3P.png";
			} else if(rd4 < Rdmin) {
				Rdmin = rd4;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos.";
				imagemFalha = "2.4P.png";
			}

			// Valor de resistência da ligação
			Rdlig = npregos * nsecoes * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

		}

		// CÁLCULO DUAS SEÇÕES PERPENDICULAR
		if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
			DuasSecoesCortePerpendicularController resultado = new DuasSecoesCortePerpendicularController();

			// Valores de Faxrk
			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

			}

			if(valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;

			}

			if(valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;

			}

			// Valor utilizado de força
			if(IncSim1) {
				falfa1 = fe0d1;
				falfa2 = fe90d2;

			}
			if(IncSim2) {
				falfa2 = fe0d2;
				falfa1 = fe90d1;

			}
			beta = falfa2 / falfa1;

			// Recebe os Rd
			myd = resultado.ValorMyd(fuk, d);
			rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
			rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
			rd3 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, myd, valorFaxrk, valorFaxrkPrego);
			rd4 = resultado.DeformPinoNasDuas(beta, myd, falfa1, d, valorFaxrk, valorFaxrkPrego);

			// Verifica o menor Rd
			Rdmin = rd1;

			tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
			imagemFalha = "2.1P.png";

			if(rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;
				tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
				imagemFalha = "2.2P.png";
			} else if(rd3 < Rdmin) {
				Rdmin = rd3;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2.";
				imagemFalha = "2.3P.png";
			} else if(rd4 < Rdmin) {
				Rdmin = rd4;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos.";
				imagemFalha = "2.4P.png";
			}

			// Valor de resistência da ligação
			Rdlig = npregos * nsecoes * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		}
	}

	public void calcularModeloLigacaoParafuso(ModeloLigacao modeloLigacao, Boolean IncSim1, Boolean IncSim2, Boolean m) {
		this.fc0k1 = modeloLigacao.getElementoLigacao1().getClasseMadeira().getfc0k();
		this.fc0k2 = modeloLigacao.getElementoLigacao2().getClasseMadeira().getfc0k();
		this.fv0k1 = modeloLigacao.getElementoLigacao1().getClasseMadeira().getfv0k();
		this.fv0k2 = modeloLigacao.getElementoLigacao2().getClasseMadeira().getfv0k();
		this.ec0m1 = modeloLigacao.getElementoLigacao1().getClasseMadeira().getec0m();
		this.ec0m2 = modeloLigacao.getElementoLigacao2().getClasseMadeira().getec0m();
		this.t1 = modeloLigacao.getElementoLigacao1().getEspessura();
		this.t2 = modeloLigacao.getElementoLigacao2().getEspessura();
		this.nsecoes = modeloLigacao.getNumSecao();
		this.kmod1 = modeloLigacao.getKmod1().getValor();
		this.kmod2 = modeloLigacao.getKmod2().getValor();
		this.kmod3 = modeloLigacao.getKmod3().getValor();
		this.npr = modeloLigacao.getConectores().getQuantidadeParafuso().getQtd();

		if(npr > 8) {
			nparafusos = (8.0 + ((2.0 / 3.0) * (npr - 8.0)));
		} else {
			nparafusos = npr;
		}

		this.angulo = modeloLigacao.getAngulo().getValorRad();
		this.d = modeloLigacao.getConectores().getTipoParafuso().getDiametro();
		this.c = modeloLigacao.getConectores().getTipoParafuso().getComprimento();
		this.fyk = modeloLigacao.getConectores().getClasseAcoParafuso().getFyk();
		this.fuk = modeloLigacao.getConectores().getClasseAcoParafuso().getFuk();
		this.alfa = 2.5;
		this.beta = fc0k2 / fc0k1;
		this.fe0d1 = fc0k1;
		this.fe0d2 = fc0k2;
		this.fe90d1 = (0.25 * fe0d1 * alfa);
		this.fe90d2 = (0.25 * fe0d2 * alfa);

		//Entradas madeiras
		double falfa1 = 0.0;
		double falfa2 = 0.0;

		//Entradas Parafusos
		d1 = modeloLigacao.getConectores().getTipoArruela().getD1();
		d2 = modeloLigacao.getConectores().getTipoArruela().getD2();

		beta = fc0k2 / fc0k1;
		fe0d1 = fc0k1;
		fe0d2 = fc0k2;
		double fe90d1 = (0.25 * fe0d1 * alfa);
		double fe90d2 = (0.25 * fe0d2 * alfa);

		if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PARALELO) {
			UmaSecaoCorteParaleloController resultado = new UmaSecaoCorteParaleloController();//Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);
			}

			if(valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;
			}

			if(valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;
			}

			double Myd = resultado.ValorMyd(fuk, d);
			rd1 = resultado.EmbMenorPeca(fe0d1, t1, d);
			rd2 = resultado.EmbMaiorPeca(fe0d1, t2, d, beta);
			rd3 = resultado.EmbDuasPecas(fe0d1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
			rd4 = resultado.DeformPinoMaiorPeca(fe0d1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
			rd5 = resultado.DeformPinoMenorPeca(fe0d1, t2, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
			rd6 = resultado.DeformPinoNasDuas(beta, Myd, fe0d1, d, valorFaxrk, valorFaxrkPrego);

			Rdmin = rd1;
			tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
			imagemFalha = "1.1.png";
			if(rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;
				tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
				imagemFalha = "1.2.png";
			} else if(rd3 < Rdmin) {
				Rdmin = rd3;
				tipo = "Embutimento do pino metálico nos dois elementos de madeira";
				imagemFalha = "1.3.png";
			} else if(rd4 < Rdmin) {
				Rdmin = rd4;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2 de madeira";
				imagemFalha = "1.4.png";
			} else if(rd5 < Rdmin) {
				Rdmin = rd5;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 1 de madeira.";
				imagemFalha = "1.5.png";
			} else if(rd6 < Rdmin) {
				Rdmin = rd6;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos de madeira.";
				imagemFalha = "1.6.png";
			}

			//Calcular o valor total da ligação
			Rdlig = nparafusos * 1 * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		} else if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
			UmaSecaoCorteInclinadaController resultado = new UmaSecaoCorteInclinadaController();//Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);
			}

			if(valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;
			}

			if(valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;
			}

			//valor utilizado de força
			if(IncSim1) {
				falfa2 = ((fe0d2 * fe90d2) / ((fe0d2 * (Math.pow(Math.sin(angulo), 2))) + (fe90d2 * (Math.pow(Math.cos(angulo), 2)))));
				falfa1 = fe0d1;
			}
			if(IncSim2) {
				falfa1 = ((fe0d1 * fe90d1) / ((fe0d1 * (Math.pow(Math.sin(angulo), 2))) + (fe90d1 * (Math.pow(Math.cos(angulo), 2)))));
				falfa2 = fe0d2;
			}

			beta = falfa2 / falfa1;

			double Myd = resultado.ValorMyd(fuk, d);
			rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
			rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
			rd3 = resultado.EmbDuasPecas(falfa1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
			rd4 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
			rd5 = resultado.DeformPinoMenorPeca(falfa1, t2, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
			rd6 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);

			Rdmin = rd1;
			tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
			imagemFalha = "1.1.png";
			if(rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;
				tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
				imagemFalha = "1.2.png";
			} else if(rd3 < Rdmin) {
				Rdmin = rd3;
				tipo = "Embutimento do pino metálico nos dois elementos de madeira.";
				imagemFalha = "1.3.png";
			} else if(rd4 < Rdmin) {
				Rdmin = rd4;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2 de madeira.";
				imagemFalha = "1.4.png";
			} else if(rd5 < Rdmin) {
				Rdmin = rd5;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 1 de madeira.";
				imagemFalha = "1.5.png";
			} else if(rd6 < Rdmin) {
				Rdmin = rd6;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos de madeira.";
				imagemFalha = "1.6.png";
			}

			//Calcular o valor total da ligação
			Rdlig = nparafusos * 1 * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		} else if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
			UmaSecaoCortePerpendicularController resultado = new UmaSecaoCortePerpendicularController();//Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);
			}

			if(valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;
			}

			if(valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;
			}

			if(IncSim1) {
				falfa1 = fe0d1;
				falfa2 = fe90d2;
			}
			if(IncSim2) {
				falfa2 = fe0d2;
				falfa1 = fe90d1;
			}

			beta = falfa2 / falfa1;

			double Myd = resultado.ValorMyd(fuk, d);
			rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
			rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
			rd3 = resultado.EmbDuasPecas(falfa1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
			rd4 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
			rd5 = resultado.DeformPinoMenorPeca(falfa1, t2, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
			rd6 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);

			Rdmin = rd1;
			tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
			imagemFalha = "1.1.png";
			if(rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;
				tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
				imagemFalha = "1.2.png";
			} else if(rd3 < Rdmin) {
				Rdmin = rd3;
				tipo = "Embutimento do pino metálico nos dois elementos de madeira.";
				imagemFalha = "1.3.png";
			} else if(rd4 < Rdmin) {
				Rdmin = rd4;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2 de madeira.";
				imagemFalha = "1.4.png";
			} else if(rd5 < Rdmin) {
				Rdmin = rd5;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 1 de madeira.";
				imagemFalha = "1.5.png";
			} else if(rd6 < Rdmin) {
				Rdmin = rd6;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos de madeira.";
				imagemFalha = "1.6.png";
			}

			//Calcular o valor total da ligação
			Rdlig = nparafusos * 1 * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PARALELO) {
			DuasSecoesCorteParaleloController resultado = new DuasSecoesCorteParaleloController(); //Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);
			}

			if(valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;
			}

			if(valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;
			}

			double Myd = resultado.ValorMyd(fuk, d);
			rd1 = resultado.EmbMenorPeca(fe0d1, t1, d);
			rd2 = resultado.EmbMaiorPeca(fe0d1, t2, d, beta);
			rd3 = resultado.DeformPinoMaiorPeca(fe0d1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
			rd4 = resultado.DeformPinoNasDuas(beta, Myd, fe0d1, d, valorFaxrk, valorFaxrkPrego);

			Rdmin = rd1;
			tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
			imagemFalha = "2.1.png";
			if(rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;
				tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
				imagemFalha = "2.2.png";
			} else if(rd3 < Rdmin) {
				Rdmin = rd3;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2 de madeira.";
				imagemFalha = "2.3.png";
			} else if(rd4 < Rdmin) {
				Rdmin = rd4;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos de madeira.";
				imagemFalha = "2.4.png";
			}

			//Calcular o valor total da ligação
			Rdlig = nparafusos * 2 * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
			DuasSecoesCorteInclinadaController resultado = new DuasSecoesCorteInclinadaController(); //Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);
			}

			if(valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;
			}

			if(valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;
			}

			if(IncSim1) {
				falfa2 = ((fe0d2 * fe90d2) / ((fe0d2 * (Math.pow(Math.sin(angulo), 2))) + (fe90d2 * (Math.pow(Math.cos(angulo), 2)))));
				falfa1 = fe0d1;
			}
			if(IncSim2) {
				falfa1 = ((fe0d1 * fe90d1) / ((fe0d1 * (Math.pow(Math.sin(angulo), 2))) + (fe90d1 * (Math.pow(Math.cos(angulo), 2)))));
				falfa2 = fe0d2;
			}

			beta = falfa2 / falfa1;

			double Myd = resultado.ValorMyd(fuk, d);
			rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
			rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
			rd3 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
			rd4 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);

			Rdmin = rd1;
			tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
			imagemFalha = "2.1.png";
			if(rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;
				tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
				imagemFalha = "2.2.png";
			} else if(rd3 < Rdmin) {
				Rdmin = rd3;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2 de madeira.";
				imagemFalha = "2.3.png";
			} else if(rd4 < Rdmin) {
				Rdmin = rd4;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos de madeira.";
				imagemFalha = "2.4.png";
			}

			//Calcular o valor total da ligação
			Rdlig = nparafusos * 2 * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
			DuasSecoesCortePerpendicularController resultado = new DuasSecoesCortePerpendicularController(); //Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);
			}

			if(valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;
			}

			if(valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;
			}

			if(IncSim1) {
				falfa1 = fe0d1;
				falfa2 = fe90d2;
			}
			if(IncSim2) {
				falfa2 = fe0d2;
				falfa1 = fe90d1;
			}

			beta = falfa2 / falfa1;

			double Myd = resultado.ValorMyd(fuk, d);
			rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
			rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
			rd3 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
			rd4 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);

			Rdmin = rd1;
			tipo = "Embutimento do pino metálico no elemento 1 de madeira.";
			imagemFalha = "2.1.png";
			if(rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;
				tipo = "Embutimento do pino metálico no elemento 2 de madeira.";
				imagemFalha = "2.2.png";
			} else if(rd3 < Rdmin) {
				Rdmin = rd3;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica no elemento 2 de madeira.";
				imagemFalha = "2.3.png";
			} else if(rd4 < Rdmin) {
				Rdmin = rd4;
				tipo = "Flexão do pino metálico com ocorrência de rótula plástica nos dois elementos de madeira.";
				imagemFalha = "2.4.png";
			}

			//Calcular o valor total da ligação
			Rdlig = nparafusos * 2 * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		}
	}

	public void calcularModeloLigacaoAco(ModeloLigacao modeloLigacao, Boolean IncSim1, Boolean IncSim2, Boolean m) {
		this.fc0k1 = modeloLigacao.getElementoLigacao1().getClasseMadeira().getfc0k();
//		this.fc0k2 = modeloLigacao.getElementoLigacao2().getClasseMadeira().getfc0k();
		this.fv0k1 = modeloLigacao.getElementoLigacao1().getClasseMadeira().getfv0k();
//		this.fv0k2 = modeloLigacao.getElementoLigacao2().getClasseMadeira().getfv0k();
		this.ec0m1 = modeloLigacao.getElementoLigacao1().getClasseMadeira().getec0m();
//		this.ec0m2 = modeloLigacao.getElementoLigacao2().getClasseMadeira().getec0m();
		this.t1 = modeloLigacao.getElementoLigacao1().getEspessura();
		//		this.t2 = modeloLigacao.getElementoLigacao2().getEspessura();
		this.nsecoes = modeloLigacao.getNumSecao();
		this.kmod1 = modeloLigacao.getKmod1().getValor();
		this.kmod2 = modeloLigacao.getKmod2().getValor();
		this.kmod3 = modeloLigacao.getKmod3().getValor();
		this.npr = modeloLigacao.getConectores().getQuantidadeParafuso().getQtd();

		if(npr > 8) {
			nparafusos = (8.0 + ((2.0 / 3.0) * (npr - 8.0)));
		} else {
			nparafusos = npr;
		}

		this.angulo = modeloLigacao.getAngulo().getValorRad();
		this.d = modeloLigacao.getConectores().getTipoParafuso().getDiametro();
		this.c = modeloLigacao.getConectores().getTipoParafuso().getComprimento();
		this.fyk = modeloLigacao.getConectores().getClasseAcoParafuso().getFyk();
		this.fuk = modeloLigacao.getConectores().getClasseAcoParafuso().getFuk();
		this.alfa = 2.5;
//		this.beta = fc0k2 / fc0k1;
		this.fe0d1 = fc0k1;
//		this.fe0d2 = fc0k2;
		this.fe90d1 = (0.25 * fe0d1 * alfa);
//		this.fe90d2 = (0.25 * fe0d2 * alfa);

		if(npr > 8) {
			nparafusos = (8.0 + ((2.0 / 3.0) * (npr - 8.0)));
		} else {
			nparafusos = npr;
		}

		if(modeloLigacao.getConectores().getTipoArruela() != null) {
			d1 = modeloLigacao.getConectores().getTipoArruela().getD1();
			d2 = modeloLigacao.getConectores().getTipoArruela().getD2();
		} else {
			d1 = modeloLigacao.getConectores().getTipoArruelaAco().getD1();
			d2 = modeloLigacao.getConectores().getTipoArruelaAco().getD2();
		}

		double fek90 = (0.25 * fc0k1 * alfa);
		double fekalfa = ((fc0k1 * fek90) / ((fc0k1 * (Math.pow((Math.sin(angulo)), 2))) + ((fek90 * (Math.pow((Math.cos(angulo)), 2))))));
		double fek = 0.0;
		if(angulo == 0) {
			fek = fc0k1;
		}
		if(angulo == 90) {
			fek = fek90;
		}
		if(angulo > 0 && angulo < 90) {
			fek = fekalfa;
		}

		//Verificação da chapa grossa ou fina
		boolean fina = false;
		boolean grossa = false;
		boolean indefinida = false;
		if(t2 <= (0.5 * d)) {
			fina = true;
		} else if(t2 >= d) {
			indefinida = true;
		} else if(t2 > (0.5 * d) && t2 < d) {
			indefinida = true;
		}

		if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && fina) {
			UmaSecaoCorteFinaController resultado = new UmaSecaoCorteFinaController();//Pegar os valores das outras classes
			//Considerar ou não o valor de FaxRk
			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk);
			}

			double Myk = resultado.ValorMyk(fuk, d);
			fv0k1 = resultado.EmbPecaMadeira(fek, t1, d);
			fv0k2 = resultado.DeformPinoPecaMadeira(fek, d, Myk, valorFaxrk);

			//Verificar o menor valor de resistência
			fvkmin = fv0k1;

			tipo = "Embutimento do pino metálico no elemento de madeira.";
			imagemFalha = "UmaSecaoEmbutimentoMadeira.png";
			if(fv0k2 < fvkmin) {
				fvkmin = fv0k2;
				tipo = "Flexão do pino metálico com ocorrência de uma rótula plástica no elemento de madeira.";
				imagemFalha = "UmaSecaoFlexaoPinoMadeira.png";
			}

			espessuraChapa = "Ligação com chapa fina.";
			relatorioChapaAco = "Ligação com chapa fina.";

			//Calcular o valor total da ligação
			Rdlig = fvkmin * 1 * nparafusos;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		} else if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && grossa) {
			UmaSecaoCorteGrossaController resultado = new UmaSecaoCorteGrossaController();//Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk);
			}

			double Myk = resultado.ValorMyk(fuk, d);
			double fvk3 = resultado.EmbPecaMadeira(fek, d, t1, valorFaxrk, Myk);
			double fvk4 = resultado.DeformPino(fek, d, Myk, valorFaxrk);
			double fvk5 = resultado.EmbPecaMadeira5(fek, t1, d);

			//Verificar o menor valor de resistência
			fvkmin = fvk3;
			tipo = "Flexão do pino metálico com ocorrência de uma rótula plástica no elemento de madeira.";
			imagemFalha = "UmaSecaoFlexaoPinoAco.png";
			if(fvk4 < fvkmin) {
				fvkmin = fvk4;
				tipo = "Flexão do pino metálico com ocorrência de duas rótulas plásticas no elemento de madeira.";
				imagemFalha = "UmaSecaoDuasFlexoesPino.png";
			} else if(fvk5 < fvkmin) {
				fvkmin = fvk5;
				tipo = "Embutimento do pino metálico no elemento de madeira.";
				imagemFalha = "UmaSecaoEmbutimentoMadeiraGrossa.png";
			}

			espessuraChapa = "Ligação com chapa grossa.";
			relatorioChapaAco = "Ligação com chapa grossa.";

			Rdlig = fvkmin * 1 * nparafusos;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		}
		if(modeloLigacao == ModeloLigacao.CORTE_DUPLO_MADEIRA_ACO_MADEIRA) {
			DuasSecoesChapaCentralController resultado = new DuasSecoesChapaCentralController();//Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fek, arruela);
			}

			double Myk = resultado.ValorMyk(fuk, d);
			double fvk6 = resultado.EmbMadeira(fek, t1, d);
			double fvk7 = resultado.DeformUmaRotula(fek, d, t1, valorFaxrk, Myk);
			double fvk8 = resultado.DeformDuasRotulas(fek, d, Myk, valorFaxrk);

			fvkmin = fvk6;
			tipo = "Embutimento do pino metálico nos elementos de madeira.";
			imagemFalha = "DuasSecoesEmbutimentoMadeiraMam.png";
			if(fvk7 < fvkmin) {
				fvkmin = fvk7;
				tipo = "Flexão dos pinos metálicos com uma rótula plástica no elemento de aço.";
				imagemFalha = "DuasSecoesFlexaoPinoAcoMam.png";
			} else if(fvk8 < fvkmin) {
				fvkmin = fvk8;
				tipo = "Flexão dos pinos metálicos com duas rótulas plásticas nos elementos de madeira.";
				imagemFalha = "DuasSecoesFlexaoDuplaPinoMam.png";
			}

			//Calcular o valor total da ligação
			Rdlig = fvkmin * 2 * nparafusos;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO_ACO_MADEIRA_ACO && fina) {
			DuasSecoesChapaFinaLateralController resultado = new DuasSecoesChapaFinaLateralController(); //Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk);
			}

			double Myk = resultado.ValorMyk(fuk, d);
			double fvk9 = resultado.EmbMadeira(fek, t1, d);
			double fvk10 = resultado.DeformUmaRotula(fek, d, Myk, valorFaxrk);

			fvkmin = fvk9;
			tipo = "Embutimento do pino metálico no elemento de madeira.";
			imagemFalha = "DuasSecoesEmbutimentoMadeiraAma.png";
			if(fvk10 < fvkmin) {
				fvkmin = fvk10;
				tipo = "Flexão dos pinos metálicos com duas rótulas plásticas nos elementos de aço.";
				imagemFalha = "DuasSecoesFlexaoPinoAcoAma.png";
			}

			espessuraChapa = "Ligação com chapa fina";
			relatorioChapaAco = "Ligação com chapa fina";

			//Calcular o valor total da ligação
			Rdlig = fvkmin * 2 * nparafusos;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO_ACO_MADEIRA_ACO && grossa) {
			DuasSecoesChapaGrossaLateralController resultado = new DuasSecoesChapaGrossaLateralController(); //Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk);
			}

			double Myk = resultado.ValorMyk(fuk, d);
			double fvk11 = resultado.EmbMadeira(fek, t1, d);
			double fvk12 = resultado.DeformDuasRotulas(fek, d, Myk, valorFaxrk);

			//Verificar o menor valor de resistência
			fvkmin = fvk11;
			tipo = "Embutimento do pino metálico no elemento de madeira.";
			imagemFalha = "DuasSecoesEmbutimentoMadeiraAma.png";
			if(fvk12 < fvkmin) {
				fvkmin = fvk12;
				tipo = "Flexão dos pinos metálicos com duas rótulas plásticas no elemento de madeira.";
				imagemFalha = "DuasSecoesFlexaoPinosDuplaAma.png";
			}

			espessuraChapa = "Ligação com chapa grossa.";
			relatorioChapaAco = "Ligação com chapa grossa.";

			//Calcular o valor total da ligação
			Rdlig = fvkmin * 2 * nparafusos;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		} else if(modeloLigacao == ModeloLigacao.CORTE_SIMPLES && indefinida) {
			relatorioChapaAco = "Cálculo realizado com interpolação linear.";

			UmaSecaoCorteFinaController resultado = new UmaSecaoCorteFinaController();//Pegar os valores das outras classes
			//Considerar ou não o valor de FaxRk
			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk);
			}

			double Myk = resultado.ValorMyk(fuk, d);
			fv0k1 = resultado.EmbPecaMadeira(fek, t1, d);
			fv0k2 = resultado.DeformPinoPecaMadeira(fek, d, Myk, valorFaxrk);

			//Verificar o menor valor de resistência
			fvkmin = fv0k1;
			tipo = "Embutimento do pino metálico no elemento de madeira.";
			imagemFalha = "UmaSecaoEmbutimentoMadeira.png";
			if(fv0k2 < fvkmin) {
				fvkmin = fv0k2;
				tipo = "Flexão do pino metálico com ocorrência de uma rótula plástica no elemento de madeira.";
				imagemFalha = "UmaSecaoFlexaoPinoMadeira.png";
			}

			//CALCULO CHAPA GROSSA    
			UmaSecaoCorteGrossaController resultado2 = new UmaSecaoCorteGrossaController();//Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk);
			}

			double fvk3 = resultado2.EmbPecaMadeira(fek, d, t1, valorFaxrk, Myk);
			double fvk4 = resultado2.DeformPino(fek, d, Myk, valorFaxrk);
			double fvk5 = resultado2.EmbPecaMadeira5(fek, t1, d);

			//Verificar o menor valor de resistência
			double fvkmin2 = fvk3;
			String tipogrossa = "Flexão do pino metálico com ocorrência de uma rótula plástica no elemento de madeira.";
			String imagemFalha2 = "UmaSecaoFlexaoPinoAco.png";
			if(fvk4 < fvkmin2) {
				fvkmin2 = fvk4;
				tipogrossa = "Flexão do pino metálico com ocorrência de duas rótulas plásticas no elemento de madeira.";
				imagemFalha2 = "UmaSecaoDuasFlexoesPino.png";
			} else if(fvk5 < fvkmin2) {
				fvkmin2 = fvk5;
				tipogrossa = "Embutimento do pino metálico no elemento de madeira.";
				imagemFalha2 = "UmaSecaoEmbutimentoMadeiraGrossa.png";
			}

			//CALCULO DA INTERPOLAÇÃO
			double fvkMinimo = fvkmin;
			double fvkMaximo = fvkmin2;
			String tipoMinimo = tipo;
			String tipoMaximo = tipogrossa;
			String imagemMinima = imagemFalha;
			String imagemMaxima = imagemFalha2;

			if(fvkmin2 < fvkmin) {
				fvkMinimo = fvkmin2;
				fvkMaximo = fvkmin;
				tipoMinimo = tipogrossa;
				tipoMaximo = tipo;
				imagemMinima = imagemFalha2;
				imagemMaxima = imagemFalha;
			}

			double fvkfinal = 0.0;

			if(fvkmin == fvkmin2) {
				fvkfinal = fvkmin;
				tipo = tipoMinimo;
				imagemFalha = imagemMinima;
			}
			if(fvkmin != fvkmin2) {
				fvkfinal = ((((fvkMaximo - fvkMinimo) * (t2 - (0.5 * d))) / (0.5 * d))) + fvkMinimo;
				double fvkmedio = (fvkMaximo - fvkMinimo) / 2;
				if(fvkfinal < (fvkMinimo + fvkmedio)) {
					tipo = tipoMinimo;
					imagemFalha = imagemMinima;
				} else if(fvkfinal > (fvkMinimo + fvkmedio)) {
					tipo = tipoMaximo;
					imagemFalha = imagemMaxima;
				}
			}

			espessuraChapa = "Cálculo realizado com interpolação linear";
			relatorioChapaAco = "Cálculo realizado com interpolação linear";

			//Calcular o valor total da ligação

			fvkmin = fvkfinal;

			Rdlig = fvkmin * 1 * nparafusos;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		} else if(modeloLigacao == ModeloLigacao.CORTE_DUPLO_ACO_MADEIRA_ACO && indefinida) {
			relatorioChapaAco = "Cálculo realizado com interpolação linear";

			DuasSecoesChapaFinaLateralController resultado = new DuasSecoesChapaFinaLateralController(); //Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk);
			}

			//double FaxRk = resultado.ValorFaxrk(d, fyk, d2, d1, fe0d1);
			double Myk = resultado.ValorMyk(fuk, d);
			double fvk9 = resultado.EmbMadeira(fek, t1, d);
			double fvk10 = resultado.DeformUmaRotula(fek, d, Myk, valorFaxrk);

			//Verificar o menor valor de resistência
			fvkmin = fvk9;
			tipo = "Embutimento do pino metálico no elemento de madeira.";
			imagemFalha = "DuasSecoesEmbutimentoMadeiraAma.png";
			if(fvk10 < fvkmin) {
				fvkmin = fvk10;
				tipo = "Flexão dos pinos metálicos com uma rótula plástica no elemento de madeira.";
				imagemFalha = "DuasSecoesFlexaoPinoAcoAma.png";
			}

			//CHAPA GROSSA
			DuasSecoesChapaGrossaLateralController resultado2 = new DuasSecoesChapaGrossaLateralController(); //Pegar os valores das outras classes

			if(m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk);
			}

			double fvk11 = resultado2.EmbMadeira(fek, t1, d);
			double fvk12 = resultado2.DeformDuasRotulas(fek, d, Myk, valorFaxrk);

			//Verificar o menor valor de resistência
			double fvkmin2 = fvk11;
			String tipogrossa = "Embutimento do pino metálico no elemento de madeira.";
			String imagemFalha2 = "DuasSecoesEmbutimentoMadeiraAma.png";
			if(fvk12 < fvkmin2) {
				fvkmin2 = fvk12;
				tipogrossa = "Flexão dos pinos metálicos com duas rótulas plásticas no elemento de madeira.";
				imagemFalha2 = "DuasSecoesFlexaoPinosDuplaAma.png";
			}

			System.out.println("fvkmin =" + fvkmin2);
			System.out.println("Tipo =" + tipo);

			//CALCULO INTERPOLAÇÃO
			double fvkMinimo = fvkmin;
			double fvkMaximo = fvkmin2;
			String tipoMinimo = tipo;
			String tipoMaximo = tipogrossa;
			String imagemMinima = imagemFalha;
			String imagemMaxima = imagemFalha2;

			if(fvkmin2 < fvkmin) {
				fvkMinimo = fvkmin2;
				fvkMaximo = fvkmin;
				tipoMinimo = tipogrossa;
				tipoMaximo = tipo;
				imagemMinima = imagemFalha2;
				imagemMaxima = imagemFalha;
			}
			double fvkfinal = 0.0;

			if(fvkmin == fvkmin2) {
				fvkfinal = fvkmin;
				tipo = tipoMinimo;
				imagemFalha = imagemMinima;
			}
			if(fvkmin != fvkmin2) {
				fvkfinal = ((((fvkMaximo - fvkMinimo) * (t2 - (0.5 * d))) / (0.5 * d))) + fvkMinimo;
				double Fvkmedio = (fvkMaximo - fvkMinimo) / 2;
				if(fvkfinal < (fvkMinimo + Fvkmedio)) {
					tipo = tipoMinimo;
					imagemFalha = imagemMinima;
				}
				if(fvkfinal > (fvkMinimo + Fvkmedio)) {
					tipo = tipoMaximo;
					imagemFalha = imagemMaxima;
				}
			}

			espessuraChapa = "Cálculo realizado com interpolação linear";
			relatorioChapaAco = "Cálculo realizado com interpolação linear";

			fvkmin = fvkfinal;

			//Calcular o valor total da ligação
			Rdlig = fvkmin * 2 * nparafusos;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		}

	}

	public double getMyd() {
		return myd;
	}

	public void setMyd(double myd) {
		this.myd = myd;
	}

	public ModeloLigacao getModeloLigacao() {
		return modeloLigacao;
	}

	public void setModeloLigacao(ModeloLigacao modeloLigacao) {
		this.modeloLigacao = modeloLigacao;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getD1() {
		return d1;
	}

	public void setD1(double d1) {
		this.d1 = d1;
	}

	public double getTp() {
		return tp;
	}

	public void setTp(double tp) {
		this.tp = tp;
	}

	public double getD2() {
		return d2;
	}

	public void setD2(double d2) {
		this.d2 = d2;
	}

	public double getForcaaplicada() {
		return forcaaplicada;
	}

	public void setForcaaplicada(double forcaaplicada) {
		this.forcaaplicada = forcaaplicada;
	}

	public double getFe0d1() {
		return fe0d1;
	}

	public void setFe0d1(double fe0d1) {
		this.fe0d1 = fe0d1;
	}

	public double getFe0d2() {
		return fe0d2;
	}

	public void setFe0d2(double fe0d2) {
		this.fe0d2 = fe0d2;
	}

	public double getFe90d1() {
		return fe90d1;
	}

	public void setFe90d1(double fe90d1) {
		this.fe90d1 = fe90d1;
	}

	public double getFe90d2() {
		return fe90d2;
	}

	public void setFe90d2(double fe90d2) {
		this.fe90d2 = fe90d2;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public double getValorFaxrk() {
		return valorFaxrk;
	}

	public void setValorFaxrk(double valorFaxrk) {
		this.valorFaxrk = valorFaxrk;
	}

	public double getValorFaxrkPrego() {
		return valorFaxrkPrego;
	}

	public void setValorFaxrkPrego(double valorFaxrkPrego) {
		this.valorFaxrkPrego = valorFaxrkPrego;
	}

	public double getValorFaxrkFinal() {
		return valorFaxrkFinal;
	}

	public void setValorFaxrkFinal(double valorFaxrkFinal) {
		this.valorFaxrkFinal = valorFaxrkFinal;
	}

	public double getKmod1() {
		return kmod1;
	}

	public void setKmod1(double kmod1) {
		this.kmod1 = kmod1;
	}

	public double getKmod2() {
		return kmod2;
	}

	public void setKmod2(double kmod2) {
		this.kmod2 = kmod2;
	}

	public double getKmod3() {
		return kmod3;
	}

	public void setKmod3(double kmod3) {
		this.kmod3 = kmod3;
	}

	public double getAlfa() {
		return alfa;
	}

	public void setAlfa(double alfa) {
		this.alfa = alfa;
	}

	public double getRdmin() {
		return Rdmin;
	}

	public void setRdmin(double rdmin) {
		Rdmin = rdmin;
	}

	public double getRdlig() {
		return Rdlig;
	}

	public void setRdlig(double rdlig) {
		Rdlig = rdlig;
	}

	public double getRd1() {
		return rd1;
	}

	public void setRd1(double rd1) {
		this.rd1 = rd1;
	}

	public double getRd2() {
		return rd2;
	}

	public void setRd2(double rd2) {
		this.rd2 = rd2;
	}

	public double getRd3() {
		return rd3;
	}

	public void setRd3(double rd3) {
		this.rd3 = rd3;
	}

	public double getRd4() {
		return rd4;
	}

	public void setRd4(double rd4) {
		this.rd4 = rd4;
	}

	public double getRd5() {
		return rd5;
	}

	public void setRd5(double rd5) {
		this.rd5 = rd5;
	}

	public double getRd6() {
		return rd6;
	}

	public void setRd6(double rd6) {
		this.rd6 = rd6;
	}

	public double getRvd() {
		return Rvd;
	}

	public void setRvd(double rvd) {
		Rvd = rvd;
	}

	public double getNpregos() {
		return npregos;
	}

	public void setNpregos(double npregos) {
		this.npregos = npregos;
	}

	public double getFc0k1() {
		return fc0k1;
	}

	public void setFc0k1(double fc0k1) {
		this.fc0k1 = fc0k1;
	}

	public double getFc0k2() {
		return fc0k2;
	}

	public void setFc0k2(double fc0k2) {
		this.fc0k2 = fc0k2;
	}

	public double getFv0k1() {
		return fv0k1;
	}

	public void setFv0k1(double fv0k1) {
		this.fv0k1 = fv0k1;
	}

	public double getFv0k2() {
		return fv0k2;
	}

	public void setFv0k2(double fv0k2) {
		this.fv0k2 = fv0k2;
	}

	public double getEc0m1() {
		return ec0m1;
	}

	public void setEc0m1(double ec0m1) {
		this.ec0m1 = ec0m1;
	}

	public double getEc0m2() {
		return ec0m2;
	}

	public void setEc0m2(double ec0m2) {
		this.ec0m2 = ec0m2;
	}

	public double getT1() {
		return t1;
	}

	public void setT1(double t1) {
		this.t1 = t1;
	}

	public double getT2() {
		return t2;
	}

	public void setT2(double t2) {
		this.t2 = t2;
	}

	public double getFalfa1() {
		return falfa1;
	}

	public void setFalfa1(double falfa1) {
		this.falfa1 = falfa1;
	}

	public double getFalfa2() {
		return falfa2;
	}

	public void setFalfa2(double falfa2) {
		this.falfa2 = falfa2;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public double getArruela() {
		return arruela;
	}

	public void setArruela(double arruela) {
		this.arruela = arruela;
	}

	public int getFyd() {
		return fyd;
	}

	public void setFyd(int fyd) {
		this.fyd = fyd;
	}

	public int getFyk() {
		return fyk;
	}

	public void setFyk(int fyk) {
		this.fyk = fyk;
	}

	public int getNsecoes() {
		return nsecoes;
	}

	public void setNsecoes(int nsecoes) {
		this.nsecoes = nsecoes;
	}

	public int getFuk() {
		return fuk;
	}

	public void setFuk(int fuk) {
		this.fuk = fuk;
	}

	public int getNpr() {
		return npr;
	}

	public void setNpr(int npr) {
		this.npr = npr;
	}

	public Boolean getIncSim1() {
		return IncSim1;
	}

	public void setIncSim1(Boolean incSim1) {
		IncSim1 = incSim1;
	}

	public Boolean getIncSim2() {
		return IncSim2;
	}

	public void setIncSim2(Boolean incSim2) {
		IncSim2 = incSim2;
	}

	public Boolean getM() {
		return m;
	}

	public void setM(Boolean m) {
		this.m = m;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getNparafusos() {
		return nparafusos;
	}

	public void setNparafusos(double nparafusos) {
		this.nparafusos = nparafusos;
	}

	public String getImagemFalha() {
		return imagemFalha;
	}

	public void setImagemFalha(String imagemFalha) {
		this.imagemFalha = imagemFalha;
	}

	public String getEspessuraChapa() {
		return espessuraChapa;
	}

	public void setEspessuraChapa(String espessuraChapa) {
		this.espessuraChapa = espessuraChapa;
	}

	public String getRelatorioChapaAco() {
		return relatorioChapaAco;
	}

	public void setRelatorioChapaAco(String relatorioChapaAco) {
		this.relatorioChapaAco = relatorioChapaAco;
	}

	public double getFvkmin() {
		return fvkmin;
	}

	public void setFvkmin(double fvkmin) {
		this.fvkmin = fvkmin;
	}

}
