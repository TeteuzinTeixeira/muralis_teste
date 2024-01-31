import React from 'react';
import { HashRouter, Route, Switch } from 'react-router-dom';
import Login from '../view/login';
import CadastroUsuario from '../view/cadastroUsuario';
import Home from '../view/home';

function Rotas(props) {
    return (
    <HashRouter>
        <Switch>
            <Route path="/home" component={Home} />
            <Route path="/login" component={Login} />
            <Route path="/cadastro-usuarios" component={CadastroUsuario} />
        </Switch>
    </HashRouter>
    );
}

export default Rotas;
