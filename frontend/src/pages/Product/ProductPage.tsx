import React, { useState, useEffect } from "react";
import ProductPreview from "../../components/Product/ProductPreview";
import { getProduct } from "../../services/ProductService";
import { useParams } from "react-router-dom";
// import {prwoducts} from "../../../public/data"

interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  imagesUrl: string[];
}

interface ErrorResponse {
  message: string;
}

const ProductPage: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [product, setProduct] = useState<Product | ErrorResponse | null>(null);

  useEffect(() => {
    const fetchProduct = async () => {
      const response = await getProduct(Number(id));
      setProduct(response);
    };

    fetchProduct();
  }, [id]);

  if (!product) {
    return <div>Loading...</div>;
  }

  if ('message' in product) {
    return <div>Error: {product.message}</div>;
  }

  return (
    <div>
      {/* <ProductPreview
        pharmacy_name="Sneaker Company"
        product_name={"Fall Limited Edition Sneakers"}
        description={"These low-profile sneakers are your perfect casual wear companion. Featuring a durable rubber outer"}
        price={125.00}
        data={products.map((image, index) => ({ id: index, image: image.image }))}
      /> */}
      <ProductPreview
        pharmacy_name="Sneaker Company"
        product_name={product.name}
        description={product.description}
        price={product.price}
        data={product.imagesUrl.map((url, index) => ({ id: index, image: url }))}
      />
    </div>
  );
};

export default ProductPage;
