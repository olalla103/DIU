import React from "react";
import { Link } from "react-router-dom";
import { auth } from "../firebase";
import { signInWithEmailAndPassword } from "firebase/auth";
import { useNavigate } from "react-router-dom";
import "bootstrap-icons/font/bootstrap-icons.css"; // Asegurar que los íconos de Bootstrap están cargados
import "../styles/Login.css"; // Importar los estilos de glassmorphism

function Login() {
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [error, setError] = React.useState(null);
    const navigate = useNavigate();

    async function LoginWithEmailAndPasswordHandler(event) {
        event.preventDefault();
        try {
            await signInWithEmailAndPassword(auth, email, password);
            navigate("/personas"); // Navegar solo después de iniciar sesión correctamente
        } catch (error) {
            setError("Error al iniciar sesión. Verifica tus credenciales.");
            console.error("Error de Login con contraseña y correo", error);
        }
    }

    function onChangeHandler(event) {
        const { name, value } = event.currentTarget;
        if (name === "userEmail") setEmail(value);
        if (name === "userPassword") setPassword(value);
    }

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="glass-container">
                <i className="bi bi-person-circle user-icon"></i> {/* Icono de usuario */}
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
                    <Link to="/personas" className="text-light">Entrar como usuario anónimo</Link>
                </p>
            </div>
        </div>
    );
}

export default Login;
