import React, { useState, useEffect } from "react";

import styled from "styled-components";
import SearchItemGrid from "./SearchItemGrid";
import SearchPanel from "./SearchPanel";
import { ItemDetails } from "../models/itemDetails";

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
const SearchItems = () => {

    const [searchItemsFetched, setSearchItemsFetched] = useState<ItemDetails[]>([]);


    const addSearchItems = (itemDetails: ItemDetails[]) => {
        console.log("TYhe Search Items-*******************-"+itemDetails)
        setSearchItemsFetched(itemDetails);
    }

    return (
        <div>
            <HeaderPanel>Search Items</HeaderPanel>
            <SearchPanel
                searchGridResults={addSearchItems} />
            <GridPanel>
                <SearchItemGrid searchGridResults = {searchItemsFetched} />
            </GridPanel>
        </div>


    )


}

export default SearchItems;