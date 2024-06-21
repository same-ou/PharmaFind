import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {request, setAuthToken} from "../../Helper/Axios_helper";

const RegisterForm = () => {
  const [input, setInput] = useState({});
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setInput((prevInput) => ({
      ...prevInput,
      [name]: value,
    }));
  };

  const validate = () => {
    let stat = false;
    if (input.password === input.cmPassword) {
      stat = true;
    }

    return stat;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(input);

    const url = "/auth/register";

    if (validate()) {
      
      request("post", "/auth/register", input)
      .then((res) => {
        console.log(res);
        if (res.status === 202) {
          alert("Registration Successful");
          navigate("/activate"); // Use navigate from your routing library (e.g., react-router-dom)
        } else {
          alert("Registration Failed");
        }
      })
      .catch((err) => {
        console.error("Error during registration:", err);
        alert("Registration Failed");
      });

    }else{
      alert("you should write the same password")
    }
  };

  return (
    <div className="h-screen flex">
      <div className="w-3/5 relative">
        <div
          className="absolute inset-0 bg-bottom bg-cover bg-no-repeat"
          style={{
            backgroundImage: `url("https://images.unsplash.com/photo-1584362917165-526a968579e8?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8bWVkaWNpbmV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60")`,
          }}
        />
        <div className="h-full flex flex-col justify-center items-center text-white">
          <div className="text-5xl font-semibold mb-6">Medicine</div>
          <div className="text-lg">Let the Power of Beats flow in you</div>
          <button className="mt-8 bg-teal-500 hover:bg-teal-700 text-white py-2 px-4 rounded">
            Learn More
          </button>
        </div>
      </div>

      <div className="flex items-center justify-center w-2/5">
        <form
          onSubmit={handleSubmit}
          id="register-form"
          className="max-w-xl p-4 mx-auto mt-4"
        >
          <div className="mb-8 font-display text-5xl font-semibold text-center text-teal-500">
            Register
          </div>
          <div className="flex flex-wrap mb-6 -mx-3">
            <div className="w-full px-3 mb-6 md:w-1/2 md:mb-0">
              <label
                className="block mb-2 text-xs font-semibold tracking-wide text-gray-700 uppercase"
                htmlFor="grid-first-name"
              >
                First Name
              </label>
              <input
                name="firstName"
                className="block w-full px-4 py-3 mb-3 leading-tight text-gray-700 border-b-2 border-gray-500 rounded appearance-none hover:border-red-500 hover:shadow-xl focus:outline-none focus:bg-white"
                id="grid-first-name"
                type="text"
                placeholder="Harsh"
                onChange={handleChange}
                required
              />
            </div>
            <div className="w-full px-3 md:w-1/2">
              <label
                className="block mb-2 text-xs font-semibold tracking-wide text-gray-700 uppercase"
                htmlFor="grid-last-name"
              >
                Last Name
              </label>
              <input
                name="lastName"
                className="block w-full px-4 py-3 leading-tight text-gray-700 border-b-2 border-gray-500 rounded appearance-none hover:border-purple-500 hover:shadow-xl focus:outline-none focus:bg-white focus:border-gray-500"
                id="grid-last-name"
                type="text"
                placeholder="Doe"
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="flex flex-wrap mb-6 -mx-3">
            <div className="w-full px-3">
              <label
                className="block mb-2 text-xs font-semibold tracking-wide text-gray-700 uppercase"
                htmlFor="grid-username"
              >
                Username
              </label>
              <input
                name="userName"
                className="block w-full px-4 py-3 mb-3 leading-tight text-gray-700 border-b-2 border-gray-500 rounded appearance-none hover:border-orange-500 hover:shadow-xl focus:outline-none focus:bg-white focus:border-gray-500"
                id="grid-username"
                type="text"
                placeholder="HarshDoe"
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="flex flex-wrap mb-6 -mx-3">
            <div className="w-full px-3 mb-6 md:w-1/2 md:mb-0">
              <label
                className="block mb-2 text-xs font-semibold tracking-wide text-gray-700 uppercase"
                htmlFor="grid-email"
              >
                Email Address
              </label>
              <input
                name="email"
                className="block w-full px-4 py-3 mb-3 leading-tight text-gray-700 border-b-2 border-gray-500 rounded appearance-none hover:border-indigo-500 hover:shadow-xl focus:outline-none focus:bg-white focus:border-gray-500"
                id="grid-email"
                type="email"
                placeholder="harshdoe@example.com"
                onChange={handleChange}
                required
              />
            </div>
            <div className="w-full px-3 md:w-1/2">
              <label
                className="block mb-2 text-xs font-semibold tracking-wide text-gray-700 uppercase"
                htmlFor="grid-phone"
              >
                Phone Number
              </label>
              <input
                name="phone"
                className="block w-full px-4 py-3 mb-3 leading-tight text-gray-700 border-b-2 border-gray-500 rounded appearance-none hover:border-yellow-500 hover:shadow-xl focus:outline-none focus:bg-white focus:border-gray-500"
                id="grid-phone"
                type="text"
                placeholder="+88 XXXXX XXXXX"
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="flex flex-wrap mb-6 -mx-3">
            <div className="w-full px-3 mb-6 md:w-1/2 md:mb-0">
              <label
                className="block mb-2 text-xs font-semibold tracking-wide text-gray-700 uppercase"
                htmlFor="grid-password"
              >
                Password
              </label>
              <input
                name="password"
                className="block w-full px-4 py-3 mb-3 leading-tight text-gray-700 border-b-2 border-gray-500 rounded appearance-none hover:border-green-500 hover:shadow-xl focus:outline-none focus:bg-white focus:border-gray-500"
                id="grid-password"
                type="password"
                placeholder="Password"
                onChange={handleChange}
                required
              />
            </div>
            <div className="w-full px-3 md:w-1/2">
              <label
                className="block mb-2 text-xs font-semibold tracking-wide text-gray-700 uppercase"
                htmlFor="grid-confirm-password"
              >
                Confirm Password
              </label>
              <input
                name="cmPassword"
                className="block w-full px-4 py-3 mb-3 leading-tight text-gray-700 border-b-2 border-gray-500 rounded appearance-none hover:border-blue-500 hover:shadow-xl focus:outline-none focus:bg-white focus:border-gray-500"
                id="grid-confirm-password"
                type="password"
                placeholder="Confirm Password"
                onChange={handleChange}
                required
              />
            </div>
          </div>

          <div className="flex justify-center mt-6">
            <button className="px-8 py-2 font-semibold text-white rounded-full shadow-lg bg-gradient-to-r from-teal-500 to-teal-600 hover:from-teal-400 hover:to-teal-500">
              Register
            </button>
          </div>

          <div className="flex justify-center mt-1">
            <h6
              onClick={(e) => navigate("/login")}
              className="text-teal-500 hover:text-teal-700 text-lg font-semibold no-underline hover:underline cursor-pointer transition ease-in duration-300"
            >
              Already have an account?
            </h6>
          </div>
        </form>
      </div>
    </div>
  );
};

export default RegisterForm;
