import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { auth } from "../firebase";
import { signInWithEmailAndPassword, signInAnonymously } from "firebase/auth";
import { useUsuarios } from "../context/UsuariosContext"; // Importar el contexto
import "bootstrap-icons/font/bootstrap-icons.css";
import "../styles/Login.css";

function Login() {
    const [email, setEmail] = React.useState("");
    const [password, setPassword] = React.useState("");
    const [error, setError] = React.useState(null);
    const navigate = useNavigate();
    const { setUserInfo } = useUsuarios(); // Obtener la función para actualizar el usuario

    async function LoginWithEmailAndPasswordHandler(event) {
        event.preventDefault();
        try {
            const userCredential = await signInWithEmailAndPassword(auth, email, password);
            setUserInfo({ name: userCredential.user.displayName || "Usuario" }); // Guardar el nombre en contexto
            navigate("/personas");
        } catch (error) {
            setError("Error al iniciar sesión. Verifica tus credenciales.");
            console.error("Error de Login con contraseña y correo", error);
        }
    }

    async function LoginAnonymouslyHandler() {
        try {
            await signInAnonymously(auth);
            setUserInfo({ name: "anonimo" }); // Guardar como "Anónimo"
            navigate("/personas");
        } catch (error) {
            setError("Error al iniciar sesión anónima.");
            console.error("Error al iniciar como anónimo", error);
        }
    }

    function onChangeHandler(event) {
        const { name, value } = event.currentTarget;
        if (name === "userEmail") setEmail(value);
        if (name === "userPassword") setPassword(value);
    }

    return (
        <div className="d-flex justify-content-center align-items-center vh-100 fondo">
            <div className="glass-container">
                <i className="bi bi-person-circle user-icon"></i>
                <h2>Iniciar Sesión</h2>

                {error && <div className="alert alert-danger">{error}</div>}

                <form onSubmit={LoginWithEmailAndPasswordHandler}>
                    <input
                        type="email"
                        className="form-control"
                        name="userEmail"
                        placeholder="Correo electrónico"
                        value={email}
                        onChange={onChangeHandler}
                        required
                    />
                    <input
                        type="password"
                        className="form-control"
                        name="userPassword"
                        placeholder="Contraseña"
                        value={password}
                        onChange={onChangeHandler}
                        required
                    />
                    <button type="submit" className="btn btn-primary">
                        Iniciar sesión
                    </button>
                </form>

                <p className="text-center mt-3">
                    ¿No tienes cuenta?{" "}
                    <Link to="/registro" className="text-light">Regístrate</Link>
                </p>

                <p className="text-center mt-3">
                    <button onClick={LoginAnonymouslyHandler} className="btn btn-secondary">
                        Entrar como usuario anónimo
                    </button>
                </p>
            </div>
        </div>
    );
}

export default Login;
