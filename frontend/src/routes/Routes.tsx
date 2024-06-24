import { createBrowserRouter } from "react-router-dom";
import App from "@/App";
import Login from "@/pages/auth/Login";
import Register from "@/pages/auth/Register";
import Activate from "@/pages/auth/Activate";
import Home from "@/pages/Home/Home";
import ProductPage from "@/pages/Product/ProductPage";
import NewProduct from "@/pages/Product/NewProduct";
import Product from "@/pages/Product/Product";
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
        ,
        {
            path: "/product",
            element: <ProductPage/>,
        },
        {
            path: "/new-product",
            element: <NewProduct/>,
        },
        {
            path: "/card",
            element: <Product/>,
        }
]);