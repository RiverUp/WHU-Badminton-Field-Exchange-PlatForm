import axios from "axios";
import { config } from "./AxiosConfig";

const userAction = axios.create({
  baseURL: config.baseURL + "/user",
  timeout: config.timeout,
});

const Login = async function (userInfo) {
  try {
    const res = await userAction.post("/login", userInfo);
    return res;
  } catch (err) {
    console.log(err);
  }
};
const Signup = async function (userInfo) {
  try {
    const res = await userAction.post("/sigup", userInfo);
    return res;
  } catch (err) {
    console.log(err);
  }
};
const GetUsers = async function () {
  try {
    const res = await userAction.get("");
    return res;
  } catch (err) {
    console.log(err);
  }
};
const BlockUser = async function (user_id) {
  try {
    const res = await userAction.get("/block", {
      params: {
        user_id: user_id,
      },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};

export { Login, Signup, GetUsers, BlockUser };
