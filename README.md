# BeMet

* first clone project 
    * copy application-server.properties in same directory
        * file name update to application-local.properties
    * update application-local.properties 
        * db.username = your db username
        * db.password = your db password
        * db.url ip update to 127.0.0.1 
* run project
    * open command line
        * enter -> gradle bootrun
 
* store path
    * page
        * src/main/resources/templates
    * static file  `css、javascript...`
        * src/main/resources/static
    * function
        * src/main/java
 
 
 ## code rule
 ### url
 `若參數欄未寫參數，請參照項目二之參數`
 * 登入
    |功能名稱| url| 參數| 說明|
    |-----|----|----|----|
    |登入 | /login | userId & password & bluetooth|
 * 個人
    * 資訊
        * 基底 : /userinformation
        * 參數 : userinformationBean

        |功能名稱| url| 參數| 說明|
        |-----|----|----|----|
        |註冊 | /add||
        |搜尋(多筆) | /search||
        |搜尋(單筆) |/get/{userId} | userId| 以帳號搜尋|
        |搜尋(單筆) |/getBluetooth/{bluetooth}| bluetooth | 以藍牙搜尋|
        |更新|/update||
    * 備註
        * 基底 : /usercustomization
        * 參數 : userCustomizationBean

        |功能名稱| url| 參數| 說明|
        |-----|----|----|----|
        |新增 | /add||
        |搜尋(多筆) | /search||
        |更新|/update||
        |刪除 | /{userCustomizationNo}/delete | userCustomizationNo||
* 好友
    * 配對資訊
        * 基底 : /friend
        * 參數 : friendBean

        |功能名稱| url| 參數| 說明|
        |-----|----|----|----|
        |配對 | /add||
        |搜尋(多筆) | /search||
        |更新|/update||
    * 備註
        * 基底 : /friendcustomization
        * 參數 : friendCustomizationBean


        |功能名稱| url| 參數| 說明|
        |-----|----|----|----|
        |新增 | /add||
        |搜尋(多筆) | /search||
        |更新|/update||
        |刪除 | /{friendCustomizationNo}/delete | friendCustomizationNo|刪除備註資訊|
    * 分群
        * 基底 : /friendgroup
        * 參數 : friendfGroupBean


        |功能名稱| url| 參數| 說明|
        |-----|----|----|----|
        |新增 | /add||
        |搜尋(多筆) | /search||
        |更新|/update||
        |刪除 | /{friendfGroupNo}/delete | friendfGroupNo|刪除好友群組|
    * 群組
        * 基底 : /groups
        * 參數 : groupsBean


        |功能名稱| url| 參數| 說明|
        |-----|----|----|----|
        |新增 | /add||
        |搜尋(多筆) | /search||
        |更新|/update||
        |刪除 | /{groupsNo}/delete | groupsNo|刪除群組|
* 時間軸
    * 內容
        * 基底 : /timeline
        * 參數 : timelineBean
        
        |功能名稱| url| 參數| 說明|
        |-----|----|----|----|
        |新增 | /add||
        |搜尋(多筆) | /search||
        |搜尋(多筆)|/searchlist||格式化日期|
        |搜尋(單筆) | /get/{timelineNo}|timelineNo|         
        |更新|/update||
        |刪除 | /{timelineNo}/delete | timelineNo||
    * 性質
        * 基底 : /timelineproperties
        * 參數 : timelinePropertiesBean

        |功能名稱| url| 參數| 說明|
        |-----|----|----|----|
        |新增 | /add||
        |搜尋(多筆) | /search||
        |更新|/update||
        |刪除 | /{timelinePropertiesNo}/delete | timelinePropertiesNo||
* 活動
    * 日期
        * 基底 : /activitydate
        * 參數 : activityDateBean

        |功能名稱| url| 參數| 說明|
        |-----|----|----|----|
        |新增 | /add||
        |搜尋(多筆) | /search||
        |更新|/update||
        |刪除 | /{activityDateNo}/delete | activityDateNo||
     * 邀請
        * 基底 : /activityinvite
        * 參數 : activityInviteBean

        |功能名稱| url| 參數| 說明|
        |-----|----|----|----|
        |新增 | /add||
        |搜尋(多筆) | /search||
        |更新|/update||
        |刪除 | /{activityInviteNo}/delete | activityInviteNo||
     * 標籤
        * 基底 : /activitylabel
        * 參數 :activityLabelBean

        |功能名稱| url| 參數| 說明|
        |-----|----|----|----|
        |新增 | /add||
        |搜尋(多筆) | /search||
        |更新|/update||
        |刪除 | /{activityLabelNo}/delete | activityLabelNo||
     * 提醒
        * 基底 : /activityremind
        * 參數 : activityRemindBean

        |功能名稱| url| 參數| 說明|
        |-----|----|----|----|
        |新增 | /add||
        |搜尋(多筆) | /search||
        |更新|/update||
        |刪除 | /{activityRemindNo}/delete | activityRemindNo||