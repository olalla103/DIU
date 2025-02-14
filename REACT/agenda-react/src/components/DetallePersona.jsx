import { useParams, Link } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../services/api";

const DetallePersona = () => {
    const { dni } = useParams(); // Obtener el parámetro de la URL
    const [persona, setPersona] = useState(null);

    useEffect(() => {
        api.get(`/person/${dni}`)
            .then(response => {
                setPersona(response.data);
            })
            .catch(error => {
                console.error("Error al obtener detalles:", error);
            });
    }, [dni]);

    if (!persona) {
        return <p>Cargando detalles...</p>;
    }

    return (
        <div className="container mt-4">
            <h2>Detalles de {persona.nombre} {persona.apellidos}</h2>
            <ul className="list-group">
                <li className="list-group-item"><b>DNI:</b> {persona.dni}</li>
                <li className="list-group-item"><b>Dirección:</b> {persona.calle}, {persona.ciudad}</li>
                <li className="list-group-item"><b>Código Postal:</b> {persona.codigoPostal}</li>
                <li className="list-group-item"><b>Cumpleaños:</b> {persona.cumpleanios}</li>
                <li className="list-group-item"><b>Tutoriales:</b> {persona.tutorialsIds ? persona.tutorialsIds.join(", ") : "Ninguno"}</li>
            </ul>
            <Link to="/personas" className="btn btn-primary mt-3">Volver a la lista</Link>
        </div>
    );
};

export default DetallePersona;
