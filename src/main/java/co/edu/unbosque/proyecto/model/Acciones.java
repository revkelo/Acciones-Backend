package co.edu.unbosque.proyecto.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Acciones {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer idCliente;
	private Integer accionesCompradas;
	private String nombreEmpresa;
	private Date fecha;
	private String estado;


	public Acciones() {
		// TODO Auto-generated constructor stub
	}


	
	
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}




	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}




	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}




	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	 * @return the idCliente
	 */
	public Integer getIdCliente() {
		return idCliente;
	}


	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}


	/**
	 * @return the accionesCompradas
	 */
	public Integer getAccionesCompradas() {
		return accionesCompradas;
	}


	/**
	 * @param accionesCompradas the accionesCompradas to set
	 */
	public void setAccionesCompradas(Integer accionesCompradas) {
		this.accionesCompradas = accionesCompradas;
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


}

 
