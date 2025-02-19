import './App.css'
import React from 'react'
import Bolsos from './components/Bolsos'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import AniadirBolso from './components/AniadirBolso'
import Editar from './components/Editar'

function App() {

  return (
    <>
    <Router>
    <nav className="navbar navbar-expand-lg bg-white fixed-top">
        <div className="container-fluid">
          {/* Botón de hamburguesa ñam ñam /}
          <button
            className="navbar-toggler d-lg-none"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          {/ Enlaces de la barra de navegación */}
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <Link to="/aniadirBolso" className="nav-link text-dark">Añadir</Link> 
              </li>

            </ul>
          </div>
        </div>
      </nav>
        <Routes>
          <Route path='/' element={<Bolsos></Bolsos>}></Route>
          <Route path='/aniadirBolso' element={<AniadirBolso></AniadirBolso>}></Route>
          <Route path='/editarBolso' element={<Editar></Editar>}></Route>
        </Routes>
    </Router>
    </>
  )
}

export default App
