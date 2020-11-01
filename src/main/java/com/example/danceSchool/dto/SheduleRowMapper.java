package com.example.danceSchool.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SheduleRowMapper implements RowMapper<SheduleReport> {

    @Override
    public SheduleReport mapRow(ResultSet resultSet, int i) throws SQLException {
        SheduleReport sheduleReport = new SheduleReport();
        sheduleReport.setId(resultSet.getLong("id"));
        sheduleReport.setBegin(resultSet.getTimestamp("begin"));
        sheduleReport.setEnd(resultSet.getTimestamp("end"));
        sheduleReport.setLength(resultSet.getInt("length"));
        sheduleReport.setGroupLevel(resultSet.getString("group_level"));
        sheduleReport.setTeacherId(resultSet.getLong("teacher_id"));
        String teacherSecondName = resultSet.getString("second_name");
        String teacherFirstName = resultSet.getString("first_name");
        String teacherLastName = resultSet.getString("last_name");
        sheduleReport.setTeacher(teacherSecondName + " " + teacherFirstName + " " + teacherLastName);
        sheduleReport.setDance(resultSet.getString("name"));
        sheduleReport.setStatus(resultSet.getString("status"));
        return sheduleReport;
    }
}