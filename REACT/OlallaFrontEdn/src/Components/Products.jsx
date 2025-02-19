import React, { useState, useEffect, useContext } from 'react';
import ProductsDataService from '../Services/products.service.js';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { ProgressContext } from '../Providers/ProgressContext.jsx';

function Bolsos() {
    const [products, setProducts] = useState([]);
    const [currentProduct, setCurrentProduct] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);

    const { setProgress } = useContext(ProgressContext);

    const setActiveProduct = (product, index) => {
        setCurrentProduct(product);
        setCurrentIndex(index);
    };

    useEffect(() => {
        retrieveProducts();
    }, []);

    const retrieveProducts = () => {
        ProductsDataService.getAll()
            .then(response => {
                setProducts(response.data);
                console.log(response.data);
                setProgress(response.data.length);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const handleDelete = (id, event) => {
        event.preventDefault(); // Evita que el link recargue la página
        ProductsDataService.delete(id)
            .then(() => {
                retrieveProducts(); // Refresca la lista después de eliminar
                setCurrentProduct(null); // Limpia la selección
            })
            .catch((e) => {
                console.log(e);
            });
    };

    return (
        <div className="container mt-4">
            <h2 className="text-center mb-4">Lista de Bolsos</h2>

            <div className="row">
                {/* Lista de Bolsos */}
                <div className="col-md-6">
                    <ul className="list-group">
                        {products && products.length > 0 ? (
                            products.map((product, index) => (
                                <li
                                    key={index}
                                    className={`list-group-item d-flex justify-content-between align-items-center 
                                        ${index === currentIndex ? 'list-group-item-primary' : ''}`}
                                    onClick={() => setActiveProduct(product, index)}
                                >
                                    <span>{product.name}</span>
                                </li>
                            ))
                        ) : (
                            <p className="text-center">No hay productos disponibles</p>
                        )}
                    </ul>
                </div>

                {currentProduct ? (
                    <div className="col-md-6">
                        <div className="border p-3">
                            <h4 className="text-center">Detalles del Producto</h4>
                            <p><strong>Stock:</strong> {currentProduct.stock}</p>
                            <p><strong>Price:</strong> {currentProduct.price} €</p>

                            {/* Botón para volver a la lista */}
                            <button className="btn btn-secondary" onClick={() => setCurrentProduct(null)}>
                                Volver a la lista
                            </button>

                            {/* Enlace para Editar */}
                            <Link to={"/editar/" + currentProduct.id} className="nav-link text-dark">Editar</Link> 

                            {/* Botón para Eliminar */}
                            <Link to="#" className="nav-link text-danger" onClick={(e) => handleDelete(currentProduct.id, e)}>
                                Eliminar
                            </Link> 
                        </div>
                    </div>
                ) : (
                    <div className="col-md-6">
                        <div className="border p-3">
                            <h4 className="text-center">Pulsa en un Producto</h4>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}

export default Bolsos;
