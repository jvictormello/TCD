/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.controller;

import java.awt.Color;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.com.tcd.modelo.ClasseAcoPrego;
import br.com.tcd.modelo.ClasseMadeira;
import br.com.tcd.modelo.ClasseQuantidadePregos;
import br.com.tcd.modelo.Kmod1;
import br.com.tcd.modelo.Kmod2;
import br.com.tcd.modelo.Kmod3;
import br.com.tcd.modelo.ModeloLigacao;
import br.com.tcd.modelo.TipoPrego;

/**
 *
 * @author Gabriela Mello
 */
public class VerificadoresPrego extends InputVerifier {

	protected JLabel status;
	protected boolean verified = false;
	protected ModeloLigacaoProvider modeloLigacaoProvider;

	public VerificadoresPrego(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
		this.status = status;
		this.modeloLigacaoProvider = modeloLigacaoProvider;
	}

	public boolean isVerified() {
		return verified;
	}

	@Override
	public boolean verify(JComponent input) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	public static class VerificadorEspessura1 extends VerificadoresPrego {

		public VerificadorEspessura1(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JTextField field = (JTextField) input;

			if (field.getText().isEmpty()) {
				input.setBackground(Color.red);
				status.setText("Digite a espessura do elemento 1 de madeira.");
				this.verified = false;
			} else {
				try {
					float value = NumberFormat.getInstance().parse(field.getText()).floatValue();
					this.modeloLigacaoProvider.getModeloLigacao().getElementoLigação1().setEspessura(value);
					validateSuccess(input);
				} catch (ParseException ex) {
					status.setText("A espessura deve ser inserida em números.");
					this.verified = false;
				}
				this.validateSuccess(input);
			}

			return this.verified;
		}
	}

	public static class VerificadorValorAngulo extends VerificadoresPrego {

		public VerificadorValorAngulo(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JTextField field = (JTextField) input;
			// double teste = Double.parseDouble(field.getText().replace(",",
			// ".")); ----> comando anteriors

			if (field.getText().isEmpty()) {
				input.setBackground(Color.red);
				status.setText("Digite o ângulo entre os elementos de madeira.");
				this.verified = false;
			} else {
				try {
					float value = NumberFormat.getInstance().parse(field.getText()).floatValue();
					this.modeloLigacaoProvider.getModeloLigacao().getAngulo().setValorGrau(value);
					validateSuccess(input);
				} catch (ParseException ex) {
					status.setText("O ângulo deve ser inserido em números.");
					this.verified = false;
				}

			}
			return this.verified;

		}
	}

	public static class VerificadorEspessura2 extends VerificadoresPrego {

		public VerificadorEspessura2(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JTextField field = (JTextField) input;

			if (field.getText().isEmpty()) {
				input.setBackground(Color.red);
				status.setText("Digite a espessura do elemento 2 de madeira.");
				this.verified = false;
			} else {
				try {
					float value = NumberFormat.getInstance().parse(field.getText()).floatValue();
					this.modeloLigacaoProvider.getModeloLigacao().getElementoLigação2().setEspessura(value);
					validateSuccess(input);
				} catch (ParseException ex) {
					status.setText("A espessura deve ser inserida em números.");
					this.verified = false;
				}

			}
			return this.verified;

		}
	}

	public static class VerificadorKmod1 extends VerificadoresPrego {

		public VerificadorKmod1(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if ((box.getSelectedIndex() == 0)) {
				input.setBackground(Color.red);
				status.setText("Escolha o Kmod 1.");
				this.verified = false;
			} else {
				modeloLigacaoProvider.getModeloLigacao().setKmod1((Kmod1) box.getSelectedItem());
				validateSuccess(input);
			}

			return this.verified;
		}

	}

	public static class VerificadorKmod2 extends VerificadoresPrego {

		public VerificadorKmod2(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if ((box.getSelectedIndex() == 0)) {
				input.setBackground(Color.red);
				status.setText("Escolha o Kmod 2.");
				this.verified = false;
			} else {
				modeloLigacaoProvider.getModeloLigacao().setKmod2((Kmod2) box.getSelectedItem());
				validateSuccess(input);
			}

			return this.verified;
		}

	}

	public static class VerificadorKmod3 extends VerificadoresPrego {

		public VerificadorKmod3(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);

		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if ((box.getSelectedIndex() == 0)) {
				input.setBackground(Color.red);
				status.setText("Escolha o Kmod 3.");
				this.verified = false;

			} else {
				modeloLigacaoProvider.getModeloLigacao().setKmod3((Kmod3) box.getSelectedItem());
				validateSuccess(input);
			}

