package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.model.Huespedes;
import jdbc.model.Reserva;

public class HuespedDAO {
	private Connection con;
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huespedes huespedes) {
		try {
			String sql = "INSERT INTO huespedes (nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva) VALUES (?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				
				pstm.setString(1, huespedes.getNombre());
				pstm.setString(2, huespedes.getApellido());
				pstm.setDate(3, huespedes.getFechadenacimiento());
				pstm.setString(4, huespedes.getNacionalidad());
				pstm.setString(5, huespedes.getTelefono());
				pstm.setInt(6, huespedes.getIdreserva());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						huespedes.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huespedes> buscar() {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			String sql = "SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes";

			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEnHuespedes(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private void transformarResultSetEnHuespedes(List<Huespedes> huespedes, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Huespedes produto = new Huespedes(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));

				huespedes.add(produto);
			}
		}
	}

	public List<Huespedes> buscarId(String text) {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			String sql = "SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE id = ?";
			
			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.setString(1, text);
				pstm.execute();

				transformarResultSetEnHuespedes(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void actualizar(String nombre, String apellido, Date fechaN, String nacionalidad, String telefono,
			Integer idReserva, Integer id) {
		try (PreparedStatement stm = con.prepareStatement("UPDATE huespedes SET nombre = ?, apellido = ?, fecha_de_nacimiento = ?, nacionalidad = ?, telefono = ?, id_reserva = ? WHERE id = ?")) {
			System.out.println(nacionalidad);
			stm.setString(1, nombre);
			stm.setString(2, apellido);
			stm.setDate(3, fechaN);
			stm.setString(4, nacionalidad);
			stm.setString(5, telefono);
			stm.setInt(6, idReserva);
			stm.setInt(7, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void eliminar(Integer id) {
		try (PreparedStatement stm = con.prepareStatement("DELETE FROM huespedes WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
