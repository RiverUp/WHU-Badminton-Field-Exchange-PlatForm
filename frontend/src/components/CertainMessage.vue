<template>
  <el-container>
    <el-page-header @back="BackToMessages">
      <template #content>
        <span>订单截图</span>
      </template>
    </el-page-header>
    <el-image :src="this.url" style="width: 100%; height: 90%" :fit="fit" />
  </el-container>
</template>
<script>
import store from "../store/index";
import router from "../router/index";

export default {
  name: "CertainMessage",
  data() {
    return {
      fit: "contain",
    };
  },
  computed: {
    message() {
      return store.state.message;
    },
    url() {
      var relatedUrl = this.message.order.fieldOrder.url.split(":")[1];
      return "http://localhost:8900" + relatedUrl;
    },
  },
  methods: {
    BackToMessages: function () {
      router.push({ name: "message" });
    },
  },
};
</script>
<style scoped>
.el-container {
  height: 100%;
  display: block;
  padding: 0%;
}
.el-page-header {
  padding: 2%;
  font-family: "mainFont";
}
.el-main {
  height: 80%;
  padding: 0%;
}
</style>
