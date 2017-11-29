package br.com.tcd.verifier;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JProgressBar;

import br.com.tcd.enumeration.TipoPrego;
import br.com.tcd.interfaces.ModeloLigacaoProvider;

public class VerificadorComboTipoPrego extends Verificador {

	public VerificadorComboTipoPrego(JProgressBar status, ModeloLigacaoProvider modeloLigacaoProvider) {
		super(status, modeloLigacaoProvider);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean verify(JComponent input) {
		JComboBox box = (JComboBox)input;

		if(box.getSelectedIndex() == 0) {
			input.setBackground(Color.red);
			status.setString("Escolha o tipo do parafuso.");
			this.verified = false;
		} else {
			modeloLigacaoProvider.getModeloLigacao().getConectores().setTipoPrego((TipoPrego)box.getSelectedItem());
			validateSuccess(input);

		}

		return this.verified;
	}

}