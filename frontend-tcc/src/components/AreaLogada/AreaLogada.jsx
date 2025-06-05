import { Navigate, Outlet } from "react-router-dom";
import Header from "./Header";
import Footer from "./Footer";
import { jwtDecode } from "jwt-decode";

export default function AreaLogada() {
  const token = localStorage.getItem("token");
  const dados = jwtDecode(localStorage.getItem("token"));

  if (!token) {
    return <Navigate to="/" />;
  }

  return (
    <div className="area-logada">
      <Header />
      <main className="area-main">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}