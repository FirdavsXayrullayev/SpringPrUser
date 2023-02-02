package user.springpruser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import user.springpruser.dto.UserDto;
import user.springpruser.response.UserResponse;
import user.springpruser.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    UserResponse<UserDto> getById(@PathVariable Integer id){
        return userService.getById(id);
    }
    @PostMapping
    UserResponse<Void> addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }
    @PutMapping
    UserResponse<Void> putUser(@RequestBody UserDto userDto){
        return userService.putUser(userDto);
    }
    @DeleteMapping("/{id}")
    UserResponse<Void> deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }
    @PutMapping("/{id}/{money}")
    UserResponse<Void> pulMoney(@PathVariable Integer id,@PathVariable double money){
        return userService.putMoney(id,money);
    }
}
