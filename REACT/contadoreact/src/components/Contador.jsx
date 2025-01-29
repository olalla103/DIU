import "./Contador.css"

function Contador(props) {

    return (
        <div className="container-counter">
            <div className="counter">
                <h1>
                    {props.numero}
                </h1>
            </div>
            <div className="buttons">
                <button onClick={props.incrementar} className="incButton">
                    Incrementar
                </button>

                <button onClick={props.decrementar}>
                    Decrementar
                </button>

                <button onClick={props.resetear}>
                    Resetear
                </button>
            </div>
        </div>
    )
}

export default Contador;