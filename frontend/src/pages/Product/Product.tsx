import React from 'react'

import ProductCard from '@/components/Product/ProductCard'

const Product:React.FC= () => {
  return (
    <div>
      <ProductCard
        image="https://i.ibb.co/0ykr0SS/image-product-1.jpg"
        title="Product 1"
        description="This is a description of product 1"
        price={10}
      />
    </div>
  )
}

export default Product