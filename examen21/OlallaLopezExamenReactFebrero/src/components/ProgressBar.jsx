import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

function ProgressBar({ current, max }) {
  const percentage = (current / max) * 100; // 🔥 Calcula el porcentaje

  return (
    <div className="mb-3">
      <label className="form-label">Productos Añadidos: {current} / {max}</label>
      <div className="progress">
        <div
          className="progress-bar bg-danger"
          role="progressbar"
          style={{ width: `${percentage}%` }}
          aria-valuenow={current}
          aria-valuemin="0"
          aria-valuemax={max}
        >
          {Math.round(percentage)}%
        </div>
      </div>
    </div>
  );
}

export default ProgressBar;
