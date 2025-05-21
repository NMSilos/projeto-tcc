import { useEffect, useState } from "react";

export default function UltimasLeituras({ leitura }) {
    const livro = leitura.livro;
    console.log(leitura);
    console.log(livro);
    
    if(!livro) return null;

  return (
    <div className="leitura">
      <div className="leitura-conteudo">
        <div className="leitura-img">
          <p>{livro.titulo}</p>
          <div className="stars">★ ★ ★ ★ ☆</div>
        </div>
        <div className="leitura-review">
          <h3>Review:</h3>
          <p>DSJFIOJIOASDFJ</p>
        </div>
      </div>
    </div>
  );
}

//<img src={leitura.imagem} alt={leitura.titulo} />
