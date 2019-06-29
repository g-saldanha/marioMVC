package modelo;

import br.ufsc.inf.leobr.cliente.Jogada;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pista implements Jogada {
    private List<Posicao> listaDePosicoes;
    private List<AtorJogador> listaDejogadores;

    public Pista(AtorJogador jogador1, AtorJogador jogador2) {
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

    public void moveJogador(AtorJogador atorJogador, Posicao posicao1, Posicao posicao2) {
        Posicao posicaoJogadorAtual = this.pegaPosicao(posicao1.getLinha(), posicao1.getColuna());
        posicaoJogadorAtual.setJogador(null);

        Posicao posicaoJogadorMoveu = this.pegaPosicao(posicao2.getLinha(), posicao2.getColuna());
        posicaoJogadorMoveu.setJogador(atorJogador);
    }

    public void colocaJogadoresInicioPartida(AtorJogador jogador1, AtorJogador jogador2) {
        Posicao posicaoJogador1 = this.pegaPosicao(1, 1);
        jogador1.setFotoJogador(new ImageIcon(this.getClass().getResource("/imagens/mario.jpg")));
        posicaoJogador1.setImagem(jogador1.getFotoJogador());
        posicaoJogador1.setJogador(jogador1);

        Posicao posicaoJogador2 = this.pegaPosicao(2, 1);
        jogador2.setFotoJogador(new ImageIcon(this.getClass().getResource("/imagens/luigi.jpg")));
        posicaoJogador2.setImagem(jogador2.getFotoJogador());
        posicaoJogador2.setJogador(jogador2);
    }

    public Posicao pegaPosicao(int linha, int coluna) {
        for (Posicao posicao :
                this.listaDePosicoes) {
            if (posicao.getLinha() == linha && posicao.getColuna() == coluna) {
                return posicao;
            }
        }
        return null;
    }

    public List<Posicao> getListaDePosicoes() {
        return this.listaDePosicoes;
    }

    public void setListaDePosicoes(List<Posicao> listaDePosicoes) {
        this.listaDePosicoes = listaDePosicoes;
    }

    public List<AtorJogador> getListaDejogadores() {
        return this.listaDejogadores;
    }

    public void setListaDejogadores(List<AtorJogador> listaDejogadores) {
        this.listaDejogadores = listaDejogadores;
    }

    public AtorJogador jogadorDaVez() {
        for (AtorJogador jogadorDaVez :
                this.listaDejogadores) {
            if (jogadorDaVez.isMinhaVez()) {
                return jogadorDaVez;
            }
        }
        return null;
    }
}
