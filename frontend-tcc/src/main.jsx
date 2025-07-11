import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { BrowserRouter, Route, Routes } from 'react-router'
import { GoogleOAuthProvider } from '@react-oauth/google'
import Login from './pages/Login'
import CadastroUsuario from './pages/CadastroUsuario'
import PerfilUsuario from './pages/PerfilUsuario'
import AreaLogada from './components/AreaLogada/AreaLogada'
import EditarUsuario from './pages/EditarUsuario'
import VerificarAdmin from './components/VerificarAdmin'
import TesteAdmin from './pages/TesteAdmin'
import BuscarLivros from './pages/BuscarLivros'
import TelaLivro from './pages/TelaLivro'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <GoogleOAuthProvider clientId="618288495398-mulk3hrit9nhb79h96jsnm5g7su51lm9.apps.googleusercontent.com">
      <BrowserRouter>
        <Routes>
          <Route index element={<Login />} />
          <Route path="/cadastro" element={<CadastroUsuario />} />
          <Route path="/" element={<AreaLogada />}>  
            <Route path="/perfil/:user" element={<PerfilUsuario />} />
            <Route path="/perfil/:user/:status" element={<PerfilUsuario />} />
            <Route path="/perfil/:user/editar" element={<EditarUsuario />} />
            <Route path="/buscar" element={<BuscarLivros />}/>
            <Route path="/livros/:isbn" element={<TelaLivro />}/>
          </Route>
          <Route path="/admin" element={<VerificarAdmin><AreaLogada /></VerificarAdmin>} >
            <Route index element={<TesteAdmin />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </GoogleOAuthProvider>
  </StrictMode>,
)
