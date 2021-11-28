const toastMessage = {
    state: {
        toast: {}
    },
    getters: {
        toast: state => state.toast
    },
    mutations: {
        setToast(state, newToast){
            state.toast = newToast
        }
    },
    actions: {
        addToast({commit}, toast){
            commit("setToast", toast)
        }
    }
}
export default toastMessage;