package org.owasp.pangloss.infra.item;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.owasp.pangloss.domain.item.Item;
import org.owasp.pangloss.domain.item.Items;
import org.owasp.pangloss.domain.item.NoItemsFoundForThisCategoryException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static java.util.Optional.ofNullable;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;

@Repository
public final class ItemsFileRepository implements Items {

    private final ObjectMapper objectMapper;

    @Value("classpath:data/itemsByCategory.json")
    private Resource itemsByCategoryFile;

    public ItemsFileRepository() {
        objectMapper = json().build();
    }

    @Override
    public Set<Item> getAllItemsOfCategory(String categoryId) {
        Map<String, Set<Item>> itemsByCategory = readItemsByCategory();
        return ofNullable(itemsByCategory.get(categoryId))
                .orElseThrow(() -> new NoItemsFoundForThisCategoryException(categoryId));
    }

    private Map<String, Set<Item>> readItemsByCategory() {
        Map<String, Set<Item>> itemsByCategory;
        try {
            itemsByCategory = objectMapper.readValue(itemsByCategoryFile.getInputStream(), new TypeReference<Map<String, Set<Item>>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return itemsByCategory;
    }
}