import './Funcionalidadcalculadora.css'

function Funcionalidadcalculadora(props) {

    return (
        <div className="contenedorCalculadora">
            <table className="tablaCalculadora">
                <tbody>
                    <tr>
                        <td colSpan={5} className="varia">{props.numero}</td>
                    </tr>
                    <tr>
                        <td><button>AC</button></td>
                        <td><button>+/-</button></td>
                        <td><button>%</button></td>
                        <td><button className="botonAccion">÷</button></td>
                    </tr>
                    <tr>
                        <td><button>7</button></td>
                        <td><button>8</button></td>
                        <td><button>9</button></td>
                        <td><button className="botonAccion">X</button></td>
                    </tr>
                    <tr>
                        <td><button>4</button></td>
                        <td><button>5</button></td>
                        <td><button>6</button></td>
                        <td><button className="botonAccion">-</button></td>
                    </tr>
                    <tr>
                        <td><button>1</button></td>
                        <td><button>2</button></td>
                        <td><button>3</button></td>
                        <td><button className="botonAccion">+</button></td>
                    </tr>
                    <tr>
                        <td colSpan={2}><button className="botonAncho">0</button></td>
                        <td><button>.</button></td>
                        <td><button className="botonAccion" onClick={props.numero}>=</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}

export default Funcionalidadcalculadora;
