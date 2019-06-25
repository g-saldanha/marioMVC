package modelo;

public class Posicao {
    private int coluna;
    private int linha;
    private boolean ocupada;

    public boolean isOcupada() {
        return this.ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
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

}
