import { useEffect } from "react"

function MovieList() {

    const movies = ["Matilda", "Cónclave", "La la land"]

    const HTMLMovie = movies.map((movie,index) =>{
        return <p key={movie}> {index+1} - {movie} </p>
    })

    useEffect(()=>{
      console.log("MovieList mounted")
    }),[]

    useEffect(()=>{
      return () =>{
        console.log("MovieList unmounted")
      }
    }),[]

  return (
    <section>
    <h2>Movies</h2>
    {HTMLMovie}
    </section> 
  )
}

export default MovieList