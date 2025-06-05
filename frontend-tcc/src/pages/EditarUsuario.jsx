import { useEffect, useState } from "react";
import "./styles/EditarUsuario.css";
import { jwtDecode } from "jwt-decode";
import { useNavigate, useParams } from "react-router";
import { request, requestLogado } from "../utils/requests";

export default function EditarUsuario() {
    
    const [novoNome, setNovoNome] = useState('');
    const [novoUsername, setNovoUsername] = useState('');
    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [editarSenha, setEditarSenha] = useState(false);
    const navigate = useNavigate();

    const token = jwtDecode(localStorage.getItem("token"));

    function handleCheckboxSenha() {
        setEditarSenha(!editarSenha);
    }

    const editarUsuario = async (e) => {
        e.preventDefault();
        if(oldPassword != '' && newPassword != '') {        
            if(oldPassword === newPassword) {
                const dados = {
                    nome: novoNome,
                    username: novoUsername,
                    password: newPassword
                };
                console.log(dados)
                const response = await requestLogado("api/usuarios/modificar", dados, "PUT");
                console.log(response);
                
                localStorage.setItem("token", response.token);

                alert("Usuario Atualizado!");
                console.log("COM SENHA");
                navigate(`/perfil/${dados.username}`);
                
            } else {
                alert('Senhas devem ser iguais');
            }
            
        } else {
            const dados = {
                nome: novoNome,
                username: novoUsername,
                password: null
            };
            const response = await requestLogado("api/usuarios/modificar", dados, "PUT");
            localStorage.setItem("token", response.token);
            console.log("SEM SENHA");
            alert("Usuario Atualizado!");
            navigate(`/perfil/${dados.username}`);
        }
    }   
    
    useEffect(() => {
        setNovoNome(token.nome);
        setNovoUsername(token.sub);
    }, []);
    
    return(
        <div className="editar-container">
            <div className="form-container">
                <h2>Editar perfil</h2>
                <form onSubmit={editarUsuario}>
                <div className="form-group">
                    <label htmlFor="nome">Nome</label>
                    <input
                    type="text"
                    id="nome"
                    name="nome"
                    value={novoNome}
                    onChange={(e) => setNovoNome(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="username">Apelido</label>
                    <input
                    type="text"
                    id="username"
                    name="username"
                    value={novoUsername}
                    onChange={(e) => setNovoUsername(e.target.value)}
                    />
                </div>
                <div className="form-group" >
                    Editar senha: <input type="checkbox" name="editar-senha" id="editar-senha" checked={editarSenha} onChange={handleCheckboxSenha} />
                    <div className="senha-campos">
                        {editarSenha && (
                            <div>    
                                <label htmlFor="old-password" className="campo-senha">Senha Antiga</label>
                                <input type="password" name="old-password" id="old-password" value={oldPassword} required  onChange={(e) => setOldPassword(e.target.value)}/>
                                <label htmlFor="password" className="campo-senha">Nova Senha</label>
                                <input type="password" name="new-password" id="new-password" value={newPassword} required onChange={(e) => setNewPassword(e.target.value)}/>
                            </div>
                        )}
                    </div>
                </div>
                <button type="submit" className="submit-btn">Atualizar</button>
                </form>
            </div>
        </div>
    );
}