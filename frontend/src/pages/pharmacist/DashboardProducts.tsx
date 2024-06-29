import { Table, 
    TableBody, 
    TableHead, 
    TableHeader, 
    TableRow, 
    } from "@/components/ui/table"
import { Button } from "@/components/ui/button"
import { Link } from "react-router-dom"
import { getProductsByPharmacy } from "@/services/ProductService";
import { PageResponse } from "@/services/PharmacyService";
import { Product } from "@/services/ProductService";
import { useLoaderData } from "react-router-dom";
import ProductEntry from "@/components/dashboard/ProductEntry";

export async function loader (){
  try {
    const data = await getProductsByPharmacy();
    return data;
  } catch (error: any) {
    throw new Response(error.message, { status: error.response?.status || 500 });
  }
}

function DashboardProducts() {
  const products = useLoaderData() as PageResponse<Product>;
  console.log(products)
  return (
    <section id="products">
            <div className="flex items-center">
              <h2 className="font-semibold text-lg md:text-2xl">Products</h2>
          
              <Button size="sm" className="ml-auto" asChild>
                <Link to="addProduct">
                Add Product
                </Link>
                
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
                  {products.content.map((product) => (
                    <ProductEntry key={product.id} product={product} />
                  ))}
                </TableBody>
              </Table>
            </div>
          </section>
  )
}



export default DashboardProducts