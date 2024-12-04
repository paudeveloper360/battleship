package battleship;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;


public class Game {

	private static JFrame frame;
	private static PanellMatriu panellMatriu;
	
    public static void main(String[] args) {
        // Matriz del inicio 
        char[][] M1 = new char[5][5];
        for (int i = 0; i < M1.length; i++) {
            for (int j = 0; j < M1[i].length; j++) {
                M1[i][j] = '*';
            }
        }

        // Crear la finestra principal
        frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(265, 290);
        frame.setAlwaysOnTop(true);

        // Inicialitzar el panell amb la matriu inicial
        panellMatriu = new PanellMatriu(M1);
        frame.add(panellMatriu);

        // Mostrar la finestra
        frame.setVisible(true);

        Scanner in = new Scanner(System.in);

        // Ubicación del barco (de 1 a 5)
        int ubibarcofila1 = (int) (Math.random() * 5) + 1;
        int ubibarcocolumna1 = (int) (Math.random() * 5) + 1;
        int ubibarcofila2, ubibarcocolumna2;

        //Cambiar la ubi del barco si son iguales 
        do {
            ubibarcofila2 = (int) (Math.random() * 5) + 1;
            ubibarcocolumna2 = (int) (Math.random() * 5) + 1;
        } while (ubibarcofila1 == ubibarcofila2 && ubibarcocolumna1 == ubibarcocolumna2);

        System.out.println("Els baixell 1 s'ubica a la posició ("+ubibarcofila1+","+ubibarcocolumna1+")");
        System.out.println("Els baixell 2 s'ubica a la posició ("+ubibarcofila2+","+ubibarcocolumna2+")");
        
        // Condición del juego
        int contadorbarcos = 2;

        //Condición del juego 
        while (contadorbarcos > 0) {

            // Mostrar tablero actualizado
            System.out.println("  A B C D E");
            for (int i = 0; i < M1.length; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < M1[i].length; j++) {
                    System.out.print(M1[i][j] + " ");
                }
                System.out.println("");
            }

            PintarGraella(M1);

            int filajugador = 0;
            // Pedir el número 
            System.out.println("Introduce la fila (1-5): ");

            while (filajugador == 0) {
                if (in.hasNextInt()) {
                    filajugador = in.nextInt();
                } else {
                    in.next();
                    System.out.println("Tiene que ser un entero entre el 1 y el 5:");
                }
                in.nextLine();
            }

            //Verificar el rango de números 
            while (filajugador < 1 || filajugador > 5) {
                System.out.println("Mal. Ingresa un número de fila correcto dentro del rango (1-5).");
                filajugador = in.nextInt();
            }

            //Pedir la letra
            System.out.println("Introduce la columna (A-E): ");
            String columnajugador = in.next().toUpperCase();
            System.out.println(columnajugador);
            //Verificar el rando de letras<<<
            while (!(columnajugador.equals("A") || columnajugador.equals("B") || columnajugador.equals("C") || columnajugador.equals("D") || columnajugador.equals("E") )) {
                System.out.println("Mal. Ingresa una letra de columna correcta dentro del rango (A-E).");
                columnajugador = in.next().toUpperCase();
            }
           
            // Convertir columna a número
            int letra = 0;
            switch (columnajugador.charAt(0)) {
                case 'A':
                    letra = 1;
                    break;
                case 'B':
                    letra = 2;
                    break;
                case 'C':
                    letra = 3;
                    break;
                case 'D':
                    letra = 4;
                    break;
                case 'E':
                    letra = 5;
                    break;
                default:
                    break;
            }

            // Validar entrada del jugador
            if ((filajugador == ubibarcofila1 && letra == ubibarcocolumna1)
                    || (filajugador == ubibarcofila2 && letra == ubibarcocolumna2)) {
                System.out.println("¡Has acertado un barco!");
                M1[filajugador - 1][letra - 1] = 'V';
                contadorbarcos--;
            } else {
                System.out.println("¡Agua!");
                M1[filajugador - 1][letra - 1] = 'A';
            }
        }

        // Mostrar tablero final
        System.out.println("  A B C D E");
        for (int i = 0; i < M1.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < M1[i].length; j++) {
                System.out.print(M1[i][j] + " ");
            }
            System.out.println("");
        }

        PintarGraella(M1);

        in.close();
    }
    
    public static void PintarGraella(char[][] novaMatriu) {
        panellMatriu.setMatriu(novaMatriu);
        panellMatriu.repaint();
    }

    // Panell personalitzat per dibuixar la matriu
    static class PanellMatriu extends JPanel {
        private char[][] matriu;

        public PanellMatriu(char[][] matriu) {
            this.matriu = matriu;
        }

        public void setMatriu(char[][] matriu) {
            this.matriu = matriu;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int midaQuadrat = 50;

            // Recórrer la matriu i dibuixar cada quadrat
            for (int i = 0; i < matriu.length; i++) {
                for (int j = 0; j < matriu[i].length; j++) {
                    // Determinar el color segons el valor de la matriu
                    switch (matriu[i][j]) {
                        case '*':
                            g.setColor(Color.WHITE);
                            break;
                        case 'V':
                            g.setColor(Color.BLACK);
                            break;
                        case 'A':
                            g.setColor(new Color(70, 130, 180)); // Blau-mar
                            break;
                        default:
                            g.setColor(Color.GRAY); // Per a valors inesperats
                    }

                    // Dibuixar el fons del quadrat
                    int x = j * midaQuadrat;
                    int y = i * midaQuadrat;
                    g.fillRect(x, y, midaQuadrat, midaQuadrat);

                    // Dibuixar el marc negre del quadrat
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, midaQuadrat, midaQuadrat);
                }
            }
        }
   }
    
    
}