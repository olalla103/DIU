import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import ListaPersonas from "./components/ListaPersonas";
import AniadirPersona from "./components/AniadirPersona";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

function App() {
    return (
        <Router>
            <div className="d-flex">
                <div className="container-fluid">
                <Navbar></Navbar>
                <br />
                <br />
                <br />
                    <Routes>
                        <Route path="/" element={<h1 className="mt-4">Inicio</h1>} />
                        <Route path="/personas" element={<ListaPersonas />} />
                        <Route path="/agregar" element={<AniadirPersona />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
