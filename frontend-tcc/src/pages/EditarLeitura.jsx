import { useParams } from "react-router";
import { useEffect, useState } from "react";
import { requestLogado, baseUrl } from "../utils/requests";
import { Star } from "lucide-react";
import "./styles/EditarLeitura.css";
import { formarEnumStatus, normalizarStatus } from "../utils/functions";
import { toast, ToastContainer } from "react-toastify";

export default function EditarLeitura() {
  const { id } = useParams();
  const [leitura, setLeitura] = useState(null);
  const [paginaAtual, setPaginaAtual] = useState(0);
  const [nota, setNota] = useState(0);
  const [texto, setTexto] = useState("");
  const [status, setStatus] = useState("lendo");
  const [dataInicio, setDataInicio] = useState();
  const [dataTermino, setDataTermino] = useState();

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
        if (data.comentario != null) {
          setNota(data.comentario.nota || 0);
          setTexto(data.comentario.texto || "");
        }
        setStatus(normalizarStatus(data.status));
        setDataInicio(data.data_inicio);
        setDataTermino(data.data_termino);

      } catch (err) {
        console.error("Erro ao carregar leitura:", err);
      }
    }
    carregarLeitura();
  }, [id]);

  if (!leitura) return <p>Carregando...</p>;

  async function handleSalvar(e) {
    e.preventDefault();
    const dados = {
      id: leitura.id,
      data_inicio: leitura.data_inicio,
      data_termino: leitura.data_termino,
      pagina_atual: paginaAtual,
      status: formarEnumStatus(status),
      comentario: {
        texto,
        nota,
      }
    }

    try {
      await requestLogado("api/leituras/editar", dados, "PUT");
      toast.success("Leitura atualizada");
    }
    catch (e) {
      toast.error(e.message);
    }

  }

  return (
    <div className="tela-leitura-container">
      <div className="leitura-topo">
        <div className="leitura-capa">
          <img
            src={
              leitura.livro.imagem?.startsWith("http")
                ? leitura.livro.imagem
                : `${baseUrl}api/livros/livroImage/${leitura.livro.imagem}`
            }
            alt={leitura.livro.titulo}
          />

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

        <div className="leitura-form">
          <h1>{leitura.livro.titulo}</h1>
          <p className="leitura-autor">{leitura.livro.autor}</p>

          <div className="progresso-container">
            <div className="barra-progresso">
              <div
                className="barra-preenchida"
                style={{
                  width: `${(paginaAtual / leitura.livro.paginas) * 100}%`,
                }}
              ></div>
            </div>
            <span className="progresso-texto">
              {paginaAtual} / {leitura.livro.paginas} páginas
            </span>
          </div>

          <div className="datas-container">
            <div className="campo-data">
              <label>Data de Início</label>
              <input
                type="date"
                value={dataInicio}
                onChange={(e) => setDataInicio(e.target.value)}
              />
            </div>

            <div className="campo-data">
              <label>Data de Término</label>
              <input
                type="date"
                value={dataTermino}
                onChange={(e) => setDataTermino(e.target.value)}
                disabled={status != "lidos" && status != "abandonado" ? true : false}
              />
            </div>
          </div>

          <form className="form-leitura" onSubmit={handleSalvar}>
            <div className="form-linha">
              <label>
                Página atual:
                <input
                  type="number"
                  value={paginaAtual}
                  onChange={(e) => setPaginaAtual(e.target.value)}
                />
              </label>

              <label>
                Status:
                <select
                  value={status}
                  onChange={(e) => setStatus(e.target.value)}
                >
                  <option value="lendo">Lendo</option>
                  <option value="lidos">Lidos</option>
                  <option value="pretendo">Pretendo Ler</option>
                  <option value="abandonado">Abandonado</option>
                </select>
              </label>
            </div>

            <label>
              Review:
              <textarea
                value={texto}
                onChange={(e) => setTexto(e.target.value)}
                disabled={status != "lidos" && status != "abandonado" ? true : false}
              ></textarea>
            </label>

            <button type="submit" className="btn-salvar">
              Salvar
            </button>
          </form>
        </div>
      </div>
      <ToastContainer />
    </div>
  );
}
