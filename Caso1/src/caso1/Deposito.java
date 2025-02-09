package caso1;

import java.util.ArrayList;

public class Deposito {
	private ArrayList<Producto> buff;
	
	public Deposito() {
		this.buff = new ArrayList<Producto>(); 
	}
	
	public synchronized void almacenar(Producto i) {
		buff.add(i);
	}
	
	public int getSize() {
		return buff.size();
	}
}
