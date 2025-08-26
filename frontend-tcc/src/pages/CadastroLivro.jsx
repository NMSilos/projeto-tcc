import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { PlusCircle } from "lucide-react";
import './styles/AdminLivros.css';
import './styles/CadastroLivro.css';
import { buscaLivros } from "../utils/requests";

export default function CadastroLivro() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    titulo: "",
    autor: "",
    ano: "",
    paginas: "",
    isbn: "",
    descricao: "",
    capa: ""
  });

  function handleChange(e) {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  }

  async function handleSubmit(e) {
    e.preventDefault();
    await buscaLivros("api/livros/novo", "POST", formData);
    navigate("/admin/livros");
  }

  return (
    <div className="admin-tabela">
      <h2 className="form-titulo">Novo Livro</h2>

      <form onSubmit={handleSubmit} className="form-livro">
        <div className="form-grupo">
          <label>Título</label>
          <input
            type="text"
            name="titulo"
            value={formData.titulo}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-grupo">
          <label>Autor</label>
          <input
            type="text"
            name="autor"
            value={formData.autor}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-linha">
            <div className="form-grupo">
            <label>Ano</label>
            <input
                type="number"
                name="ano"
                value={formData.ano}
                onChange={handleChange}
                required
            />
            </div>

            <div className="form-grupo">
            <label>Páginas</label>
            <input
                type="number"
                name="paginas"
                value={formData.paginas}
                onChange={handleChange}
                required
            />
            </div>

            <div className="form-grupo">
            <label>ISBN</label>
            <input
                type="text"
                name="isbn"
                value={formData.isbn}
                onChange={handleChange}
                required
            />
            </div>
        </div>

        <div className="form-grupo">
            <label>Descrição</label>
            <textarea 
                name="descricao" 
                value={formData.descricao} 
                onChange={handleChange}
                rows="4" 
                cols="50"
            >
            </textarea>
        </div>

        <div className="form-grupo">
          <label>Capa do Livro</label>
          <input
            type="file"
            name="capa"
            value={formData.capa}
            onChange={handleChange}
            required
          />
        </div>

        <button type="submit" className="btn-principal">
          <PlusCircle size={20} /> Salvar Livro
        </button>
      </form>
    </div>
  );
}
