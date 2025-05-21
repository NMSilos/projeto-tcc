import React from 'react';
import { jwtDecode } from "jwt-decode";
import { useEffect, useState } from "react";
import lidosIcon from "../assets/icons/lidos.svg"
import lendoIcon from "../assets/icons/lendo.svg"
import pretendoLerIcon from "../assets/icons/pretendo_ler.svg"
import abandonadosIcon from "../assets/icons/abandonados.svg"
import "./styles/PerfilUsuario.css";
import { requestLogado } from '../utils/requests';
import { useParams } from 'react-router';

export default function PerfilUsuario() {
  const [nome, setNome] = useState();
  const [username, setUsername] = useState();
  const [usuarioLogado, setUsuarioLogado] = useState();
  const { id } = useParams();

  async function carregarDados() {
    const usuario = await requestLogado(`api/usuarios/buscar/id/${id}`, {}, "GET");
    setUsuarioLogado(usuario);
  }

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      const dados = jwtDecode(token);

      setNome(dados?.nome);
      setUsername(dados?.sub);
    }
    carregarDados();
    
  }, [id]);

  return (
      <div className="perfil-container">
        <div className="perfil-topo">
          <div className="perfil-info">
            <img src="/caminho/avatar.svg" alt="Avatar" />
            <div className="perfil-detalhes">
              <p><strong>{nome}</strong></p>
              <p>{username}</p>
              <p>Criado em: 05/09/2024</p>
              <p>Total Leituras: 1</p>
            </div>
            <div className="perfil-editar">
              <button>Editar Perfil</button>
            </div>
          </div>
      </div>

      <div className="status-leitura">
        
        <div className="status-item"><a href="/lidos"><img src={lidosIcon} /></a>Lidos</div>
        <div className="status-item"><a href="/lendo"><img src={lendoIcon} /></a>Lendo</div>
        <div className="status-item"><a href="/pretendo-ler"><img src={pretendoLerIcon} /></a>Pretendo Ler</div>
        <div className="status-item"><a href="/abandonados"><img src={abandonadosIcon} /></a>Abandonados</div>
      </div>

      <div className="ultima-leitura">
        <h2>Última Leitura:</h2>
        <div className="leitura-conteudo">
          <div className="leitura-img">
            <img src="/livros/hobbit.jpg" alt="O Hobbit" />
            <p>O Hobbit</p>
            <div className="stars">★ ★ ★ ★ ☆</div>
          </div>
          <div className="leitura-review">
            <h3>Review:</h3>
            <p>O Hobbit é um livro fluido, divertido...</p>
          </div>
        </div>
      </div>
    </div>
  );
}