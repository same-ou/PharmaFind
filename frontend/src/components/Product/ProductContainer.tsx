import React, { useEffect, useState } from 'react';
import ProductCard from '@/components/Product/ProductCard';
import  { data } from '../../../public/data';

// import { getProducts } from '@/services/ProductService';  // Adjust the path based on your project structure

interface Product {
  id: number;
  image: string;
  title: string;
  description: string;
  price: number;
}

interface ErrorResponse {
  message: string;
}

const ProductContainer: React.FC = () => {

  const products:Product[] = data
  // const [products, setProducts] = useState<Product[]>([]);
  // const [error, setError] = useState<string | null>(null);

  // useEffect(() => {
  //   const fetchProducts = async () => {
  //     const result = await getProducts();
  //     if ('message' in result) {
  //       setError(result.message);
  //     } else {
  //       setProducts(result.content);
  //     }
  //   };

  //   fetchProducts();
  // }, []);

  // if (error) {
  //   return <div>Error: {error}</div>;
  // }

  return (
    <div className="p-10 m-5 grid grid-cols-1  sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-4 gap-6">
      {products.map((product) => (
        <ProductCard
          id={product.id}
          image={product.image}
          title={product.title}
          description={product.description}
          price={product.price}
        />
      ))}
    </div>
  );
};

export default ProductContainer;
