/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.modelo;

import br.com.tcd.controller.DuasSecoesCorteInclinadaController;
import br.com.tcd.controller.DuasSecoesCorteParaleloController;
import br.com.tcd.controller.DuasSecoesCortePerpendicularController;
import br.com.tcd.controller.UmaSecaoCorteInclinadaController;
import br.com.tcd.controller.UmaSecaoCorteParaleloController;
import br.com.tcd.controller.UmaSecaoCortePerpendicularController;

/**
 *
 * @author Gabriela Mello
 */
public class CalculoModeloLigacao {

	private ModeloLigacao modeloLigacao;

	private double d = 0, c = 0, d1 = 0, tp = 0, d2 = 0, forcaaplicada = 0, fe0d1 = 0, fe0d2 = 0, fe90d1 = 0,
			fe90d2 = 0, beta = 0, valorFaxrk = 0, valorFaxrkPrego = 0, valorFaxrkFinal = 0, kmod1 = 0, kmod2 = 0,
			kmod3 = 0, alfa = 0, Rdmin = 0, Rdlig = 0, rd1 = 0, rd2 = 0, rd3 = 0, rd4 = 0, rd5 = 0, rd6 = 0, Rvd = 0,
			npregos = 0, fc0k1 = 0, fc0k2 = 0, fv0k1 = 0, fv0k2 = 0, ec0m1 = 0, ec0m2 = 0, t1 = 0, t2 = 0, falfa1 = 0,
			falfa2 = 0, angulo = 0, arruela = 0, myd = 0;

	private int fyd = 0, fyk = 0, nsecoes = 0, fuk = 0, npr = 0;

	private Boolean IncSim1, IncSim2, m;

