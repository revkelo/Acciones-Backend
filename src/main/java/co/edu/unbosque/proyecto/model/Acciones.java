package co.edu.unbosque.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Acciones {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer id_cliente;
	private Integer acciones_compradas;
	private String nombre_empresa;


	public Acciones() {
		// TODO Auto-generated constructor stub
	}


	public Acciones(Integer id, Integer acciones_compradas, String nombre_empresa) {
		super();
		this.id = id;
		this.acciones_compradas = acciones_compradas;
		this.nombre_empresa = nombre_empresa;
	}


	/**
	 * @return the id_cliente
	 */
	public Integer getId_cliente() {
		return id_cliente;
	}


	/**
	 * @param id_cliente the id_cliente to set
	 */
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
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
	 * @return the acciones_compradas
	 */
	public Integer getAcciones_compradas() {
		return acciones_compradas;
	}


	/**
	 * @param acciones_compradas the acciones_compradas to set
	 */
	public void setAcciones_compradas(Integer acciones_compradas) {
		this.acciones_compradas = acciones_compradas;
	}


	/**
	 * @return the nombre_empresa
	 */
	public String getNombre_empresa() {
		return nombre_empresa;
	}


	/**
	 * @param nombre_empresa the nombre_empresa to set
	 */
	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}
	
	
}

 
