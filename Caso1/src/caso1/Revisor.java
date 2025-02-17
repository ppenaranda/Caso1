package caso1;

import java.util.concurrent.ThreadLocalRandom;

public class Revisor extends Thread {
    private int id;
    private static int nErrores = 0;
    private int nProductosTotal;
    private static final Object errorLock = new Object();
    private Deposito deposito;
    private BuzonRevision buzonRevision;
    private BuzonReproceso buzonReproceso;

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

            if (prod == null) {
                if (deposito.getSize() == nProductosTotal) {

                    break;
                } else {
                    Thread.yield();
                }
            } else {
                prod.setEstado("Revisión");
                prod.printEstado(id);

                // Generar número aleatorio para la revisión

                synchronized (errorLock) {
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 101);

                    int maxErrores = (int) Math.floor(nProductosTotal * 0.1);

                    if (randomNum % 7 == 0 && nErrores < maxErrores) {
                        buzonReproceso.almacenar(prod);
                        prod.setEstado("Reproceso");

                        System.out.println("El producto con id: " + prod.getId() + " falló en la revisión");
                        prod.printEstado(id);
                        nErrores++;
                    } else {
                        if (deposito.getSize() + 1 == nProductosTotal) {
                            System.out.println("El producto con id: " + prod.getId() + " ha pasado la revisión");
                            prod.setEstado("Deposito");
                            prod.printEstado(id);
                            deposito.almacenar(prod);

                            Producto fin = new Producto();
                            fin.setEstado("FIN");
                            buzonReproceso.almacenar(fin);
                            break;
                        } else if (deposito.getSize() + 1 > nProductosTotal) {
                            System.out.println("El producto con id: " + prod.getId() + " ha sido procesado");
                            Producto fin = new Producto();
                            fin.setEstado("FIN");
                            buzonReproceso.almacenar(fin);
                        } else {
                            System.out.println("El producto con id: " + prod.getId() + " ha pasado la revisión");
                            prod.setEstado("Deposito");
                            deposito.almacenar(prod);
                            prod.printEstado(id);
                        }
                    }
                }
            }
        }
        System.out.println("El Revisor con id: " + id + " ha terminado su trabajo");
        System.out.println("Se encuentran en el depósito: " + deposito.getSize() + " productos");
    }
}