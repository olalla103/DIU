import "./App.css";
import HeaderComponent from "./components/HeaderComponent";
import ButtonComponent from "./components/ButtonComponent";
import { useState } from "react";

function App() {
  // Esto lo he creado yo, suma la variable number, que la he hecho reactiva
  // porque también hay variables estáticas
  const [number, setNumber] = useState(0);

  const suma = () => {
    setNumber(number + 1);
  };

  const sayHello = () => {
    console.log("Hola a todos");
  };

  const handleChange = (e) => {
    console.log(e);
  };

  return (
    <>
      <HeaderComponent />
      <main className="main-content">
        <h2 onClick={sayHello}>Hola a todos</h2>

        <h2>Number: {number}</h2>

        <input type="text" onChange={handleChange} />
        <br />
        <br />
        <ButtonComponent suma={suma} />
      </main>
    </>
  );
}

export default App;
