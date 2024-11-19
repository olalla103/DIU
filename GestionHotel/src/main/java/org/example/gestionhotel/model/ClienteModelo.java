package org.example.gestionhotel.model;

import org.example.gestionhotel.model.repository.ClienteRepository;
import org.example.gestionhotel.model.repository.ExcepcionHotel;
import org.example.gestionhotel.model.util.ClienteUtil;
import org.example.gestionhotel.view.Cliente;

import java.util.ArrayList;

public class ClienteModelo {

    static ClienteRepository clienteRepository;

    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public static ArrayList<ClienteVO> obtenerClientes() {
        ArrayList<ClienteVO> listaClientes = clienteRepository.ObtenerListaClientes();
        return listaClientes;
    }

    public ArrayList<Cliente> mostrarClientes() {
        ArrayList<ClienteVO> listaClientesVO = obtenerClientes();
        return ClienteUtil.getCliente(listaClientesVO);
    }
    //ArrayList<Cliente> listaClientes = ClienteUtil.getCliente(listaClientesVO);


    public void insertarCliente(Cliente cliente) throws ExcepcionHotel {
        ClienteVO clienteVO = ClienteUtil.getClienteVO(cliente);
        clienteRepository.aniadirCliente(clienteVO);
    }

    public void eliminarCliente(String dni) throws ExcepcionHotel {
        clienteRepository.eliminarCliente(dni);
    }

    public void modificarCliente(Cliente cliente) throws ExcepcionHotel {
        ClienteVO clienteVO = ClienteUtil.getClienteVO(cliente);
        clienteRepository.editCliente(clienteVO);
    }

}
