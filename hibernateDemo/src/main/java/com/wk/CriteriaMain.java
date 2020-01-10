package com.wk;

import com.wk.entity.User;
import com.wk.entity.UserWrapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * descripiton:
 *
 * @author: wk
 * @time: 16:52 2020/1/8
 * @modifier:
 */
public class CriteriaMain {
    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    static {
        configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    /**
     *  查询所有
     */
    public static void queryAll(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        Query<User> query = session.createQuery(criteria);
        List<User> lists = query.getResultList();
        System.out.println(lists.toString());

        transaction.commit();
        session.close();
    }

    /**
     *  查询指定字段
     * @param
     */
    public static void queryField(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        // 选择多个  field
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Path<Integer> id = userRoot.get("id");
        Path<String> name = userRoot.get("name");
        // 设置要查询的 字段
        criteriaQuery.select(criteriaBuilder.array(id, name));
        // 获取查询结果
        Query<Object[]> query = session.createQuery(criteriaQuery);
        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            System.out.println("id: "+ objects[0]+", name:"+objects[1]);
            System.out.println("---------------------------------------");
        }
        transaction.commit();
        session.close();
    }

    /**
     *  查询指定字段
     */
    public static void queryFields(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UserWrapper> criteriaQuery = criteriaBuilder.createQuery(UserWrapper.class);
        Root<UserWrapper> wrapperRoot = criteriaQuery.from(UserWrapper.class);
        // 把查询结果包装到实体类
        criteriaQuery.select(criteriaBuilder.construct(UserWrapper.class,wrapperRoot.get("id"), wrapperRoot.get("name")));
        criteriaQuery.where(criteriaBuilder.equal(wrapperRoot.get("id"), 1));
        Query<UserWrapper> query = session.createQuery(criteriaQuery);
        UserWrapper userWrapper = query.uniqueResult();
        System.out.println(userWrapper.toString());
        transaction.commit();
        session.close();
    }

    /**
     *  查询指定的field
     */
    public static void queryFieldsMulSet(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.multiselect(userRoot.get("id"), userRoot.get("name"));
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get("id"),1));
        Query<Object[]> query = session.createQuery(criteriaQuery);
        Object[] objects = query.uniqueResult();
        System.out.println("id: "+objects[0]+", name:"+objects[1]);

        transaction.commit();
        session.close();
    }


    /**
     *  条件查询, 查询主键
     */
    public static void whereQuery(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        // 设定root
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot);
        // 设置要选择的字段
        criteria.where(criteriaBuilder.equal(userRoot.get("id"),1));
        Query<User> query = session.createQuery(criteria);
        User user = query.uniqueResult();
        System.out.println(user.toString());

        transaction.commit();
        session.close();
    }

    /**
     *  条件查询, 根据名称模糊查询
     */
    public static void nameLike(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot);
        criteria.where(criteriaBuilder.like(userRoot.get("name"),"%4%"));
        Query<User> query = session.createQuery(criteria);
        List<User> list = query.list();
        System.out.println(list.toString());

        transaction.commit();
        session.close();
    }

    /**
     *  根据 年龄 范围查询, 并找年龄降序
     */
    public static void betweenAge(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot);
        criteria.where(criteriaBuilder.between(userRoot.get("age"),3,5))
                .orderBy(criteriaBuilder.desc(userRoot.get("age")));
        Query<User> query = session.createQuery(criteria);
        List<User> list = query.list();
        System.out.println(list.toString());

        transaction.commit();
        session.close();
    }

    public static void insertRecord(){

    }

    public static void main(String[] args) {
//        queryAll();
//        whereQuery();
//        nameLike();
//        betweenAge();
//        queryField();
//        queryFields();
        queryFieldsMulSet();
    }
}
