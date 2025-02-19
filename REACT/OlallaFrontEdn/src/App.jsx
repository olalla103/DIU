import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { ProgressProvider } from './Providers/ProgressContext.jsx'; // Importa el contexto
import Products from './Components/Products.jsx';
import Añadir from './Components/Añadir.jsx';
import Editar from './Components/Editar.jsx';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import ProgressBar from './Components/ProgressBar.jsx';
import { useContext } from 'react';
import { ProgressContext } from './Providers/ProgressContext.jsx'; // Importar el contextoi
import Navbar from './Components/Navbar.jsx';

function App() {
  
  return (
    <ProgressProvider>
      <Router>
        
        <Navbar></Navbar>
    
        <ProgressBar />

        {/* Rutas */}
        <Routes>
          <Route path="/añadir" element={<Añadir />} />
          <Route path="/editar/:id" element={<Editar />} />
          <Route path="/productos" element={<Products />} />
          <Route path="/" element={<Products />} /> {/* Ruta por defecto */}
        </Routes>
      </Router>
    </ProgressProvider>
  );
}

export default App;
