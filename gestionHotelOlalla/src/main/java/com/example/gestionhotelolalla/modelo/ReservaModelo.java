package com.example.gestionhotelolalla.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class ReservaModelo {
    GestionRepository gestionRepository;
    IntegerProperty numeroReservasDI = new SimpleIntegerProperty();

    public void setNumeroReservas(Integer nP) {
        this.numeroReservasDI.set(nP);
    }

    public void decNumeroReservas() {
        this.numeroReservasDI.set(numeroReservasDI.get() - 1);
    }

    public void incNumeroClientes() {
        this.numeroReservasDI.set(numeroReservasDI.get() + 1);
    }

    public IntegerProperty getNumeroReservasProperty() {
        return numeroReservasDI;
    }

    IntegerProperty numeroReservasD = new SimpleIntegerProperty();

    public void setNumeroReservas2(Integer nP) {
        this.numeroReservasD.set(nP);
    }

    public void decNumeroReservas2() {
        this.numeroReservasD.set(numeroReservasD.get() - 1);
    }

    public void incNumeroClientes2() {
        this.numeroReservasD.set(numeroReservasD.get() + 1);
    }

    public IntegerProperty getNumeroReservasProperty2() {
        return numeroReservasD;
    }

    IntegerProperty numeroReservasJS = new SimpleIntegerProperty();

    public void setNumeroReservas3(Integer nP) {
        this.numeroReservasJS.set(nP);
    }

    public void decNumeroReservas3() {
        this.numeroReservasJS.set(numeroReservasJS.get() - 1);
    }

    public void incNumeroClientes3() {
        this.numeroReservasJS.set(numeroReservasJS.get() + 1);
    }

    public IntegerProperty getNumeroReservasProperty3() {
        return numeroReservasJS;
    }

    IntegerProperty numeroReservasS = new SimpleIntegerProperty();

    public void setNumeroReservas4(Integer nP) {
        this.numeroReservasS.set(nP);
    }

    public void decNumeroReservas4() {
        this.numeroReservasS.set(numeroReservasS.get() - 1);
    }

    public void incNumeroClientes4() {
        this.numeroReservasS.set(numeroReservasS.get() + 1);
    }

    public IntegerProperty getNumeroReservasProperty4() {
        return numeroReservasS;
    }

    /**
     * Constructor de gestion modelo vacio
     */
    public ReservaModelo() {
    }


    // GESTION DE ClienteS

    /**
     * Metodo para listar ClientesVO de la gestionRepository
     *
     * @return
     * @throws ExcepcionHotel
     */
    public ArrayList<ClienteVO> listarClientes() throws ExcepcionHotel {
        return gestionRepository.ObtenerListaClienteVO();
    }

    /**
     * Metodo para crear la ClienteVO de la gestionRepository
     *
     * @param clienteVO
     * @throws ExcepcionHotel
     */
    public void crearClienteVO(ClienteVO clienteVO) throws ExcepcionHotel {
        gestionRepository.addClienteVO(clienteVO);
    }

    /**
     * Metodo para editar la ClienteVO de la gestionRepository
     *
     * @param ClienteVO
     * @throws ExcepcionHotel
     */
    public void editarClienteVO(ClienteVO ClienteVO) throws ExcepcionHotel {
        gestionRepository.editClienteVO(ClienteVO);
    }

    public void deleteClienteVO(ClienteVO ClienteVO) throws ExcepcionHotel {
        gestionRepository.deleteClienteVO(ClienteVO.getDNIVO());
    }

    /**
     * Metodo para buscar una Cliente por el DNI
     *
     * @param ClienteVO
     * @return
     * @throws ExcepcionHotel
     */
    public ClienteVO busquedaClienteDNI(ClienteVO ClienteVO) throws ExcepcionHotel {
        ClienteVO = gestionRepository.busquedaClienteVO(ClienteVO.getDNIVO());
        return ClienteVO;
    }

    // GESTION DE RESERVAS

    /**
     * Metodo para listar reservasVO de la gestionRepository
     *
     * @param dniC
     * @return
     * @throws ExcepcionHotel
     */
    public ArrayList<ReservaVO> listarReservas(String dniC) throws ExcepcionHotel {
        return gestionRepository.ObtenerListaReservaVO(dniC);
    }

    /**
     * Metodo para crear la reservaVO de la gestionRepository
     *
     * @param reservaVO
     * @throws ExcepcionHotel
     */
    public void crearReservaVO(ReservaVO reservaVO) throws ExcepcionHotel {
        gestionRepository.addReservaVO(reservaVO);
    }

    /**
     * Metodo para editar la reservaVO de la gestionRepository
     *
     * @param reservaVO
     * @throws ExcepcionHotel
     */
    public void editarReservaVO(ReservaVO reservaVO) throws ExcepcionHotel {
        gestionRepository.editReservaVO(reservaVO);
    }

    /**
     * Metodo para borrar la reservaVO de la gestionRepository
     *
     * @param reservaVO
     * @throws ExcepcionHotel
     */
    public void deleteReservaVO(ReservaVO reservaVO) throws ExcepcionHotel {
        gestionRepository.deleteReservaVO(reservaVO.getIdVO());
    }

    /**
     * Metodo para recoger el id de la reserva
     *
     * @return
     * @throws ExcepcionHotel
     */
    public int lastIdReserva() throws ExcepcionHotel {
        return gestionRepository.lastIdReservaVO();
    }

    public ArrayList<ReservaVO> listarReservasTodas() throws ExcepcionHotel {
        return gestionRepository.ObtenerListaReservaVO();
    }

    public void setImpl(GestionRepository inter) {
        this.gestionRepository = inter;
    }
}
