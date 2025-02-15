package caso1;

import java.util.LinkedList;
import java.util.Queue;

public class BuzonRevision {
	private Queue<Producto> buff;
	private int nMax;

	public BuzonRevision (int n) {
		this.nMax = n;
		 this.buff = new LinkedList<Producto>();
	}
	
	public synchronized void almacenar (Producto i) {

		while (buff.size() >= nMax) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		buff.add(i);
		
	}
	
    public Producto retirar() {
        while (buff.isEmpty()) {
            Thread.yield(); // Consulta semi-activa como especifica el enunciado
        }
        synchronized(buff) {
        Producto p = buff.remove();
        buff.notifyAll();
        return p;
        }
    }
    
    public synchronized int getSize() {
        return buff.size();
    }
}