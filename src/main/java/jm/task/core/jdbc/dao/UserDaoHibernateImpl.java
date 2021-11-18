package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public static Transaction transaction;
    public static  SessionFactory sessionFactory;
    public static Session session;



    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession() ){
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE Users ("
                    + "id INT(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(45),"
                    + "lastName VARCHAR(45),"
                    + "age INT(64))");
            transaction.commit();
        } catch (Exception e){
            if (transaction !=null){
                transaction.rollback();
            }
        }

    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession() ){
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
            transaction.commit();
        }catch (Exception e){
            if (transaction !=null){
                transaction.rollback();
            }
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession() ){
            transaction = session.beginTransaction();
            session.save(new User(name,lastName,age));
        }catch (Exception e){
            if (transaction !=null){
                transaction.rollback();
            }
        }


    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession() ){
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();

        }catch (Exception e){
            if (transaction !=null){
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = Util.getSessionFactory().openSession() ){
            transaction = session.beginTransaction();
            List <User> users = session.createQuery(" FROM User ").list();
            transaction.commit();
            return users ;
        }

    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession() ){
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();

        }catch (Exception e){
            if (transaction !=null){
                transaction.rollback();
            }
        }

    }
}
