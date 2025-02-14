import { useEffect, useState } from "react";
import api from "../services/api";
import "../styles/GlassContacts.css"; // Asegúrate de que el CSS está bien referenciado
import "bootstrap/dist/css/bootstrap.min.css";

const ListaPersonas = () => {
    const [personas, setPersonas] = useState([]);
    const [contactoSeleccionado, setContactoSeleccionado] = useState(null); // Estado para el contacto seleccionado

    useEffect(() => {
        api.get("/person")
            .then(response => {
                console.log("Datos recibidos desde la API:", response.data);
                if (Array.isArray(response.data)) {
                    setPersonas(response.data);
                } else if (Array.isArray(response.data.data)) {
                    setPersonas(response.data.data);
                } else {
                    console.warn("⚠️ Respuesta inesperada en la API:", response.data);
                    setPersonas([]);
                }
            })
            .catch(error => {
                console.error("Error al obtener personas:", error);
                setPersonas([]);
            });
    }, []);

    return (
        <div className="container mt-5">
            <div className="row">
                {personas.length > 0 ? (
                    personas.map(person => (
                        <div key={person.dni} className="col-md-4">
                            <div className="glass-card" onClick={() => setContactoSeleccionado(person)}>
                                <h4>{person.nombre} {person.apellidos}</h4>
                                <p><strong>Ciudad:</strong> {person.ciudad}</p>
                                <p><strong>DNI:</strong> {person.dni}</p>
                            </div>
                        </div>
                    ))
                ) : (
                    <p className="text-center">⚠️ No hay personas registradas.</p>
                )}
            </div>

            {/* Modal flotante con la información del contacto */}
            {contactoSeleccionado && (
                <div className="modal-overlay">
                    <div className="modal-box">
                        <h2>{contactoSeleccionado.nombre} {contactoSeleccionado.apellidos}</h2>
                        <p><strong>DNI:</strong> {contactoSeleccionado.dni}</p>
                        <p><strong>Dirección:</strong> {contactoSeleccionado.calle}, {contactoSeleccionado.ciudad}</p>
                        <p><strong>Código Postal:</strong> {contactoSeleccionado.codigoPostal}</p>
                        <p><strong>Cumpleaños:</strong> {contactoSeleccionado.cumpleanios}</p>
                        <button className="btn btn-secondary mt-3" onClick={() => setContactoSeleccionado(null)}>
                            Cerrar
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default ListaPersonas;
