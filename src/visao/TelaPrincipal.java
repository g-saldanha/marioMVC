package visao;

import modelo.BarraEnergia;
import utils.Constantes;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal implements ITela {
    public static GraphicsConfiguration gc;
    private JFrame frame = new JFrame(gc);
    private JPanel panel = new JPanel();
    private JLabel contentPane;
    private Menu menu = new Menu();
    private BotaoAtacar botaoAtacar = new BotaoAtacar();
    private BotaoMovimentar botaoMovimentar = new BotaoMovimentar();
    private BarraEnergia barraDeEnergia = new BarraEnergia();

    @Override
    public void renderizar() {
        this.frame.setTitle(Constantes.BOAS_VINDAS);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setResizable(true);
        this.frame.setJMenuBar(this.getMenu());
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        java.net.URL imgURL = this.getClass().getResource("/visao/mario-kart-64.jpg");
        this.contentPane = new JLabel(new ImageIcon(imgURL));
        this.frame.setContentPane(this.contentPane);
        this.frame.setVisible(true);
    }

    @Override
    public void notifica(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void renderizarTelaJogo() {
        this.frame.repaint();
        this.frame.remove(this.contentPane);
        this.frame.setLayout(new GridLayout(2, 3, 5, 5));
        this.panel.add(this.getBotaoMovimentar());
        this.panel.add(this.getBotaoAtacar());
        this.panel.add(this.getBarraDeEnergia());
        this.frame.add(this.panel);
        this.frame.repaint();
        this.frame.validate();
    }

    private BotaoAtacar getBotaoAtacar() {
        return this.botaoAtacar;
    }

    private BotaoMovimentar getBotaoMovimentar() {
        return this.botaoMovimentar;
    }

    private BarraEnergia getBarraDeEnergia() {
        return this.barraDeEnergia;
    }

    public Menu getMenu() {
        return this.menu;
    }

}
