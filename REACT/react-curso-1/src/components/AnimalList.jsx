import React from 'react'

const animals = [
   { id: 1,
    name: "dog",
    img:""},
    {
        id:2,
        name:"cat",
        img:""
    },
    {
        id:3,
        name:"rabbit",
        img:""
    }
]

const HTMLAnimal = animals.map((animal) =>{
    return <li key={animal.id}>{animal.id} - {animal.name}</li>
} )

function AnimalList() {
  return (
    <section>
        <h2>Animals:</h2>
        <ul>
            {HTMLAnimal}
        </ul>
    </section>
  )
}

export default AnimalList