package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pista {
    private List<Posicao> listaDePosicoes;
    private List<Jogador> listaDejogadores;

    public Pista(Jogador jogador1, Jogador jogador2) {
        this.listaDePosicoes = new ArrayList<>();
        this.listaDejogadores = Arrays.asList(jogador1, jogador2);
        this.adicionarFaixa1();
        this.adicionarFaixa2();
        this.colocaJogadoresInicioPartida(this.listaDejogadores.get(0), this.listaDejogadores.get(1));
    }

    private void adicionarFaixa2() {
        for (int i = 1; i <= 20; i++) {
            this.listaDePosicoes.add(new Posicao(2, i));
        }
    }

    private void adicionarFaixa1() {
        for (int i = 1; i <= 20; i++) {
            this.listaDePosicoes.add(new Posicao(1, i));
        }
    }

    public void moveJogador(Jogador jogador, int novaPosicao) {
        Posicao posicaoJogadorAtual = this.pegaPosicao(jogador.getPosicao().getLinha(), jogador.getPosicao().getColuna());
        posicaoJogadorAtual.setJogador(null);

        Posicao posicaoJogadorMoveu = this.pegaPosicao(jogador.getPosicao().getLinha(), jogador.getPosicao().getColuna() + novaPosicao);
        jogador.setPosicao(null);
        jogador.setPosicao(posicaoJogadorMoveu);
        posicaoJogadorMoveu.setJogador(jogador);
    }

    public void colocaJogadoresInicioPartida(Jogador jogador1, Jogador jogador2) {
        Posicao posicaoJogador1 = this.pegaPosicao(1, 1);
        posicaoJogador1.setJogador(jogador1);
        jogador1.setPosicao(posicaoJogador1);

        Posicao posicaoJogador2 = this.pegaPosicao(2, 1);
        posicaoJogador2.setJogador(jogador2);
        jogador2.setPosicao(posicaoJogador2);
    }

    public Posicao pegaPosicao(int linha, int coluna) {
        if (coluna <= 20) {
            return this.listaDePosicoes.get(linha == 2 ? 19 + coluna : coluna - 1);
        } else {
            return this.listaDePosicoes.get(linha == 2 ? 19 : 39);
        }
    }

    public List<Posicao> getListaDePosicoes() {
        return this.listaDePosicoes;
    }

    public void setListaDePosicoes(List<Posicao> listaDePosicoes) {
        this.listaDePosicoes = listaDePosicoes;
    }

    public List<Jogador> getListaDejogadores() {
        return this.listaDejogadores;
    }

    public void setListaDejogadores(List<Jogador> listaDejogadores) {
        this.listaDejogadores = listaDejogadores;
    }

    public Jogador pegaJogadorDaVez() {
        if (this.getListaDejogadores().get(0).isMinhaVez()) {
            return this.getListaDejogadores().get(0);
        } else {
            return this.getListaDejogadores().get(1);
        }
    }
}
