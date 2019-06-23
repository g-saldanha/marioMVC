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

import static org.apache.log4j.Priority.WARN;

public class AtorNetGames implements OuvidorProxy {
    private static AtorNetGames ourInstance = new AtorNetGames();
    public final Proxy proxy = Proxy.getInstance();
    Logger logger = Logger.getLogger(AtorNetGames.class);


    public AtorNetGames() {
        this.proxy.addOuvinte(this);
        BasicConfigurator.configure();
    }

    public static AtorNetGames getInstance() {
        return ourInstance;
    }

    @Override
    public void iniciarNovaPartida(Integer integer) {
        this.proxy.iniciarNovaPartida(0);
    }

    public String iniciarPartida() {
        try {
            this.proxy.iniciarPartida(2);
            return Constantes.PARIDA_INICIADA;
        } catch (NaoConectadoException e) {
            this.logger.info(e.getMessage(), e);
            return e.getMessage();
        }
    }


    @Override
    public void finalizarPartidaComErro(String s) {
        this.proxy.finalizarPartidaComErro(s);
    }

    @Override
    public void receberMensagem(String s) {
        this.proxy.receberMensagem(s);
    }

    @Override
    public void receberJogada(Jogada jogada) {
    }

    @Override
    public void tratarConexaoPerdida() {
        this.proxy.tratarPerdaConexao();
    }

    @Override
    public void tratarPartidaNaoIniciada(String s) {
        this.proxy.tratarPartidaNaoInciada(s);
    }

    public String conectar(String ipServidor, String nomeJogador) {
        String sucesso;
        try {
            this.proxy.conectar(ipServidor, nomeJogador);
            sucesso = Constantes.MENSAGEM_CONECTADO;
        } catch (JahConectadoException | NaoPossivelConectarException | ArquivoMultiplayerException e) {
            sucesso = e.getMessage();
            this.logger.log(Priority.INFO, sucesso + e.getMessage());
        }
        return sucesso;
    }

    public String desconectar() {
        try {
            this.proxy.desconectar();
            return Constantes.DESCONECTADO;
        } catch (NaoConectadoException e) {
            this.logger.log(WARN, e.getMessage());
            return e.getMessage();
        }
    }
}
