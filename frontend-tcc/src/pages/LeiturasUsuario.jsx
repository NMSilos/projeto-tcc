import { useParams } from "react-router";

export default function LeiturasUsuario() {
    
    const { user, lista } = useParams(); 
    
    return (
        <h1>Teste {user} e {lista}</h1>
    );
}