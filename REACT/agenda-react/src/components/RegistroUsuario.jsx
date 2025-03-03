import React from "react";
import { Link } from "react-router-dom";
import { signInWithGoogle, auth } from "../firebase";
import { createUserWithEmailAndPassword } from "firebase/auth";
import "../styles/RegistroUsuario.css"; // Importa los estilos glass

function RegistroUsuario() {
    const [email, setEmail] = React.useState("");
    const [password, setPassword] = React.useState("");
    const [displayName, setDisplayName] = React.useState("");
    const [error, setError] = React.useState(null);

    async function createUserWithEmailAndPasswordHandler(event, email, password) {
        event.preventDefault();
        try {
            console.log(email,password)
            const { user } = await createUserWithEmailAndPassword(auth, email, password);
            console.log("Usuario creado:", user);
        } catch (error) {
            setError("Error al registrar usuario.");
            console.error("Error al registrar usuario:", error);
        }

        setEmail("");
        setPassword("");
        setDisplayName("");
    }

    function onChangeHandler(event) {
        const { name, value } = event.currentTarget;

        if (name === "userEmail") {
            setEmail(value);
        } else if (name === "userPassword") {
            setPassword(value);
        } else if (name === "displayName") {
            setDisplayName(value);
        }
    }

    return (
        <div className="registro-container">
            <h1 className="text-center">Regístrate</h1>
            {error && <div className="alert alert-danger text-center">{error}</div>}
            <form>
                <input type="text" name="displayName" placeholder="Nombre" value={displayName} onChange={onChangeHandler} required />
                <input type="email" name="userEmail" placeholder="Correo electrónico" value={email} onChange={onChangeHandler} required />
                <input type="password" name="userPassword" placeholder="Contraseña" value={password} onChange={onChangeHandler} required />
                
                <button onClick={(event) => createUserWithEmailAndPasswordHandler(event, email, password)}>Registrarse</button>
            </form>

            <p className="text-center">
                ¿Ya tienes una cuenta?{" "}
                <Link to="/" className="text-blue-500 hover:text-blue-600">
                    Inicia sesión aquí
                </Link>
            </p>
        </div>
    );
}

export default RegistroUsuario;
