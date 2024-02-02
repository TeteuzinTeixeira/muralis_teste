import React, { Component } from 'react';


import './../../../node_modules/bootswatch/dist/darkly/bootstrap.css'
import '../custom.css'
import Rotas from './rotas';
import Navbar from '../components/navbar';
import 'toastr/build/toastr.min.js'
import 'toastr/build/toastr.css'

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