	public CalculoModeloLigacao(ModeloLigacao modeloLigacao, Boolean IncSim1, Boolean IncSim2, Boolean m) {
		this.fc0k1 = modeloLigacao.getElementoLigação1().getClasseMadeira().getfc0k();
		this.fc0k2 = modeloLigacao.getElementoLigação2().getClasseMadeira().getfc0k();
		this.fv0k1 = modeloLigacao.getElementoLigação1().getClasseMadeira().getfv0k();
		this.fv0k2 = modeloLigacao.getElementoLigação2().getClasseMadeira().getfv0k();
		this.ec0m1 = modeloLigacao.getElementoLigação1().getClasseMadeira().getec0m();
		this.ec0m2 = modeloLigacao.getElementoLigação2().getClasseMadeira().getec0m();
		this.t1 = modeloLigacao.getElementoLigação1().getEspessura();
		this.t2 = modeloLigacao.getElementoLigação2().getEspessura();
		this.nsecoes = modeloLigacao.getNumSecao();
		this.kmod1 = modeloLigacao.getKmod1().getValor();
		this.kmod2 = modeloLigacao.getKmod2().getValor();
		this.kmod3 = modeloLigacao.getKmod3().getValor();
		this.npr = modeloLigacao.getConectores().getQuantidadePrego().getQuantidadePregos();

		if (npr > 8) {
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
		if (((modeloLigacao == ModeloLigacao.CORTE_SIMPLES || modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES))
				&& modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PARALELO) {

			UmaSecaoCorteParaleloController resultado = new UmaSecaoCorteParaleloController();

			// Valores de Faxrk
			if (m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

			}

			if (valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;

			}

			if (valorFaxrkPrego > 0) {
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

			if (rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;

			}
			if (rd3 < Rdmin) {
				Rdmin = rd3;

			}
			if (rd4 < Rdmin) {
				Rdmin = rd4;

			}
			if (rd5 < Rdmin) {
				Rdmin = rd5;

			}
			if (rd6 < Rdmin) {
				Rdmin = rd6;

			}

			// Valor de resistência da ligação
			Rdlig = npregos * nsecoes * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		}

		// CÁLCULO PARA UMA SEÇÃO INCLINADA
		if ((modeloLigacao == ModeloLigacao.CORTE_SIMPLES || modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES)
				&& modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {

			UmaSecaoCorteInclinadaController resultado = new UmaSecaoCorteInclinadaController();

			// Valores de Faxrk
			if (m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

			}

			if (valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;

			}

			if (valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;

			}
			// Valor utilizado de força
			if (IncSim1) {
				falfa2 = ((fe0d2 * fe90d2)
						/ ((fe0d2 * (Math.pow(Math.sin(angulo), 2))) + (fe90d2 * (Math.pow(Math.cos(angulo), 2)))));
				falfa1 = fe0d1;

			}
			if (IncSim2) {
				falfa1 = ((fe0d1 * fe90d1)
						/ ((fe0d1 * (Math.pow(Math.sin(angulo), 2))) + (fe90d1 * (Math.pow(Math.cos(angulo), 2)))));
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

			if (rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;

			}
			if (rd3 < Rdmin) {
				Rdmin = rd3;

			}
			if (rd4 < Rdmin) {
				Rdmin = rd4;
				;
			}
			if (rd5 < Rdmin) {
				Rdmin = rd5;

			}
			if (rd6 < Rdmin) {
				Rdmin = rd6;
			}

			// Valor de resistência da ligação
			Rdlig = npregos * nsecoes * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

		}

		// CÁLCULO UMA SEÇÃO PERPENDICULAR
		if ((modeloLigacao == ModeloLigacao.CORTE_SIMPLES || modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES)
				&& modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
			UmaSecaoCortePerpendicularController resultado = new UmaSecaoCortePerpendicularController();

			// Valores de Faxrk
			if (m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

			}

			if (valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;

			}

			if (valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;

			}

			// Valor utilizado de força
			if (IncSim1) {
				falfa1 = fe0d1;
				falfa2 = fe90d2;

			}
			if (IncSim2) {
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

			if (rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;

			}
			if (rd3 < Rdmin) {
				Rdmin = rd3;

			}
			if (rd4 < Rdmin) {
				Rdmin = rd4;

			}
			if (rd5 < Rdmin) {
				Rdmin = rd5;

			}
			if (rd6 < Rdmin) {
				Rdmin = rd6;

			}

			// Valor de resistência da ligação
			Rdlig = npregos * nsecoes * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
		}

		// CÁLCULO DUAS SEÇÕES PARALELA
		if (modeloLigacao == ModeloLigacao.CORTE_DUPLO
				&& modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PARALELO) {
			DuasSecoesCorteParaleloController resultado = new DuasSecoesCorteParaleloController();

			// Valores de Faxrk
			if (m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

			}

			if (valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;

			}

			if (valorFaxrkPrego > 0) {
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

			if (rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;

			}
			if (rd3 < Rdmin) {
				Rdmin = rd3;

			}
			if (rd4 < Rdmin) {
				Rdmin = rd4;

			}

			// Valor de resistência da ligação
			Rdlig = npregos * nsecoes * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

		}

		// CÁLCULO DUAS SEÇÕES INCLINADA
		if (modeloLigacao == ModeloLigacao.CORTE_DUPLO
				&& modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
			DuasSecoesCorteInclinadaController resultado = new DuasSecoesCorteInclinadaController();

			// Valores de Faxrk
			if (m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

			}

			if (valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;

			}

			if (valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;

			}

			// Valor utilizado de força
			if (IncSim1) {
				falfa2 = ((fe0d2 * fe90d2)
						/ ((fe0d2 * (Math.pow(Math.sin(angulo), 2))) + (fe90d2 * (Math.pow(Math.cos(angulo), 2)))));
				falfa1 = fe0d1;

			}
			if (IncSim2) {
				falfa1 = ((fe0d1 * fe90d1)
						/ ((fe0d1 * (Math.pow(Math.sin(angulo), 2))) + (fe90d1 * (Math.pow(Math.cos(angulo), 2)))));
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

			if (rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;

			}
			if (rd3 < Rdmin) {
				Rdmin = rd3;

			}
			if (rd4 < Rdmin) {
				Rdmin = rd4;

			}

			// Valor de resistência da ligação
			Rdlig = npregos * nsecoes * Rdmin;
			Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

		}

		// CÁLCULO DUAS SEÇÕES PERPENDICULAR
		if (modeloLigacao == ModeloLigacao.CORTE_DUPLO
				&& modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
			DuasSecoesCortePerpendicularController resultado = new DuasSecoesCortePerpendicularController();

			// Valores de Faxrk
			if (m) {
				valorFaxrk = resultado.ValorFaxrk(d, fuk, d2, d1, fe90d1, arruela);
				valorFaxrkPrego = resultado.ValorFaxrkPrego(d, fuk);

			}

			if (valorFaxrk > 0) {
				valorFaxrkFinal = valorFaxrk;

			}

			if (valorFaxrkPrego > 0) {
				valorFaxrkFinal = valorFaxrkPrego;

			}

			// Valor utilizado de força
			if (IncSim1) {
				falfa1 = fe0d1;
				falfa2 = fe90d2;

			}
			if (IncSim2) {
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

			if (rd2 < Rdmin || rd2 == Rdmin) {
				Rdmin = rd2;

			}
			if (rd3 < Rdmin) {
				Rdmin = rd3;

			}
			if (rd4 < Rdmin) {
				Rdmin = rd4;

			}

			// Valor de resistência da ligação
			Rdlig = npregos * nsecoes * Rdmin;
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

}
