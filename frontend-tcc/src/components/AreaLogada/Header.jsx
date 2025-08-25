import { Link, useNavigate, useParams } from "react-router-dom";
import "./AreaLogada.css";
import { useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";

export default function Header() {

  const [user, setUser] = useState();
  const [role, setRole] = useState();
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
    setRole(data.role);
  }, []);

  if (role === "ADMIN") {
    return (
      <aside className="sidebar">
        <h2 className="sidebar-logo">Painel Admin</h2>
        <nav className="sidebar-links">
          <Link to="/admin/dashboard">Dashboard</Link>
          <Link to="/admin/livros">Gerenciar Livros</Link>
          <Link to="/admin/sugestoes">Gerenciar Sugestões</Link>
          <button
            className="sair-btn"
            onClick={() => {
              localStorage.removeItem("token");
              window.location.href = "/";
            }}
          >
            Sair
          </button>
        </nav>
      </aside>
    );
  }

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