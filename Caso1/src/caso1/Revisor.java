package caso1;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class Revisor extends Thread{
	private int id;
	private Deposito deposito;
	private BuzonRevision buzonRevision;
	private BuzonReproceso buzonReproceso;
	private static int nErrores = 0;
	private static boolean t = true;
	private int nMax; 
	
	public Revisor(int id, caso1.Deposito deposito, BuzonRevision buzonRevision, int nMax, BuzonReproceso buzonReproceso) {

		this.id = id;
		this.deposito = deposito;
		this.buzonRevision = buzonRevision;
		this.nMax = nMax;
		this.buzonReproceso = buzonReproceso;
	}
	
	@Override
	public void run() {
		while(t) {
			Producto prod = buzonRevision.retirar();
			if(deposito.getSize() >= nMax) {
				prod.finEstado();
				buzonReproceso.almacenar(prod);
				prod.printEstado();
				t = false;
			}
			else {
				int rand_int1 = ThreadLocalRandom.current().nextInt();
				if(rand_int1%7 == 0 && nErrores < Math.floor(nMax*0.1)) {
					buzonReproceso.almacenar(prod);
					prod.setEstado("Reproceso");
					prod.printEstado();
					nErrores++;
				}
				else {
					deposito.almacenar(prod);
					prod.setEstado("Deposito");
					prod.printEstado();
				}
			}
		}				
	}
}
