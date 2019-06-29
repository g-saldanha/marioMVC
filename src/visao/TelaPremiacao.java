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

    public TelaPremiacao() {
    }

    public TelaPremiacao(boolean vitorioso) {
        this.vitorioso = vitorioso;
    }

    @Override
    public void renderizar() {
        JPanel painelPremio = new JPanel();
        painelPremio.setLayout(new BoxLayout(painelPremio, BoxLayout.Y_AXIS));
        painelPremio.add(new JLabel(this.isVitorioso() ? this.vitoria : this.derrota));
        painelPremio.add(new JLabel(this.isVitorioso() ? Constantes.VITORIA : Constantes.DERROTA, JLabel.CENTER));
        this.telaPrincipal.add(painelPremio);
        JOptionPane.showMessageDialog(painelPremio, painelPremio);
    }

    @Override
    public void notifica(String message) {

    }

    public boolean isVitorioso() {
        return this.vitorioso;
    }

    public void setVitorioso(boolean vitorioso) {
        this.vitorioso = vitorioso;
    }
}
