import './Funcionalidadcalculadora.css';
import { Container, Row, Col } from 'react-bootstrap';
import "bootstrap/dist/css/bootstrap.min.css";
import PropTypes from 'prop-types';

function Funcionalidadcalculadora(props) {

    const handleClick = (value) => {
        props.handleButtonClick(value);
    };

    const handleOperatorClick = (operator) => {
        props.handleOperatorClick(operator);
    };

    return (
        <div className="contenedorCalculadora">
            <Container fluid className="tablaCalculadora">
                <Row>
                    <Col className="varia" xs={12}>
                        {props.frase}
                    </Col>
                </Row>
                <Row>
                    <Col xs={3}><button className="botonAccion" onClick={props.handleBorrar}>AC</button></Col>
                    <Col xs={3}><button className="botonAccion" onClick={props.handlePosNeg}>+/-</button></Col>
                    <Col xs={3}><button className="botonAccion" onClick={() => handleOperatorClick('%')}>%</button></Col>
                    <Col xs={3}><button className="botonAccion" onClick={() => handleOperatorClick('/')}>÷</button></Col>
                </Row>
                <Row>
                    <Col xs={3}><button onClick={() => handleClick('7')}>7</button></Col>
                    <Col xs={3}><button onClick={() => handleClick('8')}>8</button></Col>
                    <Col xs={3}><button onClick={() => handleClick('9')}>9</button></Col>
                    <Col xs={3}><button className="botonAccion" onClick={() => handleOperatorClick('*')}>X</button></Col>
                </Row>
                <Row>
                    <Col xs={3}><button onClick={() => handleClick('4')}>4</button></Col>
                    <Col xs={3}><button onClick={() => handleClick('5')}>5</button></Col>
                    <Col xs={3}><button onClick={() => handleClick('6')}>6</button></Col>
                    <Col xs={3}><button className="botonAccion" onClick={() => handleOperatorClick('-')}>-</button></Col>
                </Row>
                <Row>
                    <Col xs={3}><button onClick={() => handleClick('1')}>1</button></Col>
                    <Col xs={3}><button onClick={() => handleClick('2')}>2</button></Col>
                    <Col xs={3}><button onClick={() => handleClick('3')}>3</button></Col>
                    <Col xs={3}><button className="botonAccion" onClick={() => handleOperatorClick('+')}>+</button></Col>
                </Row>
                <Row>
                    <Col xs={6}><button className="botonAncho" onClick={() => handleClick('0')}>0</button></Col>
                    <Col xs={3}><button className="botonPunto" onClick={() => handleClick('.')}>.</button></Col>
                    <Col xs={3}><button className="botonAccion" onClick={props.handleOperacion}>=</button></Col>
                </Row>
            </Container>
        </div>
    );
}

Funcionalidadcalculadora.propTypes = {
    handleBorrar: PropTypes.func.isRequired,
    handlePosNeg: PropTypes.func.isRequired,
    handleOperacion: PropTypes.func.isRequired,
    handleButtonClick: PropTypes.func.isRequired,
    handleOperatorClick: PropTypes.func.isRequired,
    frase: PropTypes.string.isRequired,
};

export default Funcionalidadcalculadora;