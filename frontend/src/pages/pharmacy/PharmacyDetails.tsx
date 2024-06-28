import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { Select, SelectTrigger, SelectValue, SelectContent, SelectItem } from "@/components/ui/select"
import { Card, CardHeader, CardContent } from "@/components/ui/card"
import { Pagination } from "@/components/ui/pagination"

export default function Component() {
  const products = [
    {
      id: 1,
      name: "Aspirin",
      description: "325mg pain relief tablets",
      price: 9.99,
      image: "/placeholder.svg",
    },
    {
      id: 2,
      name: "Ibuprofen",
      description: "200mg anti-inflammatory tablets",
      price: 12.99,
      image: "/placeholder.svg",
    },
    {
      id: 3,
      name: "Vitamin C",
      description: "500mg immune support tablets",
      price: 7.99,
      image: "/placeholder.svg",
    },
    {
      id: 4,
      name: "Melatonin",
      description: "3mg sleep aid tablets",
      price: 14.99,
      image: "/placeholder.svg",
    },
    {
      id: 5,
      name: "Bandages",
      description: "Assorted sizes, 50 count",
      price: 5.99,
      image: "/placeholder.svg",
    },
    {
      id: 6,
      name: "Antihistamine",
      description: "10mg allergy relief tablets",
      price: 8.99,
      image: "/placeholder.svg",
    },
  ]
  const [currentPage, setCurrentPage] = useState(1)
  const [productsPerPage] = useState(4)
  const [selectedFilters, setSelectedFilters] = useState({
    category: [],
    price: { min: 0, max: Infinity },
  })
  const indexOfLastProduct = currentPage * productsPerPage
  const indexOfFirstProduct = indexOfLastProduct - productsPerPage
  const currentProducts = products.slice(indexOfFirstProduct, indexOfLastProduct)
  const handleFilterChange = (type, value) => {
    if (type === "category") {
      setSelectedFilters({
        ...selectedFilters,
        category: selectedFilters.category.includes(value)
          ? selectedFilters.category.filter((item) => item !== value)
          : [...selectedFilters.category, value],
      })
    } else if (type === "price") {
      setSelectedFilters({
        ...selectedFilters,
        price: value,
      })
    }
  }
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber)
  }
  return (
    <div className="w-full">
      <div className="relative h-[300px] overflow-hidden rounded-b-2xl">
        <img
          src="/placeholder.svg"
          alt="Cover image"
          width={1920}
          height={600}
          className="absolute inset-0 h-full w-full object-cover"
        />
        <div className="absolute inset-0 bg-gradient-to-t from-black/50 to-transparent" />
        <div className="absolute bottom-0 left-0 flex w-full items-end justify-between px-6 py-8">
          <div className="flex items-center gap-4">
            <div className="rounded-full bg-white p-1">
              <img
                src="/placeholder.svg"
                alt="Pharmacy logo"
                width={80}
                height={80}
                className="h-20 w-20 rounded-full object-cover"
              />
            </div>
            <div className="text-white">
              <h1 className="text-2xl font-bold">Acme Pharmacy</h1>
              <p className="text-sm">123 Main St, Anytown USA</p>
            </div>
          </div>
          <Button size="sm">Follow</Button>
        </div>
      </div>
      <div className="container mx-auto grid gap-8 px-4 py-12 md:grid-cols-[1fr_2fr]">
        <div className="grid gap-4">
          <div className="grid gap-2">
            <h2 className="text-2xl font-bold">About Acme Pharmacy</h2>
            <p className="text-muted-foreground">
              Acme Pharmacy is a family-owned business that has been serving the community for over 50 years. We pride
              ourselves on providing high-quality medications and personalized customer service.
            </p>
            <div className="grid gap-2">
              <h3 className="text-xl font-bold">Contact Information</h3>
              <p className="text-muted-foreground">
                Phone: (123) 456-7890
                <br />
                Email: info@acmepharmacy.com
              </p>
            </div>
            <div className="grid gap-2">
              <h3 className="text-xl font-bold">Location</h3>
              <p className="text-muted-foreground">123 Main St, Anytown USA</p>
            </div>
            <div className="grid gap-2">
              <h3 className="text-xl font-bold">Owner</h3>
              <p className="text-muted-foreground">John Doe</p>
            </div>
          </div>
          <div className="grid gap-2">
            <h2 className="text-2xl font-bold">Our Services</h2>
            <ul className="grid gap-2 text-muted-foreground">
              <li>
                <CheckIcon className="mr-2 inline-block h-5 w-5 text-primary" />
                Prescription Refills
              </li>
              <li>
                <CheckIcon className="mr-2 inline-block h-5 w-5 text-primary" />
                Immunizations
              </li>
              <li>
                <CheckIcon className="mr-2 inline-block h-5 w-5 text-primary" />
                Medication Consultations
              </li>
              <li>
                <CheckIcon className="mr-2 inline-block h-5 w-5 text-primary" />
                Delivery Service
              </li>
            </ul>
          </div>
        </div>
        <div className="grid gap-8">
          <div>
            <h2 className="mb-4 text-2xl font-bold">Our Products</h2>
            <div className="grid gap-4">
              <div className="flex items-center justify-between">
                <div className="flex items-center gap-2">
                  <Label htmlFor="category">Category</Label>
                  <Select
                    id="category"
                    value={selectedFilters.category}
                    className="w-40"
                    onValueChange={(e) => handleFilterChange("category", e.target.value)}
                  >
                    <SelectTrigger>
                      <SelectValue placeholder="All" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="">All</SelectItem>
                      <SelectItem value="pain-relief">Pain Relief</SelectItem>
                      <SelectItem value="immune-support">Immune Support</SelectItem>
                      <SelectItem value="sleep-aid">Sleep Aid</SelectItem>
                      <SelectItem value="first-aid">First Aid</SelectItem>
                    </SelectContent>
                  </Select>
                </div>
                <div className="flex items-center gap-2">
                  <Label htmlFor="price">Price</Label>
                  <div className="w-40" />
                </div>
              </div>
              <div className="grid grid-cols-1 gap-4 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
                {currentProducts.map((product) => (
                  <Card key={product.id} className="h-full">
                    <CardHeader>
                      <img
                        src="/placeholder.svg"
                        alt={product.name}
                        width={300}
                        height={300}
                        className="h-40 w-full rounded-t-md object-cover"
                      />
                    </CardHeader>
                    <CardContent className="grid gap-2">
                      <h3 className="text-lg font-bold">{product.name}</h3>
                      <p className="text-muted-foreground">{product.description}</p>
                      <div className="flex items-center justify-between">
                        <div className="text-2xl font-bold">${product.price}</div>
                        <Button size="sm">Add to Cart</Button>
                      </div>
                    </CardContent>
                  </Card>
                ))}
              </div>
              <div className="flex justify-center">
                <Pagination
                  currentPage={currentPage}
                  totalPages={Math.ceil(products.length / productsPerPage)}
                  onPageChange={handlePageChange}
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

function CheckIcon(props) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="M20 6 9 17l-5-5" />
    </svg>
  )
}