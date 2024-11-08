package org.example.gestionhotel.model.repository;

import org.example.gestionhotel.model.util.ReservaVO;

import java.util.ArrayList;

public interface ReservaRepository {
    // Obtener lista reservas
    ArrayList<ReservaVO> ObtenerListaReservas() throws ExcepcionHotel;

    // añadir reserva
    public void aniadirReserva(ReservaVO reserva) throws ExcepcionHotel;

    // eliminar reserva
    public void eliminarReserva(int idReserva) throws ExcepcionHotel;

    // editar editReserva
    public void editReserva(int idReserva) throws ExcepcionHotel;

    // obtener último reserva
    public int ultimaReserva() throws ExcepcionHotel;
}
