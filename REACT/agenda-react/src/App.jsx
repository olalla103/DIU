import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import ListaPersonas from "./components/ListaPersonas";
import AniadirPersona from "./components/AniadirPersona";

function App() {
    return (
        <Router>
            <div className="d-flex">
                {/* Navbar fija en el lateral */}
                <Navbar />

                {/* Contenido principal ajustado */}
                <div className="container-fluid" style={{ marginLeft: "80px", transition: "margin-left 0.3s ease" }}>
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
