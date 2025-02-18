import { useState } from "react";
import { Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import "../styles/Navbar.css";

function Navbar() {
    const [isOpen, setIsOpen] = useState(false);

    const handleIniciado = () =>{
        iniciado
    }

    return (
        <nav className="navbar-container">
            <div className="navbar">
                <button className="navbar-burger" onClick={() => setIsOpen(!isOpen)}>
                    <span className="material-icons">

                        <Link to="/personas">Inicio</Link>
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
}

export default Navbar;
