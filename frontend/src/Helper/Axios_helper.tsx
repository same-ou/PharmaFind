import Axios from "axios";

Axios.defaults.baseURL = "http://localhost:8088/api/v1";
Axios.defaults.headers.post["Content-Type"] = "application/json";

export const getAuthToken = () => {
  return window.localStorage.getItem("auth-token");
};

export const setAuthToken = (token) => {
  window.localStorage.setItem("auth-token", token);
};

export const request = (method, url, data) => {
  const token = getAuthToken();
  let headers = {};
  if (token) {
    headers = {
      Authorization: `Bearer ${token}`
    };
  }

  // Use Axios to make the request
  return (method === 'post')? Axios.post(url, data, { headers }) : Axios.get(url, { headers });
};
