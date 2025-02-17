import { useState } from "react";
import axios from "axios";

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
            const response = await axios.post("http://localhost:8090/api/v1/person", formData);
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
                });
            }
        } catch (error) {
            setMensaje("Error al añadir persona.");
            console.error("Error al enviar datos:", error);
        }
    }

    return (
        <div className="container mt-4">
            <h2 className="text-center">Añadir Persona</h2>
            {mensaje && <div className="alert alert-info text-center">{mensaje}</div>}
            <form onSubmit={handleSubmit} className="p-4 border rounded bg-light">
                <div className="row mb-3">
                    <div className="col-md-6">
                        <label className="form-label">DNI</label>
                        <input
                            placeholder="Introduzca su DNI"
                            type="text"
                            className="form-control"
                            name="dni"
                            value={formData.dni}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="col-md-6">
                        <label className="form-label">Nombre</label>
                        <input
                            placeholder="Introduzca su nombre"
                            type="text"
                            className="form-control"
                            name="nombre"
                            value={formData.nombre}
                            onChange={handleChange}
                            required
                        />
                    </div>
                </div>

                <div className="row mb-3">
                    <div className="col-md-6">
                        <label className="form-label">Apellidos</label>
                        <input
                            placeholder="Introduzca sus apellidos"
                            type="text"
                            className="form-control"
                            name="apellidos"
                            value={formData.apellidos}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="col-md-6">
                        <label className="form-label">Calle</label>
                        <input
                            placeholder="Introduzca su calle"
                            type="text"
                            className="form-control"
                            name="calle"
                            value={formData.calle}
                            onChange={handleChange}
                            required
                        />
                    </div>
                </div>

                <div className="row mb-3">
                    <div className="col-md-6">
                        <label className="form-label">Código Postal</label>
                        <input
                            placeholder="Introduzca su código postal"
                            type="number"
                            className="form-control"
                            name="codigoPostal"
                            value={formData.codigoPostal}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="col-md-6">
                        <label className="form-label">Ciudad</label>
                        <input
                            placeholder="Introduzca su ciudad"
                            type="text"
                            className="form-control"
                            name="ciudad"
                            value={formData.ciudad}
                            onChange={handleChange}
                            required
                        />
                    </div>
                </div>

                <div className="mb-3">
                    <label className="form-label">Fecha de Nacimiento</label>
                    <input
                        type="date"
                        className="form-control"
                        name="cumpleanios"
                        value={formData.cumpleanios}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="text-center">
                    <button type="submit" className="btn btn-success">
                        Agregar Persona
                    </button>
                </div>
            </form>
        </div>
    );
}

export default AniadirPersona;
