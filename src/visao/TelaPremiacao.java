package visao;

import utils.Constantes;

import javax.swing.*;
import java.awt.*;

public class TelaPremiacao implements ITela {
    public GraphicsConfiguration gc;
    private ImageIcon vitoria = new ImageIcon(this.getClass().getResource("/imagens/vitoria.jpg"));
    private ImageIcon derrota = new ImageIcon(this.getClass().getResource("/imagens/derrota.jpg"));
    private JFrame telaPrincipal = new JFrame(this.gc);
    private boolean vitorioso;
    private JPanel painelPremio;

    public TelaPremiacao() {
    }

    public TelaPremiacao(boolean vitorioso) {
        this.vitorioso = vitorioso;
    }

    @Override
    public void renderizar() {
        this.painelPremio = new JPanel();
        this.painelPremio.setLayout(new BoxLayout(this.painelPremio, BoxLayout.Y_AXIS));
        this.painelPremio.add(new JLabel(this.isVitorioso() ? this.vitoria : this.derrota));
        this.painelPremio.add(new JLabel(this.isVitorioso() ? Constantes.VITORIA : Constantes.DERROTA, JLabel.CENTER));
        this.telaPrincipal.add(this.painelPremio);
        this.notifica(null);
    }

    @Override
    public void notifica(String message) {
        JOptionPane.showConfirmDialog(null, this.painelPremio, "Premiacao", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
//        if (premiacao == 0) {
//            ControladorGeral.getInstance().desconectarAction();
//        }
    }

    public boolean isVitorioso() {
        return this.vitorioso;
    }

    public void setVitorioso(boolean vitorioso) {
        this.vitorioso = vitorioso;
    }
}
