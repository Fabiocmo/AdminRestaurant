package liliyayalovchenko.dao.hibernate;

import liliyayalovchenko.dao.IngredientDAO;
import liliyayalovchenko.domain.Ingredient;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IngredientDAOImpl implements IngredientDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Ingredient> getAllIngredients() {
        return sessionFactory.getCurrentSession().createQuery("select i from Ingredient i").list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Ingredient getIngredientByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Ingredient ingredient = (Ingredient) session
                .createQuery("select i from Ingredient i where i.name = :var")
                .setParameter("var", name)
                .list().get(0);
        if (ingredient == null) {
            throw new RuntimeException("Cant get ingredient by this name. Wrong name!");
        } else {
            return ingredient;
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean exist(String ingredientName) {
        Session session = sessionFactory.getCurrentSession();
        Set<Ingredient> allIngredient = (Set<Ingredient>) session
                .createQuery("select i from Ingredient i where i.name = :var")
                .setParameter("var", ingredientName)
                .list();
        return allIngredient.contains(new Ingredient(ingredientName));
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addIngredient(Ingredient ingredient) {
        sessionFactory.getCurrentSession().save(ingredient);
    }
}
