<template>
  <el-container>
    <p>支付方式</p>
    <el-image :src="this.url" style="width: 100%; height: 50%" :fit="fit" />
    <p>附带对方用户姓名作为备注的付款记录截图</p>
    <el-upload
      class="payedShot"
      action="#"
      accept="image/*"
      limit="1"
      :http-request="UploadPayedShot"
      list-type="picture"
      v-model:file-list="payedList"
    >
      <el-icon v-if="payedList.length === 0"><Plus /></el-icon>
    </el-upload>
    <el-button @click="Exchange">提交</el-button>
    <el-button @click="BackUp">退出</el-button>
  </el-container>
</template>
<script>
import store from "../store/index";
import router from "../router/index";
import { Plus } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";
import { UpdatePayedShot } from "../api/ScreenShotActions";
import { GetOrderShot } from "../api/OrderActions";

export default {
  name: "OrderExchange",
  components: {
    Plus,
  },
  props: {
    exchanged: {
      type: Object,
    },
  },
  data() {
    return {
      payedList: [],
      fit: "contain",
      payedShot: null,
    };
  },
  computed: {
    order() {
      return store.state.order;
    },
    url() {
      var relatedUrl = this.order.payment.url.split(":")[1];
      return "http://localhost:8900" + relatedUrl;
    },
  },
  methods: {
    UploadPayedShot: async function (param) {
      var formData = new FormData();
      formData.append("file", param.file);
      UpdatePayedShot(formData).then((res) => {
        this.payedShot = res.data.data;
      });
    },
    Exchange: function () {
      // eslint-disable-next-line vue/no-mutating-props
      this.exchanged.state = true;
      //关联payedShot与order，获取orderShot，调用getOrder_Shot一个接口就可以了
      GetOrderShot(this.payedShot.id, this.order.id).then((res) => {
        if (res.data.msg === "success") {
          ElMessage({
            message: "交换成功,订单截图请在消息中查看",
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
    },
    BackUp: function () {
      router.push({ name: "home" });
    },
  },
  mounted() {
    // eslint-disable-next-line vue/no-mutating-props
    this.exchanged.order_id = this.order.id;
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
.el-icon {
  height: 6rem;
  width: 5rem;
  font-size: 2rem;
  background-color: rgb(217, 212, 212);
  margin-left: 1rem;
}
.payedShot {
  height: 7rem;
  text-align: left;
}
.el-button {
  margin: 1.2rem;
}
</style>
