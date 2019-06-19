package visao;

import utils.Constantes;

import javax.swing.*;

public class PainelInformacoes extends JPanel {
    public PainelInformacoes() {
        this.add(this.getInfoTitulo());
        this.add(this.getTurnoTitulo());
        this.add(this.getJogadorDaVezTitulo());
        this.add(this.getPistaTitulo());
    }

    public JLabel getInfoTitulo() {
        return new JLabel(Constantes.INFORMACOES);
    }

    public JLabel getTurnoTitulo() {
        return new JLabel(Constantes.TURNO);
    }

    public JLabel getJogadorDaVezTitulo() {
        return new JLabel(Constantes.JOGADOR_DA_VEZ);
    }

    public JLabel getPistaTitulo() {
        return new JLabel(Constantes.PISTA);
    }
}
