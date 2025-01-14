import './Funcionalidadcalculadora.css';
import { Container, Row, Col } from 'react-bootstrap';
import "bootstrap/dist/css/bootstrap.min.css";

function Funcionalidadcalculadora() {
    return (
        <div className="contenedorCalculadora">
            <Container fluid className="tablaCalculadora">
                <Row>
                    <Col className="varia" xs={12}>
                        0
                    </Col>
                </Row>
                <Row>
                    <Col xs={3}><button>AC</button></Col>
                    <Col xs={3}><button>+/-</button></Col>
                    <Col xs={3}><button>%</button></Col>
                    <Col xs={3}><button className="botonAccion">÷</button></Col>
                </Row>
                <Row>
                    <Col xs={3}><button>7</button></Col>
                    <Col xs={3}><button>8</button></Col>
                    <Col xs={3}><button>9</button></Col>
                    <Col xs={3}><button className="botonAccion">X</button></Col>
                </Row>
                <Row>
                    <Col xs={3}><button>4</button></Col>
                    <Col xs={3}><button>5</button></Col>
                    <Col xs={3}><button>6</button></Col>
                    <Col xs={3}><button className="botonAccion">-</button></Col>
                </Row>
                <Row>
                    <Col xs={3}><button>1</button></Col>
                    <Col xs={3}><button>2</button></Col>
                    <Col xs={3}><button>3</button></Col>
                    <Col xs={3}><button className="botonAccion">+</button></Col>
                </Row>
                <Row>
                    <Col xs={6}><button className="botonAncho">0</button></Col>
                    <Col xs={3}><button>.</button></Col>
                    <Col xs={3}><button className="botonAccion">=</button></Col>
                </Row>
            </Container>
        </div>
    );
}

export default Funcionalidadcalculadora;
