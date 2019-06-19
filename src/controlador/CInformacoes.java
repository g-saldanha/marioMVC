package controlador;

import visao.PainelInformacoes;

import javax.swing.*;

public class CInformacoes {
    private static CInformacoes cInformacoes = new CInformacoes();

    public static CInformacoes getInstance() {
        return cInformacoes;
    }

    private CInformacoes() {
    }

    private static PainelInformacoes artifacts = new PainelInformacoes();


    private static void criarPanelDeInfo(JPanel[][] panelHolder) {
        panelHolder[0][8].add(artifacts.getInfoTitulo());
        panelHolder[1][7].add(artifacts.getTurnoTitulo());
        panelHolder[2][7].add(artifacts.getJogadorDaVezTitulo());
        panelHolder[14][5].add(artifacts.getPistaTitulo());
    }

}
