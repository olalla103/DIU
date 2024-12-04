package com.example.gestionhotelolalla.modelo.repository;

import java.util.ArrayList;

public interface GestionRepository {
    ArrayList<ClienteVO> ObtenerListaClienteVO() throws ExcepcionHotel;

    ArrayList<ReservaVO> ObtenerListaReservaVO(String dniC) throws ExcepcionHotel;

    ArrayList<ReservaVO> ObtenerListaReservaVO() throws ExcepcionHotel;

    ClienteVO busquedaClienteVO(String dniB) throws ExcepcionHotel;

    void addClienteVO(ClienteVO clienteVO) throws ExcepcionHotel;

    void addReservaVO(ReservaVO reservaVO) throws ExcepcionHotel;

    void deleteClienteVO(String dni) throws ExcepcionHotel;

    void deleteReservaVO(Integer id) throws ExcepcionHotel;

    void editClienteVO(ClienteVO clienteVO) throws ExcepcionHotel;

    void editReservaVO(ReservaVO reservaVO) throws ExcepcionHotel;

    int lastIdReservaVO() throws ExcepcionHotel;

    int DNIClienteVO() throws ExcepcionHotel;
}
