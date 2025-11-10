import { useEffect, useState } from "react";
import "./styles/EditarUsuario.css";
import { jwtDecode } from "jwt-decode";
import { useNavigate } from "react-router";
import { requestFormData } from "../utils/requests";

export default function EditarUsuario() {

    const [novoNome, setNovoNome] = useState('');
    const [novoUsername, setNovoUsername] = useState('');
    const [newPassword1, setNewPassword1] = useState('');
    const [newPassword2, setNewPassword2] = useState('');
    const [imagem, setImagem] = useState(null);

    const navigate = useNavigate();
    const token = jwtDecode(localStorage.getItem("token"));

    useEffect(() => {
        setNovoNome(token.nome);
        setNovoUsername(token.sub);
    }, []);

    const handleFotoChange = (e) => {
        const file = e.target.files[0];
        if (file) setImagem(file);
    };

    const editarUsuario = async (e) => {
        e.preventDefault();

        if (newPassword1 !== '' && newPassword2 !== '' && newPassword1 !== newPassword2) {
            alert('Senhas devem ser iguais');
            return;
        }

        let formData = {};

        if (newPassword1 === '') {
            formData = {
                nome: novoNome,
                username: novoUsername,
                imagem
            }
        } else {
            formData = {
                nome: novoNome,
                username: novoUsername,
                password: newPassword1,
                imagem
            }
        }

        const response = await requestFormData("api/usuarios/modificar", formData, "PUT");
        localStorage.setItem("token", response.token);
        alert("Usuário atualizado!");
        navigate(`/perfil/${novoUsername}`);
    };

    return (
        <div className="editar-container">
            <div className="form-container">
                <h2>Editar perfil</h2>

                <form onSubmit={editarUsuario}>
                    <div className="form-group">
                        <label htmlFor="nome">Nome</label>
                        <input
                            type="text"
                            id="nome"
                            value={novoNome}
                            onChange={(e) => setNovoNome(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="username">Apelido</label>
                        <input
                            type="text"
                            id="username"
                            value={novoUsername}
                            onChange={(e) => setNovoUsername(e.target.value)}
                        />
                    </div>

                    <div className="form-group senha-campos">
                        <label>Alteração de senha</label>
                        <input
                            type="password"
                            placeholder="Digite a nova senha"
                            value={newPassword1}
                            onChange={(e) => setNewPassword1(e.target.value)}
                        />
                        <input
                            type="password"
                            placeholder="Confirme a nova senha"
                            value={newPassword2}
                            onChange={(e) => setNewPassword2(e.target.value)}
                        />
                    </div>

                    <div className="foto-perfil-container">
                        <input type="file" accept="image/*" onChange={handleFotoChange} />
                    </div>

                    <button type="submit" className="submit-btn">Atualizar</button>
                </form>
            </div>
        </div>
    );
}
