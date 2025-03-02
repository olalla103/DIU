import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import {PersonasProvider} from "./context/PersonasContext.jsx"
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <PersonasProvider>
    <App />
    </PersonasProvider>
  </StrictMode>,
)
