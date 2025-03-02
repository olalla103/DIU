import { usePersonas } from "../context/PersonasContext";
import { Link } from "react-router-dom";
import "../styles/Navbar.css"; // ✅ Verifica que la ruta es correcta

function Navbar() {
    const { cantidadPersonas } = usePersonas();
    const MAX_PERSONAS = 5;
    
    return (
        <nav className="navbar-container">  {/* 🔍 Ajusté esta clase para que coincida con el CSS */}
            <Link to="/" className="navbar-title">Mi Proyecto</Link>
            
            <div className="progress-container">
                <label>Agenda: {cantidadPersonas}/{MAX_PERSONAS}</label>
                <div className="progress-bar">
                    <div className="progress-fill" style={{ width: `${(cantidadPersonas / MAX_PERSONAS) * 100}%` }}></div>
                </div>
            </div>

            <div className="navbar-menu">
                <Link to="/personas">Personas</Link>
                <Link to="/agregar" className={cantidadPersonas >= MAX_PERSONAS ? "disabled-link" : ""}>
                    Agregar
                </Link>
            </div>
        </nav>
    );
}

export default Navbar;
