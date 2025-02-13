
function Login(props) {

    // Se crea un objeto con las dos propiedades
    const user ={
        username: "Olalla",
        email:"olallalnc@gmail.com"
    }

    // hago un console log de la propiedad username del objeto user
    const handleClick = () => {
        console.log(user.username);
        props.handleLogin(user);
    }

  return (
    <section>
        <h2>Login Section</h2>
        {/* Se crea un botón que al hacer click, se ejecuta la función handleClick */}
        <button onClick={handleClick}>Login</button>
    </section>
  )
}

export default Login