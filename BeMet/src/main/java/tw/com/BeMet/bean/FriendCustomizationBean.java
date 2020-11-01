package tw.com.BeMet.bean;

import java.util.Date;

public class FriendCustomizationBean {
    private Integer friendCustomizationNo;
    private String name;
    private Integer friendNo;
    private Date createDate;
    private Date modifyDate;
    private String content;
    private Integer statusCode;

    public Integer getFriendCustomizationNo() {
        return friendCustomizationNo;
    }

    public void setFriendCustomizationNo(Integer friendCustomizationNo) {
        this.friendCustomizationNo = friendCustomizationNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFriendNo() {
        return friendNo;
    }

    public void setFriendNo(Integer friendNo) {
        this.friendNo = friendNo;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
