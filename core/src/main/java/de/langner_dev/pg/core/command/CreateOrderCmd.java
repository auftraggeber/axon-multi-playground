package de.langner_dev.pg.core.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateOrderCmd {

    @TargetAggregateIdentifier
    String orderId;

    String articleId;
    String accountId;

}
