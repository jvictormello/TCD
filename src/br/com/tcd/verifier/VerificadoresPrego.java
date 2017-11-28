/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.verifier;

import java.awt.Color;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JProgressBar;

import br.com.tcd.interfaces.ModeloLigacaoProvider;

/**
 * @author Gabriela Mello
 */
public class VerificadoresPrego extends InputVerifier {

	protected JProgressBar status;
	protected boolean verified = false;
	protected ModeloLigacaoProvider modeloLigacaoProvider;

	public VerificadoresPrego(JProgressBar status, ModeloLigacaoProvider modeloLigacaoProvider) {
		this.status = status;
		this.modeloLigacaoProvider = modeloLigacaoProvider;
	}

	public boolean isVerified() {
		return verified;
	}

	@Override
	public boolean verify(JComponent input) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	protected void validateSuccess(JComponent input) {
		input.setBackground(new Color(255, 255, 255));
		status.setString("");
		this.verified = true;
	}

}
