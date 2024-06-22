import { createContext, useEffect, useState, useContext } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { register, login, activate } from "../services/AuthService";

type AuthContextType = {
    token: string | null;
    registerUser:(fistName: string, lastName: string, email: string, password: string) => void;
    loginUser:(email: string, password: string) => void;
    activateAccount:(token: string) => void;
    logoutUser:() => void;
    isLoggedIn:() => boolean;
}

type Props = {children: React.ReactNode};

const AuthContext = createContext<AuthContextType>({} as AuthContextType);

export const AuthProvider = ({children}: Props) => {
    const navigate = useNavigate();
    const [token, setToken_] = useState<string | null>(localStorage.getItem("token"));
    const [isReady, setIsReady] = useState<boolean>(false);

    const setToken = (token: string | null)=>{
        setToken_(token);
    }
    useEffect(() => {
        if(token) {
            axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
            localStorage.setItem("token", token);
        } else {
            delete axios.defaults.headers.common["Authorization"];
            localStorage.removeItem("token");
        }
        setIsReady(true);
    }, [token]);

    const registerUser = async (fistName: string, lastName: string, email: string, password: string) => {
        await register(fistName,
            lastName, 
            email,
            password).then((res) => {
                if (res.status === 202) {
                    navigate("/activate")
                }
            }).catch((err) => {
                console.log(err);
            });
    }
    const loginUser = async (email: string, password: string) => {
        await login(email, password)
        .then((res) => {
            if(res) {
                console.log(res);
                localStorage.setItem("token", res?.token);
                setToken(res?.token);
                // TODO : redirect to the user destination 
                navigate("/");
            }
        });
    }
    const activateAccount = async (token: string) => {
        const data = await activate(token);
        console.log(data);
        if(data.token) {
            console.log(data?.token);
            setToken(data?.token);
            localStorage.setItem("token", data?.token);
            navigate("/");
        }
    }

    const logoutUser = () => {
        setToken(null);
        localStorage.removeItem("token");
        navigate("/login");
    }

    const isLoggedIn = () => {
        return !!token;
    }

    return (
        <AuthContext.Provider
         value={{token, registerUser, loginUser, activateAccount, logoutUser, isLoggedIn}}>
            {isReady ? children : null}
        </AuthContext.Provider>
    )
}

export const useAuth = () => useContext(AuthContext);