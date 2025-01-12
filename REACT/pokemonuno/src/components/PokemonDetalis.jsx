import PropTypes from 'prop-types';
import './PokemonDetails.css';

function PokemonDetails(props) {
    const { pokemon } = props;

    // Validar si el prop pokemon existe
    if (!pokemon) {
        return <p className="text">No Pokémon selected</p>;
    }

    return (
        <section className="selected-pokemon">
            <div className="pokemon-container">
                <h2 className="text">
                    {pokemon.name}
                </h2>
                {pokemon.sprites?.front_default ? (
                    <img
                        src={pokemon.sprites.front_default}
                        alt={`${pokemon.name} sprite`}
                        className="pokemon-img"
                    />
                ) : (
                    <p className="text">No image available</p>
                )}
                <h3 className="text">HP: {pokemon.stats?.[0]?.base_stat || "N/A"}</h3>
            </div>
        </section>
    );
}

// Validación de las props con PropTypes
PokemonDetails.propTypes = {
    pokemon: PropTypes.shape({
        name: PropTypes.string.isRequired,
        sprites: PropTypes.shape({
            front_default: PropTypes.string,
        }).isRequired,
        stats: PropTypes.arrayOf(
            PropTypes.shape({
                base_stat: PropTypes.number,
            })
        ).isRequired,
    }).isRequired,
};

export default PokemonDetails;
