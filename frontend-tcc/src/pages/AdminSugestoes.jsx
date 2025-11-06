import { Link } from "react-router-dom";
import { Trash2, Book } from "lucide-react";
import './styles/AdminSugestoes.css'
import { useEffect, useState } from "react";
import { buscaSugestoes, requestLogado } from "../utils/requests";

export default function AdminSugestoes() {

  const [sugestoes, setSugestoes] = useState();

  async function carregarDados() {
    const sugestoesBuscadas = await buscaSugestoes(`api/sugestoes/buscar/all`, "GET");
    console.log(sugestoesBuscadas)
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
                <th>Status</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
              {sugestoes.map((sugestao) => (
                <tr key={sugestao.id}>
                  <td>{sugestao.titulo}</td>
                  <td>{sugestao.autor}</td>
                  <td>{sugestao.editora}</td>
                  <td>
                    <span
                      className={
                        sugestao.status === "ACEITA" ? "status-badge status-aceita" :
                          sugestao.status === "PENDENTE" ? "status-badge status-pendente" :
                            "status-badge status-recusada"
                      }
                    >
                      {sugestao.status}
                    </span>
                  </td>
                  <td>
                    <Link to={"/admin/livros/novo"} state={{ novoLivro: sugestao, idSugestao: sugestao.id }}>
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