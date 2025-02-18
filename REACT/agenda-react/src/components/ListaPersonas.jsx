import { useEffect, useState } from "react";
import axios from "axios";
import "../styles/GlassContacts.css"; // Importar los estilos

function ListaPersonas() {
    const [personas, setPersonas] = useState([]);
    const [personaSeleccionada, setPersonaSeleccionada] = useState(null);
    const [formData, setFormData] = useState({});

    useEffect(() => {
        axios.get("http://localhost:8090/api/v1/person")
            .then(response => {
                setPersonas(response.data);
            })
            .catch(error => {
                console.error("Error al obtener personas:", error);
            });
    }, []);

    function abrirModal(persona) {
        setPersonaSeleccionada(persona);
        setFormData(persona); // Cargar los datos en el formulario
    }

    function cerrarModal() {
        setPersonaSeleccionada(null);
    }

    function handleChange(e) {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    }

    async function guardarCambios() {
        try {
            await axios.put(`http://localhost:8090/api/v1/person/${formData.dni}`, formData);
            setPersonas(personas.map(p => (p.dni === formData.dni ? formData : p)));
            cerrarModal();
        } catch (error) {
            console.error("Error al actualizar persona:", error);
        }
    }

    async function eliminarPersona() {
        const confirmacion = window.confirm("¿Estás seguro de eliminar esta persona?");
        if (confirmacion) {
            try {
                await axios.delete(`http://localhost:8090/api/v1/person/${personaSeleccionada.dni}`);
                setPersonas(personas.filter(p => p.dni !== personaSeleccionada.dni));
                cerrarModal();
            } catch (error) {
                console.error("Error al eliminar persona:", error);
            }
        }
    }

    return (
        <div className="container mt-5">
            <h2 className="text-center">Lista de Personas</h2>
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
                    <p className="text-center">⚠️ No hay personas registradas.</p>
                )}
            </div>

            {/* Modal flotante */}
            {personaSeleccionada && (
                <div className="modal-overlay">
                    <div className="modal-content">
                        <h2>Editar Persona</h2>
                        <input type="text" name="nombre" value={formData.nombre} onChange={handleChange} placeholder="Nombre" />
                        <input type="text" name="apellidos" value={formData.apellidos} onChange={handleChange} placeholder="Apellidos" />
                        <input type="text" name="ciudad" value={formData.ciudad} onChange={handleChange} placeholder="Ciudad" />
                        <input type="text" name="calle" value={formData.calle} onChange={handleChange} placeholder="Calle" />
                        <input type="number" name="codigoPostal" value={formData.codigoPostal} onChange={handleChange} placeholder="Código Postal" />
                        <input type="date" name="cumpleanios" value={formData.cumpleanios} onChange={handleChange} />

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
