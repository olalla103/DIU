import { useContext } from 'react';
import { ProgressContext } from '../Providers/ProgressContext.jsx'; // Importar el contexto

function ProgressBar() {
    const { progress } = useContext(ProgressContext); // Usar el contexto

    return (
        <div className="progress mb-3">
            <div 
                className="progress-bar progress-bar-striped progress-bar-animated bg-primary" 
                role="progressbar" 
                style={{ width: `${progress * 20}%` }}
                aria-valuenow={progress} 
                aria-valuemin="0" 
                aria-valuemax="100"
            >
            </div>
        </div>
    );
}

export default ProgressBar;
