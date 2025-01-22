import 'bootstrap/dist/css/bootstrap.min.css';
import { Table } from 'react-bootstrap';

function Tabla(props) {
    return (
        <div>
            <h2 className="text-center">Canciones</h2>
            <br />
            <Table responsive striped bordered hover size="sm">
                <thead>
                    <tr>
                        <td>Nombre del Artista</td>
                        <td>Nombre de la Canción</td>
                        <td>Letra</td>
                    </tr>
                </thead>
                <tbody className='bonito-tabla'>
                    {props.songs.map((song, index) => (
                        <tr key={index}>
                            <td>{song.artist}</td> {/* Muestra el artista */}
                            <td>{song.song}</td>   {/* Muestra la canción */}
                            <td>{song.lyrics}</td> {/* Muestra la letra */}
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
    );
}

export default Tabla;
