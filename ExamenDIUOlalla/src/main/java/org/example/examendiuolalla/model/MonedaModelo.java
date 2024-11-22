package org.example.examendiuolalla.model;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import org.example.examendiuolalla.model.util.MonedaUtil;
import org.example.examendiuolalla.view.Moneda;

import java.util.ArrayList;

public class MonedaModelo {
    static MonedaRepository monedaRepository;

    public void setMonedaRepository(MonedaRepository MonedaRepository) {
        monedaRepository = MonedaRepository;
    }

    // obtiene la lista de monedas
    ArrayList<MonedaVO> ObtenerListaMonedas() throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaMonedas = monedaRepository.ObtenerListaMonedas();
        return listaMonedas;
    }

    public static ArrayList<MonedaVO> obtenerMonedas() throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaMonedas = monedaRepository.ObtenerListaMonedas();
        return listaMonedas;
    }

    public ArrayList<Moneda> mostrarMonedas() throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaMonedasVO = obtenerMonedas();
        ArrayList<Moneda> listaPersonas = new ArrayList<>();
        listaPersonas = MonedaUtil.getMoneda(listaMonedasVO);
        return listaPersonas;
    }


    // elimina Moneda
    public void deleteMoneda(Integer var1) throws ExcepcionMoneda {
        monedaRepository.deleteMoneda(var1);
    }

}
