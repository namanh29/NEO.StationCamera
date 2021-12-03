import Vue from 'vue'
import Vuex from 'vuex'
import App from './App.vue'
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'

import Button from './components/base/Button'
import Table from './components/base/Table'
import Pagination from './components/base/Pagination'
import Combobox from './components/base/Combobox'
import Input from './components/base/Input'
import ToastMessage from './components/base/ToastMessage'
import Loader from './components/base/Loader'

Vue.use(Vuex)

import storeConfigs from './store'
const store = new Vuex.Store(storeConfigs)

Vue.component("Button", Button)
Vue.component("Table", Table)
Vue.component("Pagination", Pagination)
Vue.component("Combobox", Combobox)
Vue.component("Input", Input)
Vue.component("ToastMessage", ToastMessage)
Vue.component("Loader", Loader)

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  store
}).$mount('#app')
