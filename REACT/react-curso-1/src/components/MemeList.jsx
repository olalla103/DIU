import { useEffect, useState } from "react";
import "./MemeList.css"
import axios from "axios";

function MemeList() {

    const [memes,setMemes] = useState([]);

    // useEffect(() => {
    //     fetch("https://api.imgflip.com/get_memes")
    //         // pasamos la respuesta a algo que react entienda como es un JSON
    //         .then((response) => response.json())
    //         .then((data) => {
    //             console.log(data.data.memes);
    //             setMemes(data.data.memes)
    //         });
    // }, []);

    // axios es lo mismo pero no tenemos que realizar la conversión a JSON
    useEffect(()=>{
        axios.get("https://api.imgflip.com/get_memes").then((response) => {
        console.log(response.data);
        setMemes(response.data.data.memes);
    })
    },[])

    const HTMLMeme = memes.map((meme) => {
        // renderizado de listas
        return (
        <li key={meme.id} className="meme-item">
            <h2>{meme.name}</h2>
            <img src={meme.url} alt="meme img" className="meme-img"/>
        </li>
        )
    });

    return (
        <ul className="meme-list">
            {HTMLMeme}
        </ul>
    );
}

export default MemeList;