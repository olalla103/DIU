package org.example.gestionhotel.model;

import org.example.gestionhotel.model.repository.ExcepcionHotel;
import org.example.gestionhotel.model.repository.ReservaRepository;
import org.example.gestionhotel.Reserva;
import org.example.gestionhotel.model.util.ReservaUtil;

import java.util.ArrayList;

public class ReservaModelo {

    static ReservaRepository reservaRepository;

    public void setReservaRepository(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public static ArrayList<ReservaVO> obtenerReservas() throws ExcepcionHotel {
        return reservaRepository.ObtenerListaReservas();
    }

    public ArrayList<Reserva> mostrarReservas() throws ExcepcionHotel {
        ArrayList<ReservaVO> listaReservasVO = obtenerReservas();
        ArrayList<Reserva> listaReservas;
        listaReservas = ReservaUtil.getReserva(listaReservasVO);
        return listaReservas;
    }

    public void insertarReserva(Reserva reserva) throws ExcepcionHotel {
        ReservaVO reservaVO = ReservaUtil.getReservaVO(reserva);
        reservaRepository.aniadirReserva(reservaVO);
    }

    public void eliminarReserva(int idReserva) throws ExcepcionHotel {
        reservaRepository.eliminarReserva(idReserva);
    }

    public void modificarReserva(Reserva reserva) throws ExcepcionHotel {
        ReservaVO reservaVO = ReservaUtil.getReservaVO(reserva);
        reservaRepository.editReserva(reservaVO);
    }

    public int obtenerUltimaReserva() throws ExcepcionHotel {
        return reservaRepository.ultimaReserva();
    }
}
