<template>
  <div class="loginView">
    <p>武汉大学羽毛球场地交换系统</p>
    <div class="form">
      <login-form />
    </div>
  </div>
</template>
<script>
import LoginForm from "../components/LoginForm.vue";
export default {
  name: "LoginView",
  components: { LoginForm },
};
</script>
<style scoped>
.loginView {
  height: 100%;
  background-image: url("../assets/cover.jpg");
  background-size: cover;
  background-repeat: no-repeat;
}
.form {
  height: 60%;
  width: 75%;
  font-family: "mainFont";
  padding-top: 13%;
  padding-left: 10%;
}
p {
  margin: 0%;
  font-size: 7vw;
  font-weight: bolder;
  padding-top: 25%;
}
</style>
