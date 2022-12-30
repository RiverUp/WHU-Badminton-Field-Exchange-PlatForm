<template>
  <div class="loginForm">
    <el-form :model="user">
      <el-form-item
        ><p>姓名</p>
        <el-input v-model="user.name" placeholder="请输入姓名"
      /></el-form-item>
      <el-form-item
        ><p>密码</p>
        <el-input
          v-model="user.passwords"
          type="password"
          placeholder="请输入密码"
      /></el-form-item>
      <el-form-item
        ><p>身份</p>
        <el-radio-group v-model="user.identity"
          ><el-radio label="用户" /><el-radio label="管理员" /></el-radio-group
      ></el-form-item>
      <el-button @click="Login">Login</el-button>
      <el-button @click="Signup">Signup</el-button>
    </el-form>
  </div>
</template>
<script>
import { reactive } from "@vue/reactivity";
import { Login } from "../api/UserActions";
import router from "../router/index";
import store from "../store/index";
import { ElMessage } from "element-plus";

export default {
  name: "LoginForm",
  data() {
    return {
      user: reactive({
        name: "",
        passwords: "",
        identity: "",
      }),
      errorMsg: "",
    };
  },
  methods: {
    Login: function () {
      if (this.user.identity === "管理员") {
        this.user.identity = true;
      } else {
        this.user.identity = false;
      }
      var response;
      Login(this.user).then((res) => {
        response = res.data;
        if (response.code === "200") {
          //localStorage.setItem("token", res.token);
          store.commit("StoreUser", { user: response.data });
          if (response.data.identity) {
            router.push({ name: "administrator" });
          } else {
            router.push({ name: "home" });
          }
        } else {
          //error warning
          this.errorMsg = response.msg;
          ElMessage.error(this.errorMsg);
        }
      });
    },
    Signup: function () {
      router.push({ name: "signup" });
    },
  },
};
</script>
<style scoped>
p {
  margin: 0%;
  margin-right: 2em;
  text-align: left;
  font-size: 1.3em;
}
</style>
<style>
.el-input .el-input__wrapper {
  opacity: 0.45;
}
.el-button {
  color: black;
  font-family: "mainFont";
  opacity: 0.75;
}
</style>
