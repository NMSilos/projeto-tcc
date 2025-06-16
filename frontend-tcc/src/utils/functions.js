import { jwtDecode } from "jwt-decode";

export function criarLeitura(status, livro) {
    const usuario = jwtDecode(localStorage.getItem("token"));
    let dados = {};
    switch (status) {
        case "lidos":
            dados = {
                data_inicio: new Date().toISOString().slice(0, 10),
                data_termino: new Date().toISOString().slice(0, 10),
                pagina_atual: livro.paginas,
                abandonado: false,
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
                abandonado: false,
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
                abandonado: false,
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
                abandonado: true,
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