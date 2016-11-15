/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group.model;

import Group.dao.ReportJDBC;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author jasmine
 */
public class Report {

    private Date date;
    private List<String> studentName;
    ReportJDBC database = new ReportJDBC();

    public void showPerformance(int i) throws Exception {
        switch (i) {
            case 1:
                showTestTakenByTime();
                break;
            case 2:
                showAverageByTime();
                break;
            case 3:
                int j = 1;
                
                getPassing(); //这里要写前端触发
                getFailing(); //同样前端触发设计
                break;
            case 4:
                showPerformanceByLevel();
                break;
        }
    }


    
    public Map getPassing() throws Exception {
        int i = 0;
        List<String> student = database.getStudentList();
        Map<String, Integer> passing = new HashMap<String, Integer>();
        for (String s : student) {
            passing.put(s, database.getPassingCount(s, i));
        }
        return passing;
    }

    public Map getFailing() throws Exception {
        int i = 0;
        List<String> student = database.getStudentList();
        Map<String, Integer> failing = new HashMap<String, Integer>();
        for (String s : student) {
            failing.put(s, database.getFailingCount(s, i));
        }
        return failing;
    }

    
    
    public int[] showTestTakenByTime() throws Exception {
        int[] count = new int[3];
        count[0] = database.getCountByTime(1);
        count[1] = database.getCountByTime(2);
        count[2] = database.getCountByTime(3);
        return count;
    }

    public double[] showAverageByTime() throws Exception {
        double[] score = new double[4];
        score[0] = database.getAverageByTime(1);
        score[1] = database.getAverageByTime(2);
        score[2] = database.getAverageByTime(3);
        return score;
    }

    public double[] showPerformanceByLevel() throws Exception {
        double[] performance = new double[4];
        performance[0] = database.getPerformanceByLevel(1);
     //   performance[1] = database.getPerformanceByLevel(2);
     //   performance[2] = database.getPerformanceByLevel(3);
     //   performance[3] = database.getPerformanceByLevel(4);
        return performance;
    }
    
    
    public void printPDF(){
        
    }

}
