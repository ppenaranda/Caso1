package caso1;
import java.util.LinkedList;
import java.util.Queue;

public class BuzonRevision {
    private Queue<Producto> buff;
    private int nMax;

    public BuzonRevision(int n) {
        this.nMax = n;
        this.buff = new LinkedList<Producto>();
    }

    public synchronized void almacenar(Producto i) {

        while (buff.size() >= nMax) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buff.add(i);
        notifyAll();
    }

    public synchronized Producto retirar() {     
            if (buff.isEmpty()) {
                return null;
            }
            Producto p = buff.remove();           
            notifyAll();
            return p;
    }

    public synchronized int getSize() {
        return buff.size();
    }
}