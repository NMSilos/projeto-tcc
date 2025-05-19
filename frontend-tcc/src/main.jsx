import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { BrowserRouter, Route, Routes } from 'react-router'
import { GoogleOAuthProvider } from '@react-oauth/google'
import Login from './pages/Login'
import CadastroUsuario from './pages/CadastroUsuario'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <GoogleOAuthProvider clientId="618288495398-mulk3hrit9nhb79h96jsnm5g7su51lm9.apps.googleusercontent.com">
      <BrowserRouter>
        <Routes>
          <Route index element={<Login />} />
            <Route path="/cadastro" element={<CadastroUsuario />} />
        </Routes>
      </BrowserRouter>
    </GoogleOAuthProvider>
  </StrictMode>,
)
