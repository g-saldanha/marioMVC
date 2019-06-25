package modelo;

import utils.Constantes;

import javax.swing.*;
import java.awt.*;

public class BarraEnergia extends JProgressBar {
    public BarraEnergia() {
        super.setStringPainted(true);
        super.setValue(0);
        super.setSize(new Dimension(100, 23));
        super.setString(Constantes.BARRA_DE_ENERGIA);
    }

    public BarraEnergia getBarraDeEnergia() {
        return this;
    }
}
