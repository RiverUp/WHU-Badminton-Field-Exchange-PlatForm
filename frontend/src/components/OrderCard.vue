<template>
  <el-card id="card">
    <template #header>
      <span>订单编号{{ this.order.id }}</span>
      <el-button @click="Report" v-if="completed">举报</el-button>
      <el-button @click="Withdraw" v-if="!completed">撤回</el-button>
    </template>
    <p>场地号：{{ this.order.field_no }}</p>
    <p>时间：{{ this.order.begin_time.split(" ")[0] }}</p>
    <p>开始于{{ this.order.begin_time.split(" ")[1] }}</p>
    <p>结束于{{ this.order.finish_time.split(" ")[1] }}</p>
    <p v-if="type === 'seller' && completed">
      交换者：{{ this.order.purchaser.name }}
    </p>
    <p v-if="type === 'purchaser'">交换者：{{ this.order.seller.name }}</p>
  </el-card>
</template>
<script>
import store from "../store/index";
import router from "../router/index";
import { ElMessage, ElMessageBox } from "element-plus";
import { WithdrawOrder } from "../api/OrderActions";

export default {
  name: "OrderCard",
  props: {
    order: Object,
    type: String,
  },
  computed: {
    completed() {
      if (this.order.purchaser === null) {
        return false;
      } else {
        return true;
      }
    },
  },
  methods: {
    Report: function () {
      store.commit("ReportOrder", this.order);
      var userReported;
      if (this.type === "seller") {
        userReported = this.order.purchaser;
      } else {
        userReported = this.order.seller;
      }
      store.commit("ReportUser", userReported);
      router.push({
        name: "report",
        params: {
          order_id: this.order.id,
        },
      });
    },
    Withdraw: function () {
      ElMessageBox.confirm("确认撤销订单", "注意", {
        confirmButtonText: "确认",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          WithdrawOrder(this.order.id)
            .then((res) => {
              if (res.data.code === "200") {
                ElMessage({ type: "success", message: "订单已撤销" });
              } else {
                ElMessage({ type: "error", message: "订单撤销失败" });
              }
            })
            .catch(() => {
              ElMessage({ type: "error", message: "订单撤销失败" });
            });
        })
        .catch(() => {
          ElMessage({
            type: "info",
            message: "取消撤销",
          });
        });
    },
  },
  mounted() {
    if (this.completed) {
      var card = document.getElementById("card");
      card.style.borderColor = `rgb(245, 47, 74)`;
    }
    console.log(this.completed);
  },
};
</script>
<style scoped>
.el-card {
  border-radius: 2rem;
  border-color: rgb(43, 166, 207);
  margin: 1rem;
}
.el-card .el-button {
  left: 4rem;
  position: relative;
}
</style>
