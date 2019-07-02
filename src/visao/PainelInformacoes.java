package visao;

import modelo.BarraEnergia;
import modelo.Pista;
import utils.Constantes;

import javax.swing.*;
import java.awt.*;

public class PainelInformacoes extends JPanel {
    private static final long serialVersionUID = 8622264906893469320L;
    private BarraEnergia barraDeEnergia = new BarraEnergia();
    private JLabel informacoesJogadorVez;

    public void renderizar(Pista pista) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        this.add(this.getInfoTitulo(), gbc);
        this.add(new JLabel(" "), gbc);
        this.informacoesJogadorVez = this.getJogadorDaVezTitulo(pista);

        this.add(this.informacoesJogadorVez, gbc);
        this.add(new JLabel(" "), gbc);
        this.add(this.barraDeEnergia, gbc);
        this.add(new JLabel(" "), gbc);
        this.add(new JLabel("Pode Atacar: " + this.getPodeAtacar()), gbc);
    }

    public void atualiza(Pista pista) {
        this.informacoesJogadorVez = this.getJogadorDaVezTitulo(pista);
        // falta coisa
        this.repaint();
    }

    public JLabel getInfoTitulo() {
        return new JLabel(Constantes.INFORMACOES);
    }

    public JLabel getTurnoTitulo() {
        return new JLabel(Constantes.TURNO);
    }

    public JLabel getJogadorDaVezTitulo(Pista pista) {
        return new JLabel(Constantes.JOGADOR_DA_VEZ + ": " + pista.pegaJogadorDaVez().getNome());
    }

    public JLabel getPistaTitulo() {
        return new JLabel(Constantes.PISTA);
    }

    private BarraEnergia getBarraDeEnergia() {
        return this.barraDeEnergia;
    }

    private String getPodeAtacar() {
        return null;
    }

    private String getJogadorDaVez() {
        return null;
    }
}
