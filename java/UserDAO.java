import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class UserDAO {
    private static final EntityManagerFactory MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    @PersistenceContext
    private EntityManager entityManager;

    public int add(User user){
        entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(user);
        entityTransaction.commit();

        return user.getId();
    }

    public int edit(int id, User user){
        entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User oldUser = entityManager.find(User.class, id);
        oldUser.setName(user.getName());
        oldUser.setAge(user.getAge());

        entityManager.persist(oldUser);
        entityTransaction.commit();

        return user.getId();
    }

    public List<User> getByName(String username){
        entityManager = MANAGER_FACTORY.createEntityManager();
        String query = "select i from User i where i.username = :un1";
        TypedQuery<User> typedQuery = entityManager.createQuery(query,User.class);
        typedQuery.setParameter("un1", username);
        List<User> users = typedQuery.getResultList();
        for(User user : users){
            System.out.println("Имя: " + user.getName() + ", возраст: " + user.getAge());
        }
        return users;
    }

    public List<User> getByAges(){
        entityManager = MANAGER_FACTORY.createEntityManager();
        String query = "select i from User i where i.age between :ageStart and :ageEnd";
        TypedQuery<User> typedQuery = entityManager.createQuery(query,User.class);
        typedQuery.setParameter("ageStart", 10);
        typedQuery.setParameter("ageEnd", 34);

        List<User> users = typedQuery.getResultList();
        for(User user : users){
            System.out.println("Имя: " + user.getName() + ", возраст: " + user.getAge());
        }
        return users;
    }

    public void removeByName(String name){
        entityManager = MANAGER_FACTORY.createEntityManager();
        String query = "select i from User i where i.username = :name";
        TypedQuery<User> typedQuery = entityManager.createQuery(query,User.class);
        typedQuery.setParameter("name", name);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        List<User> resUsers = typedQuery.getResultList();
        for(User user : resUsers){
            entityManager.remove(user);
            System.out.println("Пользователь с id = " + user.getId() + " был удален!");
        }
        entityTransaction.commit();
    }

}
