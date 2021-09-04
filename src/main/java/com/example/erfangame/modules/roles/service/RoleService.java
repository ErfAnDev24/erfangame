package com.example.erfangame.modules.roles.service;

import com.example.erfangame.modules.roles.model.Roles;
import com.example.erfangame.modules.roles.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private RolesRepository rolesRepository;

    @Autowired
    public RoleService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Roles> findAllRoles() {
        return rolesRepository.findAll();
    }

    public Optional<Roles> findRoleById(Long id) {
        return rolesRepository.findById(id);
    }

    public void registerRole(Roles roles) {
         rolesRepository.save(roles);
    }

    @Transactional
    public void deleteRole(Long id) {
        rolesRepository.deleteById(id);
    }
}
