import { useEffect, useState } from "react";
import { Link, useParams } from "react-router";
import { requestLogado, baseUrl } from "../utils/requests";
import "./styles/LeiturasUsuario.css";
import { Star } from "lucide-react";

export default function LeiturasUsuario() {
  const { user, lista } = useParams();
  const [leituras, setLeituras] = useState([]);
  const estrelas = [1, 2, 3, 4, 5];

  useEffect(() => {
    async function carregarLeituras() {
      try {
        const data = await requestLogado(
          `api/leituras/${user}/${lista}`,
          {},
          "GET"
        );
        setLeituras(data);
      } catch (err) {
        console.error("Erro ao carregar leituras:", err);
      }
    }

    carregarLeituras();
  }, [user, lista]);

  function formatarLista(nome) {
    switch (nome) {
      case "lidos":
        return "Lidos";
      case "lendo":
        return "Lendo";
      case "pretendo-ler":
        return "Pretendo Ler";
      case "abandonados":
        return "Abandonados";
      default:
        return nome;
    }
  }

  return (
    <div className="perfil-container">
      <div className="ultimas-leituras">
        <h2>
          {formatarLista(lista)}
        </h2>

        {leituras && leituras.length > 0 ? (
          leituras.map((leitura) => (
            <div key={leitura.id} className="leitura">
              <Link to={`/perfil/${user}/leitura/${leitura.id}/editar`} className="leitura-conteudo">
                <div className="leitura-img">
                  <img
                    src={
                      leitura.livro.imagem?.startsWith("http")
                        ? leitura.livro.imagem
                        : `${baseUrl}api/livros/livroImage/${leitura.livro.imagem}`
                    }
                    alt={leitura.livro.titulo}
                  />
                  <div className="stars">{estrelas.map(
                    item => {
                      if (leitura.comentario != null && item <= leitura.comentario.nota) {
                        return <Star key={item} fill="gold" />;
                      }
                      return <Star color="#dadada" key={item} />;
                    }
                  )}</div>
                </div>

                <div className="leitura-review">
                  <h3>{leitura.livro.titulo} ({leitura.livro.autor})</h3>
                  <p>
                    {leitura.livro.descricao
                      ? leitura.livro.descricao.slice(0, 180) + "..."
                      : "Sem descrição disponível."}
                  </p>
                </div>
              </Link>
            </div>
          ))
        ) : (
          <div className="sem-leituras">
            <p>Nenhum livro encontrado nesta lista.</p>
          </div>
        )}
      </div>
    </div>
  );
}
