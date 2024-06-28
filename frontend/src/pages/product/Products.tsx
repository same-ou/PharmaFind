import React from 'react'
import ProductContainer from '@/components/Product/ProductContainer'
import MaxWidthWrapper from '@/components/MaxWidthWrapper'

const Product:React.FC= () => {
  return (
    <MaxWidthWrapper>
      <ProductContainer/>     
    </MaxWidthWrapper>
  )
}

export default Product