package modelo;

import controlador.ControladorGeral;
import utils.Constantes;
import visao.TelaPrincipal;

import javax.swing.*;

public class Gerenciador {

    public static TelaPrincipal telaPrincipal = new TelaPrincipal();

    public Gerenciador() {
        ControladorGeral.getInstance().setGerenciador(this);
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
        String conectar = AtorNetGames.getInstance().conectar(ipServidor, nomeJogador);
        getTelaPrincipal().notifica(conectar);
    }

    public void solicitarInicioDePartida() {
        String iniciarPartida = AtorNetGames.getInstance().iniciarPartida();
        getTelaPrincipal().notifica(iniciarPartida);
    }

    public void passarCheckpoint() {

    }

    public void desconectar() {
        String desconectar = AtorNetGames.getInstance().desconectar();
        getTelaPrincipal().notifica(desconectar);
    }

    public void escolherPersonagem() {

    }

    public void movimentar() {

    }

    public void rolarDado() {

    }

    public void ataque() {

    }

    public void defesa() {

    }

    public void conectarOption() {
        JTextField username = new JTextField();
        JTextField conection = new JTextField();
        Object[] message = {
                "Usuario:", username,
                "Conexao:", conection
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Conectar", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (!username.getText().isEmpty() && !conection.getText().isEmpty()) {
                Gerenciador.conectar(conection.getText(), username.getText());
            } else {
                getTelaPrincipal().notifica(Constantes.CAMPOS_VAZIOS);
            }
        }
    }
}
