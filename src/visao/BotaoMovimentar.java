package visao;

import utils.Constantes;

import javax.swing.*;

public class BotaoMovimentar extends JButton {
    private static final long serialVersionUID = -8998179864809200439L;

    private Boolean habilitado = false;
    private ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/movimentar-icone.png"));

    public BotaoMovimentar() {
        this.init(Constantes.MOVIMENTAR, this.icon);
//        this.setVerticalTextPosition(JButton.CENTER);
//        this.setHorizontalTextPosition(JButton.CENTER);
//        this.setText(Constantes.MOVIMENTAR);
        this.setSize(350, 80);
        this.setVisible(true);
        this.setEnabled(this.habilitado);
    }

    public BotaoMovimentar getBotaoMovimentar() {
        return this;
    }
}
