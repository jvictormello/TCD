/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.modelo;

import examples.controller.DuasSecoesCorteInclinadaController;
import examples.controller.DuasSecoesCorteParaleloController;
import examples.controller.DuasSecoesCortePerpendicularController;
import examples.controller.UmaSecaoCorteInclinadaController;
import examples.controller.UmaSecaoCorteParaleloController;
import examples.controller.UmaSecaoCortePerpendicularController;

/**
 *
 * @author Gabriela Mello
 */
public class CalculoModeloLigacao {

    private ModeloLigacao modeloLigacao;

    private double d = 0,
            c = 0,
            d1 = 0,
            tp = 0,
            d2 = 0,
            forcaaplicada = 0,
            fe0d1 = 0,
            fe0d2 = 0,
            fe90d1 = 0,
            fe90d2 = 0,
            beta = 0,
            valorFaxrk = 0,
            valorFaxrkPrego = 0,
            valorFaxrkFinal = 0,
            kmod1 = 0,
            kmod2 = 0,
            kmod3 = 0,
            alfa = 0,
            Rdmin = 0,
            Rdlig = 0,
            rd1 = 0,
            rd2 = 0,
            rd3 = 0,
            rd4 = 0,
            rd5 = 0,
            rd6 = 0,
            Rvd = 0,
            npregos = 0,
            fc0k1 = 0,
            fc0k2 = 0,
            fv0k1 = 0,
            fv0k2 = 0,
            ec0m1 = 0,
            ec0m2 = 0,
            t1 = 0,
            t2 = 0,
            falfa1 = 0,
            falfa2 = 0,
            angulo = 0,
            arruela = 0;

    private int fyd = 0,
            fyk = 0,
            nsecoes = 0,
            fuk = 0,
            npr = 0;

    private Boolean IncSim1,
            IncSim2,
            m;

