package br.com.tcd.verifier;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JProgressBar;

import br.com.tcd.interfaces.ModeloLigacaoProvider;
import br.com.tcd.enumeration.ClasseAcoPrego;

public class VerificadorComboAcoPrego extends Verificador {

	public VerificadorComboAcoPrego(JProgressBar status, ModeloLigacaoProvider modeloLigacaoProvider) {
		super(status, modeloLigacaoProvider);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean verify(JComponent input) {
		JComboBox box = (JComboBox)input;

		if(box.getSelectedIndex() == 0) {
			input.setBackground(Color.red);
			status.setString("Escolha a classe do aço do prego.");
			this.verified = false;
		} else {
			modeloLigacaoProvider.getModeloLigacao().getConectores().setClasseAcoPrego((ClasseAcoPrego)box.getSelectedItem());
			validateSuccess(input);
			status.setString("Clique em \"Calcular Ligação\".");
		}

		return this.verified;
	}

}