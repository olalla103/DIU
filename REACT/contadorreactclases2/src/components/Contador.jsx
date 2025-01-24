import { Component } from 'react';
import './Contador.css';

class Contador extends Component {
    render() {
        return (
            <div className="container-counter">
                <div className="counter">
                    <h1>Contador</h1>
                    <h1>{this.props.contador}</h1> {/* Mostrar el valor del contador */}
                </div>
                <div className="buttons">
                    <button className={this.props.clase} onClick={this.props.incrementar}>+</button>
                    <button onClick={this.props.decrementar}>-</button>
                    <button onClick={this.props.resetear}>Reset</button>
                </div>
            </div>
        );
    }
}

export default Contador;
