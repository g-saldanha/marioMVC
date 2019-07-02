package visao;

import modelo.Pista;
import modelo.Posicao;
import utils.Constantes;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal implements ITela {
    public static GraphicsConfiguration gc;
    private JFrame quadroPrincipal = new JFrame(gc);
    private JPanel painelAcoes = new JPanel();
    private JPanel painelPista = new JPanel();
    private Menu menu = new Menu();
    private BotaoAtacar botaoAtacar = new BotaoAtacar();
    private BotaoMovimentar botaoMovimentar = new BotaoMovimentar();
    private PainelInformacoes painelInformacoes = new PainelInformacoes();
    private JPanel painelFalsoRodape;
    private JPanel painelPrincipal;

    @Override
    public void renderizar() {
        this.quadroPrincipal.setTitle(Constantes.BOAS_VINDAS);
        this.quadroPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.quadroPrincipal.setResizable(true);
        this.quadroPrincipal.setJMenuBar(this.getMenu());
        this.quadroPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);

        java.net.URL imgURL = this.getClass().getResource("/imagens/mario-kart-64.jpg");
        this.quadroPrincipal.setContentPane(new JLabel(new ImageIcon(imgURL)));
        this.quadroPrincipal.setVisible(true);
    }

    @Override
    public void notifica(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void criaTelaJogo(Pista pista) {
        this.quadroPrincipal = new JFrame(gc);
        this.quadroPrincipal.setTitle(Constantes.MARIO_KART);
        this.quadroPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.quadroPrincipal.setResizable(true);
        this.quadroPrincipal.setJMenuBar(this.getMenu());
        this.quadroPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.painelPrincipal = new JPanel();
        this.painelPrincipal.setLayout(new BoxLayout(this.painelPrincipal, BoxLayout.Y_AXIS));

        JPanel painelFalsoTopo = new JPanel();
        painelFalsoTopo.setPreferredSize(new Dimension(100, 100));

        this.painelFalsoRodape = new JPanel();
        this.painelFalsoRodape.setPreferredSize(new Dimension(100, 100));

        this.painelPrincipal.add(painelFalsoTopo);

        this.painelAcoes.add(new JLabel(Constantes.ACOES));
        this.painelAcoes.add(this.getBotaoMovimentar());
        this.painelAcoes.add(this.getBotaoAtacar());
        this.painelPrincipal.add(this.painelAcoes);

        this.painelInformacoes.renderizar(pista);
        this.painelPrincipal.add(this.painelInformacoes);


        this.desenhaPista(pista);
        this.painelPrincipal.add(this.painelPista);

        this.painelPrincipal.add(this.painelFalsoRodape);

        this.quadroPrincipal.add(this.painelPrincipal);
        this.quadroPrincipal.setVisible(true);
        this.notifica(Constantes.PARTIDA_INICIADA);
    }

    public BotaoAtacar getBotaoAtacar() {
        return this.botaoAtacar;
    }

    public void setBotaoAtacar(BotaoAtacar botaoAtacar) {
        this.botaoAtacar = botaoAtacar;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public BotaoMovimentar getBotaoMovimentar() {
        return this.botaoMovimentar;
    }

    public JFrame getQuadroPrincipal() {
        return this.quadroPrincipal;
    }

    public void setQuadroPrincipal(JFrame quadroPrincipal) {
        this.quadroPrincipal = quadroPrincipal;
    }

    public void setBotaoMovimentar(BotaoMovimentar botaoMovimentar) {
        this.botaoMovimentar = botaoMovimentar;
    }

    public void desenhaPista(Pista pista) {
        this.painelPista.removeAll();
        this.painelPista.setLayout(new GridLayout(2, 20));
        for (Posicao posicao : pista.getListaDePosicoes()) {
            this.painelPista.add(new JLabel(posicao.getImagem(), JLabel.CENTER));
        }
        this.painelPista.repaint();
        this.painelPista.setVisible(true);
        this.painelPrincipal.add(this.painelPista);
        this.painelPrincipal.add(this.painelFalsoRodape);
        this.quadroPrincipal.repaint();
    }

    public void atualizar(Pista pista) {
        this.painelInformacoes.atualiza(pista);
        this.desenhaPista(pista);
        this.quadroPrincipal.repaint();
    }


}
