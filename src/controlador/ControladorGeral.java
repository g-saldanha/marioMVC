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
        JMenuItem sair = getTelaPrincipal().getMenu().getMenu(0).getItem(4);
        sair.addActionListener(acao -> System.exit(0));
        JMenuItem conectar = getTelaPrincipal().getMenu().getMenu(0).getItem(0);
        conectar.addActionListener(acao -> getGerenciador().conectarOption());
        JMenuItem iniciar = getTelaPrincipal().getMenu().getMenu(0).getItem(1);
        iniciar.addActionListener(acao -> getGerenciador().solicitarInicioDePartida());
        JMenuItem desconectar = getTelaPrincipal().getMenu().getMenu(0).getItem(3);
        desconectar.addActionListener(acao -> getGerenciador().desconectar());
    }

    public TelaPrincipal getTelaPrincipal() {
        return telaPrincipal;
    }

    public void setTelaPrincipal(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    public Gerenciador getGerenciador() {
        return gerenciador;
    }

    public void setGerenciador(Gerenciador gerenciador) {
        this.gerenciador = gerenciador;
    }
}
