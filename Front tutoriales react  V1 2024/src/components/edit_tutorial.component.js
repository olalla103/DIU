import React, { useState, useEffect, use } from 'react';
import { Button, Col, Row, Form, FormGroup } from "react-bootstrap";

function TutorialComponent(props) {
    const [nombre, setNombre] = useState('');
    const [descripcion, setDescripcion] = useState('');
    const [publicado, setPublicado] = useState(false);

    useEffect(() => {
        setNombre(props.nombre);
        setDescripcion(props.descripcion);
        setPublicado(props.publicado);
    }, [props.nombre, props.descripcion, props.publicado]);

    const enviarDatos = (e) => {
        e.preventDefault();
        // Handle form submission
    };

    return (
        <div>
            <div className="header">
                <h1>Edición tutorial</h1>
            </div>
            <div className="body">
                <FormGroup>
                    <Form onSubmit={enviarDatos}>
                        <Row>
                            <Col sm={5}>
                                <Form.Group>
                                    <Form.Label>Título del formulario</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder={useEffect.nombre}
                                        value={nombre}
                                        onChange={(e) => setNombre(e.target.value)}
                                    />
                                </Form.Group>
                            </Col>
                        </Row>
                        <br />
                        <Row>
                            <Col sm={8}>
                                <Form.Group>
                                    <Form.Label>Descripción</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder={useEffect.descripcion}
                                        value={descripcion}
                                        onChange={(e) => setDescripcion(e.target.value)}
                                    />
                                </Form.Group>
                            </Col>
                        </Row>
                        <br />
                        <Row>
                            <Col sm={1}>
                                <Form.Group>
                                    <Form.Label>Publicado</Form.Label>
                                    <Form.Control
                                        type="checkbox"
                                        checked={useEffect.publicado}
                                        onChange={(e) => setPublicado(e.target.checked)}
                                    />
                                </Form.Group>
                            </Col>
                        </Row>
                        <br />
                        <Row>
                            <Col sm={5}>
                                <Button variant="primary" type="submit">
                                    Enviar
                                </Button>
                            </Col>
                        </Row>
                    </Form>
                </FormGroup>
            </div>
        </div>
    );
}

export default TutorialComponent;
