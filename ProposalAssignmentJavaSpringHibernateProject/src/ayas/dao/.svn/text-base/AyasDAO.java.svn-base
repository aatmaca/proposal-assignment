/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayas.dao;

import ayas.model.User;
import ayas.model.Proposal;
import ayas.model.Company;
import ayas.model.Panelist;
import ayas.model.Title;
import ayas.model.Subject;
import ayas.util.StringUtils;
import java.util.List;
import java.util.Locale;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

public class AyasDAO extends BaseDAO {

    public boolean checkSomething(Integer id1, Integer id2) {
        Object[] obj = new Object[2];
        obj[0] = id1;
        obj[1] = id2;

        List list = getHibernateTemplate().findByNamedParam("select count(*) from SomeObject o "
                + "where o.id1 = :id1 and o.id2 = :id2 ", new String[]{"id1", "id2"}, obj);

        if (((Long) list.iterator().next()).intValue() != 0) {
            return true;
        }

        return false;
    }

    public int deleteSomeObject(Integer aaa, String sss, List ddd) {
        Query q = getSessionFactory().getCurrentSession().createQuery("delete SomeObject o "
                + "where o.appendNumber in (:ddd) and sdfsd = :aaa and sdfdf= :sss");
        q.setParameter("aaa", aaa);
        q.setParameter("sss", sss);
        q.setParameterList("ddd", ddd);
        return q.executeUpdate();
    }

