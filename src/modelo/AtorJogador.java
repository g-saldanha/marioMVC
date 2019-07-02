package modelo;

import javax.swing.*;
import java.io.Serializable;

public class AtorJogador implements Serializable {
    private static final long serialVersionUID = 6506063253267916065L;
    private String nome;
    private boolean minhaVez;
    private Posicao posicao;
    private int energia;
    private boolean passouCheckpoint = false;
    private ImageIcon fotoJogador;

    public Posicao getPosicao() {
        return this.posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public int getEnergia() {
        return this.energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public boolean isPassouCheckpoint() {
        return this.passouCheckpoint;
    }

    public void setPassouCheckpoint(boolean passouCheckpoint) {
        this.passouCheckpoint = passouCheckpoint;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isMinhaVez() {
        return this.minhaVez;
    }

    public void setMinhaVez(boolean minhaVez) {
        this.minhaVez = minhaVez;
    }

    public ImageIcon getFotoJogador() {
        return this.fotoJogador;
    }

    public void setFotoJogador(ImageIcon fotoJogador) {
        this.fotoJogador = fotoJogador;
    }

    public void iniciarJogador(boolean posicao) {
        this.setEnergia(100);
        if (posicao) {
            this.setMinhaVez(true);
            this.setFotoJogador(new ImageIcon(this.getClass().getResource("/imagens/mario.jpg")));
        } else {
            this.setMinhaVez(false);
            this.setFotoJogador(new ImageIcon(this.getClass().getResource("/imagens/luigi.jpg")));
        }
    }
}
