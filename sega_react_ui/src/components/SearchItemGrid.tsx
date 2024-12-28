import React, { useState, useEffect, useContext } from "react";
import { AgGridReact } from 'ag-grid-react'; // React Data Grid Component

import "ag-grid-community/styles/ag-grid.css"; // Mandatory CSS required by the Data Grid
import { ItemDetails } from "../models/itemDetails";

import 'ag-grid-community/styles/ag-theme-alpine.css';
import { defaultColumnDef } from '../service/GridConstant'

interface SearhItemGridFetched{
    searchGridResults : ItemDetails[] ;
}

const SearchItemGrid = (props : SearhItemGridFetched) => {
    const [rows, setRowsData] = useState<ItemDetails[]>([]);
    return (
        <div>
            <div
                className="ag-theme-alpine" // applying the Data Grid theme
                style={{ height: '500px' }} // the Data Grid will fill the size of the parent container
            >   
            <div></div>
                    <AgGridReact
                    rowData={props.searchGridResults}
                    columnDefs={defaultColumnDef}
                    defaultColDef={{
                        flex: 1, // Makes columns stretch to fill available space
                        minWidth: 150,
                        resizable: true,
                    }}
                />
            </div>
        </div>
    )
}
export default SearchItemGrid;
