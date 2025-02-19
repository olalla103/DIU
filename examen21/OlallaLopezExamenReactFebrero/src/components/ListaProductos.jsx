import React, { useEffect, useState } from 'react';
import ProductDataService from "../services/api.js";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';
import '../styles/ListaProductos.css'

function ListaProductos() {
  const [productos, setProductos] = useState([]);
  const [productoActual, setProductoActual] = useState(null);

  const obtenerListaProductos = () => {
    ProductDataService.getAll()
      .then(response => {
        console.log(response.data);
        setProductos(response.data);
      });
  };

  const handleDelete = (id, event) => {
    event.preventDefault(); // Evita que el link recargue la página
    ProductDataService.delete(id)
      .then(() => {
        obtenerListaProductos(); // Refresca la lista después de eliminar
        setProductoActual(null); // Limpia la selección
      })
      .catch((e) => {
        console.log(e);
      });
  };

  useEffect(() => {
    obtenerListaProductos();
  }, []);

  const setProductoActivo = (producto) => {
    console.log(producto);
    setProductoActual(producto);
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center text-dark mb-4">Lista de Productos</h2>

      <div className="row">
        {/* Lista de productos */}
        <div className="col-md-6">
          <ul className="list-group">
            {productos.map((producto) => (
              <li key={producto.id}
                className="list-group-item list-group-item-action text-dark"
                style={{ backgroundColor: "#f8bbd0", cursor: "pointer" }}
                onClick={() => setProductoActivo(producto)}>
                <h5 className="mb-0">{producto.name}</h5>
              </li>
            ))}
          </ul>
        </div>

        {/* Detalles del producto seleccionado */}
        <div className="col-md-6">
          {productoActual ? (
            <div className="card p-3" style={{ backgroundColor: "#fce4ec" }}>
              <h4 className="text-dark">Detalles del Producto</h4>
              <p><strong>ID:</strong> {productoActual.id}</p>
              <p><strong>Nombre:</strong> {productoActual.name}</p>
              <p><strong>Stock:</strong> {productoActual.stock}</p>
              <p><strong>Precio:</strong> {productoActual.price}€</p>

                <div>
                {/* Enlace para Editar */}
                {productoActual.id && (
                    <button className='rounded btn btn-pink espacio'>
                    <Link to={`/editarProducto/${productoActual.id}`} className="nav-link">Editar</Link>
                    </button>
                )}
                
                {/* Botón para Eliminar */}
                <button className='btn btn-danger espacio'>
                    <Link to="#" className="nav-link" onClick={(e) => handleDelete(productoActual.id, e)}>
                    Eliminar
                    </Link>
                </button>
              </div>
            </div>
          ) : (
            <h5 className="text-muted">Seleccione un producto</h5>
          )}
        </div>
      </div>
    </div>
  );
}

export default ListaProductos;  