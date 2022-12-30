<template>
  <el-footer>
    <el-button @click="RouteToField">场地</el-button>
    <el-button @click="RouteToRelease">发布</el-button>
    <el-button @click="RouteToMessage">消息</el-button>
    <el-button @click="RouteToMe">我</el-button>
  </el-footer>
</template>
<script>
import router from "../router/index";
import store from "../store/index";

export default {
  name: "PageFooter",
  methods: {
    RouteToField: function () {
      if (this.$route.name === "home") {
        this.$emit("Refresh");
      }
      router.push({ name: "home" });
    },
    RouteToRelease: function () {
      router.push({ name: "orderLaunch" });
    },
    RouteToMessage: function () {
      if (this.$route.name === "message") {
        this.$emit("Refresh");
      }
      router.push({ name: "message" });
    },
    RouteToMe: function () {
      if (this.userLogged === null) {
        router.push({ name: "login" });
      } else {
        router.push({ name: "user", params: { id: this.userLogged.user.id } });
      }
    },
  },
  computed: {
    userLogged() {
      return store.state.user;
    },
  },
};
</script>
<style scoped>
.el-footer {
  height: 7%;
  padding: 0%;
  position: absolute;
  bottom: 0.2rem;
  width: 100%;
}
.el-footer .el-button {
  margin: 0%;
  height: 100%;
  width: 25%;
  font-size: 1.2rem;
  font-family: "mainFont";
  border: none;
}
</style>
