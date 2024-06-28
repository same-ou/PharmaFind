import { Avatar, AvatarImage, AvatarFallback } from "@/components/ui/avatar"

export default function Component() {
  return (
    <section className="grid grid-cols-1 gap-6 p-4 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 md:p-6">
      <div className="relative overflow-hidden rounded-lg group">
        <div className="absolute inset-0 z-0 bg-gradient-to-t from-black/50 to-transparent" />
        <img
          src="/placeholder.svg"
          alt="Pharmacy Cover"
          width={400}
          height={300}
          className="object-cover w-full h-60"
        />
        <div className="relative z-10 p-6">
          <div className="flex items-center gap-4">
            <Avatar className="border-2 border-background">
              <AvatarImage src="/placeholder-user.jpg" />
              <AvatarFallback>PH</AvatarFallback>
            </Avatar>
            <div>
              <h3 className="text-lg font-semibold">Acme Pharmacy</h3>
              <p className="text-sm text-muted-foreground">123 Main St, Anytown USA</p>
              <p className="text-sm text-muted-foreground">(555) 555-5555</p>
            </div>
          </div>
        </div>
      </div>
      <div className="relative overflow-hidden rounded-lg group">
        <div className="absolute inset-0 z-0 bg-gradient-to-t from-black/50 to-transparent" />
        <img
          src="/placeholder.svg"
          alt="Pharmacy Cover"
          width={400}
          height={300}
          className="object-cover w-full h-60"
        />
        <div className="relative z-10 p-6">
          <div className="flex items-center gap-4">
            <Avatar className="border-2 border-background">
              <AvatarImage src="/placeholder-user.jpg" />
              <AvatarFallback>PH</AvatarFallback>
            </Avatar>
            <div>
              <h3 className="text-lg font-semibold">Wellness Pharmacy</h3>
              <p className="text-sm text-muted-foreground">456 Oak Rd, Smalltown USA</p>
              <p className="text-sm text-muted-foreground">(555) 555-5556</p>
            </div>
          </div>
        </div>
      </div>
      <div className="relative overflow-hidden rounded-lg group">
        <div className="absolute inset-0 z-0 bg-gradient-to-t from-black/50 to-transparent" />
        <img
          src="/placeholder.svg"
          alt="Pharmacy Cover"
          width={400}
          height={300}
          className="object-cover w-full h-60"
        />
        <div className="relative z-10 p-6">
          <div className="flex items-center gap-4">
            <Avatar className="border-2 border-background">
              <AvatarImage src="/placeholder-user.jpg" />
              <AvatarFallback>PH</AvatarFallback>
            </Avatar>
            <div>
              <h3 className="text-lg font-semibold">Sunrise Pharmacy</h3>
              <p className="text-sm text-muted-foreground">789 Oak St, Bigcity USA</p>
              <p className="text-sm text-muted-foreground">(555) 555-5557</p>
            </div>
          </div>
        </div>
      </div>
      <div className="relative overflow-hidden rounded-lg group">
        <div className="absolute inset-0 z-0 bg-gradient-to-t from-black/50 to-transparent" />
        <img
          src="/placeholder.svg"
          alt="Pharmacy Cover"
          width={400}
          height={300}
          className="object-cover w-full h-60"
        />
        <div className="relative z-10 p-6">
          <div className="flex items-center gap-4">
            <Avatar className="border-2 border-background">
              <AvatarImage src="/placeholder-user.jpg" />
              <AvatarFallback>PH</AvatarFallback>
            </Avatar>
            <div>
              <h3 className="text-lg font-semibold">Riverdale Pharmacy</h3>
              <p className="text-sm text-muted-foreground">321 Maple Ln, Smalltown USA</p>
              <p className="text-sm text-muted-foreground">(555) 555-5558</p>
            </div>
          </div>
        </div>
      </div>
      <div className="relative overflow-hidden rounded-lg group">
        <div className="absolute inset-0 z-0 bg-gradient-to-t from-black/50 to-transparent" />
        <img
          src="/placeholder.svg"
          alt="Pharmacy Cover"
          width={400}
          height={300}
          className="object-cover w-full h-60"
        />
        <div className="relative z-10 p-6">
          <div className="flex items-center gap-4">
            <Avatar className="border-2 border-background">
              <AvatarImage src="/placeholder-user.jpg" />
              <AvatarFallback>PH</AvatarFallback>
            </Avatar>
            <div>
              <h3 className="text-lg font-semibold">Greenfield Pharmacy</h3>
              <p className="text-sm text-muted-foreground">159 Pine Rd, Smalltown USA</p>
              <p className="text-sm text-muted-foreground">(555) 555-5559</p>
            </div>
          </div>
        </div>
      </div>
      <div className="relative overflow-hidden rounded-lg group">
        <div className="absolute inset-0 z-0 bg-gradient-to-t from-black/50 to-transparent" />
        <img
          src="/placeholder.svg"
          alt="Pharmacy Cover"
          width={400}
          height={300}
          className="object-cover w-full h-60"
        />
        <div className="relative z-10 p-6">
          <div className="flex items-center gap-4">
            <Avatar className="border-2 border-background">
              <AvatarImage src="/placeholder-user.jpg" />
              <AvatarFallback>PH</AvatarFallback>
            </Avatar>
            <div>
              <h3 className="text-lg font-semibold">Midtown Pharmacy</h3>
              <p className="text-sm text-muted-foreground">987 Main St, Bigcity USA</p>
              <p className="text-sm text-muted-foreground">(555) 555-5560</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  )
}