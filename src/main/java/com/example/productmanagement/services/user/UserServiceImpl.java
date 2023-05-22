package com.example.productmanagement.services.user;

import com.example.productmanagement.dtos.user.UserDTO;
import com.example.productmanagement.entities.User;
import com.example.productmanagement.exceptions.InvalidException;
import com.example.productmanagement.exceptions.NotFoundException;
import com.example.productmanagement.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import static com.example.productmanagement.utils.EnumRole.ROLE_CUSTOMER;

import java.util.ArrayList;
import java.util.List;

import java.security.Principal;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Khách hàng có id %s không tồn tại", id)));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Khách hàng có email %s không tồn tại", email)));
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO dto, Principal principal) {
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên khách hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPassword())) {
            throw new InvalidException("Mật khẩu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPhone())) {
            throw new InvalidException("Số điện thoại không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getAddress())) {
            throw new InvalidException("Địa chỉ không được bỏ trống");
        }
        if (userRepository.checkUserEmail(dto.getEmail().trim())) {
            throw new InvalidException(String.format("Email %s đã tồn tại", dto.getEmail()));
        }
        User user = new User();
        user.setName(dto.getName().trim());
        user.setEmail(dto.getEmail().trim());
        user.setPassword(dto.getPassword().trim());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        List<String> roles = new ArrayList<>();
        roles.add(ROLE_CUSTOMER.name());
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    public User update(String id, UserDTO dto, Principal principal) {
        User user = getUser(id);
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên khách hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPassword())) {
            throw new InvalidException("Mật khẩu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPhone())) {
            throw new InvalidException("Số điện thoại không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getAddress())) {
            throw new InvalidException("Địa chỉ không được bỏ trống");
        }
        if (!user.getEmail().equalsIgnoreCase(dto.getEmail().trim())
                && userRepository.checkUserEmail(dto.getEmail().trim())) {
            throw new InvalidException(String.format("Email %s đã tồn tại", dto.getEmail()));
        }
        user.setName(dto.getName().trim());
        user.setEmail(dto.getEmail().trim());
        user.setPassword(dto.getPassword().trim());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        List<String> roles = new ArrayList<>();
        roles.add(ROLE_CUSTOMER.name());
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    public User delete(String id) {
        User user = getUser(id);
        userRepository.delete(user);
        return user;
    }
}