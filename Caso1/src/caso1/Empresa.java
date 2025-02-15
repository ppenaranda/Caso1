package caso1;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
	
	public static void main(String[] args) {
		int nOperarios = 3;
		int nMax = 15; 
		BuzonReproceso bReprocesamiento = new BuzonReproceso();
		BuzonRevision bRevision = new BuzonRevision(nMax);
		Deposito deposito = new Deposito();
		
        List<Productor> productores = new ArrayList<>();
        List<Revisor> revisores = new ArrayList<>();
		for(int i = 0; i< nOperarios; i++ ) {
			Productor p = new Productor(i, bReprocesamiento, bRevision);
			Revisor r = new Revisor(i, deposito, bRevision, nMax, bReprocesamiento);
			
            productores.add(p);
            revisores.add(r);
			
			p.start();
			r.start();
		}/**		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Proceso finalizado");
        System.out.println("Productos en depósito: " + deposito.getSize());
    **/}

}
