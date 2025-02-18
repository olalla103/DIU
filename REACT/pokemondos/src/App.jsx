import { useState } from 'react';
import './App.css';
import PokemonList from './components/PokemonList';
import PokemonDetails from './components/PokemonDetails';
import GetForm from './components/GetForm';

function App() {
  const [selectedPokemon, setSelectedPokemon] = useState();

  return (
    <>
      <h1>Proyecto Pokemon</h1>
      {selectedPokemon ? (
        <div>
          <h2>Pokemon Detail</h2>
          <PokemonDetails pokemon={selectedPokemon} />
        <br /><br />
        </div>
        
      ) : (
        <p>Loading...</p>
      )}

      <PokemonList selectPokemon={setSelectedPokemon} />
    </>
  );
}

export default App;
