import React from 'react';
import { HashRouter, Route, Switch } from 'react-router-dom';
import Login from '../view/login';
import CadastroUsuario from '../view/cadastroUsuario';
import ConsultaLancamentos from '../view/lancamentos/consulta-lancamentos';
import CadastroLancamentos from '../view/lancamentos/cadastro-lancamentos';
import Home from '../view/home';

function Rotas(props) {
    return (
    <HashRouter>
        <Switch>
            <Route path="/home" component={Home} />
            <Route path="/login" component={Login} />
            <Route path="/cadastro-usuarios" component={CadastroUsuario} />
            <Route path="/consulta-lancamentos" component={ConsultaLancamentos} />
            <Route path="/cadastro-lancamentos" component={CadastroLancamentos} />
        </Switch>
    </HashRouter>
    );
}

export default Rotas;