import { jwtDecode } from "jwt-decode";
import { requestLogado } from "./requests";
import { toast } from "react-toastify";

export function criarLeitura(status, livro) {
    const usuario = jwtDecode(localStorage.getItem("token"));
    let dados = {};
    switch (status) {
        case "lidos":
            dados = {
                data_inicio: new Date().toISOString().slice(0, 10),
                data_termino: new Date().toISOString().slice(0, 10),
                pagina_atual: livro.paginas,
                status: "LIDO",
                livro: {
                    id: livro.id
                },
                usuario: {
                    id: usuario.id
                }
            }
            break;
        case "lendo":
            dados = {
                data_inicio: new Date().toISOString().slice(0, 10),
                data_termino: null,
                pagina_atual: 1,
                status: "LENDO",
                livro: {
                    id: livro.id
                },
                usuario: {
                    id: usuario.id
                }
            }
            break;
        case "pretendo":
            dados = {
                data_inicio: null,
                data_termino: null,
                pagina_atual: 0,
                status: "PRETENDO_LER",
                livro: {
                    id: livro.id
                },
                usuario: {
                    id: usuario.id
                }
            }
            break;
        case "abandonado":
            dados = {
                data_inicio: new Date().toISOString().slice(0, 10),
                data_termino: new Date().toISOString().slice(0, 10),
                pagina_atual: 0,
                status: "ABANDONADO",
                livro: {
                    id: livro.id
                },
                usuario: {
                    id: usuario.id
                }
            }
            break;
        default:
            break;
    }
    return dados;
}

export default async function excluirLeitura(id) {
    const user = jwtDecode(localStorage.getItem("token"));
    const data = {
        id: id 
    }
    toast.success("Leitura removida");
    const response = await requestLogado(`api/leituras/excluir-por-livro/${user.id}`, data, "DELETE");
}

export function normalizarStatus(apiStatus) {
  switch (apiStatus) {
    case "LIDO":
      return "lidos";
    case "LENDO":
      return "lendo";
    case "PRETENDO_LER":
      return "pretendo";
    case "ABANDONADO":
      return "abandonado";
    default:
      return "lendo";
  }
}

export function formarEnumStatus(apiStatus) {
  switch (apiStatus) {
    case "lidos":
      return "LIDO";
    case "lendo":
      return "LENDO";
    case "pretendo":
      return "PRETENDO_LER";
    case "abandonado":
      return "ABANDONADO";
    default:
      return "";
  }
}
