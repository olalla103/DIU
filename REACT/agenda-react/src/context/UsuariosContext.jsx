import { createContext, useContext, useState } from "react";

const UsuariosContext = createContext();

export const UsuariosProvider = ({ children }) => {
    const [userInfo, setUserInfo] = useState({ name: "" }); // Inicializar con objeto vacío

    return (
        <UsuariosContext.Provider value={{ userInfo, setUserInfo }}>
            {children}
        </UsuariosContext.Provider>
    );
};

export const useUsuarios = () => useContext(UsuariosContext);
