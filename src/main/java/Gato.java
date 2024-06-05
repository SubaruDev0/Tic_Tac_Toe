import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gato {
    // Variable para llevar el seguimiento del turno actual
    public static int turno = 1;
    // Variable para controlar si el juego ha terminado
    public static boolean juegoTerminado = false;

    public static void main(String[] args) {
        // Definición del frame
        JFrame frame = new JFrame("Gato");
        frame.setSize(430, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Definición de un panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        colocarComponentes(panel); // Llama a la función para colocar los componentes en el panel
        frame.setVisible(true);
    }

    private static void colocarComponentes(JPanel panel) {
        // Etiqueta de bienvenida al juego
        JLabel etiqueta = new JLabel("Bienvenido al juego del gato.");
        etiqueta.setBounds(20, 5, 200, 25);

        // Etiqueta para mostrar el turno actual
        JLabel etiqueta2 = new JLabel("Turno 1, juega el jugador 1 ( O ).");
        etiqueta2.setBounds(20, 25, 200, 25);

        // Etiqueta para mostrar el resultado del juego (quién ha ganado)
        JLabel resultadoLabel = new JLabel("");
        resultadoLabel.setBounds(230, 80, 200, 25);

        // Botón para volver a jugar
        JButton play = new JButton("Volver a Jugar");
        play.setBounds(200, 175, 200, 25);

        // Botones del juego
        JButton boton1 = new JButton();
        boton1.setBounds(40, 55, 45, 45);

        JButton boton2 = new JButton();
        boton2.setBounds(90, 55, 45, 45);

        JButton boton3 = new JButton();
        boton3.setBounds(140, 55, 45, 45);

        JButton boton4 = new JButton();
        boton4.setBounds(40, 105, 45, 45);

        JButton boton5 = new JButton();
        boton5.setBounds(90, 105, 45, 45);

        JButton boton6 = new JButton();
        boton6.setBounds(140, 105, 45, 45);

        JButton boton7 = new JButton();
        boton7.setBounds(40, 155, 45, 45);

        JButton boton8 = new JButton();
        boton8.setBounds(90, 155, 45, 45);

        JButton boton9 = new JButton();
        boton9.setBounds(140, 155, 45, 45);

        // Agrega los componentes al panel
        panel.add(etiqueta);
        panel.add(etiqueta2);
        panel.add(resultadoLabel); // Añade la etiqueta para mostrar el resultado
        panel.add(play);
        panel.add(boton1);
        panel.add(boton2);
        panel.add(boton3);
        panel.add(boton4);
        panel.add(boton5);
        panel.add(boton6);
        panel.add(boton7);
        panel.add(boton8);
        panel.add(boton9);

        // Acciones
        ActionListener accionBtn = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton)e.getSource(); // Obtiene el botón que generó el evento
                // Verifica si el juego no ha terminado y el botón está vacío
                if (!juegoTerminado && btn.getText().equals("")) {
                    // Cambia el texto del botón según el jugador actual
                    if ((turno % 2) == 1) {
                        btn.setText("O"); // Jugador 1
                        turno++;
                        etiqueta2.setText("Turno " + turno + ", juega el jugador 2 ( X ).");
                    } else {
                        btn.setText("X"); // Jugador 2
                        turno++;
                        etiqueta2.setText("Turno " + turno + ", juega el jugador 1 ( O ).");
                    }
                    // Verifica si hay un ganador
                    if (verificarVictoria(boton1, boton2, boton3) || verificarVictoria(boton4, boton5, boton6) ||
                        verificarVictoria(boton7, boton8, boton9) || verificarVictoria(boton1, boton4, boton7) ||
                        verificarVictoria(boton2, boton5, boton8) || verificarVictoria(boton3, boton6, boton9) ||
                        verificarVictoria(boton1, boton5, boton9) || verificarVictoria(boton3, boton5, boton7)) {
                        juegoTerminado = true;
                        // Muestra el mensaje de resultado
                        if ((turno) % 2 == 0) {
                            resultadoLabel.setText("¡Jugador 1 (O) ha ganado!");
                        } else {
                            resultadoLabel.setText("¡Jugador 2 (X) ha ganado!");
                        }
                        etiqueta2.setText("¡Juego terminado!"); // Indica que el juego ha terminado
                    }
                }
            }
        };

        // Acción para reiniciar el juego
        ActionListener reset = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpia los botones y reinicia el juego
                boton1.setText("");
                boton2.setText("");
                boton3.setText("");
                boton4.setText("");
                boton5.setText("");
                boton6.setText("");
                boton7.setText("");
                boton8.setText("");
                boton9.setText("");
                turno = 1; // Reinicia el turno a 1
                juegoTerminado = false; // Reinicia el estado del juego
                etiqueta2.setText("Turno 1, juega el jugador 1 ( O )."); // Asegura que el primer jugador sea O
                resultadoLabel.setText(""); // Limpia el mensaje de resultado
            }
        };

        // Asigna las acciones a los botones
        boton1.addActionListener(accionBtn);
        boton2.addActionListener(accionBtn);
        boton3.addActionListener(accionBtn);
        boton4.addActionListener(accionBtn);
        boton5.addActionListener(accionBtn);
        boton6.addActionListener(accionBtn);
        boton7.addActionListener(accionBtn);
        boton8.addActionListener(accionBtn);
        boton9.addActionListener(accionBtn);
        play.addActionListener(reset); // Al botón "Volver a Jugar" se le asigna la acción de reiniciar el juego
    }

    // Función para verificar si hay un ganador
    private static boolean verificarVictoria(JButton btn1, JButton btn2, JButton btn3) {
        String text1 = btn1.getText();
        String text2 = btn2.getText();
        String text3 = btn3.getText();
        // Retorno de verdadero si los textos de los tres botones son iguales y no están vacíos
        return !text1.equals("") && text1.equals(text2) && text2.equals(text3);
    }
}
