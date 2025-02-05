import React, { useState, useEffect } from 'react';
import { Button, Col, Row, Form } from 'react-bootstrap';
import TutorialDataService from "../services/tutorial.service";
import './edit_tutorial.component.css';

function EditTutorialComponent(props) {
    const [tutorial, setTutorial] = useState({ id: "", nombre: "", descripcion: "", publicado: false });

    useEffect(() => {
        if (props.id) {
            TutorialDataService.get(props.id)
                .then(response => {
                    setTutorial({
                        id: response.data.id,
                        nombre: response.data.title,
                        descripcion: response.data.description,
                        publicado: response.data.published,
                    });
                })
                .catch(e => {
                    console.log(e);
                });
        }
    }, [props.id]);

    const enviarDatos = (e) => {
        e.preventDefault();

        if (!tutorial.id) {
            console.error("El ID del tutorial no está definido");
            return;
        }

        TutorialDataService.update(tutorial.id, {
            title: tutorial.nombre,
            description: tutorial.descripcion,
            published: tutorial.publicado
        })
        .then(response => {
            console.log("Tutorial actualizado:", response.data);
        })
        .catch(e => {
            console.log(e);
        });
    };

    return (
        <div className="container">
            <h1>Editar Tutorial</h1>
            <Form onSubmit={enviarDatos}>
                <Row>
                    <Col sm={5}>
                        <Form.Group>
                            <Form.Label>Título</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Título del tutorial"
                                value={tutorial.nombre}
                                onChange={(e) => setTutorial({ ...tutorial, nombre: e.target.value })}
                                className="form-control"
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
                                placeholder="Descripción del tutorial"
                                value={tutorial.descripcion}
                                onChange={(e) => setTutorial({ ...tutorial, descripcion: e.target.value })}
                                className="form-control"
                            />
                        </Form.Group>
                    </Col>
                </Row>
                <br />
                <Row>
                    <Col sm={1}>
                        <Form.Group>
                            <Form.Label>Publicado</Form.Label>
                            <Form.Check
                                type="checkbox"
                                checked={tutorial.publicado}
                                onChange={(e) => setTutorial({ ...tutorial, publicado: e.target.checked })}
                                className="form-control"
                            />
                        </Form.Group>
                    </Col>
                </Row>
                <br />
                <Row>
                    <Col sm={5}>
                        <Button variant="primary" type="submit" className="btn-primary">
                            Guardar
                        </Button>
                    </Col>
                </Row>
            </Form>
        </div>
    );
}

export default EditTutorialComponent;