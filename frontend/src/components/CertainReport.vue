<template>
  <el-container>
    <el-page-header @back="BackUp" />
    <el-main>
      <p>举报人：{{ report.reporter.name }}</p>
      <p>被举报人： {{ report.reported.name }}</p>
      <p>举报陈述：</p>
      <p class="statement">{{ report.evidence }}</p>
      <p>举报依据：</p>
      <el-image
        :src="this.urlOfEvidence"
        style="width: 100%; height: 90%"
        :fit="fit"
      />
      <p>订单截图：</p>
      <el-image
        :src="this.urlOfOrder"
        style="width: 100%; height: 90%"
        :fit="fit"
      />
      <p>收款截图：</p>
      <el-image
        :src="this.urlOfPayment"
        style="width: 100%; height: 50%"
        :fit="fit"
      />
      <p>付款截图</p>
      <el-image
        :src="this.urlOfPayed"
        style="width: 100%; height: 50%"
        :fit="fit"
      />
      <el-button @click="JudgeEffect">有效</el-button>
      <el-button @click="JudgeIneffect">无效</el-button>
    </el-main>
  </el-container>
</template>
<script>
import store from "../store/index";
import router from "../router/index";
import { ProcessReport } from "../api/ReportAcitions";

export default {
  name: "CertainReport",
  data() {
    return {
      fit: "contain",
    };
  },
  computed: {
    report() {
      return store.state.report;
    },
    urlOfEvidence() {
      var relatedUrl = this.report.evidencePicture.url.split(":")[1];
      return "http://localhost:8900" + relatedUrl;
    },
    urlOfOrder() {
      var relatedUrl = this.report.order.fieldOrder.url.split(":")[1];
      return "http://localhost:8900" + relatedUrl;
    },
    urlOfPayment() {
      var relatedUrl = this.report.order.payment.url.split(":")[1];
      return "http://localhost:8900" + relatedUrl;
    },
    urlOfPayed() {
      var relatedUrl = this.report.order.payed.url.split(":")[1];
      return "http://localhost:8900" + relatedUrl;
    },
  },
  methods: {
    BackUp: function () {
      router.push({ name: "administrator" });
    },
    JudgeEffect: function () {
      ProcessReport(this.report.id, 1).then(() => {
        this.BackUp();
      });
    },
    JudgeIneffect: function () {
      ProcessReport(this.report.id, 2).then(() => {
        this.BackUp();
      });
    },
  },
};
</script>
<style scoped>
.el-container {
  height: 100%;
  display: block;
  padding: 0%;
}
.el-page-header {
  padding: 2%;
  font-family: "mainFont";
}
.el-main {
  height: 90%;
  padding: 0%;
}
p {
  text-align: left;
  font-family: mainFont;
}
.statement {
  color: red;
}
.el-button {
  margin-top: 2rem;
}
</style>
