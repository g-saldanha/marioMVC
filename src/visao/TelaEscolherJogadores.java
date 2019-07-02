package visao;

import controlador.ControladorGeral;

import javax.swing.*;

public class TelaEscolherJogadores implements ITela {
    private int opcao;
    private Object[] opcoes;

    @Override
    public void renderizar() {

        this.notifica(null);
    }

    @Override
    public void notifica(String message) {
        this.opcao = JOptionPane.showOptionDialog(null, "Escolha seu perfonagem", "Escolha de Personagem", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, this.opcoes, this.opcoes[0]);
        ControladorGeral.getInstance().enviaPersonagem(this.opcao);
    }
}
