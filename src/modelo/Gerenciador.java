package modelo;

import controlador.ControladorGeral;
import visao.TelaPrincipal;

public class Gerenciador {

	public static TelaPrincipal telaPrincipal = new TelaPrincipal();
	public AtorNetGames atorNetGames = new AtorNetGames();

	public static void main(String[] args) {
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

	public static void conectar() {

	}

	public void iniciarPartida() {

	}

	public void solicitarInicioDePartida() {

	}

	public void passarCheckpoint() {

	}

	public void desconectar() {
		String desconectar = getAtorNetGames().desconectar();
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

	public AtorNetGames getAtorNetGames() {
		return atorNetGames;
	}

	public void setAtorNetGames(AtorNetGames atorNetGames) {
		this.atorNetGames = atorNetGames;
	}
}
