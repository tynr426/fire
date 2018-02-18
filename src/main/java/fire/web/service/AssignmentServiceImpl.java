package fire.web.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fire.common.entity.Assignment;
import fire.common.entity.AssignmentResult;
import fire.common.entity.AuthBind;
import fire.common.entity.CheckDevice;
import fire.common.entity.CheckDeviceResult;
import fire.common.entity.DeviceResult;
import fire.common.entity.RepairrecordResult;
import fire.common.entity.StatusStatistics;
import fire.common.entity.WeChatAccount;
import fire.sdk.utils.WechatTemplateMsg;
import fire.web.dao.AssignmentDAO;
import fire.web.dao.AuthBindDAO;
import fire.web.dao.CheckDeviceDAO;
import fire.web.dao.DeviceDAO;
import fire.web.dao.RepairrecordDAO;
import fire.web.dao.WeChatAccountDAO;
import fire.web.utils.Company;
import fire.web.utils.Constants;
import fire.web.utils.PageInfo;
@Service("assignmentService")
public class AssignmentServiceImpl implements AssignmentService{
	@Resource
	private AssignmentDAO assignmentDAO;
	@Resource
	private CheckDeviceDAO cdDAO;
	@Resource
	private DeviceDAO deviceDAO;
	
	@Resource
	private WeChatAccountDAO weChatAccountDAO;
	
	@Resource
	private AuthBindDAO authBindDAO;
	@Resource
	private RepairrecordDAO repairrecordDAO;
	@Transactional
	public int save(Assignment entity){
		    CheckDeviceResult cd = cdDAO.getCD(entity.getCheckId());
			entity.setAddTime(new Date());
			entity.setStatus(1);
			cd.setStatus(2);
			cdDAO.updateCD(cd);
			
			WeChatAccount wca= weChatAccountDAO.getWeChatAccount();
			CheckDeviceResult cdr =cdDAO.getCD(entity.getCheckId());
			DeviceResult device = deviceDAO.findById(cd.getDeviceId());
			List<AuthBind> listOpenId=authBindDAO.findOpenIds(entity.getToManagerId());
			WechatTemplateMsg bll=new WechatTemplateMsg(wca.getAppId(), wca.getSecret(), Constants.H5Domain+"company/assigment/toAssigment.do");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			for(AuthBind auth:listOpenId){
				
				bll.Send(auth.getOpenId(), auth.getNickName()+"，您好。您有新的维修工单需要处理，请查收。", "新的维修工单", device.getPositionDetail(), cdr.getSeverityLevelDes(), cdr.getDescription(), sdf.format(cdr.getAddTime()), entity.getRemark());
			}
			return assignmentDAO.addAssignment(entity);
	}
	public Assignment getAssignment(int id) {
		Assignment assignment = assignmentDAO.findById(id);
		if(assignment==null){
			throw new NameException("Id不存在");
		}
		return assignment;
	}
	public AssignmentResult getAssignmentByCheckId(int checkId) {
		AssignmentResult assignment = assignmentDAO.getAssignmentByCheckId(checkId);
		if(assignment==null){
			throw new NameException("checkId不存在");
		}
	
		return assignment;
	}

	public int deleteAssignment(int id) {
		Assignment assignment = assignmentDAO.findById(id);
		if(assignment==null){
			throw new NameException("Id不存在");
		}
		return assignmentDAO.delete(id);
	}

	public PageInfo<AssignmentResult> getAssignmentPage(int companyId, int index, int size) {
		PageInfo<AssignmentResult> pi = new PageInfo<AssignmentResult>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(assignmentDAO.findAssignmentCount(companyId));
		pi.setList(assignmentDAO.findByLimit(companyId,pi.getBegin(), size));
		return pi;
	}

	public int updateStatus(Integer id, Integer status) {
		Assignment assignment = assignmentDAO.findById(id);
		if(assignment==null){
			throw new NameException("id不存在");
		}
		assignment.setStatus(status);
		int n = assignmentDAO.updateStatus(assignment);
		return n;
	}
	public PageInfo<AssignmentResult> getAssignmentPageByManager(int companyId,int managerId, int index, int size,Integer status,String keyword) {
		PageInfo<AssignmentResult> pi = new PageInfo<AssignmentResult>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(assignmentDAO.findByManagerCount(companyId,managerId,status, keyword));
		pi.setList(assignmentDAO.findByManagerLimit(companyId,managerId,pi.getBegin(), size,status, keyword));
		return pi;
	}
	public List<StatusStatistics> getStatistics(int managerId, Integer status, String keyword) {
		List<StatusStatistics> list = assignmentDAO.getStatistics(managerId, status, keyword);
		if(list==null){
			list=new ArrayList<StatusStatistics>();
		}
		int count = 0;
		for(StatusStatistics entity:list){
			count+=entity.getCount();
		}
		StatusStatistics entity = new StatusStatistics();
		entity.setCount(count);
		entity.setStatus(999);
		list.add(entity);
		return list;
	}

}
