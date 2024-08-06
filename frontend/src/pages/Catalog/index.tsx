import "./styles.css";
import { AxiosRequestConfig } from "axios";
import React, { useCallback, useEffect, useState } from "react";
import { requestBackEnd } from "../../util/request";
import AnimalCard from "../../components/AnimalCard";
import { Link, useNavigate } from "react-router-dom";
import { User } from "../../types/user";

function Catalog() {
  const [allAnimals, setAllAnimals] = useState([""]);
  const [animalByUser, setAnimalByUser] = useState([""]);

  let userLocal;
  let getAnimalsByUser = () => {};

  if (localStorage.getItem("user")) {
    userLocal = JSON.parse(localStorage.getItem("user") || "");
    getAnimalsByUser = () => {
      const params: AxiosRequestConfig = {
        method: "GET",
        url: `/users/${userLocal.id}/animals`,
      };
      requestBackEnd(params).then((response) => {
        setAnimalByUser(response.data);
      });
    };
  }

  const getAnimals = () => {
    const params: AxiosRequestConfig = {
      method: "GET",
      url: `/animals`,
    };
    requestBackEnd(params).then((response) => {
      setAllAnimals(response.data);
    });
  };

  useEffect(() => {
    getAnimals();
    getAnimalsByUser();
  }, []);

  const handleDelete = (id) => {
    const params: AxiosRequestConfig = {
      method: "DELETE",
      url: `/animals/${id}`,
    };
    requestBackEnd(params).then(() => {
      getAnimalsByUser();
    });
  };

  return (
    <div className="catalog-main-container">
      <div className="catalog-container">
        {userLocal ? (
          userLocal.tipo === "Doador" ? (
            <div className="menu-catalog">
              <button className="btn">
                <Link to="/catalog/register">
                  <h3>Cadastrar</h3>
                </Link>
              </button>
            </div>
          ) : (
            ""
          )
        ) : (
          ""
        )}

        <div className=" animals-info">
          {userLocal ? (
            userLocal.tipo === "Doador" ? (
              <AnimalCard animals={animalByUser} onDelete={handleDelete} />
            ) : (
              <AnimalCard animals={allAnimals} onDelete={handleDelete} />
            )
          ) : (
            <AnimalCard animals={allAnimals} onDelete={handleDelete} />
          )}
        </div>
      </div>
    </div>
  );
}

export default Catalog;
