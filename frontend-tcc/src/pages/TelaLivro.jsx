import { useEffect, useState } from "react";
import { requestLogado } from "../utils/requests";
import { useParams } from "react-router";
import lidosIcon from "../assets/icons/lidos.svg"
import lendoIcon from "../assets/icons/lendo.svg"
import pretendoLerIcon from "../assets/icons/pretendo_ler.svg"
import abandonadosIcon from "../assets/icons/abandonados.svg"
import "./styles/TelaLivro.css";

export default function TelaLivro() {

    const [livroAtual, setLivroAtual] = useState();
    const { id } = useParams();
    const estrelas = [1, 2, 3, 4, 5];

    const [dropdownAberto, setDropdownAberto] = useState(false);

    function toggleDropdown() {
        setDropdownAberto(!dropdownAberto);
    }

    function selecionarStatus(status) {
        console.log("Selecionado:", status);
        // aqui você pode chamar a função que salva o status
        setDropdownAberto(false);
    }

    async function carregarLivro() {
        const livro = await requestLogado(`api/livros/buscar/${id}`, {}, "GET");
        setLivroAtual(livro);
        console.log(JSON.stringify(livro))
    }

    useEffect(() => {
        carregarLivro();
    }, [id]);

    if (!livroAtual) {
        return <p>Carregando livro...</p>
    }

    return (
        <div className="tela-livro-container">
            <div className="livro-topo">
                <div className="livro-capa">
                    <img src={"https://m.media-amazon.com/images/I/511+-lOOtsL._SY445_SX342_ControlCacheEqualizer_.jpg"} alt={livroAtual.titulo} />
                    <div className="livro-estrelas">
                        {
                            estrelas.map(item => {
                                if (item <= livroAtual.avaliacao) {
                                    return <span>★</span>;
                                }
                                return <span>☆</span>;
                            })
                        }
                    </div>
                </div>
                <div className="livro-info">
                    <h1>{livroAtual.titulo}</h1>
                    <p className="livro-autor">{livroAtual.autor}</p>
                    <hr />
                    <p className="livro-dados">Editora {livroAtual.editora}, {livroAtual.ano_publicacao}</p>
                    <p className="livro-dados">Páginas: {livroAtual.paginas}</p>
                    <div className="livro-acoes">
                        <div className="dropdown-adicionar">
                            <button className="btn-adicionar" onClick={toggleDropdown}>
                                Adicionar
                            </button>
                            <div className={`dropdown-menu ${dropdownAberto ? "aberto" : ""}`}>
                                <div onClick={() => selecionarStatus("lidos")}>
                                    <img src={lidosIcon} alt="Lidos" />
                                    <span>Lidos</span>
                                </div>
                                <div onClick={() => selecionarStatus("lendo")}>
                                    <img src={lendoIcon} alt="Lendo" />
                                    <span>Lendo</span>
                                </div>
                                <div onClick={() => selecionarStatus("pretendo")}>
                                    <img src={pretendoLerIcon} alt="Pretendo Ler" />
                                    <span>Pretendo Ler</span>
                                </div>
                                <div onClick={() => selecionarStatus("abandonado")}>
                                    <img src={abandonadosIcon} alt="Abandonado" />
                                    <span>Abandonado</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="livro-descricao">
                <h2>Descrição</h2>
                <p>{livroAtual.descricao}</p>
            </div>
        </div>
    );
}