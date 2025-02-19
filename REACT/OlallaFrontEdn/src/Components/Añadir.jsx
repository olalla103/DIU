import React, { useState } from 'react';
import ProductDataService from '../Services/products.service.js';
import { useParams, useNavigate } from 'react-router-dom';

function Añadir() {
  const [formData, setFormData] = useState({
    stock: '',
    price: '',
    name: '',
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
    
    try {ProductDataService.create(formData);
        alert("producto añadido correctamente");
    } catch (error) {
        console.log(error);
        alert("Error al añadir el bolso");
    }
    navigate('/productos');

    
    // Aquí puedes enviar los datos a una API o manejarlos como necesites
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Añadir Bolso</h2>
      <form onSubmit={handleSubmit}>
  {/* Nombre */}
  <div className="mb-3">
    <label className="form-label">Nombre</label>
    <input
      type="text"
      className="form-control"
      name="name"  // ✅ Corregido
      value={formData.name}
      onChange={handleChange}
      required
    />
  </div>
  
  {/* Stock */}
  <div className="mb-3">
    <label className="form-label">Stock</label>
    <input
      type="text"
      className="form-control"
      name="stock"  // ✅ Corregido
      value={formData.stock}
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
      name="price"  // ✅ Corregido
      value={formData.price}
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
