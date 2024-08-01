package com.xiaoliua.ctl.Enums;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class hayrackPutProperty extends Property<hayrackPuts> {
    private final ImmutableSet<hayrackPuts> values = ImmutableSet.of(hayrackPuts.leaf,hayrackPuts.pliableBranch);
    public hayrackPutProperty(String p_61692_) {
        super(p_61692_, hayrackPuts.class);
    }

    @Override
    public Collection<hayrackPuts> getPossibleValues() {
        return values;
    }

    @Override
    public String getName(hayrackPuts p_61696_) {
        return p_61696_.toString();
    }

    @Override
    public Optional<hayrackPuts> getValue(String p_61701_) {
        return !"leaf".equals(p_61701_) && !"pliableBranch".equals(p_61701_) ? Optional.empty() : Optional.of(hayrackPuts.valueOf(p_61701_));
    }
}
