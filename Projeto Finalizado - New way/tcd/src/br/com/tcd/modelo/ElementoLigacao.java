/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.modelo;

import br.com.tcd.enumeration.ClasseMadeira;

/**
 *
 * @author Gabriela Mello
 */
public class ElementoLigacao {

	private ClasseMadeira classeMadeira;
	private float espessura;
	private boolean forcaParaleraAFibra = false;

	public ClasseMadeira getClasseMadeira() {
		return classeMadeira;
	}

	public void setClasseMadeira(ClasseMadeira classeMadeira) {
		this.classeMadeira = classeMadeira;
	}

	public float getEspessura() {
		return espessura;
	}

	public void setEspessura(float espessura) {
		this.espessura = espessura;
	}

	public boolean isForcaParalelaAFibra() {
		return forcaParaleraAFibra;
	}

	public void setForcaParalelaAFibra(boolean forcaParaleraAFibra) {
		this.forcaParaleraAFibra = forcaParaleraAFibra;
	}

}
