import axios from "axios";

const API_URL = "http://localhost:8088/api/v1/auth/";
export const register = async (fistName: string, lastName: string, email: string, password: string) => {
    try {
        const response = await axios.post(API_URL + "register", {
            fistName,
            lastName,
            email,
            password
        });
        return response.data;
    } catch (error: any) {
        return error.response.data;
    }
}

export const login = async (email: string, password: string) => {
    try {
        const response = await axios.post(API_URL + "login", {
            email,
            password
        });
        return response.data;
    } catch (error: any) {
        return error.response.data;
    }
}

export const activate = async (token: string) => {
    try {
        const response = await axios.get(API_URL + "activate?token=" + token);
        return response.data;
    } catch (error: any) {
        return error.response.data;
    }
}