/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cviko3;

import cviko3.exceptions.NonexistentEntityException;
import cviko3.exceptions.PreexistingEntityException;
import entities.TOsoba;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author guntel
 */
public class TOsobaJpaController implements Serializable {

    public TOsobaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TOsoba TOsoba) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(TOsoba);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTOsoba(TOsoba.getId()) != null) {
                throw new PreexistingEntityException("TOsoba " + TOsoba + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TOsoba TOsoba) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TOsoba = em.merge(TOsoba);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = TOsoba.getId();
                if (findTOsoba(id) == null) {
                    throw new NonexistentEntityException("The tOsoba with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TOsoba TOsoba;
            try {
                TOsoba = em.getReference(TOsoba.class, id);
                TOsoba.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TOsoba with id " + id + " no longer exists.", enfe);
            }
            em.remove(TOsoba);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TOsoba> findTOsobaEntities() {
        return findTOsobaEntities(true, -1, -1);
    }

    public List<TOsoba> findTOsobaEntities(int maxResults, int firstResult) {
        return findTOsobaEntities(false, maxResults, firstResult);
    }

    private List<TOsoba> findTOsobaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TOsoba.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TOsoba findTOsoba(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TOsoba.class, id);
        } finally {
            em.close();
        }
    }

    public int getTOsobaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TOsoba> rt = cq.from(TOsoba.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
