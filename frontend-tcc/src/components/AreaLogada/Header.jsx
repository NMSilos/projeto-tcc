import { Link } from "react-router-dom";
import "./AreaLogada.css";

export default function Header() {
  return (
    <header className="header">
      <div className="header-container">
        <h1 className="logo">AppTCC</h1>
        <nav className="nav-links">
          <Link to="/perfil">Perfil</Link>
          <button className="sair-btn" onClick={() => {
            localStorage.removeItem("token");
            window.location.href = "/";
          }}>
            Sair
          </button>
        </nav>
      </div>
    </header>
  );
}