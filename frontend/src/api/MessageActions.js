import axios from "axios";
import { config } from "./AxiosConfig";

const messageAction = axios.create({
  baseURL: config.baseURL + "/message",
  timeout: config.timeout,
});

const GetMessages = async function (user_id) {
  try {
    const res = await messageAction.get("", {
      params: {
        user_id: user_id,
      },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};
const UpdateMessageRead = async function (message_id) {
  try {
    const res = await messageAction.get("/read", {
      params: {
        message_id: message_id,
      },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};

export { GetMessages, UpdateMessageRead };
