import LocalStorageService from './localstorageService'

export const USUARIO_LOGADO = '_usuario_logado'

export default class AuthService {
    static isUsuarioAutenticado() {

        const usuarioLogado = LocalStorageService.obterItem('_usuario_logado')

        const usuario = LocalStorageService.obterItem(USUARIO_LOGADO)
        return usuario && usuarioLogado;
    }
    static removerUsuarioAutenticado() {
        localStorage.removeItem(USUARIO_LOGADO)
    }

    static logar(usuario){
        LocalStorageService.adicionarItem(USUARIO_LOGADO, usuario)
    }

    obterUsuarioAutenticado(){
        return LocalStorageService.obterItem(USUARIO_LOGADO)
    }
}