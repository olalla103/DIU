import './App.css'
import './components/Funcionalidadcalculadora'
import Funcionalidadcalculadora from './components/Funcionalidadcalculadora'
//import { add, subtract, multiply, divide } from 'mathjs';
//import { useState } from 'react';


function App() {

  // const [numero, setNumero] = useState(0);

  /* const handleSumar = (buttonValue) => {
     setNumero(add(numero, buttonValue));
 
   }
 
   const handleRestar = (buttonValue) => {
     setNumero(subtract(numero, buttonValue));
 
   }
 
   const handleDividir = (buttonValue) => {
     setNumero(divide(numero, buttonValue));
 
   }
 
   const handleMultiplicar = (buttonValue) => {
     setNumero(multiply(numero, buttonValue));
   }
 
   const handlePosNeg = () => {
 
   }
 
   const handleBorrar = () => {
     setNumero(0);
   }*/


  return (
    <>
      <div>
        {/*Aquí voy a meter el componente*/}
        <Funcionalidadcalculadora
          /*numero={numero}
          handleSumar={handleSumar} handleRestar={handleRestar}
          handleDividir={handleDividir} handleMultiplicar={handleMultiplicar}
          handlePosNeg={handlePosNeg}
          handleBorrar={handleBorrar}*/></Funcionalidadcalculadora>
      </div>

      <div>
        {/*Es este es el div de los botones*/}
        <div>

        </div>

      </div>
    </>
  )
}

export default App
