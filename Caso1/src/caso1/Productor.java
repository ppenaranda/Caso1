package caso1;

import java.util.Queue;

public class Productor extends Thread {
	private int id;
	private BuzonReproceso buzonProcesamiento;
	private BuzonRevision buzonRevision;
	
	public Productor(int id, BuzonReproceso buzonProcesamiento, BuzonRevision buzonRevision) {

		this.id = id;
		this.buzonProcesamiento = buzonProcesamiento;
		this.buzonRevision = buzonRevision;
	}
	
	@Override
	public void run() {
		boolean t = true;
		while(t)
			if(buzonProcesamiento.getSize() != 0) {
				Producto p = buzonProcesamiento.retirar();
				p.setEstado("Procesamiento");
				p.printEstado();
				if (p.getEstado() != "FIN" ) {
					buzonRevision.almacenar(p);
					p.setEstado("Revisión");
					p.printEstado();
				}
				else {
					t = false;
				}	
			}
			else {
				Producto prod = new Producto();
				buzonRevision.almacenar(prod);
				prod.setEstado("Revisión");
				prod.printEstado();
			}		
		}
}