import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import ListaPersonas from "./components/ListaPersonas";
import AniadirPersona from "./components/AniadirPersona";
import Login from "./components/Login";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min"; 
import "./App.css";
import RegistroUsuario from "./components/RegistroUsuario";

function App() {
    return (
        <Router>
            <div className="d-flex">
                <div className="container-fluid">
                     {/* Solo mostrar Navbar si NO estamos en la ruta /login */}
                    {location.pathname !== "/login" && <Navbar />}
                    
                    <br /><br /><br />

                    <Routes>
                        <Route path="/" element={<ListaPersonas />} />
                        <Route path="/personas" element={<ListaPersonas />} />
                        <Route path="/agregar" element={<AniadirPersona />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/registro" element={<RegistroUsuario/>}></Route>
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
