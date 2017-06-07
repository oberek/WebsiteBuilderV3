/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteBuilder;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Maciej
 */
@ManagedBean(name = "userController")
@SessionScoped
public class UserController {

    DataModel usernames;
    UserHelper helper;

    int startId;
    int endId;
    private int recordCount = 1000;
    private int pageSize = 10;

    private Users current;
    private int selectedItemIndex;

    public UserController() {
        helper = new UserHelper();
        startId = 1;
        endId = 10;
    }

    public UserController(int start, int end) {
        helper = new UserHelper();
        startId = start;
        endId = end;
    }

    public Users getSelected() {
        if (current == null) {
            current = new Users();
            selectedItemIndex = -1;
        }
        return current;
    }

    public DataModel getUsernames() {
        if (usernames == null) {
            usernames = new ListDataModel(helper.getUsernames(startId, endId));
        }
        return usernames;
    }

    void recreateModel() {
        usernames = null;
    }

    public boolean isHasNextPage() {
        return endId + pageSize <= recordCount;
    }

    public boolean isHasPreviousPage() {
        return startId - pageSize > 0;
    }

    public String next() {
        startId = endId + 1;
        endId = endId + pageSize;
        recreateModel();
        return "index";
    }

    public String previous() {
        startId = startId - pageSize;
        endId = endId - pageSize;
        recreateModel();
        return "index";
    }

    public int getPageSize() {
        return pageSize;
    }

    public String prepareView() {
        current = (Users) getUsernames().getRowData();
        return "browse";
    }

    public String prepareList() {
        recreateModel();
        return "index";
    }
    public String getWebsites(){
        System.out.println(current.getUserId());
        List websites = helper.getWebsitesById(current.getUserId());
        StringBuilder allUserWebsites = new StringBuilder();
        for(int i = 0; i < websites.size(); i++){
            Website w = (Website) websites.get(i);
            allUserWebsites.append(w.getWebsiteName()).append(" ");
            allUserWebsites.append(w.getUrl()).append(" ");
            allUserWebsites.append(w.getPublished()).append(" ");
        }
        return allUserWebsites.toString();
    }
    
}