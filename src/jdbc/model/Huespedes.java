package jdbc.model;

import java.sql.Date;

public class Huespedes {
	private int id;
	private String nombre;
	private String apellido;
	private Date fechadenacimiento;
	private String nacionalidad;
	private String telefono;
	private int idreserva;
	
	public Huespedes(String nombre, String apellido, Date fecha, String nacionalidad, String telefono, int reserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechadenacimiento = fecha;
		this.telefono = telefono;
		this.idreserva = reserva;
	}
	
	public Huespedes(int id ,String nombre, String apellido, Date fecha, String nacionalidad, String telefono, int reserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechadenacimiento = fecha;
		this.telefono = telefono;
		this.idreserva = reserva;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFechadenacimiento() {
		return fechadenacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public int getIdreserva() {
		return idreserva;
	}
}
