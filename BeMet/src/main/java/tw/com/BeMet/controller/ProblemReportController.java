package tw.com.BeMet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tw.com.BeMet.bean.ProblemReportBean;
import tw.com.BeMet.bean.UserInformationBean;
import tw.com.BeMet.service.CodeTableService;
import tw.com.BeMet.service.ProblemReportService;
import tw.com.BeMet.service.UserInformationService;
import tw.com.BeMet.vo.UserInformation;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/problemreport")
public class ProblemReportController {
    @Autowired
    private ProblemReportService problemReportService;
    @Autowired
    private UserInformationService userInformationService;
    @Autowired
    private CodeTableService codeTableService;

    @GetMapping(path = "/add")
    public ModelAndView addPage() {
        return new ModelAndView("problem_report/userReport");
    }

    @ResponseBody
    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public String add(ProblemReportBean problemReportBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            ProblemReportBean problemreport = problemReportService.add(problemReportBean);

            result.put("result", true);
            result.put("message", "新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
            result.put("message", "新增失敗");
        }
        return o.writeValueAsString(result);
    }

    @GetMapping(path = "/add/success")
    public ModelAndView addSuccessPage() throws Exception {
        ModelAndView modelAndView = new ModelAndView("problem_report/userSuccess");
        return modelAndView;
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping(path = "")
    public ModelAndView indexPage(ProblemReportBean problemReportBean) throws Exception {
        ModelAndView modelAndView = new ModelAndView("problem_report/search");
        modelAndView.addObject("problemReportBeanList", problemReportService.search(problemReportBean));
        modelAndView.addObject("codeTableMap", codeTableService.getCodeTableMap("problem_report", "status"));
        return modelAndView;
    }

    @ResponseBody
    @GetMapping(path = "/get/{problemReportNo}", produces = "application/json;charset=UTF-8")
    public String getById(@PathVariable Integer problemReportNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            System.out.println(problemReportNo);
            ProblemReportBean problemReportBean = problemReportService.getById(problemReportNo);
            result.put("result", true);
            result.put("problemReportNo", problemReportBean.getProblemReportNo());
            result.put("content", problemReportBean.getContent());
            result.put("userId", problemReportBean.getUserId());
            result.put("userName", problemReportBean.getUserName());
            result.put("status", problemReportBean.getStatus());
            result.put("statusStr", problemReportBean.getStatusStr());

        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }


    @ResponseBody
    @GetMapping(path = "/manage/search", produces = "application/json;charset=UTF-8")
    public String manageSearch(ProblemReportBean problemReportBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {

            List<ProblemReportBean> problemReportBeanList = problemReportService.search(problemReportBean);
            result.put("result", true);
            ArrayNode data = result.putArray("array");
            for (ProblemReportBean pb : problemReportBeanList) {
                ObjectNode problemReportNode = data.addObject();
                problemReportNode.put("problemReportNo", pb.getProblemReportNo());
                problemReportNode.put("content", pb.getContent());
                problemReportNode.put("userId", pb.getUserId());
                problemReportNode.put("userName", pb.getUserName());
                problemReportNode.put("statusStr", pb.getStatusStr());
                problemReportNode.put("startDateStr", pb.getStartDate() != null ?
                        pb.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "");
                problemReportNode.put("endDateStr", pb.getEndDate() != null ?
                        pb.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }

    @GetMapping(path = "/search")
    public ModelAndView userSearchPage() {
        return new ModelAndView("problem_report/userSearch");
    }

    @ResponseBody
    @GetMapping(path = "/search", produces = "application/json;charset=UTF-8")
    public String search() throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            ProblemReportBean problemReportBean = new ProblemReportBean();
            UserInformation userInformation = (UserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            problemReportBean.setUserId(userInformation.getUserId());
            List<ProblemReportBean> problemReportBeanList = problemReportService.search(problemReportBean);
            result.put("result", true);
            ArrayNode data = result.putArray("array");
            for (ProblemReportBean pb : problemReportBeanList) {
                ObjectNode problemReportNode = data.addObject();
                problemReportNode.put("problemReportNo", pb.getProblemReportNo());
                problemReportNode.put("content", pb.getContent());
                problemReportNode.put("userId", pb.getUserId());
                problemReportNode.put("userName", pb.getUserName());
                problemReportNode.put("statusStr", pb.getStatusStr());
                problemReportNode.put("startDateStr", pb.getStartDate() != null ?
                        pb.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "");
                problemReportNode.put("endDateStr", pb.getEndDate() != null ?
                        pb.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }


    @ResponseBody
    @GetMapping(path = "/filter")
    public String getFilterData() throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<UserInformationBean> userInformationBeanList = userInformationService.searchAll();

            result.put("result", true);
            ArrayNode data = result.putArray("data");
            for (UserInformationBean userInformationBean : userInformationBeanList) {
                ObjectNode userInformationNode = data.addObject();
                userInformationNode.put("userName", userInformationBean.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);
            result.putObject("data");
        }
        return o.writeValueAsString(result);
    }

    @ResponseBody
    @PostMapping(path = "/delete/{problemReportNo}", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer problemReportNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            problemReportService.delete(problemReportNo);
            result.put("result", true);
            result.put("message", "刪除成功");

        } catch (Exception e) {
            result.put("result", false);
            result.put("message", "刪除失敗");
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }


    @ResponseBody
    @PostMapping(path = "/update", produces = "application/json;charset=UTF-8")
    public String update(ProblemReportBean problemReportBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        System.out.println(problemReportBean.getProblemReportNo());
        try {
            ProblemReportBean prb = problemReportService.update(problemReportBean);
            result.put("result", true);
            result.put("message", "更新成功");
        } catch (Exception e) {
            result.put("result", false);
            result.put("message", "更新失敗");
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }
}
