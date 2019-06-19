package visao;

import javax.swing.*;

import utils.Constantes;

public class BotaoMovimentar extends JButton {
	private Boolean habilitado = false;

	public BotaoMovimentar() {
		this.setText(Constantes.MOVIMENTAR);
		this.setSize(350, 80);
		this.setVisible(true);
		this.setEnabled(this.habilitado);
	}

	public BotaoMovimentar getBotaoMovimentar() {
		return this;
	}
}
