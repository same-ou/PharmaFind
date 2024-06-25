import React from 'react';

interface ProductCardProps {
  image: string;
  title: string;
  description: string;
  price: number;
}

const ProductCard: React.FC<ProductCardProps> = ({
  image,
  title,
  description,
  price,
}) => {
  return (
    <div className="max-w-xs mx-auto bg-white shadow-md rounded-lg overflow-hidden">
      <img className="w-full h-48 object-cover" src={image} alt={title} />
      <div className="p-4">
        <h3 className="text-lg font-semibold">{title}</h3>
       
        <p className="mt-2 text-gray-600 text-sm">{description}</p>
        <div className="mt-4 flex items-center justify-between">
          <span className="text-lg font-bold">${price}</span>
          <button className="px-4 py-2 bg-green-500 text-white text-sm font-semibold rounded hover:bg-green-600">
            Add to cart
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProductCard;
