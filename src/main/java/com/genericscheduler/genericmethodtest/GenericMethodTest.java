package com.genericscheduler.genericmethodtest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class GenericMethodTest {

    public void save(ModelsHolder modelsHolder, Security security) {
        Map<String, Counterparty> createdCounterparties = new HashMap<>();
        Supplier<String> keySupplier = security::getCounterpartyId;
        Function<String, Counterparty> function = createdCounterparties::get;
        addOrGetExisting(
                keySupplier,
                security,
                modelsHolder,
                createdCounterparties,
                null
        );
    }

    public void save(Position position) {
    }

    public <Key, Model, Value> Optional<Value> addOrGetExisting(Supplier<Key> keySupplier,
                                                                Model model,
                                                                ModelsHolder models,
                                                                Map<Key, Value> container,
                                                                Function<Model, Value> creator) {
        Key key = keySupplier.get();
        if (Objects.isNull(key)) {
            return Optional.empty();
        }

        Value value = container.get(key);
        if (Objects.isNull(value)) {
            Value newValue = creator.apply(model);
            container.put(key, newValue);
            models.add(newValue.getClass());

            return Optional.of(newValue);
        } else {
            return Optional.of(value);
        }
    }
}
