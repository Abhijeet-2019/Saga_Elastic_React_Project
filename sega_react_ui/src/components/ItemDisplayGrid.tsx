import React, { useState, useEffect, useContext } from "react";
import { AgGridReact } from 'ag-grid-react'; // React Data Grid Component
import "ag-grid-community/styles/ag-grid.css"; // Mandatory CSS required by the Data Grid

import 'ag-grid-community/styles/ag-theme-alpine.css';
import { loadAllElasticItems } from "../service/HttpService"
import { ItemDetails } from "../models/itemDetails";
import { defaultColumnDef } from '../service/GridConstant'
import { UserDataDetailsContext } from "../MyGlobalContext ";


const ItemDisplayGrid = () => {
    const [rows, setRowsData] = useState<ItemDetails[]>([]);
    const { user ,setUser} = useContext(UserDataDetailsContext);


    /**
     * This method useEffect is called when the component is loaded 
     */
    useEffect(() => {     
        console.log(" Load all Items from Invertory Service"+user.valid);
        if(user.valid){
            loadAllItems()
        }        
    }, [])

    const loadAllItems = async (): Promise<any> => {
        let result;
        try {
            result = await loadAllElasticItems(20,0);
            console.log("The Result Map we receive--->>" + result.map)
            setRowsData(result.data);
            return result;
        } catch (error) {
            console.log("Unable to load data from Elastic***"+error);
            
        }
    }

    return (
        <div
            className="ag-theme-alpine" // applying the Data Grid theme
            style={{ height: '500px' }} // the Data Grid will fill the size of the parent container
        >
            <AgGridReact
                rowData={rows}
                columnDefs={defaultColumnDef}
                defaultColDef={{
                    flex: 1, // Makes columns stretch to fill available space
                    minWidth: 150,
                    resizable: true,
                  }}
            />
        </div>
    );
}

export default ItemDisplayGrid;