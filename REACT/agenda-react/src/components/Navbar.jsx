import { usePersonas } from "../context/PersonasContext";
import { useUsuarios } from "../context/UsuariosContext";
import { Link, useNavigate } from "react-router-dom";
import { auth } from "../firebase";
import "../styles/Navbar.css";

function Navbar() {
    const { cantidadPersonas } = usePersonas();
    const { userInfo } = useUsuarios();
    const navigate = useNavigate();
    const MAX_PERSONAS = 5;

    const handleLogout = async () => {
        try {
            await auth.signOut();
            navigate("/");
        } catch (error) {
            console.error("Error al cerrar sesión:", error);
        }
    };

    return (
        <nav className="navbar-container">
            <div className="user-section">
                {userInfo.photoURL ? (
                    <img src={userInfo.photoURL} alt="Usuario" className="user-avatar" />
                ) : (
                    <i className="bi bi-person-circle default-avatar"></i>
                )}
                <button className="logout-button" onClick={handleLogout}>Cerrar sesión</button>
            </div>

            <div className="progress-container">
                <label>Cantidad de personas en la agenda: {cantidadPersonas}/{MAX_PERSONAS}</label>
                <div className="progress-bar">
                    <div className="progress-fill" style={{ width: `${(cantidadPersonas / MAX_PERSONAS) * 100}%` }} />
                </div>
            </div>

            <div className="navbar-menu">
                <Link to="/personas">Personas</Link>

                {/* Ocultar botón "Agregar" si el usuario es anónimo */}
                {!userInfo.isAnonymous && userInfo.name !== "" && (
                    <Link to="/agregar" className={cantidadPersonas >= MAX_PERSONAS ? "disabled-link" : ""}>
                        Agregar
                    </Link>
                )}
            </div>
        </nav>
    );
}

export default Navbar;