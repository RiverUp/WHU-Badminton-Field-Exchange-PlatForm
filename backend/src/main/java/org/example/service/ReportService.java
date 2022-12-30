package org.example.service;

import org.example.dao.ReportDao;
import org.example.dao.UserDao;
import org.example.entities.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportDao reportDao;
    @Autowired
    UserDao userDao;

    public JsonResult<Report> reportSomeone(Report report) {
        JsonResult<Report> result = new JsonResult<>(null, "400", "");
        try {
            reportDao.insertReport(report);
            result.setMsg("success");
            result.setCode("200");
            result.setData(report);
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }

    public JsonResult<Report> processReport(int report_id, int result) {
        JsonResult<Report> result1 = new JsonResult<>(null, "400", "");
        try {
            reportDao.updateResult(result, report_id);
            Report report = reportDao.selectReportById(report_id);
            if (result == 1) {
                userDao.updateCreditworthiness(report.reported.id);
            }
            result1.setMsg("success");
            result1.setCode("200");
            result1.setData(report);
        } catch (Exception exception) {
            System.out.println(exception);
            result1.setMsg(exception.toString());
        } finally {
            return result1;
        }
    }

    public JsonResult<List> getReportsByUser(int user_id) {
        JsonResult<List> result = new JsonResult<>(null, "400", "");
        try {
            List<Report> list = reportDao.selectReportByUserId(user_id);
            result.setMsg("success");
            result.setCode("200");
            result.setData(list);
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }

    }

    public JsonResult<List> getReports() {
        JsonResult<List> result = new JsonResult<>(null, "400", "");
        try {
            List<Report> list = reportDao.selectAllReports();
            result.setMsg("success");
            result.setCode("200");
            result.setData(list);
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }

}
