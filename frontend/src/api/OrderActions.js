import axios from "axios";
import { config } from "./AxiosConfig";

const orderAction = axios.create({
  baseURL: config.baseURL + "/order",
  timeout: config.timeout,
});

const GetAvailableOrder = async function () {
  try {
    const res = await orderAction.get("/availableOrders");
    return res;
  } catch (err) {
    console.log(err);
  }
};

const GetSatisfiedOrder = async function (begin_time, gym) {
  try {
    const res = await orderAction.get("/satisfiedOrders", {
      params: {
        begin_time: begin_time,
        gym: gym,
      },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};
const HandInOrder = async function (order) {
  try {
    const res = await orderAction.post("/field_to_exchange", order);
    return res;
  } catch (err) {
    console.log(err);
  }
};
const GetLocked = async function (order_id) {
  try {
    const res = await orderAction.get("/locked", {
      params: {
        order_id: order_id,
      },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};
const UpdatePurchaser = async function (purchaser_id, order_id) {
  try {
    const res = await orderAction.get("/purchaser", {
      params: { purchaser_id: purchaser_id, order_id: order_id },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};
const UpdatePurchaserNull = async function (order_id) {
  try {
    const res = await orderAction.get("/purchaserNull", {
      params: { order_id: order_id },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};
const GetOrderShot = async function (payed_id, order_id) {
  try {
    const res = await orderAction.get("/order_shot", {
      params: {
        payed_id: payed_id,
        order_id: order_id,
      },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};
const WithdrawOrder = async function (order_id) {
  try {
    const res = await orderAction.delete("", {
      params: { order_id: order_id },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};
const GetSellerOrder = async function (user_id) {
  try {
    const res = await orderAction.get("/historySellerOrders", {
      params: {
        user_id: user_id,
      },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};
const GetPurchaserOrder = async function (user_id) {
  try {
    const res = await orderAction.get("/historyPurchaserOrders", {
      params: {
        user_id: user_id,
      },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};

export {
  GetAvailableOrder,
  GetSatisfiedOrder,
  HandInOrder,
  GetLocked,
  UpdatePurchaser,
  GetOrderShot,
  UpdatePurchaserNull,
  WithdrawOrder,
  GetSellerOrder,
  GetPurchaserOrder,
};
