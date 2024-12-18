package com.example.agenda.model.repository.impl;


import Modelo.repository.impl.ConexionJDBC;
import com.example.agenda.model.repository.ExceptionPerson;
import com.example.agenda.model.repository.PersonVO;
import com.example.agenda.model.repository.PersonRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonRepositoryImpl implements PersonRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<PersonVO> personas;
    private PersonVO persona;

    public PersonRepositoryImpl() {
    }

    /**
     * Obtiene la lista de personas de la base de datos
     *
     * @return lista de personas
     * @throws ExceptionPerson
     */
    public ArrayList<PersonVO> ObtenerListaPersonas() throws ExceptionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.personas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM Persona";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                Integer id = rs.getInt("Identificador");
                String n = rs.getString("Nombre");
                String a = rs.getString("Apellido");
                String c = rs.getString("Calle");
                Integer cp = rs.getInt("Codigo_Postal");
                String ciu = rs.getString("Ciudad");
                LocalDate f = rs.getDate("Año_nacimiento").toLocalDate();
                this.persona = new PersonVO(id, n, a, c, cp, ciu, f);
                this.personas.add(this.persona);
            }

            this.conexion.desconectarBD(conn);
            return this.personas;
        } catch (SQLException var6) {
            throw new ExceptionPerson("No es posible");
        }
    }

    /**
     * Añade una nueva persona a la base de datos.s
     *
     * @param m Persona a agregar
     * @throws ExceptionPerson
     */
    public void addPerson(PersonVO m) throws ExceptionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO Persona (Nombre, Apellido, Calle, Codigo_Postal, Ciudad, Año_nacimiento) VALUES ('" + m.getNombre() + "','" + m.getApellido() + "','" + m.getCalle() + "','" + m.getCodigoPostal() + "','" + m.getCiudad() + "','" + m.getNacimiento() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExceptionPerson("No se ha podido realizar la operación");
        }
    }

    /**
     * Borra la persona de la base de datos.
     *
     * @param idPersona
     * @throws ExceptionPerson
     */
    public void deletePerson(Integer idPersona) throws ExceptionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM Persona WHERE Identificador = %d", idPersona);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExceptionPerson("No se ha podido relaizar la eliminación");
        }
    }

    /**
     * Edita la persona de la base de datos
     *
     * @param PersonVO
     * @throws ExceptionPerson
     */
    public void editPerson(PersonVO PersonVO) throws ExceptionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE Persona SET Nombre = '%s', Apellido = '%s', Calle = '%s', Codigo_Postal = '%s', Ciudad = '%s', Año_nacimiento = '%s' WHERE Identificador = %d", PersonVO.getNombre(), PersonVO.getApellido(), PersonVO.getCalle(), PersonVO.getCodigoPostal(), PersonVO.getCiudad(), PersonVO.getNacimiento(), PersonVO.getId());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExceptionPerson("No se ha podido relaizar la edición");
        }
    }

    /**
     * Recoge el ultimo id de la base de datos
     *
     * @return
     * @throws ExceptionPerson
     */
    public int lastId() throws ExceptionPerson {
        int lastPersonId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            ResultSet resultSet = comando.executeQuery("SELECT AUTO_INCREMENT " +
                    "FROM information_schema.TABLES " +
                    "WHERE TABLE_SCHEMA = 'Agenda' " +
                    "AND TABLE_NAME = 'Persona'");

            {
                if (resultSet.next()) {
                    lastPersonId = resultSet.getInt("AUTO_INCREMENT") - 1;
                }
                System.out.println(lastPersonId);
                return lastPersonId;
            }

        } catch (SQLException e) {
            System.out.println(e);
            throw new ExceptionPerson("No se ha podido realizar la búsqueda del ID");
        }
    }
}