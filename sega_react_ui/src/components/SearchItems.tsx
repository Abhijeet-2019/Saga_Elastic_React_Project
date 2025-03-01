import React, { useState, useEffect } from "react";
import { Button } from "react-bootstrap";

import styled from "styled-components";
import SearchItemGrid from "./SearchItemGrid";
import SearchPanel from "./SearchPanel";
import { ItemDetails } from "../models/itemDetails";

import { fetchAllUsers } from "../service/HttpService";

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
const ButtonPanel = styled.label`
padding-top: 48px;   
font-size:22
`;
const SearchItems = () => {

    const [searchItemsFetched, setSearchItemsFetched] = useState<ItemDetails[]>([]);

    const addSearchItems = (itemDetails: ItemDetails[]) => {
        console.log("The Search Items-*******************-" + itemDetails)
        setSearchItemsFetched(itemDetails);
    }

    const fetchUsers = () => {
        alert("Hi i am fetching all users");
        const result = fetchAllUsers();
        console.log(" The result " + result);
    }

    return (
        <div>
            <HeaderPanel>Search Items</HeaderPanel>
            <ButtonPanel>
                <Button className="primary" onClick={fetchUsers}>
                    fetchAllUser
                </Button>
            </ButtonPanel>
            <SearchPanel
                searchGridResults={addSearchItems} />
            <GridPanel>
                <SearchItemGrid searchGridResults={searchItemsFetched} />
            </GridPanel>
        </div>
    )
}

export default SearchItems;