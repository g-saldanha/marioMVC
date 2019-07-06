package modelo;

import utils.Constantes;
import visao.TelaPremiacao;

public class Premiacao {

    private String msgPremiacao = "nada";

    public String verificaGanhador(Jogador jogador) {
        if (jogador.getPosicao().getColuna() == 20) {
            this.msgPremiacao = Constantes.VITORIA;
            this.geraNotificaoPremio();
        } else if (jogador.getEnergia() == 0) {
            this.msgPremiacao = Constantes.DERROTA;
            this.geraNotificaoPremio();
        }
        return this.msgPremiacao;
    }

    public void notificaPerdedor(Jogador jogador) {
        if (jogador.getPosicao().getColuna() < 20) {
            this.msgPremiacao = Constantes.DERROTA;
            this.geraNotificaoPremio();
        }
    }

    public void geraNotificaoPremio() {
        TelaPremiacao telaPremiacao = new TelaPremiacao(this.msgPremiacao.equals(Constantes.VITORIA));
        telaPremiacao.renderizar();
    }
}
