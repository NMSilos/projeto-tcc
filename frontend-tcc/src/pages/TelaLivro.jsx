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
import excluirLeitura, { criarLeitura } from "../utils/functions";
import { jwtDecode } from "jwt-decode";
import { Cross, Star, StarHalf, Trash2, X } from "lucide-react";

export default function TelaLivro() {

    const [livroAtual, setLivroAtual] = useState();
    const [leituras, setLeituras] = useState();
    const [leituraExistente, setLeituraExistente] = useState();
    const { isbn } = useParams();
    const estrelas = [1, 2, 3, 4, 5];

    const [dropdownAberto, setDropdownAberto] = useState(false);

    const leiturasFiltradas = (leituras || []).filter(leitura =>
        (leitura.data_termino != null && leitura.pagina_atual === livroAtual.paginas) ||
        leitura.abandonado
    );

    function mostrarDropdown() {
        setDropdownAberto(!dropdownAberto);
    }

    async function selecionarStatus(status) {
        const dados = criarLeitura(status, livroAtual);
        const response = await requestLogado("api/leituras/criar-leitura", dados, "POST");

        if (response) {
            toast.success(`Livro adicionado à sua lista!`);
        } else {
            toast.error("Erro ao criar leitura, tente mais tarde");
        }
        setDropdownAberto(false);
        verificarLeitura();
    }

    async function carregarLivro() {
        const livro = await requestLogado(`api/livros/buscar-isbn/${isbn}`, {}, "GET");
        setLivroAtual(livro);
        setLeituras(livro.leituras);
    }

    async function verificarLeitura() {
        if (!livroAtual || !livroAtual.id) return;
        const token = jwtDecode(localStorage.getItem("token"));
        const usuario = await requestLogado(`api/usuarios/buscar/id/${token.id}`, {}, "GET");
        setLeituraExistente(usuario.leituras.some(userLeitura => userLeitura.livro.id === livroAtual.id));
    }

    async function removeAndRefresh() {
        await excluirLeitura(livroAtual.id);
        verificarLeitura();
    }

    useEffect(() => {
        carregarLivro();
    }, [isbn]);

    useEffect(() => {
        if (livroAtual && livroAtual.id) {
            verificarLeitura();
        }
    }, [livroAtual])

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
                                    return <Star key={item} fill="gold"/>;
                                }
                                return <Star color="#dadada" key={item}/>;
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
                        {!leituraExistente && (
                            <div className="dropdown-adicionar">
                                <button className="btn-adicionar" onClick={mostrarDropdown}>
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
                        )}
                        {leituraExistente && (
                            <div className="dropdown-adicionar">
                                <button className="btn-inativo">
                                    <span className="btn-texto">Adicionado à sua biblioteca</span>
                                    <span className="btn-divisor">
                                        <X color="#7c2fd0" size={20} onClick={removeAndRefresh} />
                                    </span>
                                </button>
                            </div>
                        )}
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
                                    {leitura.comentario ? (
                                        estrelas.map(item => item <= leitura.comentario.nota ? <Star size={12} fill="gold" /> : <Star size={12} color="#b3b3b3"/>)
                                        ): ""}
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
            <ToastContainer pauseOnHover={false}/>
        </div>
    );
}