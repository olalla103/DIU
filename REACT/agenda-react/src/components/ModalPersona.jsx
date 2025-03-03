import { useEffect, useState } from "react";
import { useUsuarios } from "../context/UsuariosContext"; // ✅ Importa el contexto de usuario
import TutorialsDataService from "../services/tutorialsapi";
import "bootstrap/dist/css/bootstrap.min.css";

function ModalPersona({ persona, onClose, onSave, onDelete }) {
    const [datosFormulario, setDatosFormulario] = useState(persona);
    const [tutoriales, setTutoriales] = useState([]);
    const { userInfo } = useUsuarios(); // ✅ Obtener información del usuario

    useEffect(() => {
        setDatosFormulario(persona);
        obtenerTutoriales(persona.tutorialsIds || []);
    }, [persona]);

    function handleChange(e) {
        setDatosFormulario({ ...datosFormulario, [e.target.name]: e.target.value });
    }

    async function obtenerTutoriales(ids) {
        try {
            const responses = await Promise.all(ids.map(id => TutorialsDataService.get(id)));
            setTutoriales(responses.map(res => res.data));
        } catch (error) {
            console.error("Error al obtener tutoriales:", error);
        }
    }

    return (
        <div className="modal-overlay">
            <div className="modals-container">
                
                {/* 📄 Modal Izquierdo - Detalles de la persona */}
                <div className="modal-content left">
                    <h2>{userInfo.name == "anonimo" ? "Detalles de Persona" : "Editar Persona"}</h2>
                    <input type="text" name="nombre" value={datosFormulario.nombre} onChange={handleChange} placeholder="Nombre" disabled={userInfo.name == "anonimo"} />
                    <input type="text" name="apellidos" value={datosFormulario.apellidos} onChange={handleChange} placeholder="Apellidos" disabled={userInfo.name == "anonimo"} />
                    <input type="text" name="ciudad" value={datosFormulario.ciudad} onChange={handleChange} placeholder="Ciudad" disabled={userInfo.name == "anonimo"} />
                    <input type="text" name="calle" value={datosFormulario.calle} onChange={handleChange} placeholder="Calle" disabled={userInfo.name == "anonimo"} />
                    <input type="number" name="codigoPostal" value={datosFormulario.codigoPostal} onChange={handleChange} placeholder="Código Postal" disabled={userInfo.name == "anonimo"} />
                    <input type="date" name="cumpleanios" value={datosFormulario.cumpleanios} onChange={handleChange} disabled={userInfo.name == "anonimo"} />

                    <div className="modal-buttons">
                        {userInfo.name != "anonimo" && <button className="btn-save" onClick={() => onSave(datosFormulario)}>Guardar</button>}
                        {userInfo.name != "anonimo"&& <button className="btn-delete" onClick={() => onDelete(persona.dni)}>Eliminar</button>}
                        <button className="btn-close" onClick={onClose}>Cerrar</button>
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
                                        <div>
                                            <h5>{tutorial.title}</h5>
                                        </div>
                                        <img 
                                            src={tutorial.imageURL?.startsWith("http") ? tutorial.imageURL : "/img/default-tutorial.jpg"} 
                                            className="d-block w-100 tutorial-img"
                                            alt="Tutorial"
                                            onError={(e) => { e.target.src = "/img/default-tutorial.jpg"; }} 
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
    );
}

export default ModalPersona;
