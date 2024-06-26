import axios from "axios";

axios.defaults.baseURL = "http://localhost:8088/api/v1/";
axios.defaults.headers.post["Content-Type"] = "application/json";

interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  imagesUrl: string[];
}

interface ErrorResponse {
  message: string;
  // Add other fields as necessary
}

// this function will fetch a product from the API based on the provided ID. If the product is found, it will return the product object. If an error occurs, it will return an ErrorResponse object with a message field describing the error.
export const getProduct = async (id: number): Promise<Product | ErrorResponse> => {
  try {
    const response = await axios.get<Product>(`products/${id}`);
    return response.data;
  } catch (error: any) {
    if (error.response && error.response.data) {
      return error.response.data as ErrorResponse;
    } else {
      return { message: "An unexpected error occurred" };
    }
  }
};


