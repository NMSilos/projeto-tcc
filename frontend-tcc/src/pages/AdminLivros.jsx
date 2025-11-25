import { Link } from "react-router-dom";
import { PlusCircle, Edit, Trash2 } from "lucide-react";
import './styles/AdminLivros.css'
import { useEffect, useState } from "react";
import { baseUrl, buscaLivros, requestLogado } from "../utils/requests";
import { toast, ToastContainer } from "react-toastify";

export default function AdminLivros() {

  const [livros, setLivros] = useState();

  async function carregarDados() {
    const livrosBuscados = await buscaLivros(`api/livros/buscar/all`, "GET");
    setLivros(livrosBuscados)
  }

  async function deletar(id) {
    try {
      await requestLogado(`api/livros/remover/${id}`, {}, "DELETE");
      toast.success("Livro removido com sucesso!");
      carregarDados();
    } catch (error) {
      toast.error("Erro ao remover livro");
    }
  }

  useEffect(() => {
    carregarDados();
  }, []);

  return (
    <div className="admin-tabela-container">
      <div className="admin-tabela-topo">
        <Link to="/admin/livros/novo">
          <button className="btn-principal">
            <PlusCircle size={20} /> Adicionar Livro
          </button>
        </Link>
      </div>

      <div className="admin-tabela">
        {livros && livros.length > 0 ? (
          <table>
            <thead>
              <tr>
                <th>Capa</th>
                <th>Título</th>
                <th>Autor</th>
                <th>Ano</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
              {livros.map((livro) => {
                let imgSrc = null;

                if (livro.imagem) {
                  if (livro.imagem.startsWith("https")) {
                    imgSrc = livro.imagem;
                  } else {
                    imgSrc = `${baseUrl}api/livros/livroImage/${livro.imagem}`;
                  }
                }

                return (
                  <tr key={livro.id}>
                    <td>
                      {imgSrc ? (
                        <img
                          src={imgSrc}
                          alt={livro.titulo}
                          className="admin-tabela-img"
                        />
                      ) : (
                        <span>Sem capa</span>
                      )}
                    </td>
                    <td>{livro.titulo}</td>
                    <td>{livro.autor}</td>
                    <td>{livro.ano_publicacao}</td>
                    <td>
                      <Link
                        to="/admin/livros/novo"
                        state={{ livroEdit: livro }}
                      >
                        <button className="btn-acao editar">
                          <Edit size={18} /> Editar
                        </button>
                      </Link>
                      <button
                        className="btn-acao deletar"
                        onClick={() => deletar(livro.id)}
                      >
                        <Trash2 size={18} /> Remover
                      </button>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        ) : (
          <p className="sem-livros">Nenhum livro cadastrado.</p>
        )}
      </div>
      <ToastContainer />
    </div>

  );
}