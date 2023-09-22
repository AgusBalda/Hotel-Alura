package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.model.Reserva;

public class ReservaDAO {
	private Connection con;
	
	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_pago) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setDate(1, reserva.getFechaEntrada());
				pstm.setDate(2, reserva.getFechaSalida());
				pstm.setInt(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaDePago());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> buscar() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reservas";

			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarId(String id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {

			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reservas WHERE id = ?";

			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private void transformarResultSetEnReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reserva produto = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getInt(4), rst.getString(5));

				reservas.add(produto);
			}
		}
	}

	public void actualizar(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
		try (PreparedStatement stm = con.prepareStatement("UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ? WHERE id = ?")) {
			stm.setDate(1, fechaE);
			stm.setDate(2, fechaS);
			stm.setString(3, valor);
			stm.setString(4, formaPago);
			stm.setInt(5, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void eliminar(Integer id) {
		try (PreparedStatement stm = con.prepareStatement("DELETE FROM reservas WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
