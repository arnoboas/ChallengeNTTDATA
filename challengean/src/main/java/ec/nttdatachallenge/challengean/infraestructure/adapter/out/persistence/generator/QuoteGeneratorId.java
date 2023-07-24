package ec.nttdatachallenge.challengean.infraestructure.adapter.out.persistence.generator;


import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.r2dbc.core.Parameter;

import org.springframework.stereotype.Component;

import ec.nttdatachallenge.challengean.infraestructure.adapter.out.persistence.entity.QuoteEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class QuoteGeneratorId implements BeforeSaveCallback<QuoteEntity> {
    @Override
    public Publisher<QuoteEntity> onBeforeSave(QuoteEntity entity, OutboundRow row, SqlIdentifier table) {
        return Mono.fromCallable(() -> entity)
                .map( quoteEntity -> {
        if (quoteEntity.getPurchaseId() == null) {
            quoteEntity.setPurchaseId(UUID.randomUUID().toString());
            row.append("purchase_id", Parameter.from(entity.getPurchaseId()));
        }
        return entity;
    });
}
}
