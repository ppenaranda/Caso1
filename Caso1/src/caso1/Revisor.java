package caso1;

import java.util.concurrent.ThreadLocalRandom;

public class Revisor extends Thread {
    private int id;
    private Deposito deposito;
    private BuzonRevision buzonRevision;
    private BuzonReproceso buzonReproceso;
    private static int nErrores = 0;
    private int nProductosTotal;
    private static final Object errorLock = new Object();

    public Revisor(int id, Deposito deposito, BuzonRevision buzonRevision, 
                  int nProductosTotal, BuzonReproceso buzonReproceso) {
        this.id = id;
        this.deposito = deposito;
        this.buzonRevision = buzonRevision;
        this.nProductosTotal = nProductosTotal;
        this.buzonReproceso = buzonReproceso;
    }

    @Override
    public void run() {
        while (true) {
            Producto prod = buzonRevision.retirar();
            
            // Verificar si se alcanzó el total de productos
            if (deposito.getSize() >= nProductosTotal) {
                
                prod.setEstado("FIN");
                buzonReproceso.almacenar(prod);
                prod.printEstado();
                //if(buzonRevision.getSize() == 0) {
                break;
                //}
            }

            // Generar número aleatorio para la revisión
            
            
            synchronized (errorLock) {
            	int randomNum = ThreadLocalRandom.current().nextInt(1, 101);
            	//System.out.println(randomNum%7);
                int maxErrores = (int) Math.floor(nProductosTotal * 0.1);
                //System.out.println(" nErrores: " + nErrores + " maxErrores: "+ maxErrores);
                if (randomNum % 7 == 0 && nErrores < maxErrores) {
                    buzonReproceso.almacenar(prod);
                    prod.setEstado("Reproceso");
                    //System.out.print(" Id Revisor: "+ id + " ");
                    prod.printEstado();
                    nErrores++;
                } else {
                    deposito.almacenar(prod);
                    prod.setEstado("Deposito");
                    //System.out.print(" Id Revisor: "+ id + " ");
                    prod.printEstado();
                }
            }
            
        }
        System.out.println("Terminó Revisor id: " + id);
    }
}