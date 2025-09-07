import { useParams } from "react-router";
import { useEffect, useState } from "react";
import { requestLogado, baseUrl } from "../utils/requests";
import { Star } from "lucide-react";
import "./styles/EditarLeitura.css";

export default function EditarLeitura() {
  const { id } = useParams();
  const [leitura, setLeitura] = useState(null);
  const [paginaAtual, setPaginaAtual] = useState(0);
  const [review, setReview] = useState("");
  const [nota, setNota] = useState(0);
  const [texto, setTexto] = useState("");

  useEffect(() => {
    async function carregarLeitura() {
      try {
        const data = await requestLogado(
          `api/leituras/buscar-leitura/${id}`,
          {},
          "GET"
        );
        console.log(data);
        setLeitura(data);
        setPaginaAtual(data.pagina_atual || 0);
        setReview(data.review || "");
        setNota(data.nota || 0);
        setTexto(data.texto || "");
      } catch (err) {
        console.error("Erro ao carregar leitura:", err);
      }
    }
    carregarLeitura();
  }, [id]);

  if (!leitura) return <p>Carregando...</p>;

  function handleSalvar(e) {
    e.preventDefault();
    console.log({
      paginaAtual,
      review,
      nota,
      texto,
    });
    // aqui você chama sua API para salvar
  }

  return (
    <div className="tela-leitura-container">
      <div className="leitura-topo">
        {/* Capa do livro + estrelas embaixo */}
        <div className="leitura-capa">
          <img
            src={
              leitura.livro.imagem?.startsWith("http")
                ? leitura.livro.imagem
                : `${baseUrl}api/livros/livroImage/${leitura.livro.imagem}`
            }
            alt={leitura.livro.titulo}
          />

          {/* Estrelas abaixo da capa */}
          <div className="leitura-estrelas">
            {Array.from({ length: 5 }, (_, i) => (
              <Star
                key={i}
                size={22}
                className="estrela"
                fill={i < (nota || 0) ? "gold" : "none"}
                color={i < (nota || 0) ? "gold" : "#b3b3b3"}
                onClick={() => setNota(i + 1)}
                style={{ cursor: "pointer" }}
              />
            ))}
          </div>
        </div>

        {/* Formulário de edição ao lado da capa */}
        <div className="leitura-form">
          <h1>{leitura.livro.titulo}</h1>
          <p className="leitura-autor">{leitura.livro.autor}</p>

          <form className="form-leitura" onSubmit={handleSalvar}>
            <label>
              Página atual:
              <input
                type="number"
                value={paginaAtual}
                onChange={(e) => setPaginaAtual(e.target.value)}
              />
            </label>

            <label>
              Review:
              <textarea
                value={texto}
                onChange={(e) => setTexto(e.target.value)}
              ></textarea>
            </label>

            <button type="submit" className="btn-salvar">
              Salvar
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
