<template>
  <el-container>
    <el-main>
      <el-tabs type="border-card">
        <el-tab-pane label="卖家订单">
          <div v-for="order in sellerOrders" :key="order.id">
            <order-card type="seller" :order="order" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="买家订单">
          <div v-for="order in purchaserOrders" :key="order.id">
            <order-card type="purchaser" :order="order" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="个人信息">
          <p>用户名：{{ this.userLogged.user.name }}</p>
          <p>密码： {{ this.userLogged.user.passwords }}</p>
          <p>违规次数：{{ this.userLogged.user.creditworthiness }}</p>
          <el-button type="danger" plain @click="Logout">退出登录</el-button>
        </el-tab-pane>
      </el-tabs>
    </el-main>
    <page-footer />
  </el-container>
</template>
<script>
import OrderCard from "./OrderCard.vue";
import PageFooter from "./PageFooter.vue";
import store from "../store/index";
import router from "../router/index";
import { GetSellerOrder, GetPurchaserOrder } from "../api/OrderActions";

export default {
  name: "UserPage",
  data() {
    return {
      sellerOrders: [],
      purchaserOrders: [],
    };
  },
  components: {
    OrderCard,
    PageFooter,
  },
  computed: {
    userLogged() {
      return store.state.user;
    },
  },
  methods: {
    Logout: function () {
      store.commit("StoreUser", null);
      router.push({ name: "home" });
    },
  },
  mounted() {
    GetSellerOrder(this.userLogged.user.id).then((res) => {
      this.sellerOrders = res.data.data;
    });
    GetPurchaserOrder(this.userLogged.user.id).then((res) => {
      this.purchaserOrders = res.data.data;
    });
  },
};
</script>
<style scoped>
.el-container {
  height: 100%;
  padding: 0%;
  width: 100%;
  display: block;
}
.el-main {
  height: 86%;
  padding: 0%;
}
</style>
