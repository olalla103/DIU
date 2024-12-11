import "./HeaderComponent.css";

function HeaderComponent(props) {
    return (
        <header className="Header">
            <h1 className="title">Bienvenidos!</h1>
            <nav>
                <ul className="header-list">
                    <li>
                        <a href="#">Home</a>
                        <li>
                            <a href="#">Blog</a>
                        </li>
                        <li>
                            <a href="#">News</a>
                        </li>
                        <li>
                            <a href="#">Contact</a>
                        </li>
                    </li>
                </ul>
            </nav>
        </header>
    )
}

export default HeaderComponent;