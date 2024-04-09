package de.langner_dev.pg.core.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateAccountCmd {

    @TargetAggregateIdentifier
    String name;
    String email;
}
