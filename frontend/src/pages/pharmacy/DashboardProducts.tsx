import { Table, 
    TableBody, 
    TableHead, 
    TableHeader, 
    TableRow, 
    TableCell } from "@/components/ui/table"
import { Button } from "@/components/ui/button"

function DashboardProducts() {
  return (
    <section id="products">
            <div className="flex items-center">
              <h2 className="font-semibold text-lg md:text-2xl">Products</h2>
              <Button size="sm" className="ml-auto">
                Add Product
              </Button>
            </div>
            <div className="border shadow-sm rounded-lg mt-4">
              <Table>
                <TableHeader>
                  <TableRow>
                    <TableHead className="w-[80px]">Image</TableHead>
                    <TableHead>Name</TableHead>
                    <TableHead>Price</TableHead>
                    <TableHead>Inventory</TableHead>
                    <TableHead />
                  </TableRow>
                </TableHeader>
                <TableBody>
                  <TableRow>
                    <TableCell>
                      <img
                        src="/placeholder.svg"
                        width="64"
                        height="64"
                        alt="Product image"
                        className="aspect-square rounded-md object-cover"
                      />
                    </TableCell>
                    <TableCell className="font-medium">Aspirin</TableCell>
                    <TableCell>$9.99</TableCell>
                    <TableCell>500 in stock</TableCell>
                    <TableCell>
                      <Button variant="outline" size="icon">
                        <PencilIcon className="h-4 w-4" />
                        <span className="sr-only">Edit</span>
                      </Button>
                    </TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell>
                      <img
                        src="/placeholder.svg"
                        width="64"
                        height="64"
                        alt="Product image"
                        className="aspect-square rounded-md object-cover"
                      />
                    </TableCell>
                    <TableCell className="font-medium">Ibuprofen</TableCell>
                    <TableCell>$7.99</TableCell>
                    <TableCell>300 in stock</TableCell>
                    <TableCell>
                      <Button variant="outline" size="icon">
                        <PencilIcon className="h-4 w-4" />
                        <span className="sr-only">Edit</span>
                      </Button>
                    </TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell>
                      <img
                        src="/placeholder.svg"
                        width="64"
                        height="64"
                        alt="Product image"
                        className="aspect-square rounded-md object-cover"
                      />
                    </TableCell>
                    <TableCell className="font-medium">Acetaminophen</TableCell>
                    <TableCell>$5.99</TableCell>
                    <TableCell>200 in stock</TableCell>
                    <TableCell>
                      <Button variant="outline" size="icon">
                        <PencilIcon className="h-4 w-4" />
                        <span className="sr-only">Edit</span>
                      </Button>
                    </TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell>
                      <img
                        src="/placeholder.svg"
                        width="64"
                        height="64"
                        alt="Product image"
                        className="aspect-square rounded-md object-cover"
                      />
                    </TableCell>
                    <TableCell className="font-medium">Amoxicillin</TableCell>
                    <TableCell>$14.99</TableCell>
                    <TableCell>100 in stock</TableCell>
                    <TableCell>
                      <Button variant="outline" size="icon">
                        <PencilIcon className="h-4 w-4" />
                        <span className="sr-only">Edit</span>
                      </Button>
                    </TableCell>
                  </TableRow>
                </TableBody>
              </Table>
            </div>
          </section>
  )
}

function PencilIcon(props: any) {
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
        <path d="M17 3a2.85 2.83 0 1 1 4 4L7.5 20.5 2 22l1.5-5.5Z" />
        <path d="m15 5 4 4" />
      </svg>
    )
  }

export default DashboardProducts