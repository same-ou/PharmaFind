import {create} from "zustand";
import {persist, createJSONStorage} from "zustand/middleware";
import { Product } from "@/models/product";

type CartStore = {
    cart: Product[];
    addToCart: (product: Product) => void;
    removeFromCart: (productId: number) => void;
    clearCart: () => void;
};

export const useCart = create<CartStore>()(
    persist(
    (set) => ({
    cart: [],
    addToCart: (product) => set((state) => ({ cart: [...state.cart, product] })),
    removeFromCart: (productId) => set((state) => ({ cart: state.cart.filter((product) => product.id !== productId) })),
    clearCart: () => set({ cart: [] }),
    }),{
    name: "cart-storage",
    storage: createJSONStorage(() => localStorage),
  } )
);