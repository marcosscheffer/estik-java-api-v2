package com.marcos.estik.user.application.port.in;

import com.marcos.estik.user.application.command.ExistsByUsernameCommand;

public interface ExistsByUsernamePort {
    Boolean execute(ExistsByUsernameCommand command);
}
