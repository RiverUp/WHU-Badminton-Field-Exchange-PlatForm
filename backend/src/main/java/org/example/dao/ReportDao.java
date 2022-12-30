package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.entities.Report;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ReportDao {
    public int insertReport(Report report);

    public void updateResult(@Param("state") int state, @Param("id") int report_id);

    public List<Report> selectAllReports();

    public List<Report> selectReportByUserId(int user_id);
    public Report selectReportById(int id);
}
