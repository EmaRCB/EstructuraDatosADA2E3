public class Cliente {
    String numeroCta;
    String nombre;
    int prioridad;

    //Pedimos la datos necesarios
    public Cliente(String  numeroCta, String nombre, int prioridad){
        this.nombre=nombre;
        this.numeroCta=numeroCta;
        //numero 1= VIP 
        //numero 2= normal
        this.prioridad=prioridad;
    }

    //setters and getters

    public String getNombre(){
        return this.nombre;
    }
    public String getNumeroCta(){
        return this.numeroCta;
    }
    public int getPrioridad(){
        return this.prioridad;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    } 
    public void setNumeroCta(String numeroCta){
        this.numeroCta=numeroCta;
    } 
    public void setPrioridad(int prioridad){
        this.prioridad=prioridad;
    } 
    //Sobreescribimos el metodo para establecer un formato
    @Override
    public String toString() {
        return "Nombre: "+this.nombre +"\nNo. Cuenta: "+ this.numeroCta + "\nPrioridad: "+ this.prioridad+"\n--------------------------";
    }
    
}
