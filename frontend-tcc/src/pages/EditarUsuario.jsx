import { useState } from "react";
import "./styles/EditarUsuario.css";

export default function EditarUsuario({ nome, username, email }) {
    
    const [editarSenha, setEditarSenha] = useState(false);

    function handleCheckboxSenha() {
        setEditarSenha(!editarSenha);
    }

    function editarUsuario(){
        console.log("lfmjdsjf")
    }    
    
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
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="form-group" >
                    Editar senha: <input type="checkbox" name="editar-senha" id="editar-senha" checked={editarSenha} onChange={handleCheckboxSenha} />
                    {editarSenha && (
                        <div>    
                            <label htmlFor="old-password" className="campo-senha">Senha Antiga</label>
                            <input type="password" name="old-password" id="old-password" required />
                            <label htmlFor="password" className="campo-senha">Nova Senha</label>
                            <input type="password" name="password" id="password" required />
                        </div>
                    )}
                </div>
                <button type="submit" className="submit-btn">Cadastrar</button>
                </form>
            </div>
        </div>
    );
}