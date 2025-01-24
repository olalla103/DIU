package org.example.gestionhotel.model.util;

import org.example.gestionhotel.model.ClienteVO;
import org.example.gestionhotel.model.ReservaVO;
import org.example.gestionhotel.Reserva;

import java.util.ArrayList;

public class ReservaUtil {
    // Convierto de Reserva a ReservaVO
    public static ArrayList<ReservaVO> getReservaVO(ArrayList<Reserva> reservas, ArrayList<ClienteVO> clientes) {
        ArrayList<ReservaVO> reservasVOs = new ArrayList<>();
        for (Reserva reserva : reservas) {
            ClienteVO cliente = null;
            boolean clienteEncontrado = false;

            for (ClienteVO c : clientes) {
                if (c.getDni().equals(reserva.getDni().get())) {
                    cliente = c;
                    clienteEncontrado = true;
                }
            }

            // Si se encontró el cliente, asignamos la reserva
            if (clienteEncontrado) {
                reservasVOs.add(new ReservaVO(
                        reserva.getIdReserva().get(),
                        reserva.getLlegada().get(),
                        reserva.getSalida().get(),
                        reserva.getTipoHabitacion(),
                        Integer.valueOf(reserva.getnumHabitaciones().get()),
                        reserva.getFumador().getValue(),
                        reserva.getRegimenAlojamiento(),
                        cliente // Asignamos el cliente encontrado
                ));
            } else {
                // Si no se encuentra el cliente, puedes decidir qué hacer (añadir null, lanzar excepción, etc.)
                reservasVOs.add(new ReservaVO(
                        reserva.getIdReserva().get(),
                        reserva.getLlegada().get(),
                        reserva.getSalida().get(),
                        reserva.getTipoHabitacion(),
                        Integer.valueOf(reserva.getnumHabitaciones().get()),
                        reserva.getFumador().getValue(),
                        reserva.getRegimenAlojamiento(),
                        null // Aquí asignamos null si no encontramos el cliente
                ));
            }
        }
        return reservasVOs;
    }

    // Convertir de ReservaVO a Reserva
    public static ArrayList<Reserva> getReserva(ArrayList<ReservaVO> reservaVOS) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        for (ReservaVO reservaVO : reservaVOS) {
            reservas.add(new Reserva(
                    reservaVO.getIdReserva(),
                    reservaVO.getNumeroHabitaciones(),
                    reservaVO.getLlegada(),
                    reservaVO.getSalida(),
                    reservaVO.getTipoHabitacion(),
                    reservaVO.getFumador(),
                    reservaVO.getRegimenAlojamiento(),
                    reservaVO.getCliente() != null ? reservaVO.getCliente().getDni() : null // Obtenemos el DNI del cliente si existe
            ));
        }
        return reservas;
    }

    // Convertir de Reserva a ReservaVO (versión singular)
    public static ReservaVO getReservaVO(Reserva reserva) {
        // Buscar el cliente en otro contexto, o dejarlo en null si no se encuentra
        ClienteVO cliente = null;
        // Este código puede cambiar dependiendo de cómo obtienes los clientes
        // Aquí puedes tener alguna lógica para recuperar el cliente si es necesario.

        return new ReservaVO(
                reserva.getIdReserva().get(),
                reserva.getLlegada().get(),
                reserva.getSalida().get(),
                reserva.getTipoHabitacion(),
                Integer.valueOf(reserva.getnumHabitaciones().get()),
                reserva.getFumador().getValue(),
                reserva.getRegimenAlojamiento(),
                cliente // Aquí agregamos el cliente, que podría ser null si no lo encontramos
        );
    }


    // Convertir de ReservaVO a Reserva (versión singular)
    public static Reserva getReserva(ReservaVO reservaVO) {
        return new Reserva(
                reservaVO.getIdReserva(),
                reservaVO.getNumeroHabitaciones(),
                reservaVO.getLlegada(),
                reservaVO.getSalida(),
                reservaVO.getTipoHabitacion(),
                reservaVO.getFumador(),
                reservaVO.getRegimenAlojamiento(),
                reservaVO.getCliente() != null ? reservaVO.getCliente().getDni() : null // Aseguramos que si cliente es null, el DNI también lo sea
        );
    }
}



