package de.langner_dev.pg.core.event;

import lombok.Value;

@Value
public class AccountCreatedEvt {

    String accountId;

    String name;
    String email;
}
