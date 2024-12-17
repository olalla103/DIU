import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import './App.css'
import HeaderComponent from './components/HeaderComponent'
import ButtonComponent from './components/ButtonComponent'

function App() {

  // let number = 0;

  const [number, setNumber] = useState(0);
  const [myValue, setMyValue] = useState("");
  let myPlaceholder = "Escribe aquí";

  const [greetings, setGreetings] = useState("Bienvenidos a mi web");
  const links = {
    home: "Home",
    blog: "Blog",
    news: "Noticias",
    contact: "Contáctanos"
  }

  const [user, setUser] = useState({});

  const login = (userInfo) => {
    console.log(userInfo);
    setUser(userInfo);
  }

  const addOne = () => {
    //number++;
    setNumber(number + 1);
    console.log(number);
  }

  const sayHello = () => {
    console.log("Holiwis");
  }

  const handleChange = (e) => {
    console.log(e.target.value);
  }

  return (
    <>
      <HeaderComponent greetings={greetings} links={links}></HeaderComponent>
      <main className="main-content">
        <h2 onClick={sayHello}>Hola {user.username}</h2>

        <Login ></Login>

        <h2 onClick={addOne}>Number: {number}</h2>

        <input value={myValue} placeholder={myPlaceholder} type="text" onChange={handleChange} />

        <br />
        <br />
        <ButtonComponent></ButtonComponent>
      </main>
    </>
  )
}

export default App
