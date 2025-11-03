import "./ModalAnotacao.css";
import { useState } from "react";

export default function ModalAnotacao({ idLeitura, onClose, onSalvar }) {

  const [descricao, setDescricao] = useState();

  function handleSalvar() {
    console.log(idLeitura)
    onSalvar(descricao);
    onClose();
  }

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-conteudo" onClick={(e) => e.stopPropagation()}>
        <h2>Adicionar Anotação</h2>

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
          <button className="btn-salvar" onClick={handleSalvar}>
            Salvar
          </button>
        </div>
      </div>
    </div>
  );
}
