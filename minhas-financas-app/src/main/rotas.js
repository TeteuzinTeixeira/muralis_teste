import React from 'react';
import { HashRouter, Route, Switch, Redirect } from 'react-router-dom';
import Login from '../view/login';
import CadastroUsuario from '../view/cadastroUsuario';
import ConsultaLancamentos from '../view/lancamentos/consulta-lancamentos';
import CadastroLancamentos from '../view/lancamentos/cadastro-lancamentos';
import Home from '../view/home';
import AuthService from '../app/service/authService';

const isUsuarioAutenticado = () => {
    return false;
}


function RotaAutenticada({ component: Component, ...props }) {
    return (
        <Route {...props} render={(componentProps) => {
            if (AuthService.isUsuarioAutenticado()) {
                return (
                    <Component {...componentProps} />
    )
        }else{
    return(
            <Redirect to={{pathname : '/login', state : {from: componentProps.location}}} />
        )
}
    }} />
    )
}

function Rotas(props) {
    return (
    <HashRouter>
        <Switch>
            <Route path="/login" component={Login} />
            <Route path="/cadastro-usuarios" component={CadastroUsuario} />

            <RotaAutenticada path="/home" component={Home} />
            <RotaAutenticada path="/consulta-lancamentos" component={ConsultaLancamentos} />
            <RotaAutenticada path="/cadastro-lancamentos/:id?" component={CadastroLancamentos} />
        </Switch>
    </HashRouter>
    );
}

export default Rotas;
