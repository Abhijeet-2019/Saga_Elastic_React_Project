import React from "react";
import { Nav,Navbar } from "react-bootstrap";
import { NavLink } from "react-router-dom";


export const MainNavBar = () => {

return (
 <Navbar className="navbar navbar-expand-lg navbar-light bg-example border-bottom border-red" style={{ height: 60 }}>
            <Nav>
            <Navbar.Brand> Online Store</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav"/>
            <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mrx-auto" navbar>            
                <NavLink className="nav-link" to="/searchItems"> Search Items</NavLink>
                <NavLink className="nav-link" to="/addItems"> Add Items</NavLink>
                <NavLink className="nav-link" to="/orderItems"> Orders Items</NavLink>
                </Nav>
            </Navbar.Collapse>
            </Nav>    
        </Navbar>
        
    );

}
export default MainNavBar;
