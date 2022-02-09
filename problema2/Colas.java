public class Colas {

    public int maxSize, nItems;
    private Cliente[] queArray;
    
    public Colas(int s) {
        maxSize = s;
        queArray = new Cliente[maxSize];
        nItems = 0;
    }

    public void insert(Cliente item) {
        int j;
        if (nItems == 0)
            queArray[nItems++] = item;
        else {
            for (j = nItems - 1; j >= 0; j--) {
                //Si la prioridad es mayor o igual los elementos se van corriendo
                if (item.prioridad >= queArray[j].prioridad)
                    queArray[j + 1] = queArray[j];
                else
                    break;
            }
            queArray[j + 1] = item;
            nItems++;
        }
    }

    public Cliente remove() {
        return queArray[--nItems];
    }
    //proximo elemento a sacar
    public Cliente peekMay() {
        return queArray[nItems - 1];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }



}
