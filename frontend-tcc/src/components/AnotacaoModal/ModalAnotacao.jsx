import { data } from "react-router";
import "./ModalAnotacao.css";
import { useEffect, useState } from "react";
import { requestLogado } from "../../utils/requests";

export default function ModalAnotacao({ idLeitura, anotacao, onClose, onSalvar }) {

  const [descricao, setDescricao] = useState("");
  const [titulo, setTitulo] = useState("");
  const [capitulo, setCapitulo] = useState("");
  const [pagina, setPagina] = useState("");
  const [data, setData] = useState("");

  async function salvarAnotacao() {
    const dados = {
      leitura: { id: idLeitura },
      titulo,
      descricao,
      capitulo,
      pagina,
      data
    };

    try {
      if (anotacao) {
        await requestLogado(`api/anotacoes/editar/${anotacao.id}`, dados, "PUT");
      } else {
        await requestLogado(`api/anotacoes/anotar`, dados, "POST");
      }
      onSalvar && onSalvar();
    } catch (error) {
      console.error("Erro ao salvar anotação:", error);
    }
  }

  useEffect(() => {
    if (anotacao) {
      setTitulo(anotacao.titulo || "");
      setDescricao(anotacao.descricao || "");
      setCapitulo(anotacao.capitulo || "");
      setPagina(anotacao.pagina || "");
      setData(anotacao.data || "");
    }
  }, [anotacao]);


  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-conteudo" onClick={(e) => e.stopPropagation()}>

        <h2>Adicionar Anotação</h2>

        <div className="linha-campos">
          <div className="campo">
            <label>Título</label>
            <input
              type="text"
              value={titulo}
              onChange={(e) => setTitulo(e.target.value)}
            />
          </div>
        </div>

        <div className="linha-campos">
          <div className="campo">
            <label>Capítulo</label>
            <input
              type="number"
              value={capitulo}
              onChange={(e) => setCapitulo(e.target.value)}
            />
          </div>

          <div className="campo">
            <label>Página</label>
            <input
              type="number"
              value={pagina}
              onChange={(e) => setPagina(e.target.value)}
            />
          </div>
        </div>

        <textarea
          placeholder="Escreva sua anotação..."
          value={descricao}
          onChange={(e) => setDescricao(e.target.value)}
          className="modal-textarea"
        ></textarea>

        <div className="modal-botoes">
          <button className="btn-cancelar" onClick={onClose}>
            Cancelar
          </button>

          <button
            className="btn-salvar"
            onClick={(e) => {
              e.stopPropagation();
              salvarAnotacao();
            }}
          >
            Salvar
          </button>
        </div>

      </div>
    </div>
  );
}