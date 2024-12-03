package org.example.gestionhotel.model.repository.impl;

import org.example.gestionhotel.model.repository.ExcepcionHotel;
import org.example.gestionhotel.model.repository.ReservaRepository;
import org.example.gestionhotel.model.ReservaVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaRepositoryImpl implements ReservaRepository {
    Conexion conexion = new Conexion();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ReservaVO> reservas;
    public ReservaVO reserva;

    @Override
    public ArrayList<ReservaVO> ObtenerListaReservas() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.reservas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM reserva";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                Integer idReserva = rs.getInt("idReserva");
                LocalDate llegada = rs.getDate("llegada").toLocalDate();
                LocalDate salida = rs.getDate("salida").toLocalDate();
                tipoHabitacion tipoHabitacion = null;
                if (rs.getString("tipoHabitacion") != null) {
                    try {
                        tipoHabitacion = org.example.gestionhotel.model.repository.impl.tipoHabitacion.valueOf(rs.getString("tipoHabitacion"));
                    } catch (IllegalArgumentException e) {
                        throw new ExcepcionHotel("Valor no válido en columna tipoHabitacion");
                    }
                }
                Integer numHabitaciones = rs.getInt("numHabitaciones");
                Boolean fumador = rs.getBoolean("fumador");
                regimenAlojamiento regimenAlojamiento = null;
                if (rs.getString("regimenAlojamiento") != null) {
                    try {
                        regimenAlojamiento = org.example.gestionhotel.model.repository.impl.regimenAlojamiento.valueOf(rs.getString("regimenAlojamiento"));
                    } catch (IllegalArgumentException e) {
                        throw new ExcepcionHotel("Valor no válido en columna regimenAlojamiento");
                    }
                }
                String dniCliente = rs.getString("dni");
                this.reserva = new ReservaVO(idReserva, llegada, salida, tipoHabitacion, numHabitaciones, fumador, regimenAlojamiento, dniCliente); // poner variables
                this.reserva.setIdReserva(idReserva);
                this.reservas.add(this.reserva);
            }

            this.conexion.desconectarBD(conn);
            return this.reservas;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");

        }
    }

    @Override
    public void aniadirReserva(ReservaVO reserva) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO reserva (idReserva, llegada, salida, tipoHabitacion, numHabitaciones, fumador, regimenAlojamiento,dniCliente)" +
                    " VALUES ('" + reserva.getIdReserva() + "','" + reserva.getLlegada() +
                    "','" + reserva.getSalida() + "','" + reserva.getNumeroHabitaciones() + "','" + reserva.getTipoHabitacion() + "','"
                    + reserva.getFumador() + "','" + reserva.getRegimenAlojamiento() + "','" + reserva.getCliente().getDni() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    @Override
    public void eliminarReserva(int idReserva) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "DELETE FROM reserva WHERE idReserva='" + idReserva + "'";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido eliminar");
        }
    }

    @Override
    // DA ERROR
    public void editReserva(ReservaVO reservaVO) throws ExcepcionHotel {
        // revisar si se puede cambiar el dni del cliente
        //  por ahora lo dejo como que NO
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "UPDATE reserva SET llegada = '" + reserva.getLlegada() +
                    "', salida = '" + reserva.getSalida() +
                    "', numHabitaciones = '" + reserva.getNumeroHabitaciones() +
                    "', tipoHabitacion = '" + reserva.getTipoHabitacion() +
                    "', fumador = '" + reserva.getFumador() +
                    "', regimenAlojamiento = '" + reserva.getRegimenAlojamiento() +
                    "' WHERE idReserva = '" + reserva.getIdReserva() + "';";

            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
        } catch (SQLException var4) {
            throw new ExcepcionHotel("No se ha podido editar la reserva");
        }

    }

    @Override
    public int ultimaReserva() throws ExcepcionHotel {
        int ultimaReserva = 0;
        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for (ResultSet registro = comando.executeQuery("SELECT max(idReserva) FROM reserva");
                 registro.next(); ultimaReserva = registro.getInt("dni")) {
            }
            return ultimaReserva;
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la busqueda de la reserva");
        }
    }
}
