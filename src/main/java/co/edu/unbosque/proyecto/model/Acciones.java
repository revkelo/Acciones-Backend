package co.edu.unbosque.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Acciones {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer idCliente;
	private Integer accionesCompradas;
	private String nombreEmpresa;

public Acciones() {
	// TODO Auto-generated constructor stub
}

public Acciones(Integer id, Integer idCliente, Integer accionesCompradas, String nombreEmpresa) {
	super();
	this.id = id;
	this.idCliente = idCliente;
	this.accionesCompradas = accionesCompradas;
	this.nombreEmpresa = nombreEmpresa;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Integer getIdCliente() {
	return idCliente;
}

public void setIdCliente(Integer idCliente) {
	this.idCliente = idCliente;
}

public Integer getAccionesCompradas() {
	return accionesCompradas;
}

public void setAccionesCompradas(Integer accionesCompradas) {
	this.accionesCompradas = accionesCompradas;
}

public String getNombreEmpresa() {
	return nombreEmpresa;
}

public void setNombreEmpresa(String nombreEmpresa) {
	this.nombreEmpresa = nombreEmpresa;
}

}
