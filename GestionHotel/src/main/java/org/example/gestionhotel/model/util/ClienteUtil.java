package org.example.gestionhotel.model.util;

import javafx.beans.property.SimpleStringProperty;
import org.example.gestionhotel.model.ClienteVO;
import org.example.gestionhotel.view.Cliente;

import java.util.ArrayList;

public class ClienteUtil {

    // Convierto de Cliente a ClienteVO
    public static ArrayList<ClienteVO> getClienteVO(ArrayList<Cliente> clientes) {
        ArrayList<ClienteVO> clientesVOs = new ArrayList<>();
        for (Cliente cliente : clientes) { // Cambié clientesVOs por clientes
            // Convertir Cliente a ClienteVO
            clientesVOs.add(new ClienteVO(
                    cliente.getDni().get(),
                    cliente.getNombre().get(),
                    cliente.getApellidos().get(),
                    cliente.getDireccion().get(),
                    cliente.getLocalidad().get(),
                    cliente.getProvincia().get()
            ));
        }
        return clientesVOs;
    }

    // Convierto de ClienteVO a Cliente
    public static ArrayList<Cliente> getCliente(ArrayList<ClienteVO> clienteVOS) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (ClienteVO clienteVO : clienteVOS) {

            clientes.add(new Cliente(
                    clienteVO.getDni(),
                    clienteVO.getNombre(),
                    clienteVO.getApellidos(),
                    clienteVO.getDireccion(),
                    clienteVO.getLocalidad(),
                    clienteVO.getProvincia()
            ));
        }
        return clientes;
    }

    public static Cliente getCliente(ClienteVO clienteVO) {
        Cliente cliente = new Cliente();
        // Envolvemos los valores de String en StringProperty
        cliente.setDni(new SimpleStringProperty(clienteVO.getDni()));
        cliente.setNombre(new SimpleStringProperty(clienteVO.getNombre()));
        cliente.setApellidos(new SimpleStringProperty(clienteVO.getApellidos()));
        cliente.setDireccion(new SimpleStringProperty(clienteVO.getDireccion()));
        cliente.setLocalidad(new SimpleStringProperty(clienteVO.getLocalidad()));
        cliente.setProvincia(new SimpleStringProperty(clienteVO.getProvincia()));
        return cliente;
    }

    public static ClienteVO getClienteVO(Cliente cliente) {
        ClienteVO clienteVO = new ClienteVO();
        clienteVO.setDni(cliente.getDni().get());
        clienteVO.setNombre(cliente.getNombre().get());
        clienteVO.setApellidos(cliente.getApellidos().get());
        clienteVO.setDireccion(cliente.getDireccion().get());
        clienteVO.setLocalidad(cliente.getLocalidad().get());
        clienteVO.setProvincia(cliente.getProvincia().get());
        return clienteVO;
    }


}
