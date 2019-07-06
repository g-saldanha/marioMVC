package modelo;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.*;
import controlador.ControladorGeral;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import utils.Constantes;

import javax.swing.*;

public class AtorNetGames implements OuvidorProxy {

    private static final long serialVersionUID = -6720697503377705787L;
    private transient Proxy proxy;
    private transient Logger logger = Logger.getLogger(AtorNetGames.class);
    private transient Gerenciador gerenciador;

    public AtorNetGames(Gerenciador gerenciador) {
        super();
        this.gerenciador = gerenciador;
        this.proxy = Proxy.getInstance();
        this.proxy.addOuvinte(this);
        BasicConfigurator.configure();
    }

    public String conectar(String ipServidor, String nomeJogador) {
        String sucesso;
        try {
            this.proxy.conectar(ipServidor, nomeJogador);
            this.gerenciador.getJogadorPrincipal().setNome(nomeJogador);
            this.gerenciador.setJogadorAdversario(new Jogador());
            sucesso = Constantes.MENSAGEM_CONECTADO;
        } catch (JahConectadoException | NaoPossivelConectarException | ArquivoMultiplayerException e) {
            sucesso = e.getMessage();
            this.logger.error(sucesso);
        }
        return sucesso;
    }

    public void iniciarPartida() {
        try {
            this.proxy.iniciarPartida(2);
        } catch (NaoConectadoException e) {
            this.logger.info(e.getMessage(), e);
            Gerenciador.getTelaPrincipal().notifica(e.getMessage());
        }
    }

    @Override
    public void iniciarNovaPartida(Integer posicao) {
        boolean jogadorComeca = posicao == 1;
        this.gerenciador.getJogadorPrincipal().iniciarJogador(jogadorComeca);
        this.gerenciador.getJogadorAdversario().setNome(this.obterNomeAdversario());
        this.gerenciador.getJogadorAdversario().iniciarJogador(!jogadorComeca);
        ControladorGeral.getInstance().adicionarOuvintesAcoes();
        this.gerenciador.comecouPartida();
    }


    @Override
    public void finalizarPartidaComErro(String mensagemPartidaComErro) {
        JOptionPane.showMessageDialog(null, mensagemPartidaComErro);
    }

    @Override
    public void receberMensagem(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void enviarJogada(Jogada jogada) {
        try {
            this.proxy.enviaJogada(jogada);
        } catch (NaoJogandoException e) {
            this.logger.error(e.getMessage());
        }
    }

    @Override
    public void receberJogada(Jogada jogada) {
        this.gerenciador.getJogadorPrincipal().setMinhaVez(true);
        this.gerenciador.getJogadorAdversario().setMinhaVez(false);
        this.gerenciador.executarJogada((Jogo) jogada);
    }

    @Override
    public void tratarConexaoPerdida() {
        JOptionPane.showMessageDialog(null, "Conexao perdida");
    }

    @Override
    public void tratarPartidaNaoIniciada(String mensagemPartidaNaoIniciada) {
        JOptionPane.showMessageDialog(null, mensagemPartidaNaoIniciada);
    }


    public String desconectar() {
        try {
            this.proxy.desconectar();
            this.gerenciador.getJogadorPrincipal().setNome(null);
            this.gerenciador.getJogadorPrincipal().setPassouCheckpoint(false);
            this.gerenciador.getJogadorPrincipal().setEnergia(0);
            this.gerenciador.getJogadorPrincipal().setPosicao(null);
            this.gerenciador.getJogadorPrincipal().setMinhaVez(false);
            return Constantes.DESCONECTADO;
        } catch (NaoConectadoException e) {
            this.logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    public String obterNomeAdversario() {
        if (this.gerenciador.getJogadorPrincipal().isMinhaVez()) {
            return this.proxy.obterNomeAdversario(2);
        } else {
            return this.proxy.obterNomeAdversario(1);
        }
    }
}
