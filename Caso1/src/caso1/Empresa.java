package caso1;

public class Empresa {
	
	public static void main(String[] args) {
		int nOperarios = 3;
		int nMax = 10; 
		BuzonReproceso bReprocesamiento = new BuzonReproceso();
		BuzonRevision bRevision = new BuzonRevision(nMax);
		Deposito deposito = new Deposito();
		for(int i = 0; i< nOperarios; i++ ) {
			Productor p = new Productor(i, bReprocesamiento, bRevision);
			Revisor r = new Revisor(i, deposito, bRevision, nMax, bReprocesamiento);
			
			p.start();
			r.start();
		}
    }

}
