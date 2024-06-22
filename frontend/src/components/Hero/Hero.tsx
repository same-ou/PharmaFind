import React from "react";
import Image from "../../Asset/images/hero-img.png";
import backgroundImage from "../../Asset/images/sheap.png"; // Import the background image

const Hero = () => {
  return (
    <div>
      <div
        className="flex justify-center h-full w-full bg-cover bg-center"
        style={{ backgroundImage: `url(${backgroundImage})` }}
      >
        <div className="w-2/5 flex flex-col justify-center items-center gap-10 mb-30">
          <h1 className="text-3xl font-mono font-bold text-teal-500 text-center font-4xl uppercase">
            Find the best pharmacy near you
          </h1>
  
          <h3 className="text-cblue text-2xl text-center text- w-4/5 font-mono font-bold font-4xl uppercase">
             in  just a few clicks
          </h3>
          <button className="bg-customYellow text-white px-4 py-2 rounded-lg font-mono font-bold font-3xl uppercase w-2/5 h-50">
            Get Started
            </button>
        </div>
        <div className="w-3/5">
          <img src={Image} alt="Hero" />
        </div>
      </div>
    </div>
  );
};

export default Hero;
