import React, { useState, useEffect, useContext, ReactNode } from "react";


import styled from "styled-components";
import { Button } from "react-bootstrap";
import { UserDataDetailsContext } from "../MyGlobalContext ";
import UploadItemModal from "../modal/UploadItemModal"
import { ItemType } from "../models/models";
import { sendHttpPersistRequest } from "../service/HttpService"

import { ItemDetails } from "../models/itemDetails";
import { InventoryTransaction } from "../models/InventoryTransaction";
import { fetchItemCodeList } from "../service/HttpService";

import { OrderItemObject } from "../models/OrderItemObject";

const LabelPanel = styled.label`
    color: teal;
    text-align:left;    
    font-size:22px;
    padding-bottom: 15px; 
   `;

const TextPanel = styled.label`
     color: red;
    text-align:left;    
    font-size:22px;
    padding-bottom: 15px; 
  `;


const TextFieldPanel = styled.label`
    text-align:left;    
    font-size:22px;
    width:5px;
    padding-bottom: 15px; 
  `;

const PricePanel = styled.label`
   color: red;
   background-color: #c6c6c9;
   text-align:left;    
   font-size:20px;
   padding-top: 0px; 
    
  `;


const ButtonPanel = styled.label`
   padding-top: 48px;   
   font-size:22
  `;

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

const AddItemPanel = styled.div`
      flex-flow: column;
      padding-left :10px ;
      padding-right :20px;   
      align: left
`;

const GridPanel = styled.div`
      background-color: darkGray;
      border: 4mm ridge rgba(211, 220, 50, .6);
      flex-flow: column;
      padding-left :40px ;
      padding-right :30px;   
      height : 80%;          
        align: left
`;
const modalbackdrop = styled.div`
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.4);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 2;
  `

interface AddItemPanelProps {
    isAddPanelTab: boolean;
    addToCheckList: (orderDetailsObj: OrderItemObject) => void;
    children?: ReactNode; // Add this line
}

