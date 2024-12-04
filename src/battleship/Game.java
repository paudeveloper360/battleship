package battleship;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Matriz del inicio 
        char[][] M1 = new char[5][5];
        for (int i = 0; i < M1.length; i++) {
            for (int j = 0; j < M1[i].length; j++) {
                M1[i][j] = '*';
            }
        }

        // Ubicación del barco (de 1 a 5)
        int ubibarcofila1 = (int) (Math.random() * 5) + 1;
        int ubibarcocolumna1 = (int) (Math.random() * 5) + 1;
        int ubibarcofila2, ubibarcocolumna2;

        //Cambiar la ubi del barco si son iguales 
        do {
            ubibarcofila2 = (int) (Math.random() * 5) + 1;
            ubibarcocolumna2 = (int) (Math.random() * 5) + 1;
        } while (ubibarcofila1 == ubibarcofila2 && ubibarcocolumna1 == ubibarcocolumna2);

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
        
        in.close();
    
    }
}