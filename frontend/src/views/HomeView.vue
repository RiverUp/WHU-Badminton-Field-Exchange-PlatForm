<template>
  <div class="home">
    <OrderShow />
  </div>
</template>

<script>
// @ is an alias to /src
import OrderShow from "../components/OrderShow.vue";

export default {
  name: "HomeView",
  components: {
    OrderShow,
  },
};
</script>
