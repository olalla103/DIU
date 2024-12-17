import { useState } from 'react';
import './App.css'
import Contador from './components/Contador';


function App() {

  const [numero, setNumero] = useState(0);

  const incrementar = () => {
    setNumero(numero + 1);
  }

  const decrementar = () => {
    setNumero(numero - 1);
  }

  const resetear = () => {
    setNumero(0);
  }

  return (
    <>
      <Contador incrementar={incrementar} decrementar={decrementar} resetear={resetear} numero={numero}></Contador>
    </>
  )
}

export default App
