package org.example.monedas.modelo;

import Modelo.ExcepcionMoneda;
import Modelo.repository.MonedaRepository;

public class ConversorModelo {
    // ESTO ESTÁ INYECTADO
    private MonedaRepository m;// para inyectarlo usamos un método set

    public Double obtenerMultiplicador() throws ExcepcionMoneda {
        return (double) m.ObtenerListaMonedas().getFirst().getMultiplicador();
    }

    public Double conversor(Double euro) throws ExcepcionMoneda {
        return euro * obtenerMultiplicador();
    }

    public void setM(MonedaRepository m) {
        this.m = m;
    }
} // tengo el método guardado para futuras prácticas
