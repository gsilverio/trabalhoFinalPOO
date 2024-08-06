import React, { useState } from "react";
import "./styles.css";
import { User } from "../../types/user";
import { requestBackEnd } from "../../util/request";
import { AxiosRequestConfig } from "axios";
import { useNavigate } from "react-router-dom";

function Login() {
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");

  const history = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    const params: AxiosRequestConfig = {
      method: "GET",
      url: `/users`,
      params: {
        email: login,
        password: password,
      },
    };
    requestBackEnd(params).then((response) => {
      localStorage.setItem("user", JSON.stringify(response.data));
      setTimeout(() => {
        history("/catalog");
        location.reload();
      }, 500);
    });
  };
  return (
    <section className="login-section">
      <div className="base-card">
        <div className="login-label">
          <h3>Login</h3>
        </div>
        <div className="login-menu">
          <form onSubmit={handleSubmit} className="form-container">
            <label>
              Email
              <input
                type="email"
                placeholder="Email"
                name="login-email"
                id="login-email"
                onChange={(event) => setLogin(event.target.value)}
              />
            </label>

            <label>
              Password
              <input
                type="password"
                placeholder="Password"
                name="password-email"
                id="password-email"
                onChange={(event) => setPassword(event.target.value)}
              />
            </label>
            <button type="submit" className="btn btn-login">
              Enviar
            </button>
          </form>
        </div>
      </div>
    </section>
  );
}

export default Login;
