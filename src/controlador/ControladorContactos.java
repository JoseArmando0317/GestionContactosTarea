package controlador;

import vista.VistaContactos;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class ControladorContactos {
    private VistaContactos vista;

    public ControladorContactos(VistaContactos vista) {
        this.vista = vista;
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.txtFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = vista.txtFiltro.getText();
                if (texto.trim().length() == 0) {
                    vista.sorter.setRowFilter(null);
                } else {
                    vista.sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                }
            }
        });

        vista.btnExportar.addActionListener(e -> exportarCSV());

        vista.menuEliminar.addActionListener(e -> {
            int filaSeleccionada = vista.tablaContactos.getSelectedRow();
            if (filaSeleccionada >= 0) {
                vista.modeloTabla.removeRow(vista.tablaContactos.convertRowIndexToModel(filaSeleccionada));
            }
        });

        KeyStroke ctrlE = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
        vista.getRootPane().registerKeyboardAction(e -> exportarCSV(), ctrlE, JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private void exportarCSV() {
        new Thread(() -> {
            for (int i = 0; i <= 100; i += 20) {
                vista.progressBar.setValue(i);
                try { Thread.sleep(200); } catch (InterruptedException ex) {}
            }
            try (FileWriter fw = new FileWriter("contactos.csv")) {
                for (int i = 0; i < vista.modeloTabla.getRowCount(); i++) {
                    fw.write(vista.modeloTabla.getValueAt(i, 0) + "," +
                            vista.modeloTabla.getValueAt(i, 1) + "," +
                            vista.modeloTabla.getValueAt(i, 2) + "\n");
                }
                JOptionPane.showMessageDialog(vista, "Exportación exitosa a CSV");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            vista.progressBar.setValue(0);
        }).start();
    }
}