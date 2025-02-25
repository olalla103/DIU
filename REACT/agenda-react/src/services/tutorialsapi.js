import http_tutorials from "./http_commonsTutorials";

class TutorialDataService {
  getAll() {
    return http_tutorials.get("/tutorials");
  }

  get(id) {
    return http_tutorials.get(`/tutorials/${id}`);
  }

  create(data) {
    return http_tutorials.post("/tutorials", data);
  }

  update(id, data) {
    return http_tutorials.put(`/tutorials/${id}`, data);
  }

  delete(id) {
    return http_tutorials.delete(`/tutorials/${id}`);
  }

  deleteAll() {
    return http_tutorials.delete("/tutorials");
  }
}

export default new TutorialDataService();
