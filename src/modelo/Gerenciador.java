package modelo;

import controlador.ControladorGeral;
import utils.Constantes;
import visao.TelaEscolherJogadores;
import visao.TelaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

public class Gerenciador {

    public static TelaPrincipal telaPrincipal = new TelaPrincipal();
    public static AtorNetGames atorNetGames;
    public Pista pista;
    public final URL favico = this.getClass().getResource("/imagens/conectar.png");
    public Jogador jogadorPrincipal;
    public Jogador jogadorAdversario;
    private Premiacao premiacao = new Premiacao();
    public TelaEscolherJogadores telaEscolherPersonagem;
    public ImageIcon dado = new ImageIcon(this.getClass().getResource("/imagens/dado.png"));

    public Gerenciador() {
        ControladorGeral.getInstance().setGerenciador(this);
        this.jogadorPrincipal = new Jogador();
        telaPrincipal.getQuadroPrincipal().setIconImage(Toolkit.getDefaultToolkit().getImage(this.favico));
        atorNetGames = new AtorNetGames(this);
    }

    public static void main(String[] args) {
        new Gerenciador();
        iniciarGame();
    }

    private static void iniciarGame() {
        ControladorGeral.getInstance().setTelaPrincipal(getTelaPrincipal());
        ControladorGeral.getInstance().adicionaOuvintes();
        ControladorGeral.getInstance().getTelaPrincipal().renderizar();
    }

    public static TelaPrincipal getTelaPrincipal() {
        return telaPrincipal;
    }

    public static void conectar(String ipServidor, String nomeJogador) {
        String conectar = atorNetGames.conectar(ipServidor, nomeJogador);
        getTelaPrincipal().notifica(conectar);
    }

    public void desconectar() {
        String desconectar = atorNetGames.desconectar();
        getTelaPrincipal().notifica(desconectar);
        getTelaPrincipal().renderizar();
    }

    public void solicitarInicioDePartida() {
        atorNetGames.iniciarPartida();
    }

    public void escolherPersonagem() {
        this.telaEscolherPersonagem.renderizar();
    }

    public void movimentar() {
        int resultadoRolagemDado = this.rolarDado();
        this.getJogadorPrincipal().setMinhaVez(false);
        this.getJogadorAdversario().setMinhaVez(true);
        this.executarMovimento(this.jogadorPrincipal, resultadoRolagemDado);
        atorNetGames.enviarJogada(new Jogo(Constantes.MOVIMENTAR, resultadoRolagemDado));
    }

    public int rolarDado() {
        int modalDeRolarDado = JOptionPane.showConfirmDialog(null, Constantes.ROLAR_DADO, Constantes.MODAL_ROLAR_DADO, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, this.dado);
        int dadoRolado = 0;
        if (modalDeRolarDado == 0) {
            dadoRolado = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        }
        return dadoRolado;
    }

    public void ataque() {
        int resultadoRolagemDadoAtaque = this.rolarDado();
        int resultadoRolagemDadoDefesa = this.rolarDado();
        this.jogadorPrincipal.setMinhaVez(false);
        this.getJogadorPrincipal().setPassouCheckpoint(false);
        this.executarAtaque(this.jogadorAdversario, resultadoRolagemDadoAtaque, resultadoRolagemDadoDefesa);
        atorNetGames.enviarJogada(new Jogo(Constantes.ATACAR, resultadoRolagemDadoAtaque, resultadoRolagemDadoDefesa));
    }

    public void defesa(Jogador jogadoAtacado, int resultadoAtaque, int resultadoRolagemDadoDefesa) {
        int calculoDano = resultadoAtaque - resultadoRolagemDadoDefesa;
        jogadoAtacado.setEnergia(jogadoAtacado.getEnergia() - (calculoDano <= 0 ? 0 : calculoDano));
        this.verificaVitoria();
    }

    public static void setTelaPrincipal(TelaPrincipal telaPrincipal) {
        Gerenciador.telaPrincipal = telaPrincipal;
    }

