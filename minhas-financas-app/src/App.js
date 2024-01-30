import React, { Component } from 'react';
import './App.css';


import './../../node_modules/bootswatch/dist/flatly/bootstrap.css'
import Login from './view/login';

class App extends Component {
  render(){
    return(
      <div>
        <Login/>
      </div>
    )
    
  }
}

export default App;