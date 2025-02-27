import axios from "axios";

const API_URL = "http://localhost:8080/api/v1"; // URL de la API

const http_tutorials = axios.create({
    baseURL: API_URL,
    headers: {
        "Content-Type": "application/json",
    },
});

export default http_tutorials;
