package co.edu.unbosque.proyecto.model;

public class Tendencia {
	private String nombreEmpresa;
	private double promedio;
	private int movimientos;

	public Tendencia() {
		// TODO Auto-generated constructor stub
	}



	public Tendencia(String nombreEmpresa, double promedio, int movimientos) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.promedio = promedio;
		this.movimientos = movimientos;
	}



	/**
	 * @return the movimientos
	 */
	public int getMovimientos() {
		return movimientos;
	}



	/**
	 * @param movimientos the movimientos to set
	 */
	public void setMovimientos(int movimientos) {
		this.movimientos = movimientos;
	}



	/**
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @param nombreEmpresa the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * @return the promedio
	 */
	public double getPromedio() {
		return promedio;
	}

	/**
	 * @param promedio the promedio to set
	 */
	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

}
