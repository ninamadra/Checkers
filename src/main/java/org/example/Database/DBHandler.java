package org.example.Database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DBHandler {
    private static SessionFactory sessionFactory;

    public DBHandler() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static void main(String[] args) {
        DBHandler dbHandler = new DBHandler();
        List<MoveDTO> x = dbHandler.getMoves(1);
        for (MoveDTO var : x) {
            System.out.println(var.getGameID() + " " + var.getNumber() + " " + var.getOldX() + " " + var.getOldY() + " " + var.getNewX() + " " + var.getGameID());
        }
    }
    public int addGame(String type){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int id = -1;
        try {
            tx = session.beginTransaction();
            GameDTO game = new GameDTO(type);
            id = (Integer) session.save(game);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public List<GameDTO> getGame(int id) {
        Session session = sessionFactory.openSession();
        String statement = "SELECT a FROM GameDTO a WHERE a.id=" + id;
        return session.createQuery(statement, GameDTO.class).getResultList();
    }

    public void addMove(int gameID, int number, int oldX, int oldY, int newX, int newY) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            MoveDTO move = new MoveDTO(gameID, number, oldX, oldY, newX, newY);
            session.save(move);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public List<MoveDTO> getMoves(int GameID) {
        Session session = sessionFactory.openSession();
        String statement = "SELECT a FROM MoveDTO a WHERE a.gameID=" + GameID;
        return session.createQuery(statement, MoveDTO.class).getResultList();
    }
}
