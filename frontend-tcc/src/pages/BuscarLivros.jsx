import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { requestLogado } from "../utils/requests";
import "./styles/BuscarLivros.css";

function useQuery() {
    return new URLSearchParams(useLocation().search);
}

export default function BuscarLivros() {

    const query = useQuery().get("query");
    const [livros, setLivros] = useState([]);
    const estrelas = [1,2,3,4,5];

    useEffect(() => {
        const buscarLivros = async () => {
            if (query) {
                try {
                    const data = await requestLogado(`api/livros/buscar?query=${query}`, {}, "GET");
                    setLivros(data);
                } catch (error) {
                    console.error("Erro ao buscar livros:", error);
                }
            }
        };

        buscarLivros();
    
    }, [query]);

    return(
    <div className="buscar-container">
      <h2 className="buscar-titulo">Pesquisa: <span className="buscar-query">"{query}"</span></h2>

      {livros.length > 0 ? (
        <div className="livros-grid">
          {livros.map((livro) => (
            <div key={livro.id} className="livro-card">
              <p className="livro-titulo"><strong>{livro.titulo}</strong></p>
              <p className="livro-autor">{livro.autor}</p>
              <p className="livro-avaliacao">
                {
                estrelas.map((item, index) => {
                    const estrela = livro.avaliacao == null ? "☆" : item <= livro.avaliacao ? "★" : "☆";
                    return <span key={`star-${livro.id}-${index}`}>{estrela}</span>;
                }
              )}
              </p>
            </div>
          ))}
        </div>
      ) : (
        <p className="sem-leituras">Nenhum livro encontrado.</p>
      )}
    </div>
    );

}