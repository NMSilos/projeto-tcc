import { useEffect, useState } from "react";
import { requestLogado } from "../utils/requests";
import { useParams } from "react-router";
import lidosIcon from "../assets/icons/lidos.svg"
import lendoIcon from "../assets/icons/lendo.svg"
import pretendoLerIcon from "../assets/icons/pretendo_ler.svg"
import abandonadosIcon from "../assets/icons/abandonados.svg"
import defaultUser from '../assets/default-user.jpg';
import "./styles/TelaLivro.css";
import { toast, ToastContainer } from "react-toastify";
import { criarLeitura } from "../utils/functions";

export default function TelaLivro() {

    const [livroAtual, setLivroAtual] = useState();
    const [leituras, setLeituras] = useState();
    const { isbn } = useParams();
    const estrelas = [1, 2, 3, 4, 5];

    const [dropdownAberto, setDropdownAberto] = useState(false);

    const leiturasFiltradas = (leituras || []).filter(leitura =>
        (leitura.data_termino != null && leitura.pagina_atual === livroAtual.paginas) ||
        leitura.abandonado
    );

    function toggleDropdown() {
        setDropdownAberto(!dropdownAberto);
    }

    async function selecionarStatus(status) {
        const dados = criarLeitura(status, livroAtual);
        const response = await requestLogado("api/leituras/criar-leitura", dados, "POST");

        if(response) {
            toast.success(`Livro adicionado à sua lista!`);
        } else {
            toast.error("Erro ao criar leitura, tente mais tarde");
        }

        setDropdownAberto(false);
    }

    async function carregarLivro() {
        const livro = await requestLogado(`api/livros/buscar-isbn/${isbn}`, {}, "GET");
        setLivroAtual(livro);
        setLeituras(livro.leituras);
    }

    useEffect(() => {
        carregarLivro();
    }, [isbn]);

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
                                    return "★";
                                }
                                return "☆";
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
            <div className="livro-reviews">
                <h2>{`Reviews (${leiturasFiltradas.length})`}</h2>
                <div className="reviews">
                    {leiturasFiltradas.map((leitura) => (
                            <div key={leitura.id} className="review-item">
                                <div className="review-user">
                                    <img src={leitura.usuario.imagem ? leitura.usuario.imagem : defaultUser} alt="" />
                                    <span>
                                        {leitura.comentario ? (estrelas.map(item => item <= leitura.comentario.nota ? "★" : "☆")) : "☆☆☆☆☆"}
                                    </span>
                                </div>
                                <div className="review-body">
                                    <p className="username">{leitura.usuario.username}</p>
                                    <p className="review-text">{leitura.comentario ? leitura.comentario.texto : <span>Nenhum comentário atribuído</span>}</p>
                                </div>
                            </div>
                        ))
                    }
                </div>
            </div>
            <ToastContainer />
        </div>
    );
}