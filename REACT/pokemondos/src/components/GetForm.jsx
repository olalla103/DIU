import React, { useState } from 'react'

function GetForm(props) {
    const [desde,setDesde] = useState(1);
    const [hasta,setHasta] = useState(10);

    const handleFromInput = (e) =>{
        setDesde(e.target.value);
    }

    const handleToInput = (e) =>{
        setHasta(e.target.value);
    }

    const handleSubmit = (e) =>{
        e.preventDefault();
        props.getPokemons(desde, hasta);
    }


  return (
    <form onSubmit={handleSubmit}>
        <fieldset>
            <label htmlFor="desde-pokemon">From:</label>
            <input type="number" id='desde-pokemon' 
            min={1} onChange={handleFromInput}/>
        </fieldset>
        <fieldset>
            <label htmlFor="hasta-pokemon">To:</label>
            <input type="number"
            id='hasta-pokemon' 
            min={1} onChange={handleToInput}/>
        </fieldset>
        <button>Get Pokemon</button>
    </form>
  )
}

export default GetForm