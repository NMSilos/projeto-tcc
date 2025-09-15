import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { request, requestFormData } from '../utils/requests';
import logo from '../assets/LogoLivro.png'
import LoginLayout from '../components/LoginLayout/LoginLayout';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function CadastroUsuario() {
  const [nome, setNome] = useState('');
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [imagem, setImagem] = useState('');
  const navigate = useNavigate();

  const cadastrarUsuario = (e) => {
    e.preventDefault();
    //e.preventDefault();
    if (!nome || !username || !email || !password) {
      alert("Todos os campos são obrigatórios");
      return;
    }
    const dados = {
      nome,
      username,
      email,
      password,
      imagem
    };
    requestFormData("api/usuarios/cadastrar", dados, "POST");
    toast.success("Cadastrado com sucesso!");
    setTimeout(() => {
      navigate('/');
    }, 1500)
  };

  return (
    <LoginLayout>
      <div className="form-container">
        <img src={logo} alt="Logo" className="login-logo" />
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
          <div className='form-group'>
            <label htmlFor="image">Imagem</label>
            <input 
              type="file" 
              name="image"
              id="image" 
              onChange={(e) => setImagem(e.target.files[0])}
              accept='image/png, image/jpeg'
            />
          </div>
          <button type="submit" className="submit-btn">Cadastrar</button>
        </form>
        <p>
          Já tem conta?{' '}
          <a href="/" className="link-inline">
            Faça login
          </a>
        </p>
        <ToastContainer />
      </div>
    </LoginLayout>
  );
}