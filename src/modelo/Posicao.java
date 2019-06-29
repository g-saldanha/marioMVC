package modelo;

import javax.swing.*;

public class Posicao {
    private int coluna;
    private int linha;
    private AtorJogador jogador;
    private boolean checkPoint;
    private ImageIcon imagem;

    public Posicao(int linha, int coluna) {
        this.setColuna(coluna);
        this.setLinha(linha);
        if (coluna == 10) {
            this.setImagem(new ImageIcon(this.getClass().getResource("/imagens/checkpoint.jpg")));
        } else if (coluna == 20) {
            this.setImagem(new ImageIcon(this.getClass().getResource("/imagens/chegada.jpg")));
        } else {
            this.setImagem(new ImageIcon(this.getClass().getResource("/imagens/faixa.jpg")));
        }
    }

    public boolean isOcupada() {
        return this.getJogador() != null;
    }

    public int getColuna() {
        return this.coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getLinha() {
        return this.linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public AtorJogador getJogador() {
        return this.jogador;
    }

    public void setJogador(AtorJogador jogador) {
        this.jogador = jogador;
    }

    public boolean isCheckPoint() {
        return this.checkPoint;
    }

    public void setCheckPoint(boolean checkPoint) {
        this.checkPoint = checkPoint;
    }

    public ImageIcon getImagem() {
        return this.imagem;
    }

    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    }
}
