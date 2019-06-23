package visao;

import controlador.CTelas;
import modelo.BarraEnergia;
import utils.Constantes;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal implements ITela {
    public static GraphicsConfiguration gc;
    private  JFrame frame = new JFrame(gc);
    private JPanel panel = new JPanel();
    private JLabel contentPane;
    private Menu menu = new Menu();

    @Override
    public void renderizar() {
        frame.setTitle(Constantes.BOAS_VINDAS);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setJMenuBar(getMenu());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		java.net.URL imgURL = getClass().getResource("/visao/mario-kart-64.jpg");
        contentPane = new JLabel(new ImageIcon(imgURL));
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    @Override
    public void notifica(String message) {
            JOptionPane.showMessageDialog(null, message);
    }

    public void renderizarTelaJogo(){
        frame.repaint();
        frame.remove(contentPane);
        frame.setLayout(new GridLayout(2, 3, 5, 5));
        panel.add(getBotaoMovimentar());
        panel.add(getBotaoAtacar());
        panel.add(getBarraDeEnergia());
        frame.add(panel);
        frame.repaint();
        frame.validate();
    }

    private JButton getBotaoAtacar() {
        return CTelas.getInstance().enviarBotaoAtaque();
    }

    private JButton getBotaoMovimentar() {
        return CTelas.getInstance().enviarBotaoMovimentar();
    }

    private BarraEnergia getBarraDeEnergia(){
        return CTelas.getInstance().enviarBarraDeEnergia();
    }

    public Menu getMenu() {
        return menu;
    }

}
