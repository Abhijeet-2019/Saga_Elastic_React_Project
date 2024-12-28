import React, { useState, useEffect } from "react";
import { AgGridReact } from 'ag-grid-react'; // React Data Grid Component
import "ag-grid-community/styles/ag-grid.css"; // Mandatory CSS required by the Data Grid

import 'ag-grid-community/styles/ag-theme-alpine.css';
import { OrderItemObject } from "../models/OrderItemObject";

import { ColDef } from 'ag-grid-community'

interface AddCheckoutItems {
    checkoutList: OrderItemObject[];
}

const AddOrders = (props: AddCheckoutItems) => {
    const defaultColumnDef: ColDef[] = [
        {
            field: "itemCategory", headerName: 'Order Category',
            minWidth: 5, resizable: true, sortable: true
        },
        {
            field: "itemCode", headerName: 'Order Name',
            minWidth: 150, resizable: true, sortable: true
        },
        {
            field: "priceVal", headerName: 'Item Price',
            minWidth: 5, resizable: true, sortable: true
        },
        {
            field: "itemQuantity", headerName: 'Quaintity',
            minWidth: 10, resizable: true, sortable: true
        },
        {
            field: "orderStatus", headerName: 'Order Status',
            minWidth: 15, resizable: true, sortable: true
        },
        {
            field: "date", headerName: 'Date',
            minWidth: 15, resizable: true, sortable: true
        }
    ]
    return (
        <div
            className="ag-theme-alpine" // applying the Data Grid theme
            style={{ height: '500px' }} // the Data Grid will fill the size of the parent container
        >
            <AgGridReact
                rowData={props.checkoutList}
                columnDefs={defaultColumnDef}
                defaultColDef={{
                    flex: 1, // Makes columns stretch to fill available space
                    minWidth: 150,
                    resizable: true,
                }}
            />
        </div>
    )
}
export default AddOrders