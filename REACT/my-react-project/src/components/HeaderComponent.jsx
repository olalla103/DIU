import "./HeaderComponent.css";

function HeaderComponent(props) {
    return (
        <header className="header">
            <h1 className="title">Bienvenidos!</h1>
            <nav>
                <ul className="header-list">
                    <li>
                        <a href="#" className="link">Home</a>
                    </li>
                    <li>
                        <a href="#" className="link">Blog</a>
                    </li>
                    <li>
                        <a href="#" className="link">News</a>
                    </li>
                    <li>
                        <a href="#" className="link">Contact</a>
                    </li>

                </ul>
            </nav>
        </header>
    )
}

export default HeaderComponent;