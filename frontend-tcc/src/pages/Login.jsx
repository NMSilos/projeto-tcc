import { useState } from "react";
import { Link, Navigate, useNavigate } from "react-router-dom";
import { request } from "../utils/requests";
import { GoogleLogin } from "@react-oauth/google";
import LoginLayout from "../components/LoginLayout/LoginLayout";
import { jwtDecode } from "jwt-decode";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  function changeUsername(e) {
    const username = e.target.value;
    setUsername(username);
  }

  async function onSubmit(e) {
    e.preventDefault();

    if (username == "" || password == "") {
      alert("Campos são obrigatórios");
    }

    const credenciais = {
      username: username,
      password: password,
    };

    try {
      const dados = await request("api/usuarios/login", credenciais, "POST");
      localStorage.setItem("token", dados.token);
      
      const usuario = jwtDecode(dados.token)
      if(usuario.role == "ADMIN") {
        navigate(`/admin`);
      } else {
        navigate(`/perfil/${username}`)
      }
    } catch (error) {
      alert(error.mensagem);
    }
  }

  async function logarComGoogle(googleToken) {
    const jsonData = {
      credential: googleToken.credential
    };
    try {
      const dados = await request("api/usuarios/google", jsonData, "POST");
      localStorage.setItem("token", dados.token);

      var user = jwtDecode(dados.token);
      navigate(`/perfil/${user.sub.replace(/"/g, '')}`);
    } catch (error) {
      alert(error.mensagem);
    }
  }

  return (
    <LoginLayout>
        <div className="form-container">
          <h2>Login</h2>
          <form onSubmit={onSubmit}>
              <div className="form-group">
                <input 
                  type="text" 
                  id="username" 
                  name="username" 
                  placeholder="Usuário"
                  required onChange={changeUsername}
                />
              </div>
              <div className="form-group">
                <input 
                  type="password" 
                  id="password" 
                  name="password"
                  placeholder="Senha" 
                  required onChange={(e) => setPassword(e.target.value)}
                />
              </div>
              <p>Não possui conta?{' '}    
              <Link to="/cadastro" className="link-inline">
                Cadastre-se
              </Link>
              </p>
              <button type="submit" className="submit-btn" >Entrar</button>
              <GoogleLogin shape="circle" text="continue_with"
                  onSuccess={credentialResponse => {
                      logarComGoogle(credentialResponse);
                  }}
                  onError={() => {
                      console.log('Login Failed'); 
                  }}
              />
          </form>
      </div>
    </LoginLayout>
  );
}
