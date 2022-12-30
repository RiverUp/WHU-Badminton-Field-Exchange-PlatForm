<template>
  <order-exchange :exchanged="exchanged" />
</template>
<script>
import OrderExchange from "../components/OrderExchange.vue";
import { UpdatePurchaserNull } from "../api/OrderActions";
export default {
  name: "OrderExchangeView",
  components: {
    OrderExchange,
  },
  data() {
    return {
      exchanged: { order_id: null, state: false },
    };
  },
  // eslint-disable-next-line no-unused-vars
  beforeRouteLeave(to, from, next) {
    console.log("被触发");
    if (this.exchanged.state === false) {
      UpdatePurchaserNull(this.exchanged.order_id);
    }
    next();
  },
};
</script>
