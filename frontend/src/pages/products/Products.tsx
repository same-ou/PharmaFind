import { useState, useMemo } from "react"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { Label } from "@/components/ui/label"
import { Checkbox } from "@/components/ui/checkbox"
import { Search} from "lucide-react"
import MaxWidthWrapper from "@/components/MaxWidthWrapper"

export default function Products() {
  const [searchTerm, setSearchTerm] = useState("")
  const [filters, setFilters] = useState({
    category: [],
    priceRange: [0, 100],
    inStock: false,
  })
  const products = [
    {
      id: 1,
      name: "Aspirin",
      description: "Pain relief medication",
      price: 5.99,
      category: "Pain Relief",
      inStock: true,
    },
    {
      id: 2,
      name: "Ibuprofen",
      description: "Anti-inflammatory medication",
      price: 7.99,
      category: "Pain Relief",
      inStock: true,
    },
    {
      id: 3,
      name: "Vitamin C",
      description: "Immune system support",
      price: 9.99,
      category: "Vitamins",
      inStock: true,
    },
    {
      id: 4,
      name: "Bandages",
      description: "Wound care supplies",
      price: 3.99,
      category: "First Aid",
      inStock: false,
    },
    {
      id: 5,
      name: "Cough Syrup",
      description: "Cough and cold relief",
      price: 11.99,
      category: "Cold and Flu",
      inStock: true,
    },
    {
      id: 6,
      name: "Antacid",
      description: "Heartburn relief",
      price: 6.99,
      category: "Digestive Health",
      inStock: true,
    },
  ]
  const filteredProducts = useMemo(() => {
    return products.filter((product) => {
      const searchMatch =
        product.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        product.description.toLowerCase().includes(searchTerm.toLowerCase())
      const categoryMatch = filters.category.length === 0 || filters.category.includes(product.category)
      const priceMatch = product.price >= filters.priceRange[0] && product.price <= filters.priceRange[1]
      const inStockMatch = !filters.inStock || product.inStock
      return searchMatch && categoryMatch && priceMatch && inStockMatch
    })
  }, [searchTerm, filters])
  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value)
  }
  const handleFilterChange = (type, value) => {
    setFilters((prevFilters) => ({
      ...prevFilters,
      [type]: value,
    }))
  }
  return (
    <MaxWidthWrapper>
    <div className="flex flex-col md:flex-row gap-8 px-4 md:px-6 py-8">
      <div className="flex-1">
        <div className="flex justify-center mb-8">
          <div className="relative w-full max-w-md">
            <Input
              type="search"
              placeholder="Search products..."
              value={searchTerm}
              onChange={handleSearchChange}
              className="pr-12"
            />
            <Button type="submit" variant="ghost" className="absolute right-2 top-1/2 -translate-y-1/2">
              <Search className="w-5 h-5" />
              <span className="sr-only">Search</span>
            </Button>
          </div>
        </div>
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {filteredProducts.map((product) => (
            <div key={product.id} className="bg-background rounded-lg shadow-sm overflow-hidden">
              <img
                src="/placeholder.svg"
                alt={product.name}
                width={300}
                height={200}
                className="w-full h-48 object-cover"
              />
              <div className="p-4">
                <h3 className="text-lg font-semibold">{product.name}</h3>
                <p className="text-muted-foreground">{product.description}</p>
                <div className="flex items-center justify-between mt-4">
                  <span className="text-primary font-semibold">${product.price.toFixed(2)}</span>
                  {product.inStock ? (
                    <Badge variant="default">In Stock</Badge>
                  ) : (
                    <Badge variant="destructive">Out of Stock</Badge>
                  )}
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
      <div className="w-full md:w-64 bg-background rounded-lg shadow-sm p-6">
        <h2 className="text-lg font-semibold mb-4">Filters</h2>
        <div className="space-y-4">
          <div>
            <h3 className="text-base font-semibold mb-2">Category</h3>
            <div className="space-y-2">
              <Label className="flex items-center gap-2">
                <Checkbox
                  checked={filters.category.includes("Pain Relief")}
                  onCheckedChange={(checked) => handleFilterChange("category", checked ? ["Pain Relief"] : [])}
                />
                Pain Relief
              </Label>
              <Label className="flex items-center gap-2">
                <Checkbox
                  checked={filters.category.includes("Vitamins")}
                  onCheckedChange={(checked) => handleFilterChange("category", checked ? ["Vitamins"] : [])}
                />
                Vitamins
              </Label>
              <Label className="flex items-center gap-2">
                <Checkbox
                  checked={filters.category.includes("First Aid")}
                  onCheckedChange={(checked) => handleFilterChange("category", checked ? ["First Aid"] : [])}
                />
                First Aid
              </Label>
              <Label className="flex items-center gap-2">
                <Checkbox
                  checked={filters.category.includes("Cold and Flu")}
                  onCheckedChange={(checked) => handleFilterChange("category", checked ? ["Cold and Flu"] : [])}
                />
                Cold and Flu
              </Label>
              <Label className="flex items-center gap-2">
                <Checkbox
                  checked={filters.category.includes("Digestive Health")}
                  onCheckedChange={(checked) => handleFilterChange("category", checked ? ["Digestive Health"] : [])}
                />
                Digestive Health
              </Label>
            </div>
          </div>
          <div>
            <h3 className="text-base font-semibold mb-2">Price Range</h3>
            <div />
          </div>
          <div>
            <h3 className="text-base font-semibold mb-2">In Stock</h3>
            <Label className="flex items-center gap-2">
              <Checkbox
                checked={filters.inStock}
                onCheckedChange={(checked) => handleFilterChange("inStock", checked)}
              />
              Show only in-stock products
            </Label>
          </div>
        </div>
      </div>
    </div>
    </MaxWidthWrapper>
  )
}
