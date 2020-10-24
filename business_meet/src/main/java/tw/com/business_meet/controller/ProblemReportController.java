package tw.com.business_meet.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import tw.com.business_meet.bean.ProblemReportBean;
import tw.com.business_meet.bean.UserCustomizationBean;
import tw.com.business_meet.bean.UserInformationBean;
import tw.com.business_meet.service.ProblemReportService;
import tw.com.business_meet.service.UserInformationService;

@Controller
@RequestMapping("/problemreport")
public class ProblemReportController {
	@Autowired
	private ProblemReportService  problemReportService;
	@Autowired
	private UserInformationService  userInformationService;
	
	@GetMapping(path = "/add")
	public ModelAndView addPage() {
		return new ModelAndView("problem_report/add");
	}
	
	@ResponseBody
	@PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public String add(ProblemReportBean problemReportBean) throws Exception{
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try{
        	ProblemReportBean problemreport = problemReportService.add(problemReportBean);

            result.put("result",true);
            result.putPOJO("data",problemreport);
        }catch(Exception e){
            e.printStackTrace();
            result.put("result",false);
            result.putObject("data");
        }
        return o.writeValueAsString(result);
    }
//    @PreAuthorize("hasAnyRole('admin')")
	@GetMapping(path = "")
	public ModelAndView indexPage(ProblemReportBean condition) {
		ModelAndView index = new ModelAndView("problem_report/index");
		index.addObject("problemReportBeanList", problemReportService.search(condition));
		return index;
	}

	@ResponseBody
	@GetMapping(path = "/search", produces = "application/json;charset=UTF-8")
	public String search(ProblemReportBean condition) throws Exception {
		ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try{
        	List<ProblemReportBean> problemReportBeanList = problemReportService.search(condition);
            result.put("result",true);
        	ArrayNode data = result.putArray("data");
        	for (ProblemReportBean problemReportBean : problemReportBeanList) {
        		
        		ObjectNode problemReportNode = data.addObject();
        		problemReportNode.put("id", problemReportBean.getProblemReportNo());
        		problemReportNode.put("content", problemReportBean.getContent());
        		problemReportNode.put("userId", problemReportBean.getUserId());
        		//if(problemReportBean.getStatus()==1) {problemReportNode.put("status", "未處理");}
        		//if(problemReportBean.getStatus()==2) {problemReportNode.put("status", "處理中");}
        		//if(problemReportBean.getStatus()==3) {problemReportNode.put("status", "已處理");}
        		problemReportNode.put("status", problemReportBean.getStatus());
        		problemReportNode.put("startDate", problemReportBean.getStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        		problemReportNode.put("endDate", problemReportBean.getEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        	}
        }catch(Exception e){
            e.printStackTrace();
            result.put("result",false);
            result.putObject("data");
        }
        return o.writeValueAsString(result);
	}
	
	@ResponseBody
	@GetMapping(path = "/filter")
	public String getFilterData() throws Exception {
		ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try{
        	List<UserInformationBean> userInformationBeanList = userInformationService.searchAll();
        	
            result.put("result",true);
        	ArrayNode data = result.putArray("data");
        	for (UserInformationBean userInformationBean : userInformationBeanList) {
        		ObjectNode userInformationNode = data.addObject();
        		userInformationNode.put("userName", userInformationBean.getName());
        	}
        }catch(Exception e){
            e.printStackTrace();
            result.put("result",false);
            result.putObject("data");
        }
        return o.writeValueAsString(result);
	}
	
	@ResponseBody
	@PostMapping(path = "/{problemReportNo}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer problemReportNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
        	problemReportService.delete(problemReportNo);
            result.put("result", true);

        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }
	
	@GetMapping(path = "/update")
	public ModelAndView updatePage() {
		return new ModelAndView("problem_report/update");
	}
	
	@ResponseBody
	@PostMapping(path = "/update", produces = "application/json;charset=UTF-8")
    public String update(ProblemReportBean problemReportBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
        	ProblemReportBean prb = problemReportService.update(problemReportBean);
            result.put("result", true);
            result.putPOJO("data", prb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }
}
