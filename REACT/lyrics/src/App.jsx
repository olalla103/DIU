import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Formulario from './components/Formulario';
import Tabla from './components/Tabla';

function App() {

  return (
    <>
      <div>
        <div>
          <Formulario />
        </div>
        <div>
          <Tabla />
        </div>
      </div>
    </>
  );
}

export default App
