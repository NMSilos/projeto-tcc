import { useParams } from "react-router";
import { useEffect, useState } from "react";
import { requestLogado } from "../utils/requests";

export default function EditarLeitura() {
  const { id } = useParams();
  const [leitura, setLeitura] = useState(null);

  useEffect(() => {
    async function carregarLeitura() {
      try {
        const data = await requestLogado(`api/leituras/${id}`, {}, "GET");
        setLeitura(data);
      } catch (err) {
        console.error("Erro ao carregar leitura:", err);
      }
    }
    carregarLeitura();
  }, [id]);

  if (!leitura) return <p>Carregando...</p>;

  return (
    <div className="perfil-container">
      <h2>Editar Leitura</h2>
      <h3>{leitura.livro.titulo}</h3>

      <form>
        <label>
          Página atual:
          <input type="number" defaultValue={leitura.paginaAtual} />
        </label>
        <label>
          Nota:
          <input type="number" min="1" max="5" defaultValue={leitura.nota || 0} />
        </label>
        <label>
          Review:
          <textarea defaultValue={leitura.review || ""}></textarea>
        </label>

        <button type="submit">Salvar</button>
      </form>
    </div>
  );
}
