import { useEffect, useState } from "react";
import api from "../services/api";
import axios from "axios";

const ListaPersonas = () => {
    const [personas, setPersonas] = useState([]); // Estado inicial vacío
    const borrarTodo = () => {
        const confirmacion = window.confirm("¿Estás seguro de borrar todos los registros?");
        if (!confirmacion) return;

        try{
            axios.delete("http://localhost:8090/api/v1/person");
            setPersonas([]);
            alert("Todos los registros han sido eliminados correctamente"); 
        }catch(error){
        console.error("Error al borrar los registros:", error);
        alert("Error al borrar los registros");
        }
        
    }


    useEffect(() => {
        api.get("/person")
            .then(response => {
                console.log("Datos recibidos desde la API:", response.data); // 🔍 Verifica qué devuelve la API
                
                if (Array.isArray(response.data)) {
                    setPersonas(response.data); // Caso correcto: la API devuelve un array
                } else if (Array.isArray(response.data.data)) {
                    setPersonas(response.data.data); // Caso cuando la API devuelve { data: [...] }
                } else {
                    console.warn("⚠️ Respuesta inesperada en la API:", response.data);
                    setPersonas([]); // Evita errores en .map()
                }
            })
            .catch(error => {
                console.error("Error al obtener personas:", error);
                setPersonas([]); // Si hay error, evitamos `undefined`
            });
    }, []);

    return (
        <div>
            <h2>Lista de Personas</h2>
            <div className="lista-personas">
            {personas.length > 0 ? (
                <ul>
                    {personas.map(person => (
                        <li key={person.dni}>
                            {person.nombre} {person.apellidos} - {person.ciudad}
                        </li>
                    ))}
                </ul>
            ) : (
                <p>⚠️ No hay personas registradas.</p>
            )}
            </div>
            <button className="btn btn-danger" onClick={borrarTodo}>Borrar todos</button>
        </div>
    );
};

export default ListaPersonas;
