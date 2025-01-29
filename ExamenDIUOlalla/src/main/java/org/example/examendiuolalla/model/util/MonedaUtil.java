package org.example.examendiuolalla.model.util;

import Modelo.MonedaVO;
import org.example.examendiuolalla.view.Moneda;

import java.util.ArrayList;

public class MonedaUtil {


    public static ArrayList<MonedaVO> getMonedaVO(ArrayList<Moneda> monedas) {
        ArrayList<MonedaVO> MonedaVOs = new ArrayList<>();
        for (Moneda moneda : monedas) {
            MonedaVOs.add(new MonedaVO(moneda.getNombre(), moneda.getMultiplicador(), moneda.getCodigo()));
        }
        return MonedaVOs;
    }

    public static ArrayList<Moneda> getMoneda(ArrayList<MonedaVO> monedaVOs) {
        ArrayList<Moneda> monedas = new ArrayList<>();
        for (MonedaVO monedaVO : monedaVOs) {
            monedas.add(new Moneda(monedaVO.getNombre(), monedaVO.getMultiplicador(), monedaVO.getCodigo()));
        }
        return monedas;
    }

    public static Moneda getMoneda(MonedaVO monedaVO) {
        Moneda moneda = new Moneda();
        moneda.setCodigo(monedaVO.getCodigo());
        moneda.setMultiplicador(monedaVO.getMultiplicador());
        moneda.setNombre(monedaVO.getNombre());

        return moneda;
    }

   /* public static MonedaVO getMonedaVO(Moneda moneda) {
        MonedaVO monedaVO = new MonedaVO();
        monedaVO.setCodigo(moneda.getCodigo());
        monedaVO.setMultiplicador(moneda.getMultiplicador());
        monedaVO.setNombre(moneda.getNombre());

        return monedaVO;
    }*/
}

