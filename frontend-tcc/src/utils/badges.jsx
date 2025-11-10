import graduated from "../assets/graduation-hat.png";
import books from "../assets/books.png";
import trophy from "../assets/trophy.png";

import { contadorSugestoes } from "./functions";

export function buscarBadge(sugestoes) {
  const count = contadorSugestoes(sugestoes || []);

  if (count >= 15) {
    return {
      icon: <img src={trophy} className="emoji-badge" alt="Troféu" />,
      label: "Guardião da Biblioteca",
      variant: "legendary"
    };
  } else if (count >= 5) {
    return {
      icon: <img src={books} className="emoji-badge" alt="Livros" />,
      label: "Apoia a Comunidade",
      variant: "epic"
    };
  } else if (count >= 1) {
    return {
      icon: <img src={graduated} className="emoji-badge" alt="Capelo" />,
      label: "Colaborador",
      variant: "common"
    };
  }

  return null;
}
