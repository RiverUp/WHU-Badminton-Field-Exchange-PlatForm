<template>
  <el-container>
    <el-header
      ><el-button @click="Refresh"
        ><el-icon><RefreshRight /></el-icon></el-button
    ></el-header>
    <el-main>
      <div
        class="messages"
        v-for="(message, index) in messages"
        :key="message.id"
      >
        <el-badge value="new" :hidden="message.read"
          ><el-card @click="ReadMessage(index)"
            ><p
              v-for="(info, index) in message.content.split('///')"
              :key="index"
            >
              {{ info }}
            </p></el-card
          ></el-badge
        >
      </div>
    </el-main>
    <page-footer @Refresh="Refresh" />
  </el-container>
</template>

<script>
import { RefreshRight } from "@element-plus/icons-vue";
import PageFooter from "./PageFooter.vue";
import store from "../store/index";
import router from "../router/index";
import { GetMessages, UpdateMessageRead } from "../api/MessageActions";

export default {
  name: "MessageShow",
  components: {
    RefreshRight,
    PageFooter,
  },
  data() {
    return {
      messages: [],
    };
  },
  computed: {
    userLogged() {
      return store.state.user;
    },
  },
  methods: {
    Refresh: function () {
      GetMessages(this.userLogged.user.id).then((res) => {
        this.messages = res.data.data;
      });
    },
    ReadMessage: function (index) {
      var message = this.messages[index];
      store.commit("StoreMessage", message);
      UpdateMessageRead(message.id);
      router.push({
        name: "certainMessage",
        params: {
          user_id: message.receiver.id,
          message_id: message.id,
        },
      });
    },
  },
  mounted() {
    GetMessages(this.userLogged.user.id).then((res) => {
      this.messages = res.data.data;
    });
  },
};
</script>
<style scoped>
.el-container {
  height: 100%;
  padding: 0%;
}
.el-header {
  text-align: right;
  padding: 0;
  padding-top: 2%;
}
.el-header .el-button {
  border: none;
  font-size: 1.5em;
}
.el-main {
  height: 83%;
  padding: 0%;
}
.el-card {
  border-radius: 2rem;
  border-color: rgb(182, 151, 13);
  margin: 0;
}
.el-badge {
  width: 80%;
  margin-top: 1rem;
}
</style>
