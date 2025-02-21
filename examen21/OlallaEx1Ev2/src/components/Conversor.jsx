import React, { useState,useEffect,useContext } from 'react'
import StockProgressBar from "./StockProgressBar"; // Importamos la barra de progreso
import { useParams } from 'react-router-dom';
import ProductsDataService from '../services/api.js';
import "bootstrap/dist/css/bootstrap.min.css";
import { ProgresoContext } from "../providers/ContextoProgressBar";


  /*Mostrar barra de progreso con reserva stock */
function Conversor() {

        const [cantidad,setCantidad] = useState('');
        const [equivalenciaEuros,setEquivalenciaEuros] = useState('');
        const [price,setPrice] = useState('');
        const [brand,setBrand] = useState('');
        const [active,setActive] = useState('');
        const { id }=useParams();
        const { setProgreso, setStock, stock, MAX_STOCK } = useContext(ProgresoContext);

        useEffect(() => {
            ProductsDataService.getAll() 
                .then((response) => {
                    const filteredProduct = response.data.find(product => product.id.toString() === id); 
                    
                    if (filteredProduct) {
                        setActive(filteredProduct.active)
                        setBrand(filteredProduct.brand)
                        setPrice(filteredProduct.price);
                        setStock(filteredProduct.stock);
                    } else {
                        console.log(`No se encontró un producto con el ID: ${id}`);
                    }
                })
                .catch((e) => {
                    console.log("Error al obtener los productos:", e);
                });
        }, [id]);
    
        const handleChange = (e) => {
            setCantidad(e.target.value);
        };

        const handleSubmit = async (e) => {
            e.preventDefault();
    
            const cantidadNumerica = parseInt(cantidad, 10);

            if (cantidadNumerica < 0) {
                alert("Por favor, ingrese una cantidad válida mayor a 0.");
                return;
            }
            if (cantidadNumerica > stock) {
                alert(`Total de unidades en stock: ${stock}.`);
                return;
            }
            
            const total = price * cantidadNumerica;

            setEquivalenciaEuros(total);
            console.log(total);
            
        };

        const handleSubmit2 = async (e) =>{
            e.preventDefault();
            aumentaStock();
        }

        const aumentaStock = () =>{
            let nuevoStock = stock +10;
            console.log(nuevoStock);
            setStock(nuevoStock);
            setProgreso(stock);
        }
    

  return (
    <div>
        <div>
        <StockProgressBar/>
        <br />
        {stock < 100 ? (
            <div>
                <form onSubmit={handleSubmit2}>
                    <div className='row'>
                        <label className='form-label color'><strong className='color'>Reserva NO llena</strong></label>
                    </div>
                    <div className='row'>
                        <button className='btn btn-success' onSubmit={aumentaStock} disabled={stock>=100 || !active}>Añadir</button>
                    </div>
                </form>
            </div>
        ):(
            <form>
                    <div className='row'>
                        <label className='form-label color'><strong className='colorLlena'>Reserva llena</strong></label>
                    </div>
                    <div className='row'>
                        <button className='btn btn-danger' onClick={aumentaStock} disabled>Añadir</button>
                    </div>
            </form>
        )}
        </div>
    <br />

        <div className="container mt-5">
            <form onSubmit={handleSubmit}>
                <h2 className="text-center text-dark mb-4">Conversor {brand}</h2>
                
                <div className="row">
                    <div className='col-md-6'>
                        <div className='card p-3' style={{ backgroundColor: "#fce4ec" }}>
                            <label className='form-label'>Cantidad</label>
                            <input 
                                type="number" 
                                className='form-control' 
                                value={cantidad}
                                onChange={handleChange}
                                min={1}
                                disabled={!active}
                                required/>
                        </div>
                    </div>
                    <div className='col-md-6'>
                        <div className='card p-3' style={{ backgroundColor: "#fce4ec" }}>
                            <label className='form-label'>Equivalencia en Euros</label>
                            <input 
                                type="number" 
                                className='form-control' 
                                value={equivalenciaEuros}
                                disabled />
                        </div>
                        <br />
                    </div>
                   <div className='row'>
                        <div className='col-md-12'>
                        <button className='btn btn-success' onSubmit={handleSubmit}>Convertir</button>
                        </div>
                    </div>
            </div>
            </form>
        </div>
    </div>
    
  );
}

export default Conversor