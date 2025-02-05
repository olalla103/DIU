import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import TutorialDataService from "./services/tutorial.service";

// Componente para añadir un tutorial
import AddTutorial from "./components/add_tutorial.component";
// Componente para editar tutorial
import EditTutorial from "./components/edit_tutorial.component";
import TutorialsList from "./components/tutorials-list.component";

class App extends Component {
  addTutorial = (titulo, descripcion, publicado) => {
    const data = {
      title: titulo,
      description: descripcion,
      published: publicado,
    };

    TutorialDataService.create(data)
      .then((response) => {
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/tutorials"} className="navbar-brand">
            Tutoriales
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/tutorials"} className="nav-link">
                Tutorials
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/add"} className="nav-link">
                Add
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/tutorials"]} component={TutorialsList} />
            <Route
              exact
              path="/add"
              render={(props) => (
                <AddTutorial {...props} addTutorial={this.addTutorial} />
              )}
            />
            <Route
              path="/tutorials/:id"
              render={(props) => {
                const id = props.match.params.id;
                return <EditTutorial {...props} id={id} />;
              }}
            />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;