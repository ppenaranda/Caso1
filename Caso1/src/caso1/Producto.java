package caso1;

public class Producto {
	private Integer id = 0;
	private String estado = "ok";
	private static int nId = 0;

	public Producto() {
		synchronized (Producto.class) {
			this.id = nId;
			nId++;
		}
	}

	public String getEstado() {
		return estado;
	}

	public int getId() {
		return id;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void printEstado(int fuente) {
		System.out.println(
				"El operario con id: " + fuente + " ha cambiado el estado del producto id: " + id + " a: " + estado);
	}
}
