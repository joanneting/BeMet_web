package tw.com.business_meet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.business_meet.bean.ActivityDateBean;
import tw.com.business_meet.bean.ActivityInviteBean;
import tw.com.business_meet.bean.ActivityLabelBean;
import tw.com.business_meet.bean.TimelineBean;
import tw.com.business_meet.dao.ActivityInviteDAO;
import tw.com.business_meet.dao.ActivityLabelDAO;
import tw.com.business_meet.service.ActivityDateService;
import tw.com.business_meet.service.ActivityInviteService;
import tw.com.business_meet.service.ActivityLabelService;
import tw.com.business_meet.service.TimelineService;
import tw.com.business_meet.vo.ActivityDate;
import tw.com.business_meet.vo.ActivityInvite;
import tw.com.business_meet.vo.ActivityLabel;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/timeline")
public class TimelineController {
    @Autowired
    TimelineService timelineService;
    @Autowired
    ActivityInviteService activityInviteService;
    @Autowired
    ActivityLabelService activityLabelService;
    @Autowired
    ActivityDateService activityDateService;
    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody TimelineBean timelineBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            TimelineBean tb = timelineService.add(timelineBean);
            result.put("result", true);
            result.putPOJO("data", tb);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/update", produces = "application/json;charset=UtF-8")
    public String update(@RequestBody TimelineBean timelineBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            TimelineBean tb = timelineService.update(timelineBean);
            result.put("result", true);
            result.putPOJO("data", tb);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", false);

        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/search", produces = "application/json;charset=UTF-8")
    public String search(@RequestBody TimelineBean timelineBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<TimelineBean> timelineBeanList = timelineService.search(timelineBean);
            ArrayNode arrayNode = result.putArray("data");
            for (TimelineBean tb :
                    timelineBeanList) {
                arrayNode.addPOJO(tb);
            }
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }
    @PostMapping(path = "/get/{timelineNo}", produces = "application/json;charset=UTF-8")
    public String getById(@PathVariable Integer timelineNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E M月d日 aa h:m");
            TimelineBean timelineBean = timelineService.getById(timelineNo);
            if(timelineBean.getTimelinePropertiesNo()==1) {
                ActivityInviteBean activityInviteBean = new ActivityInviteBean();
                activityInviteBean.setActivityNo(timelineNo);
                ActivityLabelBean activityLabelBean = new ActivityLabelBean();
                activityLabelBean.setActivityNo(timelineNo);
                List<ActivityInviteBean> activityInviteList = activityInviteService.search(activityInviteBean);
                List<ActivityLabelBean> activityLabelList = activityLabelService.search(activityLabelBean);
                timelineBean.setActivityInviteBeanList(activityInviteList);
                timelineBean.setActivityLabelBeanList(activityLabelList);
                ActivityDateBean activityDateBean = new ActivityDateBean();
                activityDateBean.setActivityNo(timelineNo);
                ActivityDateBean adb = activityDateService.search(activityDateBean).get(0);


                timelineBean.setStartDate(simpleDateFormat.format(adb.getStartDate()));
                timelineBean.setEndDate(simpleDateFormat.format(adb.getEndDate()));
                System.out.println("timelineBean.getEndDate() = " + timelineBean.getEndDate());
            }
            timelineBean.setCreateDateStr(simpleDateFormat.format(timelineBean.getCreateDate()));
            ArrayNode arrayNode = result.putArray("data");
            result.put("result", true);
            result.putPOJO("data", timelineBean);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

 @PostMapping(path = "/searchlist", produces = "application/json;charset=UTF-8")
    public String searchList(@RequestBody TimelineBean timelineBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            List<TimelineBean> timelineBeanList = timelineService.search(timelineBean);
            for (int i = 0; i < timelineBeanList.size(); i++) {
                TimelineBean tlb  = timelineBeanList.get(i);
                tlb.setCreateDateStr(new SimpleDateFormat("MM/dd").format(tlb.getCreateDate()));
                timelineBeanList.set(i,tlb);
            }
            ArrayNode arrayNode = result.putArray("data");
            for (TimelineBean tb :
                    timelineBeanList) {
                arrayNode.addPOJO(tb);
            }
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
            e.printStackTrace();
        }
        return o.writeValueAsString(result);
    }

    @PostMapping(path = "/{timelineNp}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer timelineNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            timelineService.delete(timelineNo);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
}
