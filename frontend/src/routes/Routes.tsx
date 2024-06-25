import { createBrowserRouter } from "react-router-dom";
import App from "@/App";
import ClientLogin from "@/pages/auth/ClientLogin";
import ClientRegistration from "@/pages/auth/ClientRegistration";
import PharmacistRegistration from "@/pages/auth/PharmacistRegistration";
import PharmacistLogin from "@/pages/auth/PharmacistLogin";
import RegisterPharmacy from "@/pages/pharmacist/RegisterPharmacy";
import NotFound from "@/pages/NotFound";
import Error from "@/pages/Error";
import PharmacistDashboard from "@/pages/pharmacist/PharmacistDashboard";
import DashboardOrders from "@/pages/pharmacist/DashboardOrders";
import DashboardProducts from "@/pages/pharmacist/DashboardProducts";
import DashboardSettings from "@/pages/pharmacist/DashboardSettings";
import Activate from "@/pages/auth/Activate";
import Home from "@/pages/Home/Home";


import Pharmacies from "@/pages/pharmacy/Pharmacies";
import PharmacyDetails from "@/pages/pharmacy/PharmacyDetails";
import Products from "@/pages/products/Products";
import ProductDetails from "@/pages/products/ProductDetails";
        
import ProductPage from "@/pages/Product/ProductPage";
import NewProduct from "@/pages/Product/NewProduct";
import Product from "@/pages/Product/Product";

export const router = createBrowserRouter([
        {
            element: <App />,
            errorElement: <Error />,
            children:[
                {index:true,element:<Home /> },
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
                {path: "pharmacies",  element: <Pharmacies />},
                {path: "pharmacies/:id",element: <PharmacyDetails />},
                {path: "products", element: <Products />},
                {path: "products/:id", element: <ProductDetails />},
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