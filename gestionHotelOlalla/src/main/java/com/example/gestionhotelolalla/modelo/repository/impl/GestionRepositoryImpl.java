package com.example.gestionhotelolalla.modelo.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestionRepositoryImpl {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ClienteVO> clientes;
    private ClienteVO cliente;
    private ArrayList<ReservaVO> reservas;
    private ReservaVO reserva;

    public GestionRepositoryImpl() {
    }

    /**
     * Obtiene la lista de ClientesVO de la base de datos
     *
     * @return
     * @throws ExcepcionHotel
     */
    @Override
    public ArrayList<ClienteVO> ObtenerListaClienteVO() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.clientes = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM cliente ";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                String dni = rs.getString("dni");
                String n = rs.getString("nombre");
                String a = rs.getString("apellidos");
                String d = rs.getString("direccion");
                String lo = rs.getString("localidad");
                String p = rs.getString("provincia");
                this.cliente = new ClienteVO(dni, n, a, d, lo, p);
                this.clientes.add(this.cliente);
            }
            this.conexion.desconectarBD(conn);
            return this.clientes;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se puede");
        }
    }

    /**
     * Obtiene la lista de reservasVO a partir del dni de el cliente de la base de datos
     *
     * @param dniC
     * @return
     * @throws ExcepcionHotel
     */
    @Override
    public ArrayList<ReservaVO> ObtenerListaReservaVO(String dniC) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.reservas = new ArrayList();
            this.stmt = conn.createStatement();
            String sql = String.format("SELECT * FROM reserva WHERE dniCliente IN (SELECT dni FROM cliente WHERE dni='%s') ORDER BY llegada DESC", dniC);
            ResultSet rs = this.stmt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("idReserva");
                LocalDate llegada = rs.getDate("llegada").toLocalDate();
                LocalDate fin = rs.getDate("salida").toLocalDate();
                String valorT = rs.getString("tipoHabitacion");
                if (valorT.equals("Individual")) {
                    valorT = "INDIVIDUAL";
                }
                if (valorT.equals("Doble")) {
                    valorT = "DOBLE";
                }
                if (valorT.equals("Junior")) {
                    valorT = "Junior";
                }
                if (valorT.equals("Suite")) {
                    valorT = "SUITE";
                }
                tipoHabitacion tipoH = tipoHabitacion.valueOf(valorT);
                Integer numH = rs.getInt("numHabitaciones");
                Boolean f = rs.getBoolean("fumador");
                String valor = rs.getString("regimenAlojamiento");
                if (valor.equals("Desayuno incluido")) {
                    valor = "ALOJAMIENTOYDESAYUNO";
                }
                if (valor.equals("Media pensión")) {
                    valor = "MEDIAPENSION";
                }
                if (valor.equals("Pensión completa")) {
                    valor = "PENSIONCOMPLETA";
                }
                if (valor.equals("Solo alojamiento")) {
                    valor = "SOLOALOJAMIENTO";
                }
                regimenAlojamiento regA = regimenAlojamiento.valueOf(valor);
                String DNIC = rs.getString("dniCliente");
                this.reserva = new ReservaVO(llegada, fin, tipoH, numH, f, regA, DNIC);
                reserva.setIdVO(id);
                this.reservas.add(this.reserva);
            }
            this.conexion.desconectarBD(conn);
            return this.reservas;
        } catch (SQLException var6) {
            var6.printStackTrace();
            throw new ExcepcionHotel("No se puede");
        }
    }

    /**
     * Busca el cliente por dni de la base de datos
     *
     * @param dniB
     * @return
     * @throws ExcepcionHotel
     */
    @Override
    public ClienteVO busquedaClienteVO(String dniB) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.cliente = new ClienteVO();
            this.stmt = conn.createStatement();
            String sql = String.format("SELECT * FROM cliente WHERE dni='%s'", dniB);
            ResultSet rs = this.stmt.executeQuery(sql);
            while (rs.next()) {
                String dni = rs.getString("dni");
                String n = rs.getString("nombre");
                String a = rs.getString("apellidos");
                String d = rs.getString("direccion");
                String lo = rs.getString("localidad");
                String p = rs.getString("provincia");
                this.cliente = new ClienteVO(dni, n, a, d, lo, p);
            }
            this.conexion.desconectarBD(conn);
            return this.cliente;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se puede");
        }
    }

    /**
     * Añade el clienteVO a la base de datos
     *
     * @param m
     * @throws ExcepcionHotel
     */
    @Override
    public void addClienteVO(ClienteVO m) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO cliente (dni, nombre, apellidos, direccion, localidad, provincia  ) VALUES ('" + m.getDNIVO() + "','" + m.getNombreVO() + "','" + m.getApellidosVO() + "','" + m.getDireccionVO() + "','" + m.getLocalidadVO() + "','" + m.getProvinciaVO() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    /**
     * Añade la reservaVO a la base de datos
     *
     * @param r
     * @throws ExcepcionHotel
     */
    @Override
    public void addReservaVO(ReservaVO r) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Integer fum;
            if (r.getFumadorVO()) {
                fum = 1;
            } else {
                fum = 0;
            }

            // Corrige la consulta sin el campo de id (autoincremental)
            this.sentencia = "INSERT INTO reserva (llegada, salida, tipoHabitacion, numHabitaciones, fumador, regimenAlojamiento, dniCliente) " +
                    "VALUES ('" + r.getFechaLlegadaVO() + "','" + r.getFechaFinVO() + "','" + r.gettipoHabitacionVO() + "'," + r.getNumHabitacionVO() + ",'" + fum + "','" + r.getRegAlojVO() + "','" + r.getDNICliente() + "');";

            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            var3.printStackTrace();
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }


    /**
     * Borrar el clienteVo de la base de datos
     *
     * @param DniP
     * @throws ExcepcionHotel
     */
    @Override
    public void deleteClienteVO(String DniP) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM cliente WHERE dni = '%s'", DniP);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la eliminación");
        }
    }

    /**
     * Borrar la reservaVO de la base de datos
     *
     * @param id
     * @throws ExcepcionHotel
     */
    @Override
    public void deleteReservaVO(Integer id) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM reserva WHERE idReserva = %d", id);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la eliminación");
        }
    }

    /**
     * Editar el clienteVO de la base de datos
     *
     * @param ClienteVO
     * @throws ExcepcionHotel
     */
    @Override
    public void editClienteVO(ClienteVO ClienteVO) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE cliente SET nombre = '%s', apellidos = '%s', direccion = '%s', localidad = '%s', provincia = '%s' WHERE dni = '%s'", ClienteVO.getNombreVO(), ClienteVO.getApellidosVO(), ClienteVO.getDireccionVO(), ClienteVO.getLocalidadVO(), ClienteVO.getProvinciaVO(), ClienteVO.getDNIVO());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionHotel("No se ha podido realizar la edición");
        }
    }

    /**
     * Edita la reservaVO en la base de datos
     *
     * @param reservaVO
     * @throws ExcepcionHotel
     */
    @Override
    public void editReservaVO(ReservaVO reservaVO) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Integer fum;
            if (reservaVO.getFumadorVO()) {
                fum = 1;
            } else {
                fum = 0;
            }
            String sql = String.format("UPDATE reserva SET llegada = '%s', salida = '%s', numHabitaciones = '%s', tipoHabitacion = '%s', fumador = '%s' , regimenAlojamiento = '%s' , dniCliente = '%s' WHERE idReserva = %d", reservaVO.getFechaLlegadaVO(), reservaVO.getFechaFinVO(), reservaVO.getNumHabitacionVO(), reservaVO.gettipoHabitacionVO(), fum, reservaVO.getRegAlojVO(), reservaVO.getDNICliente(), reservaVO.getIdVO());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionHotel("No se ha podido realizar la edición");
        }
    }

    /**
     * Recoge el id de la reserva ultimo introducido
     *
     * @return
     * @throws ExcepcionHotel
     */
    @Override
    public int lastIdReservaVO() throws ExcepcionHotel {
        int lastPersonId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            ResultSet resultSet = comando.executeQuery("SELECT AUTO_INCREMENT " +
                    "FROM information_schema.TABLES " +
                    "WHERE TABLE_SCHEMA = 'hotel' " +
                    "AND TABLE_NAME = 'reserva'");

            {
                if (resultSet.next()) {
                    lastPersonId = resultSet.getInt("AUTO_INCREMENT") - 1;
                }
                return lastPersonId;
            }

        } catch (SQLException e) {
            System.out.println(e);
            throw new ExcepcionHotel("No se ha podido realizar la búsqueda del ID");
        }
    }

    @Override
    public int DNIClienteVO() throws ExcepcionHotel {
        return 0;
    }

    @Override
    public ArrayList<ReservaVO> ObtenerListaReservaVO() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.reservas = new ArrayList();
            this.stmt = conn.createStatement();
            String sql = String.format("SELECT * FROM reserva");
            ResultSet rs = this.stmt.executeQuery(sql);
            while (rs.next()) {
                Integer id = rs.getInt("idReserva");
                LocalDate llegada = rs.getDate("llegada").toLocalDate();
                LocalDate fin = rs.getDate("salida").toLocalDate();
                Integer numH = rs.getInt("numHabitaciones");
                String valorT = rs.getString("tipoHabitacion");
                if (valorT.equals("Individual")) {
                    valorT = "INDIVIDUAL";
                }
                if (valorT.equals("Doble")) {
                    valorT = "DOBLE";
                }
                if (valorT.equals("Junior")) {
                    valorT = "Junior";
                }
                if (valorT.equals("Suite")) {
                    valorT = "SUITE";
                }
                tipoHabitacion tipoH = tipoHabitacion.valueOf(valorT);
                Boolean f = rs.getBoolean("fumador");
                String valor = rs.getString("regimenAlojamiento");
                if (valor.equals("Desayuno incluido")) {
                    valor = "ALOJAMIENTOYDESAYUNO";
                }
                if (valor.equals("Media pensión")) {
                    valor = "MEDIAPENSION";
                }
                if (valor.equals("Pensión completa")) {
                    valor = "PENSIONCOMPLETA";
                }
                if (valor.equals("Solo alojamiento")) {
                    valor = "SOLOALOJAMIENTO";
                }
                regimenAlojamiento regA = regimenAlojamiento.valueOf(valor);
                String DNIC = rs.getString("dniCliente");
                this.reserva = new ReservaVO(llegada, fin, tipoH, numH, f, regA, DNIC);
                reserva.setIdVO(id);
                this.reservas.add(this.reserva);
            }
            this.conexion.desconectarBD(conn);
            return this.reservas;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se puede");
        }
    }
}
