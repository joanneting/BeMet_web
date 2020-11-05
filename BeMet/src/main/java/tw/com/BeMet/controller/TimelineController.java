package tw.com.BeMet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tw.com.BeMet.bean.*;
import tw.com.BeMet.service.*;
import tw.com.BeMet.vo.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    @Autowired
    UserInformationService userInformationService;
    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public String add(@RequestBody TimelineBean timelineBean) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            TimelineBean tb = timelineService.add(timelineBean);
            List<ActivityInviteBean> resultInviteList = new ArrayList<>();
            if(timelineBean.getTimelinePropertiesNo() == 1){
                List<ActivityInviteBean> activityInviteBeanList = timelineBean.getActivityInviteBeanList();
                for (ActivityInviteBean activityInviteBean : activityInviteBeanList) {
                   activityInviteBean.setActivityNo(tb.getTimelineNo());
                   activityInviteBean.setStatus(1);
                    ActivityInviteBean resultBean = activityInviteService.add(activityInviteBean);
                    resultInviteList.add(resultBean);
                }
                ActivityDateBean activityDate = new ActivityDateBean();
                activityDate.setActivityNo(tb.getTimelineNo());
                activityDate.setStartDate(timelineBean.getStartDate());
                activityDate.setEndDate(timelineBean.getEndDate());
                ActivityDateBean activityDateBean = activityDateService.add(activityDate);
                ActivityLabelBean activityLabelBean = timelineBean.getActivityLabelBean();
                activityLabelBean.setActivityNo(tb.getTimelineNo());
                activityLabelBean = activityLabelService.add(activityLabelBean);
                tb.setActivityInviteBeanList(resultInviteList);
                tb.setActivityLabelBean(activityLabelBean);
                tb.setActivityDateBean(activityDateBean);
            }
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
            Integer timelineNo = timelineBean.getTimelineNo();

            if(timelineBean.getTimelinePropertiesNo() == 1){
                ActivityLabelBean activityLabelBean = timelineBean.getActivityLabelBean();
                activityLabelService.update(activityLabelBean);

                ActivityDateBean searchActivityDateBean = new ActivityDateBean();
                searchActivityDateBean.setActivityNo(timelineNo);
                List<ActivityDateBean> activityDateBeanList = activityDateService.search(searchActivityDateBean);
                ActivityDateBean activityDateBean = activityDateBeanList.get(0);
                activityDateBean.setStartDate(timelineBean.getStartDate());
                activityDateBean.setEndDate(timelineBean.getEndDate());
                activityDateService.update(activityDateBean);

                ActivityInviteBean searchInviteBean = new ActivityInviteBean();
                searchInviteBean.setActivityNo(timelineNo);

                List<ActivityInviteBean> deleteInviteBeanList = activityInviteService.search(searchInviteBean);
                for (ActivityInviteBean deleteInviteBean : deleteInviteBeanList) {
                    activityInviteService.delete(deleteInviteBean.getActivityInviteNo());
                }
                List<ActivityInviteBean> resultInviteList = new ArrayList<>();
                List<ActivityInviteBean> activityInviteBeanList = timelineBean.getActivityInviteBeanList();
                for (ActivityInviteBean activityInviteBean : activityInviteBeanList) {
                    activityInviteBean.setActivityNo(tb.getTimelineNo());
                    activityInviteBean.setStatus(activityInviteBean.getStatusCode()==null?1:activityInviteBean.getStatus());
                    ActivityInviteBean resultBean = activityInviteService.add(activityInviteBean);
                    resultInviteList.add(resultBean);
                }
                tb.setActivityLabelBean(activityLabelBean);
                tb.setActivityInviteBeanList(activityInviteBeanList);
                tb.setStartDate(activityDateBean.getStartDate());
                tb.setEndDate(activityDateBean.getEndDate());

            }
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月d日 E aa h:m");
            TimelineBean timelineBean = timelineService.getById(timelineNo);
            if(timelineBean.getTimelinePropertiesNo()==1) {
                ActivityInviteBean activityInviteBean = new ActivityInviteBean();
                activityInviteBean.setActivityNo(timelineNo);

                List<ActivityInviteBean> activityInviteList = activityInviteService.searchAccept(activityInviteBean);
                ActivityLabelBean activityLabelBean = new ActivityLabelBean();
                activityLabelBean.setActivityNo(timelineNo);
                activityLabelBean = activityLabelService.search(activityLabelBean).get(0);
                ActivityInviteBean creater = new ActivityInviteBean();
                UserInformationBean createrInformation = userInformationService.getById(timelineBean.getMatchmakerId());
                creater.setUserName(createrInformation.getName());
                creater.setUserId(createrInformation.getUserId());
                activityInviteList.add(0,creater);
                timelineBean.setActivityInviteBeanList(activityInviteList);
                timelineBean.setActivityLabelBean(activityLabelBean);
                ActivityDateBean activityDateBean = new ActivityDateBean();
                activityDateBean.setActivityNo(timelineNo);
                ActivityDateBean adb = activityDateService.search(activityDateBean).get(0);


                timelineBean.setStartDateStr(simpleDateFormat.format(adb.getStartDate()));
                timelineBean.setEndDateStr(simpleDateFormat.format(adb.getEndDate()));
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

            UserInformation userInformation = (UserInformation)SecurityContextHolder.getContext().getAuthentication().getPrincipal();


            if(timelineBean.getFriendId()!=null){
                TimelineBean searchBean = new TimelineBean();
                searchBean.setMatchmakerId(timelineBean.getMatchmakerId());
                List<TimelineBean> resultSelfList = timelineService.search(searchBean);
                searchBean.setMatchmakerId(timelineBean.getFriendId());
                List<TimelineBean> resultFriendList = timelineService.search(searchBean);

                for (TimelineBean searchTimeBean : resultSelfList) {
                    ActivityInviteBean activityInviteBean = new ActivityInviteBean();
                    activityInviteBean.setUserId(timelineBean.getFriendId());
                    activityInviteBean.setActivityNo(searchTimeBean.getTimelineNo());
                    List<ActivityInviteBean> inviteList = activityInviteService.searchAccept(activityInviteBean);
                    if (inviteList.size() > 0){
                        timelineBeanList.add(searchTimeBean);
                    }
                }
                for (TimelineBean searchTimeBean : resultFriendList) {
                    ActivityInviteBean activityInviteBean = new ActivityInviteBean();
                    activityInviteBean.setUserId(timelineBean.getMatchmakerId());
                    activityInviteBean.setActivityNo(searchTimeBean.getTimelineNo());
                    List<ActivityInviteBean> inviteList = activityInviteService.searchAccept(activityInviteBean);
                    if (inviteList.size() > 0){
                        timelineBeanList.add(searchTimeBean);
                    }
                }



            }else{
                ActivityInviteBean activityInviteBean = new ActivityInviteBean();
                activityInviteBean.setUserId(userInformation.getUserId());
                List<ActivityInviteBean> inviteList = activityInviteService.searchAccept(activityInviteBean);
                for (ActivityInviteBean inviteBean : inviteList) {
                    timelineBeanList.add(timelineService.getById(inviteBean.getActivityNo()));
                }
            }
            for (int i = 0; i < timelineBeanList.size(); i++) {
                TimelineBean tlb  = timelineBeanList.get(i);
                if(tlb.getTimelinePropertiesNo() == 1){
                    ActivityDateBean activityDateBean = new ActivityDateBean();
                    activityDateBean.setActivityNo(tlb.getTimelineNo());
                    List<ActivityDateBean> activityDateBeanList = activityDateService.search(activityDateBean);
                    if(activityDateBeanList.size() > 0)
                        tlb.setStartDateStr(new SimpleDateFormat("MM/dd").format(activityDateBeanList.get(0).getStartDate()));
                }
                tlb.setCreateDateStr(new SimpleDateFormat("MM/dd").format(tlb.getCreateDate()));
                timelineBeanList.set(i,tlb);
            }
            Collections.sort(timelineBeanList, new Comparator<TimelineBean>() {
                @Override
                public int compare(TimelineBean o1, TimelineBean o2) {
                    if(o1.getStartDateStr() == null && o2.getStartDateStr()==null){
                        return o1.getCreateDateStr().compareTo(o2.getCreateDateStr());
                    }else if(o1.getStartDateStr() == null){
                        return o1.getCreateDateStr().compareTo(o2.getStartDateStr());
                    }else if (o2.getStartDateStr() == null){
                        return o1.getStartDateStr().compareTo(o2.getCreateDateStr());

                    }
                    return o1.getStartDateStr().compareTo(o2.getStartDateStr());
                }
            });
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

    @PostMapping(path = "/{timelineNo}/delete", produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable Integer timelineNo) throws Exception {
        ObjectMapper o = new ObjectMapper();
        ObjectNode result = o.createObjectNode();
        try {
            TimelineBean timelineBean = timelineService.getById(timelineNo);
            if(timelineBean.getTimelinePropertiesNo() == 1){
                ActivityDateBean activityDateBean = new ActivityDateBean();
                activityDateBean.setActivityNo(timelineNo);
                ActivityLabelBean activityLabelBean = new ActivityLabelBean();
                activityLabelBean.setActivityNo(timelineNo);
                ActivityInviteBean activityInviteBean = new ActivityInviteBean();
                activityInviteBean.setActivityNo(timelineNo);

                List<ActivityDateBean> activityDateBeanList = activityDateService.search(activityDateBean);
                for (ActivityDateBean adb : activityDateBeanList) {
                    activityDateService.delete(adb.getActivityDateNo());
                }
                List<ActivityLabelBean> activityLabelBeanList = activityLabelService.search(activityLabelBean);
                for (ActivityLabelBean alb : activityLabelBeanList) {
                    activityLabelService.delete(alb.getActivityLabelNo());
                }
                List<ActivityInviteBean> activityInviteBeanList = activityInviteService.search(activityInviteBean);
                for (ActivityInviteBean inviteBean : activityInviteBeanList) {
                    activityInviteService.delete(inviteBean.getActivityInviteNo());
                }
            }
            timelineService.delete(timelineNo);
            result.put("result", true);
        } catch (Exception e) {
            result.put("result", false);
        }
        return o.writeValueAsString(result);
    }
}
