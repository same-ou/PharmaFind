import { createContext, useEffect, useState, useContext } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { register, login, activate } from "../services/AuthService";
import { UserProfile } from "@/models/user";

type AuthContextType = {
    user: UserProfile | null;
    token: string | null;
    registerUser:(fistName: string, lastName: string, email: string, password: string, role: string) => void;
    loginUser:(email: string, password: string) => void;
    activateAccount:(token: string) => void;
    logoutUser:() => void;
    isLoggedIn:() => boolean;
}
type Props = {children: React.ReactNode};

const AuthContext = createContext<AuthContextType>({} as AuthContextType);

export const AuthProvider = ({children}: Props) => {
    const navigate = useNavigate();
    const [token, setToken_] = useState<string | null>(null);
    const [user, setUser] = useState<UserProfile | null>(null);

    const [isReady, setIsReady] = useState<boolean>(false);

    const setToken = (token: string | null)=>{
        setToken_(token);
    }

    useEffect(() => {
        const token = localStorage.getItem("token");
        const user = localStorage.getItem("user");
        if(user && token) {
            setUser(JSON.parse(user));
            setToken(token);
            axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
        } else {
            delete axios.defaults.headers.common["Authorization"];
        }
        setIsReady(true);
    }, []);

    const registerUser = async (fistName: string, lastName: string, email: string, password: string, role: string) => {
        await register(fistName,
            lastName, 
            email,
            password,
            role).then((res) => {
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
                const userObj: UserProfile = {
                    firstName: res?.user.firstName,
                    lastName: res?.user.lastName,
                    email: res?.user.email,
                    role: res?.user.role
                }
                localStorage.setItem("user", JSON.stringify(userObj));
                setUser(userObj);
                navigate("/");
            }
        });
    }
    const activateAccount = async (token: string) => {
        await activate(token).then((res) => {
            if(res.status === 200){
                console.log(res);
                localStorage.setItem("token", res?.token);
                setToken(res?.token);
                const userObj: UserProfile = {
                    firstName: res?.user.firstName,
                    lastName: res?.user.lastName,
                    email: res?.user.email,
                    role: res?.user.role
                }
                localStorage.setItem("user", JSON.stringify(userObj));
                setUser(userObj);
                navigate("/");  
            }
        });
    }

    const logoutUser = () => {
        setToken(null);
        setUser(null);
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        navigate("/clients/login");
    }

    const isLoggedIn = () => {
        return !!token;
    }

    return (
        <AuthContext.Provider
         value={{user,token, registerUser, loginUser, activateAccount, logoutUser, isLoggedIn}}>
            {isReady ? children : null}
        </AuthContext.Provider>
    )
}

export const useAuth = () => useContext(AuthContext);