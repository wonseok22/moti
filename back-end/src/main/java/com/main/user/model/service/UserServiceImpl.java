package com.main.user.model.service;

import com.main.user.model.entity.User;
import com.main.user.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public User getUser(String userId) throws SQLException {
        return null;
    }

    @Override
    public User registUser(User user) throws Exception {
        String pw = user.getPassword();
        String hex = "";


        User userFindById = userRepository.findByUserId(user.getUserId());
        User userFindByNickname = userRepository.findByEmail(user.getEmail());
        User userFindByEmail = userRepository.findByNickname(user.getNickname());
        if (userFindByEmail != null || userFindByNickname != null || userFindById != null){
            return null;
        }


        // "SHA1PRNG"은 알고리즘 이름
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);

        // SALT 생성
        String salt = new String(Base64.getEncoder().encode(bytes));
        String rawAndSalt = pw+salt;

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // 평문+salt 암호화
        md.update(rawAndSalt.getBytes());
        hex = String.format("%064x", new BigInteger(1, md.digest()));
        user.setSalt(salt);
        user.setPassword(hex);
        user.setType("default");


        return userRepository.save(user);

    }

    @Override
    public User loginUser(User user) throws Exception {
        User findUser = userRepository.findByUserId(user.getUserId());
        if (findUser == null){
            return null;
        }
        String userPwd = user.getPassword();
        userPwd += findUser.getSalt();

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // 평문+salt 암호화
        md.update(userPwd.getBytes());
        userPwd = String.format("%064x", new BigInteger(1, md.digest()));

        if (userPwd.equals(findUser.getPassword())){
            return findUser;
        } else {
            return null;
        }
    }

    @Override
    public User modifyUser(User user) throws Exception {
        User findUser = userRepository.findByUserId(user.getUserId());
        if (findUser == null){
            return null;
        }
        String userPwd = user.getPassword();
        userPwd += findUser.getSalt();

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // 평문+salt 암호화
        md.update(userPwd.getBytes());
        userPwd = String.format("%064x", new BigInteger(1, md.digest()));
        user.setSalt(findUser.getSalt());
        user.setPassword(userPwd);
        user.setType("default");
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public int deleteUser(String userId) throws Exception {
        return userRepository.deleteByUserId(userId);
    }

    @Override
    public User checkMember(String type, String value) throws Exception {
        User user = null;
        if ("id".equals(type)){
            user = userRepository.findByUserId(value);
        } else if ("nickname".equals(type)) {
            user = userRepository.findByNickname(value);
        } else if ("email".equals(type)){
            user = userRepository.findByEmail(value);
        } else {
            // 타입 잘못된경우
            return null;
        }
        return user;
    }


    @Override
    public void saveRefreshToken(String userId, String refreshToken) throws Exception {
        User user = userRepository.findByUserId(userId);
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
    }

    @Override
    public String getRefreshToken(String userId) throws Exception {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return null;
        }
        return user.getRefreshToken();
    }

    @Override
    public void delRefreshToken(String userId) throws Exception {
        User user = userRepository.findByUserId(userId);
        user.setRefreshToken(null);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
