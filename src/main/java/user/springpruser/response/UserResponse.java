package user.springpruser.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse <T>{
    private String message;
    private int code;      // -1 - not found, 0 - ok, 1 - validation
    private boolean success;
    private T data;
}
