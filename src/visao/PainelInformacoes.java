package visao;

import modelo.BarraEnergia;
import utils.Constantes;

import javax.swing.*;
import java.awt.*;

public class PainelInformacoes extends JPanel {
    private BarraEnergia barraDeEnergia = new BarraEnergia();

    public void renderizar() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        this.add(this.getInfoTitulo(), gbc);
        this.add(new JLabel(" "), gbc);
        JLabel informacoesJogadorVez = this.getJogadorDaVezTitulo();

        this.add(informacoesJogadorVez, gbc);
        this.add(new JLabel(" "), gbc);
        this.add(this.barraDeEnergia, gbc);
        this.add(new JLabel(" "), gbc);
        this.add(new JLabel("Pode Atacar: " + this.getPodeAtacar()), gbc);
    }

    public JLabel getInfoTitulo() {
        return new JLabel(Constantes.INFORMACOES);
    }

    public JLabel getTurnoTitulo() {
        return new JLabel(Constantes.TURNO);
    }

    public JLabel getJogadorDaVezTitulo() {
        return new JLabel(Constantes.JOGADOR_DA_VEZ + ": " + this.getJogadorDaVez());
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
