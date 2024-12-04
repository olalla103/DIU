package com.example.gestionhotelolalla.modelo;

import com.example.gestionhotelolalla.modelo.repository.impl.regimenAlojamiento;
import com.example.gestionhotelolalla.modelo.repository.impl.tipoHabitacion;

import java.time.LocalDate;
import java.util.Date;

public class ReservaVO {
    private Integer idVO;
    private LocalDate fechaLlegadaVO;
    private LocalDate fechaFinVO;
    private Integer numHabitacionVO;
    private tipoHabitacion tipoHabitacionVO;
    private Boolean fumadorVO;
    private regimenAlojamiento regAlojVO;
    private String DNICliente;

    /**
     * Constructor de la reservaVO
     *
     * @param fechaLlegadaVO
     * @param fechaFinVO
     * @param numHabitacionVO
     * @param tipoHabitacionVO
     * @param fumadorVO
     * @param regAlojVO
     * @param DNICliente
     */
    public ReservaVO(LocalDate fechaLlegadaVO, LocalDate fechaFinVO, tipoHabitacion tipoHabitacionVO, Integer numHabitacionVO, Boolean fumadorVO, regimenAlojamiento regAlojVO, String DNICliente) {
        this.idVO = 0;
        this.fechaLlegadaVO = fechaLlegadaVO;
        this.fechaFinVO = fechaFinVO;
        this.numHabitacionVO = numHabitacionVO;
        this.tipoHabitacionVO = tipoHabitacionVO;
        this.fumadorVO = fumadorVO;
        this.regAlojVO = regAlojVO;
        this.DNICliente = DNICliente;
    }

    /**
     * Constructor de la reservaVO vacio
     */
    public ReservaVO() {
    }

    public String getDNICliente() {
        return DNICliente;
    }

    public void setDNICliente(String DNICliente) {
        this.DNICliente = DNICliente;
    }

    public Integer getIdVO() {
        return idVO;
    }

    public void setIdVO(Integer idVO) {
        this.idVO = idVO;
    }

    public LocalDate getFechaLlegadaVO() {
        return fechaLlegadaVO;
    }

    public void setFechaLlegadaVO(LocalDate fechaLlegadaVO) {
        this.fechaLlegadaVO = fechaLlegadaVO;
    }

    public LocalDate getFechaFinVO() {
        return fechaFinVO;
    }

    public void setFechaFinVO(LocalDate fechaFinVO) {
        this.fechaFinVO = fechaFinVO;
    }

    public Integer getNumHabitacionVO() {
        return numHabitacionVO;
    }

    public void setNumHabitacionVO(Integer numHabitacionVO) {
        this.numHabitacionVO = numHabitacionVO;
    }

    public tipoHabitacion gettipoHabitacionVO() {
        return tipoHabitacionVO;
    }

    public void settipoHabitacionVO(tipoHabitacion tipoHabitacionVO) {
        this.tipoHabitacionVO = tipoHabitacionVO;
    }

    public Boolean getFumadorVO() {
        return fumadorVO;
    }

    public void setFumadorVO(Boolean fumadorVO) {
        this.fumadorVO = fumadorVO;
    }

    public regimenAlojamiento getRegAlojVO() {
        return regAlojVO;
    }

    public void setRegAlojVO(regimenAlojamiento regAlojVO) {
        this.regAlojVO = regAlojVO;
    }
}

