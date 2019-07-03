package modelo;

import controlador.ControladorGeral;
import utils.Constantes;
import visao.TelaEscolherJogadores;
import visao.TelaPrincipal;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class Gerenciador {

    public static TelaPrincipal telaPrincipal = new TelaPrincipal();
    public static AtorNetGames atorNetGames;
    public Pista pista;
    public AtorJogador jogadorPrincipal;
    public AtorJogador jogadorAdversario;
    private Premiacao premiacao = new Premiacao();
    public TelaEscolherJogadores telaEscolherPersonagem;

    public Gerenciador() {
        ControladorGeral.getInstance().setGerenciador(this);
        this.jogadorPrincipal = new AtorJogador();
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
        ImageIcon dado = new ImageIcon(this.getClass().getResource("/imagens/dado.png"));
        int modalDeRolarDado = JOptionPane.showConfirmDialog(null, Constantes.ROLAR_DADO, Constantes.MODAL_ROLAR_DADO, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, dado);
        int dadoRolado = 0;
        if (modalDeRolarDado == 0) {
            dadoRolado = ThreadLocalRandom.current().nextInt(1, 6 + 1);
            JOptionPane.showMessageDialog(null, Constantes.DANO + dadoRolado, Constantes.VALOR_DADO, JOptionPane.INFORMATION_MESSAGE, dado);
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

    public void defesa(AtorJogador jogadoAtacado, int resultadoAtaque, int resultadoRolagemDadoDefesa) {
        int calculoDano = resultadoAtaque - resultadoRolagemDadoDefesa;
        jogadoAtacado.setEnergia(jogadoAtacado.getEnergia() - (calculoDano < 0 ? 0 : calculoDano));
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

    public void passouCheckpoint(AtorJogador atorJogador) {
        if (atorJogador.getPosicao().getColuna() >= 10) {
            atorJogador.setPassouCheckpoint(true);
        }
    }

    private void comecarJogo() {
        getTelaPrincipal().getQuadroPrincipal().setVisible(false);
        getTelaPrincipal().criaTelaJogo(this.pista);
    }

    public void executarMovimento(AtorJogador jogador, int valor) {
        this.pista.moveJogador(jogador, valor);
        this.passouCheckpoint(jogador);
        this.verificaVitoria();
    }

    private void verificaVitoria() {
        this.premiacao.verificaGanhador(this.jogadorPrincipal);
    }

    private void executarAtaque(AtorJogador jogadoAtacado, int resultadoRolagemDadoAtaque, int resultadoRolagemDadoDefesa) {
        this.defesa(jogadoAtacado, resultadoRolagemDadoAtaque, resultadoRolagemDadoDefesa);
    }

    public void conectarOption() {
        JTextField username = new JTextField();
        JTextField conection = new JTextField("localhost");
        Object[] message = {
                "Usuario:", username,
                "Conexao:", conection
        };

        ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/conectar.png"));
        int option = JOptionPane.showConfirmDialog(null, message, "Conectar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
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

    public AtorJogador getJogadorPrincipal() {
        return this.jogadorPrincipal;
    }

    public AtorJogador getJogadorAdversario() {
        return this.jogadorAdversario;
    }

    public void setJogadorAdversario(AtorJogador jogadorAdversario) {
        this.jogadorAdversario = jogadorAdversario;
    }

    public void executarJogada(Jogo jogada) {
        if (jogada.getTipoJogada().equals(Constantes.MOVIMENTAR)) {
            this.executarMovimento(this.jogadorAdversario, jogada.getValorDadoMovimentoAtaque());
        } else if (jogada.getTipoJogada().equals(Constantes.ATACAR)) {
            this.executarAtaque(this.jogadorPrincipal, jogada.getValorDadoMovimentoAtaque(), jogada.getValorDadoDefesa());
        }
        this.atualizar();
    }

    public void atualizar() {
        ControladorGeral.getInstance().atualizarBotoes();
        getTelaPrincipal().atualizar(this.pista);
    }

    public void adicionarPersonagem(int opcao) {
        this.getJogadorPrincipal().adicionarFotoJogador(opcao);
    }
}

