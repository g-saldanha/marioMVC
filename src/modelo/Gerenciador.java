package modelo;

import controlador.ControladorGeral;
import utils.Constantes;
import visao.TelaPrincipal;

import javax.swing.*;

public class Gerenciador {

    public static TelaPrincipal telaPrincipal = new TelaPrincipal();
    public static AtorNetGames atorNetGames;
    public Pista pista;
    public AtorJogador jogadorPrincipal;
    public AtorJogador jogadorAdversario;

    public Gerenciador() {
        ControladorGeral.getInstance().setGerenciador(this);
        this.jogadorPrincipal = new AtorJogador();
        atorNetGames = new AtorNetGames(this.jogadorPrincipal);
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

    public void solicitarInicioDePartida() {
        String iniciarPartida = atorNetGames.iniciarPartida();
        if (iniciarPartida.equals(Constantes.PARTIDA_INICIADA)) {
            this.jogadorAdversario = new AtorJogador();
            this.jogadorAdversario.setNome(atorNetGames.obterNomeAdversario());
            this.criarPista();
            this.comecarJogo();
        } else if (!iniciarPartida.equals("0")) {
            getTelaPrincipal().notifica(iniciarPartida);
        }
    }

    private void comecarJogo() {
        getTelaPrincipal().getQuadroPrincipal().setVisible(false);
        getTelaPrincipal().renderizarTelaJogo(this.pista);
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

    public void desconectar() {
        String desconectar = atorNetGames.desconectar();
        getTelaPrincipal().notifica(desconectar);
        getTelaPrincipal().renderizar();
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
        JTextField conection = new JTextField("localhost");
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
