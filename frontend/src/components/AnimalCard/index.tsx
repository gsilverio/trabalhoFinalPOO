import React, { useEffect, useState } from "react";
import "./styles.css";
import { AxiosRequestConfig } from "axios";
import { requestBackEnd } from "../../util/request";
import { Link, useNavigate } from "react-router-dom";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Select from "react-select";
import { Navigate } from "react-router-dom";
import { Animal } from "../../types/animal";
import { Option } from "../../types/option";

function AnimalCard({ animals, onDelete }) {
  const [nome, setNome] = useState("");
  const [idade, setIdade] = useState("");
  const [selectValueVacinado, setSelectValueVacinado] = useState("");
  const [selectValueCastrado, setSelectValueCastrado] = useState("");

  const [defaultVacinado, setDefaultVacinado] = useState("");
  const [castrado, setCastrado] = useState<Boolean>();
  const [vacinado, setVacinado] = useState<Boolean>();

  const [selectTipo, setSelectTipo] = useState();
  const [animalId, setAnimalId] = useState();
  const [animal, setAnimal] = useState<Animal>();

  let user;

  if (localStorage.getItem("user")) {
    user = JSON.parse(localStorage.getItem("user") || "");
  }
  const history = useNavigate();

  const handleAdoptClick = (x) => {
    if (user) {
      const params: AxiosRequestConfig = {
        method: "POST",
        url: `/users/adotar`,
        params: {
          animalId: x.id,
          userId: user.id,
        },
      };
      requestBackEnd(params);
    } else {
      history("/login");
    }
  };

  const options = [
    { value: "Sim", label: "Sim" },
    { value: "Não", label: "Não" },
  ];

  const handleEdit = () => {
    const params: AxiosRequestConfig = {
      method: "GET",
      url: `/animals/${animalId}`,
    };
    requestBackEnd(params).then((response) => {
      setAnimal(response.data);
    });
  };

  console.log(defaultVacinado);
  const [show, setShow] = useState(false);

  const handleSubmitEditAnimal = () => {
    let data;
    animal
      ? (data = {
          id: animal.id,
          nome: nome === "" ? animal.nome : nome,
          idade: idade === "" ? animal.idade : idade,
          tipoAnimal: selectTipo === undefined ? animal.tipoAnimal : selectTipo,
          vacinado: vacinado === undefined ? animal.vacinado : vacinado,
          castrado: castrado === undefined ? animal.castrado : castrado,
          adopted: animal.adopted,
        })
      : "";

    const params: AxiosRequestConfig = {
      method: "PUT",
      url: `/animals/${animalId}`,
      data: data,
    };
    requestBackEnd(params).then((response) => {
      history("/catalog");
    });
    console.log(data);
    setShow(false);
  };

  const handleShow = (x) => {
    setAnimalId(x.id);
    setShow(true);
  };

  const handleClose = () => {
    setShow(false);
  };

  useEffect(() => {
    if (show) {
      handleEdit();
    }
  }, [show]);

  const handleSelect = (defaultVacinado) => {
    console.log(defaultVacinado);
    setVacinado(defaultVacinado.value === "Sim" ? true : false);
  };

  const handleSelectCastrado = (defaultCastrado) => {
    setCastrado(defaultCastrado.value === "Sim" ? true : false);
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
    <div className="row row-cols-lg-4 animal-container">
      {animals.map((x, index) =>
        x.adopted ? (
          ""
        ) : (
          <div key={index} className="base-card cards col">
            {user ? (
              user.tipo == "Receptor" ? (
                ""
              ) : (
                <>
                  <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                      <Modal.Title>Editar Animal</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                      {animal ? (
                        <>
                          <label className="form-label">
                            Nome:
                            <input
                              className="form-input"
                              type="text"
                              name="nome"
                              defaultValue={animal.nome}
                              onChange={handleNome}
                            />
                          </label>
                          <label className="form-label">
                            Idade:
                            <input
                              className="form-input"
                              defaultValue={animal.idade}
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
                              defaultValue={animal.tipoAnimal}
                            >
                              <option value="Cachorro">Cachorro</option>
                              <option value="Gato">Gato</option>
                            </select>
                          </label>
                          <label className="form-label">
                            Vacinado:
                            <Select
                              options={options}
                              placeholder={animal.vacinado ? "Sim" : "Não"}
                              onChange={handleSelect}
                            />
                          </label>
                          <label className="form-label">
                            Castrado:
                            <Select
                              options={options}
                              placeholder={animal.castrado ? "Sim" : "Não"}
                              onChange={handleSelectCastrado}
                            />
                          </label>
                        </>
                      ) : (
                        ""
                      )}
                    </Modal.Body>
                    <Modal.Footer>
                      <Button variant="secondary" onClick={handleClose}>
                        Close
                      </Button>
                      <Button
                        variant="primary"
                        onClick={handleSubmitEditAnimal}
                      >
                        Save Changes
                      </Button>
                    </Modal.Footer>
                  </Modal>
                  <div className="menu-container">
                    <a onClick={() => handleShow(x)}>
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="#000"
                        className="bi bi-pen"
                        viewBox="0 0 16 16"
                      >
                        <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001m-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708z" />
                      </svg>
                    </a>

                    <Link to="/catalog">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="20"
                        height="20"
                        fill="#000"
                        className="bi bi-x-lg"
                        viewBox="0 0 16 16"
                        onClick={() => onDelete(x.id)}
                      >
                        <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8z" />
                      </svg>
                    </Link>
                  </div>
                </>
              )
            ) : (
              ""
            )}

            <div>
              {x.tipoAnimal === "Cachorro" ? (
                <div className="image-container">
                  <img src="/assets/images/cachorro.png" />
                </div>
              ) : (
                <div className="image-container">
                  <img src="/assets/images/gato.png" />
                </div>
              )}
              <h4>Nome: {x.nome}</h4>
              <h4>Idade: {x.idade}</h4>

              {x.vacinado ? <h4>Vacinado: Sim</h4> : <h4>Vacinado: Nao</h4>}
              {x.castrado ? <h4>Castrado: Sim</h4> : <h4>Castrado: Nao</h4>}
            </div>
            {user ? (
              user.tipo === "Receptor" ? (
                x.isAdopted ? (
                  ""
                ) : (
                  <button
                    className="btn button-adopt"
                    onClick={() => handleAdoptClick(x)}
                  >
                    Adotar
                  </button>
                )
              ) : (
                ""
              )
            ) : (
              ""
            )}
          </div>
        )
      )}
    </div>
  );
}

export default AnimalCard;
