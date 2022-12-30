package org.example.controller;

import org.example.entities.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.example.service.JsonResult;
import org.example.service.ReportService;

import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping(path = "report")
public class ReportController {
    @Autowired
    ReportService reportService;
    @RequestMapping(path = "",method = RequestMethod.POST)
    public JsonResult<Report> reportSomeone(@RequestBody Report report){
        return reportService.reportSomeone(report);
    }
    @RequestMapping(path = "/process",method = RequestMethod.GET)
    public JsonResult<Report> processReport(int report_id,int result){
        return reportService.processReport(report_id,result);
    }
    @RequestMapping(path = "/user",method = RequestMethod.GET)
    public JsonResult<List> getReportsByUser(int user_id){
        return reportService.getReportsByUser(user_id);
    }
    @RequestMapping(path = "",method = RequestMethod.GET)
    public JsonResult<List> getReports(){
        return reportService.getReports();
    }
}
