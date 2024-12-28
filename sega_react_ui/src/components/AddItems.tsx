import React, { useState, useEffect, PropsWithChildren } from "react";

import styled from "styled-components";
import ItemDisplayGrid from "./ItemDisplayGrid";
import ItemSelectionPanel from "./ItemSelectionPanel";

import { OrderItemObject } from "../models/OrderItemObject";


const GridPanel = styled.div`
      background-color: darkGray;
      border: 4mm ridge rgba(211, 220, 50, .6);
      flex-flow: column;
      padding-left :40px ;
      padding-right :30px;   
      height : 80%;          
      align: left
`;
const HeaderPanel = styled.label`
    color: teal;
    text-align:left;    
    font-size:20px;
    font-weight: bold;
    padding-top: 5px; 
    padding-left: 15px; 
   `;

const AddItems = () => {

    const addItemsInCheckList = ( order: OrderItemObject) => {
        
    }
    return (
        <div>
            <HeaderPanel>Add New Items</HeaderPanel>
            <ItemSelectionPanel
                isAddPanelTab={true}
                addToCheckList={addItemsInCheckList}>
            </ItemSelectionPanel>
            <GridPanel>
                <ItemDisplayGrid />
            </GridPanel>
        </div>
    );
}
export default AddItems;
