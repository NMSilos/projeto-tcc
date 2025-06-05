import { Link, useNavigate, useParams } from "react-router-dom";
import "./AreaLogada.css";
import { useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";

export default function Header() {

  const [user, setUser] = useState();
  const [query, setQuery] = useState("");
  const navigate = useNavigate();

  const pesquisarLivros = (e) => {
    e.preventDefault();
    if(query.trim() != "") {
      navigate(`/buscar?query=${encodeURIComponent(query)}`)
    }
  }

  useEffect(() => {
    const data = jwtDecode(localStorage.getItem("token"))
    setUser(data.sub);
  }, []);

  return (
    <header className="header">
      <div className="header-container">
        <div className="esquerda">
          <h1 className="logo">AppTCC</h1>
        </div>
        <div className="centro">
          <form className="form-pesquisa" onSubmit={pesquisarLivros}>
            <input
              type="text" 
              className="barra-pesquisa" 
              placeholder="🔍︎ Pesquisar" 
              value={query} 
              onChange={(e) => setQuery(e.target.value)} 
            />
          </form>
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