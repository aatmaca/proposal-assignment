/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author engin.demircioglu
 */
public class BaseDAO extends HibernateDaoSupport {
    
    public void saveObject(Object o) {
        getHibernateTemplate().persist(o);
    }

    public void updateObject(Object o) {
        getHibernateTemplate().update(o);
    }
    
    public void saveOrUpdate(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
    }
    
    public Object loadObject(Class clazz, Serializable id) {
        return getHibernateTemplate().get(clazz, id);
    }

    public List listObjects(Class clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }
    
    public void removeObject(Object obj) {
        getHibernateTemplate().delete(obj);
    }
    
    public void saveOrUpdateAllObjects(List l){
        getHibernateTemplate().saveOrUpdateAll(l);
    }
    
    public void removeAllObjects(List l){
        getHibernateTemplate().deleteAll(l);
    }
    
    public void flush(){
        getHibernateTemplate().flush();
    }
}
