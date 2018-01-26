package fire.web.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;

import fire.common.entity.AssignmentSummary;
import fire.common.entity.DeviceNumSummary;
import fire.common.entity.ReportData;
import fire.common.entity.ReportSummary;
import fire.web.dao.ReportDAO;

@Service("reportSummaryService")
public class ReportSummaryServiceImpl implements ReportSummaryService{
	@Resource
	private ReportDAO reportDAO;
	public List<DeviceNumSummary> getDeviceNumSummaryList(int companyId, int deviceTypeId, Date startTime,Date endTime,
			Integer unitproperties,Integer buildingtype,Boolean isimport) {
		List<DeviceNumSummary> list = reportDAO.getDeviceNumSummaryList(companyId, deviceTypeId, startTime, endTime, unitproperties, buildingtype, isimport);
		return list;
	}
	public ReportSummary getAssignmentSummaryList(int companyId,int deviceTypeId,String year,
			Integer unitproperties,Integer buildingtype,Boolean isimport){
		 Calendar cale =  Calendar.getInstance(); 
		 if(year.equals("")){
	         year = String.valueOf(cale.get(Calendar.YEAR));  
	        
		 }	
		List<AssignmentSummary> list= reportDAO.getAssignmentSummaryList(companyId, deviceTypeId, year, unitproperties, buildingtype, isimport);
	
		ReportSummary summary=new ReportSummary();
		ReportData reportData=new ReportData();
		ReportData reportData1=new ReportData();
		for(int i=1;i<13;i++){
			summary.getLabels().add(i+"月");
			AssignmentSummary entity=getEntity(list,i);
			reportData.getData().add(entity.getCompleteCount());
			reportData1.getData().add(entity.getAssignmentCount());
		}
		reportData.setLabel("完成数量");
	
		reportData1.setLabel("任务数量");
		
		summary.getDataSet().add(reportData);
		summary.getDataSet().add(reportData1);
		return summary;
	}
	private AssignmentSummary getEntity(List<AssignmentSummary> list,int month){
		for(AssignmentSummary entity :list){
			if(entity.getMonth()==month){
				return entity;
			}
		}
		return new AssignmentSummary();
	}
	public  String join(String join,List<Integer> strAry){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<strAry.size();i++){
             if(i==(strAry.size()-1)){
                 sb.append(strAry.get(i));
             }else{
                 sb.append(strAry.get(i)).append(join);
             }
        }
        
        return new String(sb);
    }
}
