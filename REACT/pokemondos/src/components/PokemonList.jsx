import { useEffect, useState } from "react";
import PokemonCard from "./PokemonCard";
import axios from "axios";
import "../styles/PokemonList.css"
import GetForm from "./GetForm";

function PokemonList(props) {
  const [pokemons, setPokemons] = useState([]);

  const {selectPokemon,desde,hasta} = props;

  useEffect(() => {
    getPokemons(1,10);
  }, []);

  const fetchPokemon = async (index) => {
    const response = await axios.get(`https://pokeapi.co/api/v2/pokemon/${index}`);
    return response.data;
  };

  const getPokemons = async (desde,hasta) => {
    const pokemonArray = [];

    for (let i = desde; i <= hasta; i++) {
      const pokemon = await fetchPokemon(i);
      pokemonArray.push(pokemon);
    }

    setPokemons(pokemonArray);
  };

  const pokemonCards = pokemons.map((pokemon) => {
    return (
      <PokemonCard key={pokemon.id} pokemon={pokemon}
       selectPokemon={props.selectPokemon}/>
    );
  });

  return (
    <div>
<GetForm getPokemons={getPokemons}></GetForm>
    <ul className="pokemon-list">
      {pokemonCards}
    </ul>
    </div>
    
  );
}

export default PokemonList;