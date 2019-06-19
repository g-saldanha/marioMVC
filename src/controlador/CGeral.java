package controlador;

import modelo.AtorNetGames;
import modelo.Dado;
import utils.Constantes;

import javax.swing.*;

import org.apache.log4j.Logger;

public class CGeral {
    private Logger logger = Logger.getRootLogger();
    private static CGeral ourInstance = new CGeral();
    private CInformacoes cInformacoes;
    private CJogadores cJogadores;
    private CMenu cMenu;
    private CPista cPista;
    private CTelas cTelas;
    private Dado dado = new Dado();

    public static void setIsConectado(boolean isConectado) {
        CGeral.isConectado = isConectado;
    }

    private static boolean isConectado = false;

    public Dado getDado() {
        return this.dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public static CGeral getInstance() {
        return ourInstance;
    }

    private CGeral() {
        this.cInformacoes = CInformacoes.getInstance();
        this.cJogadores = CJogadores.getInstance();
        this.cPista = CPista.getInstance();
        this.cTelas = CTelas.getInstance();
    }

    public String listenerConectar(String ipServidor, String nomeJogador) {
        return AtorNetGames.getInstance().conectar(ipServidor, nomeJogador);
    }

    public void iniciarPartida() {
        if (!isConectado) {
            this.cTelas.notifica(Constantes.VOCE_JA_ESTA_CONECTADO);
        } else {
            try {
                AtorNetGames.getInstance().iniciarNovaPartida(2);
                this.cTelas.notifica(Constantes.PARIDA_INICIADA);
                cTelas.pintarTelaDeJogo();
            } catch (Exception e){
                logger.warn(e.getMessage(), e);
            }
        }
    }
}
