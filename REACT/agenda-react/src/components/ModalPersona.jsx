import { useEffect, useState } from "react";
import TutorialsDataService from "../services/tutorialsapi";
import "bootstrap/dist/css/bootstrap.min.css";
import PersonaDataService from "../services/api";


function ModalPersona({ persona, onClose, onSave, onDelete }) {
    const [datosFormulario, setDatosFormulario] = useState(persona);
    const [tutorialesDisponibles, setTutorialesDisponibles] = useState([]); // Lista de tutoriales
    const [tutorialesSeleccionados, setTutorialesSeleccionados] = useState([]); // Tutoriales asignados

    useEffect(() => {
        setDatosFormulario(persona);
        obtenerTutorialesDisponibles();
        setTutorialesSeleccionados(persona.tutorialsIds || []);
    }, [persona]);

    function handleChange(e) {
        setDatosFormulario({ ...datosFormulario, [e.target.name]: e.target.value });
    }

    async function obtenerTutorialesDisponibles() {
        try {
            const response = await TutorialsDataService.getAll();
            setTutorialesDisponibles(response.data);
        } catch (error) {
            console.error("Error al obtener tutoriales:", error);
        }
    }
    

    function handleTutorialChange(event) {
        const selectedTutorialId = event.target.value;

        setTutorialesSeleccionados((prev) => {
            if (prev.includes(selectedTutorialId)) {
                // Eliminar tutorial de la lista
                const nuevaLista = prev.filter(id => id !== selectedTutorialId);
                setDatosFormulario({ ...datosFormulario, tutorialsIds: nuevaLista });
                return nuevaLista;
            } else {
                // Agregar tutorial a la lista
                const nuevaLista = [...prev, selectedTutorialId];
                setDatosFormulario({ ...datosFormulario, tutorialsIds: nuevaLista });
                return nuevaLista;
            }
        });
    }

    function eliminarTutorial(id) {
        setTutorialesSeleccionados((prev) => {
            const nuevaLista = prev.filter(tutorialId => tutorialId !== id);
            setDatosFormulario({ ...datosFormulario, tutorialsIds: nuevaLista });
    
            // Llamar a guardarCambios con la lista actualizada después de que React procese el estado
            setTimeout(() => {
                guardarCambios([...nuevaLista]); // Asegurar que se envía un array y no un evento
            }, 100);
    
            return nuevaLista;
        });
    }
    
    

    async function guardarCambios(nuevaListaTutoriales) {
        try {
            // Si no se pasa una lista, usa la actual del estado
            const listaTutoriales = Array.isArray(nuevaListaTutoriales) ? nuevaListaTutoriales : tutorialesSeleccionados;
    
            const datosActualizados = {
                ...datosFormulario,
                tutorialsIds: listaTutoriales // Asegurar que es un array
            };
    
            console.log("Enviando POST al backend con datos:", JSON.parse(JSON.stringify(datosActualizados))); // 📌 DEBUG
    
            const response = await PersonaDataService.create(datosActualizados);
            console.log("Respuesta del backend:", response.data); // DEBUG
    
            onSave(datosActualizados);
            onClose();
        } catch (error) {
            console.error("Error al actualizar persona con POST:", error);
        }
    }
     
    
    

    return (
        <div className="modal-overlay">
            <div className="modals-container">
                
                {/* Modal Izquierdo - Datos de la persona + Spinner para asignar/eliminar tutoriales */}
                <div className="modal-content left">
                    <h2>Editar Persona</h2>
                    <input type="text" name="nombre" value={datosFormulario.nombre} onChange={handleChange} placeholder="Nombre" />
                    <input type="text" name="apellidos" value={datosFormulario.apellidos} onChange={handleChange} placeholder="Apellidos" />
                    <input type="text" name="ciudad" value={datosFormulario.ciudad} onChange={handleChange} placeholder="Ciudad" />
                    <input type="text" name="calle" value={datosFormulario.calle} onChange={handleChange} placeholder="Calle" />
                    <input type="number" name="codigoPostal" value={datosFormulario.codigoPostal} onChange={handleChange} placeholder="Código Postal" />
                    <input type="date" name="cumpleanios" value={datosFormulario.cumpleanios} onChange={handleChange} />

                    {/* Spinner para añadir/eliminar tutoriales */}
                    <div className="form-group mt-3">
                        <label>Seleccionar/Eliminar Tutorial:</label>
                        <select 
                            className="form-control" 
                            value=""  // Establecemos el valor como vacío para evitar selección fija
                            onChange={handleTutorialChange}
                        >
                            <option disabled value="">-- Seleccionar --</option>
                            {tutorialesDisponibles.map(tutorial => (
                                <option key={tutorial.id} value={tutorial.id}>
                                    {tutorialesSeleccionados.includes(tutorial.id) ? `${tutorial.title} (Eliminar)` : `${tutorial.title} (Añadir)`}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="modal-buttons">
                        <button className="btn-save" onClick={guardarCambios}>Guardar</button>
                        <button className="btn-delete" onClick={() => onDelete(persona.dni)}>Eliminar</button>
                        <button className="btn-close" onClick={onClose}>Cerrar</button>
                    </div>
                </div>

                {/* Modal Derecho - Carrusel de Tutoriales Seleccionados */}
                <div className="modal-content right">
                    <h2>Tutoriales</h2>
                    {tutorialesSeleccionados.length > 0 ? (
                        <div id="tutorialCarousel" className="carousel slide" data-bs-ride="carousel">
                            <div className="carousel-inner">
                                {tutorialesSeleccionados.map((id, index) => {
                                    const tutorial = tutorialesDisponibles.find(t => t.id === id);
                                    return tutorial ? (
                                        <div key={id} className={`carousel-item ${index === 0 ? 'active' : ''}`}>
                                            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
                                                <div>
                                                <h5>{tutorial.title}</h5>
                                                </div>
                            
                                                <div>
                                                {/* Botón para eliminar tutorial */}
                                                <button className="btn btn-danger" onClick={() => eliminarTutorial(id)}>Eliminar Tutorial</button>
                                                </div>
                                            </div>
                                            <br />
                                            <img 
                                                src={tutorial.imageURL?.startsWith("http") ? tutorial.imageURL : "/img/default-tutorial.jpg"} 
                                                className="d-block w-100 tutorial-img"
                                                alt={tutorial.title}
                                                onError={(e) => { e.target.src = "/img/default-tutorial.jpg"; }} 
                                            />
                                        </div>
                                    ) : null;
                                })}
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
    );
}

export default ModalPersona;