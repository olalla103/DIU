import { useEffect, useState } from "react";
import PersonaDataService from "../services/api";
import TutorialsDataService from "../services/tutorialsapi";
import "../styles/GlassContacts.css";
import "bootstrap/dist/css/bootstrap.min.css";

function ListaPersonas() {
    const [personas, setPersonas] = useState([]);
    const [personaSeleccionada, setPersonaSeleccionada] = useState(null);
    const [datosFormulario, setDatosFormulario] = useState({});
    const [tutoriales, setTutoriales] = useState([]);

    useEffect(() => {
        PersonaDataService.getAll()
            .then(response => {
                setPersonas(response.data);
            })
            .catch(error => {
                console.error("Error al obtener personas:", error);
            });
    }, []);

    async function abrirModal(persona) {
        setPersonaSeleccionada(persona);
        setDatosFormulario(persona);

        // Obtener los tutoriales asociados a la persona
        try {
            const tutorialsResponse = await TutorialsDataService.getAll();
            const assignedTutorials = tutorialsResponse.data.filter(tutorial =>
                persona.tutorialsIds?.includes(tutorial.id)
            );
            setTutoriales(assignedTutorials);
        } catch (error) {
            console.error("Error al obtener tutoriales:", error);
        }
    }

    function cerrarModal() {
        setPersonaSeleccionada(null);
        setTutoriales([]);
    }

    function handleChange(e) {
        setDatosFormulario({ ...datosFormulario, [e.target.name]: e.target.value });
    }

    async function guardarCambios() {
        try {
            await PersonaDataService.update(datosFormulario.dni, datosFormulario);
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

            {/* 🖥️ Modales flotantes para detalles y tutoriales */}
            {personaSeleccionada && (
                <div className="modal-overlay">
                    <div className="modals-container">
                        
                        {/* 📄 Modal Izquierdo - Detalles de la persona */}
                        <div className="modal-content left">
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

                        {/* 🎓 Modal Derecho - Carrusel de Tutoriales */}
                        <div className="modal-content right">
                            <h2>Tutoriales</h2>
                            {tutoriales.length > 0 ? (
                                <div id="tutorialCarousel" className="carousel slide" data-bs-ride="carousel">
                                    <div className="carousel-inner">
                                        {tutoriales.map((tutorial, index) => (
                                            <div key={tutorial.id} className={`carousel-item ${index === 0 ? 'active' : ''}`}>
                                                <img 
                                                    src={tutorial.imageUrl?.startsWith("http") ? tutorial.imageUrl : "/img/default-tutorial.jpg"} 
                                                    className="d-block w-100 tutorial-img"
                                                    alt="Tutorial"
                                                    onError={(e) => e.target.src = "/img/default-tutorial.jpg"} // Si falla la carga, pone una imagen por defecto
                                                />
                                            </div>
                                        ))}
                                    </div>
                                    <button className="carousel-control-prev" type="button" data-bs-target="#tutorialCarousel" data-bs-slide="prev">
                                        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                                    </button>
                                    <button className="carousel-control-next" type="button" data-bs-target="#tutorialCarousel" data-bs-slide="next">
                                        <span className="carousel-control-next-icon" aria-hidden="true"></span>
                                    </button>
                                </div>
                            ) : (
                                <p>No tiene tutoriales asignados.</p>
                            )}
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default ListaPersonas;