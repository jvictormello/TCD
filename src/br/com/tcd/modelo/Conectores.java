/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.modelo;

import br.com.tcd.enumeration.ClasseAcoParafuso;
import br.com.tcd.enumeration.ClasseAcoPrego;
import br.com.tcd.enumeration.ClasseQuantidadePregos;
import br.com.tcd.enumeration.QuantidadeParafuso;
import br.com.tcd.enumeration.TipoArruela;
import br.com.tcd.enumeration.TipoParafuso;
import br.com.tcd.enumeration.TipoPrego;

/**
 *
 * @author Gabriela Mello
 */
public class Conectores {
	//Pregos
	private TipoPrego tipoPrego;
	private ClasseQuantidadePregos quantidadePregos;
	private ClasseAcoPrego classeAcoPrego;
	//Parafusos
	private TipoParafuso tipoParafuso;
	private TipoArruela tipoArruela;
	private QuantidadeParafuso quantidadeParafuso;
	private ClasseAcoParafuso classeAcoParafuso;

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

	public TipoParafuso getTipoParafuso() {
		return tipoParafuso;
	}

	public void setTipoParafuso(TipoParafuso tipoParafuso) {
		this.tipoParafuso = tipoParafuso;
	}

	public TipoArruela getTipoArruela() {
		return tipoArruela;
	}

	public void setTipoArruela(TipoArruela tipoArruela) {
		this.tipoArruela = tipoArruela;
	}

	public QuantidadeParafuso getQuantidadeParafuso() {
		return quantidadeParafuso;
	}

	public void setQuantidadeParafuso(QuantidadeParafuso quantidadeParafuso) {
		this.quantidadeParafuso = quantidadeParafuso;
	}

	public ClasseAcoParafuso getClasseAcoParafuso() {
		return classeAcoParafuso;
	}

	public void setClasseAcoParafuso(ClasseAcoParafuso classeAcoParafuso) {
		this.classeAcoParafuso = classeAcoParafuso;
	}

}
