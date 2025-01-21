import 'bootstrap/dist/css/bootstrap.min.css';
import './Formulario.css'

function Formulario() {
    return (
        <div className="formulario">
            <h1>Formulario</h1>
            <textarea name="nombreCancion" id="nombreCancion" placeholder="Nombre Cancion"></textarea>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <textarea name="nombreArtista" id="artista" placeholder="Nombre Artista"></textarea>
            <br />
            <button className='botonBusqueda'>Buscar</button>
        </div>
    )
}

export default Formulario