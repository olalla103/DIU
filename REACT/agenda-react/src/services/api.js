import http from "../services/http_commons";

class PersonaDataService {
  getAll() {
    return http.get("/person"); // Usa minúsculas si el backend lo usa así
  }

  get(id) {
    return http.get(`/person/${id}`);
  }

  create(data) {
    return http.post("/person", data);
  }

  update(id, data) {
    return http.put(`/person/${id}`, data);
}

  delete(id) {
    return http.delete(`/person/${id}`);
  }

  deleteAll() {
    return http.delete("/person");
  }
}

export default new PersonaDataService();
