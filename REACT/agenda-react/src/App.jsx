import { BrowserRouter as Router, Routes, Route, Navigate, useLocation } from "react-router-dom";
import { UsuariosProvider } from "./context/UsuariosContext"; // Importar el contexto
import Navbar from "./components/Navbar";
import ListaPersonas from "./components/ListaPersonas";
import AniadirPersona from "./components/AniadirPersona";
import Login from "./components/Login";
import RegistroUsuario from "./components/RegistroUsuario";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min"; 
import "./App.css";

function App() {
    return (
        <UsuariosProvider> 
            <Router>
                <AppWithNavbar />
            </Router>
        </UsuariosProvider>
    );
}

function AppWithNavbar() {
    const location = useLocation(); // Ahora está dentro de Router y no da error

    return (
        <div className="d-flex">
            <div className="container-fluid">
                {/* Mostrar Navbar solo si NO estás en /login */}
                {location.pathname !== "/login" && <Navbar />}
                
                <br /><br /><br />

                <Routes>
                    <Route path="/" element={<Navigate to="/login" />} /> {/* Redirigir a Login por defecto */}
                    <Route path="/personas" element={<ListaPersonas />} />
                    <Route path="/agregar" element={<AniadirPersona />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/registro" element={<RegistroUsuario />} />
                </Routes>
            </div>
        </div>
    );
}

export default App;
