import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const requestBackEnd = (config1) => {
  return axios({ ...config1, baseURL: BASE_URL });
};
