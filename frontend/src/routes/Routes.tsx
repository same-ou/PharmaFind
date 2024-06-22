import { createBrowserRouter } from "react-router-dom";
import App from "@/App";
import Login from "@/pages/auth/Login";
import Register from "@/pages/auth/Register";
import Activate from "@/pages/auth/Activate";
import Home from "@/pages/Home/Home";
export const router = createBrowserRouter([
        {
            path: "/",
            element: <App />,
            children:[
                {path:"", element:<Home /> },
                {path: "login", element: <Login />},
                {path:"register", element: <Register />},
                {path: "activate", element: <Activate />},
            ]
        }
]);