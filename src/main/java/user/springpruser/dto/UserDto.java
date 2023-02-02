package user.springpruser.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private double money;
}
