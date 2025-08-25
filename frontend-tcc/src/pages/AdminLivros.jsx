import { Link } from "react-router-dom";
import { BookOpen, PlusCircle, Edit, Trash2 } from "lucide-react";
import './styles/AdminLivros.css'
import { useEffect, useState } from "react";
import { buscaLivros } from "../utils/requests";

export default function AdminLivros() {

  const [livros, setLivros] = useState();

  async function carregarDados() {
      const livrosBuscados = await buscaLivros(`api/livros/buscar/all`, "GET");
      setLivros(livrosBuscados)
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
          {livros.map((livro) => (
            <tr key={livro.id}>
              <td><img src={livro.capa} alt={livro.titulo} className="admin-tabela-img" /></td>
              <td>{livro.titulo}</td>
              <td>{livro.autor}</td>
              <td>{livro.ano}</td>
              <td>
                <Link to={`/admin/livros/editar/${livro.id}`}>
                  <button className="btn-acao editar">
                    <Edit size={18}/> Editar
                  </button>
                </Link>
                <button className="btn-acao deletar" onClick={() => handleDelete(livro.id)}>
                  <Trash2 size={18}/> Remover
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    ) : (
      <p className="sem-livros">Nenhum livro cadastrado.</p>
    )}
  </div>
</div>

  );
}