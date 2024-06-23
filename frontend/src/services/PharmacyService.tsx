import axios from "axios";
const API_URL = "http://localhost:8088/api/v1/pharmacies";

export const registerPharmacy = async (    name: string,
    telephone: string,
    street: string,
    city: string,
    postalCode: string) => {
   let data = {
    name: name,
    telephone: telephone,
    address: {
      street: street,
      city: city,
      postalCode: postalCode
    }
   }
    return axios.post(API_URL, data);
}
