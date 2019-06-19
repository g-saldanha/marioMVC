package controlador;

import modelo.Pista;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CPista {
    private static CPista cPista = new CPista();
    private Pista pista;

    public static CPista getInstance() {
        return cPista;
    }

    private CPista() {
        this.pista = new Pista();
    }

    private static void criarPista(JPanel[][] panelHolder, JFrame frame) {
        int row = 20;
        int column = 10;
        for (int m = 0; m < 15; m++) {
            for (int n = 0; n < column; n++) {
                panelHolder[m][n] = new JPanel();
                frame.add(panelHolder[m][n]);
            }
        }

        for (int m = 15; m < 20; m++) {
            for (int n = 0; n < 10; n++) {
                JPanel jPanel = new JPanel();
                jPanel.setBackground(Color.GRAY);
                jPanel.setBorder(new LineBorder(Color.WHITE));
                panelHolder[m][n] = jPanel;
                frame.add(panelHolder[m][n]);
            }
        }
    }
}
