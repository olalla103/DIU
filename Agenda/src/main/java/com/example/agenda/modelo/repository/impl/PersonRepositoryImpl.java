package com.example.agenda.modelo.repository.impl;

import com.example.agenda.modelo.ExcepcionPerson;
import com.example.agenda.modelo.PersonVO;
import com.example.agenda.modelo.repository.PersonRepository;

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

    public ArrayList<PersonVO> ObtenerListaPersonas() throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.personas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM persona";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                Integer cod = rs.getInt("id");
                String nom = rs.getString("firstName");
                String ape = rs.getString("lastName");
                String ca = rs.getString("street");
                String cp = rs.getString("postalCode");
                String ciu = rs.getString("city");
                LocalDate fecha = rs.getDate("birthday").toLocalDate();
                this.persona = new PersonVO(cod, nom, ape, ca, cp, ciu, fecha);
                this.persona.setCodigo(cod);
                this.personas.add(this.persona);
            }

            this.conexion.desconectarBD(conn);
            return this.personas;
        } catch (SQLException var6) {
            throw new ExcepcionPerson("No se ha podido realizar la operación");
        }
    }

    public void addPersona(PersonVO m) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO persona (firstName, lastName, street, postalCode, city, birthday) VALUES ('" + m.getFirstName() + "','" + m.getLastName() + "','" + m.getStreet() + "','" + m.getPostalCode() + "','" + m.getCity() + "','" + m.getBirthday() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionPerson("No se ha podido realizar la operación");
        }
    }

    public void deletePersona(Integer idPersona) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM personas WHERE id = %d", lastId());
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionPerson("No se ha podido realizar la eliminación");
        }
    }

    public void editPersona(PersonVO personVO) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE persona SET firstName = '%s', lastName = '%s', street = '%s', postalCode = '%s', city = '%s', birthday = '%s' WHERE id = %d", personVO.getFirstName(), personVO.getLastName(), personVO.getStreet(), personVO.getPostalCode(), personVO.getCity(), personVO.getBirthday(), lastId());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionPerson("No se ha podido realizar la edición");
        }
    }

    public int lastId() throws ExcepcionPerson {
        int lastPersonId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for (ResultSet registro = comando.executeQuery("SELECT id FROM persona ORDER BY id DESC LIMIT 1"); registro.next(); lastPersonId = registro.getInt("id")) {
            }
            System.out.println("ultimo id: " + lastPersonId);
            return lastPersonId;
        } catch (SQLException var5) {
            throw new ExcepcionPerson("No se ha podido realizar la busqueda del ID");
        }
    }
}