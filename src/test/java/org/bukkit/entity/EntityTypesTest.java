package org.bukkit.entity;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.server.EntityTypes;
import net.minecraft.server.MinecraftKey;
import org.junit.Assert;
import org.junit.Test;

public class EntityTypesTest {

    @Test
    public void testMaps() {
        Set<EntityType> allBukkit = Arrays.stream(EntityType.values()).filter((b) -> b.getName() != null).collect(Collectors.toSet());

        for (Object o : EntityTypes.REGISTRY) {
            EntityTypes<?> nms = (EntityTypes<?>) o; // Eclipse fail
            MinecraftKey key = EntityTypes.getName(nms);

            EntityType bukkit = EntityType.fromName(key.getKey());
            Assert.assertNotNull("Missing nms->bukkit " + key, bukkit);

            Assert.assertTrue("Duplicate entity nms->" + bukkit, allBukkit.remove(bukkit));
        }

        Assert.assertTrue("Unmapped bukkit entities " + allBukkit, allBukkit.isEmpty());
    }
}