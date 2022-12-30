<template>
  <el-container>
    <el-tabs type="border-card">
      <el-tab-pane label="举报处理">
        <el-table :data="reports" style="width:100% height:90%">
          <el-table-column prop="id" label="编号" />
          <el-table-column prop="reporter.name" label="举报人" />
          <el-table-column prop="reported.name" label="被举报人" />
          <el-table-column label="查看">
            <template #default="scope">
              <el-button type="primary" plain @click="CheckReport(scope.$index)"
                >查看</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="用户封禁">
        <el-table :data="users" style="width:100% height:90%">
          <el-table-column prop="id" label="编号" />
          <el-table-column prop="name" label="姓名" />
          <el-table-column prop="creditworthiness" label="违规次数" />
          <el-table-column label="封禁">
            <template #default="scope">
              <el-button type="danger" plain @click="BlockUser(scope.$index)"
                >封禁</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="个人信息">
        <p>用户名：{{ this.userLogged.user.name }}</p>
        <p>密码： {{ this.userLogged.user.passwords }}</p>
        <el-button type="danger" plain @click="Logout">退出登录</el-button>
      </el-tab-pane>
    </el-tabs>
  </el-container>
</template>
<script>
import store from "../store/index";
import router from "../router/index";
import { GetReports } from "../api/ReportAcitions";
import { GetUsers, BlockUser } from "../api/UserActions";

export default {
  name: "AdministratorPage",
  data() {
    return {
      reports: [],
      users: [],
    };
  },
  computed: {
    userLogged() {
      return store.state.user;
    },
  },
  methods: {
    Logout: function () {
      store.commit("StoreUser", null);
      router.push({ name: "home" });
    },
    BlockUser: function (index) {
      var user = this.users[index];
      BlockUser(user.id);
    },
    CheckReport: function (index) {
      var report = this.reports[index];
      store.commit("StoreReport", report);
      router.push({
        name: "certainReport",
        params: { report_id: report.id },
      });
    },
  },
  mounted() {
    GetReports().then((res) => {
      this.reports = res.data.data;
    });
    GetUsers().then((res) => {
      this.users = res.data.data;
    });
  },
};
</script>
<style scoped>
.el-container {
  height: 100%;
  padding: 0%;
  width: 100%;
  display: block;
}
.el-main {
  height: 86%;
  padding: 0%;
}
</style>
