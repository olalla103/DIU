import { createContext, useContext, useState, useEffect } from "react";
import { auth, db } from "../firebase"; // Importamos Firebase y Firestore
import { doc, getDoc } from "firebase/firestore"; // Métodos para leer Firestore

const UsuariosContext = createContext();

export const UsuariosProvider = ({ children }) => {
    const [userInfo, setUserInfo] = useState(() => {
        // Recuperar usuario desde localStorage al cargar la app
        const storedUser = localStorage.getItem("userInfo");
        return storedUser ? JSON.parse(storedUser) : { name: "", photoURL: "" };
    });

    // Detectar cambios en el usuario autenticado
    useEffect(() => {
        const unsubscribe = auth.onAuthStateChanged(async (user) => {
            if (user) {
                let photoURL = user.photoURL || ""; // Si hay foto en Auth, usarla
                let displayName = user.displayName || "Usuario";
                let isAnonymous = user.isAnonymous || false;

                // 📌 Buscar foto en Firestore si existe
                const userRef = doc(db, "users", user.uid);
                const userSnap = await getDoc(userRef);

                if (userSnap.exists()) {
                    const userData = userSnap.data();
                    photoURL = userData.photoUrl || photoURL; // Usar la de Firestore si existe
                }

                const userData = { name: displayName, photoURL,isAnonymous };
                setUserInfo(userData);

                // Guardar sesión en localStorage
                localStorage.setItem("userInfo", JSON.stringify(userData));
            } else {
                // Si no hay usuario autenticado, reiniciar valores
                setUserInfo({ name: "", photoURL: "", isAnonymous: false });

                // Eliminar datos del usuario en localStorage
                localStorage.removeItem("userInfo");
            }
        });

        return () => unsubscribe(); // Limpiar la suscripción al desmontar
    }, []);

    return (
        <UsuariosContext.Provider value={{ userInfo, setUserInfo }}>
            {children}
        </UsuariosContext.Provider>
    );
};

export const useUsuarios = () => useContext(UsuariosContext);
