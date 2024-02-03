import React, { Component } from 'react';

import './../../../node_modules/bootswatch/dist/minty/bootstrap.css'
import '../custom.css'
import Rotas from './rotas';
import Navbar from '../components/navbar';
import ProvedorAutenticacao from './provedorAutenticacao';
import 'toastr/build/toastr.min.js'
import 'toastr/build/toastr.css'

import 'primereact/resources/themes/nova-light/theme.css'
import 'primereact/resources/primereact.min.css'
import 'primeicons/primeicons.css'

class App extends Component {
  render(){
    return(
      <ProvedorAutenticacao>
        <Navbar/>
        <div className='container'>
          <Rotas/>
        </div>
      </ProvedorAutenticacao>
    )
  }
}

export default App;