			return this.verified;
		}

	}

	public static class VerificadorComboClasseElem1 extends VerificadoresPrego {

		public VerificadorComboClasseElem1(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if (box.getSelectedIndex() == 0) {
				input.setBackground(Color.red);
				status.setText("Escolha a classe da madeira do elemento 1.");
				this.verified = false;
			} else {
				modeloLigacaoProvider.getModeloLigacao().getElementoLigação1()
						.setClasseMadeira((ClasseMadeira) box.getSelectedItem());
				validateSuccess(input);
			}

			return this.verified;
		}

	}

	public static class VerificadorComboClasseElem2 extends VerificadoresPrego {

		public VerificadorComboClasseElem2(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if (box.getSelectedIndex() == 0) {
				input.setBackground(Color.red);
				status.setText("Escolha a classe da madeira do elemento 2.");
				this.verified = false;
			} else {
				modeloLigacaoProvider.getModeloLigacao().getElementoLigação2()
						.setClasseMadeira((ClasseMadeira) box.getSelectedItem());
				validateSuccess(input);

			}

			return this.verified;
		}

	}

	public static class VerificadorComboTipoPrego extends VerificadoresPrego {

		public VerificadorComboTipoPrego(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if (box.getSelectedIndex() == 0) {
				input.setBackground(Color.red);
				status.setText("Escolha o tipo do parafuso.");
				this.verified = false;
			} else {
				modeloLigacaoProvider.getModeloLigacao().getConectores()
						.setTipoPrego((TipoPrego) box.getSelectedItem());
				validateSuccess(input);

			}

			return this.verified;
		}

	}

	public static class VerificadorComboTipoParafuso extends VerificadoresPrego {

		public VerificadorComboTipoParafuso(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if (box.getSelectedIndex() == 0) {
				input.setBackground(Color.red);
				status.setText("Escolha o tipo do parafuso.");
				this.verified = false;
			} else {
				// modeloLigacao.getConectores().setTipoParafuso((TipoParafuso)
				// box.getSelectedItem());
				validateSuccess(input);

			}

			return this.verified;
		}

	}

	public static class VerificadorComboQuantParafuso extends VerificadoresPrego {

		public VerificadorComboQuantParafuso(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if (box.getSelectedIndex() == 0) {
				input.setBackground(Color.red);
				status.setText("Escolha a quantidade de parafusos.");
				this.verified = false;
			} else {
				validateSuccess(input);

			}

			return this.verified;
		}

	}

	public static class VerificadorComboQuantPrego extends VerificadoresPrego {

		public VerificadorComboQuantPrego(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if (box.getSelectedIndex() == 0) {
				input.setBackground(Color.red);
				status.setText("Escolha a quantidade de pregos.");
				this.verified = false;
			} else {
				modeloLigacaoProvider.getModeloLigacao().getConectores()
						.setQuantidadePrego((ClasseQuantidadePregos) box.getSelectedItem());
				validateSuccess(input);

			}

			return this.verified;
		}

	}

	public static class VerificadorComboAcoParafuso extends VerificadoresPrego {

		public VerificadorComboAcoParafuso(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if (box.getSelectedIndex() == 0) {
				input.setBackground(Color.red);
				status.setText("Escolha a classe do aço do parafuso.");
				this.verified = false;
			} else {
				// modeloLigacao.getConectores().setClasseAcoParafuso((ClasseAcoParafuso)box.getSelectedItem());
				validateSuccess(input);

			}

			return this.verified;
		}

	}

	public static class VerificadorComboAcoPrego extends VerificadoresPrego {

		public VerificadorComboAcoPrego(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if (box.getSelectedIndex() == 0) {
				input.setBackground(Color.red);
				status.setText("Escolha a classe do aço do prego.");
				this.verified = false;
			} else {
				modeloLigacaoProvider.getModeloLigacao().getConectores()
						.setClasseAcoPrego((ClasseAcoPrego) box.getSelectedItem());
				validateSuccess(input);
				status.setText("Clique em \"Calcular Ligação\".");
			}

			return this.verified;
		}

	}

	public static class VerificadorComboArruelas extends VerificadoresPrego {

		public VerificadorComboArruelas(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if (box.getSelectedIndex() == 0) {
				input.setBackground(Color.red);
				status.setText("Escolha o tipo de arruela para madeira.");
				this.verified = false;
			} else {
				validateSuccess(input);
				status.setText("Clique em \"Calcular Ligação\".");
			}

			return this.verified;
		}

	}

	public static class VerificadorComboEspessuraAco extends VerificadoresPrego {

		public VerificadorComboEspessuraAco(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if (box.getSelectedIndex() == 0) {
				input.setBackground(Color.red);
				status.setText("Escolha a espessura da chapa de aço.");
				this.verified = false;
			} else {
				validateSuccess(input);
			}

			return this.verified;
		}

	}

	public static class VerificadorComboArruelaAco extends VerificadoresPrego {

		public VerificadorComboArruelaAco(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if (box.getSelectedIndex() == 0) {
				input.setBackground(Color.red);
				status.setText("Escolha o tipo de arruela para aço.");
				this.verified = false;
			} else {
				validateSuccess(input);
				status.setText("Clique em \"Calcular Ligação\".");
			}

			return this.verified;
		}

	}

	public static class VerificadorComboClasseElemAco extends VerificadoresPrego {

		public VerificadorComboClasseElemAco(JLabel status, ModeloLigacaoProvider modeloLigacaoProvider) {
			super(status, modeloLigacaoProvider);
		}

		@Override
		public boolean verify(JComponent input) {
			JComboBox box = (JComboBox) input;

			if (box.getSelectedIndex() == 0) {
				input.setBackground(Color.red);
				status.setText("Escolha a classe da chapa de aço utilizada.");
				this.verified = false;
			} else {
				this.validateSuccess(input);
			}

			return this.verified;
		}

	}

	protected void validateSuccess(JComponent input) {
		input.setBackground(new Color(255, 255, 255));
		status.setText("");
		this.verified = true;
	}

}
