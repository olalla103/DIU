import { useEffect, useState } from "react";
import PersonaDataService from "../services/api"; // Importar el servicio centralizado
import "../styles/GlassContacts.css"; // Importar los estilos

function ListaPersonas() {
    const [personas, setPersonas] = useState([]);
    const [personaSeleccionada, setPersonaSeleccionada] = useState(null);
    const [datosFormulario, setDatosFormulario] = useState({});

    useEffect(() => {
        PersonaDataService.getAll()
            .then(response => {
                setPersonas(response.data);
            })
            .catch(error => {
                console.error("Error al obtener personas:", error);
            });
    }, []);

    function abrirModal(persona) {
        setPersonaSeleccionada(persona);
        setDatosFormulario(persona); // Cargar los datos en el formulario
    }

    function cerrarModal() {
        setPersonaSeleccionada(null);
    }

    function handleChange(e) {
        setDatosFormulario({ ...datosFormulario, [e.target.name]: e.target.value });
    }

    async function guardarCambios() {
        try {
            await PersonaDataService.update(datosFormulario.dni,datosFormulario);
            setPersonas(personas.map(persona => (persona.dni === datosFormulario.dni ? datosFormulario : persona)));
            
            cerrarModal();
        } catch (error) {
            console.error("Error al actualizar persona:", error);
        }
    }

    async function eliminarPersona() {
        const confirmacion = window.confirm("¿Estás seguro de eliminar esta persona?");
        if (confirmacion) {
            try {
                await PersonaDataService.delete(personaSeleccionada.dni);
                setPersonas(personas.filter(persona => persona.dni !== personaSeleccionada.dni));
                cerrarModal();
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

            {/* Modal flotante */}
            {personaSeleccionada && (
                <div className="modal-overlay">
                    <div className="modal-content">
                        <h2>Editar Persona</h2>
                        <input type="text" name="nombre" value={datosFormulario.nombre} onChange={handleChange} placeholder="Nombre" />
                        <input type="text" name="apellidos" value={datosFormulario.apellidos} onChange={handleChange} placeholder="Apellidos" />
                        <input type="text" name="ciudad" value={datosFormulario.ciudad} onChange={handleChange} placeholder="Ciudad" />
                        <input type="text" name="calle" value={datosFormulario.calle} onChange={handleChange} placeholder="Calle" />
                        <input type="number" name="codigoPostal" value={datosFormulario.codigoPostal} onChange={handleChange} placeholder="Código Postal" />
                        <input type="date" name="cumpleanios" value={datosFormulario.cumpleanios} onChange={handleChange} />

                        <div className="modal-buttons">
                            <button className="btn-save" onClick={guardarCambios}>Guardar</button>
                            <button className="btn-delete" onClick={eliminarPersona}>Eliminar</button>
                            <button className="btn-close" onClick={cerrarModal}>Cerrar</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default ListaPersonas;
