import http from "./http_common";

class BolsosDataService {
  getAll() {
    return http.get("/Bolsos");
  }

  get(id) {
    return http.get(`/Bolsos/${id}`);
  }

  create(data) {
    return http.post("/Bolsos", data);
  }

  update(id, data) {
    return http.put(`/Bolsos/${id}`, data);
  }

  delete(id) {
    return http.delete(`/Bolsos/${id}`);
  }

  deleteAll() {
    return http.delete("/Bolsos");
  }
}

export default new BolsosDataService();