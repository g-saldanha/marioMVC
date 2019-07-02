package controlador;

import modelo.Gerenciador;
import visao.TelaPrincipal;

import javax.swing.*;

public class ControladorGeral {
    private static ControladorGeral ourInstance = new ControladorGeral();

    public static ControladorGeral getInstance() {
        return ourInstance;
    }

    public TelaPrincipal telaPrincipal;
    public Gerenciador gerenciador;

    public ControladorGeral() {
    }

    public void adicionaOuvintes() {
        JMenuItem sair = this.getTelaPrincipal().getMenu().getMenu(0).getItem(4);
        sair.addActionListener(acao -> System.exit(0));
        JMenuItem conectar = this.getTelaPrincipal().getMenu().getMenu(0).getItem(0);
        conectar.addActionListener(acao -> this.getGerenciador().conectarOption());
        JMenuItem iniciar = this.getTelaPrincipal().getMenu().getMenu(0).getItem(1);
        iniciar.addActionListener(acao -> this.getGerenciador().solicitarInicioDePartida());
        JMenuItem desconectar = this.getTelaPrincipal().getMenu().getMenu(0).getItem(3);
        desconectar.addActionListener(acao -> this.getGerenciador().desconectar());
    }

    public void atualizarBotoes() {
        boolean minhaVez = this.gerenciador.getJogadorPrincipal().isMinhaVez();
        this.telaPrincipal.getBotaoAtacar().setEnabled(minhaVez && this.gerenciador.getJogadorPrincipal().isPassouCheckpoint());
        this.telaPrincipal.getBotaoMovimentar().setEnabled(minhaVez);
    }

    public void adicionarOuvintesAcoes() {
        this.telaPrincipal.getBotaoMovimentar().addActionListener(
                acao -> {
                    this.gerenciador.movimentar();
                    this.gerenciador.atualizar();
                }
        );

        this.telaPrincipal.getBotaoAtacar().addActionListener(
                acao -> {
                    this.gerenciador.ataque();
                    this.gerenciador.atualizar();
                }
        );
    }


    public TelaPrincipal getTelaPrincipal() {
        return this.telaPrincipal;
    }

    public void setTelaPrincipal(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    public Gerenciador getGerenciador() {
        return this.gerenciador;
    }

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }

    public void desconectarAction() {
        this.getGerenciador().desconectar();
    }

    public void enviaPersonagem(int opcao) {
        this.gerenciador.adicionarPersonagem(opcao);
    }
}
