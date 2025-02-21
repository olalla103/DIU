import React, { useContext } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { ProgresoContext } from "../providers/ContextoProgressBar"; 

function StockProgressBar() {
  const { stock, MAX_STOCK } = useContext(ProgresoContext); // Obtener valores del contexto

  const porcentaje = (stock / MAX_STOCK) * 100;

  return (
    <div className="progress mt-3" style={{ height: "20px" }}>
      <div
        className="progress-bar bg-warning"
        role="progressbar"
        style={{ width: `${porcentaje}%` }}
        aria-valuenow={stock}
        aria-valuemin="0"
        aria-valuemax={MAX_STOCK}
      >
        {porcentaje}%
      </div>
    </div>
  );
}

export default StockProgressBar;
