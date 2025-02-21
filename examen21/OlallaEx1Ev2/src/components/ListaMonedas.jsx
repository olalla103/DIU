import React, { useEffect, useState, useContext } from "react";
import ProductDataService from "../services/api.js";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";
import { ProgresoContext } from "../providers/ContextoProgressBar";
import StockProgressBar from "./StockProgressBar"; // Importamos la barra de progreso
import "../styles/ListaMonedas.css"

// Listado de monedas que se muestra nombre, brand y stock 
function ListaMonedas() {

    const[monedas,setMonedas]=useState([]);
    const[monedActual,setMonedActual]=useState(null);

    const [busqueda, setBusqueda] = useState(""); // Estado para la búsqueda
    const { setProgreso, setStock, stock, MAX_STOCK } = useContext(ProgresoContext);

    const obtenerListaMonedas = () => {
        ProductDataService.getAll()
          .then((response) => {
            setMonedas(response.data);
            setProgreso(response.data.length);
          })
          .catch((error) => console.error("Error al obtener los monedas:", error));
      };

    useEffect(() => {
        obtenerListaMonedas();
    }, []);

    const setMonedActiva = (moneda) => {
        setMonedActual(moneda);
        setStock(moneda.stock);
      };

      // Filtrar productos según la búsqueda
  const monedasFiltradas = monedas.filter((moneda) =>
    moneda.name.toLowerCase().includes(busqueda.toLowerCase())
  );
    
    

  return (
    <div className="container mt-5">
        <h2 className="text-center text-dark mb-4">Lista de Monedas</h2>
        {/* Campo de búsqueda */}
        <input
            type="text"
            className="form-control mb-3"
            placeholder="Buscar moneda por nombre..."
            value={busqueda}
            onChange={(e) => setBusqueda(e.target.value)}
        />
        <div className="row">

    <div className="row">
            <div className="col-md-6">
            <ul className="list-group">
            {monedasFiltradas.map((moneda) => (
                <li
                    key={moneda.id}
                    className="list-group-item list-group-item-action text-dark"
                    style={{ backgroundColor: "#f8bbd0", cursor: "pointer" }}
                    onClick={() => setMonedActiva(moneda)}
                >
                    <h5 className="mb-0">{moneda.name}</h5>
                </li>
                ))}
                
            </ul>
            </div>


        <div className="col-md-6">
            {monedActual ? (
            <div className="card p-3" style={{ backgroundColor: "#fce4ec" }}>
                <h4 className="text-dark">Detalles de la Moneda</h4>
                <p><strong>Nombre: </strong> {monedActual.name}</p>
                <p><strong>Descripción: </strong> {monedActual.brand}</p>
                <p><strong>Cantidad en reserva: </strong> {stock}</p>
                <p><strong>Estado:</strong> {monedActual.active ? "Activada" : "Desactivada"}</p>

                {/* Barra de progreso del stock con contexto */}
                <StockProgressBar />

                <div className="mt-3">
                <button className="btn btn-success espacio" disabled={stock === 0 || !monedActual.active}>
                    <Link to={`/conversor/${monedActual.id}`} className="nav-link">
                    Conversor
                    </Link>
                </button>
                </div>
            </div>
            ) : (
            <h5 className="text-muted">Seleccione una moneda</h5>
            )}
        </div>
        </div>
    </div>
  </div>
  )
}

export default ListaMonedas
