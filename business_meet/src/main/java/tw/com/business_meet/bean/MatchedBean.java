package tw.com.business_meet.bean;

import tw.com.business_meet.vo.UserInformation;

public class MatchedBean {
    private Integer MSno;
    private String matchedBlueTooth;
    private String blueTooth;
    private String memorandum;

    public Integer getMSno() {
        return MSno;
    }

    public void setMSno(Integer MSno) {
        this.MSno = MSno;
    }

    public String getMatchedBlueTooth() {
        return matchedBlueTooth;
    }

    public void setMatchedBlueTooth(String matchedBlueTooth) {
        this.matchedBlueTooth = matchedBlueTooth;
    }

    public String getBlueTooth() {
        return blueTooth;
    }

    public void setBlueTooth(String blueTooth) {
        this.blueTooth = blueTooth;
    }

    public String getMemorandum() {
        return memorandum;
    }

    public void setMemorandum(String memorandum) {
        this.memorandum = memorandum;
    }
}
