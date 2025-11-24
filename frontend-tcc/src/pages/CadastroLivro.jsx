import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { PlusCircle } from "lucide-react";
import './styles/CadastroLivro.css';
import { requestFormData, requestLogado } from "../utils/requests";
import { toast, ToastContainer } from "react-toastify";

export default function CadastroLivro() {

  const location = useLocation();
  const novoLivro = location.state?.novoLivro;
  const idSugestao = location.state?.idSugestao;

  const [titulo, setTitulo] = useState(novoLivro?.titulo || "");
  const [autor, setAutor] = useState(novoLivro?.autor || "");
  const [editora, setEditora] = useState(novoLivro?.editora || "");
  const [ano_publicacao, setAnoPublicacao] = useState();
  const [paginas, setPaginas] = useState();
  const [isbn, setIsbn] = useState();
  const [descricao, setDescricao] = useState(novoLivro?.descricao || "");
  const [imagem, setImagem] = useState();
  const navigate = useNavigate();

  const cadastrarLivro = (e) => {
    e.preventDefault();
    const dados = {
      titulo,
      autor,
      editora,
      ano_publicacao,
      paginas,
      isbn,
      descricao,
      imagem,
    };
    requestFormData("api/livros/cadastrar", dados, "POST");
    toast.success("Cadastrado com sucesso!");
    if(idSugestao != null) {
      requestLogado(`api/sugestoes/status/${idSugestao}`, {}, "PUT");
    }
    setTimeout(() => {
      navigate('/admin');
    }, 1500)
  };

  return (
    <div className="painel-form">
      <h2 className="form-titulo">Novo Livro</h2>

      <form onSubmit={cadastrarLivro} className="form-livro">
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
              required
            />
          </div>
        </div>

        <div className="form-linha">
          <div className="form-grupo">
            <label>Ano</label>
            <input
              type="number"
              name="ano"
              value={ano_publicacao}
              onChange={(e) => setAnoPublicacao(e.target.value)}
              required
            />
          </div>

          <div className="form-grupo">
            <label>Páginas</label>
            <input
              type="number"
              name="paginas"
              value={paginas}
              onChange={(e) => setPaginas(e.target.value)}
              required
            />
          </div>

          <div className="form-grupo">
            <label>ISBN</label>
            <input
              type="text"
              name="isbn"
              value={isbn}
              onChange={(e) => setIsbn(e.target.value)}
              required
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

        <div className="form-grupo">
          <label>Imagem</label>
          <input
            type="file"
            name="imagem"
            onChange={(e) => setImagem(e.target.files[0])}
            required
          />
        </div>

        <button type="submit" className="btn-principal">
          <PlusCircle size={20} /> Salvar Livro
        </button>
      </form>
      <ToastContainer />
    </div>
  );
}
