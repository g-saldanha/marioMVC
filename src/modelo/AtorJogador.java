package modelo;

public class AtorJogador {
    private Posicao posicao;
    private int energia;
    private boolean ehSuaVez;
    private boolean passouCheckpoint;

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

    public boolean isEhSuaVez() {
        return this.ehSuaVez;
    }

    public void setEhSuaVez(boolean ehSuaVez) {
        this.ehSuaVez = ehSuaVez;
    }

    public boolean isPassouCheckpoint() {
        return this.passouCheckpoint;
    }

    public void setPassouCheckpoint(boolean passouCheckpoint) {
        this.passouCheckpoint = passouCheckpoint;
    }
}
