package br.com.tcd.verifier;

import java.awt.Color;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import br.com.tcd.interfaces.ModeloLigacaoProvider;

public class VerificadorValorAngulo extends VerificadoresPrego {

	public VerificadorValorAngulo(JProgressBar status, ModeloLigacaoProvider modeloLigacaoProvider) {
		super(status, modeloLigacaoProvider);
	}

	@Override
	public boolean verify(JComponent input) {
		JTextField field = (JTextField)input;

		if(field.getText().isEmpty()) {
			input.setBackground(Color.red);
			status.setString("Digite o ângulo entre os elementos de madeira.");
			this.verified = false;
		} else {
			try {
				float value = NumberFormat.getInstance().parse(field.getText()).floatValue();
				this.modeloLigacaoProvider.getModeloLigacao().getAngulo().setValorGrau(value);
				validateSuccess(input);
			} catch (ParseException ex) {
				status.setString("O ângulo deve ser inserido em números.");
				this.verified = false;
			}

		}
		return this.verified;

	}
}