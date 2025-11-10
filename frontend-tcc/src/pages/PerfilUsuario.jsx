import { jwtDecode } from "jwt-decode";
import { useEffect, useState } from "react";
import "./styles/PerfilUsuario.css";
import "../utils/Badges.css";
import { baseUrl, requestLogado } from '../utils/requests';
import { Link, useNavigate, useParams } from 'react-router';
import UltimasLeituras from '../components/UltimasLeituras';
import defaultUser from '../assets/default-user.jpg';
import { buscarBadge } from "../utils/Badges.jsx";
import { Bookmark, BookOpen, BookText, Trash2 } from 'lucide-react';
import { buscarStreakBadge } from "../utils/StreakBadge.jsx";

export default function PerfilUsuario() {
  const [nome, setNome] = useState();
  const [username, setUsername] = useState();
  const [imagem, setImagem] = useState();
  const [leituras, setLeituras] = useState([]);
  const [ultimasLeituras, setUltimasLeituras] = useState([]);
  const [sugestoes, setSugestoes] = useState([]);
  const [streaks, setStreaks] = useState();
  const [streakBadge, setStreakBadge] = useState(null);
  const [badge, setBadge] = useState();

  const { user } = useParams();
  const navigate = useNavigate();

  async function carregarDados() {
    const usuario = await requestLogado(`api/usuarios/buscar/username/${user}`, {}, "GET");
    setLeituras(usuario.leituras)
    setUltimasLeituras(usuario.leituras.slice().reverse().slice(0, 1))
    setStreaks(usuario.streaks)
  }


  function carregarImagem(data) {
    if (!data.imagem) {
      setImagem(defaultUser);
    } else {
      if (data.imagem.slice(0, 5) == "https") {
        setImagem(data.imagem);
      } else {
        setImagem(`${baseUrl}api/usuarios/userImage/${data.imagem}`);
      }
    }
  }

  async function carregarSugestoes() {
    const token = localStorage.getItem('token');
    const dados = jwtDecode(token);
    const response = await requestLogado(`api/sugestoes/buscar-por-usuario/${dados.id}`, {}, "GET");
    setSugestoes(response);
    setBadge(response ? buscarBadge(response) : null);
  }

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      const dados = jwtDecode(token);
      setNome(dados.nome);
      setUsername(dados.sub);
      carregarImagem(dados);
      carregarSugestoes();
    }
    carregarDados();
  }, [user]);

  useEffect(() => {
    if (streaks !== undefined && streaks !== null) {
      setStreakBadge(buscarStreakBadge(streaks));
    }
  }, [streaks]);


  return (
    <div className="perfil-container">
      <div className="perfil-topo">
        <div className="perfil-info">
          <img src={imagem} alt="Avatar" />

          <div className="perfil-detalhes">
            <p className="nome"><strong>{nome}</strong></p>
            <p className="username">{username}</p>
            <p className="total-leituras">Total Leituras: {leituras.length}</p>
          </div>

          <div className="perfil-badge">

            <div className={`badge-conquista ${badge ? badge.variant : 'sem'}`}>
              {badge ? (
                <>
                  {badge.icon}
                  <span className="badge-label">{badge.label}</span>
                </>
              ) : (
                <small>Contribua sugerindo um livro! 📚</small>
              )}
            </div>

            {streakBadge && (
              <div className={`badge-conquista ${streakBadge.variant}`}>
                {streakBadge.icon}
                <span className="badge-label">{streakBadge.label}</span>
              </div>
            )}
          </div>

          <div className="perfil-editar">
            <Link to={`/perfil/${username}/editar`}>
              <button>Editar Perfil</button>
            </Link>
            <Link to={`/perfil/${username}/sugerir-livro`}>
              <button className="btn-sugerir">Sugerir Livro</button>
            </Link>
          </div>
        </div>
      </div>

      <div className="status-leitura">
        <div className="status-item">
          <Link className='btn-listas' to={`/perfil/${username}/lidos`}>
            <BookText size={40} />
            <span>Lidos</span>
          </Link>
        </div>
        <div className="status-item">
          <Link className='btn-listas' to={`/perfil/${username}/lendo`}>
            <BookOpen size={40} />
            <span>Lendo</span>
          </Link>
        </div>
        <div className="status-item">
          <Link className='btn-listas' to={`/perfil/${username}/pretendo-ler`}>
            <Bookmark size={40} />
            <span>Pretendo Ler</span>
          </Link>
        </div>
        <div className='status-item'>
          <Link className='btn-listas' to={`/perfil/${username}/abandonados`}>
            <Trash2 size={40} />
            <span>Abandonados</span>
          </Link>
        </div>
      </div>

      <div className='ultimas-leituras'>
        <h2>Última Leitura:</h2>
        {ultimasLeituras && ultimasLeituras.length > 0 ? (
          ultimasLeituras.map((leitura) => (
            <UltimasLeituras key={leitura.id} leitura={leitura} />
          ))
        ) : (
          <div className='sem-leituras'>
            <p>Nenhuma leitura encontrada. </p>
            <p>Comece uma nova!</p>
          </div>
        )}
      </div>
    </div>
  );
}