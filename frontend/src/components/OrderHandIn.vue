<template>
  <el-container>
    <el-form :model="order">
      <el-form-item label="场馆">
        <el-select v-model="orderForm.gym" placeholder="场馆">
          <el-option
            v-for="gym in gyms"
            :key="gym.value"
            :label="gym.label"
            :value="gym.value"
          >
          </el-option> </el-select
      ></el-form-item>
      <el-form-item label="场地号">
        <el-input-number v-model="orderForm.field_no" :min="1" />
      </el-form-item>
      <el-form-item label="日期">
        <el-select v-model="orderForm.date" placeholder="日期">
          <el-option
            v-for="date in dates"
            :key="date.value"
            :label="date.label"
            :value="date.value"
            :disabled="date.disabled"
          >
          </el-option> </el-select
      ></el-form-item>
      <el-form-item label="开始时间">
        <el-time-select
          v-model="beginTime"
          start="08:00"
          step="00:30"
          end="21:00"
          placeholder="开始时间"
        />
      </el-form-item>
      <el-form-item label="结束时间">
        <el-time-select
          v-model="finishTime"
          start="08:00"
          step="00:30"
          end="21:00"
          placeholder="结束时间"
        />
      </el-form-item>
    </el-form>
    <p>订单截图</p>
    <el-upload
      class="orderShot"
      action="#"
      accept="image/*"
      limit="1"
      :http-request="UploadOrderShot"
      list-type="picture"
      v-model:file-list="orderList"
    >
      <el-icon v-if="orderList.length === 0"><Plus /></el-icon>
    </el-upload>
    <p>收款码截图</p>
    <el-upload
      class="paymentShot"
      action="#"
      accept="image/*"
      limit="1"
      :http-request="UploadPaymentShot"
      list-type="picture"
      v-model:file-list="paymentList"
    >
      <el-icon v-if="paymentList.length === 0"><Plus /></el-icon>
    </el-upload>
    <el-button @click="HandInOrder">提交</el-button>
    <el-button @click="BackUp">退出</el-button>
  </el-container>
</template>
<script>
import { Plus } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";
import { reactive } from "@vue/reactivity";
import router from "../router/index";
import store from "../store/index";
import { FormatDate } from "../util/DateFormatter";
import { UpdateOrderShot, UpdatePaymentShot } from "../api/ScreenShotActions";
import { HandInOrder } from "../api/OrderActions";

export default {
  name: "OrderHandIn",
  components: {
    Plus,
  },
  data() {
    return {
      orderForm: reactive({
        gym: "",
        field_no: 1,
        date: "",
      }),
      beginTime: "",
      finishTime: "",
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
        },
        {
          value: Date.now() + 86400000,
          label: "明天",
          disabled: true,
        },
      ],
      orderList: [],
      paymentList: [],
      orderShot: null,
      paymentShot: null,
    };
  },
  computed: {
    userLogged() {
      return store.state.user;
    },
  },
  methods: {
    UploadOrderShot: async function (param) {
      var formData = new FormData();
      formData.append("file", param.file);
      UpdateOrderShot(formData).then((res) => {
        this.orderShot = res.data.data;
      });
    },
    UploadPaymentShot: async function (param) {
      var formData = new FormData();
      formData.append("file", param.file);
      UpdatePaymentShot(formData).then((res) => {
        this.paymentShot = res.data.data;
      });
    },
    HandInOrder: function () {
      if (this.userLogged === null) {
        ElMessage({ message: "用户未登录", type: "warning" });
      } else {
        var order = new Object();
        var dateTemp = new Date(this.orderForm.date);
        var date = FormatDate(dateTemp);
        order.begin_time = date + this.beginTime + ":00";
        order.finish_time = date + this.finishTime + ":00";
        order.field_no = this.orderForm.field_no;
        order.payment = this.paymentShot;
        order.fieldOrder = this.orderShot;
        order.seller = this.userLogged.user;
        order.gym = this.orderForm.gym;
        HandInOrder(order).then((res) => {
          if (res.data.msg === "success") {
            ElMessage({
              message: "发布成功",
              type: "success",
            });
            router.push({ name: "home" });
          } else {
            ElMessage({
              message: "出现错误",
              type: "warning",
            });
          }
        });
      }
    },
    BackUp: function () {
      router.push({ name: "home" });
    },
  },
  mounted() {
    var time = new Date();
    var hour = time.getHours();
    if (hour >= 18) {
      this.dates[1].disabled = false;
    }
  },
};
</script>
<style scoped>
.el-container {
  display: block;
  height: 100%;
  padding: 1rem;
  padding-top: 1.5rem;
  font-family: "mainFont";
  padding-bottom: 0%;
}
p {
  text-align: left;
}
.orderShot {
  height: 7rem;
  text-align: left;
}
.paymentShot {
  height: 7rem;
  text-align: left;
}
.el-icon {
  height: 6rem;
  width: 5rem;
  font-size: 2rem;
  background-color: rgb(217, 212, 212);
  margin-left: 1rem;
}
.el-button {
  margin: 1.2rem;
}
</style>
