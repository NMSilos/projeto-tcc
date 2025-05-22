import { Link, useParams } from "react-router-dom";
import "./AreaLogada.css";

export default function Header() {

  const { user } = useParams();

  return (
    <header className="header">
      <div className="header-container">
        <div className="esquerda">
          <h1 className="logo">AppTCC</h1>
        </div>
        <div className="centro">
          <input type="text" className="barra-pesquisa" placeholder="🔍︎ Pesquisar" />
        </div>
        <div className="direita">
          <nav className="nav-links">
            <Link to={`/perfil/${user}`}>Perfil</Link>
            <button className="sair-btn" onClick={() => {
              localStorage.removeItem("token");
              window.location.href = "/";
            }}>
              Sair
            </button>
          </nav>
        </div>
      </div>
    </header>
  );
}