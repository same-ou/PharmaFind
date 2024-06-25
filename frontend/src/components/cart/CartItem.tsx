import { Product } from "@/models/product";

type CartItemProps = {
    product: Product;
  };

function CartItem({ product }: CartItemProps) {
    const { removeFromCart } = useCart();
  return (
    <div>CartItem</div>
  )
}

export default CartItem