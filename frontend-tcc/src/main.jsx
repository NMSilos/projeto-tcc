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
import BuscarLivros from './pages/BuscarLivros'
import TelaLivro from './pages/TelaLivro'
import ViewAdmin from './pages/ViewAdmin'
import AdminLivros from './pages/AdminLivros'
import CadastroLivro from './pages/CadastroLivro'
import LeiturasUsuario from './pages/LeiturasUsuario'
import EditarLeitura from './pages/EditarLeitura'
import SugerirLivro from './pages/SugerirLivro'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <GoogleOAuthProvider clientId="618288495398-mulk3hrit9nhb79h96jsnm5g7su51lm9.apps.googleusercontent.com">
      <BrowserRouter>
        <Routes>
          <Route index element={<Login />} />
          <Route path="/cadastro" element={<CadastroUsuario />} />
          <Route path="/" element={<AreaLogada />}>  
            <Route path="/perfil/:user" element={<PerfilUsuario />} />
            <Route path="/perfil/:user/editar" element={<EditarUsuario />} />
            <Route path="/perfil/:user/:lista" element={<LeiturasUsuario />} />
            <Route path="/perfil/:user/leitura/:id/editar" element={<EditarLeitura />} />
            <Route path="/perfil/:user/sugerir-livro" element={<SugerirLivro />} />
            <Route path="/buscar" element={<BuscarLivros />}/>
            <Route path="/livros/:isbn" element={<TelaLivro />}/>
          </Route>
          <Route path="/admin" element={<VerificarAdmin><AreaLogada /></VerificarAdmin>} >
            <Route index element={<ViewAdmin />} />
            <Route path='/admin/livros' element={<AdminLivros />}/>
            <Route path='/admin/livros/novo' element={<CadastroLivro />}/>
          </Route>
        </Routes>
      </BrowserRouter>
    </GoogleOAuthProvider>
  </StrictMode>,
)
