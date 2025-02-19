import React, { useEffect, useState } from 'react'
import BolsosDataService from "../services/api.js"


function Bolsos() {

    const [bolsos,setBolsos] = useState([]);
    const [bolsoActual,setBolsoActual] = useState(null);

    const obtenerListaBolsos = () =>{
        BolsosDataService.getAll()
        .then(response =>{
            console.log(response.data);
            
            setBolsos(response.data);
        })
    };

    useEffect(()=>{
        obtenerListaBolsos();
    },[]);

    const setBolsoActivo = (bolso) =>{
        console.log(bolso);
        setBolsoActual(bolso);
    }

    const HTMLBolsos = bolsos.map((bolso) => {
        // renderizado de listas
        return (
        <li key={bolso.id} className="bolso-item" 
            onClick={()=>setBolsoActivo(bolso)}>
            <h2>{bolso.marca}</h2>
        </li>
        )
    });

  return (
    <div>
        {bolsoActual ? (
            <div>
                <h1>{bolsoActual.marca}</h1> 
                <h2>{bolsoActual.precio}</h2>
                <h3>{bolsoActual.estilo}</h3>
            </div>
        ):(
            <h1>No ha seleccionado ningún bolso</h1>
        )}
    <ul>
        {HTMLBolsos}
    </ul>
    </div>
  )
}

export default Bolsos