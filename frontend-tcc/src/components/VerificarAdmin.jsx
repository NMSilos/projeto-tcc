import { jwtDecode } from "jwt-decode";

export default function VerificarAdmin({ children }){
    const dados = jwtDecode(localStorage.getItem("token"));

    if(dados.role == "ADMIN")
        return (children);
    else{
        localStorage.removeItem("token");
        window.location.href = "/";
    }
}