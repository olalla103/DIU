import "./HeaderComponent.css";

function HeaderComponent(props) {
    const { greetings, links } = props;
    return (
        <header className="header">
            <h1 className="title">{greetings}</h1>
            <nav>
                <ul className="header-list">
                    <li>
                        <a href="#" className="link">{links.home}</a>
                    </li>
                    <li>
                        <a href="#" className="link">{links.blog}</a>
                    </li>
                    <li>
                        <a href="#" className="link">{links.news}</a>
                    </li>
                    <li>
                        <a href="#" className="link">{links.contact}</a>
                    </li>
                </ul>
            </nav>
        </header>
    )
}

export default HeaderComponent;