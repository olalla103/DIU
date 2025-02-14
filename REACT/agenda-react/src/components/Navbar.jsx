import { useState } from "react";
import { Link } from "react-router-dom";
import "../styles/Navbar.css"; // Asegúrate de que esta hoja de estilos existe
//import fondoLista from "../img/fondoLista.jpg";

const Navbar = () => {
    const [isOpen, setIsOpen] = useState(false);

    return (
        <nav className="navbar-container">
            <div className="navbar">
                <button className="navbar-burger" onClick={() => setIsOpen(!isOpen)}>
                    <span className="material-icons">Inicio
                    </span>
                </button>
                <h1 className="navbar-title">Mi Proyecto</h1>
                <div className={`navbar-menu ${isOpen ? "open" : ""}`}>
                    <Link to="/personas" className="nav-item">Personas</Link>
                    <Link to="/agregar" className="nav-item">Agregar</Link>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
