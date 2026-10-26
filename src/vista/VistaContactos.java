package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class VistaContactos extends JFrame {
    public JTabbedPane tabbedPane = new JTabbedPane();
    public JPanel panelContactos = new JPanel(new BorderLayout());
    public JPanel panelEstadisticas = new JPanel();

    public DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"Nombre", "Teléfono", "Email"}, 0);
    public JTable tablaContactos = new JTable(modeloTabla);
    public TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabla);

    public JProgressBar progressBar = new JProgressBar();
    public JTextField txtFiltro = new JTextField(15);
    public JButton btnExportar = new JButton("Exportar CSV");

    public JPopupMenu popupMenu = new JPopupMenu();
    public JMenuItem menuEliminar = new JMenuItem("Eliminar Contacto");

    public VistaContactos() {
        setTitle("Gestión de Contactos MVC");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tablaContactos.setRowSorter(sorter);
        popupMenu.add(menuEliminar);
        tablaContactos.setComponentPopupMenu(popupMenu);

        JPanel panelTop = new JPanel();
        panelTop.add(new JLabel("Filtrar:"));
        panelTop.add(txtFiltro);
        panelTop.add(btnExportar);

        panelContactos.add(panelTop, BorderLayout.NORTH);
        panelContactos.add(new JScrollPane(tablaContactos), BorderLayout.CENTER);

        progressBar.setStringPainted(true);
        panelContactos.add(progressBar, BorderLayout.SOUTH);

        tabbedPane.addTab("Contactos", panelContactos);
        tabbedPane.addTab("Estadísticas", panelEstadisticas);

        add(tabbedPane);

        // Agregar datos de prueba
        modeloTabla.addRow(new Object[]{"Juan Perez", "0991234567", "juan@test.com"});
        modeloTabla.addRow(new Object[]{"Maria Lopez", "0987654321", "maria@test.com"});
    }
}