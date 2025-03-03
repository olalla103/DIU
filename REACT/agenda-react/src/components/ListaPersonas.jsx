import { useEffect, useState } from "react";
import PersonaDataService from "../services/api";
import "../styles/GlassContacts.css";
import "bootstrap/dist/css/bootstrap.min.css";
import ModalPersona from "./ModalPersona"; // Importa el nuevo componente

function ListaPersonas() {
    const [personas, setPersonas] = useState([]);
    const [personaSeleccionada, setPersonaSeleccionada] = useState(null);

    useEffect(() => {
        PersonaDataService.getAll()
            .then(response => setPersonas(response.data))
            .catch(error => console.error("Error al obtener personas:", error));
    }, []);

    function abrirModal(persona) {
        setPersonaSeleccionada(persona);
    }

    function cerrarModal() {
        setPersonaSeleccionada(null);
    }

    async function guardarCambios(datosFormulario) {
        try {
            await PersonaDataService.update(datosFormulario.dni, datosFormulario);
            setPersonas(personas.map(persona => (persona.dni === datosFormulario.dni ? datosFormulario : persona)));
            cerrarModal();
        } catch (error) {
            console.error("Error al actualizar persona:", error);
        }
    }

    async function eliminarPersona(dni) {
        if (window.confirm("¿Estás seguro de eliminar esta persona?")) {
            try {
                const response = await PersonaDataService.delete(dni);
    
                if (response.status === 200 || response.status === 204) {
                    setPersonas(prevPersonas => prevPersonas.filter(persona => persona.dni !== dni));
                    console.log("Persona eliminada correctamente.");
                } else {
                    console.error("Error: No se eliminó correctamente.");
                }
            } catch (error) {
                console.error("Error al eliminar persona:", error);
            }
        }
    }
    
    
    

    return (
        <div className="container mt-5">
            <div className="row">
                {personas.length > 0 ? (
                    personas.map(person => (
                        <div key={person.dni} className="col-md-4">
                            <div className="glass-card" onClick={() => abrirModal(person)}>
                                <h4>{person.nombre} {person.apellidos}</h4>
                                <p><strong>Ciudad:</strong> {person.ciudad}</p>
                                <p><strong>DNI:</strong> {person.dni}</p>
                            </div>
                        </div>
                    ))
                ) : (
                    <p className="text-center">No hay personas registradas.</p>
                )}
            </div>

            {/* Renderizar modal si hay una persona seleccionada */}
            {personaSeleccionada && (
                <ModalPersona
                    persona={personaSeleccionada}
                    onClose={cerrarModal}
                    onSave={guardarCambios}
                    onDelete={eliminarPersona}
                />
            )}
        </div>
    );
}

export default ListaPersonas;