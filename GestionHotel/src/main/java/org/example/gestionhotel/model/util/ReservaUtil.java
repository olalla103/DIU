package org.example.gestionhotel.model.util;

import org.example.gestionhotel.model.ReservaVO;
import org.example.gestionhotel.view.Reserva;

import java.util.ArrayList;

public class ReservaUtil {
    // Convierto de Reserva a ReservaVO
    public static ArrayList<ReservaVO> getReservaVO(ArrayList<Reserva> reservas) {
        ArrayList<ReservaVO> reservasVOs = new ArrayList<>();
        for (Reserva reserva : reservas) { // Cambié ReservasVOs por Reservas
            // Convertir Reserva a ReservaVO
            reservasVOs.add(new ReservaVO(
                    reserva.getIdReserva().get(),
                    reserva.getLlegada().get(),
                    reserva.getSalida().get(),
                    reserva.getTipoHabitacion(),
                    Integer.valueOf(reserva.getnumHabitaciones().get()),
                    reserva.getFumador().getValue(),
                    reserva.getRegimenAlojamiento(),
                    reserva.getDni().get()
            ));
        }
        return reservasVOs;
    }

    // Convierto de ReservaVO a Reserva
    public static ArrayList<Reserva> getReserva(ArrayList<ReservaVO> reservaVOS) {
        ArrayList<Reserva> Reservas = new ArrayList<>();
        for (ReservaVO reservaVO : reservaVOS) {

            Reservas.add(new Reserva(
                    reservaVO.getIdReserva(),
                    reservaVO.getNumeroHabitaciones(),
                    reservaVO.getLlegada(),
                    reservaVO.getSalida(),
                    reservaVO.getTipoHabitacion(),
                    reservaVO.getFumador(),
                    reservaVO.getRegimenAlojamiento(),
                    reservaVO.getDni()
            ));
        }
        return Reservas;
    }

    public static ReservaVO getReservaVO(Reserva reserva) {
        ReservaVO reservaVO = new ReservaVO(
                reserva.getIdReserva().get(),
                reserva.getLlegada().get(),
                reserva.getSalida().get(),
                reserva.getTipoHabitacion(),
                reserva.getnumHabitaciones().get(),
                reserva.getFumador().get(),
                reserva.getRegimenAlojamiento(),
                reserva.getDni().get()
        );
        return reservaVO;
    }

    public static Reserva getReserva(ReservaVO reservaVO) {
        Reserva reserva = new Reserva(
                reservaVO.getIdReserva(),
                reservaVO.getNumeroHabitaciones(),
                reservaVO.getLlegada(),
                reservaVO.getSalida(),
                reservaVO.getTipoHabitacion(),
                reservaVO.getFumador(),
                reservaVO.getRegimenAlojamiento(),
                reservaVO.getDni()
        );
        return reserva;
    }

}
