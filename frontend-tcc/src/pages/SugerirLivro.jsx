import { jwtDecode } from "jwt-decode";
import { PlusCircle, Undo2 } from "lucide-react";
import { useState } from "react";
import { useNavigate, useParams } from "react-router";
import { toast, ToastContainer } from "react-toastify";
import { requestLogado } from "../utils/requests";
import './styles/SugerirLivro.css';

export default function SugerirLivro() {

    const [titulo, setTitulo] = useState("");
    const [autor, setAutor] = useState("");
    const [editora, setEditora] = useState("");
    const [descricao, setDescricao] = useState("");
    const { user } = useParams();
    const navigate = useNavigate();

    async function enviarSugestao(e) {
        e.preventDefault();
        const token = jwtDecode(localStorage.getItem("token"));
        const id = token.id;
        const dados = {
            titulo,
            autor,
            editora,
            descricao,
            usuario: {
                id
            }
        }

        const response = await requestLogado(`api/sugestoes/cadastrar`, dados, "POST");
        if (response) {
            toast.success("Sugestão enviada com sucesso!");
            console.log(response);
            setTitulo("");
            setAutor("");
            setEditora("");
            setDescricao("");
        } else {
            toast.error("Erro ao enviar sugestão, tente novamente mais tarde");
        }
    }

    function voltar() {
        navigate(`/perfil/${user}`);
    }

    return (
        <div className="sugerir-container">
            <div className="sugerir-card">

                <div className="header-form">
                    <h2 className="form-titulo">Nova Sugestão de Livro</h2>
                    <button onClick={voltar} className="btn-voltar">
                        <Undo2 /> Voltar
                    </button>
                </div>

                <form onSubmit={enviarSugestao} className="form-livro">
                    <div className="form-grupo">
                        <label>Título</label>
                        <input
                            type="text"
                            name="titulo"
                            value={titulo}
                            onChange={(e) => setTitulo(e.target.value)}
                            required
                        />
                    </div>

                    <div className="form-linha">
                        <div className="form-grupo">
                            <label>Autor</label>
                            <input
                                type="text"
                                name="autor"
                                value={autor}
                                onChange={(e) => setAutor(e.target.value)}
                                required
                            />
                        </div>

                        <div className="form-grupo">
                            <label>Editora</label>
                            <input
                                type="text"
                                name="editora"
                                value={editora}
                                onChange={(e) => setEditora(e.target.value)}
                            />
                        </div>
                    </div>

                    <div className="form-grupo">
                        <label>Descrição</label>
                        <textarea
                            name="descricao"
                            value={descricao}
                            onChange={(e) => setDescricao(e.target.value)}
                            rows="4"
                            cols="50"
                        >
                        </textarea>
                    </div>

                    <button type="submit" className="btn-principal">
                        <PlusCircle size={20} /> Salvar Livro
                    </button>
                </form>
                <ToastContainer />
            </div>
        </div>
    );
}