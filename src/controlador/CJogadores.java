package controlador;

import modelo.Jogador;

import java.util.ArrayList;
import java.util.List;

public class CJogadores {
    private static CJogadores cJogadores = new CJogadores();
    private Jogador jogador1;
    private Jogador jogador2;
    private List<Jogador> jogadores;

    public static CJogadores getInstance() {
        return cJogadores;
    }

    private CJogadores() {
        this.jogadores = new ArrayList<>();
        this.jogador1 = new Jogador();
        this.jogador2 = new Jogador();
        this.jogadores.add(this.jogador1);
        this.jogadores.add(this.jogador2);
    }

    public Jogador getJogador1() {
        return this.jogador1;
    }

    public void setJogador1(Jogador jogador1) {
        this.jogador1 = jogador1;
    }

    public Jogador getJogador2() {
        return this.jogador2;
    }

    public void setJogador2(Jogador jogador2) {
        this.jogador2 = jogador2;
    }
}
