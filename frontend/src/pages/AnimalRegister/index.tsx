import { AxiosRequestConfig } from "axios";
import React, { useState } from "react";
import { requestBackEnd } from "../../util/request";
import "./styles.css";

function AnimalRegister() {
  const [nome, setNome] = useState("");
  const [idade, setIdade] = useState("");
  const [selectValueVacinado, setSelectValueVacinado] = useState("sim");
  const [selectValueCastrado, setSelectValueCastrado] = useState("sim");
  const [selectTipo, setSelectTipo] = useState("Cachorro");

  let userLocal;
  userLocal = JSON.parse(localStorage.getItem("user") || "");

  const handleSumit = (event) => {
    event.preventDefault();

    const animal = {
      nome: nome,
      idade: idade,
      tipoAnimal: selectTipo,
      vacinado: selectValueVacinado === "sim" ? true : false,
      castrado: selectValueCastrado === "sim" ? true : false,
      isAdopted: false,
    };

    const params: AxiosRequestConfig = {
      method: "POST",
      url: `/animals`,
      params: {
        id: userLocal.id,
      },
      data: { ...animal },
    };
    requestBackEnd(params).then((response) => {
      console.log("CADASTRADO");
    });
  };

  const handleSelect = (event) => {
    setSelectValueVacinado(event.target.value);
  };

  const handleSelectCadastrado = (event) => {
    setSelectValueCastrado(event.target.value);
  };
  const handleTipo = (event) => {
    setSelectTipo(event.target.value);
  };
  const handleIdade = (event) => {
    setIdade(event.target.value);
  };
  const handleNome = (event) => {
    setNome(event.target.value);
  };

  return (
    <div className="main-form-container">
      <form className="form" onSubmit={handleSumit}>
        <div>
          <h3>Inserir Animal</h3>
        </div>
        <label className="form-label">
          Nome:
          <input
            className="form-input"
            type="text"
            name="nome"
            onChange={handleNome}
          />
        </label>
        <label className="form-label">
          Idade:
          <input
            className="form-input"
            type="text"
            name="idade"
            onChange={handleIdade}
          />
        </label>
        <label className="form-label">
          Tipo:
          <select
            className="form-select"
            name="vacinado"
            id="vacinado"
            onChange={handleTipo}
          >
            <option value="Cachorro">Cachorro</option>
            <option value="Gato">Gato</option>
          </select>
        </label>
        <label className="form-label">
          Vacinado:
          <select
            className="form-select"
            name="vacinado"
            id="vacinado"
            onChange={handleSelect}
          >
            <option value="sim">Sim</option>
            <option value="nao">Não</option>
          </select>
        </label>
        <label className="form-label">
          Castrado:
          <select
            className="form-select"
            name="castrado"
            id="castrado"
            onChange={handleSelectCadastrado}
          >
            <option value="sim">Sim</option>
            <option value="nao">Não</option>
          </select>
        </label>
        <button className="form-button" type="submit">
          Cadastrar
        </button>
      </form>
    </div>
  );
}

export default AnimalRegister;
