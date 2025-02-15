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
        while (true) {
            Producto producto = null;
            
            // Primero intentar reprocesar
            synchronized(buzonProcesamiento) {
                if (buzonProcesamiento.getSize() > 0) {
                    producto = buzonProcesamiento.retirar();
                    if (producto.getEstado().equals("FIN")) {
                        break; // Terminar si recibimos señal de fin 
                    }
                }
            }
            
            // Si no hay productos para reprocesar, crear uno nuevo
            if (producto == null) {
                producto = new Producto();
            }
            //System.out.print(" Id Productor: "+ id + " ");
            producto.setEstado("Procesamiento");
            producto.printEstado();
            
            // Almacenar en buzón de revisión
            buzonRevision.almacenar(producto);
            producto.setEstado("Revisión");
            //System.out.print(" Id Productor: "+ id + " ");
            producto.printEstado();
        }
    }
}