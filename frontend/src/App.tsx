import { Outlet } from "react-router-dom";
import { AuthProvider } from "./hooks/useAuth";
import { Toaster } from "./components/ui/toaster";
import { ThemeProvider } from "./hooks/useTheme";
import Navbar from "./components/nav/Navbar";

const App: React.FC = () => {
  return (
  <>
    <AuthProvider>
      <ThemeProvider
       defaultTheme="light" 
       storageKey="vite-ui-theme">
      <main className="relative flex flex-col min-h-screen">
        <Navbar />
        <Outlet />
      </main>
      <Toaster />
      </ThemeProvider>
    </AuthProvider>
  </>
  );
};

export default App;
