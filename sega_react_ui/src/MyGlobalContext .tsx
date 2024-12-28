import { createContext, useContext } from "react"
import React from "react"

export const userDetails ={
    address :"",
    cellNo :"",     
    city :"", 
    state :"",
    pinCode :0,    
    amountInWallet : 0,
}
export const defaultUserDetails ={
    email :"",
    password :"",
    userName :"",
    lastLoginTime : "", 
    valid : false,
    userDetails : userDetails 
}

export const userDefaultContext ={ 
    user : defaultUserDetails,
    setUser : (user :User) =>{       
    }
}

export const UserDataDetailsContext = React.createContext(userDefaultContext)

