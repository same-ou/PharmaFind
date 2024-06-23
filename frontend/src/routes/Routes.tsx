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

import Activate from "@/pages/auth/Activate";
import Home from "@/pages/Home/Home";
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
                {path: "pharmacists/dashboard", element: <PharmacistDashboard />},
                {path: "activate", element: <Activate />},
                {path: "*", element: <NotFound />}
            ]
        }
]);