    private void criarPista() {
        if (this.jogadorPrincipal.isMinhaVez()) {
            this.pista = new Pista(this.jogadorPrincipal, this.jogadorAdversario);
        } else {
            this.pista = new Pista(this.jogadorAdversario, this.jogadorPrincipal);
        }
    }

    public void passouCheckpoint(Jogador jogador) {
        if (jogador.getPosicao().getColuna() >= 10) {
            jogador.setPassouCheckpoint(true);
        }
    }

    private void comecarJogo() {
        getTelaPrincipal().getQuadroPrincipal().setVisible(false);
        getTelaPrincipal().criaTelaJogo(this.pista);
    }

    public void executarMovimento(Jogador jogador, int valor) {
        this.pista.moveJogador(jogador, valor);
        this.passouCheckpoint(jogador);
        JOptionPane.showMessageDialog(null, String.format(Constantes.MOVIMENTO, jogador.getNome(), valor), Constantes.VALOR_DADO, JOptionPane.INFORMATION_MESSAGE, this.dado);
        this.verificaVitoria();
    }

    private void verificaVitoria() {
        String verificacao = this.premiacao.verificaGanhador(this.jogadorPrincipal);
        if (verificacao.equals(Constantes.VITORIA)) {
            atorNetGames.enviarJogada(new Jogo(Constantes.VITORIA, 0));
        }
    }

    private void executarAtaque(Jogador jogadorAtacado, int resultadoRolagemDadoAtaque, int resultadoRolagemDadoDefesa) {
        this.defesa(jogadorAtacado, resultadoRolagemDadoAtaque, resultadoRolagemDadoDefesa);
        int dano = resultadoRolagemDadoDefesa - resultadoRolagemDadoAtaque;
        JOptionPane.showMessageDialog(null, String.format(Constantes.DANO, jogadorAtacado.getNome(), dano >= 0 ? 0 : dano), Constantes.VALOR_DADO, JOptionPane.INFORMATION_MESSAGE, this.dado);
    }

    public void conectarOption() {
        JTextField username = new JTextField();
        JTextField conection = new JTextField("localhost");
        Object[] message = {
                "Usuario:", username,
                "Conexao:", conection
        };

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/conectar.png"));
        int option = JOptionPane.showConfirmDialog(null, message, Constantes.CONECTAR, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (option == JOptionPane.OK_OPTION) {
            if (!username.getText().isEmpty() && !conection.getText().isEmpty()) {
                Gerenciador.conectar(conection.getText(), username.getText());
            } else {
                getTelaPrincipal().notifica(Constantes.CAMPOS_VAZIOS);
            }
        }
    }

    public void comecouPartida() {
        this.criarPista();
        this.comecarJogo();
        this.atualizar();
    }

    public void setPista(Pista pista) {
        this.pista = pista;
    }

    public Jogador getJogadorPrincipal() {
        return this.jogadorPrincipal;
    }

    public Jogador getJogadorAdversario() {
        return this.jogadorAdversario;
    }

    public void setJogadorAdversario(Jogador jogadorAdversario) {
        this.jogadorAdversario = jogadorAdversario;
    }

    public void executarJogada(Jogo jogada) {
        if (jogada.getTipoJogada().equals(Constantes.MOVIMENTAR)) {
            this.executarMovimento(this.jogadorAdversario, jogada.getValorDadoMovimentoAtaque());
        } else if (jogada.getTipoJogada().equals(Constantes.ATACAR)) {
            this.executarAtaque(this.jogadorPrincipal, jogada.getValorDadoMovimentoAtaque(), jogada.getValorDadoDefesa());
        } else if (jogada.getTipoJogada().equals(Constantes.VITORIA)) {
            this.executarPremiacao();
        }
        this.atualizar();
    }

    private void executarPremiacao() {
        this.premiacao.notificaPerdedor(this.jogadorPrincipal);
        ControladorGeral.getInstance().desconectarAction();
    }

    public void atualizar() {
        ControladorGeral.getInstance().atualizarBotoes();
        getTelaPrincipal().atualizar(this.pista);
    }

    public void adicionarPersonagem(int opcao) {
        this.getJogadorPrincipal().adicionarFotoJogador(opcao);
    }
}

