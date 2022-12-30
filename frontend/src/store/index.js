import { createStore } from "vuex";

export default createStore({
  state() {
    return {
      user: null,
      order: null,
      message: null,
      orderreported: null,
      userReported: null,
      report: null,
    };
  },
  getters: {},
  mutations: {
    StoreUser(state, user) {
      state.user = user;
    },
    StoreOrder(state, order) {
      state.order = order;
    },
    StoreMessage(state, message) {
      state.message = message;
    },
    ReportOrder(state, order) {
      state.orderReported = order;
    },
    ReportUser(state, user) {
      state.userReported = user;
    },
    StoreReport(state, report) {
      state.report = report;
    },
  },
  actions: {},
  modules: {},
});
