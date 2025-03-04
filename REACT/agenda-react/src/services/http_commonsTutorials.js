import axios from "axios";

const API_URL = "http://agenda5-env.eba-5tciptpb.us-east-1.elasticbeanstalk.com:8080/api/v1"; // URL de la API

const http_tutorials = axios.create({
    baseURL: API_URL,
    headers: {
        "Content-Type": "application/json",
    },
});

export default http_tutorials;
