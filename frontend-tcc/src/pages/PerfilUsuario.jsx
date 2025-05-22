import React from 'react';
import { jwtDecode } from "jwt-decode";
import { useEffect, useState } from "react";
import lidosIcon from "../assets/icons/lidos.svg"
import lendoIcon from "../assets/icons/lendo.svg"
import pretendoLerIcon from "../assets/icons/pretendo_ler.svg"
import abandonadosIcon from "../assets/icons/abandonados.svg"
import "./styles/PerfilUsuario.css";
import { requestLogado } from '../utils/requests';
import { Link, useParams } from 'react-router';
import UltimasLeituras from '../components/UltimasLeituras';

export default function PerfilUsuario() {
  const [nome, setNome] = useState();
  const [username, setUsername] = useState();
  const [leituras, setLeituras] = useState([]);
  const [ultimasLeituras, setUltimasLeituras] = useState([]);
  const { user } = useParams();

  async function carregarDados() {
    const usuario = await requestLogado(`api/usuarios/buscar/username/${user}`, {}, "GET");
    setLeituras(usuario.leituras)
    setUltimasLeituras(usuario.leituras.slice().reverse().slice(0, 3))
  }

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      const dados = jwtDecode(token);

      setNome(dados?.nome);
      setUsername(dados?.sub);
    }
    carregarDados();
  }, [user]);

  return (
      <div className="perfil-container">
        <div className="perfil-topo">
          <div className="perfil-info">
            <img src="/caminho/avatar.svg" alt="Avatar" />
            <div className="perfil-detalhes">
              <p><strong>{nome}</strong></p>
              <p>{username}</p>
              <p>Criado em: 05/09/2024</p>
              <p>Total Leituras: {leituras.length}</p>
            </div>
            <div className="perfil-editar">
              <button>Editar Perfil</button>
            </div>
          </div>
      </div>

      <div className="status-leitura">
        <div className="status-item"><Link to={`/perfil/${username}/lidos`}><img src={lidosIcon} /><span>Lidos</span></Link></div>
        <div className="status-item"><Link to={`/perfil/${username}/lendo`}><img src={lendoIcon} /><span>Lendo</span></Link></div>
        <div className="status-item"><Link to={`/perfil/${username}/pretendo-ler`}><img src={pretendoLerIcon} /><span>Pretendo Ler</span></Link></div>
        <div className="status-item"><Link to={`/perfil/${username}/abandonados`}><img src={abandonadosIcon} /><span>Abandonados</span></Link></div>
      </div>
      
      <div className='ultimas-leituras'>
        <h2>Últimas Leituras:</h2>
        {ultimasLeituras.map((leitura, index) => (
          <UltimasLeituras key={index} leitura={leitura} />
        ))}
      </div>

    </div>
  );
}