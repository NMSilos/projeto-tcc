import { Star } from "lucide-react";
import { baseUrl } from "../utils/requests";
import { useEffect, useState } from "react";

export default function UltimasLeituras({ leitura }) {
  const livro = leitura.livro;
  const comentario = leitura.comentario;
  const estrelas = [1,2,3,4,5];
  const [capa, setCapa] = useState();

  async function carregarCapa() {
    const imagem = `${baseUrl}api/livros/livroImage/${livro.imagem}`;
    setCapa(imagem);
  }


  useEffect(() => {
    carregarCapa();
  }, [livro]);

  if(!livro) return null;

  return (
    <div className="leitura">
      <div className="leitura-conteudo">
        <div className="leitura-img">
          <img src={capa} alt="" />
          <div className="stars">
            {
              estrelas.map(item => {
                if(comentario == null) {
                  return <Star key={item} size={20} color="#b3b3b3" />;
                } else if(item <= comentario.nota) {
                  return <Star key={item} size={20} fill="gold" />;
                }
                return <Star key={item} size={20} color="#b3b3b3" />;
              })
            }
          </div>
        </div>
        <div className="leitura-review">
          <h3>{livro.titulo}</h3>
          <h3>Review:</h3>
          <p>{comentario ? comentario.texto : "Nenhum comentário atribuído"}</p>
        </div>
      </div>
    </div>
  );
}
