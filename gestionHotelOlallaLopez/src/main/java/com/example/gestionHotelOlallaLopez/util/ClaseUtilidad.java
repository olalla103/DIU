package com.example.gestionHotelOlallaLopez.util;

import com.example.gestionHotelOlallaLopez.modelo.Cliente;
import com.example.gestionHotelOlallaLopez.modelo.ClienteVO;
import com.example.gestionHotelOlallaLopez.modelo.Reserva;
import com.example.gestionHotelOlallaLopez.modelo.ReservaVO;

import java.util.ArrayList;

public class ClaseUtilidad {
    /**
     * Metodo para convertir de ClienteVO a Cliente
     *
     * @param clienteVO
     * @return
     */
    public Cliente convertirCliente(ClienteVO clienteVO) {
        Cliente cliente = new Cliente();
        cliente.setdni(clienteVO.getDNIVO());
        cliente.setNombre(clienteVO.getNombreVO());
        cliente.setApellidos(clienteVO.getApellidosVO());
        cliente.setDireccion(clienteVO.getDireccionVO());
        cliente.setLocalidad(clienteVO.getLocalidadVO());
        cliente.setProvincia(clienteVO.getProvinciaVO());
        return cliente;
    }

    /**
     * Metodo que transforma una lista de ClienteVO a lista de Clientes
     *
     * @param listaclienteVO
     * @return
     */
    public ArrayList<Cliente> listaCliente(ArrayList<ClienteVO> listaclienteVO) {
        ArrayList<Cliente> listacliente = new ArrayList<Cliente>();
        Cliente cliente = new Cliente();
        for (int i = 0; i < listaclienteVO.size(); i++) {
            cliente = convertirCliente(listaclienteVO.get(i));
            listacliente.add(i, cliente);
        }
        return listacliente;
    }

    /**
     * Metodo para convertir de Cliente a ClienteVO
     *
     * @param cliente
     * @return
     */
    public ClienteVO convertirClienteVO(Cliente cliente) {
        ClienteVO clienteVO = new ClienteVO();
        clienteVO.setDNIVO(cliente.getdni());
        clienteVO.setNombreVO(cliente.getNombre());
        clienteVO.setApellidosVO(cliente.getApellidos());
        clienteVO.setDireccionVO(cliente.getDireccion());
        clienteVO.setLocalidadVO(cliente.getLocalidad());
        clienteVO.setProvinciaVO(cliente.getProvincia());
        return clienteVO;
    }


    /**
     * Metodo para convertir una reservaVO a reserva
     *
     * @param reservaVO
     * @return
     */
    public Reserva convertirReserva(ReservaVO reservaVO) {
        Reserva reserva = new Reserva();
        reserva.setId(reservaVO.getIdVO());
        reserva.setFechaLlegada(reservaVO.getFechaLlegadaVO());
        reserva.setFechaFin(reservaVO.getFechaFinVO());
        reserva.setNumHabitacion(reservaVO.getNumHabitacionVO());
        reserva.settipoHabitacion(reservaVO.gettipoHabitacionVO());
        reserva.setFumador(reservaVO.getFumadorVO());
        reserva.setregimenAlojamiento(reservaVO.getRegAlojVO());
        reserva.setDNICliente(reservaVO.getDNICliente());
        return reserva;
    }

    /**
     * Metodo para convertir una lista de reservasVO a reservas
     *
     * @param listaReservaVO
     * @return
     */
    public ArrayList<Reserva> listaReserva(ArrayList<ReservaVO> listaReservaVO) {
        ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();
        Reserva reserva = new Reserva();
        for (int i = 0; i < listaReservaVO.size(); i++) {
            reserva = convertirReserva(listaReservaVO.get(i));
            listaReserva.add(i, reserva);
        }
        return listaReserva;
    }

    /**
     * Metodo para convertir de reserva a reservaVO
     *
     * @param reserva
     * @return
     */
    public ReservaVO convertirReservaVO(Reserva reserva) {
        ReservaVO reservaVO = new ReservaVO();
        reservaVO.setIdVO(reserva.getId());
        reservaVO.setFechaLlegadaVO(reserva.getFechaLlegada());
        reservaVO.setFechaFinVO(reserva.getFechaFin());
        reservaVO.setNumHabitacionVO(reserva.getNumHabitacion());
        reservaVO.settipoHabitacionVO(reserva.gettipoHabitacion());
        reservaVO.setFumadorVO(reserva.isFumador());
        reservaVO.setRegAlojVO(reserva.getregimenAlojamiento());
        reservaVO.setDNICliente(reserva.getDNICliente());
        return reservaVO;
    }
}
