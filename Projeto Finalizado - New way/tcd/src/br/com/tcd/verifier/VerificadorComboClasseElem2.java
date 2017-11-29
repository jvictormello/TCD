package br.com.tcd.verifier;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JProgressBar;

import br.com.tcd.enumeration.ClasseMadeira;
import br.com.tcd.interfaces.ModeloLigacaoProvider;

public class VerificadorComboClasseElem2 extends Verificador {

	public VerificadorComboClasseElem2(JProgressBar status, ModeloLigacaoProvider modeloLigacaoProvider) {
		super(status, modeloLigacaoProvider);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean verify(JComponent input) {
		JComboBox box = (JComboBox)input;

		if(box.getSelectedIndex() == 0) {
			input.setBackground(Color.red);
			status.setString("Escolha a classe da madeira do elemento 2.");
			this.verified = false;
		} else {
			modeloLigacaoProvider.getModeloLigacao().getElementoLigacao2().setClasseMadeira((ClasseMadeira)box.getSelectedItem());
			validateSuccess(input);

		}

		return this.verified;
	}

}