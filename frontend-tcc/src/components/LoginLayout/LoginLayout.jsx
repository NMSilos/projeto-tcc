import React from 'react';
import './LoginLayout.css';

export default function LoginLayout({ children }) {
  return (
    <div className="login-layout">
      <main className="login-main">
        {children}
      </main>
      <footer className="login-footer">
        © {new Date().getFullYear()}. Todos os direitos reservados.
      </footer>
    </div>
  );
}