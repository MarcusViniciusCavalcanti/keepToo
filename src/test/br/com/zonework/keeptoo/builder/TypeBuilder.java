package br.com.zonework.keeptoo.builder;

import br.com.zonework.keeptoo.contract.valueObject.Type;

import java.time.LocalDateTime;

public final class TypeBuilder {
    private Type type;

    private TypeBuilder() {
        type = new Type();

        type.setName("monetário");
        type.setDescription("número inteiro");
    }

    public static TypeBuilder aType() {
        return new TypeBuilder();
    }

    public TypeBuilder withName(String name) {
        type.setName(name);
        return this;
    }

    public TypeBuilder withDescription(String description) {
        type.setDescription(description);
        return this;
    }

    public TypeBuilder withId(Long id) {
        type.setId(id);
        return this;
    }

    public TypeBuilder withCreatedAt(LocalDateTime createdAt) {
        type.setCreatedAt(createdAt);
        return this;
    }

    public TypeBuilder withUpdatedAt(LocalDateTime updatedAt) {
        type.setUpdatedAt(updatedAt);
        return this;
    }

    public Type build() {
        return type;
    }
}
