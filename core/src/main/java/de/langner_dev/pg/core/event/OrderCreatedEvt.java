package de.langner_dev.pg.core.event;

import lombok.Value;

@Value
public class OrderCreatedEvt {

    String orderId;

    String articleId;
    String accountId;

}
