import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useContext } from 'react';
import { ProgressContext } from '../Providers/ProgressContext.jsx'; // Importar el contexto
import { Link, useNavigate } from 'react-router-dom';

function Navbar() {
    const { progress } = useContext(ProgressContext); // Usar el contexto
    const navigate = useNavigate(); // Hook para la navegación programática

    const handleNavigation = (e) => {
        if (progress < 5) {
            navigate('/añadir'); // Redirige si progress es menor a 5
        } else {
            e.preventDefault(); // Previene la navegación predeterminada del Link
            alert('No puedes acceder a esta página porque el progreso es mayor o igual a 5.');
        }
    };

    return (
        <nav className="navbar navbar-expand-lg bg-white fixed-top">
            <div className="container-fluid">
                <button className="navbar-toggler d-lg-none" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <Link to="/añadir" className="nav-link text-dark" onClick={handleNavigation}>
                                Añadir
                            </Link> 
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
}

export default Navbar;
