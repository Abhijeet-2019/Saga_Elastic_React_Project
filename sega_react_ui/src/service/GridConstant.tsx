import { ColDef } from 'ag-grid-community'
export const defaultColumnDef: ColDef[] = [
    { field: "itemCategory", headerName: 'Item Category', minWidth: 5, resizable: true, sortable: true },
    { field: "itemCode", headerName: 'Item Code', minWidth: 15, resizable: true, sortable: true },
    { field: "itemPrice", headerName: 'Item Price', minWidth: 5, resizable: true, sortable: true },
    { field: "itemQuantity", headerName: 'Item Quaintity', minWidth: 10, resizable: true, sortable: true },
    { field: "itemTransaction.customerEmail", headerName: 'Customer Email', minWidth: 10, resizable: true, sortable: true },
    { field: "itemTransaction.transactionType", headerName: 'Transaction Type', minWidth: 15, resizable: true, sortable: true },
    { field: "itemTransaction.transactionDate", headerName: 'Transaction Date', minWidth: 15, resizable: true, sortable: true },
]