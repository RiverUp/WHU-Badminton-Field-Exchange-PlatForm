<template>
  <el-container>
    <el-header>
      <el-select v-model="gym" placeholder="场馆">
        <el-option
          v-for="gym in gyms"
          :key="gym.value"
          :label="gym.label"
          :value="gym.value"
        >
        </el-option>
      </el-select>
      <el-select v-model="date" placeholder="日期">
        <el-option
          v-for="date in dates"
          :key="date.value"
          :label="date.label"
          :value="date.value"
          :disabled="date.disabled"
        >
        </el-option>
      </el-select>
      <el-time-select
        v-model="beginTime"
        start="08:00"
        step="00:30"
        end="21:00"
        placeholder="开始时间"
      />
      <el-button @click="SearchOrders">搜索</el-button>
    </el-header>
    <el-main>
      <div class="orders" v-for="(order, index) in orders" :key="order.id">
        <el-card>
          <template #header>
            <div class="card-header">
              <span v-if="order.gym === 0">卓尔体育馆</span>
              <span v-if="order.gym === 1">风雨体育馆</span>
              <span v-if="order.gym === 2">工学部体育馆</span>
              <span v-if="order.gym === 3">信息学部体育馆</span>
              <span v-if="order.gym === 4">网安体育馆</span>
              <span v-if="order.gym === 5">医学部体育馆</span>
              <el-button @click="Exchange(index)">交换</el-button>
            </div>
          </template>
          <p>场地号：{{ order.field_no }}</p>
          <p>时间：{{ order.begin_time.split(" ")[0] }}</p>
          <p>开始于{{ order.begin_time.split(" ")[1] }}</p>
          <p>结束于{{ order.finish_time.split(" ")[1] }}</p>
        </el-card>
      </div>
    </el-main>
    <page-footer @Refresh="Refresh" />
  </el-container>
</template>

<script>
import { FormatDate } from "../util/DateFormatter";
import {
  GetAvailableOrder,
  GetSatisfiedOrder,
  GetLocked,
  UpdatePurchaser,
} from "../api/OrderActions";
import store from "../store/index";
import router from "../router/index";
import { ElMessage } from "element-plus";
import PageFooter from "./PageFooter.vue";

export default {
  name: "OrderDisplay",
  components: {
    PageFooter,
  },
  data() {
    return {
      gyms: [
        {
          value: 0,
          label: "卓尔体育馆",
        },
        {
          value: 1,
          label: "风雨体育馆",
        },
        {
          value: 2,
          label: "工学部体育馆",
        },
        {
          value: 3,
          label: "信息学部体育馆",
        },
        {
          value: 4,
          label: "网安体育馆",
        },
        {
          value: 5,
          label: "医学部体育馆",
        },
      ],
      dates: [
        {
          value: Date.now(),
          label: "今天",
          disabled: false,
        },
        {
          value: Date.now() + 86400000,
          label: "明天",
          disabled: true,
        },
      ],
      orders: [],
      gym: "",
      date: "",
      beginTime: "",
    };
  },
  computed: {
    userLogged() {
      return store.state.user;
    },
  },
  methods: {
    SearchOrders: function () {
      var dateTemp = new Date(this.date);
      var date = FormatDate(dateTemp);
      var begin_time = date + this.beginTime + ":00";
      GetSatisfiedOrder(begin_time, this.gym).then((res) => {
        this.orders = res.data.data;
      });
    },
    Exchange: function (index) {
      var locked;
      var order = this.orders[index];
      GetLocked(order.id).then((res) => {
        locked = res.data.data;
        if (locked === true) {
          ElMessage({
            message: "该场地已被交换",
            type: "warning",
          });
        } else {
          if (this.userLogged === null) {
            ElMessage({ message: "用户未登录", type: "warning" });
          } else {
            store.commit("StoreOrder", order);
            UpdatePurchaser(this.userLogged.user.id, order.id);
            router.push({ name: "orderExchange" });
          }
        }
      });
    },
    Refresh: function () {
      GetAvailableOrder().then((res) => {
        this.orders = res.data.data;
      });
    },
  },
  mounted() {
    var time = new Date();
    var hour = time.getHours();
    if (hour >= 18) {
      this.dates[1].disabled = false;
    }
    GetAvailableOrder().then((res) => {
      this.orders = res.data.data;
    });
  },
};
</script>
<style scoped>
.el-container {
  height: 100%;
  position: absolute;
  padding: 0%;
}
.el-header {
  padding: 0;
  padding-top: 2%;
}
.el-select {
  width: 27%;
}
.el-main {
  height: 83%;
  padding: 0%;
}
.el-card {
  border-radius: 2rem;
  border-color: greenyellow;
  margin: 1rem;
}
.el-card .el-button {
  left: 4rem;
  position: relative;
}
</style>
