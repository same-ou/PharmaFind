import React, { useState, useEffect } from "react";
import ProductPreview from "../../components/Product/ProductPreview";
import { getProduct } from "../../services/ProductService";
import { useParams } from "react-router-dom";

interface Image {
  imageUrl: string;
}

interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  images: Image[];
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
      console.log(response);
    };

    fetchProduct();
  }, [id]);

  if (!product) {
    return <div>Loading...</div>;
  }

  if ("message" in product) {
    return <div>Error: {product.message}</div>;
  }

  return (
    <div>
      <ProductPreview
        pharmacy_name="Sneaker Company"
        product_name={product.name}
        description={product.description}
        price={product.price}
        data={product.images.map((image, index) => ({
          id: index,
          image: image.imageUrl,
        }))}
      />
    </div>
  );
};

export default ProductPage;
