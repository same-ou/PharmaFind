import axios from "axios";

axios.defaults.baseURL = "http://localhost:8088/api/v1/";

// Define the Product interface
export interface Product {
  id?: number;
  name: string;
  description: string;
  price: number;
  imageUrls?: string[];
}

interface ErrorResponse {
  message: string;
  // Add other fields as necessary
}

// this function will fetch a product from the API based on the provided ID. If the product is found, it will return the product object. If an error occurs, it will return an ErrorResponse object with a message field describing the error.
export const getProduct = async (
  id: number
): Promise<Product | ErrorResponse> => {
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

//this function will fetch all the product from teh api

export const getProducts = async (): Promise<Product | ErrorResponse> => {
  try {
    const response = await axios.get<Product>("products");
    return response.data;
  } catch (error: any) {
    if (error.response && error.response.data) {
      return error.response.data as ErrorResponse;
    } else {
      return { message: "An unexpected error occurred" };
    }
  }
};

// this function will save a new product to the API. It takes a Product object as input and returns a Promise that resolves to the saved Product object if successful, or an ErrorResponse object if an error occurs.

export const saveProduct = async (
  product: Product,
  images: File[] | null
): Promise<Product> => {
  if (images && images.length > 5) {
    throw new Error("You can upload a maximum of 5 images.");
  }

  const formData = new FormData();
  formData.append(
    "product",
    new Blob([JSON.stringify(product)], { type: "application/json" })
  );
  if (images) {
    Array.from(images).forEach((file) => {
      formData.append("images", file);
    });
  }

  const response = await axios.post("/products", formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });

  return response.data;
};
