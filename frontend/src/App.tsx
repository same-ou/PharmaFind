import { Outlet } from "react-router-dom";
import { AuthProvider } from "./context/useAuth";
import { Toaster } from "./components/ui/toaster";


const App: React.FC = () => {
  return (
  <>
    <AuthProvider>
      <Outlet />
      <Toaster />
    </AuthProvider>
  </>
  );
};

export default App;
