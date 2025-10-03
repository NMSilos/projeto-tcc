import { Link } from "react-router-dom";
import { PlusCircle, Edit, Trash2, Book } from "lucide-react";
import './styles/AdminLivros.css'
import { useEffect, useState } from "react";
import { baseUrl, buscaSugestoes, requestLogado } from "../utils/requests";
import CadastroLivro from "./CadastroLivro";

export default function AdminSugestoes() {

  const [sugestoes, setSugestoes] = useState();

  async function carregarDados() {
    const sugestoesBuscadas = await buscaSugestoes(`api/sugestoes/buscar/all`, "GET");
    setSugestoes(sugestoesBuscadas)
  }

  useEffect(() => {
    carregarDados();
  }, []);

  async function deletarSugestao(id) {
    await requestLogado(`api/sugestoes/deletar/${id}`, {}, "DELETE");
    carregarDados();
  }

  return (
    <div className="admin-tabela-container">
      <div className="admin-tabela-topo">
      </div>

      <div className="admin-tabela">
        {sugestoes && sugestoes.length > 0 ? (
          <table>
            <thead>
              <tr>
                <th>Título</th>
                <th>Autor</th>
                <th>Editora</th>
                <th>Descrição</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
              {sugestoes.map((sugestao) => (
                <tr key={sugestao.id}>
                  <td>{sugestao.titulo}</td>
                  <td>{sugestao.autor}</td>
                  <td>{sugestao.editora}</td>
                  <td>{sugestao.descricao}</td>
                  <td>
                    <Link to={"/admin/livros/novo"} state={{ novoLivro: sugestao }}>
                      <button className="btn-acao editar">
                        <Book size={18} /> Criar Livro
                      </button>
                    </Link>
                    <button
                      className="btn-acao deletar"
                      onClick={() => deletarSugestao(sugestao.id)}
                    >
                      <Trash2 size={18} /> Remover
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p className="sem-livros">Nenhuma sugestão cadastrada.</p>
        )}
      </div>
    </div>
  );
}