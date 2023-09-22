package jdbc.controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import jdbc.dao.ReservaDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.model.Reserva;

public class ReservasController {
	private ReservaDAO reservaDAO;
	
	public ReservasController() {
		Connection con = new ConnectionFactory().recuperarConexion();
		this.reservaDAO = new ReservaDAO(con);
	}
	
	public void guardar(Reserva reserva) {
		this.reservaDAO.guardar(reserva);
	}

	public List<Reserva> buscar() {
		return this.reservaDAO.buscar();
	}

	public List<Reserva> buscarId(String text) {
		return this.reservaDAO.buscarId(text);
	}

	public void actualizar(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
		this.reservaDAO.actualizar(fechaE, fechaS, valor ,formaPago, id);	
		}

	public void Eliminar(Integer id) {
		this.reservaDAO.eliminar(id);
	}

}
