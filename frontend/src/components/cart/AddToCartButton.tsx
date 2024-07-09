import { Product } from "@/services/ProductService"
import { Button } from "../ui/button"
import { useCart } from "@/hooks/useCart"
import { CartProductItem } from "@/types"

type AddToCartButtonProps = {
  product: Product;
}

function AddToCartButton({ product }: AddToCartButtonProps) {
  const { addToCart } = useCart();

  const item:CartProductItem = {
    id: product.id as number,
    name: product.name,
    price: product.price,
    quantity: 1,
    images: product.images
  } 

  const handleAddToCart = async () => {
    addToCart(item);
  }

  console.log(item);
  return (
    <Button
      size="lg"
      onClick={handleAddToCart}
    >
      Add to Cart
    </Button>
  )
}

export default AddToCartButton