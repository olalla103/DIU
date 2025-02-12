import { useState } from "react";
import { Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css"; // Íconos de Bootstrap
import "./styles/Navbar.css";

const Navbar = () => {
    /*const [isOpen, setIsOpen] = useState(false);*/

    const [submenuOpen, setSubmenuOpen] = useState(false);

    /*<nav className="navbar navbar-dark bg-dark vh-100 position-fixed d-flex flex-column p-3" style={{ width: isOpen ? "250px" : "80px", transition: "width 0.3s ease" }}>
            {/* Botón de Toggle }*/
            /*<button className="btn btn-outline-light mb-3" onClick={() => setIsOpen(!isOpen)}>
                <i className="bi bi-list"></i> {/* Icono de menú }*/
           /* </button>*/

           /* {/* Menú de navegación }*/
          /*  <ul className="nav flex-column">
                <li className="nav-item">
                    <Link className="nav-link text-white d-flex align-items-center" to="/">
                        <i className="bi bi-house-door"></i>
                        {isOpen && <span className="ms-2">Inicio</span>}
                    </Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link text-white d-flex align-items-center" to="/personas">
                        <i className="bi bi-person"></i>
                        {isOpen && <span className="ms-2">Personas</span>}
                    </Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link text-white d-flex align-items-center" to="/agregar">
                        <i className="bi bi-plus-circle"></i>
                        {isOpen && <span className="ms-2">Agregar</span>}
                    </Link>
                </li>
            </ul>
        </nav>*/

    return (
        <nav>
        <ul className="menu">
            <li><Link to="/about">Inicio</Link></li>
            <li><Link to="/works">Agregar Persona</Link></li>
            <li
                onMouseEnter={() => setSubmenuOpen(true)}
                onMouseLeave={() => setSubmenuOpen(false)}
            >
                <Link to="#">Personas</Link>
                {submenuOpen && (
                    <ul className="submenu">
                        <li><Link to="/skills/react">Agregar Tutorial</Link></li>
                    </ul>
                )}
            </li>
        </ul>
        </nav>
            );
        };

export default Navbar;
