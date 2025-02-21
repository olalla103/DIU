import { createContext, useState } from "react";

// Crear el contexto
export const ProgresoContext = createContext();

// Proveedor del contexto
export function ProgresoProvider({ children }) {
    const [progreso, setProgreso] = useState(0);
    const [stock, setStock] = useState(0);

    // Definir el mismo máximo de stock para todos los productos
    const MAX_STOCK = 100; 

    return (
        <ProgresoContext.Provider value={{ progreso, setProgreso, stock, setStock, MAX_STOCK }}>
            {children}
        </ProgresoContext.Provider>
    );
}
