import React, { useState } from 'react';
import BolsosDataService from '../services/api.js';
import { useParams, useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

function Añadir() {
  const [formData, setFormData] = useState({
    marca: '',
    precio: '',
    estilo: ''
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    try {BolsosDataService.create(formData);
        alert("Bolso añadido correctamente");
    } catch (error) {
        console.log(error);
        alert("Error al añadir el bolso");
    }
    navigate('/bolsos');


    // Aquí puedes enviar los datos a una API o manejarlos como necesites
  };
return (
    <div className="container mt-5">
      <h2 className="mb-4">Añadir Bolso</h2>
      <form onSubmit={handleSubmit}>
        {/* Marca */}
        <div className="mb-3">
          <label className="form-label">Marca</label>
          <input
            type="text"
            className="form-control"
            name="marca"
            value={formData.marca}
            onChange={handleChange}
            required
          />
        </div>

        {/* Precio */}
        <div className="mb-3">
          <label className="form-label">Precio (€)</label>
          <input
            type="number"
            className="form-control"
            name="precio"
            value={formData.precio}
            onChange={handleChange}
            required
          />
        </div>

        {/* Estilo */}
        <div className="mb-3">
          <label className="form-label">Estilo</label>
          <input
            type="text"
            className="form-control"
            name="estilo"
            value={formData.estilo}
            onChange={handleChange}
            required
          />
        </div>

        {/* Botón de Enviar */}
        <button type="submit" className="btn btn-primary">Guardar</button>
      </form>
    </div>
  );
}

export default Añadir;