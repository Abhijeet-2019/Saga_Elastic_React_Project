import React, { useState, useEffect } from "react";
import { toast } from 'react-toastify';
import styled from "styled-components";
import { Button } from "react-bootstrap";
import { AppAlert, ToastErrorOptions } from "../modal/AppAlert";

import { SearchCriteria } from "../models/SearchCriteria";

import { fetchedSelectedItems } from "../service/HttpService";
import { ItemDetails } from "../models/itemDetails";
import { ItemType } from "../models/models";

const MainPanel = styled.div`
position :relative;
height : 100%;        
display : flex;
border : 0.0625rem solid white;   
padding: 25px;
margin :20px;
flex-wrap: wrap;
margin-right :30px ;
`;
const LabelPanel = styled.label`
color: teal;
text-align:left;    
font-size:22px;
padding-bottom: 15px; 
`;
const AddItemPanel = styled.div`
  flex-flow: column;
  padding-left :10px ;
  padding-right :20px;   
  align: left
`;
const ButtonPanel = styled.label`
padding-top: 48px;   
font-size:22
`;

interface SearchItemsProps {
    searchGridResults: (itemDetails: ItemDetails[]) => void
}

const SearchPanel = (props: SearchItemsProps) => {
    const itemCategoryPlaceHolder = "Input Category Please!";
    const itemNamePlaceHolder = "Input Name Please!";
    const itemSoldByPlaceHolder = "Seller Name Please!";

    const [nameSearch, setNameSearch] = useState("");
    const [categorySearch, setCategorySearch] = useState("");
    const [sellerName, setSetSellerName] = useState("");


    const itemCodeChange = (e: any) => {
        e.preventDefault();
        setCategorySearch(e.target.value);
        
        
    }

    const submitSearch = (e: any) => {
        e.preventDefault();
        if (!validateFields()) {
            return;
        }
        const requestBody: SearchCriteria =
            { nameSearch: nameSearch, categorySearch: categorySearch, sellerName: sellerName };
        startFetchingData(requestBody);
    }

    const startFetchingData = async (searchCriteria: SearchCriteria): Promise<any> => {
        let resultfetched;
        console.log("The Search criteria " + searchCriteria.nameSearch + "");
        try {
            resultfetched = await fetchedSelectedItems(searchCriteria);
            console.log(" The Result Map we receive" + resultfetched.data);
            // console.log("The Search Grid data --->"+searchGridData);
            props.searchGridResults(resultfetched.data);
            if (resultfetched.data == '') {
                alert("Please check a valid combination of Search");
            }
        } catch {

        }
        // Add a Logic to call the search Grid method to populate the grid details
    }

    const validateFields = () => {
        if ((nameSearch == undefined || nameSearch == "")
            && (categorySearch == undefined || categorySearch == "")
            && (sellerName == undefined || sellerName == "")
        ) {
            toast.error(displayNameEmptyError("Please Enter atleast One Search Criteria"), ToastErrorOptions)
            return false;
        } else {
            return true;
        }

    }

    const displayNameEmptyError = (errorMessage: string) => {
        return (<AppAlert header='Missing Fields'
            content={`Error::${errorMessage}`} />)
    }

    return (
        <div>
            <MainPanel>
                <AddItemPanel>
                    <LabelPanel>
                        Item Category:
                    </LabelPanel>
                    <select className="custom-select form-control"
                        value={categorySearch}
                        id="itemTypeList" onChange={itemCodeChange}>
                        <option value="">Please select</option>
                        {
                            Object.values(ItemType).map((item) => (<option key={item}
                                value={item}>{item}</option>
                            ))
                        }
                    </select>
                    {/* <input type="text" className="form-control"
                        placeholder={itemCategoryPlaceHolder}
                        value={categorySearch}
                        style={{ width: 180 }}
                        onChange={(e) => setCategorySearch(e.target.value)} /> */}
                </AddItemPanel>
                <AddItemPanel>
                    <LabelPanel>
                        Item Name:
                    </LabelPanel>
                    <input type="text" className="form-control"
                        placeholder={itemNamePlaceHolder}
                        value={nameSearch}
                        style={{ width: 180 }}
                        onChange={(e) => setNameSearch(e.target.value)} />
                </AddItemPanel>
                <AddItemPanel>
                    <LabelPanel>
                        Item Sold By:
                    </LabelPanel>
                    <input type="text" className="form-control"
                        placeholder={itemSoldByPlaceHolder}
                        value={sellerName}
                        style={{ width: 180 }}
                        onChange={(e) => setSetSellerName(e.target.value)} />
                </AddItemPanel>

                <ButtonPanel>
                    <Button className="primary" onClick={submitSearch}>
                        Search
                    </Button>
                </ButtonPanel>

            </MainPanel>
        </div>
    )
}

export default SearchPanel;