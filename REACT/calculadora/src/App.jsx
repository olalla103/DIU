import './App.css'
import Funcionalidadcalculadora from './components/Funcionalidadcalculadora'
import { evaluate } from 'mathjs';
import { useState } from 'react';

function App() {

  const [frase, setFrase] = useState("0");
  const [isResult, setIsResult] = useState(false);

  const handleOperacion = () => {
    const resultado = evaluate(frase);
    setFrase(resultado.toString());
    setIsResult(true);
  }

  const handlePosNeg = () => {
    if (frase !== "0") {
      if (parseFloat(frase) > 0) {
        setFrase((-Math.abs(parseFloat(frase))).toString());
      } else {
        setFrase((Math.abs(parseFloat(frase))).toString());
      }
    }
  }

  const handleBorrar = () => {
    setFrase("0");
    setIsResult(false);
  }

  const handleButtonClick = (value) => {
    if (isResult && !isNaN(value)) {
      setFrase(value);
      setIsResult(false);
    } else {
      setFrase(prevFrase => prevFrase === "0" ? value : prevFrase + value);
    }
  }

  const handleOperatorClick = (operator) => {
    if (isResult) {
      setIsResult(false);
      setFrase(prevFrase => prevFrase + operator);
    } else {
      setFrase(prevFrase => prevFrase + operator);
    }
  }

  return (
    <>
      <div>
        <Funcionalidadcalculadora
          frase={frase}
          handleOperacion={handleOperacion}
          handlePosNeg={handlePosNeg}
          handleBorrar={handleBorrar}
          handleButtonClick={handleButtonClick}
          handleOperatorClick={handleOperatorClick}>
        </Funcionalidadcalculadora>
      </div>
    </>
  )
}

export default App;