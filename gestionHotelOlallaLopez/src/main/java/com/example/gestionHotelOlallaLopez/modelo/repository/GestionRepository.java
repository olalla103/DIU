package com.example.gestionHotelOlallaLopez.modelo.repository;

import com.example.gestionHotelOlallaLopez.modelo.ClienteVO;
import com.example.gestionHotelOlallaLopez.modelo.ExcepcionHotel;
import com.example.gestionHotelOlallaLopez.modelo.ReservaVO;

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


