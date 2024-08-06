import React from "react";
import "./styles.css";
import { NavLink, useNavigate } from "react-router-dom";

function Navbar() {
  let user;
  const history = useNavigate();
  if (localStorage.getItem("user")) {
    user = JSON.parse(localStorage.getItem("user") || "");
  }

  const handleLogout = () => {
    localStorage.clear();
    location.reload();
    history("/");
  };

  return (
    <div className="nabar-main-container">
      <div className="navbar-container">
        <div>
          <h3>AdotSystem</h3>
        </div>
        <div className="menu-items">
          <ul>
            <li>
              <NavLink to="/">Home</NavLink>
            </li>
            <li>|</li>
            <li>
              <NavLink to="/catalog">Catalogo</NavLink>
            </li>
            {user ? (
              user.tipo === "Receptor" ? (
                <>
                  <li>|</li>
                  <li>
                    <NavLink to="/adotados">Adotados</NavLink>
                  </li>
                </>
              ) : (
                ""
              )
            ) : (
              ""
            )}
            <li>|</li>
            <li>
              {user ? (
                <NavLink to="/" onClick={handleLogout}>
                  Logout
                </NavLink>
              ) : (
                <NavLink to="/login">Login</NavLink>
              )}
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Navbar;
