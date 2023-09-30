import Vue from 'vue'
import App from './App.vue'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'bootstrap/dist/css/bootstrap.min.css'

Vue.use(BootstrapVue)

Vue.config.productionTip = false

Vue.filter('to-uppercase', function (value) {
  return value.toUpperCase()
})

new Vue({
  router,
  render: function (h) { return h(App) }
}).$mount('#app')
