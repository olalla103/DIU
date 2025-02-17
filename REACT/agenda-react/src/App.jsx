import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import { useState } from "react";
import Navbar from "./components/Navbar";
import ListaPersonas from "./components/ListaPersonas";
import AniadirPersona from "./components/AniadirPersona";
import Login from "./components/Login";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    const handleLogin = () => setIsAuthenticated(true);
    const handleLogout = () => setIsAuthenticated(false);

    return (
        <Router>
            <div className="d-flex">
                <div className="container-fluid">
                    {/* Muestra la navbar solo si el usuario está autenticado */}
                    {isAuthenticated && <Navbar onLogout={handleLogout} />}
                    
                    <br /><br /><br />

                    <Routes>
                        {/* Ruta de Login */}
                        <Route path="/login" element={<Login onLogin={handleLogin} />} />

                        {/* Redirigir a login si no está autenticado */}
                        <Route path="/" element={<Navigate to={isAuthenticated ? "/personas" : "/login"} />} />

                        {/* Rutas protegidas (solo accesibles si el usuario ha iniciado sesión) */}
                        {isAuthenticated ? (
                            <>
                                <Route path="/personas" element={<ListaPersonas />} />
                                <Route path="/agregar" element={<AniadirPersona />} />
                            </>
                        ) : (
                            <>
                                {/* Redirigir a login si intentan acceder sin autenticación */}
                                <Route path="/personas" element={<Navigate to="/login" />} />
                                <Route path="/agregar" element={<Navigate to="/login" />} />
                            </>
                        )}
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
