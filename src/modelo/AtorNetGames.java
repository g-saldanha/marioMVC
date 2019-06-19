package modelo;

import static org.apache.log4j.Priority.WARN;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import controlador.CGeral;
import controlador.CTelas;
import utils.Constantes;

public class AtorNetGames implements OuvidorProxy {
    private static AtorNetGames ourInstance = new AtorNetGames();
    public final Proxy proxy = Proxy.getInstance();
    private Logger logger = Logger.getRootLogger();

    public AtorNetGames() {
        this.proxy.addOuvinte(this);
    }

    public static AtorNetGames getInstance() {
        return ourInstance;
    }

    @Override
    public void iniciarNovaPartida(Integer integer)  {
        try {
            this.proxy.iniciarPartida(2);
        } catch (NaoConectadoException e) {
            logger.info("Jogador não conectado");
            CTelas.getInstance().notifica(e.getMessage());
        }
    }

    @Override
    public void finalizarPartidaComErro(String s) {

    }

    @Override
    public void receberMensagem(String s) {

    }

    @Override
    public void receberJogada(Jogada jogada) {

    }

    @Override
    public void tratarConexaoPerdida() {

    }

    @Override
    public void tratarPartidaNaoIniciada(String s) {

    }

    public String conectar(String ipServidor, String nomeJogador) {
        String suxesso;
        try {
            this.proxy.conectar(ipServidor, nomeJogador);
            suxesso = "Conectou";
            CGeral.setIsConectado(true);
        } catch (JahConectadoException e) {
            suxesso = "Ja esta conectado";
            this.logger.log(Priority.INFO, suxesso + e.getMessage());
        } catch (NaoPossivelConectarException e) {
            suxesso = "Nao foi possivel conectar";
            this.logger.log(Priority.INFO, suxesso + e.getMessage());
        } catch (ArquivoMultiplayerException e) {
            suxesso = "Alguma exception que nao entendi";
            this.logger.log(Priority.INFO, suxesso + e.getMessage());
        }
        return suxesso;
    }

    public String  desconectar() {
        try {
            proxy.desconectar();
            return Constantes.DESCONECTADO;
        } catch (NaoConectadoException e) {
            this.logger.log(WARN, e.getMessage());
            return e.getMessage();
        }
    }
}