    public CalculoModeloLigacao(ModeloLigacao modeloLigacao) {
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

        //CÁLCULO PARA UMA SEÇÃO DE CORTE PARALELO
        if (((modeloLigacao == ModeloLigacao.CORTE_SIMPLES || modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES)) && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PARALELO) {

            UmaSecaoCorteParaleloController resultado = new UmaSecaoCorteParaleloController();

            //Valores de Faxrk
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

            //Recebendo os valores de Rd
            double Myd = resultado.ValorMyd(fuk, d);
            rd1 = resultado.EmbMenorPeca(fe0d1, t1, d);
            rd2 = resultado.EmbMaiorPeca(fe0d1, t2, d, beta);
            rd3 = resultado.EmbDuasPecas(fe0d1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
            rd4 = resultado.DeformPinoMaiorPeca(fe0d1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
            rd5 = resultado.DeformPinoMenorPeca(fe0d1, t2, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
            rd6 = resultado.DeformPinoNasDuas(beta, Myd, fe0d1, d, valorFaxrk, valorFaxrkPrego);

            //Verifica o menor Rd
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

            //Valor de resistência da ligação
            Rdlig = npregos * nsecoes * Rdmin;
            Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
        }

        //CÁLCULO PARA UMA SEÇÃO INCLINADA
        if ((modeloLigacao == ModeloLigacao.CORTE_SIMPLES || modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES) && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {

            UmaSecaoCorteInclinadaController resultado = new UmaSecaoCorteInclinadaController();

            //Valores de Faxrk
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
            //Valor utilizado de força
            if (IncSim1) {
                falfa2 = ((fe0d2 * fe90d2) / ((fe0d2 * (Math.pow(Math.sin(angulo), 2))) + (fe90d2 * (Math.pow(Math.cos(angulo), 2)))));
                falfa1 = fe0d1;

            }
            if (IncSim2) {
                falfa1 = ((fe0d1 * fe90d1) / ((fe0d1 * (Math.pow(Math.sin(angulo), 2))) + (fe90d1 * (Math.pow(Math.cos(angulo), 2)))));
                falfa2 = fe0d2;

            }
            beta = falfa2 / falfa1;
            //Recebe os Rd
            double Myd = resultado.ValorMyd(fuk, d);
            rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
            rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
            rd3 = resultado.EmbDuasPecas(falfa1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
            rd4 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
            rd5 = resultado.DeformPinoMenorPeca(falfa1, t2, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
            rd6 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);

            //Verifica o menor Rd
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

            //Valor de resistência da ligação
            Rdlig = npregos * nsecoes * Rdmin;
            Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

        }

        //CÁLCULO UMA SEÇÃO PERPENDICULAR
        if ((modeloLigacao == ModeloLigacao.CORTE_SIMPLES || modeloLigacao == ModeloLigacao.DUPLO_CORTE_SIMPLES) && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
            UmaSecaoCortePerpendicularController resultado = new UmaSecaoCortePerpendicularController();

            //Valores de Faxrk
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

            //Valor utilizado de força
            if (IncSim1) {
                falfa1 = fe0d1;
                falfa2 = fe90d2;

            }
            if (IncSim2) {
                falfa2 = fe0d2;
                falfa1 = fe90d1;

            }
            beta = falfa2 / falfa1;

            //Recebe os Rd
            double Myd = resultado.ValorMyd(fuk, d);
            rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
            rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
            rd3 = resultado.EmbDuasPecas(falfa1, d, t1, t2, beta, valorFaxrk, valorFaxrkPrego);
            rd4 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
            rd5 = resultado.DeformPinoMenorPeca(falfa1, t2, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
            rd6 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);

            //Verifica o menor Rd
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

            //Valor de resistência da ligação
            Rdlig = npregos * nsecoes * Rdmin;
            Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
        }

        //CÁLCULO DUAS SEÇÕES PARALELA
        if (modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PARALELO) {
            DuasSecoesCorteParaleloController resultado = new DuasSecoesCorteParaleloController();

            //Valores de Faxrk
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

            //Recebe os Rd
            double Myd = resultado.ValorMyd(fuk, d);
            rd1 = resultado.EmbMenorPeca(fe0d1, t1, d);
            rd2 = resultado.EmbMaiorPeca(fe0d1, t2, d, beta);
            rd3 = resultado.DeformPinoMaiorPeca(fe0d1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
            rd4 = resultado.DeformPinoNasDuas(beta, Myd, fe0d1, d, valorFaxrk, valorFaxrkPrego);

            //Verifica o menor Rd
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

            //Valor de resistência da ligação
            Rdlig = npregos * nsecoes * Rdmin;
            Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

        }

        //CÁLCULO DUAS SEÇÕES INCLINADA
        if (modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.INCLINADO) {
            DuasSecoesCorteInclinadaController resultado = new DuasSecoesCorteInclinadaController();

            //Valores de Faxrk
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

            //Valor utilizado de força
            if (IncSim1) {
                falfa2 = ((fe0d2 * fe90d2) / ((fe0d2 * (Math.pow(Math.sin(angulo), 2))) + (fe90d2 * (Math.pow(Math.cos(angulo), 2)))));
                falfa1 = fe0d1;

            }
            if (IncSim2) {
                falfa1 = ((fe0d1 * fe90d1) / ((fe0d1 * (Math.pow(Math.sin(angulo), 2))) + (fe90d1 * (Math.pow(Math.cos(angulo), 2)))));
                falfa2 = fe0d2;

            }
            beta = falfa2 / falfa1;

            //Recebe os Rd
            double Myd = resultado.ValorMyd(fuk, d);
            rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
            rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
            rd3 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
            rd4 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);

            //Verifica o menor Rd
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

            //Valor de resistência da ligação
            Rdlig = npregos * nsecoes * Rdmin;
            Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);

        }

        //CÁLCULO DUAS SEÇÕES PERPENDICULAR
        if (modeloLigacao == ModeloLigacao.CORTE_DUPLO && modeloLigacao.getAngulo().getTipoAngulo() == Angulo.TipoAngulo.PERPENDICULAR) {
            DuasSecoesCortePerpendicularController resultado = new DuasSecoesCortePerpendicularController();

            //Valores de Faxrk
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

            //Valor utilizado de força
            if (IncSim1) {
                falfa1 = fe0d1;
                falfa2 = fe90d2;

            }
            if (IncSim2) {
                falfa2 = fe0d2;
                falfa1 = fe90d1;

            }
            beta = falfa2 / falfa1;

            //Recebe os Rd
            double Myd = resultado.ValorMyd(fuk, d);
            rd1 = resultado.EmbMenorPeca(falfa1, t1, d);
            rd2 = resultado.EmbMaiorPeca(falfa1, t2, d, beta);
            rd3 = resultado.DeformPinoMaiorPeca(falfa1, t1, d, beta, Myd, valorFaxrk, valorFaxrkPrego);
            rd4 = resultado.DeformPinoNasDuas(beta, Myd, falfa1, d, valorFaxrk, valorFaxrkPrego);

            //Verifica o menor Rd
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

            //Valor de resistência da ligação
            Rdlig = npregos * nsecoes * Rdmin;
            Rvd = ((Rdlig * kmod1 * kmod2 * kmod3) / 1.4);
        }
    }

}
