import axios from "axios";
import { ItemDetails } from "../models/itemDetails";
import { SearchCriteria } from "../models/SearchCriteria";


const baseItemUrl = "http://localhost:8082/inventoryService/";

const baseUserUrl = "http://localhost:8084/userService/";

/**
 * Request using axios client to save an Item in Elastic Search..
 */
export const sendHttpPersistRequest = (itemDetails: ItemDetails): any => {
    const result = axios.post(baseItemUrl + "elasticItems/addItemInElastic", itemDetails);
    console.log("The output of Individual Insert--" + result);
    return result;
}

/**
 * HTTP Request using axios client to send a search request for Elastic search.
 */
export const fetchItemDetails = (searchCriteria: SearchCriteria): any => {
    const result = axios.get(baseItemUrl + "elasticItems/fetchItemDetails?nameSearch="
        + searchCriteria.nameSearch + "&categorySearch=" + searchCriteria.categorySearch
        + "&sellerName=" + searchCriteria.sellerName);
    return "working calling a HTTP fun" + result;
}



/**
 * Fetch all data from Sega Item Service 
 * http://localhost:8082/itemService/items/fetchItems
 */
export const fectAllItems = (): any => {
    const response = axios.get(baseItemUrl + "items/fetchItems")
    return response;
}

/**
 * This method is used to load all the items from Elastic.
 * @returns 
 */
export const loadAllElasticItems = (fetchSize: any, offset: any): any => {
    const response = axios.get(baseItemUrl +
        "elasticItems/initialLoadItems?fetchSize=" + fetchSize + "&offset=" + offset);
    console.log("The initail load from Elastic" + response)
    return response;
}


export const fetchedSelectedItems = (selectedItemCategory: SearchCriteria): any => {
    const response =
        axios.get(baseItemUrl +
            "elasticItems/fetchedSelectedItems?nameSearch=" + selectedItemCategory.nameSearch +
            "&categorySearch="+selectedItemCategory.categorySearch+
            "&sellerName="+selectedItemCategory.sellerName)
    return response;
}


/**
 * 
 * @param signUpUserDetails 
 * @returns 
 */
export const signUpUserDetails = (signUpUserDetails: User): string => {
    const result = axios.post(baseUserUrl + "user/SignUp", signUpUserDetails);
    return "working calling a HTTP fun" + result;
}

export const validateLogin = (email: String, password: String): any => {
    const response =
        axios.get(baseUserUrl + "user/validateLogin?email=" + email + "&password=" + password)
    return response;
}





/**
 * Update Bulk Data in Elastic 
 */
export const persistDataInElastic = (datatoPersist: Array<any>): any => {
    const result = axios.post(baseItemUrl + "elasticItems/uploadBulkItems", datatoPersist);
    return result;
}