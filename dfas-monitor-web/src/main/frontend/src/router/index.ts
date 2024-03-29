import Vue from 'vue'
import Router from 'vue-router'
import routes from "./routes"

Vue.use(Router);
const routerPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(error => error);
};
export default new Router({
  routes
});
