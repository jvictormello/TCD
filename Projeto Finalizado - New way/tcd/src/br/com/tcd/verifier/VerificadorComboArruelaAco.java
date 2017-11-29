package br.com.tcd.verifier;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JProgressBar;

import br.com.tcd.enumeration.TipoArruelaAco;
import br.com.tcd.interfaces.ModeloLigacaoProvider;

public class VerificadorComboArruelaAco extends Verificador {

	public VerificadorComboArruelaAco(JProgressBar status, ModeloLigacaoProvider modeloLigacaoProvider) {
		super(status, modeloLigacaoProvider);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean verify(JComponent input) {
		JComboBox box = (JComboBox)input;

		if(box.getSelectedIndex() == 0) {
			input.setBackground(Color.red);
			status.setString("Escolha o tipo de arruela para aço.");
			this.verified = false;
		} else {
			validateSuccess(input);
			modeloLigacaoProvider.getModeloLigacao().getConectores().setTipoArruelaAco((TipoArruelaAco)box.getSelectedItem());
			status.setString("Clique em \"Calcular Ligação\".");
		}

		return this.verified;
	}

}
