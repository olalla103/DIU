import { Component } from 'react';
import './App.css';
import Contador from './components/Contador';

class App extends Component {
  constructor() {
    super();
    this.state = {
      counter: 0,
    };
  }

  // Método para incrementar el contador
  incrementar = () => {
    this.setState((prevState) => ({
      counter: prevState.counter + 1,
    }));
  };

  // Método para decrementar el contador
  decrementar = () => {
    this.setState((prevState) => ({
      counter: prevState.counter - 1,
    }));
  };

  // Método para resetear el contador
  resetear = () => {
    this.setState({ counter: 0 });
  };

  render() {
    const clase = "incButton";

    return (
      <div className="App">
        <Contador
          contador={this.state.counter}
          incrementar={this.incrementar}
          decrementar={this.decrementar}
          resetear={this.resetear}
          clase={clase}
        />
      </div>
    );
  }
}

export default App;
