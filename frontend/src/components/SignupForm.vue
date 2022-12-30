<template>
  <div class="loginForm">
    <el-form :model="user" :rules="rules">
      <el-form-item prop="name"
        ><p>姓名</p>
        <el-input v-model="user.name" placeholder="请输入姓名"
      /></el-form-item>
      <el-form-item prop="passwords"
        ><p>密码</p>
        <el-input
          v-model="user.passwords"
          type="password"
          placeholder="请输入密码"
      /></el-form-item>
      <el-form-item prop="passwords2"
        ><p>验证</p>
        <el-input
          v-model="user.passwords2"
          type="password"
          placeholder="请重复密码"
      /></el-form-item>
      <el-button @click="Signup">Signup</el-button>
    </el-form>
  </div>
</template>
<script>
import { reactive } from "@vue/reactivity";
import { Signup } from "../api/UserActions";
import router from "../router/index";
import store from "../store/index";
import { ElMessage } from "element-plus";

export default {
  name: "SignupForm",
  data() {
    const ValidateName = function (rules, value, callback) {
      if (value === "") {
        callback(new Error("姓名不能为空"));
      } else {
        callback();
      }
    };
    const ValidatePasswords = function (rules, value, callback) {
      if (value.length < 8 || value.length > 16) {
        callback(new Error("密码应是8~16位"));
      } else {
        callback();
      }
    };
    const VerrifyPasswords = function (rules, value, callback) {
      if (value !== this.user.passwords) {
        callback(new Error("确认密码与密码不同"));
      } else {
        callback();
      }
    };
    return {
      user: reactive({
        name: "",
        passwords: "",
        passwords2: "",
      }),
      rules: reactive({
        name: [{ validator: ValidateName, trigger: "blur" }],
        passwords: [{ validator: ValidatePasswords, trigger: "blur" }],
        passwords2: [{ validator: VerrifyPasswords, trigger: "blur" }],
      }),
      errorMsg: "",
    };
  },
  methods: {
    Signup: function () {
      const userInfo = {
        name: this.user.name,
        passwords: this.user.passwords2,
        identity: true,
      };
      var response;
      Signup(userInfo).then((res) => {
        response = res.data;
        if (response.code === "200") {
          //localStorage.setItem("token", res.token);
          store.commit("StoreUser", { user: response.data });
          router.push({ name: "home" });
        } else {
          //error warning
          ElMessage.error(this.errorMsg);
        }
      });
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
  margin-top: 5%;
}
</style>
