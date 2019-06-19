package visao;

import javax.swing.*;

import utils.Constantes;

public class BotaoAtacar extends JButton {
	private Boolean habilitado = true;

	public BotaoAtacar() {
		this.setText(Constantes.ATACAR);
		this.setSize(350, 80);
		this.setVisible(true);
		this.setEnabled(this.habilitado);
	}

	public BotaoAtacar getBotaoAtacar() {
		return this;
	}
}
