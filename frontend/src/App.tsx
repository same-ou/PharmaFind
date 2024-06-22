import { RouterProvider, 
  createBrowserRouter} from "react-router-dom";
import Home from "./pages/Home/Home";
import Login from "./pages/auth/Login";
import Registration from "./pages/auth/Registration";
import Activate from "./components/auth/Activation/Activate";

const routes = [
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/register",
    element: <Registration />,
  },
  {
    path: "/activate",
    element: <Activate />,
  }
];

const router = createBrowserRouter(routes);

const App: React.FC = () => {
  return <RouterProvider router={router} />;
};

export default App;
