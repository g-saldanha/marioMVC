package visao;

import modelo.AtorJogador;
import modelo.BarraEnergia;
import modelo.Pista;
import utils.Constantes;

import javax.swing.*;
import java.awt.*;

public class PainelInformacoes extends JPanel {
    private static final long serialVersionUID = 8622264906893469320L;
    private BarraEnergia barraDeEnergia = new BarraEnergia();
    private JLabel informacoesJogadorVez;
    private JLabel informacoesPodeAtacar;

    public void renderizar(Pista pista) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        AtorJogador jogador = pista.pegaJogadorDaVez();

        this.add(this.getInfoTitulo(), gbc);
        this.add(new JLabel(" "), gbc);
        this.informacoesJogadorVez = this.getJogadorDaVezTitulo(jogador.getNome());
        this.barraDeEnergia.setValue(jogador.getEnergia());
        this.add(this.informacoesJogadorVez, gbc);
        this.add(new JLabel(" "), gbc);
        this.add(this.barraDeEnergia, gbc);
        this.add(new JLabel(" "), gbc);
        this.informacoesPodeAtacar = new JLabel(Constantes.PODE_ATACAR + this.getPodeAtacar(jogador.isPassouCheckpoint()));
        this.add(this.informacoesPodeAtacar, gbc);
    }

    public void atualiza(Pista pista) {
        AtorJogador jogador = pista.pegaJogadorDaVez();
        this.informacoesJogadorVez = this.getJogadorDaVezTitulo(jogador.getNome());
        this.barraDeEnergia.setValue(jogador.getEnergia());
        this.informacoesPodeAtacar.setText(Constantes.PODE_ATACAR + this.getPodeAtacar(jogador.isPassouCheckpoint()));
        this.repaint();
    }

    public JLabel getInfoTitulo() {
        return new JLabel(Constantes.INFORMACOES);
    }

    public JLabel getTurnoTitulo() {
        return new JLabel(Constantes.TURNO);
    }

    public JLabel getJogadorDaVezTitulo(String nome) {
        return new JLabel(Constantes.JOGADOR_DA_VEZ + ": " + nome);
    }

    public JLabel getPistaTitulo() {
        return new JLabel(Constantes.PISTA);
    }

    private BarraEnergia getBarraDeEnergia() {
        return this.barraDeEnergia;
    }


    private String getPodeAtacar(boolean passouCheckpoint) {
        return passouCheckpoint ? Constantes.SIM : Constantes.NAO;
    }
}
