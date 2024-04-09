package de.langner_dev.pg.order.command;

import de.langner_dev.pg.core.command.CreateOrderCmd;
import de.langner_dev.pg.core.event.OrderCreatedEvt;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@NoArgsConstructor
@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    String orderId;

    String articleId;
    String accountId;

    @CommandHandler
    public OrderAggregate(CreateOrderCmd cmd) {
        AggregateLifecycle.apply(new OrderCreatedEvt(cmd.getOrderId(), cmd.getArticleId(), cmd.getAccountId()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvt evt) {
        this.orderId = evt.getOrderId();
        this.articleId = evt.getArticleId();
        this.accountId = evt.getAccountId();
    }

}
