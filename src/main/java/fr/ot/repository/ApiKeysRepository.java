package fr.ot.repository;

import fr.ot.entities.ApiKeysEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApiKeysRepository implements PanacheRepositoryBase<ApiKeysEntity, Integer> {

}
