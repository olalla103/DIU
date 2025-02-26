import { useParams, Link } from "react-router-dom";
import { useEffect, useState } from "react";
import PersonaDataService from "../services/api";
import TutorialsDataService from "../services/tutorialsapi"; // Servicio para obtener tutoriales
import "../styles/DetallePersona.css"; // Estilos CSS

function DetallePersona() {
    const { dni } = useParams(); 
    const [persona, setPersona] = useState(null);
    const [tutoriales, setTutoriales] = useState([]);

    useEffect(() => {
        async function fetchData() {
            try {
                const response = await PersonaDataService.get(dni);
                setPersona(response.data);

                // Obtener tutoriales de la persona si existen
                if (response.data.tutorialsIds) {
                    const tutorialsResponse = await TutorialsDataService.getAll();
                    // Filtrar los tutoriales asociados a esta persona
                    const assignedTutorials = tutorialsResponse.data.filter(tutorial => 
                        response.data.tutorialsIds.includes(tutorial.id)
                    );
                    setTutoriales(assignedTutorials);
                }
            } catch (error) {
                console.error("Error al obtener detalles:", error);
            }
        }
        fetchData();
    }, [dni]);

    if (!persona) {
        return <p>Cargando detalles...</p>;
    }

    return (
        <div className="detalle-container">
            {/* 📄 Panel Izquierdo - Datos de la persona */}
            <div className="persona-info">
                <h2>{persona.nombre} {persona.apellidos}</h2>
                <ul className="list-group">
                    <li className="list-group-item"><b>DNI:</b> {persona.dni}</li>
                    <li className="list-group-item"><b>Dirección:</b> {persona.calle}, {persona.ciudad}</li>
                    <li className="list-group-item"><b>Código Postal:</b> {persona.codigoPostal}</li>
                    <li className="list-group-item"><b>Cumpleaños:</b> {persona.cumpleanios}</li>
                </ul>
                <Link to="/personas" className="btn btn-primary mt-3">Volver a la lista</Link>
            </div>

            {/* 🎓 Panel Derecho - Tutoriales Asociados */}
            <div className="tutoriales-info">
                <h3>Tutoriales de {persona.nombre}</h3>
                {tutoriales.length > 0 ? (
                    <div className="tutoriales-grid">
                        {tutoriales.map((tutorial) => (
                            <div key={tutorial.id} className="tutorial-card">
                                <img src={tutorial.imageUrl || "/img/default-tutorial.jpg"} alt={tutorial.title} />
                                <p><b>{tutorial.title}</b></p>
                            </div>
                        ))}
                    </div>
                ) : (
                    <p>No tiene tutoriales asignados.</p>
                )}
            </div>
        </div>
    );
}

export default DetallePersona;
