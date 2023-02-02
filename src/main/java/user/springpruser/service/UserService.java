package user.springpruser.service;

import org.springframework.stereotype.Service;
import user.springpruser.dto.UserDto;
import user.springpruser.response.UserResponse;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    List<UserDto> userDtoList = new LinkedList<>();

    public UserResponse<UserDto> getById(Integer id){
        Optional<UserDto> optionalUserDto = userDtoList.stream().filter(t-> t.getId().equals(id)).findFirst();

        return UserResponse.<UserDto>builder()
                .success(optionalUserDto.isPresent())
                .code(optionalUserDto.isPresent() ? 0 : -1)
                .message(optionalUserDto.isPresent() ? "Ok" : "This user Id not found " + id)
                .data(optionalUserDto.orElse(null))
                .build();
    }
    public UserResponse<Void> addUser(UserDto userDto){
        if(userDtoList.stream().anyMatch(t-> t.getId().equals(userDto.getId()))){
            return UserResponse.<Void>builder()
                    .code(1)
                    .message("This user Id already exists " + userDto.getId())
                    .build();
        }
        userDtoList.add(userDto);
        return UserResponse.<Void>builder()
                .success(true)
                .message("Ok")
                .build();
    }
    public UserResponse<Void> putUser(UserDto userDto){
        if(userDtoList.stream().noneMatch(t-> t.getId().equals(userDto.getId()))){
            return UserResponse.<Void>builder()
                    .code(-1)
                    .message("This user Id not found " + userDto.getId())
                    .build();
        } else if(userDto.getName() == null || userDto.getMoney() < 0){
            return UserResponse.<Void>builder()
                    .code(1)
                    .message("Validation error")
                    .build();
        }
        for (int i = 0; i < userDtoList.size(); i++) {
            if (userDtoList.get(i).getId().equals(userDto.getId())) {
                userDtoList.get(i).setName(userDto.getName());
                userDtoList.get(i).setMoney(userDto.getMoney());
                break;
            }
        }
        return UserResponse.<Void>builder()
                .success(true)
                .message("Ok")
                .build();
    }
    public UserResponse<Void> deleteUser(Integer id){
        for (UserDto userDto : userDtoList) {
            if (userDto.getId().equals(id)) {
                userDtoList.remove(userDto);
                return UserResponse.<Void>builder()
                        .success(true)
                        .message("Ok")
                        .build();
            }
        }
        return UserResponse.<Void>builder()
                .code(-1)
                .message("This user Id not found " + id)
                .build();
    }
    public UserResponse<Void> putMoney(Integer id, double money){
        if(userDtoList.stream().noneMatch(t-> t.getId().equals(id))){
            return UserResponse.<Void>builder()
                    .code(-1)
                    .message("This user Id not found " + id)
                    .build();
        } else if(money < 0){
            return UserResponse.<Void>builder()
                    .code(1)
                    .message("This money negative")
                    .build();
        }
        for (int i = 0; i < userDtoList.size(); i++) {
            if (userDtoList.get(i).getId().equals(id)) {
                userDtoList.get(i).setMoney(userDtoList.get(i).getMoney() + money);
                break;
            }
        }
        return UserResponse.<Void>builder()
                .success(true)
                .message("Ok")
                .build();
    }
}
