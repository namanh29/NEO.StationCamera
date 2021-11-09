import Vue from 'vue'
import App from './App.vue'
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'

import Button from './components/base/Button'
import Table from './components/base/Table'
import Pagination from './components/base/Pagination'
import Combobox from './components/base/Combobox'

Vue.component("Button", Button)
Vue.component("Table", Table)
Vue.component("Pagination", Pagination)
Vue.component("Combobox", Combobox)

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
