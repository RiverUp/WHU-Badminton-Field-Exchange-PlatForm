import axios from "axios";
import { config } from "./AxiosConfig";

const screenShotAction = axios.create({
  baseURL: config.baseURL + "/screenshot",
  timeout: config.timeout,
  headers: { "Content-Type": "multipart/form-data" },
});

const UpdateOrderShot = async function (orderShot) {
  try {
    const res = await screenShotAction.post("/order_shot", orderShot);
    return res;
  } catch (err) {
    console.log(err);
  }
};
const UpdatePaymentShot = async function (paymentShot) {
  try {
    const res = await screenShotAction.post("/payment_shot", paymentShot);
    return res;
  } catch (err) {
    console.log(err);
  }
};
const UpdatePayedShot = async function (paymentShot) {
  try {
    const res = await screenShotAction.post("/payed_shot", paymentShot);
    return res;
  } catch (err) {
    console.log(err);
  }
};
const GetScreenShot = async function (screenshotId) {
  try {
    const res = await screenShotAction.get("", {
      params: {
        screem_shot_id: screenshotId,
      },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};
const UpdateEvidenceShot = async function (evidenceShot) {
  try {
    const res = screenShotAction.post("/evidence_picture", evidenceShot);
    return res;
  } catch (err) {
    console.log(err);
  }
};

export {
  UpdateOrderShot,
  UpdatePaymentShot,
  GetScreenShot,
  UpdatePayedShot,
  UpdateEvidenceShot,
};
