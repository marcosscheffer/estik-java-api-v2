package com.marcos.estik.user.application.port.in;

import com.marcos.estik.user.application.view.UserView;

public interface FindUserByUsernamePort {
    UserView execute(String command);
}
