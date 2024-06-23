import axios from "axios";
const API_URL = "http://localhost:8080/api/v1/pharmacies";

export const registerPharmacy = async (    name: string,
    telephone: string,
    street: string,
    city: string,
    postalCode: string) => {
    const  data = JSON.stringify({
        "name": name,
        "telephone": telephone,
        "address": {
          "street": street,
          "city": city,
          "postalCode": postalCode
        }
      });
      let config = {
        method: 'post',
        maxBodyLength: Infinity,
        url:API_URL,
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        data : data
        };
        const response =await axios.request(config); 
        return response;    
}
