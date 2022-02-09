package problema1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class InfijoAPostfijoApp {
    private String ArchivoEntrada;
    private String ArchivoSalida;
    private ArrayList<Operacion> operacion;
    private Stack<Character> p;
    private LinkedList<Character> postfija;
    boolean noVacio;

    public static void main(String[] args) {
        String UbicacionArchivos = "C:\\Users\\BlueW\\OneDrive - Universidad Autonoma de Yucatan\\Desktop\\Uni\\Teoria Lenguajes\\ADA2\\problema1\\";
        String NombreArchivoEntrada = "exp_infijas.txt";
        String NombreArchivoSalida = "exp_postfijas.txt";
        String ArchivoEntrada = UbicacionArchivos + NombreArchivoEntrada;
        String ArchivoSalida = UbicacionArchivos + NombreArchivoSalida;
        ArrayList<Operacion> operacion = new ArrayList<Operacion>();
        InfijoAPostfijoApp c = new InfijoAPostfijoApp(ArchivoEntrada, ArchivoSalida, operacion);

        c.leerArchivo(); // lee el archivo, no modificar
        // c.mostrarOperaciones(); // lee las operaciones en el archivo .txt, solo para
        // pruebas
        c.crearArchivo(); // problema 1

    }

    public InfijoAPostfijoApp(String ArchivoEntrada, String ArchivoSalida, ArrayList<Operacion> operacion) {
        this.ArchivoEntrada = ArchivoEntrada;
        this.ArchivoSalida = ArchivoSalida;
        if (operacion == null) {
            this.operacion = new ArrayList<Operacion>();
        } else {
            this.operacion = operacion;
        }
    }

    public void leerArchivo() {

        FileReader fr;
        try {
            fr = new FileReader(ArchivoEntrada);
            String linea = "";
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                noVacio = true;
                String[] datosLinea = linea.split(";"); // lee una nueva celda cuando lee una coma
                // System.out.println(" " + linea);

                String ecuacion = datosLinea[0].trim();

                Operacion o = new Operacion(ecuacion);
                this.operacion.add(o);
            }

            if (!noVacio && (linea = br.readLine()) == null) {
                JOptionPane.showMessageDialog(null, "El archivo seleccionado esta vacio");
            }

            br.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "El archivo de estudiantes no existe, por lo tanto no se podran capturar calificaciones", "ERROR",
                    JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarOperaciones() {
        // Se imprime el listado de las operaciones en consola
        for (Operacion a : this.operacion) {
            System.out.println(a.getEcuacion());
            String ecuacion = a.getEcuacion();
            System.out.println("lenght: " + ecuacion.length());
        }
    }

    public void crearArchivo() {
        FileWriter fw;
        try {
            fw = new FileWriter(this.ArchivoSalida);
            BufferedWriter bw = new BufferedWriter(fw);
            String lineaTexto = "";
            for (Operacion a : this.operacion) {
                String ecuacion = a.getEcuacion();
                String entrada = ecuacion;
                // Inciso a
                // System.out.println(convertir(entrada));
                // convertir(entrada);

                // System.out.println(salida);

                // Inciso b
                String operacionRealizada = "XDD";

                lineaTexto = "Expr: " + convertirPostfijo(entrada) + "; Eval:" + operacionRealizada + "\n";
                bw.write(lineaTexto);
            }
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmptyStackException e) {
            System.out.println("LOL");
        }
    }

    // Declara el nivel de prioridad de los operadores
    public int prioridad(char valor) {
        switch (valor) {
            case '*':
                return 2;
            case '+':
                return 1;
            case '-':
                return 1;
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return 0;
    }

    public int evaluacion(char E) {
        if (E == ';') {
            return 5;
        } else if (E != '^' && E != '+' && E != '-' && E != '*' && E != '/' && E != ')' && E != '(') { // es un número
            return 1;
        } else if (E == '(') {
            return 2;
        } else if (E == ')') {
            return 3;
        } else { // Es un operador
            return 4;
        }
    }

    // Problema 1 Inciso A
    public LinkedList<Character> convertirPostfijo(String expresion) {

        p = new Stack<Character>();
        postfija = new LinkedList<Character>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);
            switch (evaluacion(c)) {

                case 1: // número
                    postfija.addLast(c);
                    break;
                case 2: // '('
                    p.push(c);
                    break;
                case 3: // ')'
                    while (p.isEmpty() == false && p.peek() != '(') {
                        postfija.addLast(p.pop());
                    }
                    try {
                        if (p.peek() == '(') {
                            p.pop();
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                    break;
                case 4: // operador
                    while (p.isEmpty() == false && prioridad(p.peek()) >= prioridad(c)) {
                        postfija.addLast(p.pop());
                    }
                    p.push(c);
                    break;
                case 5:
                    break;

            }
            // System.out.println("POSTFIA " + postfija.toString());
            // System.out.println("PILA :" + p.toString());

        }
        while (!p.isEmpty()) {

            postfija.addLast(p.pop());
        }

        // String w = postfija.toString();
        // System.out.println("POSTFIA " + w);
        // System.out.println(postfija);
        return postfija;
    }

}
