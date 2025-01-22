import 'bootstrap/dist/css/bootstrap.min.css';
import './Formulario.css';
import { Button, Col, Row, Form, FormGroup } from 'react-bootstrap';
import { useState } from 'react';

function Formulario(props) {
    const [artist, setArtist] = useState(''); // Nombre del artista
    const [song, setSong] = useState('');   // Nombre de la canción

    // Enviar los datos del formulario al componente padre
    const onSubmit = (e) => {
        e.preventDefault();
        props.handleSubmit(artist, song);
        setArtist(''); // Limpia el campo del artista
        setSong('');   // Limpia el campo de la canción
    };

    return (
        <div className="formulario">
            <h1>Formulario</h1>
            <br />
            <FormGroup>
                <Form onSubmit={onSubmit}>
                    <Row>
                        <Col>
                            <Form.Group>
                                <Form.Label>Nombre del Artista</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="Escribe el nombre del artista"
                                    value={artist}
                                    onChange={(e) => setArtist(e.target.value)}
                                />
                            </Form.Group>
                        </Col>
                        <Col>
                            <Form.Group>
                                <Form.Label>Nombre de la Canción</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="Escribe el nombre de la canción"
                                    value={song}
                                    onChange={(e) => setSong(e.target.value)}
                                />
                            </Form.Group>
                        </Col>
                    </Row>
                    <br />
                    <Button className="botonBusqueda" variant="primary" type="submit">
                        Buscar
                    </Button>
                </Form>
            </FormGroup>
        </div>
    );
}

export default Formulario;
