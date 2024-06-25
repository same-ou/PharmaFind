import { createBrowserRouter } from "react-router-dom";
import App from "@/App";
import ClientLogin from "@/pages/auth/ClientLogin";
import ClientRegistration from "@/pages/auth/ClientRegistration";
import PharmacistRegistration from "@/pages/auth/PharmacistRegistration";
import PharmacistLogin from "@/pages/auth/PharmacistLogin";
import RegisterPharmacy from "@/pages/pharmacy/RegisterPharmacy";
import NotFound from "@/pages/NotFound";
import Error from "@/pages/Error";
import PharmacistDashboard from "@/pages/pharmacy/PharmacistDashboard";
import DashboardOrders from "@/pages/pharmacy/DashboardOrders";
import DashboardProducts from "@/pages/pharmacy/DashboardProducts";
import DashboardSettings from "@/pages/pharmacy/DashboardSettings";

import Activate from "@/pages/auth/Activate";
import Home from "@/pages/Home/Home";
import ProductPage from "@/pages/Product/ProductPage";
import NewProduct from "@/pages/Product/NewProduct";
import Product from "@/pages/Product/Product";

export const router = createBrowserRouter([
        {
            path: "/",
            element: <App />,
            errorElement: <Error />,
            children:[
                {path:"", element:<Home /> },
                {path: "clients/login", element: <ClientLogin />},
                {path:"clients/register", element: <ClientRegistration />},
                {path: "pharmacists/register", element: <PharmacistRegistration />},
                {path: "pharmacists/login", element: <PharmacistLogin />},
                {path: "pharmacists/register-pharmacy", element: <RegisterPharmacy />},
                {
                    path: "pharmacists/dashboard",
                    element: <PharmacistDashboard />,
                    children:[
                        {path: "", index:true , element:<DashboardProducts/> },
                        {path: "orders", element:<DashboardOrders/>},
                        {path: "settings", element:<DashboardSettings/>}
                    ]
                },
                {path: "activate", element: <Activate />},
                {path: "*", element: <NotFound />}
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