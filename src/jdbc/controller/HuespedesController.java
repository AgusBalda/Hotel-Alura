package jdbc.controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import jdbc.dao.HuespedDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.model.Huespedes;

public class HuespedesController {
	private HuespedDAO huespedDAO;
	
	public HuespedesController() {
		Connection con = new ConnectionFactory().recuperarConexion();
		this.huespedDAO = new HuespedDAO(con);
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedDAO.guardar(huespedes);
	}

	public List<Huespedes> listarHuespedes() {
		return this.huespedDAO.buscar();
	}

	public List<Huespedes> listarHuespedesId(String text) {
		return this.huespedDAO.buscarId(text);
	}

	public void actualizar(String nombre, String apellido, Date fechaN, String nacionalidad, String telefono,
			Integer idReserva, Integer id) {
		this.huespedDAO.actualizar(nombre, apellido, fechaN, nacionalidad, telefono, idReserva, id);
	}

	public void Eliminar(Integer id) {
		this.huespedDAO.eliminar(id);
	}


}
