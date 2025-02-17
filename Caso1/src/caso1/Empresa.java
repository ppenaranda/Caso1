package caso1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Empresa {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el número de productores y revisores:");
        int nOperarios = sc.nextInt();
        System.out.println("Introduce la capacidad del buzón de revisión: ");
        int sizeBuzon = sc.nextInt();
        System.out.println("Introduce el número de productos: ");
        int nMax = sc.nextInt();
        sc.close();

        BuzonReproceso bReprocesamiento = new BuzonReproceso();
        BuzonRevision bRevision = new BuzonRevision(sizeBuzon);
        Deposito deposito = new Deposito();

        List<Productor> productores = new ArrayList<>();
        List<Revisor> revisores = new ArrayList<>();
        for (int i = 0; i < nOperarios; i++) {
            Productor p = new Productor(i, bReprocesamiento, bRevision);
            Revisor r = new Revisor(i + nOperarios, deposito, bRevision, nMax, bReprocesamiento);

            productores.add(p);
            revisores.add(r);

            // Muestra el id de los hilos
            System.out.println("Se han creado ---> Productor: " + i + " y Revisor: " + (i + nOperarios));

            p.start();
            r.start();
        }
    }

}
