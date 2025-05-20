import { Navigate, Outlet } from "react-router-dom";
import Header from "./Header";
import Footer from "./Footer";

export default function AreaLogada() {
  const token = localStorage.getItem("token");

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