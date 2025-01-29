import './App.css'
import PokemonList from './components/PokemonList'
import PokemonDetails from './components/PokemonDetalis';
import { useState } from 'react';

function App() {
  const [selectedPokemon, setSelectedPokemon] = useState();

  return (
    <>
      {selectedPokemon && (
        <div>
          <h2>Pokemon Seleccionado</h2>
          <PokemonDetails pokemon={selectedPokemon}></PokemonDetails>

        </div>
      )}

      <h2>Lista de Pokemons</h2>
      <PokemonList selectPokemon={setSelectedPokemon}></PokemonList>
    </>
  )
}

export default App
