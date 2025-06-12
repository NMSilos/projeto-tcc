import { useEffect, useState } from "react";
import { requestLogado } from "../utils/requests";
import { useParams } from "react-router";

export default function TelaLivro() {

    const [livroAtual, setLivroAtual] = useState();
    const { id } = useParams();

    async function carregarLivro() {
        const livro = await requestLogado(`api/livros/buscar/${id}`, {}, "GET");
        setLivroAtual(livro);
    }

    useEffect(() => {
        carregarLivro();
    }, [livroAtual])

    return(
        <h1>{livroAtual.titulo}</h1>
    );
}