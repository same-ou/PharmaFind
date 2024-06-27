import React from 'react'
import ProductContainer from '@/components/Product/ProductContainer'
// import ProductCard from '@/components/Product/ProductCard'

const Product:React.FC= () => {
  return (
    <div >
      <ProductContainer/>     
      {/* <ProductCard
        image="https://i.ibb.co/0ykr0SS/image-product-1.jpg"
        title="Product 1"
        description="This is a description of product 1"
        price={100.99}
      /> */}
    </div>
  )
}

export default Product