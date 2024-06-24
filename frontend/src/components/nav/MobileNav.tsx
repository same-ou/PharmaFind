import { PRODUCT_CATEGORIES } from "@/config/config"
import { UserProfile } from "@/models/user"
import { Menu } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Sheet, SheetContent, SheetTrigger } from "@/components/ui/sheet";
import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion";
import UserNav from "./UserNav";

type MobileNavProps = {
    user: UserProfile | null;
}

function MobileNav({user}: MobileNavProps) {
  return (
    <Sheet>
    <SheetTrigger asChild className="block lg:hidden">
      <Button variant="outline" size="icon">
        <Menu className="h-10" />
      </Button>
    </SheetTrigger>
    <SheetContent side="left" className="lg:hidden" >
      {PRODUCT_CATEGORIES.map((product, i) => (
        <Accordion key={product.value} type="single" collapsible>
          <AccordionItem value="item-1">
            <AccordionTrigger>{product.label}</AccordionTrigger>
            <AccordionContent className="">
              {product.featured.map((item) => (
                <a
                  key={item.name}
                  href={item.href}
                  className="text-sm block py-2 px-4 hover:bg-gray-100"
                >
                  {item.name}
                </a>
              ))}
            </AccordionContent>
          </AccordionItem>
        </Accordion>
      ))}

      <div className="mt-2">
        <UserNav user={user} className="flex flex-col items-start" />
      </div>
    </SheetContent>
  </Sheet>
  )
}

export default MobileNav