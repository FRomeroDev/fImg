package net.ausiasmarch.foxforumserver.service;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.ausiasmarch.foxforumserver.entity.UserEntity;
import net.ausiasmarch.foxforumserver.exception.ResourceNotFoundException;
import net.ausiasmarch.foxforumserver.helper.DataGenerationHelper;
import net.ausiasmarch.foxforumserver.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository oUserRepository;

    public UserEntity get(Long id) {
        return oUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public Long create(UserEntity oUserEntity) {
        oUserEntity.setId(null);
        oUserEntity.setPassword("e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e");
        return oUserRepository.save(oUserEntity).getId();
    }

    public UserEntity update(UserEntity oUserEntity) {
        return oUserRepository.save(oUserEntity);
    }

    public Long delete(Long id) {
        oUserRepository.deleteById(id);
        return id;
    }

    public Page<UserEntity> getPage(Pageable oPageable) {
        return oUserRepository.findAll(oPageable);
    }

    public UserEntity getOneRandom() {
        Pageable oPageable = PageRequest.of((int) (Math.random() * oUserRepository.count()), 1);
        return oUserRepository.findAll(oPageable).getContent().get(0);
    }

    public Long populate(Integer amount) {
        for (int i = 0; i < amount; i++) {
            String name = DataGenerationHelper.getRadomName();
            String surname = DataGenerationHelper.getRadomSurname();
            String lastname = DataGenerationHelper.getRadomSurname();
            String email = name.substring(0, 3) + surname.substring(0, 3) + lastname.substring(0, 2) + i
                    + "@ausiasmarch.net";
            String username = DataGenerationHelper
                    .doNormalizeString(name.substring(0, 3) + surname.substring(1, 3) + lastname.substring(1, 2) + i);
            oUserRepository.save(new UserEntity(name, surname, lastname, email, username,
                    "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e", true));
        }
        return oUserRepository.count();
    }

}
