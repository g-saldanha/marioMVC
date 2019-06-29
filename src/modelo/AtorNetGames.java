package modelo;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import utils.Constantes;

import javax.swing.*;

import static org.apache.log4j.Priority.WARN;

public class AtorNetGames implements OuvidorProxy {

    private transient AtorJogador atorJogador;
    private transient Proxy proxy;
    private transient Logger logger = Logger.getLogger(AtorNetGames.class);

    public AtorNetGames(AtorJogador atorJogador) {
        super();
        this.proxy = Proxy.getInstance();
        this.proxy.addOuvinte(this);
        this.atorJogador = atorJogador;
        BasicConfigurator.configure();
    }

    public String conectar(String ipServidor, String nomeJogador) {
        String sucesso;
        try {
            this.proxy.conectar(ipServidor, nomeJogador);
            sucesso = Constantes.MENSAGEM_CONECTADO;
            this.atorJogador.setNome(nomeJogador);
            if (this.proxy.obterNomeAdversarios().isEmpty()) {
                this.atorJogador.setMinhaVez(true);
            }
        } catch (JahConectadoException | NaoPossivelConectarException | ArquivoMultiplayerException e) {
            sucesso = e.getMessage();
            this.logger.log(Priority.INFO, sucesso + e.getMessage());
        }
        return sucesso;
    }

    public String iniciarPartida() {
        try {
            this.proxy.iniciarPartida(2);
            if (this.proxy.obterNomeAdversarios() != null && !this.proxy.obterNomeAdversarios().isEmpty()) {
                return Constantes.PARTIDA_INICIADA;
            } else {
                return "0";
            }
        } catch (NaoConectadoException e) {
            this.logger.info(e.getMessage(), e);
            return e.getMessage();
        }
    }

    @Override
    public void iniciarNovaPartida(Integer posicao) {
        this.atorJogador.setMinhaVez(posicao == 1);
        this.proxy.iniciarNovaPartida(0);
    }


    @Override
    public void finalizarPartidaComErro(String mensagemPartidaComErro) {
        JOptionPane.showMessageDialog(null, mensagemPartidaComErro);
    }

    @Override
    public void receberMensagem(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void receberJogada(Jogada jogada) {
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
            this.atorJogador.setNome(null);
            this.atorJogador.setPassouCheckpoint(false);
            this.atorJogador.setEnergia(0);
            this.atorJogador.setPosicao(null);
            this.atorJogador.setMinhaVez(false);
            return Constantes.DESCONECTADO;
        } catch (NaoConectadoException e) {
            this.logger.log(WARN, e.getMessage());
            return e.getMessage();
        }
    }

    public String obterNomeAdversario() {
        if (this.atorJogador.isMinhaVez()) {
            return this.proxy.obterNomeAdversario(2);
        } else {
            return this.proxy.obterNomeAdversario(1);
        }
    }
}
