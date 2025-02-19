import { useState } from "react";
import PersonaDataService from "../services/api"; // Importa el servicio centralizado
import "../styles/AniadirPersona.css"; // Importa los estilos glass

function AniadirPersona() {
    const [formData, setFormData] = useState({
        dni: "",
        nombre: "",
        apellidos: "",
        calle: "",
        codigoPostal: "",
        ciudad: "",
        cumpleanios: "",
    });

    const [mensaje, setMensaje] = useState("");

    function handleChange(e) {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    }

    async function handleSubmit(e) {
        e.preventDefault();
        try {
            const response = await PersonaDataService.create(formData);
            if (response.status === 201 || response.status === 200) {
                setMensaje("Persona añadida con éxito.");
                setFormData({
                    dni: "",
                    nombre: "",
                    apellidos: "",
                    calle: "",
                    codigoPostal: "",
                    ciudad: "",
                    cumpleanios: "",
                });
            }
        } catch (error) {
            setMensaje("Error al añadir persona.");
            console.error("Error al enviar datos:", error);
        }
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
                
                <button type="submit">Agregar Persona</button>
            </form>
        </div>
    );
}

export default AniadirPersona;
