import NavItem from "./NavItem"

const items = [

  { to: '/pharmacies', label: 'Pharmacies' },
  { to: '/products', label: 'Products' },
  { to: '/services', label: 'Services' },
];

function NavItems() {
  return (
    <div className="flex h-full items-center space-x-4">
     {items.map((item, index) => (
        <NavItem key={index} to={item.to} label={item.label} />
      ))}
    </div>
  )
}

export default NavItems