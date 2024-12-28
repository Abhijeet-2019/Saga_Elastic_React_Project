import { InventoryTransaction } from "./InventoryTransaction";


export type ItemDetails = {
    itemId:string;
    itemCategory : string;
    itemCode :string;
    itemQuantity : string ;
    itemPrice :string;
    itemSoldBy :string;
    itemAddedDate :string;
    itemTransaction :InventoryTransaction ;   
}


