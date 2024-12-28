import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";


import AddItems from "../components/AddItems";
import SearchItems from "../components/SearchItems";
import OrderItems from "../components/OrderItems";
import MainNavBar from "../components/MainNavBar";
export const AppRoutes = () => {

    return (
        <Router>
            <MainNavBar />
            <Routes>
                <Route path="/" element={<SearchItems/>} />
                <Route path="/searchItems" element={<SearchItems    />} />
                <Route path="/addItems" element={<AddItems />} />
                <Route path="/orderItems" element={<OrderItems />} />
            </Routes>
        </Router>
    );
}
export default AppRoutes;
