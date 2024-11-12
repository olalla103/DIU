package org.example.gestionhotel.model.repository;

import org.example.gestionhotel.model.util.ClienteVO;

import java.util.ArrayList;

public interface ClienteRepository {
    // Obtener lista clientes
    ArrayList<ClienteVO> ObtenerListaClientes() throws ExcepcionHotel;

    // añadir cliente
    public void aniadirCliente(ClienteVO cliente) throws ExcepcionHotel;

    // eliminar cliente
    public void eliminarCliente(String dni) throws ExcepcionHotel;

    // editar cliente
    public void editCliente(ClienteVO clienteVO) throws ExcepcionHotel;

    // obtener último cliente
    public int ultimoCliente() throws ExcepcionHotel;
}
