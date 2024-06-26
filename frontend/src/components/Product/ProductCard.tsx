import React from 'react';
import { FaEye } from 'react-icons/fa';

interface ProductCardProps {
  id: number;
  image: string;
  title: string;
  description: string;
  price: number;
}

const ProductCard: React.FC<ProductCardProps> = ({
  id,
  image,
  title,
  description,
  price,
}) => {
  return (
    <div className="w-3/4 mx-auto bg-white shadow-md rounded-lg overflow-hidden relative">
      <img className="w-full h-48 object-cover" src={image} alt={title} />
      <span className="absolute top-4 left-2 bg-teal-500 text-white text-lg font-bold px-2 py-1 rounded">
        ${price}
      </span>
      <div className="p-4">
        <h3 className="text-lg font-semibold">{title}</h3>
        <p className="mt-2 text-gray-600 text-sm">{description}</p>
        <div className="mt-4 flex items-center justify-between space-x-4">
          <button className="flex items-center space-x-2 px-3 py-2 bg-yellow-300 text-white text-sm font-semibold rounded hover:bg-yellow-600">
            <FaEye />
            <span>View</span>
          </button>
          <button className="px-4 py-2 bg-teal-500 text-white text-sm font-semibold rounded hover:bg-teal-400">
            Add to cart
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProductCard;