const ItemSelectionPanel = (props: AddItemPanelProps) => {
    const itemId = "";
    const itemNamePlaceHolder = "Item Name Please";
    const itemQuaintityHolder = "Quaintity Please";
    const itemPriceHolder = "Price Please";
    const itemSoldByHolder = "Item Sold By";
    const { user, setUser } = useContext(UserDataDetailsContext);

    const [itemCategory, setItemCategotry] = useState('');
    const [itemCode, setItemCode] = useState('');
    const [itemQuantity, setQuaintity] = useState('');
    const [itemPrice, setItemPrice] = useState('');
    const [itemSoldBy, setItemSoldBy] = useState('');
    const [itemCodesList, setItemCodeList] = useState<string[]>([]);
    const [itemPriceMap, setItemPriceMap] = useState(new Map());
    const [priceVal, setPriceValue] = useState('Price In INR');

    const [orderStatus, setOrderStatus] = useState("Order placed")

    const [displayBulkUploadModal, setDisplayItemUpdateModal] = useState(false)
    const itemCategoryChange = (e: any) => {
        setItemCategotry(e.target.value);
        if (!props.isAddPanelTab) {
            console.log("Fetching all added item code for Category" + e.target.value);
            loadItemCodeList(e.target.value);
        }
    }

    const itemCodeChange = (e: any) => {
        e.preventDefault();
        setItemCode(e.target.value);
        const price = itemPriceMap.get(e.target.value);
        setPriceValue(price);
    }


    const loadItemCodeList = async (selectedItemCategory: String): Promise<any> => {
        let result;
        if (selectedItemCategory != "" || selectedItemCategory != null) {
            console.log(" The Selected Item ====== " + selectedItemCategory);
            try {
                result = await fetchItemCodeList(selectedItemCategory);
                console.log("The Result Map we receive--->>" + result.data)
                const mapData = result.data;
                console.log("HI "+Object.keys(mapData))
                setItemCodeList(Object.keys(mapData));   
                // setItemCodeList(mapData);

                setItemPriceMap(new Map(Object.entries(mapData)));
            } catch (error) {
                console.log(error);
                // throw error;
            }
        }
    }

    const itemTransaction: InventoryTransaction
        = {
        customerEmail: user.email,
        transactionType: "Items Added By Admin",
        transactionQuantity: itemQuantity,
        transactionDate: getCurrentDate()
    }

    function getCurrentDate() {
        const currentDate = new Date();
        const formattedDate = currentDate.toISOString();
        console.log(formattedDate);
        return formattedDate
    }


    const bulkAddItems = (e: any) => {
        e.preventDefault();
        setDisplayItemUpdateModal(!displayBulkUploadModal)

    }

    const addItenmTocheckList = (e: any) => {
        e.preventDefault();
        const item: OrderItemObject =
        {
            itemCategory, itemCode, itemQuantity, priceVal, orderStatus,
            date: getCurrentDate()
        };
        console.log("Adding an item to check list " + item);
        props.addToCheckList(item);
        setPriceValue("Price In INR");
        setItemCategotry('');
        setItemCode('');
        setQuaintity("");
    }

    const submitItem = (e: any) => {
        e.preventDefault();
        try {
            const requestBody: ItemDetails = {itemId,
                itemCategory, itemCode, itemQuantity,
                itemPrice, itemSoldBy, itemAddedDate: getCurrentDate(), itemTransaction
            };
            console.log("The requested mail ID" + requestBody.itemTransaction.customerEmail);
            saveNewItems(requestBody);
            setItemCode("");
            setItemCategotry("");
            setItemPrice("");
            setQuaintity("");
        } catch (error) {
            console.log(error);
        }
    }

    const saveNewItems = async (itemDetails: ItemDetails): Promise<any> => {
        alert("HI");
        let response;
        try {
            response = await sendHttpPersistRequest(itemDetails);
            console.log(" The Result Map we receive" + response)
        } catch (e) {
            console.log(e);
        }
    }
    const closeUploadModal = () => {
        setDisplayItemUpdateModal(false);
    }
    return (
        <div>
            <MainPanel>
                <AddItemPanel>
                    <LabelPanel>
                        Item Category:
                    </LabelPanel>
                    <select className="custom-select form-control"
                        value={itemCategory}
                        id="itemTypeList" onChange={itemCategoryChange}>
                        <option value="">Please select</option>
                        {
                            Object.values(ItemType).map((item) => (<option key={item}
                                value={item}>{item}</option>
                            ))
                        }
                    </select>
                </AddItemPanel>
                <AddItemPanel>
                    <LabelPanel>
                        Item Name:
                    </LabelPanel>
                    {props.isAddPanelTab ?
                        (<input type="text" className="form-control TextFieldPanel"
                            placeholder={itemNamePlaceHolder}
                            value={itemCode}
                            style={{ width: 140 }}
                            onChange={(e) => setItemCode(e.target.value)} />
                        ) :
                        (
                            <select className="custom-select form-control , TextFieldPanel" id="itemTypeList"
                                value={itemCode}
                                style={{ width: 100 }}
                                onChange={itemCodeChange}>
                                <option value="">Select Items</option>
                                {
                                    Object.values(itemCodesList).map((item) => (<option key={item}
                                        value={item}>{item}</option>
                                    ))
                                }
                            </select>
                        )}
                </AddItemPanel>
                <AddItemPanel>
                    <LabelPanel>
                        Item Quaintity:
                    </LabelPanel>
                    <input type="number" className="form-control ,TextFieldPanel"
                        placeholder={itemQuaintityHolder}
                        value={itemQuantity}
                        style={{ width: 130 }}
                        onChange={(e) => setQuaintity(e.target.value)} />
                </AddItemPanel>
                <AddItemPanel>
                    <LabelPanel>
                        Item unit Price:
                    </LabelPanel>
                    {
                        props.isAddPanelTab ?
                            (
                                <input type="number" className="form-control ,TextFieldPanel"
                                    placeholder={itemPriceHolder}
                                    value={itemPrice}
                                    style={{ width: 130 }}
                                    onChange={(e) => setItemPrice(e.target.value)} />
                            ) : (
                                <PricePanel className="form-control"
                                >{priceVal}
                                </PricePanel>
                            )
                    }
                </AddItemPanel>
                <AddItemPanel>
                    <LabelPanel>
                        Item Sold By:
                    </LabelPanel>
                    {
                        props.isAddPanelTab ?
                            (
                                <input type="text" className="form-control"
                                    placeholder={itemSoldByHolder}
                                    value={itemSoldBy}
                                    style={{ width: 180 }}
                                    onChange={(e) => setItemSoldBy(e.target.value)} />
                            ) : (
                                <PricePanel className="form-control"
                                >{priceVal}
                                </PricePanel>
                            )
                    }
                </AddItemPanel>
                <AddItemPanel>
                    <TextPanel>email :{user.email}</TextPanel>                    {
                        props.isAddPanelTab ?
                            (
                                <PricePanel className="form-control">

                                    <Button className="primary" onClick={submitItem}>
                                        Add Item
                                    </Button>
                                    &nbsp; &nbsp;
                                    <Button className="primary" onClick={bulkAddItems}>
                                        Add Bulk Items
                                    </Button>
                                </PricePanel>
                            ) : (
                                <PricePanel className="form-control">
                                    wallet : {user.userDetails.amountInWallet}
                                </PricePanel>)
                    }
                </AddItemPanel>
                <AddItemPanel >
                    <ButtonPanel>
                        {props.isAddPanelTab ?
                            (
                                <div></div>
                            ) : (
                                <Button className="primary" onClick={addItenmTocheckList}>
                                    +
                                </Button>
                            )
                        }
                    </ButtonPanel>
                </AddItemPanel>
            </MainPanel>
            {props.isAddPanelTab ? (
                <div style={{ display: !displayBulkUploadModal ? "none" : "block" }}>
                    <UploadItemModal
                        show={displayBulkUploadModal}
                        onClose={closeUploadModal}
                    ></UploadItemModal>
                </div>
            ) : (
                <div></div>
            )
            }

        </div>
    )
}
export default ItemSelectionPanel;