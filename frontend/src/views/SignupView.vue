<template>
  <div class="signupView">
    <div class="form">
      <signup-form />
    </div>
  </div>
</template>
<script>
import SignupForm from "../components/SignupForm.vue";
export default {
  name: "SignupView",
  components: { SignupForm },
};
</script>
<style scoped>
.signupView {
  height: 100%;
  background-image: url("../assets/cover.jpg");
  background-size: cover;
  background-repeat: no-repeat;
}
.form {
  height: 60%;
  width: 75%;
  font-family: "mainFont";
  padding-top: 40%;
  padding-left: 10%;
}
</style>
