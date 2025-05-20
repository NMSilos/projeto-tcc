import { useState } from "react";
import { Link, Navigate, useNavigate } from "react-router-dom";
import { request } from "../utils/requests";
import { GoogleLogin } from "@react-oauth/google";
import LoginLayout from "../components/LoginLayout/LoginLayout";

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
      navigate("/perfil");
      alert("LOGADO COM SUCESSO!")
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
      navigate("/perfil");
      alert("LOGADO COM SUCESSO!")
    } catch (error) {
      alert(error.mensagem);
    }
  }

  return (
    /*<AuthLayout
      title="Acesse sua conta"
      subtitle="Entre com seus dados para continuar"
      footerText="Não tem uma conta?"
      footerLink="/cadastroUsuario"
      footerLinkText="Cadastre-se"
    >*/
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
              <GoogleLogin shape="circle" text="continue_with" locale="pt-BR"
                  onSuccess={credentialResponse => {
                      logarComGoogle(credentialResponse);
                      //console.log(credentialResponse);
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
