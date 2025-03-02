import { useState, useEffect } from "react";
import PersonaDataService from "../services/api"; // Servicio para personas
import TutorialDataService from "../services/tutorialsapi"; // Servicio para tutoriales
import "../styles/AniadirPersona.css"; // Importar estilos glass
import { usePersonas } from "../context/PersonasContext";

function AniadirPersona() {
    const { cantidadPersonas } = usePersonas(); // Obtener la cantidad de personas del contexto
    const MAX_PERSONAS = 5; // Definir el máximo de personas

    const [formData, setFormData] = useState({
        dni: "",
        nombre: "",
        apellidos: "",
        calle: "",
        codigoPostal: "",
        ciudad: "",
        cumpleanios: "",
        tutorialsIds: [], // Lista de IDs de tutoriales
    });

    const [mensaje, setMensaje] = useState("");
    const [tutoriales, setTutoriales] = useState([]); // Lista de tutoriales disponibles
    const [cargando, setCargando] = useState(false); // Estado del spinner
    const [tutorialesSeleccionados, setTutorialesSeleccionados] = useState([]); // Lista de nombres de tutoriales seleccionados

    useEffect(() => {
        // Cargar tutoriales disponibles
        TutorialDataService.getAll()
            .then(response => setTutoriales(response.data))
            .catch(error => console.error("Error al obtener tutoriales:", error));
    }, []);

    function handleChange(e) {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    }

    function handleTutorialChange(e) {
        const selectedOptions = Array.from(e.target.selectedOptions, option => ({
            id: option.value,
            titulo: option.textContent
        }));

        setFormData({
            ...formData,
            tutorialsIds: selectedOptions.map(option => option.id)
        });

        setTutorialesSeleccionados(selectedOptions.map(option => option.titulo)); // Guardar los nombres de los tutoriales seleccionados
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setCargando(true); // Mostrar spinner

        try {
            const response = await PersonaDataService.create(formData);
            if (response.status === 201 || response.status === 200) {
                setMensaje("✅ Persona añadida con éxito.");
                setFormData({
                    dni: "",
                    nombre: "",
                    apellidos: "",
                    calle: "",
                    codigoPostal: "",
                    ciudad: "",
                    cumpleanios: "",
                    tutorialsIds: [],
                });
                setTutorialesSeleccionados([]); // Limpiar la lista de tutoriales seleccionados
            }
        } catch (error) {
            setMensaje("Error al añadir persona.");
            console.error("Error al enviar datos:", error);
        }

        setCargando(false); // Ocultar spinner
    }

    return (
        <div className="aniadir-persona-container">
            <h2 className="text-center">Añadir Persona</h2>
            {mensaje && <div className="alert alert-info text-center">{mensaje}</div>}

            <form onSubmit={handleSubmit}>
                <input type="text" name="dni" placeholder="DNI" value={formData.dni} onChange={handleChange} required />
                <input type="text" name="nombre" placeholder="Nombre" value={formData.nombre} onChange={handleChange} required />
                <input type="text" name="apellidos" placeholder="Apellidos" value={formData.apellidos} onChange={handleChange} required />
                <input type="text" name="calle" placeholder="Calle" value={formData.calle} onChange={handleChange} required />
                <input type="number" name="codigoPostal" placeholder="Código Postal" value={formData.codigoPostal} onChange={handleChange} required />
                <input type="text" name="ciudad" placeholder="Ciudad" value={formData.ciudad} onChange={handleChange} required />
                <input type="date" name="cumpleanios" value={formData.cumpleanios} onChange={handleChange} required />

                {/* Selector de tutoriales */}
                <label>Seleccionar Tutoriales:</label>
                <select multiple name="tutorialsIds" value={formData.tutorialsIds} onChange={handleTutorialChange} className="select-tutoriales">
                    {tutoriales.map(tutorial => (
                        <option key={tutorial.id} value={tutorial.id}>
                            {tutorial.title}
                        </option>
                    ))}
                </select>

                {/* Spinner de carga con nombres de tutoriales */}
                {cargando && (
                    <div className="spinner-container">
                        <div className="spinner"></div>
                        <p>Guardando {tutorialesSeleccionados.join(", ")}...</p>
                    </div>
                )}

                <button type="submit" disabled={cargando || cantidadPersonas >= MAX_PERSONAS}>
                    {cargando ? "Guardando..." : "Agregar Persona"}
                </button>
            </form>
        </div>
    );
}

export default AniadirPersona;