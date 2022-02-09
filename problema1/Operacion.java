package problema1;

public class Operacion {
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

}
