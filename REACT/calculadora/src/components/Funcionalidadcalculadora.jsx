import './Funcionalidadcalculadora.css'

function Funcionalidadcalculadora() {
    return (
        <div className="calculator-container">
            <div className="grid">
                <button className="span-two">AC</button>
                <button>+/-</button>
                <button>%</button>
                <button className="operation">÷</button>
                <button>7</button>
                <button>8</button>
                <button>9</button>
                <button className="operation">x</button>
                <button>4</button>
                <button>5</button>
                <button>6</button>
                <button className="operation">-</button>
                <button>1</button>
                <button>2</button>
                <button>3</button>
                <button className="operation">+</button>
                <button className="span-two">0</button>
                <button>.</button>
                <button className="operation">=</button>
            </div>
        </div>
    );
}

export default Funcionalidadcalculadora;
