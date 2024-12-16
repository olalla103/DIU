import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import HeaderComponent from './components/HeaderComponent'
import ButtonComponent from './components/ButtonComponent'

function App() {
  const sayHello = () => {
    console.log("Holiwis");
  }

  const handleChange = (e) => {
    console.log(e);

  }

  return (
    <>
      <HeaderComponent></HeaderComponent>
      <main className="main-content">
        <h2 onClick={sayHello}>Hola a todos</h2>
        <input type="text" onChange={handleChange} />

        <br />
        <br />
        <ButtonComponent></ButtonComponent>
      </main>
    </>
  )
}

export default App
