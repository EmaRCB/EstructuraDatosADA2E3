import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.PriorityQueue;
import java.util.Stack;

import javax.swing.JOptionPane;

public class InfijoAPostfijoAppIntento {
    private String ArchivoEntrada;
    private String ArchivoSalida;
    private ArrayList<Operacion> operacion;
    boolean noVacio;

    public static void main(String[] args) {
        String ArchivoEntrada = "C:\\Users\\BlueW\\OneDrive - Universidad Autonoma de Yucatan\\Desktop\\Uni\\Teoria Lenguajes\\ADA2\\exp_infijas.txt";
        String ArchivoSalida = "C:\\Users\\BlueW\\OneDrive - Universidad Autonoma de Yucatan\\Desktop\\Uni\\Teoria Lenguajes\\ADA2\\exp_postfijas.txt";
        ArrayList<Operacion> operacion = new ArrayList<Operacion>();
        InfijoAPostfijoAppIntento c = new InfijoAPostfijoAppIntento(ArchivoEntrada, ArchivoSalida, operacion);

        c.leerArchivo(); // lee el archivo, no modificar
        c.mostrarOperaciones(); // lee las operaciones en el archivo .txt, no sirve para nada pero puedes
                                // comprobar cosas aqui
        c.crearArchivo(); // problema 1

    }

    public InfijoAPostfijoAppIntento(String ArchivoEntrada, String ArchivoSalida, ArrayList<Operacion> operacion) {
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
        // Se imprime el listado de alumnos
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
                String salida = "";
                Stack<Character> p = new Stack<Character>();

                for (int i = 0; i < entrada.length(); i++) {
                    char c = entrada.charAt(i); // el caracter en la posicion i
                    if (c >= '0' & c <= '9') {
                        salida = salida + c;
                    }
                    if (c == '(') {
                        p.push(c);
                    }
                    if (c == ')') {
                        while (!p.isEmpty()) {
                            int d = p.pop();
                            if (d != '(') {
                                salida = salida + d;
                            }
                        }
                        continue;
                    }
                    if (c >= '*' & c <= '/') {
                        if (!p.isEmpty()) {
                            p.push(c);
                        } else {
                            while (!p.isEmpty()) {
                                p.pop();
                            }
                        }
                        if (p.peek() < c) {
                            p.push(p.peek());
                            continue;
                        }
                        if (p.peek() >= c) {
                            p.pop();
                        }
                        p.push(c);
                    }
                    while (!p.isEmpty()) {
                        p.pop();
                    }
                    System.out.println(salida);
                }
                // System.out.println(salida);

                // Inciso b
                String operacionRealizada = "";

                lineaTexto = "Expr: " + salida + "; Eval:" + operacionRealizada + "\n";
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

    // Declaración clase Operacion
    private String ecuacion;

    public Operacion(String ecuacion) {
        this.ecuacion = ecuacion;
    }

    public String getEcuacion() {
        return ecuacion;
    }

    public void setEcuacion(String ecuacion) {
        this.ecuacion = ecuacion;
    }

    @Override
    public String toString() {
        return "Operacion [ecuacion=" + ecuacion + "]";
    }
    // Fin clase Operacion

}
