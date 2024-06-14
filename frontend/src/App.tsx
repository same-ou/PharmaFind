import { Route, 
  RouterProvider,
  createBrowserRouter,
   createRoutesFromElements  } from "react-router-dom"
import Layout from "./components/Layout"
import Home from "./pages/Home"
import About from "./pages/About"
import Login from "./pages/auth/Login"
import Signup from "./pages/auth/Signup"
import NotFound from "./pages/NotFound"

const router = createBrowserRouter(createRoutesFromElements(
  <Route path="/" element={<Layout />} >
    <Route index element={<Home />} />
    <Route path="about" element={<About />} />  
    <Route path="login" element={<Login />} />
    <Route path="signup" element={<Signup />} />
    <Route path="*" element={<NotFound />} />
  </Route>
))
function App() {
  return (
 <RouterProvider router={router}/>
  )
}

export default App
