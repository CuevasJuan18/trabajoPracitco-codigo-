package Swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

    public class Ventana extends JFrame {

        private JTextField nombreUsuarioTextField;
        private JTextField documentoTextField;
        private JLabel mensajeLabel;
        private Connection connection;

        public Ventana() {
            // Inicializa la conexión en el constructor
            connection = new ConexionBD().conector();

            // Crea la ventana de inicio de sesión
            setTitle("Inicio de Sesión");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(300, 200);

            // Crear un panel
            JPanel panel = new JPanel();
            panel.setLayout(null);

            // Crear componentes
            JLabel nombreUsuarioLabel = new JLabel("Apellido del Usuario:");
            nombreUsuarioLabel.setBounds(20, 20, 150, 25);
            panel.add(nombreUsuarioLabel);

            nombreUsuarioTextField = new JTextField(20);
            nombreUsuarioTextField.setBounds(180, 20, 100, 25);
            panel.add(nombreUsuarioTextField);

            JLabel documentoLabel = new JLabel("Documento:");
            documentoLabel.setBounds(20, 50, 150, 25);
            panel.add(documentoLabel);

            documentoTextField = new JTextField(20);
            documentoTextField.setBounds(180, 50, 100, 25);
            panel.add(documentoTextField);

            JButton loginButton = new JButton("Iniciar Sesión");
            loginButton.setBounds(100, 80, 160, 25);
            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Verificar las credenciales
                    String apellido = nombreUsuarioTextField.getText();
                    String documento = documentoTextField.getText();
                    if (verificarCredenciales(apellido, documento)) {
                        mensajeLabel.setText("Inicio de sesión exitoso");
                        // Aquí puedes agregar lógica adicional después de un inicio de sesión exitoso
                    } else {
                        mensajeLabel.setText("Credenciales incorrectas");
                    }
                }
            });
            panel.add(loginButton);

            mensajeLabel = new JLabel("");
            mensajeLabel.setBounds(20, 110, 250, 25);
            panel.add(mensajeLabel);

            // Agregar el panel al JFrame
            add(panel);

            // Hacer visible la ventana
            setVisible(true);

        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new Ventana();
                }
            });
        }

        private boolean verificarCredenciales(String apellido, String documento) {
            try {
                String sql = "SELECT * FROM Alumnos WHERE apellido = ? AND dni = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, apellido);
                statement.setString(2, documento);

                System.out.println("Consulta SQL: " + statement.toString());  // Imprime la consulta SQL

                ResultSet resultSet = statement.executeQuery();

                return resultSet.next();  // Devuelve true si se encontró una fila, es decir, credenciales válidas
            } catch (SQLException e) {
                e.printStackTrace();
                return false;  // En caso de error, se asume que las credenciales son incorrectas
            }
        }
        public class ConexionBD {
            private static Connection conexion;

            private ConexionBD() {
                // Constructor privado para evitar instanciación externa
            }

            public static Connection obtenerConexion() {
                if (conexion == null) {
                    try {
                        // Use the new driver class
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        String url = "jdbc:mysql://localhost:3306/TrabajoPractico";
                        String usuario = "root";
                        String contraseña = "cuevasjuan1828";

                        conexion = DriverManager.getConnection(url, usuario, contraseña);
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                }
                return conexion;
            }

            public Connection conector() {
                return obtenerConexion();
            }
    }
    }



