import "./App.css";
import HeaderComponent from "./components/HeaderComponent";
import { useState, useEffect } from "react";
//import Login from "./components/Login";
//import MovieList from "./components/MovieList";
//import AnimalList from "./components/AnimalList";
import MemeList from "./components/MemeList";

function App() {




  // Esto lo he creado yo, suma la variable number, que la he hecho reactiva
  // porque también hay variables estáticas
  const [greetings]=useState("Bienvenidos a mi web"); 

  

  const links={
    meme: "meme",
    blog: "Blog",
    news:"News",
    contact: "Contact"  
  }

  const [user, setUser] = useState({});

  const login =(userInfo)=>{
    setUser(userInfo);
  }

  
//   useEffect(()=>{
//     console.log("Ejecución cada vez que se renderiza el componenente raíz.")
//   })

//  useEffect(()=>{
//    console.log("Ejecución con cada cambio de la variable reactiva user.")
//  },[user])

  const sayHello = () => {
    console.log("Hola a todos");
  };

  const handleChange = (e) => {
    console.log(e);
  };

  const [showMovies,setShowMovies] = useState(true);

  const conditions = true; 

  return (
    <>
      <HeaderComponent greetings={greetings} links={links} />
      <main className="main-content">
        <MemeList></MemeList>
      </main>
    </>
  );
}

/*conditions ? (<h2>La condición se cumple</h2>)
  : (<h2>La condición no se cumple</h2>)
  

  <h2>Películas</h2>
          <button onClick={()=> setShowMovies(!showMovies)}>Toggle Movies</button>
          {showMovies ? <MovieList></MovieList>: null} {/* uso de renderizado condicional}
          <AnimalList></AnimalList>

          {user.username && <h2 onClick={sayHello}>Hola {user.username}</h2>}
        {/*Se pasa por props la función que se ha hecho en el componente login
        En la función login, encontramos un setUser que coge el usuario del componente }
        <Login handleLogin={login}></Login>
        <MemeList></MemeList>
  */

export default App;
