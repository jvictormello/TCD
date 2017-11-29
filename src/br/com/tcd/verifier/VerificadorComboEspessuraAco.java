package br.com.tcd.verifier;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JProgressBar;

import br.com.tcd.enumeration.EspessuraChapaAco;
import br.com.tcd.interfaces.ModeloLigacaoProvider;

public class VerificadorComboEspessuraAco extends Verificador {

	public VerificadorComboEspessuraAco(JProgressBar status, ModeloLigacaoProvider modeloLigacaoProvider) {
		super(status, modeloLigacaoProvider);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean verify(JComponent input) {
		JComboBox box = (JComboBox)input;

		if(box.getSelectedIndex() == 0) {
			input.setBackground(Color.red);
			status.setString("Escolha a espessura da chapa de aço.");
			this.verified = false;
		} else {
			modeloLigacaoProvider.getModeloLigacao().getChapaAco().setEspessuraChapaAco((EspessuraChapaAco)box.getSelectedItem());
			validateSuccess(input);
		}

		return this.verified;
	}

}