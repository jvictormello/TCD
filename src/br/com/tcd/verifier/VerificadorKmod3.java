package br.com.tcd.verifier;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JProgressBar;

import br.com.tcd.interfaces.ModeloLigacaoProvider;
import br.com.tcd.enumeration.Kmod3;

public class VerificadorKmod3 extends VerificadoresPrego {

	public VerificadorKmod3(JProgressBar status, ModeloLigacaoProvider modeloLigacaoProvider) {
		super(status, modeloLigacaoProvider);

	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean verify(JComponent input) {
		JComboBox box = (JComboBox)input;

		if((box.getSelectedIndex() == 0)) {
			input.setBackground(Color.red);
			status.setString("Escolha o Kmod 3.");
			this.verified = false;

		} else {
			modeloLigacaoProvider.getModeloLigacao().setKmod3((Kmod3)box.getSelectedItem());
			validateSuccess(input);
		}

		return this.verified;
	}

}