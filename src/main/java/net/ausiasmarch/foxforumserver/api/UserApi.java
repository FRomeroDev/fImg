package net.ausiasmarch.foxforumserver.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.ausiasmarch.foxforumserver.entity.UserEntity;
import net.ausiasmarch.foxforumserver.service.FileSystemStorageService;
import net.ausiasmarch.foxforumserver.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    UserService oUserService;

    @Autowired
    private FileSystemStorageService storageService;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUserService.get(id));
    }

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<UserEntity> get(@PathVariable("username") String username) {
        return ResponseEntity.ok(oUserService.getByUsername(username));
    }

    /*
     * @PostMapping("")
     * public ResponseEntity<Long> create(@RequestParam("file") MultipartFile file,
     * 
     * @RequestParam("user") String userJson) {
     * ObjectMapper objectMapper = new ObjectMapper();
     * try {
     * UserEntity userEntity = objectMapper.readValue(userJson, UserEntity.class);
     * // Subir imagen al sistema de archivos y guardar la URL en la entidad del
     * // usuario
     * if (file != null && !file.isEmpty()) {
     * String imageUrl = storageService.store(file);
     * userEntity.setProfileImageUrl(imageUrl);
     * }
     * 
     * // Guardar el usuario en la base de datos
     * Long userId = oUserService.create(userEntity);
     * return ResponseEntity.ok(userId);
     * } catch (IOException e) {
     * e.printStackTrace();
     * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     * }
     * }
     */

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody UserEntity oUserEntity) {
        return ResponseEntity.ok(oUserService.create(oUserEntity));
    }

    @PutMapping("")
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity oUserEntity) {
        return ResponseEntity.ok(oUserService.update(oUserEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUserService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<UserEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return ResponseEntity.ok(oUserService.getPage(oPageable, strFilter));
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oUserService.populate(amount));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oUserService.empty());
    }

    @GetMapping("/byRepliesNumberDesc")
    public ResponseEntity<Page<UserEntity>> getPageByRepliesNumberDesc(Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getPageByRepliesNumberDesc(oPageable));
    }

}
