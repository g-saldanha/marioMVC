package controlador;

import modelo.BarraEnergia;
import visao.BotaoAtacar;
import visao.BotaoMovimentar;
import visao.*;

import javax.swing.*;
import java.util.HashMap;

public class CTelas {
    private static final String TELA_PRINCIPAL = "TELA_PRINCIPAL";
    private static final String TELA_JOGADORES = "TELA_JOGADORES";
    private static final String TELA_MODAL = "TELA MODAL";
    private static final String TELA_PREMIACAO = "TELA_PREMIACAO";
    private static CTelas cTelas = new CTelas();
    private HashMap<String, ITela> telas;
    private BotaoAtacar botaoAtacar = new BotaoAtacar();
    private BotaoMovimentar botaoMovimentar = new BotaoMovimentar();
    private TelaPrincipal telaPrincipal= new TelaPrincipal();
    private BarraEnergia barraEnergia =  new BarraEnergia();

    public static CTelas getInstance() {
        return cTelas;
    }

    private CTelas() {
        this.telas = new HashMap<>();
        this.telas.put(CTelas.TELA_PRINCIPAL, new TelaPrincipal());
        this.telas.put(CTelas.TELA_JOGADORES, new TelaEscolherJogadores());
        this.telas.put(CTelas.TELA_MODAL, new TelaModal());
        this.telas.put(CTelas.TELA_PREMIACAO, new TelaPremiacao());
    }

    public void pintarTela() {
        telaPrincipal.renderizar();
    }

    public void pintarTelaDeJogo(){
        telaPrincipal.renderizarTelaJogo();
    }

    public void notifica(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public BotaoAtacar enviarBotaoAtaque() {
        return this.botaoAtacar.getBotaoAtacar();
    }

    public BotaoMovimentar enviarBotaoMovimentar() {
        return this.botaoMovimentar.getBotaoMovimentar();
    }

    public BarraEnergia enviarBarraDeEnergia() {
        return barraEnergia.getBarraDeEnergia();
    }
}
