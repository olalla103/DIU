import './App.css';
import Funcionalidadcalculadora from './components/Funcionalidadcalculadora';
import { useState } from 'react';

function App() {
  const [frase, setFrase] = useState("0");
  const [isResult, setIsResult] = useState(false);

  const handleOperacion = async () => {
    try {
      const encodedExpression = encodeURIComponent(frase);
      const response = await fetch(`http://api.mathjs.org/v4/?expr=${encodedExpression}`);

      if (response.ok) {
        const result = await response.text();
        setFrase(result);
        setIsResult(true);
      } else {
        throw new Error("Error en la API");
      }
    } catch (error) {
      console.error("Error en la operación:", error);
      setFrase("Error");
      setIsResult(true);
    }
  };

  const handlePosNeg = () => {
    if (frase !== "0") {
      if (parseFloat(frase) > 0) {
        setFrase((-Math.abs(parseFloat(frase))).toString());
      } else {
        setFrase((Math.abs(parseFloat(frase))).toString());
      }
    }
  };

  const handleBorrar = () => {
    setFrase("0");
    setIsResult(false);
  };

  const handleButtonClick = (value) => {
    if (isResult && !isNaN(value)) {
      setFrase(value);
      setIsResult(false);
    } else if (isResult && value === '.') {
      setFrase(prevFrase => prevFrase + value);
      setIsResult(false);
    } else {
      setFrase(prevFrase => prevFrase === "0" ? value : prevFrase + value);
    }
  };

  const handleOperatorClick = (operator) => {
    if (isResult) {
      setIsResult(false);
      setFrase(prevFrase => prevFrase + operator);
    } else {
      setFrase(prevFrase => prevFrase + operator);
    }
  };

  return (
    <div>
      <Funcionalidadcalculadora
        frase={frase}
        handleOperacion={handleOperacion}
        handlePosNeg={handlePosNeg}
        handleBorrar={handleBorrar}
        handleButtonClick={handleButtonClick}
        handleOperatorClick={handleOperatorClick}
      />
    </div>
  );
}

export default App;
