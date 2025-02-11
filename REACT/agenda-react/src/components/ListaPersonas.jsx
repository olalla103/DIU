import { useEffect, useState } from "react";
import api from "../services/api";

const ListaPersonas = () => {
    const [personas, setPersonas] = useState([]); // Estado inicial como array vacío

    useEffect(() => {
        api.get("/person")
            .then(response => {
                console.log("Datos recibidos:", response.data); // ✅ Verificar qué devuelve la API
                setPersonas(Array.isArray(response.data) ? response.data : response.data.data || []);
            })
            .catch(error => {
                console.error("Error al obtener personas:", error);
                setPersonas([]); // Si hay error, aseguramos que `personas` sea un array vacío
            });
    }, []);

    return (
        <div>
            <h2>Lista de Personas</h2>
            {Array.isArray(personas) && personas.length > 0 ? (
                <ul>
                    {personas.map(person => (
                        <li key={person.DNI}>
                            {person.nombre} {person.apellidos} - {person.ciudad}
                        </li>
                    ))}
                </ul>
            ) : (
                <p>No hay personas registradas.</p>
            )}
        </div>
    );
};

export default ListaPersonas;
