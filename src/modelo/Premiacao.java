package modelo;

import utils.Constantes;
import visao.TelaPremiacao;

public class Premiacao {

    private String msgPremiacao;
    private AtorJogador atorPremiado;

    public void verificaGanhador(AtorJogador atorJogador) {
        if (atorJogador.getPosicao().getColuna() == 20) {
            this.atorPremiado = atorJogador;
            this.msgPremiacao = Constantes.VITORIA;
            this.geraNotificaoPremio();
        } else if (atorJogador.getEnergia() == 0) {
            this.atorPremiado = atorJogador;
            this.msgPremiacao = Constantes.DERROTA;
            this.geraNotificaoPremio();
        }
    }

    public void geraNotificaoPremio() {
        TelaPremiacao telaPremiacao = new TelaPremiacao(this.msgPremiacao.equals(Constantes.VITORIA));
        telaPremiacao.renderizar();
    }
}
