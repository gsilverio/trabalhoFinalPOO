import { AxiosRequestConfig } from "axios";
import React, { useEffect, useState } from "react";
import { requestBackEnd } from "../../util/request";
import AnimalCard from "../../components/AnimalCard";

function Adotados() {
  const [listAdotados, setListAdotados] = useState([""]);
  let userLocal;
  const getAnimals = () => {
    userLocal = JSON.parse(localStorage.getItem("user") || "");
    const params: AxiosRequestConfig = {
      method: "GET",
      url: `/users/${userLocal.id}/animals`,
    };
    requestBackEnd(params).then((response) => {
      console.log(response.data);
      setListAdotados(response.data);
    });
  };
  useEffect(() => {
    getAnimals();
  }, []);
  console.log(listAdotados);
  return (
    <div>
      <AnimalCard animals={listAdotados} onDelete={""} />
    </div>
  );
}

export default Adotados;
