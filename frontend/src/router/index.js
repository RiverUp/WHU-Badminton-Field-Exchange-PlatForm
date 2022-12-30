import { createRouter, createWebHashHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../views/LoginView.vue"),
  },
  {
    path: "/signup",
    name: "signup",
    component: () => import("../views/SignupView.vue"),
  },
  {
    path: "/orderLaunch",
    name: "orderLaunch",
    component: () => import("../views/OrderHandingView.vue"),
  },
  //aboutView
  {
    path: "/message",
    name: "message",
    component: () => import("../views/MessageShowView.vue"),
  },
  {
    path: "/user/:id",
    name: "user",
    component: () => import("../views/UserView.vue"),
  },
  {
    path: "/exchange",
    name: "orderExchange",
    component: () => import("../views/OrderExchangeView.vue"),
  },
  {
    path: "/message/:user_id/:message_id",
    name: "certainMessage",
    component: () => import("../views/CertainMessageView.vue"),
  },
  {
    path: "/report/:order_id",
    name: "report",
    component: () => import("../views/ReportView.vue"),
  },
  {
    path: "/administrator",
    name: "administrator",
    component: () => import("../views/AdministratorView.vue"),
  },
  {
    path: "/report/:report_id",
    name: "certainReport",
    component: () => import("../views/CertainReportView.vue"),
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
