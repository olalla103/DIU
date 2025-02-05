import React from 'react';
import { Button, Col, Row, Form, FormGroup } from 'react-bootstrap';
import './add_tutorial.component.css';

function AddTutorialComponent(props) {
  const [titulo, setTitulo] = React.useState(""); // Nombre del tutorial
  const [descripcion, setDescripcion] = React.useState(""); // Descripción del tutorial
  const [publicado, setPublicado] = React.useState(false); // Estado de publicación del tutorial

  // Enviar los datos del formulario al componente padre
  const enviarDatos = (e) => {
    e.preventDefault();
    props.addTutorial(titulo, descripcion, publicado);
    setTitulo("");
    setDescripcion("");
    setPublicado(false);
  }

  return (
    <div className="container">
      <br />
      <h1>Agregar Tutorial</h1>
      <br />
      <FormGroup>
        <Form onSubmit={enviarDatos}>
          <Row>
            <Col sm={5}>
              <Form.Group>
                <Form.Label>Nombre</Form.Label>
                <Form.Control 
                  type="text" 
                  placeholder="Escriba el nombre" 
                  value={titulo}
                  onChange={(e) => setTitulo(e.target.value)}
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
                  placeholder="Escriba una pequeña descripción" 
                  value={descripcion}
                  onChange={(e) => setDescripcion(e.target.value)}
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
                <Form.Control 
                  type="checkbox"
                  checked={publicado}
                  onChange={(e) => setPublicado(e.target.checked)}
                  className="form-control"
                />
              </Form.Group>
            </Col>
          </Row>
          <br />
          <Row>
            <Col sm={5}>
              <Button variant="primary" type="submit" className="btn-primary">Enviar</Button>
            </Col>
          </Row>
        </Form>
      </FormGroup>
    </div>
  );
}

export default AddTutorialComponent;