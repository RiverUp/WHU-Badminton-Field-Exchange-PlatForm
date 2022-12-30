import axios from "axios";
import { config } from "./AxiosConfig";

const reportAction = axios.create({
  baseURL: config.baseURL + "/report",
  timeout: config.timeout,
});

const HandInReport = async function (report) {
  try {
    const res = await reportAction.post("", report);
    return res;
  } catch (err) {
    console.log(err);
  }
};
const GetReports = async function () {
  try {
    const res = await reportAction.get("");
    return res;
  } catch (err) {
    console.log(err);
  }
};
const ProcessReport = async function (report_id, result) {
  try {
    const res = await reportAction.get("/process", {
      params: { report_id: report_id, result: result },
    });
    return res;
  } catch (err) {
    console.log(err);
  }
};

export { HandInReport, GetReports, ProcessReport };
