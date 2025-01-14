import './App.css'
import './components/Funcionalidadcalculadora'
import Funcionalidadcalculadora from './components/Funcionalidadcalculadora'

function App() {

  const [numero, setNumero] = useState(0);

  const handleSumar(buttonValue) => {
    setNumero(numero + buttonValue);

  }

  const handleRestar() => {

  }

  const handleDividir() => {

  }

  const handleMultiplicar() => {

  }

  const handlePosNeg() => {

  }

  const handleBorrar() => {
    setNumero(0);
  }


  return (
    <>
      <div>
        {/*Aquí voy a meter el componente*/}
        <Funcionalidadcalculadora></Funcionalidadcalculadora>
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
