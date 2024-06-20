// Activate.js
import {  useState } from "react";
import { useNavigate } from "react-router-dom";
import { request, setAuthToken } from "../Helper/Axios_helper";

function Activate() {
  const [message, setMessage] = useState("");
  const [token, setToken] = useState("");
  const navigate = useNavigate();

  const activateAccount = () => {
    request("post", "/auth/activate", { token })
      .then((response) => {
        setMessage("Account activated successfully!");

        if (response.status === 200 ) {
          setMessage("Account activated successfully!");
          setAuthToken(response.data.token);
          navigate("/");

        } else {
          setMessage("Activation failed. The link may be expired or invalid.");
        }
      })
      .catch((error) => {
        console.error("Activation error:", error);
        setMessage("Activation failed. The link may be expired or invalid.");
      });
  };

  return (
    <>
      <form onSubmit={activateAccount}>
        <h1>Account Activation</h1>
        <input
          type="text"
          placeholder="Enter your activation code"
          value={token}
          onChange={(e) => setToken(e.target.value)}
        />
      </form>
    </>
  );
}

export default Activate;
