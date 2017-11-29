/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.modelo;

import br.com.tcd.enumeration.ClasseAco;
import br.com.tcd.enumeration.EspessuraChapaAco;

/**
 *
 * @author Gabriela Mello
 */
public class ChapaAco {

	private ClasseAco classeAco;
	private EspessuraChapaAco espessuraChapaAco;

	public ClasseAco getClasseAco() {
		return classeAco;
	}

	public void setClasseAco(ClasseAco classeAco) {
		this.classeAco = classeAco;
	}

	public EspessuraChapaAco getEspessuraChapaAco() {
		return espessuraChapaAco;
	}

	public void setEspessuraChapaAco(EspessuraChapaAco espessuraChapaAco) {
		this.espessuraChapaAco = espessuraChapaAco;
	}

}
