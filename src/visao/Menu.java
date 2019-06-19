package visao;

import utils.Constantes;

import javax.swing.*;

public class Menu extends JMenuBar {

    public Menu() {
        JMenu jMenu = new JMenu(Constantes.JOGO);
        jMenu.add(new JMenuItem(Constantes.CONECTAR));
        jMenu.add(new JMenuItem(Constantes.INICIAR_NOVA_PARTIDA));
        jMenu.addSeparator();
        jMenu.add(new JMenuItem(Constantes.DESCONECTAR));
        jMenu.add(new JMenuItem(Constantes.SAIR));
        this.add(jMenu);
    }

    public JMenuBar getMenu() {
        return this;
    }
}
