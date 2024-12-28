import React, { useState, useEffect } from "react";
import styled from "styled-components";
import ItemSelectionPanel from "./ItemSelectionPanel";
import AddOrders from "./AddOrders";
import { OrderItemObject } from "../models/OrderItemObject";


const HeaderPanel = styled.label`
    color: teal;
    text-align:left;    
    font-size:20px;
    font-weight: bold;
    padding-top: 5px; 
    padding-left: 15px; 
   `;
const GridPanel = styled.div`

   background-color: darkGray;
   border: 4mm ridge rgba(211, 220, 50, .6);
   flex-flow: column;
   padding-left :150px ;
   padding-right :150px;   
   height : 80%;          
   align: left
`;
const OrderItems = () => {

    
    const [list, setList] = useState<OrderItemObject[]>([]);

    const addItemsInCheckList = (order: OrderItemObject) => {
        setList([...list, order])
    }

    return (
        <div>
            <HeaderPanel>Order New Items</HeaderPanel>
            <ItemSelectionPanel
                isAddPanelTab={false}
                addToCheckList={addItemsInCheckList}>
            </ItemSelectionPanel>
            <GridPanel>
                <AddOrders checkoutList = {list} />
            </GridPanel>
            <>
            </>

        </div>
    );
}
export default OrderItems;