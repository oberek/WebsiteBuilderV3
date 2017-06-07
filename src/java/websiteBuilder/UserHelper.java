/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteBuilder;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Maciej
 */
public class UserHelper {

    Session session = null;

    public UserHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List getWebsitesById(int user_id) {
        List<Website> websiteList = null;

        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Website as ws where ws.user_id = '" + user_id + "'");
            websiteList = (List<Website>) q.list();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
        return websiteList;
    }

    public List getWebsiteNames() {
        List<Website> websiteList = null;

        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select website_name from Website");
            websiteList = (List<Website>) q.list();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
        return websiteList;
    }

    public Website getWebsiteByPath(String path) {
        Website website = null;
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Website as ws where ws.url='" + path + "'");
            website = (Website) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return website;
    }

    public Website getWebsiteByName(String name) {
        Website website = null;
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Website as ws where ws.website_name='" + name + "'");
            website = (Website) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return website;
    }

    List getUsernames(int startId, int endId) {
        List<Users> usernameList = null;

        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select username from Website");
            usernameList = (List<Users>) q.list();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
        return usernameList;
    }

    List getUserIds() {
        List<Users> usernameList = null;

        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select user_id from Website");
            usernameList = (List<Users>) q.list();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
        return usernameList;
    }
}
