import axios from "axios";

const API_URL = "http://localhost:8090/api/v1/person"; // URL de la API
const api = axios.create({
    baseURL: API_URL,
    headers: {
        "Content-Type": "application/json",
    },
});

export default api;
