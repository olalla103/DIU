package com.example.agenda.model.repository.impl;

import com.example.agenda.model.repository.ExceptionPerson;
import com.example.agenda.model.repository.PersonRepository;
import com.example.agenda.model.repository.PersonVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonRepositoryImpl implements PersonRepository {
    /* Tengo que empezar haciendo el select en la base de datos para obtener
     * todas las personas que están en la base de datos.
     * Después tengo que pasar estos datos a la clase Person y más tarde transmitirlos a
     * la interfaz*/

    // ATRIBUTOS
    private final Conexion conexion = new Conexion();
    private Statement stmt;
    private String sentenciaSQL;
    private ArrayList<PersonVO> personas;
    private PersonVO persona;

    // voy a hacer el método para obtener TODAS las personas de la base de datos
    @Override
    public ArrayList<PersonVO> ObtenerListaPersonas() throws ExceptionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.personas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentenciaSQL = "SELECT * FROM persona";
            ResultSet rs = this.stmt.executeQuery(this.sentenciaSQL); // Aquí salta al catch

            while (rs.next()) {
                Integer codigo = rs.getInt("id");
                String nombre = rs.getString("firstName");
                String apellidos = rs.getString("lastName");
                String calle = rs.getString("street");
                String ciudad = rs.getString("city");
                Integer codigoPostal = rs.getInt("postalCode");
                LocalDate fechaNacimiento = rs.getDate("birthday").toLocalDate();

                this.persona = new PersonVO(codigo, nombre, apellidos, calle, ciudad, codigoPostal, fechaNacimiento);
                this.personas.add(this.persona);
            }
            this.conexion.desconectarBD(conn);
            return this.personas;
        } catch (SQLException var6) {
            throw new ExceptionPerson("No se ha podido realizar la operación");
        }
    }

    @Override
    public void addPersona(PersonVO personVO) throws ExceptionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentenciaSQL = "INSERT INTO persona (nombre,apellido,calle,ciudad,codigoPostal,fechaNacimiento) " +
                    "VALUES ('" + persona.getfirstName() + "','" + persona.getlastName() + "', '" +
                    persona.getstreet() + "', '" + persona.getcity() + "', '" +
                    persona.getpostalCode() + "', '" + persona.getbirthday() + "');";
            this.stmt.executeUpdate(this.sentenciaSQL);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExceptionPerson("No se ha podido realizar la operación");
        }

    }

    // hacer más tarde
    @Override
    public void editPersona(PersonVO personVO) throws ExceptionPerson {

    }

    @Override
    public void deletePersona(Integer id) throws ExceptionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentenciaSQL = "DELETE FROM PERSONA WHERE id = " + id;
            this.stmt.executeUpdate(this.sentenciaSQL);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExceptionPerson("No se ha podido realizar la operación");
        }
    }

    @Override
    public void lastID() throws ExceptionPerson {
        // no está acabado
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentenciaSQL = "SELECT max(id) from persona";
            this.stmt.executeUpdate(this.sentenciaSQL);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (
                SQLException var3) {
            throw new ExceptionPerson("No se ha podido realizar la operación");
        }

    }
}
