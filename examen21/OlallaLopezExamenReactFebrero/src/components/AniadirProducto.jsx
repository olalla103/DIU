import React, { useState, useEffect } from 'react';
import ProductDataService from '../services/api.js';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import ProgressBar from './ProgressBar'; // 🔥 Importar el nuevo componente

function AñadirProducto() {
  const MAX_PRODUCTOS = 10; // 🔥 Límite de productos permitidos

  const [formData, setFormData] = useState({
    id: '',
    name: '',
    stock: '',
    price: ''
  });

  const [productos, setProductos] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    // 🔹 Obtener la cantidad actual de productos desde la API
    ProductDataService.getAll().then(response => {
      setProductos(response.data.length);
    });
  }, []);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
  
    if (productos >= MAX_PRODUCTOS) {
      alert("No puedes añadir más productos. Has alcanzado el límite.");
      return;
    }
  
    // 🔹 Crear un nuevo objeto sin `id`
    const newProduct = {
      name: formData.name,
      stock: formData.stock,
      price: formData.price
    };
  
    try {
      ProductDataService.create(newProduct).then(() => {
        alert("Producto añadido correctamente");
  
        // 🔥 Recargar la lista de productos después de añadir uno nuevo
        ProductDataService.getAll().then(response => {
          setProductos(response.data.length);
        });
      });
    } catch (error) {
      console.log(error);
      alert("Error al añadir el producto");
    }
  
    navigate('/');
  };

  return (
    <div className="container mt-5 p-4 rounded w-75" style={{ backgroundColor: "#fce4ec" }}>
      <h2 className="mb-4 text-center text-dark">Añadir Producto</h2>

      {/* 🔥 Usar el nuevo componente ProgressBar */}
      <ProgressBar current={productos} max={MAX_PRODUCTOS} />

      <form onSubmit={handleSubmit}>
        {/* Nombre */}
        <div className="mb-3">
          <label className="form-label">Nombre</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={formData.name}
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
            name="price"
            value={formData.price}
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
            name="stock"
            value={formData.stock}
            onChange={handleChange}
            required
          />
        </div>

        {/* Botón de Enviar (Deshabilitado si se llega al límite) */}
        <button 
          type="submit" 
          className="btn btn-pink w-100"
          disabled={productos >= MAX_PRODUCTOS}
        >
          {productos >= MAX_PRODUCTOS ? "Límite Alcanzado" : "Guardar"}
        </button>
      </form>
    </div>
  );
}

export default AñadirProducto;
