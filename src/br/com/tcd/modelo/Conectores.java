/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.modelo;

import br.com.tcd.enumeration.ClasseAcoPrego;
import br.com.tcd.enumeration.ClasseQuantidadePregos;
import br.com.tcd.enumeration.TipoPrego;

/**
 *
 * @author Gabriela Mello
 */
public class Conectores {
	private TipoPrego tipoPrego;
	private ClasseQuantidadePregos quantidadePregos;
	private ClasseAcoPrego classeAcoPrego;

	public TipoPrego getTipoPrego() {
		return tipoPrego;
	}

	public void setTipoPrego(TipoPrego tipoPrego) {
		this.tipoPrego = tipoPrego;
	}

	public ClasseAcoPrego getClasseAcoPrego() {
		return classeAcoPrego;
	}

	public void setClasseAcoPrego(ClasseAcoPrego classeAcoPrego) {
		this.classeAcoPrego = classeAcoPrego;
	}

	public ClasseQuantidadePregos getQuantidadePrego() {
		return quantidadePregos;
	}

	public void setQuantidadePrego(ClasseQuantidadePregos quantidadePrego) {
		this.quantidadePregos = quantidadePrego;
	}

}
