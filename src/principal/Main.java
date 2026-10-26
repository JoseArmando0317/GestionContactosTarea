package principal;

import controlador.ControladorContactos;
import vista.VistaContactos;

public class Main {
    public static void main(String[] args) {
        VistaContactos vista = new VistaContactos();
        ControladorContactos controlador = new ControladorContactos(vista);
        vista.setVisible(true);
    }
}
