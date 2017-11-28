package br.com.tcd.verifier;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JProgressBar;

import br.com.tcd.interfaces.ModeloLigacaoProvider;
import br.com.tcd.enumeration.ClasseQuantidadePregos;

public class VerificadorComboQuantPrego extends Verificador {

	public VerificadorComboQuantPrego(JProgressBar status, ModeloLigacaoProvider modeloLigacaoProvider) {
		super(status, modeloLigacaoProvider);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean verify(JComponent input) {
		JComboBox box = (JComboBox)input;

		if(box.getSelectedIndex() == 0) {
			input.setBackground(Color.red);
			status.setString("Escolha a quantidade de pregos.");
			this.verified = false;
		} else {
			modeloLigacaoProvider.getModeloLigacao().getConectores().setQuantidadePrego((ClasseQuantidadePregos)box.getSelectedItem());
			validateSuccess(input);

		}

		return this.verified;
	}

}