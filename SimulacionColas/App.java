public class App {
    public static void main(String[] args) throws Exception {


        //numero 1= VIP 
        //numero 2= normal
        Cliente c1= new Cliente("17003222", "Karyme", 1);
        Cliente c2= new Cliente("17005415", "Jaqui", 2);
        Cliente c3= new Cliente("17009824", "Carla", 2);
        Cliente c4= new Cliente("17003554", "Juan", 1);
        Cliente c5 = new Cliente("170006546", "Juli", 2);
        Cliente c6 = new Cliente("65156454", "Mario", 1);
        Cliente c7 = new Cliente("989848118", "Francisco", 1);
        Cliente c8 = new Cliente("170006546", "Rita", 2);
        Cliente c9 = new Cliente("65156454", "Luis", 1);
        Cliente c10 = new Cliente("989848118", "Kuka", 1);

        int tamañoCola=7;
        Colas colaEspera= new Colas(tamañoCola);
        

        System.out.println("¿La cola esta vacia?"+colaEspera.isEmpty());
        colaEspera.insert(c1);
        colaEspera.insert(c2);
        colaEspera.insert(c3);
        colaEspera.insert(c4);
        colaEspera.insert(c5);
        colaEspera.insert(c6);
        colaEspera.insert(c7);
        System.out.println("Agregamos los datos exitosamente");
        System.out.println("\n¿La cola esta llena?"+colaEspera.isFull());

        System.out.println("\nEl primero en pasar:\n "+colaEspera.peekMay());

        System.out.println("\nEmpezamos a remover\n");
        for(int i=0; i<=tamañoCola-1;i++){
        System.out.println("Posicion: "+(i+1)+"\n"+colaEspera.remove());
        }
        System.out.println("Le agregamos nuevos datos:\n");

        colaEspera.insert(c8);
        colaEspera.insert(c9);
        System.out.println("El siguiente en pasar\n: " + colaEspera.peekMay());
        colaEspera.insert(c10);
        

        System.out.println("¿La cola esta vacia? "+ colaEspera.isEmpty());
        System.out.println("\n");
        System.out.println(colaEspera.remove());
        System.out.println(colaEspera.remove());
        System.out.println(colaEspera.remove());
        System.out.println("\n¿La cola esta vacia? "+ colaEspera.isEmpty());

    

    }
}
