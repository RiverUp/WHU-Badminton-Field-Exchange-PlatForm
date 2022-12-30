<template>
  <el-container>
    <p>举报陈述：</p>
    <el-input type="textarea" v-model="evidence" :rows="15" />
    <p>举报依据：</p>
    <el-upload
      class="reportShot"
      action="#"
      accept="image/*"
      limit="1"
      :http-request="UploadReportShot"
      list-type="picture"
      v-model:file-list="reportList"
    >
      <el-icon v-if="reportList.length === 0"><Plus /></el-icon>
    </el-upload>
    <el-button @click="HandInReport">提交</el-button>
    <el-button @click="BackUp">退出</el-button>
  </el-container>
</template>
<script>
import { Plus } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";
import { UpdateEvidenceShot } from "../api/ScreenShotActions";
import { HandInReport } from "../api/ReportAcitions";
import router from "../router/index";
import store from "../store/index";

export default {
  name: "ReportHandIn",
  components: {
    Plus,
  },
  data() {
    return {
      reportList: [],
      evidence: "",
      evidenceShot: null,
    };
  },
  computed: {
    userLogged() {
      return store.state.user;
    },
    userReported() {
      return store.state.userReported;
    },
    orderReported() {
      return store.state.orderReported;
    },
  },
  methods: {
    UploadReportShot: async function (param) {
      var formData = new FormData();
      formData.append("file", param.file);
      UpdateEvidenceShot(formData).then((res) => {
        this.evidenceShot = res.data.data;
      });
    },
    HandInReport: function () {
      var report = new Object();
      report.reporter = this.userLogged.user;
      report.reported = this.userReported;
      report.evidence = this.evidence;
      report.evidencePicture = this.evidenceShot;
      report.order = this.orderReported;
      HandInReport(report).then((res) => {
        if (res.data.msg === "success") {
          ElMessage({
            message: "举报提交成功",
            type: "success",
          });
          router.push({
            name: "user",
            params: { id: this.userLogged.user.id },
          });
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
.reportShot {
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
