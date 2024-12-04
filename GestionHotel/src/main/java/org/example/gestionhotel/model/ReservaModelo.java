package org.example.gestionhotel.model;

import org.example.gestionhotel.model.repository.ExcepcionHotel;
import org.example.gestionhotel.model.repository.ReservaRepository;
import org.example.gestionhotel.Reserva;
import org.example.gestionhotel.model.util.ReservaUtil;

import java.util.ArrayList;

public class ReservaModelo {

    static ReservaRepository reservaRepository;

    // Setter para la inyección del repositorio
    public void setReservaRepository(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    // Método para obtener las reservas desde el repositorio
    public static ArrayList<ReservaVO> obtenerReservas() throws ExcepcionHotel {
        return reservaRepository.ObtenerListaReservas();
    }

    // Método para mostrar todas las reservas
    public ArrayList<Reserva> mostrarReservas() throws ExcepcionHotel {
        ArrayList<ReservaVO> listaReservasVO = obtenerReservas();
        ArrayList<Reserva> listaReservas = ReservaUtil.getReserva(listaReservasVO);  // Convertimos de VO a modelo
        return listaReservas;
    }

    // Método para insertar una nueva reserva
    public void insertarReserva(Reserva reserva) throws ExcepcionHotel {
        ReservaVO reservaVO = ReservaUtil.getReservaVO(reserva);  // Convertimos de modelo a VO
        reservaRepository.aniadirReserva(reservaVO);  // Añadimos la reserva al repositorio
    }

    // Método para eliminar una reserva por su ID
    public void eliminarReserva(int idReserva) throws ExcepcionHotel {
        reservaRepository.eliminarReserva(idReserva);  // Eliminamos la reserva por ID
    }

    // Método para modificar una reserva existente
    public void modificarReserva(Reserva reserva) throws ExcepcionHotel {
        ReservaVO reservaVO = ReservaUtil.getReservaVO(reserva);  // Convertimos de modelo a VO
        reservaRepository.editReserva(reservaVO);  // Modificamos la reserva en el repositorio
    }

    // Método para obtener el ID de la última reserva insertada
    public int obtenerUltimaReserva() throws ExcepcionHotel {
        return reservaRepository.ultimaReserva();  // Obtenemos el último ID de reserva
    }
}
