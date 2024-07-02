import { NavLink } from "react-router-dom"
import { Link } from "react-router-dom"

type NavItemProps = {
    to: string,
    label: string
}

function NavItem({to, label}: NavItemProps) {
  const defaultClassName="text-sm font-semibold text-gray-700 hover:text-gray-900 px-4 py-2"
  return (
    <NavLink to={to} className={({isActive}) => {
      return isActive ? `text-sm  text-black hover:text-gray-900 px-4 py-2 font-semibold` : defaultClassName
    }}>
      {label}
    </NavLink>
  )
}

export default NavItem