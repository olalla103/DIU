package org.example.gestionhotel.model.repository.impl;

import org.example.gestionhotel.model.repository.ClienteRepository;
import org.example.gestionhotel.model.util.ClienteVO;
import org.example.gestionhotel.model.repository.ExcepcionHotel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteRepositoryImpl implements ClienteRepository {
    Conexion conexion = new Conexion();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ClienteVO> clientes;
    private ClienteVO cliente;

    @Override
    public ArrayList<ClienteVO> ObtenerListaClientes() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.clientes = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM cliente";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String direccion = rs.getString("direccion");
                String localidad = rs.getString("localidad");
                String provincia = rs.getString("provincia");
                this.cliente = new ClienteVO(dni, nombre, apellidos, direccion, localidad, provincia); // poner variables
                this.cliente.setDni(dni);
                this.clientes.add(this.cliente);
            }
            this.conexion.desconectarBD(conn);
            return this.clientes;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }

    }

    @Override
    public void aniadirCliente(ClienteVO cliente) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO cliente (dni,nombre,apellidos,direccion,localidad,provincia) VALUES ('" + cliente.getDni() + "','" + cliente.getNombre() +
                    "','" + cliente.getApellidos() + "','" + cliente.getDireccion() + "','" + cliente.getLocalidad()
                    + "','" + cliente.getDireccion() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    @Override
    public void eliminarCliente(String dni) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM cliente WHERE dni = %s", dni);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido eliminar");
        }
    }

    @Override
    public void editCliente(String dni) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE cliente SET dni = '%s', nombre = '%s',apellidos = '%s', direccion = '%s'," +
                            "localidad = '%s', provincia = '%s'" +
                            " WHERE dni = %d",
                    cliente.getDni(), cliente.getNombre(), cliente.getApellidos(),
                    cliente.getDireccion(), cliente.getLocalidad(), cliente.getProvincia());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionHotel("No se ha podido editar el cliente");
        }
    }

    @Override
    public int ultimoCliente() throws ExcepcionHotel {
        int ultimoDni = 0;
        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for (ResultSet registro = comando.executeQuery("SELECT dni FROM cliente ORDER BY codigo DESC LIMIT 1");
                 registro.next(); ultimoDni = registro.getInt("dni")) {
            }
            return ultimoDni;
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la busqueda del DNI");
        }
    }
}
