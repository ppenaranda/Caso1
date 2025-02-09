package caso1;

public class Producto {
	private int id;
	private String estado = "ok";
	private static int nId = 0;
	
	public Producto() {
		this.id = nId;
		nId++;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void finEstado() {
		this.estado = "FIN";
	}
	
	public int getId() {
		return id;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void printEstado() {
		System.out.println("El producto id: "+ id + " se encuentra en estado de: "+ estado);
	}
}
