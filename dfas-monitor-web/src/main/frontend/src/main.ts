// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import filters from './mixin/filters'

Vue.config.productionTip = false

Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key]);
});
//全局锁屏判断
router.beforeEach((to, from, next) => {
  if (localStorage.getItem("lockName")) {
    next(false);
  } else {
    next();
  }
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
