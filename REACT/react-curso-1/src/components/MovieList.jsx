function MovieList() {

    const movies = ["Matilda", "Cónclave", "La la land"]

    const HTMLMovie = movies.map((movie,index) =>{
        return <p key={movie}>{index+1} - {movie}</p>

    })
  return (
    <section>
    <h2>Movies</h2>
    {HTMLMovie}
    </section> 
  )
}

export default MovieList