import "./App.css";
import HeaderComponent from "./components/HeaderComponent";
import { useState } from "react";
import Login from "./components/Login";
import MovieList from "./components/MovieList";
import AnimalList from "./components/AnimalList";

function App() {
  // Esto lo he creado yo, suma la variable number, que la he hecho reactiva
  // porque también hay variables estáticas
  const [greetings]=useState("Bienvenidos a mi web"); 

  const links={
    home: "Home",
    blog: "Blog",
    news:"News",
    contact: "Contact"  
  }

  const [user, setUser] = useState({});

  const login =(userInfo)=>{
    setUser(userInfo);

  }

  const sayHello = () => {
    console.log("Hola a todos");
  };

  const handleChange = (e) => {
    console.log(e);
  };

  const conditions = true; 

  return (
    <>
      <HeaderComponent greetings={greetings} links={links} />
      <main className="main-content">
        {user.username && <h2 onClick={sayHello}>Hola {user.username}</h2>}
        {/*Se pasa por props la función que se ha hecho en el componente login
        En la función login, encontramos un setUser que coge el usuario del componente */}
        <Login handleLogin={login}></Login>
          <h2>Películas</h2>
          <MovieList></MovieList>
          <AnimalList></AnimalList>
      </main>
    </>
  );
}

/*conditions ? (<h2>La condición se cumple</h2>)
  : (<h2>La condición no se cumple</h2>)*/

export default App;
