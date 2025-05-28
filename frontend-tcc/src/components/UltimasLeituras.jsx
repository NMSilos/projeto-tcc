export default function UltimasLeituras({ leitura }) {
  const livro = leitura.livro;
  const comentario = leitura.comentario;
  const estrelas = [1,2,3,4,5];
  
  if(!livro) return null;

  return (
    <div className="leitura">
      <div className="leitura-conteudo">
        <div className="leitura-img">
          <p>{livro.titulo}</p>
          <div className="stars">
            {
              estrelas.map(item => {
                if(comentario == null) {
                  return "☆";
                } else if(item <= comentario.nota) {
                  return "★";
                }
                return "☆"; 
              })
            }
          </div>
        </div>
        <div className="leitura-review">
          <h3>Review:</h3>
          <p>{comentario ? comentario.texto : "Nenhum comentário atribuído"}</p>
        </div>
      </div>
    </div>
  );
}

//<img src={leitura.imagem} alt={leitura.titulo} />
