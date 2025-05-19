import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { request } from '../utils/requests';

export default function CadastroUsuario() {
  const [nome, setNome] = useState('');
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const cadastrarUsuario = (e) => {
    e.preventDefault();
    e.preventDefault();
    if (!nome || !username || !email || !password) {
      alert("Todos os campos são obrigatórios");
      return;
    }
    const dados = {
      nome,
      username,
      email,
      password
    };
    request("api/usuarios/cadastrar", dados, "POST");
    navigate('/');
  };

  return (
    <main>
      <div className="form-container">
        <h2>Cadastro</h2>
        <form onSubmit={cadastrarUsuario}>
          <div className="form-group">
            <label htmlFor="nome">Nome</label>
            <input
              type="text"
              id="nome"
              name="nome"
              required
              value={nome}
              onChange={(e) => setNome(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="username">Apelido</label>
            <input
              type="text"
              id="username"
              name="username"
              required
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="email">E-mail</label>
            <input
              type="email"
              id="email"
              name="email"
              required
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Senha</label>
            <input
              type="password"
              id="password"
              name="password"
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button type="submit" className="submit-btn">Cadastrar</button>
        </form>
        <p>
          Já tem conta?{' '}
          <a href="/" className="text-blue-600 hover:underline">
            Faça login
          </a>
        </p>
      </div>
    </main>
  );
}