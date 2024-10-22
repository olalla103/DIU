package com.example.agenda.model.repository.impl;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.impl.ConexionJDBC;
import com.example.agenda.model.repository.ExceptionPerson;
import com.example.agenda.model.repository.PersonVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonRepositoryImpl {
    /* Tengo que empezar haciendo el select en la base de datos para obtener
     * todas las personas que están en la base de datos.
     * Después tengo que pasar estos datos a la clase Person y más tarde transmitirlos a
     * la interfaz*/

    // ATRIBUTOS
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentanciaSQL;
    private ArrayList<PersonVO> personas;
    private PersonVO persona;

    // voy a hacer el método para obtener las personas de la base de datos
    public ArrayList<PersonVO> ObtenerListaPesonas() throws ExceptionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.personas = new ArrayList<>();
            this.stmt = conn.createStatement();
            this.sentanciaSQL = "SELECT * FROM persona";
            ResultSet rs = this.stmt.executeQuery(this.sentanciaSQL);

            while (rs.next()) {
                Integer codigo = rs.getInt("codigo");
                this.persona = new PersonVO(persona.getNombre(), persona.getApellidos(), persona.getCalle(),
                        persona.getCiudad(), persona.getCodigoPostal(), persona.getFechaNacimiento());
                this.persona.setCodigoPostal(codigo);
                this.personas.add(this.persona);
            }

            this.conexion.desconectarBD(conn);
            return this.personas;
        } catch (SQLException var6) {
            throw new ExceptionPerson("No se ha podido realizar la operación");
        }
    }

}
