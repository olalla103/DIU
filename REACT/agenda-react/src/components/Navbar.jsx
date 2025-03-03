import { usePersonas } from "../context/PersonasContext";
import { useUsuarios } from "../context/UsuariosContext"; // Importar el contexto de usuario
import { Link } from "react-router-dom";
import "../styles/Navbar.css"; 

function Navbar() {
    const { cantidadPersonas } = usePersonas();
    const { userInfo } = useUsuarios(); // Obtener info del usuario desde el contexto
    const MAX_PERSONAS = 5;

    return (
        <nav className="navbar-container">
            <Link to="/" className="navbar-title">Mi Proyecto</Link>
            
            <div className="progress-container">
                <label>Agenda: {cantidadPersonas}/{MAX_PERSONAS}</label>
                <div className="progress-bar">
                    <div className="progress-fill" style={{ width: `${(cantidadPersonas / MAX_PERSONAS) * 100}%` }}></div>
                </div>
            </div>

            <div className="navbar-menu">
                <Link to="/personas">Personas</Link>
                {/* Ocultar botón "Agregar" si el usuario es anónimo */}
                {!userInfo.name == "anonimo" && (
                    <Link to="/agregar" className={cantidadPersonas >= MAX_PERSONAS ? "disabled-link" : ""}>
                        Agregar
                    </Link>
                )}
            </div>
        </nav>
    );
}

export default Navbar;
