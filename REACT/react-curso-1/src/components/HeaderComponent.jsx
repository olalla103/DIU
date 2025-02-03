import "./HeaderComponent.css";

function HeaderComponent() {
  return (
    <header className="header">
      <h1 className="title">Bienvenidos</h1>
      <nav>
        <ul className="header-list">
          <li>
            <a className="link" href="#">Inicio</a>
          </li>
          <li>
            <a className="link" href="#">Casa</a>
          </li>
          <li>
            <a className="link" href="#">Blog</a>
          </li>
          <li>
            <a className="link" href="#">Contáctanos</a>
          </li>
        </ul>
      </nav>
    </header>
  );
}

export default HeaderComponent;
