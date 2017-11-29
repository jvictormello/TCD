/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.enumeration;

import br.com.tcd.modelo.Angulo;
import br.com.tcd.modelo.ChapaAco;
import br.com.tcd.modelo.Conectores;
import br.com.tcd.modelo.ElementoLigacao;

/**
 *
 * @author Gabriela Mello
 */
public enum ModeloLigacao {
	
	CORTE_SIMPLES(1),
	CORTE_DUPLO(2),
	DUPLO_CORTE_SIMPLES(1),
	CORTE_DUPLO_ACO_MADEIRA_ACO(3),
	CORTE_DUPLO_MADEIRA_ACO_MADEIRA(4);

	private ModeloLigacao(int numSecao) {
		this.numSecao = numSecao;
		this.angulo = new Angulo();
	}

	private int numSecao;
	private Angulo angulo;
	private ElementoLigacao elementoLigacao1 = new ElementoLigacao();
	private ElementoLigacao elementoLigacao2 = new ElementoLigacao();
	private EspecieMadeira especieMadeira;
	private TipoMadeira tipoMadeira;
	private Conectores conectores;
	private ChapaAco chapaAco = new ChapaAco();
	private Kmod1 kmod1;
	private Kmod2 kmod2;
	private Kmod3 kmod3;

	public int getNumSecao() {
		return this.numSecao;
	}

	public void setAngulo(Angulo angulo) {
		this.angulo = angulo;
	}

	public Angulo getAngulo() {
		return this.angulo;
	}

	public ElementoLigacao getElementoLigacao1() {
		return this.elementoLigacao1;
	}

	public ElementoLigacao getElementoLigacao2() {
		return this.elementoLigacao2;
	}

	public EspecieMadeira getEspecieMadeira() {
		return this.especieMadeira;
	}

	public void setEspecieMadeira(EspecieMadeira especieMadeira) {
		this.especieMadeira = especieMadeira;
	}

	public Kmod1 getKmod1() {
		return this.kmod1;
	}

	public void setKmod1(Kmod1 kmod1) {
		this.kmod1 = kmod1;
	}

	public Kmod2 getKmod2() {
		return this.kmod2;
	}

	public void setKmod2(Kmod2 kmod2) {
		this.kmod2 = kmod2;
	}

	public Kmod3 getKmod3() {
		return this.kmod3;
	}

	public void setKmod3(Kmod3 kmod3) {
		this.kmod3 = kmod3;
	}

	public TipoMadeira getTipoMadeira() {
		return this.tipoMadeira;
	}

	public void setTipoMadeira(TipoMadeira tipoMadeira) {
		this.tipoMadeira = tipoMadeira;
	}

	public Conectores getConectores() {
		return this.conectores;
	}

	public void setConectores(Conectores conectores) {
		this.conectores = conectores;
	}

	public void setAngulo(float value) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public float getBeta() {
		return this.elementoLigacao2.getClasseMadeira().getfc0k() / this.elementoLigacao1.getClasseMadeira().getfc0k();
	}

	public ChapaAco getChapaAco() {
		return chapaAco;
	}

	public void setChapaAco(ChapaAco chapaAco) {
		this.chapaAco = chapaAco;
	}

}
