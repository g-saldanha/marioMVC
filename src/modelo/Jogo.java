package modelo;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Jogo implements Jogada {
    private static final long serialVersionUID = 5555949523675617742L;
    private String tipoJogada;
    private int valorDadoMovimentoAtaque;
    private int valorDadoDefesa;


    public Jogo(String tipoJogada, int valorDadoMovimentoAtaque) {
        this.tipoJogada = tipoJogada;
        this.valorDadoMovimentoAtaque = valorDadoMovimentoAtaque;
    }

    public Jogo(String tipoJogada, int valorDadoMovimentoAtaque, int valorDadoDefesa) {
        this.tipoJogada = tipoJogada;
        this.valorDadoMovimentoAtaque = valorDadoMovimentoAtaque;
        this.valorDadoDefesa = valorDadoDefesa;
    }

    public int getValorDadoDefesa() {
        return this.valorDadoDefesa;
    }

    public void setValorDadoDefesa(int valorDadoDefesa) {
        this.valorDadoDefesa = valorDadoDefesa;
    }

    public String getTipoJogada() {
        return this.tipoJogada;
    }

    public void setTipoJogada(String tipoJogada) {
        this.tipoJogada = tipoJogada;
    }

    public int getValorDadoMovimentoAtaque() {
        return this.valorDadoMovimentoAtaque;
    }

    public void setValorDadoMovimentoAtaque(int valorDadoMovimentoAtaque) {
        this.valorDadoMovimentoAtaque = valorDadoMovimentoAtaque;
    }
}