    /**
     *
     * This method shows how to write an SQL directly
     *
     * @param partialData
     * @return
     */
    public List getAllNames(String partialData) {

        partialData = "%" + StringUtils.toUpperCase(partialData, new Locale("tr")) + "%";

        String sql = "select K.ADI AS KURUM_ADI FROM KURULUS K "
                + "WHERE NLS_upper(K.ADI,'NLS_SORT = XTurkish') like :partialData";

        SQLQuery query = getSession().createSQLQuery(sql);
        query.setString("partialData", partialData);
        query.addScalar("KURUM_ADI", Hibernate.STRING);
        List list = query.list();

        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

    public User login(String username, String password) {
        Object[] obj = new Object[2];
        obj[0] = username;
        obj[1] = password;

        List list = getHibernateTemplate().findByNamedParam("select u from User u "
                + "where u.username = :username and u.password = :password ", new String[]{"username", "password"}, obj);

        if (list == null || list.isEmpty()) {
            return null;
        }

        return (User) list.iterator().next();
    }

    public List getAllPanelsOfUser(Integer id) {

        List list = getHibernateTemplate().find("from Panel "
                + "where owner = ?", id);

        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

    public List listAllAssignmentsOfPanel(Integer panelId) {
        List list = getHibernateTemplate().find("from AssignedPanelistToProposal "
                + "where panelistToProposalId.assignedProposal.panelId = ?", panelId);

        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }
    
    public List listAllAssignedPanelists(Integer panelId) {
        List list = getHibernateTemplate().find("from AssignedPanelist "
                + "where panelId = ?", panelId);

        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

    public List listAllAssignedProposals(Integer panelId) {
        List list = getHibernateTemplate().find("from AssignedProposal "
                + "where panelId = ?", panelId);

        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

    public int getTotalCapacityOfReferee( Integer panelistId){
        List list = getHibernateTemplate().find( "select sum(capacity) from AssignedPanelist " +
                "where panelist.id = ?", panelistId);

        return ((Integer)list.get(0)).intValue();
    }

    public List getRefereeCapacities( Integer panelId){
        List list = getHibernateTemplate().find( "from AssignedPanelist " +
                "where panelId = ?", panelId);

        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

    public int updateRefereeCapacities( Integer[] capacities, Integer[] assignmentIds){
        
        for( int i = 0; i < capacities.length; i++ ){
            String sql = "UPDATE ASSIGNED_PANELIST SET "
                    + "CAPACITY = :ccc WHERE ASSIGNMENT_ID = :id";
            SQLQuery query = getSession().createSQLQuery(sql);
            query.setInteger("ccc", capacities[i]);
            query.setInteger("id", assignmentIds[i]);
            query.executeUpdate();
        }
        return 1;
    }
    
    public int updateProposalProvisions( Integer[] provisions, Integer[] recordIds){
        
        for( int i = 0; i < provisions.length; i++ ){
            String sql = "UPDATE ASSIGNED_PROPOSAL SET "
                    + "PROVISION = :ccc WHERE RECORD_ID = :id";
            SQLQuery query = getSession().createSQLQuery(sql);
            query.setInteger("ccc", provisions[i]);
            query.setInteger("id", recordIds[i]);
            query.executeUpdate();
        }
        return 1;
    }
    
    public List getAllAssignedIds( Integer[] recordIds){
           String sql = "select ASSIGNMENT_ID FROM ASG_PANELISTS_TO_PROPOSALS "
                + "WHERE RECORD_ID = :id";

            SQLQuery query = getSession().createSQLQuery(sql);
            query.setInteger("id", recordIds[0]);
            List list = query.list();
        
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

     public List<Proposal> getSearchedProposals(String s1) {


        Object[] obj = new Object[1];
        obj[0] = "%" + s1 + "%";

        List list =getHibernateTemplate().findByNamedParam("from Proposal "
                + "where NAME LIKE :filter",  new String[]{"filter"}, obj);
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

     public List<Proposal> getSearchedProposalsKeyword(String s1) {


        Object[] obj = new Object[1];
        obj[0] = "%" + s1 + ",%";

        List list =getHibernateTemplate().findByNamedParam("from Proposal "
                + "where KEYWORDS LIKE :filter",  new String[]{"filter"}, obj);
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

    public List<Panelist> getSearchedReferee(List<String> names, List<String> surnames, List<Company> companies, List<Title> titles, List<Subject> subjects) {
        Object[] obj = new Object[1];
        String[] str = new String[1];
        String sql = " WHERE ";
        int count = 0;
        int current = 0;
        for(int k = 0; k < 2; k++)
        {

            for(int i = 0; i < names.size(); i++)
            {
                String old_sql = sql;
                if(names.get(i) != null)
                {
                    if(k== 0)
                        count++;
                    else
                    {
                        obj[current] = "%" + names.get(i) + "%";
                        str[current] = "filter" + current;
                        sql = sql.concat(" NAME LIKE :filter" + current + " AND ");
                        current++;
                    }

                }

                if(surnames.get(i) != null)
                {
                    if(k== 0)
                        count++;
                    else
                    {
                        obj[current] = "%" + surnames.get(i) + "%";
                        str[current] = "filter" + current;
                        sql = sql.concat(" SURNAME LIKE :filter" + current + " AND ");
                        current++;
                    }

                }

                if(companies.get(i) != null)
                {
                    if(k== 0)
                        count++;
                    else
                    {
                        obj[current] = companies.get(i).getId();
                        str[current] = "filter" + current;
                        sql = sql.concat(" COMPANY_ID = filter" + current + " AND ");
                        current++;
                    }

                }

                if(titles.get(i) != null)
                {
                    if(k== 0)
                        count++;
                    else
                    {
                        obj[current] = titles.get(i).getId();
                        str[current] = "filter" + current;
                        sql = sql.concat(" TITLE_ID = filter" + current + " AND ");
                        current++;
                    }

                }

                if(subjects.get(i) != null)
                {
                    if(k== 0)
                        count++;
                    else
                    {
                        obj[current] = subjects.get(i).getId();
                        str[current] = "filter" + current;
                        sql = sql.concat(" SUBJECT_ID = filter" + current + " AND ");
                        current++;
                    }

                }

                if (!old_sql.equals(sql) && k == 1)
                        sql = sql.substring(0, sql.length()-4);
                if (k == 1) sql = sql.concat("OR ");
            }
            if (k == 1) sql = sql.substring(0, sql.length() - 3);
            if (k == 0)
            {
                obj = new Object[count];
                str = new String[count];
            }
        }

        List list =getHibernateTemplate().findByNamedParam("from Referee_Search "
                + sql,  str, obj);
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

    public List<Company> getAllCompanies() {


        List list = getHibernateTemplate().find("from Company "
                + "where 1 = ?", 1);
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

    public List<Title> getAllTitles() {


        List list = getHibernateTemplate().find("from Title "
                + "where 1 = ?", 1);
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

    public List<Subject> getAllSubjects() {


        List list = getHibernateTemplate().find("from Subject "
                + "where 1 = ?", 1);
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

}
