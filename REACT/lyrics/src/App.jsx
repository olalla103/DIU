import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Formulario from './components/Formulario';
import Tabla from './components/Tabla';
import { useState } from 'react';

function App() {
  const [songs, setSongs] = useState([]);

  // Busca la letra de una canción en la API
  const fetchLyrics = async (artist, song) => {
    try {
      const response = await fetch(`https://api.lyrics.ovh/v1/${artist}/${song}`);
      const data = await response.json();
      if (data.lyrics) {
        setSongs((prevSongs) => [
          ...prevSongs,
          { artist, song, lyrics: data.lyrics }
        ]);
      }
    } catch (error) {
      console.error('Error fetching lyrics:', error);
    }
  };

  // Maneja el envío del formulario
  const handleSubmit = (artist, song) => {
    fetchLyrics(artist, song);
  };

  return (
    <div>
      <h1 className='text-center'>Buscador de canciones</h1>
      <br />
      <Formulario handleSubmit={handleSubmit} />
      <br /><br />
      <Tabla songs={songs} />
    </div>
  );
}

export default App;
