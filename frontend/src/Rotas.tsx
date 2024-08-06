import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import Home from "./pages/Home";

import Login from "./pages/Login";
import AnimalRegister from "./pages/AnimalRegister";
import Catalog from "./pages/Catalog";
import Navbar from "./components/Navbar";
import Adotados from "./pages/Adotados";

function Rotas() {
  return (
    <>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/catalog" element={<Catalog />} />
          <Route path="catalog/register" element={<AnimalRegister />} />
          <Route path="/login" element={<Login />} />
          <Route path="/adotados" element={<Adotados />} />
        </Routes>
      </Router>
    </>
  );
}

export default Rotas;
