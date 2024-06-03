import javax.swing.*;

public class GUI {
    private static final String TITLE = "Sistema de Gestión de Parking";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private Parking parking;
    private JFrame frame;
    private JTextArea textArea;
    private JPanel panel;
    private JButton btnIntroducir;
    private JButton btnSacar;
    private JButton btnComprobar;
    private JButton btnSalir;

    public GUI() {
        parking = new Parking(10);
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        textArea = new JTextArea();
        frame.add(new JScrollPane(textArea), "Center");

        panel = new JPanel();
        btnIntroducir = new JButton("Introducir Vehículo");
        btnSacar = new JButton("Sacar Vehículo");
        btnComprobar = new JButton("Comprobar Estado");
        btnSalir = new JButton("Salir");

        panel.add(btnIntroducir);
        panel.add(btnSacar);
        panel.add(btnComprobar);
        panel.add(btnSalir);
        frame.add(panel, "South");

        addActionListeners();
        frame.setVisible(true);
    }

    private void addActionListeners() {
        btnIntroducir.addActionListener(e -> introducirVehiculo());
        btnSacar.addActionListener(e -> sacarVehiculo());
        btnComprobar.addActionListener(e -> comprobarEstado());
        btnSalir.addActionListener(e -> frame.dispose());
    }

    private void introducirVehiculo() {
        String tipo = JOptionPane.showInputDialog("Ingrese el tipo de vehículo (coche/camion):");
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del vehículo:"));
        Vehiculo vehiculo;
        if (tipo.equalsIgnoreCase("coche")) {
            vehiculo = new Coche(id);
        } else if (tipo.equalsIgnoreCase("camion")) {
            vehiculo = new Camion(id);
        } else {
            JOptionPane.showMessageDialog(frame, "Tipo de vehículo no válido.");
            return;
        }
        parking.introducirParking(vehiculo);
    }

    private void sacarVehiculo() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del vehículo a retirar:"));
        parking.sacarParking(id);
    }

    private void comprobarEstado() {
        textArea.setText("");
        parking.comprobarParking();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}