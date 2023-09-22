package jdbc.model;

import java.sql.Date;

public class Reserva {
	
	private int id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private int valor;
	private String formaDePago;
	
	public Reserva(Date entrada, Date salida, int valor, String forma) {
		this.fechaEntrada = entrada;
		this.fechaSalida = salida;
		this.valor = valor;
		this.formaDePago = forma;
	}
	
	public Reserva(int id, Date entrada, Date salida, int valor, String forma) {
		this.id = id;
		this.fechaEntrada = entrada;
		this.fechaSalida = salida;
		this.valor = valor;
		this.formaDePago = forma;
	}

	public int getId() {
		return id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public int getValor() {
		return valor;
	}

	public String getFormaDePago() {
		return formaDePago;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
