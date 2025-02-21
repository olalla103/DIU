import './App.css';
import './index.css';
import React from 'react';
import ListaMonedas from './components/ListaMonedas';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { ProgresoProvider } from './providers/ContextoProgressBar'; // Importar el Proveedor de contexto
import Conversor from './components/Conversor';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <ProgresoProvider> {/* Envuelve toda la app */}
       {/* Ahora se mostrará en toda la aplicación */}

      <Router>
        <nav className="navbar navbar-expand-lg navbar-pink fixed-top">
          <div className="container-fluid">
            <button className="navbar-toggler d-lg-none" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                <li>
                  <Link to="/" className='nav-link text-white'>Lista de Monedas</Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>

        <div className="container mt-1">
          <Routes>
            <Route path='/' element={<ListaMonedas />} />
            <Route path='/conversor/:id' element={<Conversor />} />
          </Routes>
        </div>
      </Router>
    </ProgresoProvider>
  );
}

export default App;
