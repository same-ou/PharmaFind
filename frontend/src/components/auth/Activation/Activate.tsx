// Activate.js
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { request, setAuthToken } from "../../../Helper/Axios_helper";

const Activate = () => {
  const [message, setMessage] = useState("");
  const [token, setToken] = useState("");
  const [error, setError] = useState(false);
  const navigate = useNavigate();

  const activateAccount = (e) => {
    e.preventDefault();
    request("post", "/auth/activate", { token })
      .then((response) => {
        if (response.status === 200) {
          setMessage("Account activated successfully!");
          setAuthToken(response.data.token);
          navigate("/");
          setError(false);
        } else {
          setMessage("Activation failed. The link may be expired or invalid.");
          setError(true);
        }
      })
      .catch((error) => {
        console.error("Activation error:", error);
        setMessage("Activation failed. The link may be expired or invalid.");
        setError(true);
      });
  };

  return (
    <>
      <div className="flex flex-col justify-center items-center min-h-screen bg-teal-50 p-4">
        <div className="bg-white shadow-md rounded-lg p-8 max-w-md w-full">
          <h1 className="text-2xl font-semibold text-center text-teal-600 mb-4">
            Activate Your Account
          </h1>
          <p className="text-center text-gray-600 mb-6">
            Enter your verification token to activate your account.
          </p>
          <form onSubmit={activateAccount} className="space-y-4">
            <div>
              <label htmlFor="token" className="block text-gray-700">
                Verification Token
              </label>
              <input
                type="text"
                id="token"
                value={token}
                onChange={(e) => setToken(e.target.value)}
                className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring focus:ring-teal-200 focus:border-teal-300"
                required
              />
            </div>
            <button
              type="submit"
              className="w-full bg-teal-600 text-white py-2 rounded-md hover:bg-teal-700 transition-colors duration-200"
            >
              Activate Account
            </button>
          </form>
          {error ? (
            <p className="mt-4 text-center text-red-500">{message}</p>
          ) : ( 
            <p className="mt-4 text-center text-green-600 ">{message}</p>
          )}
        </div>
      </div>
    </>
  );
};

export default Activate;
