package caso1;

import java.util.LinkedList;
import java.util.Queue;

public class BuzonReproceso {
	private Queue<Producto> buff;
	
	public BuzonReproceso() {
		this.buff = new LinkedList<Producto>();
	}
	
	public  synchronized void almacenar(Producto i) {
		buff.add(i);
	}
	
	public synchronized Producto retirar() {
		return buff.remove();
	}
	
	public int getSize() {
		return buff.size();
	}
}