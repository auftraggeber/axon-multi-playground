package de.langner_dev.pg.account.command;

import de.langner_dev.pg.core.command.CreateAccountCmd;
import de.langner_dev.pg.core.event.AccountCreatedEvt;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class AccountAggregate {

    @AggregateIdentifier
    String accountId;

    String name;
    String email;

    @CommandHandler
    public AccountAggregate(CreateAccountCmd cmd) {
        AggregateLifecycle.apply(new AccountCreatedEvt(cmd.getAccountId(), cmd.getName(), cmd.getEmail()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvt evt) {
        this.accountId = evt.getAccountId();
        this.name = evt.getName();
        this.email = evt.getEmail();
    }
}
