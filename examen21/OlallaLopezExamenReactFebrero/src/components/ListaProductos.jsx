import React, { useEffect, useState, useContext } from "react";
import ProductDataService from "../services/api.js";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";
import "../styles/ListaProductos.css";
import { ProgresoContext } from "../providers/ContextoProgressBar";
import StockProgressBar from "./StockProgressBar"; // Importamos la barra de progreso

function ListaProductos() {
  const [productos, setProductos] = useState([]);
  const [productoActual, setProductoActual] = useState(null);
  const [busqueda, setBusqueda] = useState(""); // Estado para la búsqueda
  const { setProgreso, setStock, stock, MAX_STOCK } = useContext(ProgresoContext);

  const obtenerListaProductos = () => {
    ProductDataService.getAll()
      .then((response) => {
        setProductos(response.data);
        setProgreso(response.data.length);
      })
      .catch((error) => console.error("Error al obtener los productos:", error));
  };

  const handleDelete = (id, event) => {
    event.preventDefault();
    ProductDataService.delete(id)
      .then(() => {
        obtenerListaProductos();
        setProductoActual(null);
        setStock(0);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  useEffect(() => {
    obtenerListaProductos();
  }, []);

  const setProductoActivo = (producto) => {
    setProductoActual(producto);
    setStock(producto.stock);
  };

  // Filtrar productos según la búsqueda
  const productosFiltrados = productos.filter((producto) =>
    producto.name.toLowerCase().includes(busqueda.toLowerCase())
  );

  return (
    <div className="container mt-5">
      <h2 className="text-center text-dark mb-4">Lista de Productos</h2>

      {/* Campo de búsqueda */}
      <input
        type="text"
        className="form-control mb-3"
        placeholder="Buscar producto por nombre..."
        value={busqueda}
        onChange={(e) => setBusqueda(e.target.value)}
      />

      <div className="row">
        <div className="col-md-6">
          <ul className="list-group">
            {productosFiltrados.map((producto) => (
              <li
                key={producto.id}
                className="list-group-item list-group-item-action text-dark"
                style={{ backgroundColor: "#f8bbd0", cursor: "pointer" }}
                onClick={() => setProductoActivo(producto)}
              >
                <h5 className="mb-0">{producto.name}</h5>
              </li>
            ))}
          </ul>
        </div>

        <div className="col-md-6">
          {productoActual ? (
            <div className="card p-3" style={{ backgroundColor: "#fce4ec" }}>
              <h4 className="text-dark">Detalles del Producto</h4>
              <p><strong>ID:</strong> {productoActual.id}</p>
              <p><strong>Nombre:</strong> {productoActual.name}</p>
              <p><strong>Stock:</strong> {stock}</p>
              <p><strong>Precio:</strong> {productoActual.price}€</p>
              <p><strong>Estado:</strong> {productoActual.active ? "Activado" : "Desactivado"}</p>

              {/* Barra de progreso del stock con contexto */}
              <StockProgressBar />

              <div className="mt-3">
                {productoActual.id && (
                  <button className="rounded btn btn-pink espacio">
                    <Link to={`/editarProducto/${productoActual.id}`} className="nav-link">
                      Editar
                    </Link>
                  </button>
                )}
                <button className="btn btn-danger espacio">
                  <Link to="#" className="nav-link" onClick={(e) => handleDelete(productoActual.id, e)}>
                    Eliminar
                  </Link>
                </button>
                <button className="btn btn-success espacio" disabled={stock === 0|| productoActual.active ===0}>
                  <Link to={`/comprarProducto/${productoActual.id}`} className="nav-link">
                    Comprar
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
