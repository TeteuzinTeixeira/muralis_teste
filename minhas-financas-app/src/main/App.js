import React, { Component } from 'react';


import './../../../node_modules/bootswatch/dist/flatly/bootstrap.css'
import '../custom.css'
import Rotas from './rotas';
import Navbar from '../components/navbar';



class App extends Component {
  render(){
    return(
      <>
        <Navbar/>
        <div className='container'>
          <Rotas/>
        </div>
      </>
    )
  }
}

export default App;