import { useEffect, useState } from "react";
import { Link, NavLink, useLocation, useNavigate } from "react-router-dom";
import { requestLogado } from "../utils/requests";
import "./styles/BuscarLivros.css";
import { Star } from "lucide-react";

function useQuery() {
    return new URLSearchParams(useLocation().search);
}

export default function BuscarLivros() {

    const query = useQuery().get("query");
    const [livros, setLivros] = useState([]);
    const estrelas = [1,2,3,4,5];
    const navigate = useNavigate();

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

    
    async function abrirLivro(id) {
      const livro = await requestLogado(`api/livros/buscar-id/${id}`, {}, "GET");
      navigate(`/livros/${livro.isbn}`);
    }
    
    return(
    <div className="buscar-container">
      <h2 className="buscar-titulo">Pesquisa: <span className="buscar-query">"{query}"</span></h2>
      {livros.length > 0 ? (
          <div className="livros-grid">
            {livros.map((livro) => (
              <div key={livro.id} className="livro-card" onClick={() => {
                abrirLivro(livro.id);
              }}>
                <img src="https://m.media-amazon.com/images/I/511+-lOOtsL._SY445_SX342_.jpg" />
                <p className="livro-titulo"><strong>{livro.titulo}</strong></p>
                <p className="livro-autor">{livro.autor}</p>
                <p className="livro-avaliacao">
                  {
                  estrelas.map((item, index) => {
                      const estrela = livro.avaliacao == null 
                      ? <Star key={item} size={12} color="#b3b3b3" /> 
                      : item <= livro.avaliacao ? <Star key={item} size={12} fill="gold" /> 
                      : <Star key={item} size={12} color="#b3b3b3" />;
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