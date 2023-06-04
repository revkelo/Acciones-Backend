package co.edu.unbosque.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nombre;
	private int precioAccion;
	
	public Empresa() {
		// TODO Auto-generated constructor stub
	}

	


	public Empresa(String nombre) {
		super();
		this.nombre = nombre;
	}




	public Empresa(String nombre, int precioAccion) {
		super();
		this.nombre = nombre;
		this.precioAccion = precioAccion;
	}




	/**
	 * @return the precioAccion
	 */
	public int getPrecioAccion() {
		return precioAccion;
	}


	/**
	 * @param precioAccion the precioAccion to set
	 */
	public void setPrecioAccion(int precioAccion) {
		this.precioAccion = precioAccion;
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
