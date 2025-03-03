import { createContext, useContext, useEffect, useState } from "react";
import PersonaDataService from "../services/api";

// Crear el contexto
const PersonasContext = createContext();

// Proveedor del contexto
export const PersonasProvider = ({ children }) => {
    const [cantidadPersonas, setCantidadPersonas] = useState(0);

    // Cargar la cantidad de personas al inicio
    useEffect(() => {
        actualizarCantidad(); // Llamamos la función para inicializar el estado
    }, []);

    // Función para actualizar la cantidad de personas
    const actualizarCantidad = async () => {
        try {
            const response = await PersonaDataService.getAll();
            setCantidadPersonas(response.data.length);
        } catch (error) {
            console.error("Error al obtener personas:", error);
        }
    };

    return (
        <PersonasContext.Provider value={{ cantidadPersonas, actualizarCantidad }}>
            {children}
        </PersonasContext.Provider>
    );
};

// Hook personalizado para usar el contexto fácilmente
export const usePersonas = () => useContext(PersonasContext);
