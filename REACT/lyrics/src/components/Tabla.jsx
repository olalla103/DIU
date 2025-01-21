import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-bootstrap';

function Tabla() {

    return (
        <div>
            <h2 className='text-center'>Canciones</h2>
            <div className="container">
                <div className="row border border-dark">
                    <div className="col border border-dark">Nombre Canción</div>
                    <div className="col border border-dark">Artista</div>
                    <div className='col border border-dark'>Letra</div>
                </div>
                <div className="row border border-dark">
                    <div className="col border border-dark">Canción 1</div>
                    <div className="col border border-dark">Artista 1</div>
                    <div className="col border border-dark">Letra 1</div>
                </div>
                <div className="row border border-dark">
                    <div className="col border border-dark">Canción 2</div>
                    <div className="col border border-dark">Artista 2</div>
                    <div className="col border border-dark">Letra 2</div>
                </div>
            </div>
        </div>
    );
}

export default Tabla;