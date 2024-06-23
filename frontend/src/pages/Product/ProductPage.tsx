import React from "react";
import ProductCard from "../../components/Product/ProductCard";
import { products } from "../../../public/data"; // Ensure the path is correct
// import { useParams } from "react-router-dom";


const ProductPage: React.FC = () => {

  // const { id } = useParams<{ id: string }>();

  return (
    <div>
      <ProductCard
        pharmacy_name="Sneaker Company"
        product_name="Fall Limited Edition Sneakers"
        description="These low-profile sneakers are your perfect casual wear companion. Featuring a durable rubber outer sole, they’ll withstand everything the weather can offer."
        price={125.00}
        data={products}
      />
    </div>
  );
};

export default ProductPage;
