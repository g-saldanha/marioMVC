package visao;

import utils.Constantes;

import javax.swing.*;

public class BotaoAtacar extends JButton {
    private static final long serialVersionUID = 838584243405814183L;
    private Boolean habilitado = false;
    private ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/atacar-icone.png"));


    public BotaoAtacar() {
        this.init(Constantes.ATACAR, this.icon);
        this.setSize(350, 80);
        this.setVisible(true);
        this.setEnabled(this.habilitado);
    }

    public BotaoAtacar getBotaoAtacar() {
        return this;
    }
}
