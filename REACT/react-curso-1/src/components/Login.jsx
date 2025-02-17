function Login(props) {

  // Se crea un objeto con las dos propiedades
  const user = {
      username: "",
      email: ""
  };

  // hago un console log de la propiedad username del objeto user
  const handleSubmit = (e) => {
      e.preventDefault();
      console.log(user);
      props.handleLogin(user);
  };

  const setUsername = (e) => {
      user.username = e.target.value;
  };

  const setUseremail = (e) => {
      user.email = e.target.value;
  };

  return (
      <section>
          <h2>Login Section</h2>
          {/* Se crea un botón que al hacer click, se ejecuta la función handleClick */}
          <form onSubmit={handleSubmit}>

              <fieldset>
                  <input
                      id="nombre_usuario"
                      type="text"
                      placeholder="Introduzca el usuario"
                      onChange={setUsername} />
                  <label htmlFor="username">Username</label>
              </fieldset>

              <fieldset>
                  <input
                      id="email"
                      type="text"
                      placeholder="Introduzca el email"
                      onChange={setUseremail} 
                      />
                  <label htmlFor="email">Email</label>
              </fieldset>

              <br /><br />

              <button>Login</button>
          </form>
      </section>
  );
}

export default Login;