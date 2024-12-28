import React, { useState, useEffect } from "react";
import AppRoutes from './routes/AppRoutes';

import LoginModal from "./modal/LoginModal";
import { defaultUserDetails, UserDataDetailsContext } from "./MyGlobalContext ";

import { CustomToastContainer } from "./styles/styled-toast";
import 'react-toastify/dist/ReactToastify.css'
import './App.css';

function App() {

  const [showLoginModal, setShowLoginModal] = useState(false);

  useEffect(() => {
    setShowLoginModal(true);
  }, [])

  const handleCloseLoginModal = () => {
    setShowLoginModal(false);
  };

  const displayModelForInvalidLogin = () => {
    setShowLoginModal(true);
  }

  const handelAppLogin = (user) => {
    console.log("Start the login for the application" + user.email + "--" + user.password);
    setUser(user);
    setShowLoginModal(true);
  }


  const [user, setUser] = useState(defaultUserDetails);

  return (
    <div className="App">
      <UserDataDetailsContext.Provider value={{ user, setUser }}>
        <AppRoutes />
        <CustomToastContainer/>
      </UserDataDetailsContext.Provider>

      <LoginModal
        show={showLoginModal}
        onClose={handleCloseLoginModal}
        showModelForInValidUser={displayModelForInvalidLogin}
        onLogin={handelAppLogin}
      />
    </div>
  );
}

export default App;
